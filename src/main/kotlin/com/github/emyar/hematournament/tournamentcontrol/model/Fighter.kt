package com.github.emyar.hematournament.tournamentcontrol.model

data class Fighter(val email: String,
                   val name: String,
                   val club: Club) {

    override fun equals(other: Any?) =
        this === other ||
                other is Fighter && email == other.email

    override fun hashCode() = email.hashCode()
}