@file:Suppress("DEPRECATION")

package com.example.apppasteleria.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apppasteleria.R
import com.example.apppasteleria.data.model.Pasteleria
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_new_order.*

class NewOrderFragment : Fragment() {

    private lateinit var pasteleria: Pasteleria
    private lateinit var option: Spinner
    private lateinit var optionCantidad: Spinner
    private var producto: String = ""
    private var cantidad: String = ""
    private var precio: Int = 0
    private lateinit var progressDialog: ProgressDialog

    //Atributos de Firebase.
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    //Atributo para añadir pedido
    private val pedido = hashMapOf<String?, Any?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            pasteleria = it.getParcelable("pasteleria")!!
        }

        //Utilizado para cambiar el título y activar buscador del toolbar
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.appbar)
        val texto = toolbar?.findViewById<TextView>(R.id.txt_saludo)
        val buscador = toolbar?.findViewById<LinearLayout>(R.id.buscador)
        val recuadro = toolbar?.findViewById<LinearLayout>(R.id.recuadro)
        recuadro?.visibility = View.VISIBLE
        buscador?.visibility = View.GONE
        texto?.text = "Realizar pedido"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Atributos Firebase
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        //Se crean instancias de los spinner
        option = spnProducto as Spinner
        optionCantidad = spnCantidad as Spinner

        val options = arrayOf(
            "Seleccione producto",
            "Torta de mil hojas",
            "Panqueques",
            "galletas",
            "Torta de lúcuma",
            "Torta de manjar"
        )

        val optionsCantidad = arrayOf(
            "Seleccione cantidad",
            "1",
            "2",
            "3",
            "4",
            "5"
        )

        optionCantidad.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            optionsCantidad
        )

        optionCantidad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, seleccione una opción",
                    Toast.LENGTH_LONG
                )
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var seleccion = options.get(p2)
            }

        }

        option.adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    requireContext(),
                    "Por favor, seleccione una opción",
                    Toast.LENGTH_LONG
                )
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var seleccion = options.get(p2)
            }
        }

        btnCalcularPrecio.setOnClickListener {
            var producto = spnProducto.selectedItemId
            var cantidad = spnCantidad.selectedItemId

            if (cantidad == 0L) {

            }

            if (producto == 0L) {
                precio = 0 * cantidad.toInt()
                txtPrecio.text = precio.toString()
            } else if (producto == 1L) {
                precio = 12000 * cantidad.toInt()
                txtPrecio.text = precio.toString()
            } else if (producto == 2L) {
                precio = 2000 * cantidad.toInt()
                txtPrecio.text = precio.toString()
            } else if (producto == 3L) {
                precio = 100 * cantidad.toInt()
                txtPrecio.text = precio.toString()

            } else if (producto == 4L) {
                precio = 15000 * cantidad.toInt()
                txtPrecio.text = precio.toString()
            } else {
                precio = 18000 * cantidad.toInt()
                txtPrecio.text = precio.toString()
            }

        }
        btnAceptar.setOnClickListener {
            var image: String =
                "https://image.freepik.com/vector-gratis/ilustracion-icono-galeria_53876-27002.jpg"
            producto = spnProducto.selectedItem.toString()
            cantidad = spnCantidad.selectedItem.toString()
            var precio = txtPrecio.text.toString()
            var descripcion = txtDescripcion.text.toString()

            Log.d(
                "VALORES",
                "Esto se manda: $producto $cantidad $precio $descripcion"
            )

            sendData(image, producto, cantidad, precio, descripcion)


        }

    }

    private fun sendData(
        image: String,
        producto: String,
        cantidad: String,
        precio: String,
        descripcion: String
    ) {


        val user: FirebaseUser? = auth.currentUser
        //Se obtiene el UID de usuario para guardarlo en la BD como id.
        val uidUser = user?.uid.toString()
        println("ID USUARIO: $uidUser")
        pedido["uid"] = uidUser
        pedido["image"] = image
        pedido["name"] = producto
        pedido["nameCakeShop"] = pasteleria.nombre
        pedido["description"] = descripcion
        pedido["price"] = precio
        pedido["quantity"] = cantidad

        val pedidosBD = db.collection("pedidos")

        this.progressDialog = ProgressDialog(requireContext())
        this.progressDialog.show()
        this.progressDialog.setContentView(R.layout.progress_dialog)
        this.progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        pedidosBD.add(pedido).addOnSuccessListener {
            this.progressDialog.dismiss()
            Toast.makeText(
                requireContext(),
                "Pedido agregado correctamente",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_newOrderFragment_to_pasteleriasFragment)
        }


    }


}