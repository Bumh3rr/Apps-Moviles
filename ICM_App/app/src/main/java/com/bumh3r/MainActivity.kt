package com.bumh3r

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
        private var isSelectMale = true
        private var isSelectFemale = false
        private lateinit var cardMale: CardView
        private lateinit var cardFemale: CardView
        private lateinit var rangeHeight: RangeSlider
        private lateinit var texHeight: TextView
        private  var contHeight :Float = Float.MIN_VALUE
        private lateinit var buttonAddWeight: FloatingActionButton
        private lateinit var buttonRemoveWeight: FloatingActionButton
        private lateinit var textWeight: TextView
        private var contWeight :Int = Int.MIN_VALUE
        private lateinit var buttonAddAge: FloatingActionButton
        private lateinit var buttonRemoveAge: FloatingActionButton
        private lateinit var textAge: TextView
        private  var contAge :Int = Int.MIN_VALUE
        private lateinit var btonCalculetor: Button
        private var sex :String = String.toString()

        val handler = Handler()
        var runnable: Runnable? = null
        val delayMillis = 225L

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            initCompotents()
            setOnClickListener()
        }

        private fun initCompotents() {
            cardMale = findViewById(R.id.cardMale)
            cardFemale = findViewById(R.id.cardFemale)

            rangeHeight = findViewById(R.id.rangeHeight)
            texHeight = findViewById(R.id.texHeight)
            contHeight = 120.0f

            buttonAddWeight = findViewById(R.id.btonAddWeight)
            buttonRemoveWeight = findViewById(R.id.btonRemoveWeight)
            textWeight = findViewById(R.id.texWeight)
            contWeight = 0

            buttonAddAge = findViewById(R.id.btonAddAge)
            buttonRemoveAge = findViewById(R.id.btonRemoveAge)
            textAge = findViewById(R.id.texAge)
            contAge = 0

            btonCalculetor = findViewById(R.id.btonCalculetor)
            sex = "Male"
        }


        @SuppressLint("ClickableViewAccessibility")
        private fun setOnClickListener() {
            cardMale.setOnClickListener {
                selectGenero()
                setGenderColor()
                getGenero()
            }

            cardFemale.setOnClickListener {
                selectGenero()
                setGenderColor()
                getGenero()
            }

            rangeHeight.addOnChangeListener { _, value, _ ->
                val f = DecimalFormat("#.#")
                val result = f.format(value)
                contHeight = result.toFloat()
                texHeight.text = "${result} CM"
            }

            buttonAddWeight.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Se ejecuta cuando se presiona el FloatingActionButton
                        runnable = Runnable {
                            // Llama a tu función aquí
                            if (contWeight >= 0 && contWeight < 150) {
                                this.contWeight++
                                textWeight.text = "${this.contWeight} Kg"
                            }
                            handler.postDelayed(runnable!!, delayMillis)
                        }
                        handler.post(runnable!!)
                    }
                    MotionEvent.ACTION_UP -> {
                        // Se ejecuta cuando se levanta el dedo del FloatingActionButton
                        runnable?.let { handler.removeCallbacks(it) }
                    }
                }
                true
            }

            buttonRemoveWeight.setOnClickListener {
                if (contWeight > 0 && contWeight <= 150) {
                    this.contWeight--
                    textWeight.text = "${this.contWeight} Kg"
                }
            }

            buttonAddAge.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Se ejecuta cuando se presiona el FloatingActionButton
                        runnable = Runnable {
                            // Llama a tu función aquí
                            if (contAge >= 0 && contAge < 120) {
                                this.contAge++
                                textAge.text = "${this.contAge}"
                            }
                            handler.postDelayed(runnable!!, delayMillis)
                        }
                        handler.post(runnable!!)
                    }
                    MotionEvent.ACTION_UP -> {
                        // Se ejecuta cuando se levanta el dedo del FloatingActionButton
                        runnable?.let { handler.removeCallbacks(it) }
                    }
                }
                true
            }

            buttonRemoveAge.setOnClickListener {
                if (contAge > 0 && contAge <= 120) {
                    this.contAge--
                    textAge.text = "${this.contAge}"
                }
            }

            btonCalculetor.setOnClickListener {
                if (contHeight>0.0f && contWeight>0 &&contAge >0){
                    Log.i("Result","Años: $contAge años Peso: $contWeight Kg Altura: $contHeight Cm Genero: $sex")

                    val intent = Intent(this,ResultActivity::class.java)

                    intent.putExtra("KEY_AGE",contAge)
                    intent.putExtra("KEY_WEIGHT",contWeight)
                    intent.putExtra("KEY_HEIGHT",contHeight)
                    intent.putExtra("KEY_SEX",sex)
                    startActivity(intent)

                }
            }
        }

        private fun getGenero(){
            sex = if (isSelectMale) "Masculino" else "Femenino"
        }

        private fun selectGenero() {
            isSelectMale = !isSelectMale
            isSelectFemale = !isSelectFemale
        }

        private fun setGenderColor() {
            cardMale.setCardBackgroundColor(getColor(isSelectMale))
            cardFemale.setCardBackgroundColor(getColor(isSelectFemale))
        }

        private fun getColor(root: Boolean): Int {
            var color = if (root) R.color.component_C else R.color.component_NotSelect
            return ContextCompat.getColor(this, color)
        }
    }