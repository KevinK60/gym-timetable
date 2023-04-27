package Controllers
import Models.Table

class TableAPI{
    private var users = ArrayList<Table>()
    fun add(Table: Table): Boolean {
        return users.add(Table)
    }
    fun listAllTable(): String =
        if (users.isEmpty()) "No users found"
        else users.joinToString(separator = "\n") { note ->
            users.indexOf(note).toString() + ": " + note.toString()
        }
}
