@file:Suppress("DEPRECATION")

package com.example.apppasteleria.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apppasteleria.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    //Atributos para obtener datos del xml.
    private lateinit var txtCorreo: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.txtCorreo = findViewById(R.id.txtCorreo)
        this.txtContrasena = findViewById(R.id.txtContrasena)



        auth = FirebaseAuth.getInstance()
        println("auth: $auth")
    }

    fun login(view: View) {
        loginUser()
    }

    private fun loginUser() {
        val correo: String = txtCorreo.text.toString()
        val contrasena: String = txtContrasena.text.toString()

        this.progressDialog = ProgressDialog(this)
        this.progressDialog.show()
        this.progressDialog.setContentView(R.layout.progress_dialog)
        this.progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        if (validarCamposInicioSesion(correo, contrasena)) {
            auth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        this.progressDialog.dismiss()
                        action()
                    } else {
                        Toast.makeText(this, "Correo/contraseña incorrecta", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }

    private fun action() {
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }

    private fun validarCamposInicioSesion(
        correo: String,
        contrasena: String
    ): Boolean {
        if (contrasena.isEmpty()) {
            this.txtContrasena.error = "Campo requerido"
            this.txtContrasena.requestFocus()
            return false
        } else if (correo.isEmpty()) {
            this.txtCorreo.error = "Campo requerido"
            this.txtCorreo.requestFocus()
            return false
        }
        return true
    }

    fun registrarUsuario(view: View) {
        startActivity(Intent(this, Registro::class.java))
        overridePendingTransition(
            R.anim.slide_from_right,
            R.anim.slide_to_left
        )
        //finish()
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

    /**
     * Método que valida si el email ingresado por el usuario es válido (se utiliza la clase
     * Pattern de Java).
     */
    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
