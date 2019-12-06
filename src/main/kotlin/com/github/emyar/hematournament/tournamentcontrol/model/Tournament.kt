package com.github.emyar.hematournament.tournamentcontrol.model

class Tournament(nominations: Collection<Nomination>) {

    val nominations: List<Nomination> = nominations.toMutableList()

    fun addNomination(nomination: Nomination) {
        (nominations as MutableList).add(nomination)
    }
}