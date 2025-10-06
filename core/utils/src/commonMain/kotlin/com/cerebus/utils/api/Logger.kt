package com.cerebus.utils.api

import com.cerebus.utils.impl.LoggerProvider


val logger: Logger
    get() = LoggerProvider.logger
interface Logger {

    // Trailing function parameter
    fun i(throwable: Throwable? = null, moduleTag: String, tag: String, message: () -> String)
    fun d(throwable: Throwable? = null, moduleTag: String, tag: String, message: () -> String)
    fun w(throwable: Throwable? = null, moduleTag: String, tag: String, message: () -> String)
    fun e(throwable: Throwable? = null, moduleTag: String, tag: String, message: () -> String)
}