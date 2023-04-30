package Controllers
import Models.Table
import Models.User
import persistence.Serializer

import kotlin.collections.ArrayList
class TableAPI(serializerType: Serializer) { /**
     The serializer used for storing and loading data. The serializer type is determined by the serializerType parameter
     passed to the SwimmerAPI constructor.
     */
    private var serializer: Serializer = serializerType
    /**
     *
     Loads the swimmer data from the serialized data using the currently assigned serializer. The loaded data is
     then used to populate the tables and users lists.
     @throws Exception If there's an error reading or deserializing the data.
     */
    @Throws(Exception::class)
    fun load() {
        tables = serializer.read() as ArrayList<Table>
        users = serializer.read() as ArrayList<User>
    }
    /**
     Writes the swimmer data to a serialized format using the currently assigned serializer. The data to be written is
     obtained from the tables and users lists.
     @throws Exception If there's an error writing or serializing the data.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(tables)
        serializer.write(users)
    }
    /**
     ** A list of Table objects representing the swimmer data. The list is initially empty.
     */
    private var tables = ArrayList<Table>()
    /**
     A list of User objects representing the user data. The list is initially empty.
     */
    private var users = ArrayList<User>()
    /**
     Adds a new User object to the users list.
     @param user The User object to be added.
     @return true if the user was successfully added, false otherwise.
     */
    fun addUser(user: User): Boolean {
        return users.add(user)
    }
    /**

     Adds a new Table object to the tables list.
     @param table The Table object to be added.
     @return true if the table was successfully added, false otherwise.
     */
    fun addTable(index: Table): Boolean {
        return tables.add(index)
    }
    /**

     Returns a string representation of all User objects in the users list.
     If the users list is empty, the string "No users found" is returned.
     Otherwise, the string contains one line for each User object in the users list,
     in the format "index: User.toString()".
     @return A string representation of all User objects in the users list.
     */
    fun listAllUsers(): String =
        if (users.isEmpty()) "No users found"
        else users.joinToString(separator = "\n") { User ->
            users.indexOf(User).toString() + ": " + User.toString()
        }
    /**
     Returns the total number of User objects in the users list.
     @return The total number of User objects in the users list.
     */

    fun getTotalUsers(): Int {
        return users.size
    }

    /**

     Returns a string representation of all User objects in the users list that do not have
     a Timetable object assigned to them.
     If there are no such User objects in the users list, the string "No users found" is returned.
     Otherwise, the string contains one line for each User object in the users list that
     does not have a Timetable object assigned to it, in the format "index: User.toString()".
     @return A string representation of all User objects in the users list that do not have

     */
    fun noTables(): String =
        if (users.isEmpty()) "No users found"
        else users.filter { it.timetable == null }
            .joinToString(separator = "\n") { user ->
                users.indexOf(user).toString() + ": " + user.toString()
            }

    /**
     Returns a string representation of all User objects in the users list whose name
     field contains the specified search string, ignoring case.
     The search is case-insensitive and matches any User object whose name field contains
     the specified search string as a substring.
     The returned string contains one line for each User object in the users list that
     matches the search criteria, in the format "index: User.toString()".
     @param searchString The string to search for in the name field of User objects.
     @return A string representation of all User objects in the users list whose name
     */
    fun searchByName(searchString: String) =
        users.filter { user -> user.name.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") { user -> users.indexOf(user).toString() + ": " + user.toString() }
    /**

     Returns a string representation of all User objects in the users list whose email
     field contains the specified search string, ignoring case.
     The search is case-insensitive and matches any User object whose email field contains
     the specified search string as a substring.
     The returned string contains one line for each User object in the users list that
     matches the search criteria, in the format "index: User.toString()".
     @param searchString The string to search for in the email field of User objects.
     @return A string representation of all User objects in the users list whose email

     */
    fun searchByEmail(searchString: String) =
        users.filter { user -> user.email.contains(searchString, ignoreCase = true) }
            .joinToString(separator = "\n") { user -> users.indexOf(user).toString() + ": " + user.toString() }

    /**

     Returns the User object at the specified index in the users list, or null if the
     index is out of range or the users list is empty.
     @param index The index of the User object to retrieve.
     @return The User object at the specified index in the users list, or null if the
     */
    fun findUser(index: Int): User? {
        return if (isValidUserIndex(index, users)) {
            users[index]
        } else null
    }
    /**
     Prints a string representation of the timetable of each User object in the users list.
     If a User object has a non-null timetable field, the function prints the string
     representation of the timetable field using the toString() method.
     If a User object has a null timetable field, the function does nothing for that user.
     Note: This function does not return a string; it only prints the timetables to the console.
     */
    fun listAllTables() {
        for (user in users) {
            println(user.timetable?.toString())
        }
    }
     /**
    Determines whether the specified index is a valid index for the users list.
    An index is valid if it is greater than or equal to 0 and less than the size of the users
    list.
     @param index The index to check.
    @param users The list to check against.
    @return true if the index is valid for the list; false otherwise.
     */
    fun isValidUserIndex(index: Int, users: List<Any>): Boolean {
       return (index >= 0 && index < users.size)

    }

    /**

    Returns the number of tables currently stored in the tables list.
    @return The number of tables in the tables list.
     */
        fun listnmbtables(): Int {
        return tables.size
    }
    /**

    Determines whether the specified index is a valid index for the tables list.
    An index is valid if it is greater than or equal to 0 and less than the size of the tables
    list.
    @param index The index to check.
    @param tables The list to check against.
    @return true if the index is valid for the list; false otherwise.
     */
    fun isValidTableIndex(index: Int, users: List<Any>): Boolean {
        return (index >= 0 && index < users.size)
    }

    /**

    Returns the number of users who have the specified day marked as active in their timetable.
    @param day The day to search for (e.g. "monday", "tuesday", etc.).
    @return The number of users with the specified day marked as active in their timetable.
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
     * Returns the number of inactive members on the specified day.
     *
     * @param day the day of the week (e.g. "monday", "tuesday", etc.)
     * @return the number of inactive members on the specified day
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

    fun getTable(tableName: String?): Table? {
        return tables.find { it.name == tableName }
    }
    fun getUser(user: User): User? {
        return users.find { it.name == user.name }
    }
    /**
     Returns the User object at the specified index in the list of users.
     Returns null if the index is invalid.
     @param index the index of the desired user
     @return the User object at the specified index, or null if the index is invalid
     */
    fun updateTable(userId: Int, updateTable: Table) {
        // Find the user with the matching user ID
        val user = findUser(userId)

        // Update the table if the user exists
        user?.let { it.timetable = updateTable }
    }

    fun findNote(index: Int): Table? {
        return if (isValidTableIndex(index, tables)) {
            tables[index]
        } else null
    }

    }