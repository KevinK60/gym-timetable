import Controllers.TableAPI
import Models.Table
import utils.ScannerInput
import utils.ScannerInput.readNextLine

fun main() = runMenu()

fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> createUser()
            2 -> listAllUsers()
            3 -> ListNmbUsers()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun ListNmbUsers() {
    if (TableAPI.numberOfTables() == 0) {
        println("there is noone registered in the gym")
    } else
        println(
            """there is currently ${TableAPI.numberOfTables()} users registered in the gym
        
    """.trimMargin()
        )
}

fun listAllUsers() {
    println(TableAPI.listAllTable())
}

private val TableAPI = TableAPI()
fun createUser() {
    val name = readNextLine("Enter a name for your gym schedule: ")
    val monday = readNextLine("Is Monday a workout day for you? (y/n)") == "y"
    val tuesday = readNextLine("Is Tuesday a workout day for you? (y/n)") == "y"
    val wednesday = readNextLine("Is Wednesday a workout day for you? (y/n)") == "y"
    val thursday = readNextLine("Is Thursday a workout day for you? (y/n)") == "y"
    val friday = readNextLine("Is Friday a workout day for you? (y/n)") == "y"
    val saturday = readNextLine("Is Saturday a workout day for you? (y/n)") == "y"
    val sunday = readNextLine("Is Sunday a workout day for you? (y/n)") == "y"

    val gymSchedule1 = Table(name, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
    TableAPI.add(gymSchedule1)
    println(gymSchedule1)
}

fun mainMenu() = ScannerInput.readNextInt(
    """ 
         >━━━━━Main Menu━━━━━━━━  
         >│ 1. Add user        │
         >│ 2. List All users  │
         >│ 3. Get Total Users │
         >│                   │
         >│━━━━━━━━━━━━━━━━━━━━│
         >your option      
         """.trimMargin("   >")
)
