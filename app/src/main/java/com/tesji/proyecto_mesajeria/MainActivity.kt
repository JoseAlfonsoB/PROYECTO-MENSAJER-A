package com.tesji.proyecto_mesajeria

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.tesji.proyecto_mesajeria.Fragments.FragmentChats
import com.tesji.proyecto_mesajeria.Fragments.FragmentPerfil
import com.tesji.proyecto_mesajeria.Fragments.FragmentUsuarios
import com.tesji.proyecto_mesajeria.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser == null){
            irOpcionesLogin()
        }

        verFragmentProfile()
        binding.bottomNV.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.item_perfil->{
                    verFragmentProfile()
                    true
                }
                R.id.item_usuarios->{
                    verFragmentUsers()
                    true
                }
                R.id.item_chats->{
                    verFragmentChats()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun irOpcionesLogin() {
        startActivity(Intent(applicationContext, OptionsLoginActivity::class.java))
    }

    private fun verFragmentProfile(){
        binding.tvTitulo.text = "Perfil"

        val fragment = FragmentPerfil()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id, fragment, "Fragment Perfil")
        fragmentTransaction.commit()
    }

    private fun verFragmentUsers(){
        binding.tvTitulo.text = "Usuarios"

        val fragment = FragmentUsuarios()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id, fragment, "Fragment Usuarios")
        fragmentTransaction.commit()

    }

    private fun verFragmentChats(){
        binding.tvTitulo.text = "Chats"

        val fragment = FragmentChats()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentoFL.id, fragment, "Fragment Chats")
        fragmentTransaction.commit()
    }
}