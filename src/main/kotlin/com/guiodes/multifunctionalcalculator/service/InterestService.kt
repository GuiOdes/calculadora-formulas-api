package com.guiodes.multifunctionalcalculator.service

import com.guiodes.multifunctionalcalculator.request.InterestCalculateRequest
import com.guiodes.multifunctionalcalculator.response.InterestFormulaResponse
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.concurrent.atomic.AtomicBoolean

@Service
class InterestService(
) {
    private fun isValidFields(calculateInterestSimple: InterestCalculateRequest): Boolean {
        val isNullField = mutableMapOf<String, Boolean>()

        isNullField["interest"] = calculateInterestSimple.interest == null
        isNullField["amount"] = calculateInterestSimple.amount == null
        isNullField["rate"] = calculateInterestSimple.rate == null
        isNullField["capital"] = calculateInterestSimple.capital == null
        isNullField["duration"] = calculateInterestSimple.duration == null

        var isValidFields = true

        if (isNullField["interest"]!! && isNullField["capital"]!!) {
            isValidFields = false
        }
        else if (isNullField["rate"]!! && isNullField["duration"]!!){

        }


        return isValidFields
    }

    fun calculeteInterestSimple(calculateInterestSimple:InterestCalculateRequest):InterestFormulaResponse {
        var capital = calculateInterestSimple.capital
        var interest = calculateInterestSimple.interest
        var amount = calculateInterestSimple.amount
        var rate = calculateInterestSimple.rate
        var duration = calculateInterestSimple.duration
        val resolution = mutableListOf<String>()
        val result = "Deu Certo"
//        j=c.i.t
        if (!isValidFields(calculateInterestSimple)) {
            throw RuntimeException("Campos inválidos")
        }

        if (capital == null && interest!! >= 0.0 && amount!! >= 0.0){
            capital = amount - interest
        }
        if (interest >= 0 && capital>=0 && rate>=0 && duration.equals("")){
            duration = interest/(capital*rate)
        }
        if ()
        return InterestFormulaResponse(
            resolution, result
        )
    }
}