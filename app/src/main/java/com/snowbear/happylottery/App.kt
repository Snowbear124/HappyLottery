package com.snowbear.happylottery

import android.app.Activity
import android.app.Application

class App: Application() {
    companion object {
        private var activityList = mutableListOf<Activity>()

        fun addActivity(activity: Activity) {
            activityList.add(activity)
        }

        fun clearActivity() {
            if (activityList.size != 0) {
                for (i in 0..activityList.size - 1) {
                    activityList.get(i).finish()
                }
                activityList.clear()
            }
        }
    }
}