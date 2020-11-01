Система событий

    Событие можно выкинуть в любом месте программы, для этого
    нужно прописать emit(YourEvent(param1, param2)), где
    YourEvent - класс, унаследованный от класса Event()
    

    Чтобы отловить событие, нужно в том месте,
    где вы собираетесь его отлавливать прописать
    register<YourEvent> {
        // и тут код
    }

    Пример:

    fun main() {
        register<ExampleEvent> {
            println("Id is ${it.id}")
            println("Red part of color: ${it.color.red}")
        }
        event_thrower()
    }

    fun event_thrower() {
        emit(ExampleEvent(123, Color.YELLOW))
    }