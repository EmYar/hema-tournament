package com.github.emyar.hematournament.tournamentcontrol.dataimport

class ImportInfo internal constructor(val fighterEmailColumn: Int = 0,
                                      val fighterNameColumn: Int = 1,
                                      val fighterClubColumn: Int = 2,
                                      val fighterCityColumn: Int = 3,
                                      val fighterNominationsColumn: Int = 4) {

    companion object {
        fun builder(): ImportInfoBuilder = ImportInfoBuilder()
    }
}

class ImportInfoBuilder internal constructor() {

    var fighterEmailColumn: Int = 0
    var fighterNameColumn: Int = 0
    var fighterClubColumn: Int = 0
    var fighterCityColumn: Int = 0
    var fighterNominationsColumn: Int = 0

    fun build() = ImportInfo(fighterEmailColumn, fighterNameColumn, fighterClubColumn, fighterCityColumn, fighterNominationsColumn)
}