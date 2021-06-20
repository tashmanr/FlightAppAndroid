package com.example.flightappandroid

import java.io.PrintWriter
import java.net.Socket

class Model(port:Int, ip: String) {
    //var fg = Socket(ip, port)
    //var out: PrintWriter = PrintWriter(fg.getOutputStream(),true)
    fun setRudder(f: Float){
        //out.print("set /controls/flight/rudder $f\r\n")
        //out.flush()
    }
    fun setThrottle(f: Float){
        println("Model throttle is:$f")
        //out.print("set /controls/flight/throttle $f\r\n")
        //out.flush()
    }
    fun setElevator(f: Float){
        println("Model elevator is:$f")
        //out.print("set /controls/flight/elevator $f\r\n")
        //out.flush()
    }
    fun setAileron(f: Float){
        println("Model aileron is:$f")
        //out.print("set /controls/flight/aileron $f\r\n")
        //out.flush()
    }

}