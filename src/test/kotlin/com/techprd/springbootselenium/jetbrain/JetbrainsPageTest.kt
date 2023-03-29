package com.techprd.springbootselenium.jetbrain

import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.*
import com.techprd.springbootselenium.pages.JetbrainsMainPage
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldBeEqualIgnoringCase
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JetbrainsPageTest(jetbrainsMainPage: JetbrainsMainPage) : BehaviorSpec() {

    init {

        beforeContainer {
            jetbrainsMainPage.open()
        }

        given("I want to test main page") {

            When("I search for Selenium term") {
                jetbrainsMainPage.searchButton.click()

                element("[data-test='search-input']").sendKeys("Selenium")
                element("button[data-test='full-search-button']").click()

                then("search input should exists") {
                    element("input[data-test='search-input']")
                        .shouldHave(attribute("value", "Selenium"))
                }
            }

            When("toolsMenu") {
                jetbrainsMainPage.toolsMenu.click()

                element("div[data-test='main-submenu']").shouldBe(visible)
            }

            When("navigationToAllTools") {
                jetbrainsMainPage.seeDeveloperToolsButton.click()
                jetbrainsMainPage.findYourToolsButton.click()

                element("#products-page").shouldBe(visible)

                title() shouldBeEqualIgnoringCase "All Developer Tools and Products by JetBrains"

            }
        }
    }

}
