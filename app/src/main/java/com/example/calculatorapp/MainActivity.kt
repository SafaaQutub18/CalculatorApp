package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculatorapp.databinding.ActivityMainBinding
import com.example.calculatorapp.databinding.CalButtonsBinding


class MainActivity : AppCompatActivity() {
    lateinit var resultTV : TextView
    lateinit var inputTV : TextView
    lateinit var btnsView : CalButtonsBinding
    lateinit var binding : ActivityMainBinding
    var firstNum = ""
    var oprator = ""
    var secNum = ""
    var result = 0.0
    var isClear = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnsView = binding.btnView
        inputTV = binding.inputTV
        resultTV = binding.resultTV


        btnsView.equalBt.setOnClickListener {
            try{
            when (oprator) {
                "/" -> result = firstNum.toDouble() / secNum.toDouble()
                "*" -> result = firstNum.toDouble() * secNum.toDouble()
                "-" -> result = firstNum.toDouble() - secNum.toDouble()
                "+" -> result = firstNum.toDouble() + secNum.toDouble()
            }
                } catch (e: Exception){
                    inputTV.setText("")
                    Toast.makeText(this, "please enter integer number!", Toast.LENGTH_LONG).show()
        }
            resultTV.setText(result.toString())
        }
        btnsView.clearBt.setOnClickListener {
            firstNum = "" ; oprator = "";  secNum = ""; result = 0.0; isClear = true
            inputTV.setText(""); resultTV.setText("")
        }
    }
    fun numberEvents(view:View){
        var inputString = inputTV.text.toString()
        val selectedButton = view as Button
        btnsView.apply {
            when(selectedButton.id){
                zeroBt.id -> inputString += "0"
                oneBt.id -> inputString += "1"
                twoBt.id -> inputString += "2"
                threeBt.id -> inputString += "3"
                fourBt.id -> inputString += "4"
                fiveBt.id -> inputString += "5"
                sixBt.id -> inputString += "6"
                sevenBt.id -> inputString += "7"
                eightBt.id -> inputString += "8"
                nineBt.id -> inputString += "9"
                decimalBt.id -> inputString += "."
                minusBt.id -> inputString += "-"
            }
        }
        if (oprator == "")
            firstNum = inputString
        else
            secNum = inputString.substringAfterLast("$oprator")

        inputTV.setText(inputString)
    }

    fun opratorEvents(view: View) {
        if(oprator != "") {
            firstNum = result.toString()
            inputTV.setText(firstNum)
        }
        var currentText = inputTV.text.toString()
        val selectedButton = view as Button
        btnsView.apply {
            when (selectedButton.id) {
                divisionBt.id -> oprator = "/"
                multiBt.id -> oprator = "*"
                subtracBt.id -> oprator = "-"
                addBt.id -> oprator = "+"
            }
        }

        inputTV.setText(currentText + oprator)
    }
}