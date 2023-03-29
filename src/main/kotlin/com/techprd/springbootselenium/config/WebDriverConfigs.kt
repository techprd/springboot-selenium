package com.techprd.springbootselenium.config

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.openqa.selenium.chrome.ChromeOptions


@org.springframework.context.annotation.Configuration
class WebDriverConfigs(appConfigs: AppConfigs) {

    init {
        Configuration.browserSize = "1280x800"
        Configuration.headless = appConfigs.headless
        Configuration.browserCapabilities = ChromeOptions().addArguments("--remote-allow-origins=*")
        Configuration.timeout = appConfigs.timeout

        // Allure reports
        SelenideLogger.addListener("allure", AllureSelenide())
    }
}
