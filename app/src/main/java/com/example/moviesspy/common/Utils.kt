package com.example.moviesspy.common

fun String.ensureHttpsUrl(): String {
    return if (this.startsWith("http:")) {
        this.replaceFirst("http:", "https:")
    } else {
        return this
    }
}