package controllers
import Controllers.TableAPI
import Models.Table
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.*
import persistence.XMLSerializer
import java.io.File

class TableAPITest {
    private var JoePerson1: Table? = null
    private var james: Table? = null
    private var populatedTables: TableAPI? = TableAPI(XMLSerializer(File("Tables.xml")))
    @BeforeEach
    fun setup() {
        JoePerson1 = Table("joe", false, true, true, true, true, true, true)
        james = Table("james", false, false, false, false, false, false, false)
        populatedTables!!.addTable(JoePerson1!!)
        populatedTables!!.addTable(james!!)
    }
    @AfterEach
    fun tearDown() {
        JoePerson1 = null
        james = null
    }
    @Nested
    internal inner class AddUser {
        @Test
        fun `adding a new user `() {
            val newTable = Table("tommy", false, false, false, false, false, false, false)
        }
    }
    @Nested
    inner class PersistenceTests {

        @Test
        fun `saving and loading an empty collection in XML doesn't crash app`() {
            // Saving an empty tables.XML file.
            val storingTables = TableAPI(XMLSerializer(File("tables.xml")))
            storingTables.store()

            //Loading the empty tables.xml file into a new object
            val loadedTables = TableAPI(XMLSerializer(File("tables.xml")))
            loadedTables.load()

            //Comparing the source of the tables (storingNotes) with the XML loaded tables (loadedTables)
            Assertions.assertEquals(0, storingTables.listnmbtables())
            Assertions.assertEquals(0, loadedTables.listnmbtables())
            assertEquals(storingTables.listnmbtables(), loadedTables.listnmbtables())
        }

        @Test
        fun `saving and loading an loaded collection in XML doesn't loose data`() {
            // Storing 3 notes to the notes.XML file.
            val storingTables = TableAPI(XMLSerializer(File("tables.xml")))
            storingTables.addTable(JoePerson1!!)
            storingTables.addTable(james!!)

            storingTables.store()

            //Loading tables.xml into a different collection
            val loadedTables = TableAPI(XMLSerializer(File("tables.xml")))
            loadedTables.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedTables)
            Assertions.assertEquals(2, storingTables.listnmbtables())
            Assertions.assertEquals(2, loadedTables.listnmbtables())
            assertEquals(storingTables.listnmbtables(), loadedTables.listnmbtables())
            assertEquals(storingTables.findNote(0), loadedTables.findNote(0))
            assertEquals(storingTables.findNote(1), loadedTables.findNote(1))
            assertEquals(storingTables.findNote(2), loadedTables.findNote(2))
        }
    }
}
