package com.snowbear.happylottery

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class LotteryItem: Application() {
    companion object {
        private var itemViewList = mutableListOf<View>()
        private var itemEditList = mutableListOf<EditText>()
        private var itemNumberList = mutableListOf<TextView>()

        fun itemAdd(activity: Activity, count: Int) {
            val layout_item = activity.findViewById<LinearLayout>(R.id.layout_item)

            for (x in 0..count-1) { //指定id
                itemViewList.add( //加到layout裡的data_item
                    LayoutInflater.from(activity)
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

        fun getItem(sharePre: SharedPreferences) { //讀取資料
            for (x in 0..itemViewList.size-1) {
                val getData = sharePre.getString("item_${x+1}", "")
                itemEditList.get(x).setText(getData)
            }
        }

        fun saveItem(sharePre: SharedPreferences) {    //儲存資料
            for (x in 0..itemViewList.size-1) {
                val text = itemEditList.get(x).text
                sharePre.edit().putString("item_${x+1}", text.toString()).apply()
            }
        }
    }
}