package com.snowbear.happylottery

import kotlin.random.Random

open class BallItem() {
    var listBall1 = mutableListOf<String>()

    fun totalBalls1(list: String): Int {
        listBall1.add(list)
        return listBall1.size
    }
}

class RandomBoll: BallItem() {
    val ballNumber1 = Random.nextInt(listBall1.size)

    fun itemRepeatSwitch(repeat: Boolean) {
        if(repeat == false) {
            println("Ball-${ballNumber1}, ${listBall1[ballNumber1]}")
        }

        if(repeat == true) {
            println("Ball-${ballNumber1}, ${listBall1[ballNumber1]}")
            listBall1.remove(listBall1[ballNumber1])
        }
    }
}
