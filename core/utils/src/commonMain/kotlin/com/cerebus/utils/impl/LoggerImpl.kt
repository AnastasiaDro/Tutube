package com.cerebus.utils.impl

import com.cerebus.utils.api.Logger
import co.touchlab.kermit.Logger as KermitLogger

class LoggerImpl() : Logger {

    override fun i(throwable: Throwable?, moduleTag: String, tag: String, message: () -> String) {
        KermitLogger.i(throwable, "[$moduleTag][$tag]", message)
    }

    override fun d(throwable: Throwable?, moduleTag: String, tag: String, message: () -> String) {
        KermitLogger.d(throwable, "[$moduleTag][$tag]", message)
    }

    override fun w(throwable: Throwable?, moduleTag: String, tag: String, message: () -> String) {
        KermitLogger.w(throwable, "[$moduleTag][$tag]", message)
    }

    override fun e(throwable: Throwable?, moduleTag: String, tag: String, message: () -> String) {
        KermitLogger.e(throwable, "[$moduleTag][$tag]", message)
    }
}