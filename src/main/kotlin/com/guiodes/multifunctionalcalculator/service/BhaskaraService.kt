package com.guiodes.multifunctionalcalculator.service

import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
import com.guiodes.multifunctionalcalculator.response.BhaskaraFormulaResponse
import kotlin.math.sqrt
import org.springframework.stereotype.Service

@Service
class BhaskaraService{
    fun calculateBhaskaraService(calculateBhaskaraRequest: CalculateThreeFieldsRequest):BhaskaraFormulaResponse {
        val message:String = "A fórmula de Bhaskara é uma fórmula quadrática que permite calcular as raízes de uma equação do segundo grau. A fórmula é dada por:<br>" +
                "<br>" +
                "x = (-b ± √(Δ)) / 2a<br>" +
                "Δ = b² - 4ac" +
                "<br><br>" +
                "Onde \"a\", \"b\" e \"c\" são os coeficientes da equação ax² + bx + c = 0.<br>" +
                "<br>" +
                "Para calcular as raízes usando a fórmula de Bhaskara, siga os seguintes passos:" +
                "Calcule o valor dentro da raiz quadrada, ou seja, o valor de \"b² - 4ac\".<br>" +
                "<br>" +
                "Se o valor de \"b² - 4ac\" for negativo, a equação não tem raízes reais, apenas raízes complexas.<br>" +
                "<br>" +
                "Se o valor de \"b² - 4ac\" for zero, a equação tem uma única raiz real.<br>" +
                "<br>" +
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
        val valorDelta: String = "<br>Δ = $formulaDelta <br>" +
                "Δ = $resolucaoDelta - $resolucaoDelta2<br>" +
                "Δ = $delta"
        if (delta < 0){
            val messageSeg = "No seu caso, ficaria assim: "
            val messageTer = "Como o Δ tem valor negativo, logo sua equação não possui raiz"
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
            val messageSeg = "No seu caso, ficaria assim: "
            val messageTer = "Como o Δ tem valor igual a 0, logo sua equação possui somente uma raiz"
            resolution.add(message)
            resolution.add(messageSeg)
            resolution.add(messageTer)
            resolution.add(formulaDelta)
            resolution.add(formulaBhaskara)
            val valorXContinuacao:Double = 2*a
            val valorX = "x' = $formulaBhaskara<br>" +
                    "x' = -$b/$valorXContinuacao <br>" +
                    "x' = $x1"
            result = "$valorDelta <br><br> $valorX"
            return BhaskaraFormulaResponse(resolution, result)
        }
        else{
            val formulax1 = "(-$b + √($delta)) / (2*$a)"
            val formulax2 = "(-$b - √($delta)) / (2*$a)"
            formulaBhaskara = "(-$b ± √($formulaDelta) / (2*$a)"
            val formulaBhaskaraX = "(-$b ± √($delta) / (2*$a)"
            val x1 = (-b + sqrt(delta))/(2*a)
            val x2 = (-b - sqrt(delta))/(2*a)
            val messageSeg = "No seu caso, ficaria assim: "
            val messageTer = "Como o Δ tem valor maior que 0, logo sua equação possui duas raizes"
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
            val valorX = "x = $formulaBhaskaraX<br>" +
                    "x' = $formulax1 <br>" +
                    "x' = -$b + $valorRaiz / (2*$a) <br>" +
                    "x' = $valorBSomaRaiz / (2*$a) <br> " +
                    "x' = $valorBSomaRaiz / $valorXDivisao <br>" +
                    "x' = $x1 <br> <br>" +
                    "x\" = $formulax2 <br>" +
                    "x\" = -$b - $valorRaiz / (2*$a) <br>" +
                    "x\" = $valorBSubtraiRaiz / (2*$a) <br>" +
                    "x\" = $valorBSubtraiRaiz / $valorXDivisao <br>" +
                    "x\" = $x2"
            result = "$valorDelta <br><br> $valorX"
            return BhaskaraFormulaResponse(resolution, result)
        }
    }
}