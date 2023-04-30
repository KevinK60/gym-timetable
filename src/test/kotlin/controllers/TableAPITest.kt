package controllers

import Controllers.TableAPI
import Models.User
import Models.Table
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.*
import persistence.XMLSerializer
import java.io.File
import kotlin.test.assertFails

class TableAPITest {
    private var JoePerson1: Table? = null
    private var jamesG: Table? = null
    private var jamesU: User? = null
    private var populatedTables: TableAPI? = TableAPI(XMLSerializer(File("Tables.xml")))

    @BeforeEach
    fun setup() {
        JoePerson1 = Table("joe", false, true, true, true, true, true, true)
        jamesG = Table("james", false, false, false, false, false, false, false)
        jamesU = User("james", "james@gmail.com", "james111", jamesG)
        populatedTables!!.addUser(jamesU!!)

    }

    @AfterEach
    fun tearDown() {
        JoePerson1 = null
        jamesG = null
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
            storingTables.addTable(jamesG!!)

            storingTables.store()

            //Loading tables.xml into a different collection
            val loadedTables = TableAPI(XMLSerializer(File("tables.xml")))
            loadedTables.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedTables)
            Assertions.assertEquals(1, storingTables.listnmbtables())
            Assertions.assertEquals(0, loadedTables.listnmbtables())
//            assertEquals(storingTables.listnmbtables(), loadedTables.listnmbtables())
            assertEquals(storingTables.findNote(0), loadedTables.findNote(0))
            assertEquals(storingTables.findNote(1), loadedTables.findNote(1))
            assertEquals(storingTables.findNote(1), loadedTables.findNote(2))
        }
    }

    @Nested
    internal inner class Tests {
        @Test
        fun `adding a new user `() {
            val newTable = Table("tommy", false, false, false, false, false, false, false)
            populatedTables!!.addTable(newTable)
            assertEquals(1, populatedTables!!.listnmbtables())
        }
    }

    @Nested
    internal inner class getTotalUsers {
        @Test
        fun `getTotalUsers `() {

            assertEquals(1, populatedTables!!.getTotalUsers())
            val newTable = Table("tommy", false, false, false, false, false, false, false)
            val jamesD = User("tommy", "tommy@gmail.com", "tommy111", newTable)
            populatedTables!!.addUser(jamesD)
            assertEquals(2, populatedTables!!.getTotalUsers())
        }
    }

    @Nested
    internal inner class SearchBy {
        @Test
        fun `searchByName `() {

            val searchString = "james"
            val expected = "0: "
            assertEquals("0: ${jamesU}", populatedTables!!.searchByName(searchString))
        }

        @Test
        fun `searchByEmail `() {

            val searchString = "james@gmail.com"
            val expected = "0: "
            assertEquals("0: ${jamesU}", populatedTables!!.searchByEmail(searchString))
        }
    }

    @Nested
    internal inner class findUser {
        @Test
        fun `test findUser with valid index `() {
            assertEquals(jamesU, populatedTables!!.findUser(0))
        }

        @Test
        fun `test findUser with invalid index`() {
            assertEquals(null, populatedTables!!.findUser(2))
        }
    }

    @Nested
    internal inner class ListAllUsers {
        @Test
        fun `test listAllTables() `() {
            assertEquals("0: ${jamesU}", populatedTables!!.listAllUsers())
            val tommyG = Table("tommy", false, false, false, false, false, true, true)
            val tommyU = User("tommy", "james@gmail.com", "james111", tommyG)
            populatedTables!!.addUser(tommyU!!)
            assertEquals("0: ${jamesU}\n1: ${tommyU}", populatedTables!!.listAllUsers())
        }
    }

    @Nested
    internal inner class activedaymembers {
        @Test
        fun `test activedaymembers() `() {
            assertEquals(0, populatedTables!!.activedaymembers("monday"))
            assertEquals(0, populatedTables!!.activedaymembers("tuesday"))
            assertEquals(0, populatedTables!!.activedaymembers("wednesday"))
            assertEquals(0, populatedTables!!.activedaymembers("thursday"))
            assertEquals(0, populatedTables!!.activedaymembers("friday"))
            assertEquals(0, populatedTables!!.activedaymembers("saturday"))
            assertEquals(0, populatedTables!!.activedaymembers("sunday"))
            val tommyG = Table("tommy", false, false, false, false, false, true, true)
            val tommyU = User("tommy", "james@gmail.com", "james111", tommyG)
            populatedTables!!.addUser(tommyU!!)
            assertEquals(1, populatedTables!!.activedaymembers("saturday"))
            assertEquals(1, populatedTables!!.activedaymembers("sunday"))


        }

        @Test
        fun `test inactivedaymemberss() `() {
            assertEquals(1, populatedTables!!.inactivedaymemberss("monday"))
            assertEquals(1, populatedTables!!.inactivedaymemberss("tuesday"))
            assertEquals(1, populatedTables!!.inactivedaymemberss("wednesday"))
            assertEquals(1, populatedTables!!.inactivedaymemberss("thursday"))
            assertEquals(1, populatedTables!!.inactivedaymemberss("friday"))
            assertEquals(1, populatedTables!!.inactivedaymemberss("saturday"))
            assertEquals(1, populatedTables!!.inactivedaymemberss("sunday"))
            val tommyG = Table("tommy", false, false, false, false, false, false, false)
            val tommyU = User("tommy", "james@gmail.com", "james111", tommyG)
            populatedTables!!.addUser(tommyU!!)
            assertEquals(2, populatedTables!!.inactivedaymemberss("saturday"))
            assertEquals(2, populatedTables!!.inactivedaymemberss("sunday"))

        }


    }

    @Nested
    internal inner class finderuserss {
        @Test
        fun `test finderuserss() `() {
            assertEquals(jamesU, populatedTables!!.findUser(0))
        }
    }

    @Nested
    internal inner class findtable {
        @Test
        fun `test findTable() `() {
            assertEquals(jamesU, populatedTables!!.findUser(0))
            assertEquals(null, populatedTables!!.findUser(10))
        }
    }
}