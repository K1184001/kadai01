package com.example.a1184001.phonebook

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_usertable_row.view.*
import org.w3c.dom.Text

data class UserTable(val name:String,val kana:String,val tel:String,val school:String)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sennidaisukibutton = findViewById(R.id.sennidaisukibutton) as Button
        sennidaisukibutton.setOnClickListener {
            val intent = Intent(this, sennidaisukiActivity::class.java)
            startActivity(intent)
        }
        //リストビュー
        val listTable = findViewById(R.id.listTable) as ListView

        /*val users = listOf(
                UserTable("川下","080-3150-3150"))

        val sql = "select * from phonetable"
        val cur = db.rawQuery(sql,null)

        while(cur.moveToNext()){
            var id = cur.getString(cur.getColumnIndex("type"))
            var name = cur.getString(cur.getColumnIndex("date"))

        }

        cur.close()*/

        /*val adapter = UserTableAdapter(
                this,
                users
        )
        listTable.adapter = adapter*/


        show()

    }

    override fun onStart(){
        super.onStart()
        show()
    }

    private fun show(){
        //val states = arrayListOf<Text>()
        val texts = queryTexts(this)
        val listTable = findViewById<ListView>(R.id.listTable)
        listTable.adapter = ArrayAdapter<String>(this,
                R.layout.list_usertable_row,R.id.txtName,texts)
        listTable.setOnItemClickListener { _name, view, _, _ ->
            val intent2 = Intent(this, Main2Activity::class.java)
            intent2.putExtra("textName",view.txtName.text.toString())
            startActivity(intent2)
        }
    }
}
