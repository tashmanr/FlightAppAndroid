package com.example.flightappandroid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.math.sqrt


class JoystickView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var onChange:(Float, Float) -> Unit //lambda expression will be defined in mainActivity
    private var middle1: PointF = PointF()
    private var middle2: PointF = PointF()
    private var aileron : Float = 0.0F
    private var elevator : Float = 0.0F
    private var radius1: Float = 0.0F
    private var radius2: Float = 0.0F
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    //calculates intial size and place of joystick
    override fun onSizeChanged(nW: Int, nH: Int, oW: Int, oH: Int) {
        middle1.x = nW.toFloat() / 2
        middle1.y = nH.toFloat() / 2
        middle2.x = nW.toFloat() / 2
        middle2.y = nH.toFloat() / 2
        var d = nW / 2
        radius1 = d.toFloat() - 80
        radius2 = d.toFloat() - 150
    }
    
    //function in charge of drawing content of joystick
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawFirstCircle(canvas)
        drawSecondCircle(canvas)

    }

    
    //in case of a touch event updates the place of the joystick according to users request
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {




            MotionEvent.ACTION_MOVE -> {
                if(event!=null) {
                    var newPoint =  PointF(event.x,event.y)
                    if (distance(middle1, newPoint) <= 80) {
                            middle2.x = event.x
                            middle2.y = event.y
                        }
                }
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                if (event != null) {
                    middle2.x = middle1.x
                    middle2.y = middle1.y
                }
                invalidate()
            }

            MotionEvent.ACTION_CANCEL -> {

            }

            else -> {

            }
        }
        aileron = (middle2.x-middle1.x)/80
        elevator = (middle2.y-middle1.y)/80
        onChange(aileron, elevator)
        return true
    }
    
    //draws the background of the joystick
    private fun drawFirstCircle(canvas: Canvas) {
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 250F
        val newR = radius1 - 50
        canvas.drawCircle(this.middle1.x, this.middle1.y, newR, paint)
        paint.color = Color.GRAY

        paint.strokeWidth = 20F
        canvas.drawCircle(this.middle1.x, this.middle1.y, radius1, paint)


    }
    
    //draws the middle of the joystick
    private fun drawSecondCircle(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        canvas.drawCircle(this.middle2.x, this.middle2.y, radius2, paint)
    }
    
    //calculates ditance between two points namely the middle and destination point to ensure we dont leave the joystick 
    private fun distance(start: PointF, end: PointF): Float{
        return sqrt(((start.x-end.x)*(start.x-end.x)) +((start.y-end.y)*(start.y-end.y)))
    }

}



