package com.snowbear.happylottery

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.DrawableContainer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation
import android.provider.ContactsContract
import android.text.Layout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DataItemSet : AppCompatActivity() {
    val TAG = DataItemSet::class.java.simpleName
    var datas = mutableListOf<String>()
    var itemCount = GlobalVariable.getItemCount()   //  itemCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_item_set)

        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)
        val data_state: Int = shareLogin.getInt("data_state", 0)
        Log.d(TAG, "Get data_state: $data_state")

        val data_name = findViewById<Button>(R.id.data_name)
        data_name.setOnTouchListener(butAction)

        itemAdd(itemCount)  //item的數量

        if(data_state == 1) {
            val dataName = shareData_1.getString("dataName", "DATA 1")
            data_name.setText(dataName)
            getItem(shareData_1)
//            Log.d(TAG, "Data name: ${data_name.text}")

        }else if(data_state == 2) {
            val dataName = shareData_2.getString("dataName", "DATA 2")
            data_name.setText(dataName)
            getItem(shareData_2)
//            Log.d(TAG, "Data name: ${data_name.text}")

        }else if(data_state == 3) {
            val dataName = shareData_3.getString("dataName", "DATA 3")
            data_name.setText(dataName)
            getItem(shareData_3)
//            Log.d(TAG, "Data name: ${data_name.text}")

        }else if(data_state == 4) {
            val dataName = shareData_4.getString("dataName", "DATA 4")
            data_name.setText(dataName)
            getItem(shareData_4)
//            Log.d(TAG, "Data name: ${data_name.text}")

        }else {
            data_name.setText("No find data")
        }

        //使用LayoutInflater取得另一個Activity的元件資料
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_data_name, null)
        val dialog_but_yes = view.findViewById<Button>(R.id.but_yes)  //view.findViewById是用來找其他.xml的元件id
        val dialog_data_text = view.findViewById<EditText>(R.id.data_name)

        dialog_but_yes.setOnTouchListener(butAction)
        dialog_data_text.setOnTouchListener(butAction)

        data_name.setOnClickListener {
            //使用LayoutInflater取得另一個Activity的元件資料
//            val view = LayoutInflater.from(this).inflate(R.layout.dialog_data_name, null)
//            val dialog_but_yes = view.findViewById<Button>(R.id.but_yes)  //view.findViewById是用來找其他.xml的元件id
//            val dialog_data_name = view.findViewById<EditText>(R.id.data_name)

            val Bundle = AlertDialog.Builder(this).setView(view)
            val Dialog = Bundle.show()
            Dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // 消除背景

            dialog_data_text.setText(data_name.text.toString()) // 設定dialog的data name與本Activity相同

            dialog_but_yes.setOnClickListener {
                //取得id:data_name的輸入資料，並設定回this的按鈕text上
                data_name.setText(dialog_data_text.text)
                Log.d(TAG, "Dialog Data name: ${dialog_data_text.text}")
                Dialog.dismiss()
            }
        }

        val but_cancel = findViewById<Button>(R.id.data_cancel)
        val but_Save = findViewById<Button>(R.id.data_save)

        but_cancel.setOnTouchListener(butAction)
        but_Save.setOnTouchListener(butAction)

        but_Save.setOnClickListener {
//            intentDataName = data_name.text.toString()  //將傳輸用的變數，指定為data name
//            Log.d(TAG, "Intent Data name: ${intentDataName}")
//            intentDataItemSet.putExtra("DATANAME", intentDataName)  //把要返回的值，存進intent
//            setResult(Activity.RESULT_OK, intentDataItemSet)   //回傳RESULT_OK + 剛剛存取的intent值

            val dataText: String = data_name.text.toString()
            if(data_state == 1) {
                shareData_1.edit().putString("dataName", dataText).apply()
                Log.d(TAG, "Save data 1: ${dataText}")

                saveItem(shareData_1)

            }else if(data_state == 2) {
                shareData_2.edit().putString("dataName", dataText).apply()
                Log.d(TAG, "Save data 2: ${dataText}")

                saveItem(shareData_2)

            }else if(data_state == 3) {
                shareData_3.edit().putString("dataName", dataText).apply()
                Log.d(TAG, "Save data 3: ${dataText}")

                saveItem(shareData_3)

            }else if(data_state == 4) {
                shareData_4.edit().putString("dataName", dataText).apply()
                Log.d(TAG, "Save data 4: ${dataText}")

                saveItem(shareData_4)
            }
            finish()
        }

        but_cancel.setOnClickListener{
            finish()    //關閉Activity介面
        }
    }

    //按鈕按壓時的大小變化
    val butAction = object: View.OnTouchListener{
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when(event?.action) {
                MotionEvent.ACTION_DOWN ->
                    v?.animate()?.scaleX(0.85f)?.scaleY(0.85f)?.setDuration(150)?.start()
                MotionEvent.ACTION_UP ->
                    v?.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(150)?.start()
            }
            return false
        }
    }

    val itemViewList = mutableListOf<View>()
    val itemEditList = mutableListOf<EditText>()
    val itemNumberList = mutableListOf<TextView>()

    fun itemAdd(count: Int) {
        val layout_item = findViewById<LinearLayout>(R.id.layout_item)
        var itemCount = count

        for (x in 0..itemCount-1) { //指定id
            itemViewList.add( //加到layout裡的data_item
                LayoutInflater.from(this@DataItemSet)
                    .inflate(R.layout.data_item, null, false)
            )
            itemEditList.add(itemViewList.get(x).findViewById(R.id.item_date))  //取得各個view, edit的資料id
            itemNumberList.add(itemViewList.get(x).findViewById(R.id.item_number))  //取得各個view, 開頭數字的id
        }

        for (x in 0..itemCount-1) { //介面顯示
            layout_item.addView(itemViewList.get(x))
            itemNumberList.get(x).setText((x+1).toString())
        }
    }

    fun getItem(shareData: SharedPreferences) { //讀取資料
        for (x in 0..itemViewList.size-1) {
            val getData = shareData.getString("item_${x+1}", "")
            itemEditList.get(x).setText(getData)
        }
    }

    fun saveItem(shareData: SharedPreferences) {    //儲存資料
        for (x in 0..itemViewList.size-1) {
            val text = itemEditList.get(x).text
            shareData.edit().putString("item_${x+1}", text.toString()).apply()
//            if(text.length > 0) {
//                Log.d(TAG, "saveItem_${x+1}: ${text}")
//            }else {
//                Log.d(TAG, "saveItem_${x+1}: empty")
//            }
        }
    }

    //Activity週期變化顯示---------------------------------------------------
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart: ")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume: ")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause: ")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop: ")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d(TAG, "onRestart: ")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy: ")
//    }
//--------------------------------------------------------------
}

class DataItem(){

}