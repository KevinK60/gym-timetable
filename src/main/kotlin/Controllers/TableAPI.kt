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
        users = serializer.read() as ArrayList<User>

    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(tables)
        serializer.write(users)

    }

    private var tables = ArrayList<Table>()
    private var users = ArrayList<User>()


    // Adds a user
    fun addUser(user: User): Boolean {
        return users.add(user)
    }
    // adds a table
    fun addTable(index: Table): Boolean {
        return tables.add(index)
    }
//    List all users

    fun listAllUsers(): String =
    if (users.isEmpty()) "No users found"
    else users.joinToString(separator = "\n") { User ->
        users.indexOf(User).toString() + ": " + User.toString()
    }
    // Get total users
    fun getTotalUsers(): Int {
        return users.size
    }

// Search by Users name
fun searchByName(searchString: String) =
    users.filter { user -> user.name.contains(searchString, ignoreCase = true) }
        .joinToString(separator = "\n") {
            user -> users.indexOf(user).toString() + ": " + user.toString() }
    fun searchByEmail(searchString: String) =
        users.filter { user -> user.email.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") {
                    user -> users.indexOf(user).toString() + ": " + user.toString() }

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

    fun findNote(index: Int): Table? {
        return if (isValidTableIndex(index, tables)) {
            tables[index]
        } else null
    }
}


