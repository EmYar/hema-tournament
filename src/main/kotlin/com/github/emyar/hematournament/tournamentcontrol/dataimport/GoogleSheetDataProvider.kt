package com.github.emyar.hematournament.tournamentcontrol.dataimport

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.File

class GoogleSheetDataProvider(private val spreadsheetId: String,
                              private val clientSecret: String)
    : DataProvider {

    companion object {
        private const val APPLICATION_NAME = "HEMA Tournament"
        private const val CREDENTIALS_FOLDER = "google_sheets_credentials"
        private val SCOPES = listOf(SheetsScopes.SPREADSHEETS)
    }

    private val jsonFactory = JacksonFactory.getDefaultInstance()
    private val sheets: Sheets

    init {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        sheets = Sheets.Builder(httpTransport, jsonFactory, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build()
    }

    override fun getDataToParse(): Pair<List<List<String>>, ImportInfo> {
        @Suppress("UNCHECKED_CAST")
        val headers: List<String> = sheets.spreadsheets().values()
                .get(spreadsheetId, ImportSettings.headersRange)
                .execute()
                .getValues()
                .first() as List<String>

        val importInfoBuilder = ImportInfo.builder()
        headers.forEachIndexed {index, header ->
            when (header) {
                ImportSettings.fighterEmailColumnName -> importInfoBuilder.fighterEmailColumn = index
                ImportSettings.fighterNameColumnName -> importInfoBuilder.fighterNameColumn = index
                ImportSettings.fighterClubColumnName -> importInfoBuilder.fighterClubColumn = index
                ImportSettings.fighterClubCityColumnName -> importInfoBuilder.fighterCityColumn = index
                ImportSettings.fighterNominationsColumnName -> importInfoBuilder.fighterNominationsColumn = index
            }
        }

        @Suppress("UNCHECKED_CAST")
        val data: List<List<String>> = sheets.spreadsheets().values()
                .get(spreadsheetId, ImportSettings.dataRange)
                .execute()
                .getValues() as List<List<String>>

        return data to importInfoBuilder.build()
    }

    private fun getCredentials(httpTransport: HttpTransport): Credential {
        val clientSecrets = jsonFactory.fromString(clientSecret, GoogleClientSecrets::class.java)
        val flow = GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, SCOPES)
                .setDataStoreFactory(FileDataStoreFactory(File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build()

        return AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")
    }
}