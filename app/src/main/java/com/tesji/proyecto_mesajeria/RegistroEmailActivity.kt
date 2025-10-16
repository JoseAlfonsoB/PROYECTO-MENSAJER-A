package com.tesji.proyecto_mesajeria

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.tesji.proyecto_mesajeria.databinding.ActivityRegistroEmailBinding
import org.intellij.lang.annotations.Pattern

class RegistroEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroEmailBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegister.setOnClickListener {
            validarInformacion()
        }
    }

    private var nombres = ""
    private var email = ""
    private var password = ""
    private var r_password = ""

    private fun RegistroEmailActivity.validarInformacion() {
        nombres = binding.etNames.text.toString().trim()
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        r_password = binding.etRPassword.text.toString().trim()

        if (nombres.isEmpty()){
            binding.etNames.error = "Ingresa tu nombre"
            binding.etNames.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Correo Invalido"
            binding.etEmail.requestFocus()
        } else if (email.isEmpty()){
            binding.etEmail.error = "Ingresa un correo"
            binding.etEmail.requestFocus()
        } else if (password.isEmpty()){
            binding.etPassword.error = "Ingresa una contraseña"
            binding.etPassword.requestFocus()
        } else if (r_password.isEmpty()){
            binding.etRPassword.error = "Repite la contraseña"
            binding.etRPassword.requestFocus()
        } else if (password != r_password) {
            binding.etRPassword.error = "No coinciden las contraseñas"
            binding.etRPassword.requestFocus()
        } else {
            registrarUsuario()
        }

    }

    private fun registrarUsuario() {
        progressDialog.setMessage("Creando cuenta")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            actualizarInformacion()
        }.addOnFailureListener { e->
            progressDialog.dismiss()
            Toast.makeText(this, "Falló la creación de la cuenta debido a ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun actualizarInformacion() {
        progressDialog.setMessage("Guardando Información")

        val uidU = firebaseAuth.uid
        val nombresU = nombres
        val emailU = firebaseAuth.currentUser!!.email
        val tiempoR = Constants.obtenerTiempoD()

        val datosUsuario = HashMap<String, Any>()

        datosUsuario["uidU"] = "$uidU"
        datosUsuario["nombres"] = "$nombresU"
        datosUsuario["email"] = "$emailU"
        datosUsuario["tiempoR"] = "$tiempoR"
        datosUsuario["proveedor"] = "Email"
        datosUsuario["estado"] = "Online"

        val reference = FirebaseDatabase.getInstance().getReference("Usuarios")
        reference.child(uidU!!).setValue(datosUsuario).addOnSuccessListener {
            progressDialog.dismiss()

            startActivity(Intent(applicationContext, MainActivity::class.java))
        }.addOnFailureListener { e->
            progressDialog.dismiss()
            Toast.makeText(this, "Falló la creación de la cuenta debido a ${e.message}", Toast.LENGTH_SHORT).show()

        }
    }
}