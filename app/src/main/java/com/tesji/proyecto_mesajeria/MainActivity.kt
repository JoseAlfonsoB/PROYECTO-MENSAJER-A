package com.tesji.proyecto_mesajeria

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tesji.proyecto_mesajeria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNV.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.item_perfil->{
                    true
                }
                R.id.item_usuarios->{
                    true
                }
                R.id.item_chats->{
                    true
                }
                else -> {
                    false
                }
            }
        }

            // MINUTO DEL VÍDEO 9:00
<<<<<<< HEAD
=======
            // Prueba Ramas Push
        }
>>>>>>> 4f7d1a37bf69130aa69547a91b87d4c3e27208a8
    }
}