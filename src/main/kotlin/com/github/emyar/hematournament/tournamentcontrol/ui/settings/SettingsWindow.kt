package com.github.emyar.hematournament.tournamentcontrol.ui.settings

import com.github.emyar.hematournament.tournamentcontrol.Settings
import javafx.scene.control.TabPane
import tornadofx.*

class SettingsWindow : View("Settings") {

    companion object {
        fun show() {
            find<SettingsWindow>().openModal()
        }
    }

    override val root = borderpane {
        center = tabpane {
            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
            tab<ImportSettingsTab>()
        }
        bottom = buttonbar {
            button("Save").action {
                find<ImportSettingsTab>().saveValues()
                Settings.save()
                close()
            }
            button("Cancel").action { close() }
        }
    }
}
