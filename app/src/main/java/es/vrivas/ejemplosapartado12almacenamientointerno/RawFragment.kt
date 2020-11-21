package es.vrivas.ejemplosapartado12almacenamientointerno

import es.vrivas.ejemplosapartado12almacenamientointerno.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_raw.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


/**
 * A simple [Fragment] subclass.
 * Use the [RawFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RawFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_raw, container, false)
    }

    private fun leerFicheroRaw() {
        var mensaje = ""
        try {
            val ficheroRaw: InputStream = resources.openRawResource(R.raw.test_raw)
            val buffer = BufferedReader(InputStreamReader(ficheroRaw))
            var linea: String=""
            var numLinea=0
            while (buffer.ready()) {
                linea = buffer.readLine()
                mensaje += """
                #${++numLinea}: $linea
                
                """.trimIndent()
            }
            ficheroRaw.close()
            this.tv_raw_content.text = mensaje
        } catch (ex: Exception) {
            Toast.makeText(
                context, "leerFicheroRaw: " + "Error: " + ex.message, Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leerFicheroRaw()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RawFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RawFragment().apply {
            }
    }
}