package com.example.tdm2_td2.exercice4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.tdm2_td2.R
import com.example.tdm2_td2.models.Todo


class Adapter(val context: Context, val data: List<Todo>):BaseAdapter() {

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        val holder:ViewHolder
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.todo_list_item,parent,false)
            val title = view.findViewById(R.id.title) as TextView
            val todo = view.findViewById(R.id.todo) as TextView
            val completed = view.findViewById(R.id.completed) as TextView
            holder = ViewHolder(todo,title,completed)
            view.tag = holder
        }
        else {
            holder = view.tag as ViewHolder
        }
        holder.todo.text = "Todo #${data[position].id}"
        holder.title.text = data[position].title
        if (data[position].completed) {
            holder.completed.text = "✓ Completed."

        } else {
            holder.completed.text = "✘ Not Completed."
        }
        return view
    }

    private class ViewHolder(val todo: TextView,val title: TextView, val completed: TextView)

}