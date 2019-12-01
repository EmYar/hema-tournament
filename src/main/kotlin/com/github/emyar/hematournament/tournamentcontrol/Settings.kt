package com.github.emyar.hematournament.tournamentcontrol

import org.intellij.lang.annotations.Language
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Paths
import java.util.*
import kotlin.collections.set
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

object Settings {

    private const val settingsVersion = 1
    @Language(value = "FILE REFERENCE")
    private const val defaultSettingsPath = "/settingsdefaults.properties"

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
        subsections.forEach { it.fillProperties() }
        if (!settingsFile.exists()) {
            if (!settingsFile.parentFile.exists())
                settingsFile.parentFile.mkdirs()
            settingsFile.createNewFile()
        }
        currentProperties.store(FileWriter(settingsFile), "Save to settings file '$settingsFile'")
    }

    fun resetToDefault() {
        TODO("Not yet implemented")
    }
}

abstract class SettingsSection(
    private val settingsPrefix: String,
    private val currentProperties: Properties,
    private val defaultProperties: Properties
) {

    private val fields = this::class.memberProperties.asSequence()
        .filterIsInstance<KMutableProperty<String>>()
        .filter { it.name != "settingsPrefix" }
        .onEach { it.isAccessible = true }
        .toList()

    fun fillProperties() {
        fields.forEach { currentProperties[settingsPrefix + it.name] = it.getter.call(this) }
    }

    fun loadValuesFromProperties() {
        fields.forEach { it.setter.call(this, getCurrentValue(it.name)) }
    }

    fun resetToDefaults() {
        fields.forEach { it.setter.call(this, defaultProperties[settingsPrefix + it.name]) }
    }

    protected fun getCurrentValue(key: String): String =
        currentProperties[settingsPrefix + key] as String?
            ?: defaultProperties[settingsPrefix + key] as String

    protected fun getDefaultValue(key: String): String =
        defaultProperties[settingsPrefix + key] as String
}

class CommonSettings(
    currentProperties: Properties,
    defaultProperties: Properties
) : SettingsSection("", currentProperties, defaultProperties) {

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
}

class ImportSettings(
    currentProperties: Properties,
    defaultProperties: Properties
) : SettingsSection("import.", currentProperties, defaultProperties) {

    val headersRangeDefault = getDefaultValue("headersRange")
    val dataRangeDefault = getDefaultValue("dataRange")
    val emailColumnNameDefault = getDefaultValue("emailColumnName")
    val fullNameColumnNameDefault = getDefaultValue("fullNameColumnName")
    val clubColumnNameDefault = getDefaultValue("clubColumnName")
    val clubCityColumnNameDefault = getDefaultValue("clubCityColumnName")
    val nominationsColumnNameDefault = getDefaultValue("nominationsColumnName")
    val nominationsDelimiterDefault = getDefaultValue("nominationsDelimiter")

    private var headersRange = getCurrentValue("headersRange")
    private var dataRange = getCurrentValue("dataRange")
    private var emailColumnName = getCurrentValue("emailColumnName")
    private var fullNameColumnName = getCurrentValue("fullNameColumnName")
    private var clubColumnName = getCurrentValue("clubColumnName")
    private var clubCityColumnName = getCurrentValue("clubCityColumnName")
    private var nominationsColumnName = getCurrentValue("nominationsColumnName")
    private var nominationsDelimiter = getCurrentValue("nominationsDelimiter")

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
        this.dataRange = dataRange;
        this.emailColumnName = emailColumnName;
        this.fullNameColumnName = nameColumnName;
        this.clubColumnName = clubColumnName;
        this.clubCityColumnName = clubCityColumnName;
        this.nominationsColumnName = nominationsColumnName;
        this.nominationsDelimiter = nominationsDelimiter;
    }
}