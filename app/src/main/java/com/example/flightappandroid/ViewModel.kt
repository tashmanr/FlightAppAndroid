package com.example.flightappandroid

import android.graphics.PointF

class ViewModel {
    private lateinit var model : Model
    fun connect(ip:String, port:String){
        model = Model(port.toInt(), ip)
    }

    fun setRudder(f: Float){
        model.setRudder(f)
    }

    fun setThrottle(f: Float){
        model.setThrottle(f)
    }

    fun setElevator(f: Float){
        model.setElevator(f)
    }

    fun setAileron(f: Float){
        model.setAileron(f)
    }
}