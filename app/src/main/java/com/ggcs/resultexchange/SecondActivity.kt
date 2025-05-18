package com.ggcs.resultexchange

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var editTextUserInput: EditText
    private lateinit var buttonReturnResult: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        window.statusBarColor = getColor(android.R.color.white)
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        editTextUserInput = findViewById(R.id.editInput)
        buttonReturnResult = findViewById(R.id.btnReturn)

        // Handle "Return Result" button click
        buttonReturnResult.setOnClickListener {
            val userInput = editTextUserInput.text.toString().trim()

            if (userInput.isEmpty()) {
                Toast.makeText(this, "Input cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                putExtra("user_input", userInput)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        // Hide keyboard on "Done" action from keyboard
        editTextUserInput.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                view.clearFocus()
                true
            } else {
                false
            }
        }
    }
}
