package com.example.selfmed.model

class PatientDetails {
    private var password: String = ""
    private var fullname: String = ""
    private var username: String = ""
    private var userUID: String = ""

    constructor()
    constructor(password_1: String, fullname_1: String, username_1: String, userUID_1: String) {
        this.password = password_1
        this.userUID = userUID_1
        this.fullname = fullname_1
        this.username = username_1
    }

    fun getFullname(): String {
        return fullname
    }

    fun setFullname(fullname_1: String) {
        this.fullname = fullname_1
    }


    fun getUserUID(): String {
        return password
    }

    fun setUserUID(userUID_1: String) {
        this.userUID = userUID_1
    }


    fun getUsername(): String {
        return username
    }

    fun setUsername(username_1: String) {
        this.username = username_1
    }


    fun getPassword(): String {
        return password
    }

    fun setPassword(password_1: String) {
        this.password = password_1
    }
}