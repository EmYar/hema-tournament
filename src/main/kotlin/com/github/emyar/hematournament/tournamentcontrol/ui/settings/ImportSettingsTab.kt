package com.github.emyar.hematournament.tournamentcontrol.ui.settings

import com.github.emyar.hematournament.tournamentcontrol.Settings
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class ImportSettingsTab : View("Import") {

    private val headersRange = SimpleStringProperty(Settings.importSettings.getHeadersRange())
    private val dataRange = SimpleStringProperty(Settings.importSettings.getDataRange())
    private val emailColumnName = SimpleStringProperty(Settings.importSettings.getEmailColumnName())
    private val fullNameColumnName = SimpleStringProperty(Settings.importSettings.getFullNameColumnName())
    private val clubColumnName = SimpleStringProperty(Settings.importSettings.getClubColumnName())
    private val clubCityColumnName = SimpleStringProperty(Settings.importSettings.getClubCityColumnName())
    private val nominationsColumnName = SimpleStringProperty(Settings.importSettings.getNominationsColumnName())
    private val nominationsDelimiter = SimpleStringProperty(Settings.importSettings.getNominationsDelimiter())

    override val root = form {
        fieldset {
            field("Headers range") { textfield(headersRange) }
            field("Data range") { textfield(dataRange) }
            field("Fighter Email column name") { textfield(emailColumnName) }
            field("Fighter Name column name") { textfield(fullNameColumnName) }
            field("Fighter Club column name") { textfield(clubColumnName) }
            field("Fighter Club City column name") { textfield(clubCityColumnName) }
            field("Fighter Nominations column name") { textfield(nominationsColumnName) }
            field("Fighter Nominations Delimiter column name") { textfield(nominationsDelimiter) }
        }
        button("Reset to default").action { resetToDefaults() }
    }

    private fun resetToDefaults() {
        headersRange.value = Settings.importSettings.headersRangeDefault
        dataRange.value = Settings.importSettings.dataRangeDefault
        emailColumnName.value = Settings.importSettings.emailColumnNameDefault
        fullNameColumnName.value = Settings.importSettings.fullNameColumnNameDefault
        clubColumnName.value = Settings.importSettings.clubColumnNameDefault
        clubCityColumnName.value = Settings.importSettings.clubCityColumnNameDefault
        nominationsColumnName.value = Settings.importSettings.nominationsColumnNameDefault
        nominationsDelimiter.value = Settings.importSettings.nominationsDelimiterDefault
    }
    
    fun saveValues() {
        Settings.importSettings.updateValues(
            headersRange.value,
            dataRange.value,
            emailColumnName.value,
            fullNameColumnName.value,
            clubColumnName.value,
            clubCityColumnName.value,
            nominationsColumnName.value,
            nominationsDelimiter.value
        )
    }
}
