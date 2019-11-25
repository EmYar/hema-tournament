package com.github.emyar.hematournament.tournamentcontrol.dataimport

interface DataProvider {

    fun getDataToParse(): Pair<List<List<String>>, ImportInfo>
}