package com.github.emyar.hematournament.tournamentcontrol.model

import kotlinx.serialization.Serializable

@Serializable
data class Club(val name: String, val city: City)