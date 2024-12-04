package dev.tomco.a25a_10357_l04

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textview.MaterialTextView
import dev.tomco.a25a_10357_l04.Utilities.Constants
import dev.tomco.a25a_10357_l04.Utilities.TimeFormatter
import kotlinx.coroutines.Runnable
import java.util.Timer
import java.util.TimerTask

class CountdownTimerObjectActivity : AppCompatActivity() {


    private lateinit var main_LBL_time: MaterialTextView
    private lateinit var main_FAB_play: ExtendedFloatingActionButton
    private lateinit var main_FAB_stop: ExtendedFloatingActionButton

    private var startTime: Long = 0
    private var timerOn: Boolean = false

    private lateinit var countDownTimer: CountDownTimer

    private fun updateTimerUI() {
        val currentTime = System.currentTimeMillis()
        main_LBL_time.text = TimeFormatter.formatTime(currentTime - startTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Timer Type:", "CountDownTimer")

        findViews()
        initViews()
    }

    private fun findViews() {
        main_LBL_time = findViewById(R.id.main_LBL_time)
        main_FAB_play = findViewById(R.id.main_FAB_play)
        main_FAB_stop = findViewById(R.id.main_FAB_stop)
    }

    private fun initViews() {
        main_FAB_play.setOnClickListener { v -> startTimer() }
        main_FAB_stop.setOnClickListener { v -> stopTimer() }
    }

    private fun startTimer() {
        if (!timerOn) {
            timerOn = true
            startTime = System.currentTimeMillis()
            countDownTimer = object : CountDownTimer(999_999_999, Constants.Timer.DELAY) {
                override fun onTick(millisUntilFinished: Long) {
                    updateTimerUI()
                }

                override fun onFinish() {
                    timerOn = false
                }

            }.start()
        }
    }

    private fun stopTimer() {
        timerOn = false
        countDownTimer.cancel()
    }
}
