package org.reza.currency

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform