package com.bumh3r

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumh3r.model.IMC
import java.text.DecimalFormat

class ResultActivity : AppCompatActivity() {

    private var age: Int = Int.MIN_VALUE
    private var weight: Int = Int.MIN_VALUE
    private var height: Float = Float.MIN_VALUE
    private var sex: String = toString()
    private var icmCalculate :Float = Float.MIN_VALUE
    private lateinit var edtWhight : TextView
    private lateinit var edtAge : TextView
    private lateinit var edtSex : TextView
    private lateinit var edtHeight : TextView
    private lateinit var edtTexR : TextView
    private lateinit var btonBackCaluletor : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initComponents()
        calculateR()
        setListener()
    }

    private fun initComponents() {
        val root = intent
        age = root.getIntExtra("KEY_AGE", 0)
        weight = root.getIntExtra("KEY_WEIGHT", 0)
        height = root.getFloatExtra("KEY_HEIGHT", 0.0f)
        sex = root.getStringExtra("KEY_SEX").orEmpty()


        edtWhight = findViewById(R.id.edtWhight)
        edtAge = findViewById(R.id.edtAge)
        edtSex = findViewById(R.id.edtSex)
        edtHeight = findViewById(R.id.edtHeight)

        edtTexR = findViewById(R.id.edtTexR)
        btonBackCaluletor = findViewById(R.id.btonBackCaluletor)
    }
    private fun calculateR(){
        val icm = IMC(age,weight,height,sex)
        icmCalculate = icm.calculate()
        viewResult()
    }

    private fun viewResult(){
        edtWhight.text = "Peso ${weight} Kg"
        edtAge.text = "Años: ${age}"
        edtSex.text = "Genero: ${sex}"
        edtHeight.text = "Altura: ${height} cm"

        val f =DecimalFormat("#.#")

        edtTexR.text = "Su IMC es ${f.format(icmCalculate)}, lo que indica que su peso esta en la categoría dePeso saludable para adultos de su estatura. El IMC es una medida de detección y no para diagnosticar enfermedades o padecimientos."
    }
    private fun setListener(){
        btonBackCaluletor.setOnClickListener {
            val int = Intent(this, MainActivity::class.java)
            startActivity(int)

        }
    }
}