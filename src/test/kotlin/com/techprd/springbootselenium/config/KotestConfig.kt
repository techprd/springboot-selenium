package com.techprd.springbootselenium.config

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode

class KotestConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension, SpringTestExtension(SpringTestLifecycleMode.Root))
}
