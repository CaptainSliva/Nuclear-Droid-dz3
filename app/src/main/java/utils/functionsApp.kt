package utils

import android.content.Context
import android.graphics.Color
import android.nucleardz3.R
import android.widget.EditText
import androidx.core.graphics.toColorInt

object functionsApp {
    val keyIntentColor = "COLOR"
    fun generateColor(): Int {
        val randomR = (0..255).random()
        val randomG = (0..255).random()
        val randomB = (0..255).random()

        return Color.rgb(
            randomR,
            randomG,
            randomB
        )
    }

    fun changeTextColor(context: Context, etColor: EditText, s: String): Boolean {
        return if (s.length == 7 && s[0] == '#') {
            etColor.setTextColor(context.getColor(com.google.android.material.R.color.m3_default_color_primary_text))
            true
        }
        else {
            etColor.setTextColor(context.getColor(R.color.red))
            false
        }
    }

    fun parseColor(context: Context, etColor: EditText, color: String): Int {
        try {
            return color.toColorInt()
        }catch (e: Exception) {
            etColor.setTextColor(context.getColor(R.color.red))
            return 0
        }
    }
}