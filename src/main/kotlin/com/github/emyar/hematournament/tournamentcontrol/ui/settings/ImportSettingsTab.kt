package com.github.emyar.hematournament.tournamentcontrol.ui.settings

import com.github.emyar.hematournament.tournamentcontrol.Settings
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import tornadofx.FX.Companion.messages

class ImportSettingsTab : View(messages["settingswindow.tabs.importsettings"]) {

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
            field(messages["fields.headers"]) { textfield(headersRange) }
            field(messages["fields.datarange"]) { textfield(dataRange) }
            field(messages["fields.email"]) { textfield(emailColumnName) }
            field(messages["fields.name"]) { textfield(fullNameColumnName) }
            field(messages["fields.club"]) { textfield(clubColumnName) }
            field(messages["fields.city"]) { textfield(clubCityColumnName) }
            field(messages["fields.nominations"]) { textfield(nominationsColumnName) }
            field(messages["fields.delimiter"]) { textfield(nominationsDelimiter) }
        }
        button(messages["buttons.reset"]).action { resetToDefaults() }
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
