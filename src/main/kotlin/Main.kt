fun main() {
    val archiveMenu = ArchiveMenu()
    val mainMenu = MainMenu(archiveMenu)
    mainMenu.sayHello()
    mainMenu.start()
}