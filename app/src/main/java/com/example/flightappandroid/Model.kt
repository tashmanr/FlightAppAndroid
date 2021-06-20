package com.example.flightapp2

import java.io.PrintWriter
import java.net.Socket

class Model {
    //lateinit var fg: Socket
    //var out: PrintWriter = PrintWriter(fg.getOutputStream(),true)
    fun connectToSocket(port: String, ip: String){
        println("connect to socket")
        //println("ip is:$ip")
       // println("port is: $port")
        //fg = Socket(ip,port)
    }
    fun getRudder(f: Float){}
    fun getThrottle(f: Float){
        println("Model throttle is:$f")
    }
    fun getElevator(f: Float){}
    fun getAileron(f: Float){}

}