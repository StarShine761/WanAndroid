package com.example.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.bean.WxArticle

/**
 * Created by CC
 * On 2021/12/9.
 */
class RecyclerAdapter(private val list: List<WxArticle>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size ?: 0

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        val textpos = list[position]
        holder.name.text = textpos.name
        holder.id.text = textpos.order.toString()
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${holder.name.text}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.wx_id)
        val name: TextView = itemView.findViewById(R.id.wx_name)
    }
}