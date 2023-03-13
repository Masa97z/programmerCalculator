package com.masa_97.testand


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.programmercalculator.NumberConverter
import com.masa_97.testand.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var buttonsFromTowToNine:Array<Button>
    lateinit var buttonPressed:MutableMap<String, Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallbacks()

        buttonsFromTowToNine = arrayOf(binding.num2, binding.num3,
            binding.num4, binding.num5,
            binding.num6, binding.num9,
            binding.num8,binding.num99)


        buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to false,
            DEC_BUTTON to false, HEX_BUTTON to true)

    }

    fun onClickNumber(v: View){
        val newDigit = (v as Button).text.toString()
        val oldDigits = binding.inputtext.text.toString()
        val newTextNumber = oldDigits + newDigit
        binding.inputtext.text = newTextNumber
    }
    private fun addCallbacks(){
        //Operations buttons
        binding.binBtn.setOnClickListener {
            changeButtonColorOnClick(binding.binBtn, binding.octBtn, binding.decBtn, binding.hexbtn)
            clearInput()
            changButtonPressed(BIN_BUTTON)
        }
        binding.octBtn.setOnClickListener {
            changeButtonColorOnClick(binding.octBtn, binding.binBtn, binding.decBtn, binding.hexbtn)
            clearInput()
            changButtonPressed(OCT_BUTTON)
        }
        binding.decBtn.setOnClickListener {
            changeButtonColorOnClick(binding.decBtn, binding.decBtn, binding.decBtn, binding.hexbtn)
            clearInput()
            changButtonPressed(DEC_BUTTON)
        }
        binding.hexbtn.setOnClickListener {
            changeButtonColorOnClick(binding.hexbtn, binding.decBtn, binding.octBtn, binding.binBtn)
            changButtonPressed(HEX_BUTTON)
        }


        binding.deleteBtn.setOnClickListener {
            clearInput()
        }

        binding.result.setOnClickListener {
            if (binding.inputtext.text.isNotEmpty()){
                val currentNumber = binding.inputtext.text.toString()
                for ((key, value) in buttonPressed) {
                    if(key == BIN_BUTTON && value){
                        val result = handleConversionButtons(binding.binBtn, currentNumber)
                        binding.inputtext.text = result
                    }else if (key == OCT_BUTTON && value){
                        val result = handleConversionButtons(binding.octBtn, currentNumber)
                        binding.inputtext.text = result
                    }else if (key == DEC_BUTTON && value){
                        val result = handleConversionButtons(binding.decBtn, currentNumber)
                        binding.inputtext.text = result
                    }else if (key == HEX_BUTTON && value){
                        val result = handleConversionButtons(binding.hexbtn, currentNumber)
                        binding.inputtext.text = result
                    }
                }

            }else{
                Toast.makeText(this, "Please Enter a Number", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun clearInput(){
        binding.inputtext.text = ""
    }

    private fun changeButtonColorOnClick(selectedButton: Button, button1: Button, button2: Button, button3: Button) {
        val defaultButtonDrawable = ContextCompat.getDrawable(selectedButton.context, R.drawable.rounded_button)
        val selectedButtonDrawable = ContextCompat.getDrawable(selectedButton.context, R.drawable.selected_rounded_button)

          selectedButton.background = selectedButtonDrawable
          button1.background = defaultButtonDrawable
          button2.background = defaultButtonDrawable
          button3.background = defaultButtonDrawable

        disableIrrelevantButtons(selectedButton)
    }

    private fun handleConversionButtons(selectedButton: Button, number:String): String {
        return when (selectedButton) {
            binding.binBtn -> {
                val calculationResult = NumberConverter.fromBinary(number)
                val toOct = calculationResult.value1
                val toDec = calculationResult.value2
                val toHex = calculationResult.value3

                "OCT: $toOct \nDEC: $toDec\nHEX: $toHex"
            }
            binding.octBtn -> {
                val calculationResult = NumberConverter.fromOctal(number)
                val toBin = calculationResult.value1
                val toDec = calculationResult.value2
                val toHex = calculationResult.value3

                "BIN: $toBin \nDEC: $toDec\nHEX: $toHex"
            }
            binding.decBtn -> {
                val calculationResult = NumberConverter.fromDecimal(number)
                val toBin = calculationResult.value1
                val toOct = calculationResult.value2
                val toHex = calculationResult.value3

                "BIN: $toBin \nOCT: $toOct\nHEX: $toHex"
            }
            binding.hexbtn -> {
                val calculationResult = NumberConverter.fromHexadecimal(number)
                val toBin = calculationResult.value1
                val toOct = calculationResult.value2
                val toDec = calculationResult.value3

                "BIN: $toBin \nOCT: $toOct\nDEC: $toDec"
            }
            else -> {
                "BIN: 0 \nOCT: 0\nDEC: 0"
            }
        }
    }
    private fun disableIrrelevantButtons(selectedButton: Button){
        when (selectedButton) {
            binding.binBtn -> {
                binding.hexButtonsLayout.visibility = View.INVISIBLE
                for (i in buttonsFromTowToNine.indices){
                    buttonsFromTowToNine[i].isClickable = false
                }
            }
            binding.octBtn -> {
                binding.hexButtonsLayout.visibility = View.INVISIBLE

                for (i in buttonsFromTowToNine.indices){
                    buttonsFromTowToNine[i].isClickable =
                        !(buttonsFromTowToNine[i] == binding.num99 || buttonsFromTowToNine[i] == binding.num8)
                }

            }
            binding.decBtn -> {
                binding.hexButtonsLayout.visibility = View.INVISIBLE
                for (i in buttonsFromTowToNine.indices){
                    if(!buttonsFromTowToNine[i].isClickable){
                        buttonsFromTowToNine[i].isClickable = true
                    }
                }

            }
            binding.hexbtn -> {
                if(binding.hexButtonsLayout.visibility == View.INVISIBLE){
                    binding.hexButtonsLayout.visibility = View.VISIBLE
                }
                for (i in buttonsFromTowToNine.indices){
                    if(!buttonsFromTowToNine[i].isClickable){
                        buttonsFromTowToNine[i].isClickable = true
                    }
                }
            }
        }
    }
    private fun changButtonPressed(buttonName:String){
        when(buttonName){
            BIN_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to true, OCT_BUTTON to false, DEC_BUTTON to false, HEX_BUTTON to false)
            OCT_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to true, DEC_BUTTON to false, HEX_BUTTON to false)
            DEC_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to false, DEC_BUTTON to true, HEX_BUTTON to false)
            HEX_BUTTON -> buttonPressed = mutableMapOf(BIN_BUTTON to false, OCT_BUTTON to false, DEC_BUTTON to false, HEX_BUTTON to true)
        }

    }
    companion object{
        val BIN_BUTTON = "binBtn"
        val OCT_BUTTON = "octBtn"
        val DEC_BUTTON = "decBtn"
        val HEX_BUTTON = "hexbtn"
    }

}
