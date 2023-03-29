package com.techprd.springbootselenium.todos

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.techprd.springbootselenium.pages.TodoAppPage
import io.kotest.assertions.retry
import io.kotest.core.annotation.Tags
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeExactly
import org.springframework.boot.test.context.SpringBootTest
import kotlin.time.Duration.Companion.seconds

@SpringBootTest
@Tags("TodoApp")
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

                val task = "buy pizza sauce"

                retry(2, 30.seconds) {
                    todoPage.addTodoTask(task)
                }

                then("I should see the added task") {
                    val taskElement = todoPage.todoList.findAll("li").first()
                    taskElement.shouldBe(visible)
                    taskElement.shouldHave(text(task))
                }
            }

            When("I input a second todo task") {
                val task = "buy cheese"
                todoPage.addTodoTask(task)

                then("Todo counts has increase to 2") {
                    todoPage.todoCount.shouldHave(text("2 items left"))
                    todoPage.todoList.findAll("li").size shouldBeExactly 2
                }
            }
        }
    }
}
