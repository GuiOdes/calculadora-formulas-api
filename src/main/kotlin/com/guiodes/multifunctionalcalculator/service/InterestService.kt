package com.guiodes.multifunctionalcalculator.service

import com.guiodes.multifunctionalcalculator.request.InterestCalculateRequest
import com.guiodes.multifunctionalcalculator.response.InterestFormulaResponse
import org.springframework.stereotype.Service
import kotlin.math.log10
import kotlin.math.pow

@Service
class InterestService(
) {
    private fun isValidFields(calculateInterestSimple: InterestCalculateRequest, isInterestSimple:Boolean): Boolean {
        val isNullField = mutableMapOf<String, Boolean>()
        isNullField["interest"] = calculateInterestSimple.interest == null
        isNullField["amount"] = calculateInterestSimple.amount == null
        isNullField["rate"] = calculateInterestSimple.rate == null
        isNullField["capital"] = calculateInterestSimple.capital == null
        isNullField["duration"] = calculateInterestSimple.duration == null
        var isValidFields = true
        if (isNullField["interest"]!! && isNullField["capital"]!! && isInterestSimple) {
            isValidFields = false
        }
        else if (isNullField["capital"]!! && isNullField["amount"]!! && !isInterestSimple){
            isValidFields = false
        }
        else if (isNullField["rate"]!! && isNullField["interest"]!! && isNullField["amount"]!! && isInterestSimple){
            isValidFields = false
        }
        else if (isNullField["rate"]!! && isNullField["capital"]!! && isNullField["amount"]!!){
            isValidFields = false
        }
        else if (isNullField["duration"]!! && isNullField["interest"]!! && isNullField["amount"]!! && isInterestSimple){
            isValidFields = false
        }
        else if (isNullField["duration"]!! && isNullField["capital"]!! && isNullField["amount"]!!){
            isValidFields = false
        }
        else if (isNullField["rate"]!! && isNullField["duration"]!!){
            isValidFields = false
        }
        return isValidFields
    }
    fun calculeteInterestSimple(calculateInterestSimple:InterestCalculateRequest):InterestFormulaResponse {
        val isInterestSimple = true
        if (!isValidFields(calculateInterestSimple,isInterestSimple)) {
            throw RuntimeException("Campos Invalidos")
        }
        val resolution = mutableListOf<String>()
        var result:String
        var capital = calculateInterestSimple.capital
        var amount = calculateInterestSimple.amount
        var interest = calculateInterestSimple.interest
        var rate = calculateInterestSimple.rate
        var duration = calculateInterestSimple.duration
//        j=c.i.t


        if ((capital == null && rate == null) || (capital == null && duration == null)){
            capital = calculateCapitalCaseRateNullOrDurationNullSimple(amount!!, interest!!)
        }
        if (interest == null && rate == null || interest == null && duration == null){
            interest = calculateInterestCaseRateNullOrDurationNullCompoundInterest(capital!!, amount!!)
        }
        if (rate == null){
            rate = calculateRateSimple(interest!!, capital!!, duration!!)
        }
        if (duration == null){
            duration = calculateDurationSimple(interest!!, capital!!, rate!!)
        }
        if (interest == null){
            interest = calculateInterestSimple(capital!!, rate!!, duration!!)
        }
        if (amount == null){
            amount = calculateAmountSimple(capital!!, interest!!)
        }
        result = "Interest = " + interest +
                " \n amount = " + amount +
                "\n capital = "+ capital+
                "\n rate = "+rate+
                "\n duration = "+duration
        resolution.add("Deu certo")
        return InterestFormulaResponse(resolution, result)
    }
    fun calculateCompoundInterest(calculateCompoundInterest: InterestCalculateRequest):InterestFormulaResponse{
        val isInterestSimple = false
        if (!isValidFields(calculateCompoundInterest, isInterestSimple)){
            throw RuntimeException("Campos Invalidos")
        }
        val resolution = mutableListOf<String>()
        var result:String
        var capital = calculateCompoundInterest.capital
        var interest = calculateCompoundInterest.interest
        var amount = calculateCompoundInterest.amount
        var rate = calculateCompoundInterest.rate
        var duration = calculateCompoundInterest.duration
        resolution.add(DefaultTextCompoundInterest())
        if (capital == null){
            capital = calculateCapitalCompoundInterst(amount!!, rate!!, duration!!)
            resolution.add(calculateCapitalCompoundInterstText(amount, rate, duration))
        }
        if (rate == null){
            rate = calculateRateCompoundInterst(amount!!, capital!!, duration!!)
        }
        if (duration == null){
            duration = calculateDurationCompoundInterst(amount!!, capital!!, rate!!)
        }
        if (amount == null){
            amount = calculateAmountCompoundInterest(capital!!, rate!!, duration!!)
        }
        if (interest == null){
            interest = calculateInterestCaseRateNullOrDurationNullCompoundInterest(capital!!, amount!!)
        }

        result = "Interest = " + interest +
                " \n amount = " + amount +
                "\n capital = "+ capital+
                "\n rate = "+rate+
                "\n duration = "+duration
        return InterestFormulaResponse(resolution, result)


    }
    private fun calculateCapitalCompoundInterst(amount: Double, rate: Double, duration: Double): Double{
        return amount/(1+rate.pow(duration))
    }
    private fun calculateRateCompoundInterst(amount: Double, capital: Double, duration: Double): Double{
        return ((amount/capital).pow(1.0/duration))-1
    }
    private fun calculateDurationCompoundInterst(amount: Double, capital: Double, rate: Double): Double{
        return log10(amount/capital) / log10(1+rate)
    }
    private fun calculateAmountCompoundInterest(capital: Double, rate: Double, duration: Double):Double{
        return capital*((1+rate).pow(duration))
    }
    private fun calculateAmountSimple(capital: Double, interest: Double): Double {
        return interest + capital
    }
    private fun calculateCapitalCaseRateNullOrDurationNullSimple(amount: Double, interest: Double): Double {
        return amount - interest
    }
    private fun calculateRateSimple(interest: Double, capital: Double, duration: Double): Double {
        return interest / (capital * duration)
    }
    private fun calculateDurationSimple(interest: Double, capital: Double, rate: Double): Double {
        return interest / (capital * rate)
    }
    private fun calculateInterestSimple(capital: Double, rate: Double, duration: Double): Double {
        return capital * rate * duration
    }
    private fun calculateInterestCaseRateNullOrDurationNullCompoundInterest(capital: Double, amount: Double): Double{
        return amount-capital
    }
    private fun DefaultTextCompoundInterest():String{
        return "A formula do juros composto é demonimada pela formula M = C(1+i)<sup>T</sup> <br>" +
                "Onde: M = Montante é o valor total acumulado, incluindo o principal e juros<br>" +
                "C = Capital inicial ou Principal é o valor que foi investido ou emprestado<br>" +
                "i = taxa de juros aplicada ao capital, exmplo: 0,05 para 5% <br>" +
                "T = periodo de tempo o qual o dinherio ficou emprestado ou investido<br<br>+"
    }
    private fun calculateCapitalCompoundInterstText(amount: Double, rate: Double, duration: Double):String{
        val calculoTaxaMais1:Double = 1+rate
        val calculoTempoSobreTaxa:Double = calculoTaxaMais1.pow(duration)
        val capital:Double = calculateCapitalCompoundInterst(amount, rate, duration)
        return "Jubstituindo os valores, ficara assim: <br>" +
                "M = ${amount}, C = ?, i = ${rate}, t = ${duration}, tempo comvertido para meses<br<br>" +
                "Como queremos calcular o Capital Inicial, primeiro tempos que trabalhar a formula<br>" +
                "${amount} = C(1+${rate})<sup>${duration}</sup><br>" +
                "${amount} = C(${calculoTaxaMais1})<sup>${duration}</sup><br>" +
                "${amount} = C(${calculoTempoSobreTaxa}<br>" +
                "C = ${amount}/${(calculoTempoSobreTaxa)}<br>" +
                "C = ${capital} <br>"
    }

}