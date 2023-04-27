import utils.ScannerInput

fun main() = runMenu()

fun runMenu() {
    do {
        when (val option = mainMenu()) {

            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}
fun mainMenu() = ScannerInput.readNextInt(
    """ 
         > -----------------------------------------------------  
         > 1 Add a table
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
         """.trimMargin(">"))


