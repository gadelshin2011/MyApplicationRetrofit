package com.example.myapplicationretrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationretrofit.R
import com.example.myapplicationretrofit.databinding.ActivityMainBinding
import com.example.myapplicationretrofit.model.level2.Result
import com.squareup.picasso.Picasso

class RcAdapter:RecyclerView.Adapter<RcAdapter.MyHolder>() {

    var listItem = ArrayList<Result>()

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.rc_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.personName).text = listItem[position].name
        holder.itemView.findViewById<TextView>(R.id.personGender).text = listItem[position].gender
        holder.itemView.findViewById<TextView>(R.id.personStatus).text = listItem[position].status
        Picasso.get().load(listItem[position].image).into(holder.itemView.findViewById<ImageView>(R.id.photoPerson))
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<Result>){
        listItem = list as ArrayList<Result>
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addList(list:List<Result>){
        listItem.addAll(list)
        notifyDataSetChanged()
    }

}