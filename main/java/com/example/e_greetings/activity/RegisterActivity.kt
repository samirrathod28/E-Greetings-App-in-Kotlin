package com.example.e_greetings.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_greetings.R
import com.example.e_greetings.other.MyUrl
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        btnRegister.setOnClickListener()
        {
            val fname=edtFirstName.text.toString()
            val lname=edtLastName.text.toString()
            val email=edtEmail.text.toString()
            val phone=edtPhone.text.toString()
            val pass=edtPassword.text.toString()
            val cpass=edtConfirmPassword.text.toString()
            val mobileRegex = "^[0-9]{10}$"
            val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            if (TextUtils.isEmpty(fname)){
                edtFirstName.error="Enter First Name"
            }else if (TextUtils.isEmpty(lname)){
                edtLastName.error="Enter Last Name"
            }else if (TextUtils.isEmpty(email)){
               edtEmail.error="Enter Email ID"
            }else if (!email.matches(Regex(emailRegex))){
                edtEmail.error="Invalid Email ID"
            }
            else if (TextUtils.isEmpty(phone)){
               edtPhone.error="Enter Phone Number"
            }else if (!phone.matches(Regex(mobileRegex))){
                edtPhone.error="Invalid Mobile Number"
            }
            else if (TextUtils.isEmpty(pass)){
                edtPassword.error="Enter Password"
            }else if (TextUtils.isEmpty(cpass)){
                edtConfirmPassword.error="Enter Confirm Password"
            }else if (pass != cpass){
               edtConfirmPassword.error="Not Match"
            } else if(pass == cpass)
            {
                val stringRequest: StringRequest =object :StringRequest(Request.Method.POST, MyUrl.register,
                    Response.Listener {

                    Toast.makeText(applicationContext,"Registration Successful",Toast.LENGTH_LONG).show()
                    val i =Intent(this,VerifyActivity::class.java)
                        i.putExtra("number",phone)
                        startActivity(i)
                }
                    ,
                    {

                        Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()

                    })

                {
                    override fun getParams(): MutableMap<String, String>
                    {
                        val hashmap=HashMap<String,String>()
                        hashmap.put("firstname",fname)
                        hashmap.put("lastname",lname)
                        hashmap.put("email",email)
                        hashmap.put("phone",phone)
                        hashmap.put("password",pass)
                        return hashmap
                    }
                }
                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
            else
            {
                Toast.makeText(applicationContext,"Please Enter Correct Details",Toast.LENGTH_LONG).show()
            }
        }
        btnAlreadyAccount.setOnClickListener(){

            startActivity(Intent(this,LoginActivity::class.java))

        }
    }
    override fun onBackPressed() {

        val alert= AlertDialog.Builder(this)
        alert.setTitle( Html.fromHtml("<font color='#545454'>Want to Exit??</font>"))
        alert.setPositiveButton("YES",{
                dialogInterface: DialogInterface, i: Int ->

            finishAffinity()

        })
        alert.setNegativeButton("No",{
                dialogInterface: DialogInterface, i: Int ->

            dialogInterface.cancel()

        })
        alert.show()

    }
}