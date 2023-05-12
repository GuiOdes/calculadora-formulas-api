//package com.guiodes.multifunctionalcalculator.service
//
//import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
//import com.guiodes.multifunctionalcalculator.response.PitagorasFormulaResponse
//import org.springframework.stereotype.Service
//import kotlin.math.sqrt
//
//@Service
//class PitagorasService(
//    val pitagorasFormulaResponse: PitagorasFormulaResponse
//) {
//    fun calcutePitagoras(calculateThreeFieldsRequest: CalculateThreeFieldsRequest): PitagorasFormulaResponse {
//        val a = calculateThreeFieldsRequest.a
//        val b = calculateThreeFieldsRequest.b
//        val c = calculateThreeFieldsRequest.c
//        val formulaPiagoras = "$a² + $b² = $c²"
//        var contError = 0
//
//        if (a <= 0.0) {
//            contError++
//        }
//
//        if (b <= 0.0) {
//            contError++
//        }
//
//        if (c <= 0.0) {
//            contError++
//        }
//
//        if (contError > 1) {
//            throw IllegalArgumentException("Pelo menos dois valores precisam ser maiores que zer0")
//        } else {
//            if (c == 0.0 || c.equals(null)) {
//                val somaQuadradoCat = a * a + b * b
//                val somaQuadradoCateto = ("$a x $a + $b x $b = $somaQuadradoCat")
//                val hipotenusa = sqrt(somaQuadradoCat)
//                val raizSomaCateto = ("√$somaQuadradoCat = $hipotenusa")
//                return pitagorasFormulaResponse.pitagorasHipotenusa(
//                    hipotenusa,
//                    somaQuadradoCateto,
//                    raizSomaCateto,
//                    formulaPiagoras
//                )
//            }
//            if (a == 0.0 || a.equals(null)) {
//                val subtracaoQudradoACatetoHip = a * a - c * c
//                val catetoFaltante = sqrt(subtracaoQudradoACatetoHip)
//                val subtracaoQuadradoCatetoHipotenusa = "$a x $a - $c x $c = $subtracaoQudradoACatetoHip"
//                val raizSubtracaoCatetoHipotenusa = "√$subtracaoQudradoACatetoHip = $catetoFaltante"
//
//                return pitagorasFormulaResponse.pitagorasCateto(
//                    catetoFaltante, subtracaoQuadradoCatetoHipotenusa, raizSubtracaoCatetoHipotenusa, formulaPiagoras
//                )
//            }
//
//            if (b == 0.0 || b.equals(null)) {
//                val subtracaoQudradoBCatetoHip = b * b - c * c
//                val catetoFaltante = sqrt(subtracaoQudradoBCatetoHip)
//                val subtracaoQuadradoCatetoHipotenusa = "$b x $b - $c x $c = $subtracaoQudradoBCatetoHip"
//                val raizSubtracaoCatetoHipotenusa = "√$subtracaoQudradoBCatetoHip = $catetoFaltante"
//
//                return pitagorasFormulaResponse.pitagorasCateto(
//                    catetoFaltante, subtracaoQuadradoCatetoHipotenusa, raizSubtracaoCatetoHipotenusa, formulaPiagoras
//                )
//            }
//        }
//        return pitagorasFormulaResponse
//    }
//}