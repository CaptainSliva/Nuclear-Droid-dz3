package android.nucleardz3

import Functions.FunctionsApp
import android.app.KeyguardManager
import android.content.Intent
import android.nucleardz3.databinding.ActivityMainBinding
import android.os.Bundle
import android.os.PowerManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var color = 0
    val colorRestoreKey = "KEY_COLOR"

    val functionsApp = FunctionsApp()


    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val etColor = binding.etColor
        etColor.setTextColor(getColor(com.google.android.material.R.color.m3_default_color_primary_text))

        binding.bOpenB.setOnClickListener {

            try {
                color = functionsApp.parseColor(etColor.text.toString())
            }catch (e: Exception) {
                etColor.setTextColor(getColor(R.color.red))
            }

            val intent = Intent(this, ActivityB::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                .putExtra("color", color)
            this.startActivity(intent)
        }

        binding.bColorGen.setOnClickListener {
            color = functionsApp.generateColor()
            etColor.setText("#${color.toHexString().slice(0..5)}")
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
                if (functionsApp.changeTextColor(applicationContext, etColor, s.toString())) {
                    try {
                        color = functionsApp.parseColor(s.toString())
                    }catch (e: Exception) {
                        etColor.setTextColor(getColor(R.color.red))
                    }
                }
                else color = 0
            }

        })


    }

    override fun onResume() {
        super.onResume()
        Log.i("Task stack", "${this.taskId}")
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putInt(colorRestoreKey, color)
//        super.onSaveInstanceState(outState)
//    }
//
//    @ExperimentalStdlibApi
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//
//        val etColor = binding.etColor
//        color = savedInstanceState.getInt(colorRestoreKey)
//        etColor.setText(color.toHexString())
//        functionsApp.changeTextColor(applicationContext, etColor, color.toHexString())
//    }
}