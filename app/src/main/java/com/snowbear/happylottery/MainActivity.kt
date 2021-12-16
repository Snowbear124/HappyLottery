package com.snowbear.happylottery

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    //也可以直接用"MainActivity"字串，但用class這個方式不占記憶體空間，會比較好
    val TAG = MainActivity::class.java.simpleName
    var itemList = mutableListOf<String>()
    var itemCount = GlobalVariable.getItemCount()
    var lotteryTouchFlag = false
    var nullDataFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val but_dataName = findViewById<Button>(R.id.data_name)
        val but_start = findViewById<Button>(R.id.but_start)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_set = findViewById<Button>(R.id.but_set)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)

        butAnim()
        getSharedPreData()
        lotteryTouchFlag = false

        but_dataName.setOnClickListener{
            touchDataButton()
        }

        but_set.setOnClickListener{
            touchSetButton()
        }

        but_lottery.setOnClickListener {
            lotteryAnim()  //跳到抽獎動畫
        }

        but_lottery_result.setOnClickListener {
            lotteryAnim()
        }

        but_start.setOnClickListener {
            lotteryAnim()
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
        getSharedPreData()

        val but_start = findViewById<Button>(R.id.but_start)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)

        but_lottery.setOnClickListener {
            lotteryAnim()  //跳到抽獎動畫
        }

        but_lottery_result.setOnClickListener {
            lotteryAnim()
        }

        but_start.setOnClickListener {
            lotteryAnim()
        }

        //按下抽獎按鈕後的畫面
        lotteryAction(lotteryTouchFlag)
    }

    private fun getSharedPreItem(shareData: SharedPreferences) {
        itemList.clear()

        for (x in 0..itemCount-1) {
            val item = shareData.getString("item_${x+1}", "").toString()

            itemList.add(item)

            if (item.length == 0) { //排除空的資料
                itemList.remove(item)
            }
        }

        nullDataFlag = (itemList.size == 0)   //nnullDataFlag = itemList.size時,也等於0 (false)
    }

    private fun getSharedPreData() {
        val sharedLogin = getSharedPreferences(GlobalVariable.login, MODE_PRIVATE)
        val shareData_1 = getSharedPreferences(GlobalVariable.data_1, MODE_PRIVATE)
        val shareData_2 = getSharedPreferences(GlobalVariable.data_2, MODE_PRIVATE)
        val shareData_3 = getSharedPreferences(GlobalVariable.data_3, MODE_PRIVATE)
        val shareData_4 = getSharedPreferences(GlobalVariable.data_4, MODE_PRIVATE)

        val data_state: Int = sharedLogin.getInt("data_state", 1)
        Log.d(TAG, "data_state: $data_state")

        val but_data = findViewById<Button>(R.id.data_name)

        if (data_state == 1) {
            val dataName = shareData_1.getString("dataName", "DATA 1")
            but_data.text = dataName
            getSharedPreItem(shareData_1)

        } else if (data_state == 2) {
            val dataName = shareData_2.getString("dataName", "DATA 2")
            but_data.text = dataName
            getSharedPreItem(shareData_2)

        } else if (data_state == 3) {
            val dataName = shareData_3.getString("dataName", "DATA 3")
            but_data.text = dataName
            getSharedPreItem(shareData_3)

        } else if (data_state == 4) {
            val dataName = shareData_4.getString("dataName", "DATA 4")
            but_data.text = dataName
            getSharedPreItem(shareData_4)

        } else {
            but_data.setText("Read error.")
        }
    }

    private fun emptyDataHint() {
        val messenger = "No set data itme."
        val toast = Toast.makeText(this, messenger, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        toast.show()
    }

    private fun lotteryAnim() {
        lotteryTouchFlag = true //確認已按下抽獎紐

        if (nullDataFlag == false) {
            val AnimLotteryActivity = Intent(this, AnimLotteryActivity::class.java)
            startActivity(AnimLotteryActivity)  //跳到抽獎動畫
        }else {
            emptyDataHint()
        }
    }
    //抽獎的 開始/結果 畫面顯示 (開始畫面, 結束畫面, 判斷值)
    private fun lotteryIcon(lotteryFlag: Boolean) {
        val layout_lottery_result = findViewById<LinearLayout>(R.id.layout_lottery_result)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_start = findViewById<Button>(R.id.but_start)

        if(lotteryFlag == true) {
            but_lottery.visibility = View.GONE
            layout_lottery_result.visibility = View.VISIBLE
            but_start.text = "AGAIN"
        }else {
            but_lottery.visibility = View.VISIBLE
            layout_lottery_result.visibility = View.GONE
            but_start.text = "START"
        }
    }

    //抽獎的數字的判斷 (彩球, 項目)
    private fun lotteryAction(lotteryFlag: Boolean) {
        val tV_lottery_result = findViewById<TextView>(R.id.tV_lottery_result)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)
        val balls: Int = LotteryBall().getBallNumber(itemList)

        lotteryIcon(lotteryFlag)

        if (itemList.size == 0) {
            but_lottery_result.text = ""
            tV_lottery_result.setText("Read error.")

        } else {
            but_lottery_result.text = balls.toString()
            tV_lottery_result.text = itemList.get(balls - 1)
        }

        // 改變彩球的顏色
        if (balls % 4 == 0) {
            but_lottery_result.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_yellow)
            )
        } else if (balls % 4 == 3) {
            but_lottery_result.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_rad)
            )
        } else if (balls % 4 == 2) {
            but_lottery_result.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_blue)
            )
        } else if (balls % 4 == 1) {
            but_lottery_result.setBackground(
                ContextCompat
                    .getDrawable(this, R.drawable.lotteryball_green)
            )
        }
    }

    private fun butAnim() {
        val but_dataName = findViewById<Button>(R.id.data_name)
        val but_start = findViewById<Button>(R.id.but_start)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_set = findViewById<Button>(R.id.but_set)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)
        val anim = GlobalVariable().butAction

        but_dataName.setOnTouchListener(anim)
        but_lottery.setOnTouchListener(anim)
        but_set.setOnTouchListener(anim)
        but_start.setOnTouchListener(anim)
        but_lottery_result.setOnTouchListener(anim)
    }

    private fun touchSetButton() {
        lotteryTouchFlag = false
        App.addActivity(this)   //將此activity加入list

        // intent是用來做介面傳送功能的程式
        val SetActivity = Intent(this, SetActivity::class.java)
        startActivity(SetActivity)
        overridePendingTransition(R.anim.slide_down_in, R.anim.no_anim_transition)
    }

    private fun touchDataButton() {
        lotteryTouchFlag = false
        val but_data = findViewById<Button>(R.id.data_name)
        val DataActivity = Intent(this, DataActivity::class.java)
        val transitionAnim = ActivityOptions.makeSceneTransitionAnimation(this, but_data, "data_trsnsition")
//        startActivity(DataActivity, transitionAnim.toBundle())
        startActivity(DataActivity)
        overridePendingTransition(R.anim.fade_in, R.anim.no_anim_transition)
    }

    private fun isExit() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_exit, null)
        val but_yes = view.findViewById<Button>(R.id.but_yes)
        val but_no = view.findViewById<Button>(R.id.but_no)

        val bundle = AlertDialog.Builder(this).setView(view)
        val dialog = bundle.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        but_yes.setOnClickListener {
            finish()    //關閉APP
        }

        but_no.setOnClickListener {
            dialog.dismiss() //返回
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            isExit()
            return false    //防止一下按下按鈕就關閉
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
//        if(exitFlag == false) {
//            System.exit(0)  //確保程式完全退出
//        }
    }
//--------------------------------------------------------------
}