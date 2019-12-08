package com.github.emyar.hematournament.tournamentcontrol.ui.settings

import com.github.emyar.hematournament.tournamentcontrol.CommonSettings
import com.github.emyar.hematournament.tournamentcontrol.Settings
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.*
import tornadofx.FX.Companion.messages

class CommonSettingsTab : View(messages["settingswindow.tabs.commonsettings"]) {

    private val availableLocales = FXCollections.observableArrayList(*CommonSettings.Language.values())
    private val selectedLocale = SimpleObjectProperty(Settings.commonSettings.getLanguage())

    override val root = form {
        fieldset {
            field(messages["fields.locale"]) {
                combobox(values = availableLocales, property = selectedLocale)
            }
        }
    }

    fun saveValues() {
        Settings.commonSettings.setLanguage(selectedLocale.value)
    }
}
