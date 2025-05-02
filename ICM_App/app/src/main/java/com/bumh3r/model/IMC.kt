package com.bumh3r.model

class IMC(age :Int, weight :Int, height :Float, sex :String){
    private val sex :String
    private val age :Int
    private val weight :Int
    private val height :Float
    init {
        this.age = age
        this.weight = weight
        this.height = height
        this.sex = sex

    }
    fun calculate():Float{
        val mts = toMts(this.height)
        return (this.weight) / (mts*mts)
    }
    private fun toMts(cm :Float) :Float{
        return cm/100.0f
    }

}