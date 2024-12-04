package dev.tomco.a25a_10357_l04

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textview.MaterialTextView
import dev.tomco.a25a_10357_l04.Utilities.Constants
import dev.tomco.a25a_10357_l04.Utilities.TimeFormatter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class KotlinCoroutinesActivity : AppCompatActivity() {


    private lateinit var main_LBL_time: MaterialTextView
    private lateinit var main_FAB_play: ExtendedFloatingActionButton
    private lateinit var main_FAB_stop: ExtendedFloatingActionButton

    private var startTime: Long = 0
    private var timerOn: Boolean = false

    private lateinit var timerJob: Job

    private fun updateTimerUI() {
        val currentTime = System.currentTimeMillis()
        main_LBL_time.text = TimeFormatter.formatTime(currentTime - startTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Timer Type:", "Kotlin Coroutines")

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
            timerJob = lifecycleScope.launch {
                while (timerOn) {
                    updateTimerUI()
                    delay(Constants.Timer.DELAY)
                }
            }
        }
    }

    private fun stopTimer() {
        timerOn = false
        timerJob.cancel()
    }
}
