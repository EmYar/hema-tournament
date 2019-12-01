package com.github.emyar.hematournament.tournamentcontrol.ui

import com.github.emyar.hematournament.tournamentcontrol.ui.dataimport.FileImport
import com.github.emyar.hematournament.tournamentcontrol.ui.dataimport.GoogleSheetsImport
import com.github.emyar.hematournament.tournamentcontrol.ui.settings.SettingsWindow
import tornadofx.*

class MainWindow : View() {

    override val root = borderpane {
        top = menubar {
            menu("File") {
                menu("Import") {
                    item("From Google Sheets") { action { GoogleSheetsImport.show() } }
                    item("From file") { action { FileImport.show() } }
                }
            }
            menu("Edit") {
                item("Settings") { action { SettingsWindow.show() } }
            }
            menu("Help") {
                item("About") { action { TODO("Not yet implemented") } }
            }
        }
    }
}