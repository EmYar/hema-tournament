package com.github.emyar.hematournament.tournamentcontrol

import com.github.emyar.hematournament.tournamentcontrol.ui.MainWindow
import javafx.stage.Stage
import tornadofx.App

class EntryPoint : App(MainWindow::class) {

    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = 1280.0
        stage.height = 720.0
    }
}