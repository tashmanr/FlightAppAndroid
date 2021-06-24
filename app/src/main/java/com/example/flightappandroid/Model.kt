package com.example.flightappandroid

import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class Model constructor(port: String, ip: String) {
    private var modelPort = port
    private var modelIP = ip
    private var isConnected = false
    private lateinit var fg: Socket
    private lateinit var out: PrintWriter
    private var tasks: BlockingQueue<Runnable> = LinkedBlockingQueue<Runnable>();
    
    //Thread that is in charge of taking tasks out of queue and running them
    var tasksThread = Thread(Runnable() {
        while (true) {
            try {
                tasks.take().run();
            } catch (e: InterruptedException) {
            }
        }
    }).start()
    
    //checks that connection is valid
    fun checkConnection(): Boolean {
        return isConnected
    }
    
    //first task to be inserted to queue is the task to connect to the flightGear
    fun connect() {
        tasks.put(Runnable() {
            try {
                fg = Socket(modelIP, modelPort.toInt())
                out = PrintWriter(fg.getOutputStream(), true)
                isConnected = fg.isConnected
            } catch (e: Exception) {
            }
        })
    }

    fun setRudder(f: Float) {
        tasks.put(Runnable() {
            out.print("set /controls/flight/rudder $f\r\n")
            out.flush()
        })
    }

    fun setThrottle(f: Float) {
        tasks.put(Runnable() {
            out.print("set /controls/engines/current-engine/throttle $f\r\n")
            out.flush()
        })
    }

    fun setElevator(f: Float) {
        tasks.put(Runnable() {
            out.print("set /controls/flight/elevator $f\r\n")
            out.flush()
        })
    }

    fun setAileron(f: Float) {
        tasks.put(Runnable() {
            out.print("set /controls/flight/aileron $f\r\n")
            out.flush()
        })
    }
}
