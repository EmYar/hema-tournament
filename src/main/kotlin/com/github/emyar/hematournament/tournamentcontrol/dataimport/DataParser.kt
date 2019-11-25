package com.github.emyar.hematournament.tournamentcontrol.dataimport

import com.github.emyar.hematournament.tournamentcontrol.model.*

class DataParser {

    private val cities = mutableMapOf<String, City>()
    private val clubs = mutableMapOf<String, Club>()
    private val nominations = mutableMapOf<String, Nomination>()

    fun parse(provider: DataProvider): Tournament {
        val result = provider.getDataToParse()
        val data = result.first
        val info = result.second

        return Tournament(data.map { row ->
            Fighter(row[info.fighterEmailColumn],
                    row[info.fighterNameColumn],
                    getClub(row[info.fighterClubColumn], row[info.fighterCityColumn]),
                    getNominations(row[info.fighterNominationsColumn]))
        })
    }

    private fun getClub(clubName: String, cityName: String): Club {
        val key = clubName + cityName
        return if (clubs.contains(key))
            clubs[key]!!
        else {
            val club = Club(clubName, getCity(cityName))
            clubs[key] = club
            club
        }
    }

    private fun getCity(cityName: String): City {
        return if (cities.contains(cityName))
            cities[cityName]!!
        else {
            val club = City(cityName)
            cities[cityName] = club
            club
        }
    }

    private fun getNominations(nominationsString: String): List<Nomination> {
        return nominationsString.split(ImportSettings.fighterNominationsDelimiter).asSequence()
                .map {
                    val nominationName = it.trim()
                    if (nominations.contains(nominationName))
                        nominations[nominationName]!!
                    else {
                        val nomination = Nomination(nominationName)
                        nominations[nominationName] = nomination
                        nomination
                    }
                }
                .toList()
    }
}