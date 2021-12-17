package com.snowbear.happylottery

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class DataItemSet : AppCompatActivity() {
    val TAG = DataItemSet::class.java.simpleName
    var itemCount = GlobalVariable.getItemCount()   //  itemCount = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_item_set)

        val but_cancel = findViewById<Button>(R.id.data_cancel)
        val but_Save = findViewById<Button>(R.id.data_save)
        val but_data = findViewById<Button>(R.id.data_name)

        butAnim()
        getSharedPreData()  //sharedPre取得資料

        but_data.setOnClickListener {
            touchDataButton()
        }

        but_Save.setOnClickListener {
            touchSavaButton()
        }

        but_cancel.setOnClickListener{
            finish()    //關閉Activity介面
        }
    }

    private fun butAnim() {
        val anim = GlobalVariable().butAction
        //使用LayoutInflater取得另一個Activity的元件資料
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_data_name, null)
        val dialog_but_yes = view.findViewById<Button>(R.id.but_yes)  //view.findViewById是用來找其他.xml的元件id
        val dialog_data_text = view.findViewById<EditText>(R.id.data_name)

        dialog_but_yes.setOnTouchListener(anim)
        dialog_data_text.setOnTouchListener(anim)

        val but_data = findViewById<Button>(R.id.data_name)
        val but_cancel = findViewById<Button>(R.id.data_cancel)
        val but_Save = findViewById<Button>(R.id.data_save)

        but_data.setOnTouchListener(GlobalVariable().butAction)
        but_cancel.setOnTouchListener(anim)
        but_Save.setOnTouchListener(anim)
    }

    private fun touchDataButton() {
        val but_data = findViewById<Button>(R.id.data_name)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_data_name, null)
        val dialog_but_yes = view.findViewById<Button>(R.id.but_yes)  //view.findViewById是用來找其他.xml的元件id
        val dialog_data_text = view.findViewById<EditText>(R.id.data_name)

        val Bundle = AlertDialog.Builder(this).setView(view)
        val Dialog = Bundle.show()
        Dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // 消除背景

        dialog_data_text.setText(but_data.text.toString()) // 設定dialog的data name與本Activity相同

        dialog_but_yes.setOnClickListener {
            //取得id:data_name的輸入資料，並設定回this的按鈕text上
            but_data.setText(dialog_data_text.text)
            Log.d(TAG, "Dialog Data name: ${dialog_data_text.text}")

            Dialog.dismiss()
        }
    }

    private fun touchSavaButton() {
        val shareLogin = getSharedPreferences(GlobalVariable.login, MODE_PRIVATE)
        val shareData_1 = getSharedPreferences(GlobalVariable.data_1, MODE_PRIVATE)
        val shareData_2 = getSharedPreferences(GlobalVariable.data_2, MODE_PRIVATE)
        val shareData_3 = getSharedPreferences(GlobalVariable.data_3, MODE_PRIVATE)
        val shareData_4 = getSharedPreferences(GlobalVariable.data_4, MODE_PRIVATE)

        val data_state: Int = shareLogin.getInt("data_state", 1)
        val but_data = findViewById<Button>(R.id.data_name)
        val dataText: String = but_data.text.toString()

        if(dataText != "") {
            if(data_state == 1) {
                if(dataText != "") {
                    shareData_1.edit().putString("dataName", dataText).apply()
                }
                saveItem(shareData_1)
                Log.d(TAG, "Save data 1: ${dataText}")

            }else if(data_state == 2) {
                if(dataText != "") {
                    shareData_2.edit().putString("dataName", dataText).apply()
                }
                saveItem(shareData_2)
                Log.d(TAG, "Save data 2: ${dataText}")

            }else if(data_state == 3) {
                if(dataText != "") {
                    shareData_3.edit().putString("dataName", dataText).apply()
                }
                saveItem(shareData_3)
                Log.d(TAG, "Save data 3: ${dataText}")

            }else if(data_state == 4) {
                if(dataText != "") {
                    shareData_4.edit().putString("dataName", dataText).apply()
                }
                saveItem(shareData_4)
                Log.d(TAG, "Save data 4: ${dataText}")

            }
            finish()
        }else {
            val messenger = getString(R.string.not_data_name)
            Toast
                .makeText(this, messenger, Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun getSharedPreData() {
        val shareLogin = getSharedPreferences(GlobalVariable.login, MODE_PRIVATE)
        val shareData_1 = getSharedPreferences(GlobalVariable.data_1, MODE_PRIVATE)
        val shareData_2 = getSharedPreferences(GlobalVariable.data_2, MODE_PRIVATE)
        val shareData_3 = getSharedPreferences(GlobalVariable.data_3, MODE_PRIVATE)
        val shareData_4 = getSharedPreferences(GlobalVariable.data_4, MODE_PRIVATE)

        val data_state: Int = shareLogin.getInt("data_state", 1)
        Log.d(TAG, "data_state: $data_state")

        val but_data = findViewById<Button>(R.id.data_name)
        val DATA_1 = getString(R.string.data_1)
        val DATA_2 = getString(R.string.data_2)
        val DATA_3 = getString(R.string.data_3)
        val DATA_4 = getString(R.string.data_4)

        itemAdd(itemCount)

        if (data_state == 1) {
            val dataName = shareData_1.getString("dataName", DATA_1)
            but_data.text = dataName
            getItem(shareData_1)

        } else if (data_state == 2) {
            val dataName = shareData_2.getString("dataName", DATA_2)
            but_data.text = dataName
            getItem(shareData_2)

        } else if (data_state == 3) {
            val dataName = shareData_3.getString("dataName", DATA_3)
            but_data.text = dataName
            getItem(shareData_3)

        } else if (data_state == 4) {
            val dataName = shareData_4.getString("dataName", DATA_4)
            but_data.text = dataName
            getItem(shareData_4)

        } else {
            but_data.text = getString(R.string.read_error)
        }
    }

    private val itemViewList = mutableListOf<View>()
    private val itemEditList = mutableListOf<EditText>()
    private val itemNumberList = mutableListOf<TextView>()

    private fun itemAdd(count: Int) {
        val layout_item = findViewById<LinearLayout>(R.id.layout_item)

        for (x in 0..count-1) { //指定id
            itemViewList.add( //加到layout裡的data_item
                LayoutInflater.from(this)
                    .inflate(R.layout.data_item, null, false)
            )
            itemEditList.add(itemViewList.get(x).findViewById(R.id.item_date))  //取得各個view, edit的資料id
            itemNumberList.add(itemViewList.get(x).findViewById(R.id.item_number))  //取得各個view, 開頭數字的id
        }

        for (x in 0..count-1) { //介面顯示
            layout_item.addView(itemViewList.get(x))
            itemNumberList.get(x).setText((x+1).toString())
        }
    }

    private fun getItem(sharePre: SharedPreferences) { //讀取資料
        for (x in 0..itemViewList.size-1) {
            val getData = sharePre.getString("item_${x+1}", "")
            itemEditList.get(x).setText(getData).toString()
        }
    }

    private fun saveItem(sharePre: SharedPreferences) {    //儲存資料
        for (x in 0..itemViewList.size-1) {
            val text = itemEditList.get(x).text
            sharePre.edit().putString("item_${x+1}", text.toString()).apply()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_anim_transition, R.anim.fade_out)
    }
}