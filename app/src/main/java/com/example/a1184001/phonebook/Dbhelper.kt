package com.example.a1184001.phonebook

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbHelper(var context : Context) : SQLiteOpenHelper(context,"UserDB",null,1){
    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL("drop table if exists phonetable;")
        onCreate(p0)
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table phonetable(_tel text primary key,_name text not null,_name_kana text not null,_school text );")
    }
}

fun queryTexts(context:Context) : List<String>{
    val database = DbHelper(context).readableDatabase
    val cursor = database.query(
            "phonetable",null,null,null,null,null,null)

    val texts = mutableListOf<String>()
    cursor.use{
        while(cursor.moveToNext()){
            val text = cursor.getString(cursor.getColumnIndex("_name"))
            texts.add(text)
        }
    }
    database.close()
    return texts
}

fun getTel(context: Context, _name: String): String{
    val database = DbHelper(context).readableDatabase
    val cursor = database.rawQuery("select _tel from usertable where _name = '${_name}';",null)
    cursor.moveToNext()
    val tel = cursor.getString(cursor.getColumnIndex("_name"))
    return tel
}