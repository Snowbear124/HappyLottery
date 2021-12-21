package com.snowbear.happylottery

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.view.MotionEvent
import android.view.View

class GlobalVariable: Application() {
    companion object {
        //指定變數
        private var itemCount = 30

        //設定變數fun
        fun setItemCount(count: Int) {
            this.itemCount = count
        }

        //取得變數fun
        fun getItemCount(): Int {
            return itemCount
        }

        //sharedPre 資料夾名稱設定
        val login = "login_app"
        val data_1 = "data_1"
        val data_2 = "data_2"
        val data_3 = "data_3"
        val data_4 = "data_4"
        val data_5 = "data_5"
        val data_6 = "data_6"

        //sharePre 特定資料名稱
        val data_state = "data_state"
        val anim_switch = "lottrey_anim"
    }



    //按鈕按壓時的大小變化
    val butAction = object: View.OnTouchListener{
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when(event?.action) {
                MotionEvent.ACTION_DOWN ->
                    v?.animate()?.scaleX(0.9f)?.scaleY(0.9f)?.setDuration(100)?.start()
                MotionEvent.ACTION_UP ->
                    v?.animate()?.scaleX(1f)?.scaleY(1f)?.setDuration(150)?.start()
            }
            return false
        }
    }
}