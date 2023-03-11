package com.techprd.springbootselenium.todos

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.SelenideLogger
import com.techprd.springbootselenium.pages.JetbrainsMainPage
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldBeEqualIgnoringCase
import io.qameta.allure.selenide.AllureSelenide
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JetbrainsPageTest(jetbrainsMainPage: JetbrainsMainPage) : BehaviorSpec() {

    init {

        beforeAny {
            Configuration.browserSize = "1280x800"
            SelenideLogger.addListener("allure", AllureSelenide())
            Selenide.open("https://www.jetbrains.com/")
        }

        Given("I want to test main page") {

            beforeEach {
                Selenide.open("https://www.jetbrains.com/")
            }

            When("I search for Selenium term") {
                jetbrainsMainPage.searchButton.click()

                Selenide.element("[data-test='search-input']").sendKeys("Selenium")
                Selenide.element("button[data-test='full-search-button']").click()
                Selenide.element("input[data-test='search-input']").shouldHave(Condition.attribute("value", "Selenium"))
            }

            When("toolsMenu") {
                jetbrainsMainPage.toolsMenu.click()

                Selenide.element("div[data-test='main-submenu']").shouldBe(Condition.visible)
            }

            When("navigationToAllTools") {
                jetbrainsMainPage.seeDeveloperToolsButton.click()
                jetbrainsMainPage.findYourToolsButton.click()

                Selenide.element("#products-page").shouldBe(Condition.visible)

                Selenide.title() shouldBeEqualIgnoringCase "All Developer Tools and Products by JetBrains"

            }
        }
    }

}
