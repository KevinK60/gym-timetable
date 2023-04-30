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

    // Get total users // tested
    fun getTotalUsers(): Int {
        return users.size
    }

    // not tested
    fun noTables(): String =
        if (users.isEmpty()) "No users found"
        else users.filter { it.timetable == null }
            .joinToString(separator = "\n") { user ->
                users.indexOf(user).toString() + ": " + user.toString()
            }



    // Search by Users name // tested
    fun searchByName(searchString: String) =
        users.filter { user -> user.name.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") { user -> users.indexOf(user).toString() + ": " + user.toString() }
  // Search by Users email // tested
    fun searchByEmail(searchString: String) =
        users.filter { user -> user.email.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") { user -> users.indexOf(user).toString() + ": " + user.toString() }

    // find user by index // tested
fun findUser(index: Int): User? {
        return if (isValidUserIndex(index, users)) {
            users[index]
        } else null
    }
    // List all tables tested
    fun listAllTables() {
        for (user in users) {
            println(user.timetable?.toString())
        }
    }
    fun isValidUserIndex(index: Int, users: List<Any>): Boolean {
       return (index >= 0 && index < users.size)

    }





// tested
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

    fun activedaymembers(day: String): Int {
        return users.stream()
            .filter { user: User ->
                when (day) {
                    "monday" -> user.timetable?.monday == true
                    "tuesday" -> user.timetable?.tuesday == true
                    "wednesday" -> user.timetable?.wednesday == true
                    "thursday" -> user.timetable?.thursday == true
                    "friday" -> user.timetable?.friday == true
                    "saturday" -> user.timetable?.saturday == true
                    "sunday" -> user.timetable?.sunday == true
                    else -> false
                }
            }
            .count()
            .toInt()
    }



    fun inactivedaymemberss(day: String): Int {
        return users.stream()
            .filter { user: User ->
                when (day) {
                    "monday" -> user.timetable?.monday == false
                    "tuesday" -> user.timetable?.tuesday == false
                    "wednesday" -> user.timetable?.wednesday == false
                    "thursday" -> user.timetable?.thursday == false
                    "friday" -> user.timetable?.friday == false
                    "saturday" -> user.timetable?.saturday == false
                    "sunday" -> user.timetable?.sunday == false
                    else -> false
                }
            }
            .count()
            .toInt()
    }
// tested
    fun findNote(index: Int): Table? {
        return if (isValidTableIndex(index, tables)) {
            tables[index]
        } else null
    }

    fun addTabletoUser(index: Int, table: Table): Boolean {
        return if (isValidUserIndex(index, users)) {
            users[index].timetable = table
            true
        } else false
    }

}


