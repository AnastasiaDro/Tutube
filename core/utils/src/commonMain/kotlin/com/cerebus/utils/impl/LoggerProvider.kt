package com.cerebus.utils.impl

import com.cerebus.utils.api.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal object LoggerProvider : KoinComponent {
        val logger: Logger by inject()
}