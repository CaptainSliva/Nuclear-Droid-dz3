package android.nucleardz3

import android.app.KeyguardManager
import android.content.Intent
import android.nucleardz3.FunctionsApp.changeTextColor
import android.nucleardz3.FunctionsApp.generateColor
import android.nucleardz3.FunctionsApp.keyIntentColor
import android.nucleardz3.FunctionsApp.parseColor
import android.nucleardz3.databinding.ActivityABinding
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ActivityA : AppCompatActivity() {

    private lateinit var binding: ActivityABinding
    var color = 0


    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)


        binding = ActivityABinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        window.addFlags(
            WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)

        keyguardManager.requestDismissKeyguard(this, null)
        this.setTurnScreenOn(true)
        this.setShowWhenLocked(true)

        val etColor = binding.etColor
        etColor.setTextColor(getColor(com.google.android.material.R.color.m3_default_color_primary_text))

        binding.bOpenB.setOnClickListener {

            color = parseColor(applicationContext, etColor, etColor.text.toString())

            val intent = Intent(this, ActivityB::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                .putExtra(keyIntentColor, color)
            this.startActivity(intent)
        }

        binding.bColorGen.setOnClickListener {
            color = generateColor()
            etColor.setText("#${color.toHexString().slice(2..7)}")
        }

        etColor.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //TODO("Not yet implemented")
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (changeTextColor(applicationContext, etColor, s.toString())) {
                    color = parseColor(applicationContext, etColor, etColor.text.toString())
                }
                else color = 0
            }

        })
    }

    override fun onResume() {
        super.onResume()
        Log.i("Task stack", "${this.taskId}")
    }

}