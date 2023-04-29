package Models

data class User(
    var name: String,
    var email: String,
    var password: String,
    var timetable: Table?
) {
    override fun toString(): String {
        return """
        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                        User Details               
        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                      Name: $name
                     Email: $email
        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        $timetable
        """.trimIndent()
    }
}