package com.github.emyar.hematournament.tournamentcontrol.ui.settings

import com.github.emyar.hematournament.tournamentcontrol.Settings
import javafx.scene.control.TabPane
import tornadofx.*
import tornadofx.FX.Companion.messages

class SettingsWindow : View(messages["settingswindow.title"]) {

    companion object {
        fun show() {
            find<SettingsWindow>().openModal()
        }
    }

    override val root = borderpane {
        center = tabpane {
            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
            tab<CommonSettingsTab>()
            tab<ImportSettingsTab>()
        }
        bottom = buttonbar {
            button(messages["buttons.save"]).action {
                find<CommonSettingsTab>().saveValues()
                find<ImportSettingsTab>().saveValues()
                Settings.save()
                close()
            }
            button(messages["buttons.cancel"]).action { close() }
        }
    }
}
