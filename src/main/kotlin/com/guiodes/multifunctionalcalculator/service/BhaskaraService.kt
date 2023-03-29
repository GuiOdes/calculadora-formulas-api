package com.guiodes.multifunctionalcalculator.service

import com.guiodes.multifunctionalcalculator.request.CalculateBhaskaraRequest
import com.guiodes.multifunctionalcalculator.response.BhaskaraFormulaResponse
import kotlin.math.sqrt

class BhaskaraService(
    val bhaskaraFormulaResponse: BhaskaraFormulaResponse
) {
    fun calculateBhaskaraService(calculateBhaskaraRequest: CalculateBhaskaraRequest):BhaskaraFormulaResponse {
        val a = calculateBhaskaraRequest.a
        val b = calculateBhaskaraRequest.b
        val c = calculateBhaskaraRequest.c
        val delta = b * b -4 * a * c;
        val formulaDelta : String
        val formulaBhaskara : String
        formulaDelta = "$b² - 4*$a*$c)"
        if (delta < 0){
            return bhaskaraFormulaResponse.bhaskaraDeltaNegativo(delta, formulaDelta)
        }
        else if(delta == 0.0){
            formulaBhaskara = "(-$b/2*$a)"
            val x1 = -b/(2*a)
            return bhaskaraFormulaResponse.bhaskaraDeltaZero(x1, formulaBhaskara, delta, formulaDelta)
        }
        else{
            val formulax1 = "(-$b + √($delta)) / 2$a"
            val formulax2 = "(-$b - √($delta)) / 2$a"
            formulaBhaskara = "(-$b ± √($formulaDelta) / 2*$a"
            val x1 = (-b + sqrt(delta))/(2*a)
            val x2 = (-b - sqrt(delta))/(2*a)
            return bhaskaraFormulaResponse.bhaskaraFormula(formulaBhaskara, x1, x2, formulax1, formulax2, formulaDelta, delta)
        }
    }
}