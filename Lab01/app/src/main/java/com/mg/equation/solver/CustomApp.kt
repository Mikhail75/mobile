package com.mg.equation.solver

import android.app.Application

class CustomApp : Application() {
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: CustomApp

        fun getApplication() : CustomApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun test() : String {
        return "Hello from CustomApp!"
    }
}