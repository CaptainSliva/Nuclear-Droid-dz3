package android.nucleardz3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.nucleardz3.databinding.ActivityBBinding
import android.util.Log
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import utils.functionsApp.keyIntentColor
import kotlin.text.toHexString

class ActivityB : AppCompatActivity() {

    private lateinit var binding: ActivityBBinding
    var userColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userColor = intent.getIntExtra(keyIntentColor, 0)
        println("ucolor - {$userColor}")

        if (userColor != 0) {
            binding.mainB.setBackgroundColor(userColor)
        }


        binding.bOpenC.setOnClickListener {
            finish()
            val intent = Intent(this, ActivityC::class.java)
            this.startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.i("Task stack", "${this.taskId}")
    }

}