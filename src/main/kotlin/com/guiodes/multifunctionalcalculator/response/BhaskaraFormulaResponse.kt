package com.guiodes.multifunctionalcalculator.response

class BhaskaraFormulaResponse {
    fun bhaskaraDeltaNegativo(delta: Double, formulaDelta: String){

        val message:String = "Para calcular o \"Δ\" usamos a formula b² - 4ac, " +
                "que no seu caso, ficaria assim $formulaDelta \n\n" +
                "É importante lembrar que a equação quadrática só tem solução " +
                "real se o discriminante (Δ) for maior ou igual a zero. " +
                "Caso contrário, a equação não possui solução real. \n\n" +
                "Essa regra se aplica nesse seu caso, onde o \"Δ\" é negativo, logo" +
                "a equação não possui valores reais"
    }
    fun bhaskaraDeltaZero(x1: Double, formulaBhaskara: String) {
        val message:String = "Para calcular a formula de bhaskara usamos a formula" +
                "x = (-b ± √(Δ)) / 2a, onde: Δ = b² - 4ac \n\n" +
                "Onde:\n" +
                "\n" +
                "x são as raízes da equação\n" +
                "a, b e c são coeficientes da equação quadrática, com a ≠ 0\n" +
                "± indica que você deve encontrar duas soluções, uma adicionando a raiz e outra subtraindo.\n" +
                "Quando o resultado de Δ for igual a zero, só existe a possibilidade de encontrar uma raiz, " +
                "podendo trabalhar a formula assim:\n" +
                "x = -b±√(0)+/2a\n" +
                "Como √(0) = 0, logo: \n"+
                "x = -b/2a\n" +
                "Assim, sua conta fica assim x = $formulaBhaskara\n" +
                "x = $x1"
    }
    fun bhaskaraFormula(
        formulaBhaskara: String,
        x1: Double,
        x2: Double,
        formulax1: String,
        formulax2: String,
        formulaDelta: String,
        delta: Double
    ) {
        val message:String ="A fórmula de Bhaskara é uma fórmula matemática usada para encontrar as raízes de uma equação quadrática, que é uma equação polinomial de segundo grau. A fórmula é dada por:\n" +
                "\n" +
                "x = (-b ± √(Δ)) / 2a, onde: Δ = b² - 4ac\n" +
                "\n" +
                "Onde:\n" +
                "\n" +
                "x são as raízes da equação\n" +
                "a, b e c são coeficientes da equação quadrática, com a ≠ 0\n" +
                "± indica que você deve encontrar duas soluções, uma adicionando a raiz e outra subtraindo.\n\n" +
                "Dado os valores de A, B e C, essa será sua formula \n" +
                "x = $formulaBhaskara \n" +
                "Δ = $formulaDelta\n " +
                "Δ = $delta \n\n" +
                "Dado o valor de Δ, podemos calcular as duas raizes \n\n" +
                "A primeira raiz será descoberta pela seguinte formula \n" +
                "x' = $formulax1 \n" +
                "x' = $x1 \n\n" +
                "A segunda raiz será pela formula subtraindo o Δ \n\n" +
                "x\"= $formulax2 \n" +
                "x\" = $x2 \n"
    }
}