package Models

data class Table(
    var name: String,
    var monday: Boolean,
    var tuesday: Boolean,
    var wednesday: Boolean,
    var thursday: Boolean,
    var friday: Boolean,
    var saturday: Boolean,
    var sunday: Boolean
)
{
override fun toString(): String {
    val green = "\u001B[32m"
    val reset = "\u001B[0m"
    val red = "\u001b[31m"

    return """
            $name'
            ${if (monday) "$green Monday $red" else "Monday"}
            ${if (tuesday) "$green Tuesday $red" else "Tuesday"}
            ${if (wednesday) "$green Wednesday $red" else "Wednesday"}
            ${if (thursday) "$green Thursday $red" else "Thursday"}
            ${if (friday) "$green Friday $red" else "Friday"}
            ${if (saturday) "$green Saturday $red" else "Saturday"}
            ${if (sunday) "$green Sunday $red" else "Sunday"}${reset}"""
}
}