package com.guiodes.multifunctionalcalculator.service

import com.guiodes.multifunctionalcalculator.request.InterestCalculateRequest
import com.guiodes.multifunctionalcalculator.response.InterestFormulaResponse
import org.springframework.stereotype.Service
import kotlin.math.log10
import kotlin.math.pow

@Service
class InterestService(
) {
    private fun isValidFields(calculateInterestSimple: InterestCalculateRequest, isInterestSimple: Boolean): Boolean {
        val isNullField = mutableMapOf<String, Boolean>()
        isNullField["interest"] = calculateInterestSimple.interest == null
        isNullField["amount"] = calculateInterestSimple.amount == null
        isNullField["rate"] = calculateInterestSimple.rate == null
        isNullField["capital"] = calculateInterestSimple.capital == null
        isNullField["duration"] = calculateInterestSimple.duration == null
        var isValidFields = true
        if (isNullField["interest"]!! && isNullField["capital"]!! && isInterestSimple) {
            isValidFields = false
        } else if (isNullField["capital"]!! && isNullField["amount"]!! && !isInterestSimple) {
            isValidFields = false
        } else if (isNullField["rate"]!! && isNullField["interest"]!! && isNullField["amount"]!! && isInterestSimple) {
            isValidFields = false
        } else if (isNullField["rate"]!! && isNullField["capital"]!! && isNullField["amount"]!!) {
            isValidFields = false
        } else if (isNullField["duration"]!! && isNullField["interest"]!! && isNullField["amount"]!! && isInterestSimple) {
            isValidFields = false
        } else if (isNullField["duration"]!! && isNullField["capital"]!! && isNullField["amount"]!!) {
            isValidFields = false
        } else if (isNullField["rate"]!! && isNullField["duration"]!!) {
            isValidFields = false
        }
        return isValidFields
    }

    fun calculateInterestSimple(calculateInterestSimple: InterestCalculateRequest): InterestFormulaResponse {
        val isInterestSimple = true
        if (!isValidFields(calculateInterestSimple, isInterestSimple)) {
            throw RuntimeException("Campos Invalidos")
        }
        val resolution = mutableListOf<String>()
        var capital = calculateInterestSimple.capital
        var amount = calculateInterestSimple.amount
        var interest = calculateInterestSimple.interest
        var rate = calculateInterestSimple.rate
        var duration = calculateInterestSimple.duration
//        j=c.i.t
        resolution.add(defaultTextSimpleInterest())
        if (capital == null && amount == null) {
            capital = calculateCapitalCaseAmountIsNullInterestSimple(interest!!, rate!!, duration!!)
            resolution.add(calculateCapitalCaseAmountIsNullInterestSimpleText(interest, rate, duration))
        }
        if ((capital == null && rate == null) || (capital == null && duration == null)) {
            capital = calculateCapitalCaseRateNullOrDurationNullSimple(amount!!, interest!!)
            resolution.add(calculateCapitalCaseRateNullOrDurationNullSimpleText(amount, interest))
        }
        if (interest == null && rate == null || interest == null && duration == null) {
            interest = calculateInterestCaseRateNullOrDurationNullInterest(capital!!, amount!!)
            resolution.add(calculateInterestCaseRateNullOrDurationNullInterestText(capital, amount))
        }
        if (rate == null) {
            rate = calculateRateSimple(interest!!, capital!!, duration!!)
            resolution.add(calculateRateSimpleInterestText(interest, capital, duration))
        }
        if (duration == null) {
            duration = calculateDurationSimple(interest!!, capital!!, rate)
            resolution.add(calculateDurationSimpleInterestText(interest, capital, rate))
        }
        if (interest == null) {
            interest = calculateInterestSimple(capital!!, rate, duration)
            resolution.add(calculateInterestSimpleInterestText(capital, rate, duration))
        }
        if (amount == null) {
            amount = calculateAmountSimple(capital!!, interest)
            resolution.add(calculateAmountSimpleInterestText(capital, interest))
        }
        val result = "Juros = " + interest +
                " <br> Montante = " + amount +
                "<br> Capital = " + capital +
                "<br> Taxa = " + rate +
                "<br> Duração = " + duration
        return InterestFormulaResponse(resolution, result)
    }


    fun calculateCompoundInterest(calculateCompoundInterest: InterestCalculateRequest): InterestFormulaResponse {
        val isInterestSimple = false
        if (!isValidFields(calculateCompoundInterest, isInterestSimple)) {
            throw RuntimeException("Campos Invalidos")
        }
        val resolution = mutableListOf<String>()
        var capital = calculateCompoundInterest.capital
        var interest = calculateCompoundInterest.interest
        var amount = calculateCompoundInterest.amount
        var rate = calculateCompoundInterest.rate
        var duration = calculateCompoundInterest.duration
        resolution.add(defaultTextCompoundInterest())
        if (capital == null) {
            capital = calculateCapitalCompoundInterest(amount!!, rate!!, duration!!)
            resolution.add(calculateCapitalCompoundInterestText(amount, rate, duration))
        }
        if (rate == null) {
            rate = calculateRateCompoundInterest(amount!!, capital, duration!!)
            resolution.add(calculateRateCompoundInterestText(amount, capital, duration))
        }
        if (duration == null) {
            duration = calculateDurationCompoundInterst(amount!!, capital, rate)
            resolution.add(calculateDurationCompoundInterestText(amount, capital, rate))
        }
        if (amount == null) {
            amount = calculateAmountCompoundInterest(capital, rate, duration)
            resolution.add(calculateAmountCompoundInterestText(capital, rate, duration))

        }
        if (interest == null) {
            interest = calculateInterestCaseRateNullOrDurationNullInterest(capital, amount)
            resolution.add(calculateInterestCaseRateNullOrDurationNullInterestText(capital, amount))
        }

        val result = "Interest = " + interest +
                " <br>amount = " + amount +
                "<br>capital = " + capital +
                "<br>rate = " + rate +
                "<br>duration = " + duration
        return InterestFormulaResponse(resolution, result)


    }
    private fun defaultTextSimpleInterest(): String {
        return "A formula do juros simples é demonimada pela formula J = C.i.t <br>" +
                "Onde: J = é o valor dos juros acumulados ao longo do período.<br>" +
                "C = Capital inicial ou Principal é o valor que foi investido ou emprestado.<br>" +
                "i = taxa de juros aplicada ao capital, exmplo: 0,05 para 5%. <br>" +
                "t = periodo de tempo o qual o dinherio ficou emprestado ou investido.<br<br>" +
                "Como podemos analisar, essa formula não entrega o M, no caso, o montante, mas podemos calcular da seguinte forma:<br>" +
                "M = C + J <br>" +
                "Montante é o valor total acumulado, incluindo o principal e os juros.<br><br>"
    }
    private fun calculateCapitalCaseAmountIsNullInterestSimpleText(
        interest: Double,
        rate: Double,
        duration: Double
    ): String {
        val calculoTaxaVezesTempo = rate * duration
        return "Sabendo que J = ${interest}, C = ?, i = ${rate}, t = $duration <br>" +
                "Substituindo na formula ficaria: <br>" +
                "$interest = C x $rate x $duration <br>" +
                "Logo queremos calcular o capital inicial ou o principal <br>" +
                "Para isso precisamos isolar o elemento 'C', mas antes vamos trabalhar a fórmula:<br>" +
                "$interest = C x $calculoTaxaVezesTempo <br>" +
                "C = $interest / $calculoTaxaVezesTempo<br>" +
                "C = ${calculateCapitalCaseAmountIsNullInterestSimple(interest, rate, duration)} <br><br>"
    }

    private fun calculateInterestCaseRateNullOrDurationNullInterestText(capital: Double, amount: Double): String {
        val interest = amount - capital
        return "Sabendo que M = $amount, C = $capital, J = ? <br>" +
                "Substituindo na formula ficaria: <br>" +
                "$amount = $capital + J <br>" +
                "Isolamos os juros para descobrir ele, passando o capital para o outro lado: <br>" +
                "J = $amount - $capital" +
                "C = ${interest}<br><br>"
    }

    private fun calculateRateSimpleInterestText(interest: Double, capital: Double, duration: Double): String {
        val rate = interest / (capital * duration)
        return "Sabendo que J = $interest, C = $capital, i = ?, T = $duration <br>" +
                "Substituindo na formula ficaria: <br>" +
                "$interest = $capital x i x $duration<br>" +
                "Passa a taxa para antes da igualdade, e os juros para o outro lado dividindo, ficaria assim:<br>" +
                "i = ($interest / $capital) x $duration <br>" +
                "Basta dividir os juros pelo capital e multiplicar pela duração, e teremos a taxa de juros:<br>" +
                "i = $rate <br><br>"
    }
    private fun calculateDurationSimpleInterestText(interest: Double, capital: Double, rate: Double): String {
        val duration = interest / (capital * rate)
        val capxrate = capital * rate
        return "Sabendo que J = $interest, C = $capital, i = $rate, T = ? <br>" +
                "Substituindo na formula ficaria: <br>" +
                "$interest = $capital x $rate x t<br>" +
                "multiplique o capital pela taxa e ficará da seguinte forma:<br>" +
                "$interest = $capxrate x t<br>" +
                "Isole o expoente t e passe o juros que estava multiplicando, divindo para o outro lado:<br>" +
                "t = $interest / $capxrate<br>" +
                "Após feita a divisão, terá a duração:<br>"+
                "t = $duration <br><br>"
    }

    private fun calculateInterestSimpleInterestText(capital: Double, rate: Double, duration: Double): String {
        val interest = capital * rate * duration
        return "Sabendo que J = ?, C = $capital, i = $rate, T = $duration <br>" +
                "Substituindo na formula ficaria: <br>" +
                "J = $capital x $rate x $duration<br>" +
                "Sendo assim basta multiplicar o capital, a taxa e a duração:<br>" +
                "J = $interest<br><br>"
    }

    private fun calculateAmountSimpleInterestText(capital: Double, interest: Double): String {
        val amount = capital + interest
        return "Sabendo que M = ?, C = $capital, J = $interest <br>" +
                "Substituindo na formula ficaria: <br>" +
                "M = $capital + $interest<br>" +
                "Dessa forma, basta somar o capital mais os juros:<br>" +
                "M = $amount<br><br>"
    }

    private fun calculateCapitalCaseRateNullOrDurationNullSimpleText(amount: Double, interest: Double): String {
        val capital = amount - interest
        return "Sabendo que M = ${amount}, C = ?, J = $interest <br>" +
                "Substituindo na formula ficaria: <br>" +
                "$amount = C + ${interest}<br>" +
                "Isolamos o capital inicial para descobrir ele, passando o juros para o outro lado:<br>" +
                "C = {$amount} - $interest <br>" +
                "C = ${capital}<br><br>"
    }


    private fun calculateCapitalCompoundInterestText(amount: Double, rate: Double, duration: Double): String {
        val calculoTaxaMais1: Double = 1 + rate
        val calculoTempoSobreTaxa: Double = calculoTaxaMais1.pow(duration)
        return "Substituindo os valores, ficara assim: <br>" +
                "M = ${amount}, C = ?, i = ${rate}, t = ${duration}, tempo e taxa convertidos em meses<br<br>" +
                "Como queremos calcular o Capital Inicial, primeiro trabalhamos a formula:<br>" +
                "${amount} = C(1+${rate})<sup>${duration}</sup><br>" +
                "Fazemos a soma e continuamos o calculo:" +
                "$amount = C(${calculoTaxaMais1})<sup>${duration}</sup><br>" +
                "Após somada a taxa mais um, elevamos o valor a duração:" +
                "$amount = C(${calculoTempoSobreTaxa}<br>" +
                "Feito isso, passamos a incognita para o outro lado, e o montante passa dividindo:<br>" +
                "C = ${amount}/${(calculoTempoSobreTaxa)}<br>" +
                "tendo como resultado o capital inicial:<br>" +
                "C = ${calculateCapitalCompoundInterest(amount, rate, duration)} <br><br>"
    }


    private fun calculateRateCompoundInterestText(amount: Double, capital: Double, duration: Double): String {
        return "Substituindo os valores, ficara assim: <br>" +
                "M = ${amount}, C = ${capital}, i = ?, t = ${duration}, tempo e taxa convertidos para meses<br<br>" +
                "Como queremos calcular a taxa de juros, primeiro temos que trabalhar a formula:<br>" +
                "${amount} = ${capital}(1+i)<sup>${duration}</sup><br>" +
                "A multiplicação, passará para o outro lado dividindo:<br>" +
                "${amount}/${capital} = (1+i)<sup>${duration}</sup><br>" +
                "Realizamos a divisão:<br>" +
                "${amountDividedByCapital(amount, capital)} = (1+i)<sup>${duration}</sup><br>" +
                "O elevado passa para o outro lado como radiciação, utilizando a propriedade de igualdade:<br>" +
                "<sup>${duration}</sup>√<sub>${amountDividedByCapital(amount, capital)}</sub> = 1+i<br>" +
                "Seguimos o calculo da radiciação:<br>" +
                "${rootAmountDividedByCapital(amount, capital, duration)} = 1 + i <br>" +
                "Passamos o número um para o outro lado, invertendo o sinal:<br>" +
                "${rootAmountDividedByCapital(amount, capital, duration)} -1 = i <br>" +
                "Por fim temos o valor da taxa calculada:<br>" +
                "i = ${calculateRateCompoundInterest(amount, capital, duration)}<br><br>"
    }

    private fun calculateDurationCompoundInterestText(amount: Double, capital: Double, rate: Double): String {
        return "Substituindo os valores, vai ficar assim: <br>" +
                "M = ${amount}, C = ${capital}, i = ${rate}, t = ?, tempo e taxa convertidos para meses<br<br>" +
                "Como queremos calcular o tempo/prazo, primeiro temos que trabalhar a formula:<br>" +
                "${amount} = ${capital}(1+${rate})<sup>t</sup><br>" +
                "Realizamos a soma:" +
                "${amount} = ${capital}(${ratePlusOne(rate)})<sup>t</sup><br>" +
                "Passamos o capital que estava multiplicando, virando uma divisão do outro lado:" +
                "${amount}/${capital} = ${ratePlusOne(rate)}<sup>t</sup><br>" +
                "Ficará da seguinte forma:" +
                "${amountDividedByCapital(amount, capital)} = ${ratePlusOne(rate)}<sup>t</sup><br>" +
                "Como precisamos isolar o T, usamos a função logaritimo nos dois lados da equação:<br>" +
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
                "log(${amountDividedByCapital(amount, capital)}) = log(${ratePlusOne(rate)})<sup>t</sup><br>" +
                "log(${amountDividedByCapital(amount, capital)}) = t.log(${ratePlusOne(rate)})<br>" +
                "${log10(amountDividedByCapital(amount, capital))} = t.${log10(ratePlusOne(rate))}<br>" +
                "${log10(amountDividedByCapital(amount, capital))}/${log10(ratePlusOne(rate))} = t<br>" +
                "Seguindo os calculos de log e as propriedades, teremos o valor do tempo/prazo:<br>" +
                "t = ${(log10(amountDividedByCapital(amount, capital))) / (log10(ratePlusOne(rate)))} <br>"
    }

    private fun calculateAmountCompoundInterestText(capital: Double, rate: Double, duration: Double): String {
        val rateMaisUm = rate + 1
        val rateMaisUmDuration = (rate + 1).pow(duration)
        return "Substituindo os valores, ficara assim: <br>" +
                "M = ?, C = ${capital}, i = $rate, n = $duration, tempo e taxa convertidos para meses<br<br>" +
                "Como queremos calcular o montante eleve (1 + i) à potência de n:<br>" +
                "M = ${capital}(1+$rate)<sup>${duration}</sup><br>" +
                "Faça a soma, e eleve a duração, depois multiplique o capital:<br>" +
                "M = $capital.({$rateMaisUm})<sup>${duration}</sup><br>" +
                "Após elevar, faça a multiplicação do capital:<br>" +
                "M = $capital.$rateMaisUmDuration<br>" +
                "Quando multiplicamos, temos como resultado o montante:<br>" +
                "M = ${calculateAmountCompoundInterest(capital, rate, duration)}<br><br>"
    }

    private fun defaultTextCompoundInterest(): String {
        return "A formula do juros composto é demonimada pela formula M = C(1+i)<sup>n</sup> <br>" +
                "Onde: M = Montante é o valor total acumulado, incluindo o principal e juros<br>" +
                "C = Capital inicial ou Principal é o valor que foi investido ou emprestado<br>" +
                "i = taxa de juros aplicada ao capital, exmplo: 0,05 para 5% <br>" +
                "n = periodo de tempo o qual o dinherio ficou emprestado ou investido<br<br>"
    }

    private fun calculateAmountCompoundInterest(capital: Double, rate: Double, duration: Double): Double {
        return capital * ((1 + rate).pow(duration))
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

    private fun calculateInterestCaseRateNullOrDurationNullInterest(capital: Double, amount: Double): Double {
        return amount - capital
    }

    private fun amountDividedByCapital(amount: Double, capital: Double): Double {
        return amount / capital
    }

    private fun ratePlusOne(rate: Double): Double {
        return 1 + rate
    }

    private fun rootAmountDividedByCapital(amount: Double, capital: Double, duration: Double): Double {
        return ((amount / capital).pow(1.0 / duration))
    }

    private fun calculateRateCompoundInterest(amount: Double, capital: Double, duration: Double): Double {
        return ((amount / capital).pow(1.0 / duration)) - 1
    }

    private fun calculateCapitalCompoundInterest(amount: Double, rate: Double, duration: Double): Double {
        return amount / (1 + rate.pow(duration))
    }

    private fun calculateDurationCompoundInterst(amount: Double, capital: Double, rate: Double): Double {
        return log10(amount / capital) / log10(1 + rate)
    }

    private fun calculateCapitalCaseAmountIsNullInterestSimple(
        interest: Double,
        rate: Double,
        duration: Double
    ): Double {
        return interest / (rate * duration)
    }
}