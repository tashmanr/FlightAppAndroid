package com.example.flightappandroid

import java.util.concurrent.Future


//All functions im this class are merely there to seperate between the view and the model and have no functionality of themselves names are sel explanatory
class ViewModel {
    private lateinit var model: Model //field model will be intialised in the future

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
