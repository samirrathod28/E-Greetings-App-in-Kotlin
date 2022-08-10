package com.example.e_greetings.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.e_greetings.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_verify.*
import java.util.concurrent.TimeUnit

class VerifyActivity : AppCompatActivity() {
    var verificationid=""
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        var i = intent
        var n = i.getStringExtra("number")
        var nu = "+91"+"$n"
        edtnum.setText(nu)

        mAuth = FirebaseAuth.getInstance()
        btn1.setOnClickListener()
        {
            if(TextUtils.isEmpty(edtnum.text))
            {
                Toast.makeText(applicationContext,"Please Enter valid Number ",Toast.LENGTH_LONG).show()
            }
            else
            {

                var num=edtnum.text.toString()
                sendverificationcode(num)
            }
        }
        btn2.setOnClickListener()
        {
            if(TextUtils.isEmpty(edtotp.text.toString()))
            {
                Toast.makeText(applicationContext,"Please Enter valid OTP ",Toast.LENGTH_LONG).show()
            }
            else
            {
                verifyCode(edtotp.text.toString());
            }
        }
    }
    private val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken)
        {
            super.onCodeSent(p0, p1)
            verificationid=p0
        }
        override fun onVerificationCompleted(p0: PhoneAuthCredential)
        {
            val code=p0.smsCode
            if (code != null)
            {

                edtotp!!.setText(code)

                verifyCode(code)
            }

        }
        override fun onVerificationFailed(p0: FirebaseException)
        {
            Toast.makeText(applicationContext,""+p0.message,Toast.LENGTH_LONG).show()
        }

    }

    private fun verifyCode(code: String)
    {

        val credential = PhoneAuthProvider.getCredential(verificationid, code)
        signInWithCredential(credential)

    }

    private fun signInWithCredential(credential: PhoneAuthCredential)
    {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener{task->
                if(task.isSuccessful)
                {
                    var i= Intent(this,DashBoardActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }

            }
            .addOnFailureListener()
            {
                Toast.makeText(applicationContext,"fail",Toast.LENGTH_LONG).show()
            }
    }

    private fun sendverificationcode(num: String)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(num,60, TimeUnit.SECONDS,this,mCallBack)
    }
    override fun onBackPressed() {

        var alert= AlertDialog.Builder(this)
        alert.setTitle( Html.fromHtml("<font color='#545454'>Re-Enter Registration Details??</font>"))
        alert.setPositiveButton("YES",{
                dialogInterface: DialogInterface, i: Int ->
            startActivity(Intent(this,RegisterActivity::class.java))
        })
        alert.setNegativeButton("No",{
                dialogInterface: DialogInterface, i: Int ->

            dialogInterface.cancel()

        })
        alert.show()

    }
}