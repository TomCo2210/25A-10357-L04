package dev.tomco.a25a_10357_l04

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textview.MaterialTextView
import dev.tomco.a25a_10357_l04.Utilities.Constants
import dev.tomco.a25a_10357_l04.Utilities.TimeFormatter
import kotlinx.coroutines.Runnable

class HandlerRunnableActivity : AppCompatActivity() {


    private lateinit var main_LBL_time: MaterialTextView
    private lateinit var main_FAB_play: ExtendedFloatingActionButton
    private lateinit var main_FAB_stop: ExtendedFloatingActionButton

    val handler: Handler = Handler(Looper.getMainLooper())
    private var startTime: Long = 0
    private var timerOn :Boolean = false

    val runnable: Runnable = object : Runnable {
        override fun run() {
            //reschedule:
            handler.postDelayed(this, Constants.Timer.DELAY)
            Log.d("Timer Runnable:", "" + System.currentTimeMillis())
            //refresh UI:
            updateTimerUI()
        }
    }

    private fun updateTimerUI() {
        val currentTime = System.currentTimeMillis()
        main_LBL_time.text = TimeFormatter.formatTime(currentTime - startTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Timer Type:", "Handler + Runnable")

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
            startTime = System.currentTimeMillis()
            handler.postDelayed(runnable, Constants.Timer.DELAY)
            timerOn = true
        }
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable)
        timerOn = false
    }
}
