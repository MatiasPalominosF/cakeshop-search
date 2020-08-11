package com.example.apppasteleria.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.apppasteleria.R
import com.example.apppasteleria.activity.Login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
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
        texto?.text = "Configuraciones"
        auth = FirebaseAuth.getInstance()
        cerrarSesion.setOnClickListener {
            cerrarSesion()
            finishActivity()
        }
    }

    private fun cerrarSesion() {
        auth.signOut()
        activity?.let {
            val intent = Intent(it, Login::class.java)
            it.startActivity(intent)
        }
    }

    private fun finishActivity() {
        activity?.finish()
    }
}