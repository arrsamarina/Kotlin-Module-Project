import java.util.*
class NotesMenu(private val archiveMenu: ArchiveMenu) {

    private val scanner = Scanner(System.`in`)
    private val menuLogic = MenuLogic()
    private var currentArchive: Archive? = null
    fun showNotesMenu(archive: Archive){
        currentArchive = archive
        menuLogic.clearItems()
        println("****************")
        println("МЕНЮ ЗАМЕТОК - Архив: ${archive.name}")
        menuLogic.addItem("Вернуться к выбору архива", ::returnToArchiveMenu)
        menuLogic.addItem("Создать заметку", ::createNote)

        if (currentArchive != null && currentArchive!!.notes.isNotEmpty()) {
            for (note in currentArchive!!.notes) {
                menuLogic.addItem("Открыть заметку: ${note.title}"){
                    openNote(note)
                }
            }
        }
        menuLogic.displayMenu()
        val userInput = menuLogic.getUserChoice(isMainMenu = false)
        menuLogic.executeAction(userInput)
    }
    private fun returnToArchiveMenu() {
        archiveMenu.showArchiveMenu()
    }
    private fun createNote() {
        println("Для создания новой заметки введите её название. Для возврата к меню заметок введите 0")
        val noteName = scanner.nextLine()
        when {
            noteName.isBlank() -> {
                println("Название заметки не может быть пустым. Пожалуйста, введите корректное название.")
                createNote()
            }
            noteName == "0" -> showNotesMenu(currentArchive!!)
            else -> {
                println("Введите содержание заметки. Для возврата к меню заметок введите цифру 0 (заметка будет удалена)")
                val noteBody: String = scanner.nextLine()

                when {
                    noteBody == "0" -> {
                        println("Заметка удалена")
                        showNotesMenu(currentArchive!!)
                    }
                    noteBody.isBlank() -> {
                        println("Содержание заметки не может быть пустым. Пожалуйста, введите корректное содержание.")
                        createNote()
                    }
                    else -> {
                        val newNote = Notes(noteName, noteBody)
                        currentArchive!!.notes.add(newNote)
                        println("Заметка успешно создана")
                        showNotesMenu(currentArchive!!)
                    }
                }
            }
        }
    }
    private fun openNote(note: Notes){
        println("Открываю заметку...")
        println("Заголовок : ${note.title}")
        println("Содержание: ${note.content}")
        showNotesMenu(currentArchive!!)
    }

}