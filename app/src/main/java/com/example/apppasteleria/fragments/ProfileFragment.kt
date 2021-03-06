@file:Suppress("DEPRECATION")

package com.example.apppasteleria.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.apppasteleria.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    //Se crean las variables de Firebase.
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Utilizado para cambiar el título y activar buscador del toolbar
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        val texto = toolbar?.findViewById<TextView>(R.id.txt_saludo)
        val buscador = toolbar?.findViewById<LinearLayout>(R.id.buscador)
        val recuadro = toolbar?.findViewById<LinearLayout>(R.id.recuadro)
        recuadro?.visibility = View.VISIBLE
        buscador?.visibility = View.GONE
        texto?.text = "Mi perfil"

        val editar = view.findViewById<Button>(R.id.buttonEditarProfile)

        editar.setOnClickListener {
            val rut = txtRut.text.toString()
            val nombre = txtNombre.text.toString()
            val apellido = txtApellido.text.toString()
            val correo = txtEmail.text.toString()
            val contrasena = txtContrasena.text.toString()

            editarPerfil(rut, nombre, apellido, correo, contrasena)

        }

        //Se inicializan las varibles de Firebase.
        this.auth = FirebaseAuth.getInstance()
        this.db = FirebaseFirestore.getInstance()
        cargarDatos()

    }

    private fun editarPerfil(
        rut: String,
        nombre: String,
        apellido: String,
        correo: String,
        contrasena: String
    ) {
        this.progressDialog = ProgressDialog(requireContext())
        this.progressDialog.show()
        this.progressDialog.setContentView(R.layout.progress_dialog)
        this.progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        //Se obtiene la referencia de la base de datos.
        val docRef = db.collection("usuarios").document(auth.uid.toString())

        //Se accede a la base de datos y para actualizar el usuario.
        docRef.update(
            "rut",
            rut,
            "nombre",
            nombre,
            "apellido",
            apellido,
            "email",
            correo,
            "contrasena",
            contrasena
        ).addOnSuccessListener {
            this.progressDialog.dismiss()
            Toast.makeText(
                requireContext(),
                "Su perfil se ha editado correctamente.",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Error al editar perfil", Toast.LENGTH_SHORT).show()
        }

    }

    private fun cargarDatos() {
        this.progressDialog = ProgressDialog(requireContext())
        this.progressDialog.show()
        this.progressDialog.setContentView(R.layout.progress_dialog)
        this.progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        val docRef = db.collection("usuarios").document(auth.uid.toString())
        docRef.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val document: DocumentSnapshot? = it.result
                if (document!!.exists()) {
                    val rut = document.get("rut").toString()
                    val nombre = document.get("nombre").toString()
                    val apellido = document.get("apellido").toString()
                    val correo = document.get("email").toString()
                    val contrasena = document.get("contrasena").toString()
                    txtRut.setText(rut)
                    txtNombre.setText(nombre)
                    txtApellido.setText(apellido)
                    txtEmail.setText(correo)
                    txtContrasena.setText(contrasena)
                    this.progressDialog.dismiss()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "No se han podido cargar los datos de la base de datos, reinicie.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}