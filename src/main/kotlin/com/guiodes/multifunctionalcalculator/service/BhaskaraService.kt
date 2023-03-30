package com.guiodes.multifunctionalcalculator.service

import com.guiodes.multifunctionalcalculator.request.CalculateBhaskaraRequest
import com.guiodes.multifunctionalcalculator.response.BhaskaraFormulaResponse
import kotlin.math.sqrt
import org.springframework.stereotype.Service

@Service
class BhaskaraService{
    fun calculateBhaskaraService(calculateBhaskaraRequest: CalculateBhaskaraRequest):BhaskaraFormulaResponse {
        val message:String = "A fórmula de Bhaskara é uma fórmula quadrática que permite calcular as raízes de uma equação do segundo grau. A fórmula é dada por:\n" +
                "\n" +
                "x = (-b ± √(Δ)) / 2a\n" +
                "Δ = b² - 4ac" +
                "\n\n" +
                "Onde \"a\", \"b\" e \"c\" são os coeficientes da equação ax² + bx + c = 0.\n" +
                "\n" +
                "Para calcular as raízes usando a fórmula de Bhaskara, siga os seguintes passos:" +
                "Calcule o valor dentro da raiz quadrada, ou seja, o valor de \"b² - 4ac\".\n" +
                "\n" +
                "Se o valor de \"b² - 4ac\" for negativo, a equação não tem raízes reais, apenas raízes complexas.\n" +
                "\n" +
                "Se o valor de \"b² - 4ac\" for zero, a equação tem uma única raiz real.\n" +
                "\n" +
                "Se o valor de \"b² - 4ac\" for positivo, a equação tem duas raízes reais e distintas."
        val a = calculateBhaskaraRequest.a
        val b = calculateBhaskaraRequest.b
        val c = calculateBhaskaraRequest.c
        val delta = b * b -4 * a * c;
        val formulaDelta : String
        val formulaBhaskara : String
        val resolution = mutableListOf<String>()
        val result = mutableListOf<Double>()
        formulaDelta = "$b² - 4*$a*$c)"
        if (delta < 0){
            val messageSeg:String = "No seu caso, ficaria assim: "
            val messageTer:String = "Como o Δ tem valor negativo, logo sua equação não possui raiz"
            resolution.add(message)
            resolution.add(messageSeg)
            resolution.add(messageTer)
            resolution.add(formulaDelta)
            result.add(delta)
            return BhaskaraFormulaResponse(
                resolution, result
            )
        }
        else if(delta == 0.0){
            formulaBhaskara = "(-$b/2*$a)"
            val x1 = -b/(2*a)
            val messageSeg:String = "No seu caso, ficaria assim: "
            val messageTer:String = "Como o Δ tem valor igual a 0, logo sua equação possui somente uma raiz"
            resolution.add(message)
            resolution.add(messageSeg)
            resolution.add(messageTer)
            resolution.add(formulaDelta)
            resolution.add(formulaBhaskara)
            result.add(delta)
            result.add(x1)
            return BhaskaraFormulaResponse(resolution, result)
        }
        else{
            val formulax1 = "(-$b + √($delta)) / 2$a"
            val formulax2 = "(-$b - √($delta)) / 2$a"
            formulaBhaskara = "(-$b ± √($formulaDelta) / 2*$a"
            val x1 = (-b + sqrt(delta))/(2*a)
            val x2 = (-b - sqrt(delta))/(2*a)
            val messageSeg:String = "No seu caso, ficaria assim: "
            val messageTer:String = "Como o Δ tem valor maior que 0, logo sua equação possui duas raizes"
            resolution.add(message)
            resolution.add(messageSeg)
            resolution.add(messageTer)
            resolution.add(formulaDelta)
            resolution.add(formulaBhaskara)
            resolution.add(formulax1)
            resolution.add(formulax2)
            result.add(delta)
            result.add(x1)
            result.add(x2)
            return BhaskaraFormulaResponse(resolution, result)
        }
    }
}