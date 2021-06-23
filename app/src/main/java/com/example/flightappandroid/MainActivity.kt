package com.example.flightappandroid

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var viewModel: ViewModel = ViewModel()
    private lateinit var connectButton: Button
    private lateinit var ip: EditText
    private lateinit var port: EditText
    private lateinit var joystick: JoystickView
    private lateinit var throttle: SeekBar
    private lateinit var rudder: SeekBar
    private var isConnected = false
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
        joystick.onChange = { aileron: Float, elevator: Float ->
            if (isConnected) {
                viewModel.setAileron(aileron)
                viewModel.setElevator(elevator)
            } else {
                showErrorMessage("Not connected to FlightGear")
            }
        }
        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                if (isConnected) {
                    var tmp = progress.toFloat() / 100
                    viewModel.setThrottle(tmp)
                } else {
                    showErrorMessage("Not connected to FlightGear")

                }
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
                if (isConnected) {
                    var tmp = (progress.toFloat() - 50) / 50
                    viewModel.setRudder(tmp)
                } else {
                    showErrorMessage("Not connected to FlightGear")
                }
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
                if (port.length() > 0 && ip.length() > 0) {
                    viewModel.connect(ip.text.toString(), port.text.toString())
                } else {
                    showErrorMessage("Port and IP address required")
                    return
                }
                sleep(1000)
                if (!checkConnection()) {
                    showErrorMessage("Connection to FlightGear failed, try again")
                }
            }
        }
    }

    @SuppressLint("ShowToast")
    fun showErrorMessage(s: String) {
        Snackbar.make(joystick, s, Snackbar.LENGTH_SHORT)
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
            .setBackgroundTint(Color.GRAY).show()
    }

    private fun checkConnection(): Boolean {
        isConnected = viewModel.checkConnection()
        return isConnected
    }

}
