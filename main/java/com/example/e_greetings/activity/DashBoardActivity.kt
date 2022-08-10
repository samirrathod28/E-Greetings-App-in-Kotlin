package com.example.e_greetings.activity

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_greetings.R
import com.example.e_greetings.adapters.DashBoard_Adapter
import com.example.e_greetings.models.Dashboard_Model
import com.example.e_greetings.other.MyUrl
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_dash_board.view.*
import org.json.JSONArray

class DashBoardActivity : AppCompatActivity() {

    lateinit var list: List<Dashboard_Model>
    lateinit var sharedPreferences: SharedPreferences
    lateinit var pd:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        list=ArrayList<Dashboard_Model>()
        sharedPreferences=getSharedPreferences("user_session", MODE_PRIVATE)
        Toast.makeText(applicationContext,"welcome "+sharedPreferences.getString("m1","Samir"),Toast.LENGTH_LONG).show()
        pd= ProgressDialog(this)
        pd.create()
        pd.setMessage("Loading....")
        pd.show()
        val stringRequest=StringRequest(Request.Method.POST,MyUrl.category_dashboard,{ response->

            val jsonArray=JSONArray(response)

                pd.hide()
            for(i in 0 until jsonArray.length())
            {
                val jsonObject=jsonArray.getJSONObject(i)

                val name=jsonObject.getString("name")
                val image=jsonObject.getString("image")

                val m=Dashboard_Model()
                m.title_board=name
                m.image_dashboard=image
                (list as ArrayList<Dashboard_Model>).add(m)
            }
            val db=DashBoard_Adapter(applicationContext,list)
            gridview.adapter=db

            gridview.setOnItemClickListener(){
                    adapterView: AdapterView<*>, view: View, i: Int, l: Long ->

                when(i) {

                    0 ->
                    {
                        val i2 = Intent(this, CategoryActivity::class.java)
                        i2.putExtra("Key", i)
                        startActivity(i2)

                    }


                    1 -> {
                        val i2 = Intent(this, CategoryActivity::class.java)
                        i2.putExtra("Key", i)
                        startActivity(i2)
                    }


                    2 -> {
                        val i2 = Intent(this, CategoryActivity::class.java)
                        i2.putExtra("Key", i)
                        startActivity(i2)

                    }


                    3 -> {
                        val i2 = Intent(this, CategoryActivity::class.java)
                        i2.putExtra("Key", i)
                        startActivity(i2)

                    }
                    4 -> {
                        val i2 = Intent(this, CategoryActivity::class.java)
                        i2.putExtra("Key", i)
                        startActivity(i2)

                    }
                    5 -> {
                        val i2 = Intent(this, CategoryActivity::class.java)
                        i2.putExtra("Key", i)
                        startActivity(i2)

                    }
                    6 -> {
                        val i2 = Intent(this, CategoryActivity::class.java)
                        i2.putExtra("Key", i)
                        startActivity(i2)

                    }
                    else ->{
                        Toast.makeText(this,"Not Open",Toast.LENGTH_LONG).show()
                    }
                }

                }


        })
        {
            pd.hide()
            Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
        }

        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.option_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.logout->
            {
                sharedPreferences.edit().clear().apply()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
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
