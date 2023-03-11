package com.techprd.springbootselenium.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "configs")
class AppConfigs(
    var headless: Boolean = true
)
