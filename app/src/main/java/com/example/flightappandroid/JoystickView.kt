package com.example.flightapp2

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
import kotlin.math.max
import kotlin.math.min


class JoystickView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
  ) : View(context, attrs, defStyleAttr){

    private var middle1 : PointF = PointF()
    private var middle2 : PointF = PointF()
    private var radius1 : Float = 0.0F
    private var radius2 : Float = 0.0F
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private var paint3 = Paint(Paint.ANTI_ALIAS_FLAG)
  override fun onSizeChanged(nW: Int, nH: Int, oW: Int, oH: Int){
    middle1.x = nW.toFloat()/2
    middle1.y = nH.toFloat()/2
    middle2.x = nW.toFloat()/2
    middle2.y = nH.toFloat()/2
    var d = nW / 2
    radius1 = d.toFloat() - 80
    radius2 = d.toFloat() - 120
  }

    override fun onDraw(canvas: Canvas) {
      super.onDraw(canvas)
      drawFirstCircle(canvas)
      drawSecondCircle(canvas)

    }



  @RequiresApi(Build.VERSION_CODES.M)
  override fun onTouchEvent(event: MotionEvent?): Boolean{
    when(event?.action){

      MotionEvent.ACTION_DOWN -> {
        if (event != null) {
          middle2.x= event.x
        }
        if (event != null) {
          middle2.y= event.y
        }
      }


      MotionEvent.ACTION_MOVE -> {
        if (event != null) {
          if(middle2.x<event.x){
            middle2.x= max(event.x,middle1.x-30)
          }else{
            middle2.x= min(event.x,middle1.x+30)
          }
        }
        if (event != null) {
          if(middle2.y<event.y){
            middle2.y= max(event.y,middle1.y-30)
          }else{
            middle2.y= min(event.y,middle1.y+30)
          }
        }
        invalidate()
      }

      MotionEvent.ACTION_UP -> {
        if (event != null) {
          if(middle2.x<event.x){
            middle2.x= max(event.x,middle1.x-30)
          }else{
            middle2.x= min(event.x,middle1.x+30)
          }
        }
        if (event != null) {
          if(middle2.y<event.y){
            middle2.y= max(event.y,middle1.y-30)
          }else{
            middle2.y= min(event.y,middle1.y+30)
          }
        }
        invalidate()
      }

      MotionEvent.ACTION_CANCEL -> {

      }

      else ->{

      }
    }
    return true
  }

  private fun drawFirstCircle(canvas: Canvas){
    paint.isAntiAlias = true
    paint.isDither = true
    paint.color = Color.BLACK
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 250F
    val newR = radius1 - 50
    canvas.drawCircle(this.middle1.x, this.middle1.y, newR, paint)
    paint.color = Color.GREEN
    paint.strokeWidth = 20F
    canvas.drawCircle(this.middle1.x, this.middle1.y, radius1, paint)


  }

  private fun drawSecondCircle(canvas: Canvas){
    paint.style = Paint.Style.FILL
    paint.color = Color.RED
    canvas.drawCircle(this.middle2.x, this.middle2.y, radius2, paint)
  }

}



