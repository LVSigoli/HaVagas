package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var checkBoxReceiveUpdates: CheckBox
    private lateinit var editTextPhone: EditText
    private lateinit var radioGroupPhoneType: RadioGroup
    private lateinit var checkBoxAddCellPhone: CheckBox
    private lateinit var editTextCellPhone: EditText
    private lateinit var spinnerGender: Spinner
    private lateinit var datePickerBirthDate: DatePicker
    private lateinit var spinnerEducation: Spinner
    private lateinit var editTextGraduationYear: EditText
    private lateinit var editTextInstitution: EditText
    private lateinit var editTextThesisTitle: EditText
    private lateinit var editTextAdvisor: EditText
    private lateinit var editTextJobInterests: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando componentes
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        checkBoxReceiveUpdates = findViewById(R.id.checkBoxReceiveUpdates)
        editTextPhone = findViewById(R.id.editTextPhone)
        radioGroupPhoneType = findViewById(R.id.radioGroupPhoneType)
        checkBoxAddCellPhone = findViewById(R.id.checkBoxAddCellPhone)
        editTextCellPhone = findViewById(R.id.editTextCellPhone)
        spinnerGender = findViewById(R.id.spinnerGender)
        datePickerBirthDate = findViewById(R.id.datePickerBirthDate)
        spinnerEducation = findViewById(R.id.spinnerEducation)
        editTextGraduationYear = findViewById(R.id.editTextGraduationYear)
        editTextInstitution = findViewById(R.id.editTextInstitution)
        editTextThesisTitle = findViewById(R.id.editTextThesisTitle)
        editTextAdvisor = findViewById(R.id.editTextAdvisor)
        editTextJobInterests = findViewById(R.id.editTextJobInterests)
        buttonSave = findViewById(R.id.buttonSave)
        buttonClear = findViewById(R.id.buttonClear)

        // Configurando Spinner de Sexo
        val genderOptions = arrayOf("Masculino", "Feminino", "Outro")
        spinnerGender.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderOptions)

        // Configurando Spinner de Formação
        val educationOptions = arrayOf("Fundamental", "Médio", "Graduação", "Especialização", "Mestrado", "Doutorado")
        spinnerEducation.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, educationOptions)

        spinnerEducation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selected = educationOptions[position]
                handleEducationFieldsVisibility(selected)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Exibindo ou ocultando campo de telefone celular
        checkBoxAddCellPhone.setOnCheckedChangeListener { _, isChecked ->
            editTextCellPhone.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        // Configurando botão salvar
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val receiveUpdates = checkBoxReceiveUpdates.isChecked
            val phone = editTextPhone.text.toString()
            val phoneType = when (radioGroupPhoneType.checkedRadioButtonId) {
                R.id.radioCommercial -> "Comercial"
                R.id.radioResidential -> "Residencial"
                else -> "Não especificado"
            }
            val cellPhone = if (checkBoxAddCellPhone.isChecked) editTextCellPhone.text.toString() else "Não informado"
            val gender = spinnerGender.selectedItem.toString()
            val birthDate = "${datePickerBirthDate.dayOfMonth}/${datePickerBirthDate.month + 1}/${datePickerBirthDate.year}"
            val education = spinnerEducation.selectedItem.toString()
            val graduationYear = editTextGraduationYear.text.toString()
            val institution = editTextInstitution.text.toString()
            val thesisTitle = editTextThesisTitle.text.toString()
            val advisor = editTextAdvisor.text.toString()
            val jobInterests = editTextJobInterests.text.toString()

            // Construindo mensagem para exibição
            val message = """
                Nome: $name
                Email: $email
                Receber atualizações: $receiveUpdates
                Telefone: $phone ($phoneType)
                Telefone celular: $cellPhone
                Sexo: $gender
                Data de Nascimento: $birthDate
                Formação: $education
                Ano de Conclusão: $graduationYear
                Instituição: $institution
                Título da Monografia: $thesisTitle
                Orientador: $advisor
                Vagas de Interesse: $jobInterests
            """.trimIndent()

            // Exibindo mensagem
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        // Configurando botão limpar
        buttonClear.setOnClickListener {
            clearForm()
        }
    }

    private fun handleEducationFieldsVisibility(selectedEducation: String) {
        editTextGraduationYear.visibility = if (selectedEducation != "Fundamental" && selectedEducation != "Médio") View.VISIBLE else View.GONE
        editTextInstitution.visibility = if (selectedEducation == "Graduação" || selectedEducation == "Especialização" || selectedEducation == "Mestrado" || selectedEducation == "Doutorado") View.VISIBLE else View.GONE
        editTextThesisTitle.visibility = if (selectedEducation == "Mestrado" || selectedEducation == "Doutorado") View.VISIBLE else View.GONE
        editTextAdvisor.visibility = if (selectedEducation == "Mestrado" || selectedEducation == "Doutorado") View.VISIBLE else View.GONE
    }

    private fun clearForm() {
        editTextName.text.clear()
        editTextEmail.text.clear()
        checkBoxReceiveUpdates.isChecked = false
        editTextPhone.text.clear()
        radioGroupPhoneType.clearCheck()
        checkBoxAddCellPhone.isChecked = false
        editTextCellPhone.text.clear()
        spinnerGender.setSelection(0)
        datePickerBirthDate.updateDate(2000, 0, 1)
        spinnerEducation.setSelection(0)
        editTextGraduationYear.text.clear()
        editTextInstitution.text.clear()
        editTextThesisTitle.text.clear()
        editTextAdvisor.text.clear()
        editTextJobInterests.text.clear()
    }
}
