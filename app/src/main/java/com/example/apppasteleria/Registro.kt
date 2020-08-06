package com.example.apppasteleria


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class Registro : AppCompatActivity() {

    //Atributos para obtener datos del xml.
    private lateinit var txtRut: EditText
    private lateinit var txtNombre: EditText
    private lateinit var txtApellido: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var btnRegistro: Button

    //Atributo añadir usuario
    private val users = hashMapOf<String?, Any?>()

    //Atributos de Firebase.
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        this.txtRut = findViewById(R.id.txtRut)
        this.txtNombre = findViewById(R.id.txtNombre)
        this.txtApellido = findViewById(R.id.txtApellido)
        this.txtEmail = findViewById(R.id.txtEmail)
        this.txtContrasena = findViewById(R.id.txtContrasena)
        this.btnRegistro = findViewById(R.id.button2)


        this.btnRegistro.setOnClickListener {
            println("Holaaa")
            crearNuevoUsuario()
        }
        //this.progressBar = ProgressBar(this)

        //Atributos Firebase
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


    }

    private fun crearNuevoUsuario() {
        val rut: String = this.txtRut.text.toString()
        val nombre: String = this.txtNombre.text.toString()
        val apellido: String = this.txtApellido.text.toString()
        val email: String = this.txtEmail.text.toString()
        val contrasena: String = this.txtContrasena.text.toString()

        users["rut"] = rut
        users["nombre"] = nombre
        users["apellido"] = apellido
        users["email"] = email
        users["contrasena"] = contrasena

        if (isValidEmail(email)) {
            if (validarCamposRegistroUsuario(rut, nombre, apellido, email, contrasena)) {
                auth.createUserWithEmailAndPassword(email, contrasena)
                    .addOnCompleteListener(this) { task ->
                        if (task.isComplete) {
                            val user: FirebaseUser? = auth.currentUser
                            //Se envía mensaje de verificación al usuario.
                            verifyEmail(user)

                            //Se obtiene el UID de usuario para guardarlo en la BD como id.
                            val uidUser = user?.uid.toString()
                            val userBD = db.collection("usuarios")

                            userBD.document(uidUser).set(users).addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Usuario agregado correctamente",
                                    Toast.LENGTH_LONG
                                ).show()
                                action()
                            }.addOnFailureListener {
                                println("Error en agregar usuario: $it")
                            }

                            action()
                        }
                    }
            }
        } else {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_LONG)
                .show()
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
        startActivity(Intent(this, Dashboard::class.java))
        finish()
    }

    private fun validarCamposRegistroUsuario(
        rut: String,
        nombre: String,
        apellido: String,
        correo: String,
        contrasena: String
    ): Boolean {
        when {
            rut.isEmpty() -> {
                this.txtRut.error = "Campo requerido"
                this.txtRut.requestFocus()
                return false
            }
            correo.isEmpty() -> {
                this.txtEmail.error = "Campo requerido"
                this.txtEmail.requestFocus()
                return false
            }
            nombre.isEmpty() -> {
                this.txtNombre.error = "Campo requerido"
                this.txtNombre.requestFocus()
                return false
            }
            contrasena.isEmpty() -> {
                this.txtContrasena.error = "Campo requerido"
                this.txtContrasena.requestFocus()
                return false
            }
            apellido.isEmpty() -> {
                this.txtApellido.error = "Campo requerido"
                this.txtApellido.requestFocus()
                return false
            }
            else -> return true
        }
    }

    fun volverLogin(view: View) {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    /**
     * Método que valida si el email ingresado por el usuario es válido (se utiliza la clase
     * Pattern de Java).
     */
    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}