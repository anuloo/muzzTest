package com.example.muztest.domain.model

sealed class User(val name: String) {
    object UserOne : User("Sarah")
    object UserTwo : User("Max")
}
