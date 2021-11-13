package com.snowbear.happylottery

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.DrawableContainer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation
import android.provider.ContactsContract
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DataItemSet : AppCompatActivity() {
    val TAG = DataItemSet::class.java.simpleName
    val intentDataItemSet = Intent()
    val setDataName = "What Data Name?"
    var intentDataName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_item_set)

        val data_name = findViewById<Button>(R.id.data_name)
//        data_name.setText(DataActivity_dataName)

        data_name.setOnClickListener {
            //使用LayoutInflater取得另一個Activity的元件資料
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_data_name, null)
            val Bundle = AlertDialog.Builder(this)
            Bundle.setView(view)

            val Dialog = Bundle.create()
            Dialog.show()
            Dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // 消除背景

            val butYes = view.findViewById<Button>(R.id.but_yes)  //view.findViewById是用來找其他.xml的元件id
            butYes.setOnClickListener {

            }
        }

//        data_name.setText("Love fruit")
//        Log.d(TAG, "DataName1: Love fruit")

        val dataSave = findViewById<Button>(R.id.data_save)
        dataSave.setOnClickListener {
            setResult(Activity.RESULT_OK)   //回傳RESULT_OK
            finish()
        }
    }

    //Activity週期變化顯示---------------------------------------------------
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
//--------------------------------------------------------------
    fun backUp(view: View) {
        finish() //關閉Activity介面
    }
}