import java.util.*
class ArchiveMenu() {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)
    private val menuLogic = MenuLogic()
    private val notesMenu = NotesMenu(this)
    private val mainMenu = MainMenu(this)
    fun showArchiveMenu() {
        menuLogic.clearItems()
        println("****************")
        println("МЕНЮ АРХИВОВ")
        menuLogic.addItem("Вернуться в главное меню", ::returnToMainMenu)
        menuLogic.addItem("Создать архив", ::createArchive)
        var userInput: Int
        if (archives.isEmpty()) {
            menuLogic.displayMenu()
            userInput = menuLogic.getUserChoice(isMainMenu = false)
            menuLogic.executeAction(userInput)
        } else {
            for (archive in archives) {
                menuLogic.addItem("Открыть архив: ${archive.name}"){
                    openArchive(archive)
                }
            }
            menuLogic.displayMenu()
            userInput = menuLogic.getUserChoice(isMainMenu = false)
            menuLogic.executeAction(userInput)
        }
    }
    private fun returnToMainMenu(){
        mainMenu.start()
    }
    private fun createArchive(){
        println("Для создания нового архива введите его название. Для возврата к меню архивов введите 0")
        val archiveName = scanner.nextLine()
        if (archiveName.isBlank()) {
            println("Название архива не может быть пустым. Пожалуйста, введите корректное название.")
            createArchive()
        } else if (archiveName == "0") {
            showArchiveMenu()
        } else {
            val archive = Archive(archiveName)
            archives.add(archive)
            println("Архив \"$archiveName\" успешно создан")
            showArchiveMenu()
        }
    }
    private fun openArchive(archive: Archive) {
        notesMenu.showNotesMenu(archive)
    }
}
