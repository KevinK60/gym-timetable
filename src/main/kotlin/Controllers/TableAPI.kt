package Controllers
import Models.Table
import Models.User
import persistence.Serializer

import kotlin.collections.ArrayList

/**

This class provides an API for managing tables and users using a serializer.

@property serializer An instance of the Serializer class to read and write data to disk.

@property tables A list of all tables in the system.

@property users A list of all users in the system.
 */
class TableAPI(serializerType: Serializer) {

    private var serializer: Serializer = serializerType
    private var tables = ArrayList<Table>()
    private var users = ArrayList<User>()

    /**

    Load tables and users from disk.
    @throws Exception if an error occurs while reading data from disk.
     */
    @Throws(Exception::class)
    fun load() {
        tables = serializer.read() as ArrayList<Table>
        users = serializer.read() as ArrayList<User>
    }
    /**

    Save tables and users to disk.
    @throws Exception if an error occurs while writing data to disk.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(tables)
        serializer.write(users)
    }
    /**

    Add a user to the system.
    @param user The user to add.
    @return true if the user was added successfully, false otherwise.
     */
    fun addUser(user: User): Boolean {
        return users.add(user)
    }
    /**

    Add a table to the system.
    @param index The table to add.
    @return true if the table was added successfully, false otherwise.
     */
    fun addTable(index: Table): Boolean {
        return tables.add(index)
    }
    /**

    Get a string representation of all users in the system.
    @return A string representation of all users in the system, or "No users found" if there are no users.
     */
    fun listAllUsers(): String =
        if (users.isEmpty()) "No users found"
        else users.joinToString(separator = "\n") { User ->
            users.indexOf(User).toString() + ": " + User.toString()
        }
    /**

    Get the total number of users in the system.
    @return The total number of users in the system.
     */
    fun getTotalUsers(): Int {
        return users.size
    }
    /**

    Get a string representation of all users in the system who do not have a timetable assigned to them.
    @return A string representation of all users who do not have a timetable assigned to them, or "No users found" if there are none.
     */
    fun noTables(): String =
        if (users.isEmpty()) "No users found"
        else users.filter { it.timetable == null }
            .joinToString(separator = "\n") { user ->
                users.indexOf(user).toString() + ": " + user.toString()
            }
    /**

    Search for a user by name.
    @param searchString The name to search for.
    @return A string representation of all users whose name contains the search string, or an empty string if there are none.
     */
    fun searchByName(searchString: String) =
        users.filter { user -> user.name.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") { user -> users.indexOf(user).toString() + ": " + user.toString() }
    /**

    Search for a user by email.
    @param searchString The email to search for.
    @return A string representation of all users whose email contains the search string, or an empty string if there are none.
     */
    /**
     * Returns a user with the given index, if it is a valid index.
     * @param index The index of the user to return.
     * @return The user at the given index, or null if the index is invalid.
     */
    fun findUser(index: Int): User? {
        return if (isValidUserIndex(index, users)) {
            users[index]
        } else null
    }

    /**
     * Prints the string representation of each user's timetable in the users list.
     */
    fun listAllTables() {
        for (user in users) {
            println(user.timetable?.toString())
        }
    }

    /**
     * Checks if the given index is valid for the given list.
     * @param index The index to check.
     * @param users The list to check the index against.
     * @return True if the index is valid, false otherwise.
     */
    fun isValidUserIndex(index: Int, users: List<Any>): Boolean {
        return (index >= 0 && index < users.size)
    }

    /**
     * Returns the number of tables in the tables list.
     * @return The number of tables in the list.
     */
    fun listnmbtables(): Int {
        return tables.size
    }
    fun searchByEmail(searchString: String) =
        users.filter { user -> user.email.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") { user -> users.indexOf(user).toString() + ": " + user.toString() }
    /**
     * Checks if the given index is valid for the given list.
     * @param index The index to check.
     * @param users The list to check the index against.
     * @return True if the index is valid, false otherwise.
     */
    fun isValidTableIndex(index: Int, users: List<Any>): Boolean {
        return (index >= 0 && index < users.size)
    }

    /**
     * Returns the number of users who have the given day as an active day in their timetable.
     * @param day The day to check for.
     * @return The number of users with the given day as an active day.
     */
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

    /**
     * Returns the number of users who have the given day as an inactive day in their timetable.
     * @param day The day to check for.
     * @return The number of users with the given day as an inactive day.
     */



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

    /**
     * Updates the timetable of the user with the specified ID.
     * @param userId The ID of the user to update.
     * @param updateTable The new timetable to assign to the user.
     */

    fun updateTable(userId: Int, updateTable: Table) {
        val user = findUser(userId)
        user?.let { it.timetable = updateTable }
    }
    /**
     * Finds a table by index.
     * @param index The index of the table to find.
     * @return The table at the specified index, or null if it doesn't exist.
     */
    fun findNote(index: Int): Table? {
        return if (isValidTableIndex(index, tables)) {
            tables[index]
        } else null
    }

}