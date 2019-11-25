package com.github.emyar.hematournament.tournamentcontrol.model

import kotlinx.serialization.Serializable

@Serializable
data class Tournament(val fighters: List<Fighter>)