package com.github.emyar.hematournament.tournamentcontrol

import org.intellij.lang.annotations.Language
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Paths
import java.util.*
import kotlin.collections.set

object Settings {

    private const val settingsVersion = 1
    @Language(value = "FILE REFERENCE")
    private const val defaultSettingsPath = "/settings_defaults.properties"

    private val settingsFile =
        Paths.get(System.getProperty("user.home"), ".hema-tournament", "settings.properties").toFile()
    private val defaultProperties =
        Properties().apply { load(Settings::class.java.getResourceAsStream(defaultSettingsPath)) }
    private var currentProperties: Properties =
        settingsFile.let { Properties().apply { if (it.exists()) load(FileReader(it)) } }

    val commonSettings = CommonSettings(currentProperties, defaultProperties)
    val importSettings = ImportSettings(currentProperties, defaultProperties)

    private val subsections = listOf(commonSettings, importSettings).apply { forEach { it.loadValuesFromProperties() } }

    fun save() {
        currentProperties["settingsVersion"] = settingsVersion.toString()
        subsections.forEach { it.saveValuesToProperties() }
        if (!settingsFile.exists()) {
            val parentFile = settingsFile.parentFile
            if (!parentFile.exists())
                parentFile.mkdirs()
            settingsFile.createNewFile()
        }
        currentProperties.store(FileWriter(settingsFile), "Save to settings file '$settingsFile'")
    }

    fun resetToDefault() {
        subsections.forEach { it.resetToDefaults() }
    }
}

abstract class SettingsSection(
    private val settingsPrefix: String,
    private val currentProperties: Properties,
    private val defaultProperties: Properties
) {

    abstract fun saveValuesToProperties()

    abstract fun loadValuesFromProperties()

    abstract fun resetToDefaults()

    protected fun saveValue(key: String, value: String) {
        currentProperties[settingsPrefix + key] = value
    }

    protected fun getValue(key: String): String =
        currentProperties[settingsPrefix + key] as String?
            ?: defaultProperties[settingsPrefix + key] as String

    protected fun getDefaultValue(key: String): String =
        defaultProperties[settingsPrefix + key] as String
}

class CommonSettings(currentProperties: Properties, defaultProperties: Properties) :
    SettingsSection("", currentProperties, defaultProperties) {

    companion object {
        val supportedLocales = setOf(Locale("en", "EN"), Locale("ru", "RU"))
    }

    private lateinit var language: String

    fun getLocale(): Locale = supportedLocales.first { it.language == language }

    fun setLanguage(language: String) {
        if (supportedLocales.none { it.language == language })
            throw IllegalArgumentException("Language '$language' is not in supported list: $supportedLocales")
        this.language = language
    }

    override fun saveValuesToProperties() {
        saveValue("language", language)
    }

    override fun loadValuesFromProperties() {
        language = getValue("language")
    }

    override fun resetToDefaults() {
        language = getDefaultValue("language")
    }
}

class ImportSettings(currentProperties: Properties, defaultProperties: Properties) :
    SettingsSection("import.", currentProperties, defaultProperties) {

    val headersRangeDefault = getDefaultValue("headersRange")
    val dataRangeDefault = getDefaultValue("dataRange")
    val emailColumnNameDefault = getDefaultValue("emailColumnName")
    val fullNameColumnNameDefault = getDefaultValue("fullNameColumnName")
    val clubColumnNameDefault = getDefaultValue("clubColumnName")
    val clubCityColumnNameDefault = getDefaultValue("clubCityColumnName")
    val nominationsColumnNameDefault = getDefaultValue("nominationsColumnName")
    val nominationsDelimiterDefault = getDefaultValue("nominationsDelimiter")

    private var headersRange = getValue("headersRange")
    private var dataRange = getValue("dataRange")
    private var emailColumnName = getValue("emailColumnName")
    private var fullNameColumnName = getValue("fullNameColumnName")
    private var clubColumnName = getValue("clubColumnName")
    private var clubCityColumnName = getValue("clubCityColumnName")
    private var nominationsColumnName = getValue("nominationsColumnName")
    private var nominationsDelimiter = getValue("nominationsDelimiter")

    fun getHeadersRange(): String = headersRange
    fun getDataRange(): String = dataRange
    fun getEmailColumnName(): String = emailColumnName
    fun getFullNameColumnName(): String = fullNameColumnName
    fun getClubColumnName(): String = clubColumnName
    fun getClubCityColumnName(): String = clubCityColumnName
    fun getNominationsColumnName(): String = nominationsColumnName
    fun getNominationsDelimiter(): String = nominationsDelimiter

    fun updateValues(
        headersRange: String = this.headersRange,
        dataRange: String = this.dataRange,
        emailColumnName: String = this.emailColumnName,
        nameColumnName: String = this.fullNameColumnName,
        clubColumnName: String = this.clubColumnName,
        clubCityColumnName: String = this.clubCityColumnName,
        nominationsColumnName: String = this.nominationsColumnName,
        nominationsDelimiter: String = this.nominationsDelimiter
    ) {
        this.headersRange = headersRange
        this.dataRange = dataRange
        this.emailColumnName = emailColumnName
        this.fullNameColumnName = nameColumnName
        this.clubColumnName = clubColumnName
        this.clubCityColumnName = clubCityColumnName
        this.nominationsColumnName = nominationsColumnName
        this.nominationsDelimiter = nominationsDelimiter
    }

    override fun saveValuesToProperties() {
        saveValue("headersRange", headersRange)
        saveValue("dataRange", dataRange)
        saveValue("emailColumnName", emailColumnName)
        saveValue("fullNameColumnName", fullNameColumnName)
        saveValue("clubColumnName", clubColumnName)
        saveValue("clubCityColumnName", clubCityColumnName)
        saveValue("nominationsColumnName", nominationsColumnName)
        saveValue("nominationsDelimiter", nominationsDelimiter)
    }

    override fun loadValuesFromProperties() {
        headersRange = getValue("headersRange")
        dataRange = getValue("dataRange")
        emailColumnName = getValue("emailColumnName")
        fullNameColumnName = getValue("fullNameColumnName")
        clubColumnName = getValue("clubColumnName")
        clubCityColumnName = getValue("clubCityColumnName")
        nominationsColumnName = getValue("nominationsColumnName")
        nominationsDelimiter = getValue("nominationsDelimiter")
    }

    override fun resetToDefaults() {
        headersRange = getDefaultValue("headersRange")
        dataRange = getDefaultValue("dataRange")
        emailColumnName = getDefaultValue("emailColumnName")
        fullNameColumnName = getDefaultValue("fullNameColumnName")
        clubColumnName = getDefaultValue("clubColumnName")
        clubCityColumnName = getDefaultValue("clubCityColumnName")
        nominationsColumnName = getDefaultValue("nominationsColumnName")
        nominationsDelimiter = getDefaultValue("nominationsDelimiter")
    }
}