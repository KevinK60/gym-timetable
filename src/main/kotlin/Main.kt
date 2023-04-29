import Controllers.TableAPI
import Models.Table
import Models.User
import utils.ScannerInput
import utils.ScannerInput.readNextLine

fun main() = runMenu()

fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> createUser()
            2 -> listAllUsers()
            3 -> ListNmbUsers()
            4 -> findUser()
            5 -> CountPplDay()
            6 -> countinactivepplday()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun CountPplDay() {


    val day = readNextLine("Enter the day which you will see how many people are going ")
    println(
        """
         
     there is currently ${TableAPI.numberinactiveday(day)} going on $day
         
         """.trimIndent()
    )
}

fun countinactivepplday() {
    val day = readNextLine("Enter the day which you will see how many people are not going ")
    println(
        """
         
     there is currently ${TableAPI.numberinactiveday(day)} going on $day
         
         """.trimIndent()
    )
}

fun findUser() {

}

fun ListNmbUsers() {
    if (TableAPI.listnmbtables() == 0) {
        println("there is noone registered in the gym")
    } else
        println(
            """there is currently ${TableAPI.listnmbtables()} users registered in the gym
        
    """.trimMargin()
        )
}

fun listAllUsers() {
    println(TableAPI.listAllTable())
}

private val TableAPI = TableAPI()


fun createUser() {
    val name = readNextLine("Enter your name: ")
    val email = readNextLine("Enter your email: ")
    val fullName = readNextLine("Enter your full name: ")
    val user = User(name, email, fullName, null)
    println("User created: $user")
    println("Would you like to add a timetable? (y/n)")
    var answer = readNextLine("Enter your answer: ")
    if (answer == "y") {
        val timetableName = readNextLine("Enter a name for your gym schedule: ")
        val monday = readNextLine("Is Monday a workout day for you? (y/n)") == "y"
        val tuesday = readNextLine("Is Tuesday a workout day for you? (y/n)") == "y"
        val wednesday = readNextLine("Is Wednesday a workout day for you? (y/n)") == "y"
        val thursday = readNextLine("Is Thursday a workout day for you? (y/n)") == "y"
        val friday = readNextLine("Is Friday a workout day for you? (y/n)") == "y"
        val saturday = readNextLine("Is Saturday a workout day for you? (y/n)") == "y"
        val sunday = readNextLine("Is Sunday a workout day for you? (y/n)") == "y"

        val gymSchedule = Table(timetableName, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
        TableAPI.add(gymSchedule)
        user.timetable = gymSchedule
        println(user)
        println("Gym schedule added: $gymSchedule")
    } else {
        println("No gym schedule added.")
    }
}

fun mainMenu() = ScannerInput.readNextInt(
    """ 
         >━━━━━Main Menu━━━━━━━━  
         >│ 1. Add user        │
         >│ 2. List All users  │
         >│ 3. Get Total Users │
         >│ 4. Find User       │
            5. count active people on day 
         >│━━━━━━━━━━━━━━━━━━━━│
         >your option      
         """.trimMargin("   >")
)
