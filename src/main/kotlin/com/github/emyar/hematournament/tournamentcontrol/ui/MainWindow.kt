package com.github.emyar.hematournament.tournamentcontrol.ui

import tornadofx.*

class MainWindow : View() {

    override val root = borderpane {
        top = menubar {
            menu("File") {
                item("Import data").apply {
                    action {

                    }
                }
            }
            menu("Edit") {
                item("Settings")
            }
            menu("Help") {
                item("About")
            }
        }
    }
}