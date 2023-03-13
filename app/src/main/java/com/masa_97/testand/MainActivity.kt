package com.masa_97.testand

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var btnHex: Button
    lateinit var btnBin: Button
    lateinit var btnDec: Button
    lateinit var btnOct: Button
    lateinit var btnEqual: Button
   var input  = findViewById<TextView>(R.id.inputNumber)
    lateinit var output : TextView

    lateinit var a:Button
    lateinit var b:Button
    lateinit var c:Button
    lateinit var d:Button
    lateinit var e:Button
    lateinit var f:Button

    lateinit var num1:Button
    lateinit var num0:Button
    lateinit var num2:Button
    lateinit var num3:Button
    lateinit var num4:Button
    lateinit var num5:Button
    lateinit var num6:Button
    lateinit var num7:Button
    lateinit var num8:Button
    lateinit var num9:Button







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initBtn()

    }

    fun initBtn() {
        btnBin = findViewById(R.id.binBtn)
        btnHex = findViewById(R.id.hexbtn)
        btnDec = findViewById(R.id.decBtn)
        btnOct = findViewById(R.id.octBtn)
        btnEqual = findViewById(R.id.num22)
    }

     fun onClickBtn(v:View) {
       val newDigit = (v as Button).text.toString()
         val oldNumber = input.toString()
         val newText = oldNumber + newDigit
         input.text = newText

     }



}