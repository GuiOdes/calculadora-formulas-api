package com.guiodes.multifunctionalcalculator.response

class BhaskaraFormulaResponse(
    val resolution: MutableList<String>,
    val result: MutableList<Double>
) {
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

    fun bhaskaraDeltaNegativo(delta: Double, formulaDelta: String) = apply{
        val messageSeg:String = "No seu caso, ficaria assim: "
        val messageTer:String = "Como o Δ tem valor negativo, logo sua equação não possui raiz"
        resolution.add(message)
        resolution.add(messageSeg)
        resolution.add(messageTer)
        resolution.add(formulaDelta)
        result.add(delta)

    }
    fun bhaskaraDeltaZero(x1: Double, formulaBhaskara: String, delta: Double, formulaDelta: String) = apply {
        val messageSeg:String = "No seu caso, ficaria assim: "
        val messageTer:String = "Como o Δ tem valor igual a 0, logo sua equação possui somente uma raiz"
        resolution.add(message)
        resolution.add(messageSeg)
        resolution.add(messageTer)
        resolution.add(formulaDelta)
        resolution.add(formulaBhaskara)
        result.add(delta)
        result.add(x1)

    }
    fun bhaskaraFormula(
        formulaBhaskara: String,
        x1: Double,
        x2: Double,
        formulax1: String,
        formulax2: String,
        formulaDelta: String,
        delta: Double
    ) = apply {
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
    }
}