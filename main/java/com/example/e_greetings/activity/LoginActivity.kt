package com.example.e_greetings.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_greetings.R
import com.example.e_greetings.other.MyUrl
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences=getSharedPreferences("user_session", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("user_session", false) && !sharedPreferences.getString("m1", "")!!.isEmpty())
        {
            val i = Intent(this, DashBoardActivity::class.java)
            startActivity(i)
            finish()
        }
        btnLogin.setOnClickListener()
        {
            var number=edtMobile.text.toString()
            var pass=edtPass.text.toString()


            var stringRequest: StringRequest =object :
                StringRequest(Request.Method.POST, MyUrl.login, Response.Listener { response->

                    if(response.trim() == "0")
                    {
                        Toast.makeText(applicationContext,"Fail",Toast.LENGTH_LONG).show()
                    }
                    else
                    {

                        Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_LONG).show()
                        var editor:SharedPreferences.Editor=sharedPreferences.edit()
                        editor.putString("m1",number)
                        editor.putString("p1",pass)
                        sharedPreferences.edit().putBoolean("user_session",true).commit();
                        editor.apply()
                        editor.commit()

                        startActivity(Intent(this,DashBoardActivity::class.java))
                    }
                }
                    ,
                    {
                        Toast.makeText(applicationContext,"No Internet", Toast.LENGTH_LONG).show()
                    })
            {
                override fun getParams(): MutableMap<String, String>
                {
                    var hashmap=HashMap<String,String>()
                    hashmap.put("phone",number)
                    hashmap.put("password",pass)
                    return hashmap
                }
            }
            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)
        }
        btnNewUser.setOnClickListener()
        {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }
    override fun onBackPressed() {

        var alert= AlertDialog.Builder(this)
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