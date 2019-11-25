import com.github.emyar.hematournament.tournamentcontrol.dataimport.DataParser
import com.github.emyar.hematournament.tournamentcontrol.dataimport.DataProvider
import com.github.emyar.hematournament.tournamentcontrol.dataimport.ImportInfo
import com.github.emyar.hematournament.tournamentcontrol.model.Tournament
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataImport {

    @UnstableDefault
    @Test
    fun parsing() {
        val dataProvider = object : DataProvider {
            override fun getDataToParse(): Pair<List<List<String>>, ImportInfo> {
                val data = listOf(
                    listOf("email1", "name1", "club1", "city1", "nomination1"),
                    listOf("email2", "name2", "club2", "city1", "nomination2"),
                    listOf("email3", "name3", "club3", "city2", "nomination1, nomination2")
                )

                return data to ImportInfo.builder().build()
            }
        }

        assertEquals(
            Json.parse(Tournament.serializer(), javaClass.getResource("dataimport/tournament.json").readText()),
            DataParser().parse(dataProvider)
        )
    }
}