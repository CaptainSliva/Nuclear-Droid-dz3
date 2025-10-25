package android.nucleardz3

import android.content.Intent
import android.nucleardz3.databinding.ActivityCBinding
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class ActivityC : AppCompatActivity() {

    private lateinit var binding: ActivityCBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        binding = ActivityCBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bOpenA.setOnClickListener {
            finish()
            val intent = Intent(this, ActivityA::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.i("Task stack", "${this.taskId}")
    }
}