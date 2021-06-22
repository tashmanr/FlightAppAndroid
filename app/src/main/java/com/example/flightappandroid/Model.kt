package com.example.flightappandroid

import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class Model constructor(port: Int, ip: String) {
    var modelPort = port
    var modelIP = ip
    lateinit var fg: Socket
    lateinit var out: PrintWriter
    var tasks: BlockingQueue<Runnable> = LinkedBlockingQueue<Runnable>();

    var tasksThread = Thread(Runnable() {
        while (true) {
            try {
                tasks.take().run();
            } catch (e: InterruptedException) {
            }
        }
    })

    fun connect() {
        tasks.put(Runnable() {
                println("Connecting now to the port! $modelPort with IP of $modelIP")
                fg = Socket(modelIP, modelPort)
                out = PrintWriter(fg.getOutputStream(), true)
        })
    }

    fun setRudder(f: Float) {
        tasks.put(Runnable() {
                println("setting rudder at $f")
                out.print("set /controls/flight/rudder " + f.toString() + "\r\n")
                out.flush()
        })
    }

    fun setThrottle(f: Float) {
        tasks.put(Runnable() {
                println("setting throttle at $f")
                println(f.toString())
                out.print("set /controls/engines/current-engine/throttle " + f.toString() + "\r\n")
                out.flush()
        })
    }

    fun setElevator(f: Float) {
        tasks.put(Runnable() {
                println("setting elevator at $f")
                out.print("set /controls/flight/elevator "+ f.toString() + "\r\n")
                out.flush()
        })
    }

    fun setAileron(f: Float) {
        tasks.put(Runnable() {
                println("setting aileron at $f")
                out.print("set /controls/flight/aileron "+ f.toString() + "\r\n")
                out.flush()
        })
    }
}