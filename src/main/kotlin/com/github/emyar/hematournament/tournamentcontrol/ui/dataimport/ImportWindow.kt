package com.github.emyar.hematournament.tournamentcontrol.ui.dataimport

import tornadofx.View
import tornadofx.borderpane
import tornadofx.hbox
import tornadofx.radiobutton

class ImportWindow : View() {

    override val root = borderpane {
        top = hbox {
            radiobutton("Google Sheets")
            radiobutton("Excel")
        }
    }
}