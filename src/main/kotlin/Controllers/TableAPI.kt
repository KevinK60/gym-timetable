package Controllers
import Models.Table


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
    fun numberOfTables(): Int {
        return users.size
    }



}

