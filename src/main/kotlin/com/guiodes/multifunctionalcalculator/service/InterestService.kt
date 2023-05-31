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
        resolution.add(defaultTextSimpleInterest())
        if (capital == null && amount == null){
            capital = calculateCapitalCaseAmountIsNullInterestSimple(interest!!, rate!!, duration!!)
            resolution.add(calculateCapitalCaseAmountIsNullInterestSimpleText(interest, rate, duration))
        }
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
                " <br> amount = " + amount +
                "<br> capital = "+ capital+
                "<br> rate = "+rate+
                "<br> duration = "+duration
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
        resolution.add(defaultTextCompoundInterest())
        if (capital == null){
            capital = calculateCapitalCompoundInterst(amount!!, rate!!, duration!!)
            resolution.add(calculateCapitalCompoundInterstText(amount, rate, duration))
        }
        if (rate == null){
            rate = calculateRateCompoundInterst(amount!!, capital!!, duration!!)
            resolution.add(calculateRateCompoundInterstText(amount, capital, duration))
        }
        if (duration == null){
            duration = calculateDurationCompoundInterst(amount!!, capital!!, rate!!)
            resolution.add(calculateDurationCompoundInterstText(amount, capital, rate))
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
    private fun defaultTextSimpleInterest():String{
        return "A formula do juros simples é demonimada pela formula j = C.i.t <br>" +
                "Onde: j = é o valor dos juros acumulados ao longo do período.<br>" +
                "C = Capital inicial ou Principal é o valor que foi investido ou emprestado.<br>" +
                "i = taxa de juros aplicada ao capital, exmplo: 0,05 para 5%. <br>" +
                "T = periodo de tempo o qual o dinherio ficou emprestado ou investido.<br<br>" +
                "Como você pode analisar, essa formula não entrega o M, no caso, o montante, mas podemos calcular da seguinte forma:<br>" +
                "M = C + j <br>" +
                "Montante é o valor total acumulado, incluindo o principal e os juros.<br><br>"
    }
    private fun calculateCapitalCaseAmountIsNullInterestSimple(interest: Double, rate: Double, duration: Double): Double {
        return interest/(rate*duration)
    }
    private fun calculateCapitalCaseAmountIsNullInterestSimpleText(interest: Double, rate: Double, duration: Double):String{
        val calculoTaxaVezesTempo = rate*duration
        return "Sabendo que j = ${interest}, C = ?, i = ${rate}, t = ${duration} <br>" +
                "Tempos a seguinte fórmula: <br>" +
                "${interest} = C.${rate}.${duration}<br>" +
                "Logo, queremos calcular o capital inicial ou o principal<br>" +
                "Para isso, precisamos isolar o elemento 'C', mas antes vamos trabalahr a fórmula<br>" +
                "${interest} = C.${calculoTaxaVezesTempo}<br>" +
                "${interest}/${calculoTaxaVezesTempo} = C<br>" +
                "C = ${calculateCapitalCaseAmountIsNullInterestSimple(interest, rate, duration)} <br><br>"
    }

    private fun calculateCapitalCompoundInterst(amount: Double, rate: Double, duration: Double): Double{
        return amount/(1+rate.pow(duration))
    }
    private fun calculateCapitalCompoundInterstText(amount: Double, rate: Double, duration: Double):String{
        val calculoTaxaMais1:Double = 1+rate
        val calculoTempoSobreTaxa:Double = calculoTaxaMais1.pow(duration)
        return "Substituindo os valores, ficara assim: <br>" +
                "M = ${amount}, C = ?, i = ${rate}, n = ${duration}, tempo comvertido para meses<br<br>" +
                "Como queremos calcular o Capital Inicial, primeiro tempos que trabalhar a formula<br>" +
                "${amount} = C(1+${rate})<sup>${duration}</sup><br>" +
                "${amount} = C(${calculoTaxaMais1})<sup>${duration}</sup><br>" +
                "${amount} = C(${calculoTempoSobreTaxa}<br>" +
                "C = ${amount}/${(calculoTempoSobreTaxa)}<br>" +
                "C = ${calculateCapitalCompoundInterst(amount, rate, duration)} <br><br>"
    }

    private fun calculateRateCompoundInterst(amount: Double, capital: Double, duration: Double): Double{
        return ((amount/capital).pow(1.0/duration))-1
    }
    private fun calculateRateCompoundInterstText(amount: Double, capital: Double, duration: Double):String{
        return "Substituindo os valores, ficara assim: <br>" +
                "M = ${amount}, C = ${capital}, i = ?, n = ${duration}, tempo comvertido para meses<br<br>" +
                "Como queremos calcular a taxa de juros, primeiro tempos que trabalhar a formula<br>" +
                "${amount} = ${capital}(1+i)<sup>${duration}</sup><br>" +
                "${amount}/${capital} = (1+i)<sup>${duration}</sup><br>" +
                "${amountDividedByCapital(amount, capital)} = (1+i)<sup>${duration}</sup><br>" +
                "<sup>${duration}</sup>√<sub>${amountDividedByCapital(amount, capital)}</sub> = 1+i<br>" +
                "${rootAmountDividedByCapital(amount, capital, duration)} = 1 + i <br>" +
                "${rootAmountDividedByCapital(amount, capital, duration)} -1 = i <br>" +
                "i = ${calculateRateCompoundInterst(amount, capital, duration)}<br><br>"
    }
    private fun calculateDurationCompoundInterst(amount: Double, capital: Double, rate: Double): Double{
        return log10(amount/capital) / log10(1+rate)
    }
    private fun calculateDurationCompoundInterstText(amount: Double, capital: Double, rate: Double):String{
        return "Substituindo os valores, ficara assim: <br>" +
                "M = ${amount}, C = ${capital}, i = ${rate}, n = ?, taxa comvertida para meses<br<br>" +
                "Como queremos calcular a taxa de juros, primeiro tempos que trabalhar a formula<br>" +
                "${amount} = ${capital}(1+${rate})<sup>n</sup><br>" +
                "${amount} = ${capital}(${ratePlusOne(rate)})<sup>n</sup><br>" +
                "${amount}/${capital} = ${ratePlusOne(rate)}<sup>n</sup><br>" +
                "${amountDividedByCapital(amount, capital)} = ${ratePlusOne(rate)}<sup>n</sup><br>" +
                "Como precisamos isolar o N, usamos a função logaritimo nos dois lados da equação<br>" +
                "O logaritmo é uma função matemática que relaciona uma base específica com um determinado número, expressando o expoente ao qual a base deve ser elevada para produzir esse número.<br>" +
                "Em outras palavras, o logaritmo é o inverso da operação de exponenciação.<br>" +
                "<br>" +
                "O logaritmo de um número \"x\" na base \"b\" é denotado como logₐ(x), onde \"a\" representa a base.<br>" +
                "A definição formal do logaritmo é dada pela seguinte equação:<br>" +
                "<br>" +
                "x = b<sup>y</sup> ⇔ y = log<sub>b</sub>(x)<br>" +
                "<br>" +
                "Nessa equação, \"x\" é o número para o qual queremos encontrar o logaritmo, \"b\" é a base e \"y\" é o valor do logaritmo na base \"b\" que nos dá o número \"x\".<br><br>" +
                "No nosso caso, ficara assim:<br>" +
                "log(${amountDividedByCapital(amount, capital)}) = log(${ratePlusOne(rate)})<sup>n</sup><br>" +
                "log(${amountDividedByCapital(amount, capital)}) = n.log(${ratePlusOne(rate)})<br>" +
                "${log10(amountDividedByCapital(amount, capital))} = n.${log10(ratePlusOne(rate))}<br>" +
                "${log10(amountDividedByCapital(amount, capital))}/${log10(ratePlusOne(rate))} = n<br>" +
                "n = ${(log10(amountDividedByCapital(amount, capital)))/(log10(ratePlusOne(rate)))} <br>"
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
    private fun defaultTextCompoundInterest():String{
        return "A formula do juros composto é demonimada pela formula M = C(1+i)<sup>n</sup> <br>" +
                "Onde: M = Montante é o valor total acumulado, incluindo o principal e juros<br>" +
                "C = Capital inicial ou Principal é o valor que foi investido ou emprestado<br>" +
                "i = taxa de juros aplicada ao capital, exmplo: 0,05 para 5% <br>" +
                "n = periodo de tempo o qual o dinherio ficou emprestado ou investido<br<br>"
    }
    private fun amountDividedByCapital(amount: Double, capital: Double):Double{
        return amount/capital
    }
    private fun ratePlusOne(rate: Double):Double{
        return 1+rate
    }
    private fun rootAmountDividedByCapital(amount: Double, capital: Double, duration: Double):Double{
        return ((amount/capital).pow(1.0/duration))
    }


}