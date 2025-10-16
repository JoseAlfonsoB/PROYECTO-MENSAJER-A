package com.tesji.proyecto_mesajeria

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tesji.proyecto_mesajeria.databinding.ActivityOptionsLoginBinding

class OptionsLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.optionEmail.setOnClickListener {
            startActivity(Intent(applicationContext, LoginEmailActivity::class.java))
        }

    }
}