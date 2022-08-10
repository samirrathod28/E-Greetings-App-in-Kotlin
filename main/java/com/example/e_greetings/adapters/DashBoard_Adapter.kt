package com.example.e_greetings.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.e_greetings.R
import com.example.e_greetings.models.Dashboard_Model
import com.squareup.picasso.Picasso

class DashBoard_Adapter(var context: Context, var list: List<Dashboard_Model>) : BaseAdapter() {
    override fun getCount(): Int {
       return list.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflater= LayoutInflater.from(context);
        var view=inflater.inflate(R.layout.design_dashboard,p2,false)
        var image: ImageView =view.findViewById(R.id.img_dashbaord)
        var txt: TextView =view.findViewById(R.id.txt_dashboard)
        Picasso.get().load(list.get(p0).image_dashboard).placeholder(R.mipmap.ic_launcher).into(image)
        txt.setText(list.get(p0).title_board)
        return view
    }
}