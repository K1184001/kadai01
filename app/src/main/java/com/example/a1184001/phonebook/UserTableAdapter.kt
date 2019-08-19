package com.example.a1184001.phonebook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class UserTableAdapter(private val context: Context, private val phonetable : List<UserTable>) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)
    override fun getView(p0: Int,p1: View?,p2: ViewGroup?): View {
        val view = p1 ?: createView(p2)

        val user = getItem(p0)

        val viewHolder = view.tag as ViewHolder

        viewHolder.name.text = user.name
        return view
    }

    override fun getItem(p0: Int) = phonetable[p0]
    override fun getItemId(p0: Int) = p0.toLong()
    override fun getCount() = phonetable.size
    private class ViewHolder(view: View){

        val name = view.findViewById(R.id.txtName) as TextView
    }
    private fun createView(parent : ViewGroup?) : View{
        val view = inflater.inflate(R.layout.list_usertable_row,parent,false)
        view.tag = ViewHolder(view)
        return view
    }
}