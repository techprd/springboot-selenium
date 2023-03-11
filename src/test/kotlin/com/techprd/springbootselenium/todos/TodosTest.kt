package com.techprd.springbootselenium.todos

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.SelenideLogger
import com.techprd.springbootselenium.pages.MainPage
import io.kotest.core.spec.style.BehaviorSpec
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TodosTest : BehaviorSpec() {

    @Autowired
    lateinit var mainPage: MainPage

    init {

        beforeAny {
            Configuration.browserSize = "1280x800"
            SelenideLogger.addListener("allure", AllureSelenide())
            Selenide.open("https://www.jetbrains.com/")
        }

        Given("I want to test main page") {

            When("I search for Selenium term") {
                mainPage.searchButton.click()

                Selenide.element("[data-test='search-input']").sendKeys("Selenium")
                Selenide.element("button[data-test='full-search-button']").click()

                Then("search input should exists") {
                    Selenide.element("input[data-test='search-input']").shouldHave(Condition.attribute("value", "Selenium"))
                }
            }

            When("toolsMenu") {
                mainPage.toolsMenu.click()

                Selenide.element("div[data-test='main-submenu']").shouldBe(Condition.visible)
            }

            When("navigationToAllTools") {
                mainPage.seeDeveloperToolsButton.click()
                mainPage.findYourToolsButton.click()

                Selenide.element("#products-page").shouldBe(Condition.visible)

                Assertions.assertEquals("All Developer Tools and Products by JetBrains", Selenide.title())
            }
        }
    }

}
