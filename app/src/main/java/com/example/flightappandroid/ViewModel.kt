package com.example.flightappandroid

import android.graphics.PointF

class ViewModel {
    private lateinit var model : Model
    fun connect(ip:String, port:String){
        println("went into connect ;)")
        model = Model(port.toInt(), ip)
        println("connected :)")
    }

    fun setRudder(f: Float){
        model.setRudder(f)
    }

    fun setThrottle(f: Float){
        println("viewModel throttle is:$f")
        model.setThrottle(f)
    }

    fun setElevator(f: Float){
        model.setElevator(f)
    }

    fun setAileron(f: Float){
        model.setAileron(f)
    }
}