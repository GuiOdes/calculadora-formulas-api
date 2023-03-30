package com.guiodes.multifunctionalcalculator.service;

import com.guiodes.multifunctionalcalculator.request.CalculateThreeFieldsRequest
import com.guiodes.multifunctionalcalculator.response.PitagorasFormulaResponse
import org.springframework.stereotype.Service

@Service
class PitagorasService {
    fun calcutePitagoras(calculateThreeFieldsRequest: CalculateThreeFieldsRequest): PitagorasFormulaResponse {
        val a = calculateThreeFieldsRequest.a
        val b = calculateThreeFieldsRequest.b
        val c = calculateThreeFieldsRequest.c
        var contError = 0

        if (a <= 0.0) {
            contError++
        }

        if (b <= 0.0) {
            contError++
        }

        if (c <= 0.0) {
            contError++
        }

        if (contError > 1) {
            throw IllegalArgumentException("Os valores precisam ser maiores que zer0")
        } else {
            if()
        }

    }

    if ()
    {
        throw IllegalArgumentException("A lista de valores precisa ter no mínimo três elementos")
    }

    {
        throw IllegalArgumentException("Os valores precisam ser maiores que zer0")
    }
    if (a * a + b * b != c * c)
    {
        throw IllegalArgumentException("Os valores não correspondem a um triângulo retângulo")
    }
    return PitagorasModel(a ,b ,c)
}

