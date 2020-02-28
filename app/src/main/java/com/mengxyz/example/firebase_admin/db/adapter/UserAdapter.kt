package com.mengxyz.example.firebase_admin.db.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mengxyz.example.firebase_admin.R
import com.mengxyz.example.firebase_admin.db.response.User
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(private val context: Context) :RecyclerView.Adapter<UserAdapter.Holder>(){

    private var items:Array<User> = arrayOf()
    private val inflater:LayoutInflater = LayoutInflater.from(context)

    inner class Holder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = inflater.inflate(R.layout.user_item,parent,false)
        val email = v.name
        return Holder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.name.text = items[position].email
    }

    fun update(items:Array<User>){
        this.items = items
        notifyDataSetChanged()
    }

}