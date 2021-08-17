package com.br.algorithms.montyhall

import com.br.algorithms.ext.randomRange


/**
 *https://pt.wikipedia.org/wiki/Problema_de_Monty_Hall
 *
 * Simulacao 1
 *
 * 1) temos a porta A, B e C
 * 2) o premio esta na porta A
 * 3) o participante escolhe a porta A
 * 4) o apresentador abre a porta B
 * 5) o apresentador pergunta se o participante quer trocar
 * 6) o participante troca para porta C e erra
 *
 *
 * Simulacao 2 - mantendo as condicoes 1 e 2 da simulacao 1
 *
 * 3) o participante escolhe a porta B
 * 4) o apresentador abre a porta C
 * 5) o apresentador pergunta ao participante se ele quer trocar
 * 6) o participante troca e acerta
 *
 *
 * Simulacao 3 - mantendo as condicoes 1 e 2 da simulacao 1
 *
 * 3) o participante escolher a porta C
 * 4) o apresentador abre a porta B
 * 5) o apresentador pergunta ao participante se ele desja troca
 * 6) o participante troca e acerta
 *
 * Vemos que em 2 de 3 situacoes a troca e favorável e a condicao eh
 * que o participante tenha escolhido a porta errada na primeira vez
 *
 * */


data class GameResult(
    val prizedDoor: Int,
    val firstChosenDoor: Int,
    val secondChosenDoor: Int,
    val hasChange: Boolean
)

private fun simulationWithChange(quantityGames: Int, percentageToChangeChoose: Double) {
    val qGamesShouldChangeChoice = (quantityGames * percentageToChangeChoose / 100.0).toInt()

    println("************************************************************************************************")
    println("Quantidade de simulacoes: $quantityGames - Quantidade de trocas: $qGamesShouldChangeChoice")

    val gameThatShouldChangeChoice = (0..quantityGames).randomRange(qGamesShouldChangeChoice)
    val results = mutableListOf<GameResult>()

    for (i in 0..quantityGames) {
        val prizedDoor = fromOneToThree
        val chosenDoor = fromOneToThree
        val change = gameThatShouldChangeChoice.contains(i)
        val newChosenDor = if (change) {
            choosingLastDoor(prizedDoor, chosenDoor)
        } else {
            chosenDoor
        }
        results.add(GameResult(prizedDoor, chosenDoor, newChosenDor, change))
    }

    val gamesHasChange = results.filter { it.hasChange }
    val qGamesWonWithChange = gamesHasChange.filter { it.prizedDoor == it.secondChosenDoor }.count()
    println(
        "Mudancas Realizadas: ${gamesHasChange.size}." +
                " Mudancas que resultaram em vitoria: $qGamesWonWithChange - ${qGamesWonWithChange * 1.0 / gamesHasChange.size}"
    )

    val qGamesWonWithoutChange = results.filter { !it.hasChange && it.prizedDoor == it.firstChosenDoor }.count()

    println("Games vencidos sem troca: $qGamesWonWithoutChange\nGames vencidos com troca: $qGamesWonWithChange")
    println("************************************************************************************************")

}

private fun simulationFrom1To100Percent() {
    for (i in 1..100) {
        simulationWithChange(100000, i * 1.0)
    }
}

private fun simulationII(quantityGames: Int) {
    var g1 = 0
    var p1 = 0
    var g2 = 0
    var p2 = 0
    for (i in 1..quantityGames) {
        val prizeWinning = fromOneToThree
        val playerChoice = fromOneToThree
        val presenterChoice = choosingLastDoor(prizeWinning, playerChoice)
        /*
        println(
            "Prized Door $prizeWinning, Player Choice: $playerChoice, Presenter Choice: $presenterChoice, " +
                    "${playerChoice == presenterChoice}, ${prizeWinning == presenterChoice}"
        )
         */

        // ficar com a primeira escolha
        if (prizeWinning == playerChoice)
            g1 += 1
        else
            p1 += 1

        // trocar para porta 1
        if (playerChoice != 1 && presenterChoice != 1) {
            if (prizeWinning == 1)
                g2 += 1
            else
                p2 += 1
        }

        // trocar para a porta 2
        else if(playerChoice != 2 && presenterChoice != 2) {
            if (prizeWinning == 2)
                g2 += 1
            else
                p2 += 1
        }
        // trocar para porta 3
        else {
            if (prizeWinning == 3)
                g2 += 1
            else
                p2 += 1
        }

        // ficar e ganhar = g1 ficar e perder = p1
        // trocar e ganhar = g2 trocar e perder = p2
/*
        println("Premio na porta: $prizeWinning, Jogador: $playerChoice, " +
                "Apresenntador: $presenterChoice\n" +
                "Ganhos sem troca: $g1\nPerdas sem troca: $p1\n" +
                "Ganhos com troca: $g2\nPerdas com troca: $p2\n")

 */
    }

    println("Simulacoes: $quantityGames\n" +
            "Ganho sem troca: ${g1 * 1.0 / quantityGames}\n" +
            "Perda sem troca: ${p1 * 1.0 / quantityGames}\n" +
            "Ganho com troca: ${g2 * 1.0 / quantityGames}\n" +
            "Perda com troca: ${p2 * 1.0 / quantityGames}\n")
}

private fun simulationFrom10ToInf() {
    var x = 10
    while (x < 1000000000) {
        simulationII(x)
        x *= 10
    }
}

fun main() {
    //simulationFrom1To100Percent()
    simulationFrom10ToInf()
}