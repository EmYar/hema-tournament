package com.github.emyar.hematournament.tournamentcontrol.model

import kotlinx.serialization.Serializable

@Serializable
data class Fighter(val email: String,
                   val name: String,
                   val club: Club,
                   val nominations: List<Nomination>) {

    override fun equals(other: Any?) =
        this === other ||
                other is Fighter && email == other.email

    override fun hashCode() = email.hashCode()
}