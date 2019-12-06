package com.github.emyar.hematournament.tournamentcontrol.model

class Nomination(val name: String, fighters: Collection<Fighter> = emptyList()) {

    val fighters: List<Fighter> = fighters.toMutableList()

    fun addFighter(fighter: Fighter) {
        (fighters as MutableList<Fighter>).add(fighter)
    }

    override fun equals(other: Any?) =
        this === other ||
                other is Nomination && name == other.name

    override fun hashCode() = name.hashCode()

    override fun toString() = name
}