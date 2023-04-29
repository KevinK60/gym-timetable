package Controllers
import Models.Table
import kotlin.collections.ArrayList

class TableAPI {
    private var users = ArrayList<Table>()
    fun add(index: Table): Boolean {
        return users.add(index)
    }

    fun listAllTable(): String =
        if (users.isEmpty()) "No users found"
        else users.joinToString(separator = "\n") { Table ->
            users.indexOf(Table).toString() + ": " + Table.toString()
        }

    fun listnmbtables(): Int {
        return users.size
    }
    fun isValidTableIndex(index: Int, users: List<Any>): Boolean {
        return (index >= 0 && index < users.size)
    }
    fun findTable(index: Int): Table? {
        return if (isValidTableIndex(index, users)) {
            users[index]
        } else null
    }
    fun numberinactiveday(day: String): Int {
        return users.stream()
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
}


