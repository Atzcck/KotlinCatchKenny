package com.alptazecicek.kotlincatchkenny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable = Runnable {  }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //imageArray
        imageArray.add(imageView)
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        hideImages()

        //countdowntimer

        object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                timeText.text ="Left: ${p0/1000}"
            }
            override fun onFinish() {
                timeText.text ="Left: 0"
                val alert = AlertDialog.Builder(this@MainActivity)
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                alert.setTitle("Game Over")
                alert.setMessage("Do you want to play again?")
                alert.setPositiveButton("Yes") {dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }
        }.start()
    }

    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }


    fun increaseScore(view: View){
        score++
        scoreText.text = "Score: $score"
    }
}