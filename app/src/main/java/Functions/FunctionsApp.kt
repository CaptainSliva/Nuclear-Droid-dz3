package Functions

import android.content.Context
import android.graphics.Color
import android.nucleardz3.R
import android.widget.EditText

class FunctionsApp {
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

    fun parseColor(color: String): Int {
        if (color.length ==  7 && color[0] == '#') {
            return Color.parseColor(color)
        }
        else {
            return 0
        }
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
}