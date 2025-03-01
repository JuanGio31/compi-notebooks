package com.juangio31.notebooks

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.juangio31.notebooks.analyzers.CParser
import com.juangio31.notebooks.analyzers.CodeScan
import com.juangio31.notebooks.arbol.AST
import com.juangio31.notebooks.arbol.Contexto
import com.juangio31.notebooks.arbol.ErrorInterprete
import com.juangio31.notebooks.arbol.Instruccion
import com.juangio31.notebooks.arbol.Print
import java.io.StringReader
import java.util.LinkedList


class MainActivity : ComponentActivity() {

    private lateinit var containerLayout: LinearLayout

    //    private lateinit var contenedor: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_layout)

        containerLayout = findViewById(R.id.containerlayout)

        val codigo: Button = findViewById(R.id.button_codigo_1)
        codigo.setOnClickListener {
            addFilaAnalisisCodigo();
        }
    }

    ///codigo
    private fun addFilaAnalisisText() {
        // Crea un nuevo LinearLayout para contener el conjunto de elementos
        val elementSetLayout = LinearLayout(this)
        elementSetLayout.orientation = LinearLayout.VERTICAL // definirlo como vertical
        elementSetLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Crea un LinearLayout horizontal para los botones(Play y editar) y EditText
        val horizontalLayout = LinearLayout(this)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL
        horizontalLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // boton play
        val playButton = Button(this)
        playButton.text = "Play"
        val playButtonParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        playButtonParams.setMargins(0, 0, 16, 0) // agregar margen, derecho
        playButton.layoutParams = playButtonParams
        horizontalLayout.addView(playButton)

        //editText
        val editText = EditText(this)
        editText.hint = "Escribe aquí"
        val editTextParams = LinearLayout.LayoutParams(
            0, // Use 0 for width to allow weight to work
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f // Use weight to make it take up available space
        )
        editText.layoutParams = editTextParams
        horizontalLayout.addView(editText)

        //boton editar
        val editButton = Button(this)
        editButton.text = "Editar"
        val editButtonParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editButtonParams.setMargins(16, 0, 0, 0)
        editButton.layoutParams = editButtonParams
        horizontalLayout.addView(editButton)

        // textView (oculto inicialmente)
        val textView = TextView(this)
        textView.visibility = View.GONE
        val textViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = textViewParams

        elementSetLayout.addView(horizontalLayout)
        elementSetLayout.addView(textView)

        containerLayout.addView(elementSetLayout)

        // agregar evento/aacion al boton Play
        playButton.setOnClickListener {
            textView.text = editText.text.toString()
            textView.visibility = View.VISIBLE
        }
    }

    ///codigo
    private fun addFilaAnalisisCodigo() {
        val salida = LinkedList<Any>();
        val errores = LinkedList<ErrorInterprete>();


        // Crea un nuevo LinearLayout para contener el conjunto de elementos
        val elementSetLayout = LinearLayout(this)
        elementSetLayout.orientation = LinearLayout.VERTICAL // definirlo como vertical
        elementSetLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // Crea un LinearLayout horizontal para los botones(Play y editar) y EditText
        val horizontalLayout = LinearLayout(this)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL
        horizontalLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // boton play
        val playButton = Button(this)
        playButton.text = "Play"
        val playButtonParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        playButtonParams.setMargins(0, 0, 16, 0) // agregar margen, derecho
        playButton.layoutParams = playButtonParams
        horizontalLayout.addView(playButton)

        //editText
        val editText = EditText(this)
        editText.hint = "Escribe aquí"
        val editTextParams = LinearLayout.LayoutParams(
            0, // Use 0 for width to allow weight to work
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f // Use weight to make it take up available space
        )
        editText.layoutParams = editTextParams
        horizontalLayout.addView(editText)

        //boton editar
        val editButton = Button(this)
        editButton.text = "Editar"
        val editButtonParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editButtonParams.setMargins(16, 0, 0, 0)
        editButton.layoutParams = editButtonParams
        horizontalLayout.addView(editButton)

        // textView (oculto inicialmente)
        val textView = TextView(this)
        textView.visibility = View.GONE
        val textViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = textViewParams

        elementSetLayout.addView(horizontalLayout)
        elementSetLayout.addView(textView)

        containerLayout.addView(elementSetLayout)

        // agregar evento/aacion al boton Play
        playButton.setOnClickListener {
            textView.text = "";
            //ejecutarCodigo(editText.text.toString() + "\n", salida, errores);
            val analizador = AnalizadorGeneral();
            analizador.analisisCodigo(editText.text.toString() + "\n", salida, errores);
            val strBuilder = StringBuilder();
            if (!salida.isEmpty()) {
                for (item in salida) {
                    strBuilder.append(item);
                }
                textView.text = strBuilder;
            } else
                if (!errores.isEmpty()) {
                    for (item in errores) {
                        strBuilder.append(item);
                    }
                } else {
                    textView.text = "Error en Analizador";
                }
            textView.visibility = View.VISIBLE
        }
    }
}

private fun ejecutarCodigo(
    tmp: String,
    salida: LinkedList<String>,
    error: LinkedList<ErrorInterprete>
) {
    try {
        val reader = StringReader(tmp)
        val codeScan = CodeScan(reader)
        val parser = CParser(codeScan)
        val resultado = parser.parse()
        val ast = AST(resultado.value as LinkedList<Instruccion?>)
        val contexto = Contexto()

        for (i in ast.instruccions.indices) {
            val result = ast.instruccions[i].interpreter(ast, contexto) ?: continue

            if (result is Print) {
                salida.add(result.toString());
            }
            if (result is ErrorInterprete) {
                error.add(result);
                println(result.toString())
            }
        }
    } catch (e: Exception) {
        println(e)
    }
}

// mostrar msj temporal
//            Toast.makeText(
//                this@MainActivity, "Le di click al boton Text, \nASTRID MI AMOR", Toast.LENGTH_SHORT
//            ).show()