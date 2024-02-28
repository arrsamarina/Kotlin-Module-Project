class MainMenu(private val archiveMenu: ArchiveMenu) {
    private val menuLogic = MenuLogic()
    fun sayHello() {
        println("Приложение Заметки запущено!")
    }
    fun start() {
        menuLogic.clearItems()
        println("------------------")
        println("ГЛАВНОЕ МЕНЮ")
        menuLogic.addItem("Открыть архив или создать новый", ::goToArchiveMenu)
        var userInput: Int
        do {
            menuLogic.displayMainMenu()
            userInput = menuLogic.getUserChoice(isMainMenu = true)

            if (!menuLogic.isChoiceValid(userInput)) {
                println("Некорректный выбор. Пожалуйста, выберите существующий пункт меню.")
            } else {
                menuLogic.executeAction(userInput)
            }
        } while (userInput != 0)
        println("Программа завершена.")
    }
    private fun goToArchiveMenu() {
        archiveMenu.showArchiveMenu()
    }
}
