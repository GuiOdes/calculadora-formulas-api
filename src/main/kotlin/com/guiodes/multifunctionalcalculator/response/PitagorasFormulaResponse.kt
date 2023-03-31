package com.guiodes.multifunctionalcalculator.response
class PitagorasFormulaResponse(
    val resolution: MutableList<String>,
    val result: MutableList<Double>
) {
    val message: String =
        "A fórmula de Pitágoras é uma expressão matemática que relaciona os lados de um triângulo retângulo. Ela afirma que o quadrado da hipotenusa (o lado oposto ao ângulo reto) é igual à soma dos quadrados dos catetos (os lados adjacentes ao ângulo reto). A fórmula pode ser escrita como:\n" +
                "\n" +
                "a² + b² = c²\n" +
                "\n\n" +
                "Onde \"a\" e \"b\" são catetos, enquanto \"c\" é a hipotenusa.\n" +
                "\n" +
                "Para ser possível calcular precisa ter dois(2) dos três(3) valores da formula, e aplicar:"

    fun pitagorasHipotenusa(
        hipotenusa: Double,
        somaQuadradoCateto: String,
        raizSomaCateto: String,
        formulaPitagoras: String
    ) = apply {
        val messageSeg =
            "No caso para calcular o valor da hipotenusa, substitua A e B na formula pelos valores \"a\" e \"b\", na formula abaixo:"
        val messageTer = "faça o quadrado do valor de cada um dos catetos, \"a\" e \"b\", e some eles, resultando em:"
        val messageQuar = "depois tire a raiz quadrada da soma dos catetos para ter o valor da hipotenusa:"
        resolution.add(message)
        resolution.add(messageSeg)
        resolution.add(formulaPitagoras)
        resolution.add(messageTer)
        resolution.add(somaQuadradoCateto)
        resolution.add(messageQuar)
        resolution.add(raizSomaCateto)
        result.add(hipotenusa)
    }

    fun pitagorasCateto(
        catetoFaltante: Double,
        subtracaoQuadradoCatetoHipotenusa: String,
        raizSubtracaoCatetoHipotenusa: String,
        formulaPitagoras: String
    ) = apply {
        val messageSeg =
            "No caso para descobrir o valor de um dos catetos, substitua o cateto na formula pelo valor dele, e o valor da hipotenusa em \"c\", na formula abaixo:"
        val messageTer = "faça o quadrado do valor do cateto e da hipotenusa o qual você tem o valor, e passe o valor para o outro lado subtraindo:"
        val messageQuar = "depois tire a raiz quadrada do valor resultante da subtração, para ter o valor do cateto faltante:"
        resolution.add(message)
        resolution.add(messageSeg)
        resolution.add(formulaPitagoras)
        resolution.add(messageTer)
        resolution.add(subtracaoQuadradoCatetoHipotenusa)
        resolution.add(messageQuar)
        resolution.add(raizSubtracaoCatetoHipotenusa)
        result.add(catetoFaltante)
    }
}
