package com.example.e_greetings.activity

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_greetings.R
import com.example.e_greetings.adapters.Category_Adapter
import com.example.e_greetings.models.Category_Model
import com.example.e_greetings.other.MyUrl
import kotlinx.android.synthetic.main.activity_category.*
import org.json.JSONArray

class CategoryActivity : AppCompatActivity() {

    lateinit var list: List<Category_Model>
    lateinit var sharedPreferences: SharedPreferences
    lateinit var pd:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        list=ArrayList()
        pd= ProgressDialog(this)
        pd.create()
        pd.setMessage("Loading....")
        pd.show()

        val i =intent
        val id_img=i.getIntExtra("Key",101)

        when(id_img)
        {
            0->
            {
                val stringRequest= StringRequest(Request.Method.POST, MyUrl.anni_category,{ response->

                    val jsonArray= JSONArray(response)
                    pd.hide()
                    for(i in 0 until jsonArray.length())
                    {
                        val jsonObject=jsonArray.getJSONObject(i)
                        val image=jsonObject.getString("image")
                        val m=Category_Model()
                        m.image_category=image
                        (list as ArrayList<Category_Model>).add(m)
                    }
                    val category_adapter= Category_Adapter(applicationContext,list)
                    gridview.adapter=category_adapter


                })
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
            1->
            {
                val stringRequest=StringRequest(Request.Method.POST,MyUrl.birthday_category,{ response->

                    var jsonArray=JSONArray(response)
                    pd.hide()
                    for(i in 0 until jsonArray.length())
                    {
                        val jsonObject=jsonArray.getJSONObject(i)
                        val image=jsonObject.getString("image")
                        val m=Category_Model()
                        m.image_category=image
                        (list as ArrayList<Category_Model>).add(m)
                    }
                    val category_adapter=Category_Adapter(applicationContext,list)
                    gridview.adapter=category_adapter


                })
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                val queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
            2->
            {
                val stringRequest=StringRequest(Request.Method.POST,MyUrl.christmas_category,{ response->

                    var jsonArray=JSONArray(response)
                    pd.hide()
                    for(i in 0 until jsonArray.length())
                    {
                        val jsonObject=jsonArray.getJSONObject(i)
                        val image=jsonObject.getString("image")
                        val m=Category_Model()
                        m.image_category=image
                        (list as ArrayList<Category_Model>).add(m)
                    }
                    val category_adapter=Category_Adapter(applicationContext,list)
                    gridview.adapter=category_adapter


                })
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                val queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
            3->
            {
                val stringRequest=StringRequest(Request.Method.POST,MyUrl.diwali_category,{ response->

                    var jsonArray=JSONArray(response)
                    pd.hide()
                    for(i in 0 until jsonArray.length())
                    {
                        val jsonObject=jsonArray.getJSONObject(i)
                        val image=jsonObject.getString("image")
                        val m=Category_Model()
                        m.image_category=image
                        (list as ArrayList<Category_Model>).add(m)
                    }
                    val category_adapter=Category_Adapter(applicationContext,list)
                    gridview.adapter=category_adapter


                })
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                val queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
            4->
            {
                val stringRequest=StringRequest(Request.Method.POST,MyUrl.newyear_category,{ response->

                    var jsonArray=JSONArray(response)
                    pd.hide()
                    for(i in 0 until jsonArray.length())
                    {
                        val jsonObject=jsonArray.getJSONObject(i)
                        val image=jsonObject.getString("image")
                        val m=Category_Model()
                        m.image_category=image
                        (list as ArrayList<Category_Model>).add(m)
                    }
                    val category_adapter=Category_Adapter(applicationContext,list)
                    gridview.adapter=category_adapter


                })
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                val queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
            5->
            {
                val stringRequest=StringRequest(Request.Method.POST,MyUrl.holi_category,{ response->

                    var jsonArray=JSONArray(response)
                    pd.hide()
                    for(i in 0 until jsonArray.length())
                    {
                        val jsonObject=jsonArray.getJSONObject(i)
                        val image=jsonObject.getString("image")
                        val m=Category_Model()
                        m.image_category=image
                        (list as ArrayList<Category_Model>).add(m)
                    }
                    val category_adapter=Category_Adapter(applicationContext,list)
                    gridview.adapter=category_adapter


                })
                {
                    pd.hide()
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                val queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
        }

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

}
