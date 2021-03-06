package com.github.emyar.hematournament.tournamentcontrol

import com.github.emyar.hematournament.tournamentcontrol.ui.MainWindow
import javafx.scene.Scene
import tornadofx.App
import tornadofx.FX
import tornadofx.UIComponent

class EntryPoint : App(MainWindow::class) {

    init {
        FX.locale = Settings.commonSettings.getLanguage().locale
    }

    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 1280.0, 720.0)
}