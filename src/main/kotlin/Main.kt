import Models.Table
import utils.ScannerInput

import utils.ScannerInput.readNextLine
fun main() = runMenu()

fun runMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> createUser()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun createUser() {
    val name = readNextLine("Enter a name for your gym schedule: ")
    val monday = readNextLine("Is Monday a workout day for you? (y/n)").equals("y")
    val tuesday = readNextLine("Is Tuesday a workout day for you? (y/n)").equals("y")
    val wednesday = readNextLine("Is Wednesday a workout day for you? (y/n)").equals("y")
    val thursday = readNextLine("Is Thursday a workout day for you? (y/n)").equals("y")
    val friday = readNextLine("Is Friday a workout day for you? (y/n)").equals("y")
    val saturday = readNextLine("Is Saturday a workout day for you? (y/n)").equals("y")
    val sunday = readNextLine("Is Sunday a workout day for you? (y/n)").equals("y")

    val gymSchedule = Table(name, monday, tuesday, wednesday, thursday, friday, saturday, sunday)
    println("""
        ${gymSchedule}
    """.trimIndent())
}

fun mainMenu() = ScannerInput.readNextInt(
    """ 
         > -----------------------------------------------------  
         >  Add user 
         > 
         > 
         > 
         > 
         > 
         > 
         > 
         > 
         > 
         > 
         """.trimMargin(">")
)
