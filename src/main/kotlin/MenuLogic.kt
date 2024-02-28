import java.util.*
class MenuLogic {
    private val items =
            mutableListOf<Pair<String, () -> Unit>>()
    fun addItem(name: String, action: () -> Unit) {
        if (name.isNotBlank()) {
            items.add(Pair(name, action))
        } else {
            println("Ошибка: Нельзя добавить пункт меню с пустым именем.")
        }
    }
    fun clearItems() {
        items.clear()
    }
    fun displayMainMenu() {
        println("Меню:")
        items.forEachIndexed { index, (name, _) ->
            println("${index + 1}. $name")
        }
        println("0. Выйти")
    }
    fun displayMenu() {
        println("Меню:")
        items.forEachIndexed { index, (name, _) ->
            println("${index + 1}. $name")
        }
    }
    fun getUserChoice(isMainMenu: Boolean): Int {
        var userInput: Int? = null
        val scanner = Scanner(System.`in`)
        while (userInput == null || !isChoiceValid(userInput)) {
            print("Выберите пункт меню: ")
            try {
                userInput = scanner.nextInt()

                if (!isMainMenu && userInput == 0) {
                    println("Некорректный ввод. Попробуйте снова.")
                    userInput = null
                } else if (!isChoiceValid(userInput)) {
                    println("Некорректный выбор. Пожалуйста, выберите существующий пункт меню.")
                    userInput = null
                }

            } catch (e: InputMismatchException) {
                println("Некорректный ввод. Попробуйте снова.")
                scanner.next()
            }
        }
        return userInput
    }
    fun executeAction(choice: Int) {
        if (choice in 0..items.size) {
            if (choice == 0) {
                println("Выход из программы.")
                System.exit(0)
            } else {
                val action = items[choice - 1].second
                action.invoke()
            }
        }
    }
    fun isChoiceValid(choice: Int): Boolean {
        return choice in 0..items.size
    }

}