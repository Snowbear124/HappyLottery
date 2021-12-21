package com.snowbear.happylottery

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
    var setDataFlag = false
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
        Log.d(TAG, "onRestart:")
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

        //按下設定鈕才會切回彩球，若只是退出則不會
        if(setDataFlag) {
//            lotteryAction(false)
            lotteryIcon(false)    //變回玻璃彩球
            setDataFlag = false
        }else {
            lotteryAction(lotteryTouchFlag)
        }

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
        val shareData_5 = getSharedPreferences(GlobalVariable.data_5, MODE_PRIVATE)
        val shareData_6 = getSharedPreferences(GlobalVariable.data_6, MODE_PRIVATE)

        val data_state: Int = sharedLogin.getInt(GlobalVariable.data_state, 1)
        Log.d(TAG, "data_state: $data_state")

        val but_data = findViewById<Button>(R.id.data_name)
        val DATA_1 = getString(R.string.data_1)
        val DATA_2 = getString(R.string.data_2)
        val DATA_3 = getString(R.string.data_3)
        val DATA_4 = getString(R.string.data_4)
        val DATA_5 = getString(R.string.data_5)
        val DATA_6 = getString(R.string.data_6)

        if (data_state == 1) {
            val dataName = shareData_1.getString("dataName", DATA_1)
            but_data.text = dataName
            getSharedPreItem(shareData_1)

        } else if (data_state == 2) {
            val dataName = shareData_2.getString("dataName", DATA_2)
            but_data.text = dataName
            getSharedPreItem(shareData_2)

        } else if (data_state == 3) {
            val dataName = shareData_3.getString("dataName", DATA_3)
            but_data.text = dataName
            getSharedPreItem(shareData_3)

        } else if (data_state == 4) {
            val dataName = shareData_4.getString("dataName", DATA_4)
            but_data.text = dataName
            getSharedPreItem(shareData_4)

        } else if (data_state == 5) {
            val dataName = shareData_5.getString("dataName", DATA_5)
            but_data.text = dataName
            getSharedPreItem(shareData_5)

        }else if (data_state == 6) {
            val dataName = shareData_6.getString("dataName", DATA_6)
            but_data.text = dataName
            getSharedPreItem(shareData_6)

        } else {
            but_data.text = getString(R.string.read_error)
        }
    }

    private fun emptyDataHint() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_null_data, null)
        val bundle = AlertDialog.Builder(this)
        val dialog = bundle.create()
        dialog.setView(view)
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        hintTime(dialog, 2)
    }

    private fun hintTime(dialog: AlertDialog, times: Long) {
        object : CountDownTimer(times*1000, 1000) {
            override fun onTick(p0: Long) { }
            override fun onFinish() {
                dialog.dismiss()
            }
        }.start()
    }

    private fun lotteryAnimSwitch(): Boolean {
        val sharedLogin = getSharedPreferences(GlobalVariable.login, MODE_PRIVATE)
        val switch_anim = sharedLogin.getBoolean(GlobalVariable.anim_switch, true)

        return switch_anim
    }

    private fun lotteryAnim() {
        lotteryTouchFlag = true //確認已按下抽獎紐

        if (nullDataFlag == false) {    //判斷項目是否為空值
            if (lotteryAnimSwitch()) {
                val AnimLotteryActivity = Intent(this, AnimLotteryActivity::class.java)
                startActivity(AnimLotteryActivity)  //跳到抽獎動畫
                overridePendingTransition(R.anim.fade_in, R.anim.no_anim_transition)
            }else {
                lotteryAction(true) //按下抽獎按鈕後的畫面
            }
        }else {
            emptyDataHint()
        }
    }

    //抽獎的 開始/結果 畫面顯示 (開始畫面, 結束畫面, 判斷值)
    private fun lotteryIcon(lotteryTouchFlag: Boolean) {
        val layout_lottery_result = findViewById<LinearLayout>(R.id.layout_lottery_result)
        val but_lottery = findViewById<Button>(R.id.but_lottery)
        val but_start = findViewById<Button>(R.id.but_start)

        if(lotteryTouchFlag == true) {
            but_lottery.visibility = View.GONE
            layout_lottery_result.visibility = View.VISIBLE
            but_start.text = getString(R.string.anagin)
        }else {
            but_lottery.visibility = View.VISIBLE
            layout_lottery_result.visibility = View.GONE
            but_start.text = getString(R.string.start)
        }
    }

    //抽獎的數字的判斷 (彩球, 項目)
    private fun lotteryAction(lotteryTouchFlag: Boolean) {
        val tV_lottery_result = findViewById<TextView>(R.id.tV_lottery_result)
        val but_lottery_result = findViewById<Button>(R.id.but_lottery_result)
        val balls: Int = LotteryBall().getBallNumber(itemList)

        if(lotteryTouchFlag) {  //按下抽獎按鈕才抽獎
            if (itemList.size == 0) {
                but_lottery_result.text = ""
                tV_lottery_result.text = getString(R.string.read_error)

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
            lotteryIcon(lotteryTouchFlag)    //更改彩球狀態
            this.lotteryTouchFlag = false
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
//        setDataFlag = true
        App.addActivity(this)   //將此activity加入list

        // intent是用來做介面傳送功能的程式
        val SetActivity = Intent(this, SetActivity::class.java)
        startActivity(SetActivity)
        overridePendingTransition(R.anim.slide_down_in, R.anim.no_anim_transition)
    }

    private fun touchDataButton() {
        lotteryTouchFlag = false
        setDataFlag = true
        val but_data = findViewById<Button>(R.id.data_name)
        val DataActivity = Intent(this, DataActivity::class.java)
        val transitionAnim = ActivityOptions.makeSceneTransitionAnimation(this, but_data, "data_trsnsition")
        startActivity(DataActivity, transitionAnim.toBundle())
//        startActivity(DataActivity)
//        overridePendingTransition(R.anim.fade_in, R.anim.no_anim_transition)
    }

    private fun isExit() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_exit, null)
        val but_yes = view.findViewById<Button>(R.id.but_yes)
        val but_no = view.findViewById<Button>(R.id.but_no)

        val bundle = AlertDialog.Builder(this).setView(view)
        val dialog = bundle.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val anim = GlobalVariable().butAction
        but_yes.setOnTouchListener(anim)
        but_no.setOnTouchListener(anim)

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
//        Log.d(TAG, "onDestroy: ")
    }
//--------------------------------------------------------------
}