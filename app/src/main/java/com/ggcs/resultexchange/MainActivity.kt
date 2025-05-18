package com.ggcs.resultexchange

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private lateinit var buttonOpenSecondActivity: Button

    // Register launcher to receive result from SecondActivity
    private val secondActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val returnedText = result.data?.getStringExtra("user_input")
            textViewResult.text = "Received: $returnedText"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = getColor(android.R.color.white)
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        textViewResult = findViewById(R.id.resultText)
        buttonOpenSecondActivity = findViewById(R.id.btnStart)

        buttonOpenSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            secondActivityResultLauncher.launch(intent)
        }
    }
}
