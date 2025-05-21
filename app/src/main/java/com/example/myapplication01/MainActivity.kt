package com.example.myapplication01

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var autenticado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val valCorreo = findViewById<EditText>(R.id.etCorreo)
        val valPasswordAuthentication = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val tvRes = findViewById<TextView>(R.id.tvResultado)

        btnSignIn.setOnClickListener {
            val correo = valCorreo.text.toString()
            val password = valPasswordAuthentication.text.toString()

            if (correo == "correo@gmail.com" && password == "Admin123") {
                tvRes.text = "Bienvenido!"
                autenticado = true
                mostrarPantallaOperaciones()
            } else {
                tvRes.text = "Las credenciales son incorrectas"
            }
        }
    }

    private fun mostrarPantallaOperaciones() {
        // Cargar layout de operaciones
        setContentView(R.layout.operaciones)

        val spinner = findViewById<Spinner>(R.id.operac_spinner)
        val et1 = findViewById<EditText>(R.id.etValor1)
        val et2 = findViewById<EditText>(R.id.etValor2)
        val et3 = findViewById<EditText>(R.id.etValor3)
        val btnCalcular = findViewById<Button>(R.id.btnValor)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)


        btnCalcular.setOnClickListener {
            val valor1 = et1.text.toString().toDoubleOrNull()
            val valor2 = et2.text.toString().toDoubleOrNull()
            val valor3 = et3.text.toString().toDoubleOrNull()
            val operacion = spinner.selectedItem.toString()

            if (valor1 == null || valor2 == null || valor3 == null) {
                tvResultado.text = "Por favor ingrese todos los valores numéricos"
                return@setOnClickListener
            }

            val resultado = when (operacion) {
                "Suma" -> valor1 + valor2 + valor3
                "Resta" -> valor1 - valor2 - valor3
                "Multiplicación" -> valor1 * valor2 * valor3
                "División" -> {
                    if (valor2 == 0.0 || valor3 == 0.0) {
                        tvResultado.text = "No se puede dividir por cero"
                        return@setOnClickListener
                    } else {
                        valor1 / valor2 / valor3
                    }
                }
                else -> {
                    tvResultado.text = "Operación no válida"
                    return@setOnClickListener
                }
            }

            tvResultado.text = "Resultado: $resultado"
        }
    }
}
