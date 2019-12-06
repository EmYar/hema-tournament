package com.github.emyar.hematournament.tournamentcontrol.ui.dataimport

import com.github.emyar.hematournament.tournamentcontrol.dataimport.DataParser
import com.github.emyar.hematournament.tournamentcontrol.dataimport.GoogleSheetDataProvider
import com.github.emyar.hematournament.tournamentcontrol.model.Model
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class GoogleSheetsImport : View("Import From Google Sheets") {

    companion object {
        fun show() {
            find<GoogleSheetsImport>().openModal()
        }
    }

    private val spreadSheetId = SimpleStringProperty()
    private val clientSecret = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Spreadsheet id") { textfield(spreadSheetId) }
            field("Client secret") { textarea(clientSecret) }
        }
        checkbox("Save client secret") {  }
        buttonbar {
            button("Import") { action {
                Model.tournament = DataParser.parse(GoogleSheetDataProvider(spreadSheetId.value, clientSecret.value))
            } }
            button("Cancel") { action { close() } }
        }
    }
}
