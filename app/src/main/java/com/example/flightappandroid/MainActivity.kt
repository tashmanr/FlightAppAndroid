package com.example.flightappandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var viewModel : ViewModel = ViewModel()
    lateinit var connectButton : Button
    lateinit var ip :EditText
    lateinit var port :EditText
    lateinit var joystick :JoystickView
    lateinit var throttle :SeekBar
    lateinit var rudder :SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectButton = findViewById<Button>(R.id.button)
        connectButton.setOnClickListener(this)
        ip = findViewById<EditText>(R.id.IP)
        port = findViewById<EditText>(R.id.port)
        joystick = findViewById<JoystickView>(R.id.joystick)
        throttle = findViewById<SeekBar>(R.id.throttle)
        rudder = findViewById<SeekBar>(R.id.rudder)

        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
                println("mainActivity throttle is:$progress")
                viewModel.getThrottle(progress.toFloat())
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                Log.d("myTag",throttle.progress.toString())
                /*Toast.makeText(this@MainActivity,
                    "Progress is: " + throttle.progress + "%",
                    Toast.LENGTH_SHORT).show()*/
            }
        })
    }

    override fun onClick(view : View?){
        println("we have pressed on the button:) 1")

        when(view?.id) {

            R.id.button-> {
                viewModel.connect(ip.text.toString(),port.text.toString())
            }}
        println("we have pressed on the button:) 2")
    }
}
