package com.github.emyar.hematournament.tournamentcontrol.dataimport

import com.github.emyar.hematournament.tournamentcontrol.Settings
import com.github.emyar.hematournament.tournamentcontrol.model.*

object DataParser {

    fun parse(provider: DataProvider): Tournament {
        val (data, info) = provider.getDataToParse()

        val nominations = mutableMapOf<String, Nomination>()
        val clubs = mutableMapOf<String, Club>()
        val cities = mutableMapOf<String, City>()

        data.forEach { row ->
            Fighter(
                row[info.fighterEmailColumn],
                row[info.fighterNameColumn],
                getClub(row[info.fighterClubColumn], row[info.fighterCityColumn], clubs, cities)
            ).apply {
                parseNominations(row[info.fighterNominationsColumn], nominations)
                    .forEach { it.addFighter(this) }
            }
        }

        return Tournament(nominations.values)
    }

    private fun parseNominations(
        nominationsString: String,
        parsedNominations: MutableMap<String, Nomination>
    ): Sequence<Nomination> =
        nominationsString.split(Settings.importSettings.getNominationsDelimiter()).asSequence()
            .map {
                val name = it.trim()
                parsedNominations[name]
                    ?: Nomination(name).apply { parsedNominations[name] = this }
            }

    private fun getClub(
        clubName: String,
        cityName: String,
        parsedClubs: MutableMap<String, Club>,
        parsedCities: MutableMap<String, City>
    ): Club {
        val key = clubName + cityName
        return parsedClubs[key]
            ?: Club(clubName, getCity(cityName, parsedCities)).apply { parsedClubs[key] = this }
    }

    private fun getCity(cityName: String, parsedCities: MutableMap<String, City>): City =
        parsedCities[cityName]
            ?: City(cityName).apply { parsedCities[cityName] = this }
}