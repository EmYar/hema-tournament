package com.github.emyar.hematournament.tournamentcontrol.ui

import com.github.emyar.hematournament.tournamentcontrol.ui.dataimport.FileImport
import com.github.emyar.hematournament.tournamentcontrol.ui.dataimport.GoogleSheetsImport
import com.github.emyar.hematournament.tournamentcontrol.ui.settings.SettingsWindow
import tornadofx.*
import tornadofx.FX.Companion.messages

class MainWindow : View(messages["mainwindow.title"]) {

    override val root = borderpane {
        top = menubar {
            menu(messages["menu.file"]) {
                menu(messages["menu.file.import"]) {
                    item(messages["menu.file.import.googlesheets"]) { action { GoogleSheetsImport.show() } }
                    item(messages["menu.file.import.file"]) { action { FileImport.show() } }
                }
            }
            menu(messages["menu.edit"]) {
                item(messages["menu.edit.settings"]) { action { SettingsWindow.show() } }
            }
            menu(messages["menu.help"]) {
                item(messages["menu.help.about"]) { action { TODO("Not yet implemented") } }
            }
        }
    }
}