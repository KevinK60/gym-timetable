package Controllers
import Models.Table
import Models.User
import persistence.Serializer
import kotlin.collections.ArrayList

class TableAPI(serializerType: Serializer) {

    private var serializer: Serializer = serializerType
    @Throws(Exception::class)
    fun load() {
        tables = serializer.read() as ArrayList<Table>


    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(tables)

    }

    private var tables = ArrayList<Table>()
    fun add(index: Table): Boolean {
        return tables.add(index)
    }

    fun listAllTable(): String =
        if (tables.isEmpty()) "No users found"
        else tables.joinToString(separator = "\n") { Table ->
            tables.indexOf(Table).toString() + ": " + Table.toString()
        }

    fun listnmbtables(): Int {
        return tables.size
    }
    fun isValidTableIndex(index: Int, users: List<Any>): Boolean {
        return (index >= 0 && index < users.size)
    }
    fun findTable(index: Int): Table? {
        return if (isValidTableIndex(index, tables)) {
            tables[index]
        } else null
    }
    fun numberinactiveday(day: String): Int {
        return tables.stream()
            .filter { user: Table ->
                when (day) {
                    "monday" -> user.monday
                    "tuesday" -> user.tuesday
                    "wednesday" -> user.wednesday
                    "thursday" -> user.thursday
                    "friday" -> user.friday
                    "saturday" -> user.saturday
                    "sunday" -> user.sunday
                    else -> false
                }
            }
            .count()
            .toInt()
    }

    fun numberhowinactiveday(day: String): Int {
        return tables.stream()
            .filter { user: Table ->
                when (day) {
                    "monday" -> !user.monday
                    "tuesday" -> !user.tuesday
                    "wednesday" -> !user.wednesday
                    "thursday" -> !user.thursday
                    "friday" -> !user.friday
                    "saturday" -> !user.saturday
                    "sunday" -> !user.sunday
                    else -> false
                }
            }
            .count()
            .toInt()
    }
}


