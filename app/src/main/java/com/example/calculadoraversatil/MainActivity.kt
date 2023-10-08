package com.example.calculadoraversatil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Switch
import android.widget.Toast
import com.example.calculadoraversatil.databinding.ActivityMainBinding
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val items = listOf("Binary", "Octal", "Hexadecimal")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dropDownMenuLayout()
    }

    private fun dropDownMenuLayout() {
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        val menu = binding.menu
        menu.setAdapter(adapter)
        menu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (menu.selectedItemPosition == 0){
                    onConvertButtonClick(0)
                }
                if (menu.selectedItemPosition == 1){
                    onConvertButtonClick(1)
                }
                if (menu.selectedItemPosition == 2){
                    onConvertButtonClick(2)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun onConvertButtonClick(i: Int) {
        binding.buttonConvert.setOnClickListener {
            val value = binding.editValue.text.toString()
            if (value.length > 10) {
                Toast.makeText(this, "Please put a more small number", Toast.LENGTH_SHORT).show()
                binding.textResult.setText("err0r")
            } else if (value.isNotEmpty() && value.isNotBlank()) {
                when(i){
                    0 -> {
                        convertToBinary(value.toInt())
                    }

                    1 -> {
                        convertToOctal(value.toInt())
                    }

                    2 -> {
                        convertToHexadecimal(value.toInt())
                    }
                }
            } else {
                Toast.makeText(this, "Please fill with a number", Toast.LENGTH_SHORT).show()
                binding.textResult.setText("0")
            }
        }
    }

    private fun convertToBinary(value: Int) {
        if (value == 0){
            binding.textResult.setText("0")
        }
        var aux = value
        var binary = ""

        while (aux > 0) {
            val binaryCode = aux % 2
            binary = "${binaryCode.toString()}${binary}"
            aux /= 2
        }

        binding.textResult.setText(binary)
    }

    private fun convertToOctal(value: Int) {
        if (value == 0){
            binding.textResult.setText("0")
        }
        var aux = value
        var oct = ""

        while (aux > 0) {
            val octCode = aux % 8
            oct = "${octCode.toString()}${oct}"
            aux /= 8
        }

        binding.textResult.setText(oct)
    }

    private fun convertToHexadecimal(value: Int) {
        if (value == 0){
            binding.textResult.setText("0")
        }
        var aux = value
        var hex = ""

        while (aux > 0) {
            val hexCode = aux % 16
            var auxhex:String

            when(hexCode){
                10-> {
                    auxhex = "A"
                }

                11 -> {
                    auxhex = "B"
                }

                12 -> {
                    auxhex = "C"
                }

                13 -> {
                    auxhex = "D"
                }

                14 -> {
                    auxhex = "E"
                }

                15 -> {
                    auxhex = "F"
                }

                else -> {
                    auxhex = hexCode.toString()
                }
            }

            hex = "${auxhex}${hex}"
            aux /= 16
        }

        binding.textResult.setText(hex)
    }
}