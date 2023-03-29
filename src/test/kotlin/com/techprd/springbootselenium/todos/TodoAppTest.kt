package com.techprd.springbootselenium.todos

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.techprd.springbootselenium.pages.TodoAppPage
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TodoAppTest(todoPage: TodoAppPage) : BehaviorSpec() {

    init {

        beforeContainer {
            todoPage.open()
        }

        given("I have opened the todo app") {
            then("I should see the todo input") {
                todoPage.h1Todos.shouldBe(visible)
            }

            When("I input a new todo task") {

                val task = "buy milk"
                todoPage.addTodoTask(task)

                then("I should see the added task") {
                    val taskElement = todoPage.todoList.findAll("li").first()
                    taskElement.shouldBe(visible)
                    taskElement.shouldHave(text(task))
                }
            }
        }
    }
}
