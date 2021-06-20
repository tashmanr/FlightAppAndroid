package com.example.flightappandroid

import android.graphics.Point
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import com.example.flightappandroid.JoystickView
import com.example.flightappandroid.ViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var viewModel: ViewModel = ViewModel()
    private lateinit var connectButton: Button
    private lateinit var ip: EditText
    private lateinit var port: EditText
    private lateinit var joystick: JoystickView
    private lateinit var throttle: SeekBar
    private lateinit var rudder: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectButton = findViewById<Button>(R.id.button)
        connectButton.setOnClickListener(this)
        ip = findViewById<EditText>(R.id.IP)
        port = findViewById<EditText>(R.id.port)
        joystick = findViewById<JoystickView>(R.id.joystick)
        joystick.setMainView(this)
        throttle = findViewById<SeekBar>(R.id.throttle)
        rudder = findViewById<SeekBar>(R.id.rudder)

        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
                println("mainActivity throttle is:$progress")
                viewModel.setThrottle(progress.toFloat())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                Log.d("myTag", throttle.progress.toString())
                /*Toast.makeText(this@MainActivity,
                    "Progress is: " + throttle.progress + "%",
                    Toast.LENGTH_SHORT).show()*/
            }
        })
        rudder?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                // write custom code for progress is changed
                println("mainActivity rudder is:$progress")
                viewModel.setRudder(progress.toFloat())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                Log.d("myTag", rudder.progress.toString())
                /*Toast.makeText(this@MainActivity,
                    "Progress is: " + rudder.progress + "%",
                    Toast.LENGTH_SHORT).show()*/
            }
        })

    }


    override fun onClick(view: View?) {
        println("we have pressed on the button:) 1")

        when (view?.id) {

            R.id.button -> {
                viewModel.connect(ip.text.toString(), port.text.toString())
            }
        }
        println("we have pressed on the button:) 2")
    }

    fun joystickChanged(a: Float, e: Float) {
        viewModel.setAileron(a)
        viewModel.setElevator(e)
    }
}
