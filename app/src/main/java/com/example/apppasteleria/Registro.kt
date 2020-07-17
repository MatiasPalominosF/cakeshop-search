package com.example.apppasteleria

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registro : AppCompatActivity() {

    //Atributos para obtener datos del xml.
    private lateinit var txtNombre: EditText
    private lateinit var txtApellido: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var progressBar: ProgressBar

    //Atributos de Firebase.
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        this.txtNombre = findViewById(R.id.txtNombre)
        this.txtApellido = findViewById(R.id.txtApellido)
        this.txtEmail = findViewById(R.id.txtEmail)
        this.txtContrasena = findViewById(R.id.txtContrasena)
        this.progressBar = ProgressBar(this)
        //progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        //Se obtiene la referencia de la BD para escribir en ella.
        dbReference = database.reference.child("Usuario")
    }

    fun registrar(view: View) {
        crearNuevoUsuario()

    }

    private fun crearNuevoUsuario() {
        val nombre: String = this.txtNombre.text.toString()
        val apellido: String = this.txtApellido.text.toString()
        val email: String = this.txtEmail.text.toString()
        val contrasena: String = this.txtContrasena.text.toString()

        if (validarCamposRegistroUsuario(nombre, apellido, email, contrasena)) {
            progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(email, contrasena)
                .addOnCompleteListener(this) { task ->
                    if (task.isComplete) {
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userBD = dbReference.child(user?.uid!!)

                        userBD.child("Name").setValue(nombre)
                        userBD.child("LastName").setValue(apellido)
                        action()
                    }
                }
        }
    }

    //Función que envía un email de verificación al usuario.
    private fun verifyEmail(user: FirebaseUser?) {
        user?.sendEmailVerification()?.addOnCompleteListener(this) { task ->
            if (task.isComplete) {
                Toast.makeText(this, "E-mail enviado correctamente", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Error al enviar el e-mail", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun action() {
        startActivity(Intent(this, Login::class.java))
    }

    private fun validarCamposRegistroUsuario(
        nombre: String,
        apellido: String,
        correo: String,
        contrasena: String
    ): Boolean {
        if (correo.isEmpty()) {
            this.txtEmail.setError("Campo requerido")
            this.txtEmail.requestFocus()
            return false
        } else if (nombre.isEmpty()) {
            this.txtNombre.setError("Campo requerido")
            this.txtNombre.requestFocus()
            return false
        } else if (contrasena.isEmpty()) {
            this.txtContrasena.setError("Campo requerido")
            this.txtContrasena.requestFocus()
            return false
        } else if (apellido.isEmpty()) {
            this.txtApellido.setError("Campo requerido")
            this.txtApellido.requestFocus()
            return false
        }
        return true
    }

    fun volverLogin(view: View) {
        startActivity(Intent(this, Login::class.java))
    }
}