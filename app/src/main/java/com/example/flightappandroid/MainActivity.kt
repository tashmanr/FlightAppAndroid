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
        throttle = findViewById<SeekBar>(R.id.throttle)
        rudder = findViewById<SeekBar>(R.id.rudder)
        rudder.progress = 50
        joystick.onChange = {
                aileron:Float, elevator:Float->
            viewModel.setAileron(aileron)
            viewModel.setElevator(elevator)
        }
        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                var tmp = progress.toFloat()/100
                viewModel.setThrottle(tmp)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
            }
        })
        rudder?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                var tmp = (progress.toFloat()-50)/50
                viewModel.setRudder(tmp)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
            }
        })



    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button -> {
                viewModel.connect(ip.text.toString(), port.text.toString())
            }
        }
    }

}
