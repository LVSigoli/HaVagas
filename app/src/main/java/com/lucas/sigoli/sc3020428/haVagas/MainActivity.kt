package com.lucas.sigoli.sc3020428.haVagas

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.AdapterView
import android.app.DatePickerDialog

import java.util.Calendar
import androidx.core.view.ViewCompat
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatActivity
import com.lucas.sigoli.sc3020428.haVagas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding
    private var educationExtraView: MutableList<View> = mutableListOf()

    override fun onCreate(savedInstance: Bundle?) {

        super.onCreate(savedInstance)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        applyInsets()
        setupListenners()

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
        binding.btnLimpar.setOnClickListener { clearFields() }
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

    private fun updateEducationFields(education: String) {
        val layoutExtra = binding.layoutFormacaoExtra
        layoutExtra.removeAllViews()
        educationExtraView.clear()

        when (education) {
            "Fundamental", "Médio" -> {
                val etAno = EditText(this).apply {
                    hint = "Ano de formatura"
                    inputType = android.text.InputType.TYPE_CLASS_NUMBER
                }
                layoutExtra.addView(etAno)
                educationExtraView.add(etAno)
            }
            "Graduação", "Especialização" -> {
                val etAno = EditText(this).apply {
                    hint = "Ano de conclusão"
                    inputType = android.text.InputType.TYPE_CLASS_NUMBER
                }
                val etInst = EditText(this).apply { hint = "Instituição" }
                layoutExtra.addView(etAno)
                layoutExtra.addView(etInst)
                educationExtraView.addAll(listOf(etAno, etInst))
            }
            "Mestrado", "Doutorado" -> {
                val etAno = EditText(this).apply {
                    hint = "Ano de conclusão"
                    inputType = android.text.InputType.TYPE_CLASS_NUMBER
                }
                val etInst = EditText(this).apply { hint = "Instituição" }
                val etTitulo = EditText(this).apply { hint = "Título da monografia" }
                val etOrientador = EditText(this).apply { hint = "Orientador" }
                layoutExtra.addView(etAno)
                layoutExtra.addView(etInst)
                layoutExtra.addView(etTitulo)
                layoutExtra.addView(etOrientador)
                educationExtraView.addAll(listOf(etAno, etInst, etTitulo, etOrientador))
            }
        }
    }

    private fun clearFields(){
        binding.etNome.text.clear()
        binding.etEmail.text.clear()
        binding.cbReceberEmail.isChecked = false
        binding.etTelefone.text.clear()
        binding.rgTelefone.clearCheck()
        binding.cbAddCelular.isChecked = false
        binding.etCelular.text.clear()
        binding.etCelular.visibility = View.GONE
        binding.spSexo.setSelection(0)
        binding.etNascimento.setText("")
        binding.spFormacao.setSelection(0)
        binding.layoutFormacaoExtra.removeAllViews()
        educationExtraView.clear()
        binding.etVagas.text.clear()
    }

    private fun save (){
        val nome = binding.etNome.text.toString()
        val email = binding.etEmail.text.toString()
        val receberEmail = if (binding.cbReceberEmail.isChecked) "Sim" else "Não"
        val telefone = binding.etTelefone.text.toString()
        val tipoTel = when (binding.rgTelefone.checkedRadioButtonId) {
            binding.rbResidencial.id -> "Residencial"
            binding.rbComercial.id -> "Comercial"
            else -> "Não informado"
        }
        val celular = if (binding.cbAddCelular.isChecked) binding.etCelular.text.toString() else "Não informado"
        val sexo = binding.spSexo.selectedItem.toString()
        val data = binding.etNascimento.text.toString()
        val formacao = binding.spFormacao.selectedItem.toString()
        val extras = educationExtraView.joinToString { (it as EditText).text.toString() }
        val vagas = binding.etVagas.text.toString()

        val resumo = """
            Nome: $nome
            E-mail: $email
            Receber e-mails: $receberEmail
            Telefone: $telefone ($tipoTel)
            Celular: $celular
            Sexo: $sexo
            Data de nascimento: $data
            Formação: $formacao ($extras)
            Vagas de interesse: $vagas
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Dados cadastrais").setMessage(resumo)
            .setPositiveButton("ok", null).show()
    }


}


