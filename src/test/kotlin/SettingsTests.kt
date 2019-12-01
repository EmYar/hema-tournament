//import com.github.emyar.hematournament.tournamentcontrol.CommonSettings
//import com.github.emyar.hematournament.tournamentcontrol.ImportSettings
//import io.kotlintest.shouldBe
//import io.kotlintest.shouldFail
//import io.kotlintest.shouldNotThrowExactly
//import io.kotlintest.shouldThrowExactly
//import io.kotlintest.specs.FunSpec
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import java.util.*
//
//class SettingsTests : FunSpec({
//
////    test("CommonSettings should not apply unsupported locales") {
////        val commonSettings = CommonSettings()
////        for (locale in Locale.getAvailableLocales())
////            if (!CommonSettings.supportedLocales.contains(locale))
////                shouldThrowExactly<IllegalArgumentException> { commonSettings.setLanguage(locale.language) }
////            else
////                shouldNotThrowExactly<IllegalArgumentException> { commonSettings.setLanguage(locale.language) }
////    }
//
//    test("CommonSettings should save values") {
//        val commonSettings = CommonSettings()
//        for (locale in CommonSettings.supportedLocales) {
//            commonSettings.setLanguage(locale.language)
//            commonSettings.getLocale() shouldBe locale
//        }
//    }
//
//    test("ImportSettings should save values") {
//        val headersRange = "A1:B1"
//        val dataRange = "A1:B100"
//        val emailColumnName = "em"
//        val fullNameColumnName = "na"
//        val clubColumnName = "cl"
//        val clubCityColumnName = "ci"
//        val nominationsColumnName = "no"
//        val nominationsDelimiter = "^"
//        val importSettings = ImportSettings().apply {
//            updateValues(
//                headersRange,
//                dataRange,
//                emailColumnName,
//                fullNameColumnName,
//                clubColumnName,
//                clubCityColumnName,
//                nominationsColumnName,
//                nominationsDelimiter
//            )
//        }.getValues()
//        val expectedValues = arrayOf(
//            "import.headersRange" to headersRange,
//            "import.dataRange" to dataRange,
//            "import.emailColumnName" to emailColumnName,
//            "import.fullNameColumnName" to fullNameColumnName,
//            "import.clubColumnName" to clubColumnName,
//            "import.clubCityColumnName" to clubCityColumnName,
//            "import.nominationsColumnName" to nominationsColumnName,
//            "import.nominationsDelimiter" to nominationsDelimiter
//        )
//
//        importSettings.size shouldBe expectedValues.size
//        for ((property, value) in expectedValues)
//            importSettings[property] shouldBe value
////        "expected value of $property: <$value> but was <importSettings[property]>"
//    }
//})