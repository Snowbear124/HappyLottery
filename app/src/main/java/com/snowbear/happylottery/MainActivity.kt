package com.snowbear.happylottery

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.transition.Slide
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    //也可以直接用"MainActivity"字串，但用class這個方式不占記憶體空間，會比較好
    val TAG = MainActivity::class.java.simpleName
    val intentMainActivity = Intent()
    var itemList = mutableListOf<String>()
    var itemCount = GlobalVariable.getItemCount()

    val testList = mutableListOf<String>("Apply","Banana","Cherry","Dragonfruit","Fig")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_main)

        val but_dataName = findViewById<Button>(R.id.data_name)
        val but_start = findViewById<Button>(R.id.but_start)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_set = findViewById<Button>(R.id.but_set)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)
        val tV_lottery_result = findViewById<TextView>(R.id.tV_lottery_result)
        val layout_lottery_result = findViewById<LinearLayout>(R.id.layout_lottery_result)
        val intentMainActivity = Intent()

        but_dataName.setOnTouchListener(butAction)
        but_lottery.setOnTouchListener(butAction)
        but_set.setOnTouchListener(butAction)
        but_start.setOnTouchListener(butAction)

        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)

        val reSwitch = shareLogin.getBoolean("switch", true)
        val data_state: Int = shareLogin.getInt("data_state", 1)
        Log.d(TAG, "data_state: $data_state")

        if(data_state == 1) {
            val dataName = shareData_1.getString("dataName", "DATA 1")
            but_dataName.setText(dataName)
            getItem(shareData_1)

        }else if(data_state == 2) {
            val dataName = shareData_2.getString("dataName", "DATA 2")
            but_dataName.setText(dataName)
            getItem(shareData_2)

        }else if(data_state == 3) {
            val dataName = shareData_3.getString("dataName", "DATA 3")
            but_dataName.setText(dataName)
            getItem(shareData_3)

        }else if(data_state == 4) {
            val dataName = shareData_4.getString("dataName", "DATA 4")
            but_dataName.setText(dataName)
            getItem(shareData_4)

        }else {
            but_dataName.setText("No find data")
        }

        but_dataName.setOnClickListener{
            intentMainActivity.setClass(this, DataActivity::class.java)
            startActivity(intentMainActivity)
        }

        val viewLottery = LayoutInflater.from(this).inflate(R.layout.dialog_lottery, null, false)
//        val layout = findViewById<ConstraintLayout>(R.id.main_layout)
//        val anim_lottery = viewLottery.findViewById<ImageView>(R.id.anim_lottery)
        val intentAnimLotteryActivity = intentMainActivity.setClass(this,AnimLotteryActivity::class.java)
        val intentLotteryResultActivity = intentMainActivity.setClass(this, LotteryResultActivity::class.java)
        val balls: Int = LotteryBall().getBallNumber(itemList)

        but_lottery.setOnClickListener {
//            startActivity(intentAnimLotteryActivity)  //跳到抽獎動畫

            lotteryAction(but_lottery, layout_lottery_result, but_lottery_result, tV_lottery_result, balls)
            ballColor(balls, but_lottery_result)    //設定抽到的彩球顏色

        }

        val slide = Slide()
        but_set.setOnClickListener{
            // intent是用來做介面傳送功能的程式
            val intentSetActivity = Intent(this, SetActivity::class.java)
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                this, but_set, ViewCompat.getTransitionName(but_set).toString()
//            )
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, but_set, "set_trsnsition"
            )
            startActivity(intentSetActivity, options.toBundle())    // startActivity指開啟()內的介面, 與開啟時的動畫
        }
    }

    private fun lotteryAction(  //抽獎的結果顯示
        start_icon: Button,
        result_icon: LinearLayout,
        result_but: Button,
        result_text: TextView,
        num: Int
    ) {
        start_icon.visibility = View.GONE
        result_icon.visibility = View.VISIBLE


        if (itemList.size == 0) {
            result_but.setText("0")
            result_text.setText("No data...")

        } else {
            result_but.setText((num).toString())
            result_text.setText(itemList.get(num - 1))
        }
    }

    private fun ballColor(num: Int, but_id: Button) {   //設定抽到的彩球顏色
        if (num % 4 == 0) {
            but_id.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_yellow)
            )
        } else if (num % 4 == 3) {
            but_id.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_rad)
            )
        } else if (num % 4 == 2) {
            but_id.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_blue)
            )
        } else if (num % 4 == 1) {
            but_id.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_green)
            )
        }
    }

    override fun onRestart() {
        super.onRestart()
//        Log.d(TAG, "onRestart: ")

        val but_dataName = findViewById<Button>(R.id.data_name)
        val shareLogin = getSharedPreferences("login_app", MODE_PRIVATE)
        val shareData_1 = getSharedPreferences("data_1", MODE_PRIVATE)
        val shareData_2 = getSharedPreferences("data_2", MODE_PRIVATE)
        val shareData_3 = getSharedPreferences("data_3", MODE_PRIVATE)
        val shareData_4 = getSharedPreferences("data_4", MODE_PRIVATE)

        val data_state = shareLogin.getInt("data_state", 1)
        Log.d(TAG, "Data state: ${shareLogin.getInt("data_state", 1)}")

        if(data_state == 1) {
            val dataName = shareData_1.getString("dataName", "DATA 1")
            but_dataName.setText(dataName)

        }else if(data_state == 2) {
            val dataName = shareData_2.getString("dataName", "DATA 2")
            but_dataName.setText(dataName)

        }else if(data_state == 3) {
            val dataName = shareData_3.getString("dataName", "DATA 3")
            but_dataName.setText(dataName)

        }else if(data_state == 4) {
            val dataName = shareData_4.getString("dataName", "DATA 4")
            but_dataName.setText(dataName)

        }else {
            but_dataName.setText("No find data")
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

    fun lottery(view: View) {
//        intentMainActivity.setClass()
//        val item = testList.random()
//        Log.d(TAG,"Lottery Item = $item")    //標籤，這裡做debug用
    }

    fun getItem(shareData: SharedPreferences) {
        for (x in 0..itemCount-1) {
            val item = shareData.getString("item_${x+1}", "").toString()
            itemList.add(item)

            if (item.length == 0) {
                itemList.remove(item)
            }
        }
    }

//    //設定抽到的彩球顏色
//    fun ballColor(but_background: Button) {
//        if (balls%4 == 0) {
//            but_background.setBackground(ContextCompat.getDrawable(this,R.drawable.lotteryball_yellow))
//
//        }else if (balls%4 == 3) {
//            but_background.setBackgroundColor(R.drawable.lotteryball_rad)
//
//        }else if (balls%4 == 2) {
//            but_background.setBackgroundColor(R.drawable.lotteryball_blue)
//
//        }else if (balls%4 == 1) {
//            but_background.setBackgroundColor(R.drawable.lotteryball_green)
//        }
//    }

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
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy: ")
//    }
//--------------------------------------------------------------
}