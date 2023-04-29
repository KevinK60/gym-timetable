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
        populatedTables!!.add(JoePerson1!!)
        populatedTables!!.add(james!!)
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
            // Saving an empty notes.XML file.
            val storingNotes = TableAPI(XMLSerializer(File("Table.xml")))
            storingNotes.store()

            //Loading the empty notes.xml file into a new object
            val loadedNotes = TableAPI(XMLSerializer(File("Table.xml")))
            loadedNotes.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            Assertions.assertEquals(0, storingNotes.listnmbtables())
            Assertions.assertEquals(0, loadedNotes.listnmbtables())
            assertEquals(storingNotes.listnmbtables(), loadedNotes.listnmbtables())
        }

        @Test
        fun `saving and loading an loaded collection in XML doesn't loose data`() {
            // Storing 3 notes to the notes.XML file.
            val storingNotes = TableAPI(XMLSerializer(File("Table.xml")))
//            storingNotes.add(testApp!!)
//            storingNotes.add(swim!!)
//            storingNotes.add(summerHoliday!!)
            storingNotes.store()

            //Loading notes.xml into a different collection
            val loadedNotes = TableAPI(XMLSerializer(File("Table.xml")))
            loadedNotes.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            Assertions.assertEquals(3, storingNotes.listnmbtables())
            Assertions.assertEquals(3, loadedNotes.listnmbtables())
            assertEquals(storingNotes.listnmbtables(), loadedNotes.listnmbtables())
//            assertEquals(storingNotes.findNote(0), loadedNotes.findNote(0))
//            assertEquals(storingNotes.findNote(1), loadedNotes.findNote(1))
//            assertEquals(storingNotes.findNote(2), loadedNotes.findNote(2))
        }
    }
}
