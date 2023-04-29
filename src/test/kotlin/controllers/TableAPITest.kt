package controllers
import Controllers.TableAPI
import org.junit.jupiter.api.BeforeEach
import Models.Table
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TableAPITest {
    private var JoePerson1: Table? = null
    private var james: Table? = null
    private var populatedTables: TableAPI? = TableAPI()

    @BeforeEach
    fun setup() {
        JoePerson1 = Table("joe",false,true,true,true,true,true,true)
        james=Table("james",false,false,false,false,false,false,false)
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
        fun `adding a new user `(){
            val newTable =  Table("tommy",false,false,false,false,false,false,false)


        }

    }
}
