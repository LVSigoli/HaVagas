package com.lucas.sigoli.sc3020428.haVagas




import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lucas.sigoli.sc3020428.haVagas.databinding.ActivityMainBinding
import java.util.Calendar


class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
    private var educationExtraView: MutableList<View> = mutableListOf()

    override fun onCreate(savedInstance: Bundle?) {

        super.onCreate(savedInstance)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        applyInsets()

    }

    private fun applyInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.etNome) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListenners(){

        binding.cbAddCelular.setOnCheckedChangeListener { _, isChecked ->
            binding.etCelular.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        binding.etNascimento.setOnClickListener { showDatePicker() }

        binding.spFormacao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                updateEducationFields(binding.spFormacao.selectedItem.toString())
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.btnSalvar.setOnClickListener { save() }
        binding.btnLimpar.setOnClickListener { clear() }
    }

    private fun clear() {
        TODO("Not yet implemented")
    }

    private fun save() {
        TODO("Not yet implemented")
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
            binding.etNascimento.setText(selectedDate)
        },year, month, day )
    }

    private fun updateEducationFields(toString: String) {}


}


