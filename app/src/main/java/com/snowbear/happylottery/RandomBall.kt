package com.snowbear.happylottery

import android.util.Log
import kotlin.random.Random

class LotteryBall {
//    var ballsList = listOf<>()    //留以後有加抽籤重複選項用
    val TAG = "LotteryBall"
    var balls: Int = 0

    //取得隨機彩球的號碼
    fun getBallNumber(list: MutableList<String>): Int {
//         ballsList = list     //留以後有加抽籤重複選項用
        if (list.size > 0) {
            balls = Random.nextInt(list.size) +1
            Log.d(TAG, "Get a ball: $balls")
        }else {
            balls = 0
            Log.d(TAG, "Not any ball.")
        }

        return balls
    }



    //留以後有加抽籤重複選項用
//    fun setRepeat(repeat: Boolean): Int{
//
//        if(repeat == true) {
////            val TAG = "lottery_false"
////            Log.d(TAG, "Ball-${balls}, ${list[balls]}")
//            balls = Random.nextInt(ballsList.size)
//        }
//
//        if(repeat == false) {
////            val TAG = "lottery_true"
////            Log.d(TAG, "Ball-${balls}, ${list[balls]}")
//            if(ballsList.size == 0) {
//
//            }
//
//            balls = Random.nextInt(ballsList.size)
//
//            if(ballsList.size > 1) {
//                ballsList.remove(ballsList[balls])
//            }else {
//                ballsList = list
//            }
//        }
//        return balls
//    }
}
