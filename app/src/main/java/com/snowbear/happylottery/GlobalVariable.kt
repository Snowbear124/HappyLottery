package com.snowbear.happylottery

import android.app.Application

class GlobalVariable: Application() {
    companion object {
        //指定變數
        private var itemCount = 10

        //設定變數fun
        fun setItemCount(count: Int) {
            this.itemCount = count
        }

        //取得變數fun
        fun getItemCount(): Int {
            return itemCount
        }
    }
}