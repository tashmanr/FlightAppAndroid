package com.example.flightapp2

import android.graphics.PointF

class ViewModel {
    private var model : Model = Model()
    //private var ip : String = ""
    //private var port: Int = 0
    fun connect(ip:String, port:String){
        println("went into connect ;)")

        model.connectToSocket(port,ip)
        println("connected :)")
    }

    fun getRudder(f: Float){
        model.getRudder(f)
    }
    fun getThrottle(f: Float){
        println("viewModel throttle is:$f")
        model.getThrottle(f)
    }
    fun getElevator(f: Float){
        model.getElevator(f)
    }
    fun getAileron(f: Float){
        model.getAileron(f)
    }


}