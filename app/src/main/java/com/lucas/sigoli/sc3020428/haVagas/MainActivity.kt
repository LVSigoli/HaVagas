package com.lucas.sigoli.sc3020428.haVagas




import android.os.Bundle
import android.view.View

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

    }

    private fun applyInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.etNome) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}
