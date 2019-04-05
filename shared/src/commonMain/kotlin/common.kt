package br.com.rafaelgabriel

import kotlin.coroutines.CoroutineContext

expect fun platformName(): String

class Greeting {
    fun greeting(): String = "Hello, ${platformName()}"
}


internal expect val ApplicationDispatcher: CoroutineContext