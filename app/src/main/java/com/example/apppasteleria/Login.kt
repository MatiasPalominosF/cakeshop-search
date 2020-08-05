package com.example.apppasteleria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    //Atributos para obtener datos del xml.
    private lateinit var txtCorreo: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.txtCorreo = findViewById(R.id.txtCorreo)
        this.txtContrasena = findViewById(R.id.txtContrasena)

        //progressBar = findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()
        println("auth" + auth)
    }

    fun login(view: View) {
        loginUser()
    }

    private fun loginUser() {
        var correo: String = txtCorreo.text.toString()
        var contrasena: String = txtContrasena.text.toString()

        if (validarCamposInicioSesion(correo, contrasena)) {
            auth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        action()
                    } else {
                        //progressBar.visibility = View.INVISIBLE
                        Toast.makeText(this, "Correo/contraseña incorrecta", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    private fun action() {
        startActivity(Intent(this, Dashboard::class.java))
    }

    private fun validarCamposInicioSesion(
        correo: String,
        contrasena: String
    ): Boolean {
        if (contrasena.isEmpty()) {
            this.txtContrasena.setError("Campo requerido")
            this.txtContrasena.requestFocus()
            return false
        } else if (correo.isEmpty()) {
            this.txtCorreo.setError("Campo requerido")
            this.txtCorreo.requestFocus()
            return false
        }
        return true
    }

    fun registrarUsuario(view: View) {
        startActivity(Intent(this, Registro::class.java))
        finish()
    }

    /**
     * Método utilizado para que verifique si el usuario ya está logeado, si es true, lo manda
     * directo a la pantalla dashboard.
     */
    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            action()
        }
    }
}
