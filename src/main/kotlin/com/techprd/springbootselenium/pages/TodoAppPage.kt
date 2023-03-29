package com.techprd.springbootselenium.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import org.springframework.stereotype.Component

// page_url = https://todomvc.com/examples/typescript-react/#/
@Component
class TodoAppPage {

    val h1Todos = element("h1")

    val todoInput = element("input[placeholder='What needs to be done?']")

    val todoList = element(".todo-list")

    val todoFooter = element("footer[class='footer']")

    val todoCount = element("span[class='todo-count']")

    val filters = element("ul[class='filters']")

    val filterAllSelect = element("ul[class='filters'] > li:nth-of-type(1)")

    val filterActiveSelect = element("ul[class='filters'] > li:nth-of-type(2)")

    val filterCompletedBtn = element("ul[class='filters'] > li:nth-of-type(3)")

    fun open() {
        Selenide.open("https://todomvc.com/examples/typescript-react/#/")
    }

    fun addTodoTask(task: String) {
        todoInput.clear()
        todoInput.`val`(task)
        todoInput.pressEnter()
    }

}
