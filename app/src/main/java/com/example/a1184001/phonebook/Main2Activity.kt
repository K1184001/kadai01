package com.example.a1184001.phonebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ListView
import android.widget.TextView

class Main2Activity : AppCompatActivity() {

    /*companion object{
        val KEY_STATE = "key_state"
    }
    val state = intent.getSerializableExtra(KEY_STATE)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textName = findViewById<TextView>(R.id.textName)

        val name = intent.getStringExtra("textName")
        textName.setText(name)

        val database = DbHelper(this).readableDatabase
        val cursor = database.rawQuery("select _tel from phonetable where _name = '${name}';",null)
        cursor.moveToNext()
        val tel = cursor.getString(cursor.getColumnIndex("_tel"))

        val cursor2 = database.rawQuery("select _name_kana from phonetable where _name = '${name}';",null)
        cursor2.moveToNext()
        val kana = cursor2.getString(cursor2.getColumnIndex("_name_kana"))

        val cursor3 = database.rawQuery("select _school from phonetable where _name = '${name}';",null)
        cursor3.moveToNext()
        val school = cursor3.getString(cursor3.getColumnIndex("_school"))

        val textNameKana = findViewById<TextView>(R.id.textNameKana)
        val textTel = findViewById<TextView>(R.id.textTel)
        val textSchool = findViewById<TextView>(R.id.textSchool)

        textNameKana.setText(kana)
        textTel.setText(tel)
        textSchool.setText(school)



        //val txtPass = findViewById(R.id.txt)

        /*if (state is DatePicker) {
            txtName.setText( (state.txt_Name).toString())
            txt_Name_Kana.setText( ((state._txtName_Kana) /2).toString())
            txt_Tel.setText( (state.txt_Tel).toString())
            txt_School.setText( (state.txt_School).toString())
        }*/

        /*val texts = queryTexts(this)
        val listTable2 = findViewById<ListView>(R.id.listTable2)
        listTable2.adapter = ArrayAdapter<String>(this,
                R.layout.list_usertable2_row,R.id.parent,texts)*/
    }
}
