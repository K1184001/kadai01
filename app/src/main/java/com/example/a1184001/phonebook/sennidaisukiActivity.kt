package com.example.a1184001.phonebook

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

class sennidaisukiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sendaisuki)

        //Toast.makeText(this, "遷移してますねぇ！", LENGTH_LONG).show()

        val dbHelper = DbHelper(this)
        val db = dbHelper.writableDatabase

        //挿入ボタン
        val btnInsert = findViewById(R.id.btnInsert) as Button
        btnInsert.setOnClickListener {

            val edt_NameEditText = findViewById<EditText>(R.id.edtName)
            val edt_NameText: String = edt_NameEditText.text.toString()
            val edt_Name_KanaEditText = findViewById<EditText>(R.id.edt_Name_Kana)
            val edt_Name_KanaText: String = edt_Name_KanaEditText.text.toString()
            val edt_TelEditText = findViewById<EditText>(R.id.edtTel)
            val edt_TelText: String = edt_TelEditText.text.toString()
            val edt_SchoolEditText = findViewById<EditText>(R.id.edtSchool)
            val edt_SchoolText: String = edt_SchoolEditText.text.toString()

            var isValid = true

            if (edt_NameText.isEmpty()) {
                edt_NameEditText.error = "名前を入力してください"
                isValid = false
            }
            if (edt_Name_KanaText.isEmpty()) {
                edt_Name_KanaEditText.error = "名前(カナ)を入力してください"
                isValid = false
            }
            if (edt_TelText.isEmpty()) {
                edt_TelEditText.error = "電話番号を入力してください"
                isValid = false
            }
            if(edt_TelText.isEmpty()){
                edt_TelEditText.error = "‐を入力してもう一度登録してください"
            }
            if (edt_SchoolText.isEmpty()) {
                edt_SchoolEditText.error = "学校名を入力してください"
                isValid = false
            }
            if (isValid) {
                //インサート
                if (isPraimaryKeyCheck(db, edt_TelEditText.text.toString())) {
                    Toast.makeText(this, "電話番号が重複しています。", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "追加しました", Toast.LENGTH_SHORT).show()
                    val values = ContentValues()
                    values.put("_name", edt_NameEditText.text.toString())
                    values.put("_name_kana", edt_Name_KanaEditText.text.toString())
                    values.put("_tel", edt_TelEditText.text.toString())
                    values.put("_school", edt_SchoolEditText.text.toString())
                    db.insertOrThrow("phonetable", null, values)
                    edt_NameEditText.setText("")
                    edt_Name_KanaEditText.setText("")
                    edt_TelEditText.setText("")
                    edt_SchoolEditText.setText("")
                }
            }
        }
            val btnDelete = findViewById(R.id.btnDelete) as Button
            btnDelete.setOnClickListener {

                val edt_TelEditText = findViewById<EditText>(R.id.edtTel)
                val edt_TelText: String = edt_TelEditText.text.toString()

                var isValid = true

                if (edt_TelText.isEmpty()) {
                    edt_TelEditText.error = "電話番号を入力してください"
                    isValid = false
                }
                if(isValid) {
                    //ディレート
                    if (isPraimaryKeyCheck(db, edt_TelEditText.text.toString())) {
                        Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show()
                        db.delete("phonetable", "_tel = ?", arrayOf(edt_TelEditText.text.toString()))
                    } else {
                        Toast.makeText(this, "入力された電話番号が存在しません", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    fun isPraimaryKeyCheck(db: SQLiteDatabase, key:String):Boolean{
        //存在する場合はtrueを返す
        val sql = "select count(*) as cnt from phonetable where _tel = ?"
        val cur = db.rawQuery(sql,arrayOf(key))
        cur.moveToFirst()
        val cnt = cur.getInt(cur.getColumnIndex("cnt"))
        cur.close()
        return cnt > 0
    }
}
