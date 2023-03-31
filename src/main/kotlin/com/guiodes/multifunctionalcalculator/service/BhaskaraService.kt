package com.guiodes.multifunctionalcalculator.service

import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
import com.guiodes.multifunctionalcalculator.response.BhaskaraFormulaResponse
import kotlin.math.sqrt
import org.springframework.stereotype.Service

@Service
class BhaskaraService{
    fun calculateBhaskaraService(calculateBhaskaraRequest: CalculateThreeFieldsRequest):BhaskaraFormulaResponse {
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
        val delta:Double = b * b - (4.0 * a * c)
        val formulaBhaskara : String
        val resolution = mutableListOf<String>()
        val result:String
        val formulaDelta : String = "$b² - 4*$a*$c)"
        val resolucaoDelta:Double = b*b
        val resolucaoDelta2:Double = 4*a*c
        val valorDelta: String = "Δ = $formulaDelta \n" +
                "Δ = $resolucaoDelta - $resolucaoDelta2\n" +
                "Δ = $delta"
        if (delta < 0){
            val messageSeg:String = "No seu caso, ficaria assim: "
            val messageTer:String = "Como o Δ tem valor negativo, logo sua equação não possui raiz"
            resolution.add(message)
            resolution.add(messageSeg)
            resolution.add(messageTer)
            resolution.add(formulaDelta)
            result = valorDelta
            return BhaskaraFormulaResponse(
                resolution, result
            )
        }
        else if(delta == 0.0){
            formulaBhaskara = "-$b/(2*$a)"
            val x1 = -b/(2*a)
            val messageSeg:String = "No seu caso, ficaria assim: "
            val messageTer:String = "Como o Δ tem valor igual a 0, logo sua equação possui somente uma raiz"
            resolution.add(message)
            resolution.add(messageSeg)
            resolution.add(messageTer)
            resolution.add(formulaDelta)
            resolution.add(formulaBhaskara)
            val valorXContinuacao:Double = 2*a
            val valorX = "x' = $formulaBhaskara\n" +
                    "x' = -b/$valorXContinuacao \n" +
                    "x' = $x1"
            result = "$valorDelta \n\n $valorX"
            return BhaskaraFormulaResponse(resolution, result)
        }
        else{
            val formulax1 = "(-$b + √($delta)) / (2*$a)"
            val formulax2 = "(-$b - √($delta)) / (2*$a)"
            formulaBhaskara = "(-$b ± √($formulaDelta) / (2*$a)"
            val formulaBhaskaraX = "(-$b ± √($delta) / (2*$a)"
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
            val valorXDivisao:Double = 2*a
            val valorRaiz:Double = sqrt(delta)
            val valorBSomaRaiz:Double = -b + valorRaiz
            val valorBSubtraiRaiz:Double = -b - valorRaiz
            val valorX = "x = $formulaBhaskaraX\n" +
                    "x' = $formulax1 \n" +
                    "x' = -$b + $valorRaiz / (2*$a) \n" +
                    "x' = $valorBSomaRaiz / (2*$a) \n " +
                    "x' = $valorBSomaRaiz / $valorXDivisao \n" +
                    "x' = $x1 \n \n" +
                    "x\" = $formulax2 \n" +
                    "x\" = -$b - $valorRaiz / (2*$a) \n" +
                    "x\" = $valorBSubtraiRaiz / (2*$a) \n" +
                    "x\" = $valorBSubtraiRaiz / $valorXDivisao \n" +
                    "x\" = $x2"
            result = "$valorDelta \n\n $valorX"
            return BhaskaraFormulaResponse(resolution, result)
        }
    }
}