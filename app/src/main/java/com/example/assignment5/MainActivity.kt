package com.example.assignment5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var submitButton: Button
    private lateinit var resetButton: Button

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        checkBox1 = findViewById<CheckBox>(R.id.checkBox1)
        checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        checkBox3 = findViewById<CheckBox>(R.id.checkBox3)
        checkBox4 = findViewById<CheckBox>(R.id.checkBox4)
        submitButton = findViewById<Button>(R.id.submitButton)
        resetButton = findViewById<Button>(R.id.resetButton)

        submitButton.setOnClickListener { onSubmitButtonClick() }
        resetButton.setOnClickListener { onResetButtonClick() }

    }

    private fun onSubmitButtonClick() {
        score = calculateScore()
        val currentDateAndTime = getCurrentDateAndTime()
        showResultDialog(currentDateAndTime, score)
    }

    private fun onResetButtonClick() {
        radioGroup.clearCheck()
        checkBox1.isChecked = false
        checkBox2.isChecked = false
        checkBox3.isChecked = false
        checkBox4.isChecked = false
    }

    private fun calculateScore(): Int {
        var question1Score = 0
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        if (selectedRadioButtonId == R.id.radioButton3) {
            question1Score = 50
        }

        var question2Score = 0
        if (checkBox2.isChecked && checkBox3.isChecked && !checkBox1.isChecked && !checkBox4.isChecked) {
            question2Score = 50
        }

        return question1Score + question2Score
    }

    private fun getCurrentDateAndTime(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun showResultDialog(dateAndTime: String, score: Int) {
        val message = "Congratulations! You submitted on $dateAndTime and achieved $score%"
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Quiz Result")
            .setMessage(message)
            .setPositiveButton("OK") { _, _ -> }
            .create()

        alertDialog.show()
    }
}