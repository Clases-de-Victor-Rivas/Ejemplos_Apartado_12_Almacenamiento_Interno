package es.vrivas.ejemplosapartado12almacenamientointerno.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.vrivas.ejemplosapartado12almacenamientointerno.R
import kotlinx.android.synthetic.main.fragment_lectura_escritura.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter


/**
 * A simple [Fragment] subclass.
 * Use the [LecturaEscrituraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LecturaEscrituraFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lectura_escritura, container, false)
    }

    private fun guardarFicheroInterno() {
        try {
            var mensaje = "Guardar fichero interno: \n"
            val ficheroInterno = OutputStreamWriter(
                context?.openFileOutput(
                    "fichero_interno.txt",
                    Context.MODE_PRIVATE+Context.MODE_APPEND
                )
            )
            ficheroInterno.write("En un puerto italiano al pie de las montañas\n")
            ficheroInterno.write("2*7=${2 * 7}\n")
            ficheroInterno.write("2/7=${2.0 / 7}\n")
            ficheroInterno.write("1==1 es ${1 == 1}\n")
            val random=Math.random()*100
            ficheroInterno.write(("Aleatorio: $random\n"))
            ficheroInterno.flush()
            ficheroInterno.close()
            mensaje += " Fichero interno creado.\n"
            tv_lectura_escritura.text = mensaje
        } catch (ex: Exception) {
            Toast.makeText(
                context, "guardarFicheroInterno: " + "Error: " + ex.message, Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun leerFicheroInterno() {
        var mensaje = tv_lectura_escritura.text.toString()+ "\n"+
                "Leer fichero interno: \n"
        try {
            val ficheroInterno = InputStreamReader(
                context?.openFileInput("fichero_interno.txt")
            )
            val buffer = BufferedReader(ficheroInterno)
            var numLinea = 0
            while (buffer.ready()) {
                val linea = buffer.readLine()
                mensaje += " #${++numLinea}: $linea\n"
            }
            ficheroInterno.close()
            tv_lectura_escritura.text = mensaje
        } catch (ex: java.lang.Exception) {
            Toast.makeText(
                context, "guardarFicheroInterno: " + "Error: " + ex.message, Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        guardarFicheroInterno()
        leerFicheroInterno()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
        * @return A new instance of fragment LecturaEscrituraFragment.
         */
        @JvmStatic
        fun newInstance() =
            LecturaEscrituraFragment().apply {
            }
    }
}