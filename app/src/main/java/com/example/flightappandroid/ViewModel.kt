package com.example.flightappandroid

import java.util.concurrent.Future

class ViewModel {
    private lateinit var model: Model

    fun connect(ip: String, port: String) {
        model = Model(port, ip)
        model.connect()
    }

    fun checkConnection(): Boolean {
        return model.checkConnection()
    }

    fun setRudder(f: Float) {
        model.setRudder(f)
    }

    fun setThrottle(f: Float) {
        model.setThrottle(f)
    }

    fun setElevator(f: Float) {
        model.setElevator(f)
    }

    fun setAileron(f: Float) {
        model.setAileron(f)
    }
}