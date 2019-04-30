package com.manoj.circularprogressbarexamples

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View

class CircularFillProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        attrs?.let { setAttributes(it, context) }
    }

    var fillColorInt: Int = 0
    var backgroundColorInt: Int = 0
    var maxProgress: Int = 100

    val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    val rect = RectF()

    //dummy
    var progress = 75
    set(value) {
        field = value
        invalidate()
    }

    private fun setAttributes(attrs: AttributeSet, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularDiscProgressView)
        fillColorInt = typedArray.getColor(R.styleable.CircularFillProgressView_fill_progress_fill_color, Color.GREEN)
        backgroundColorInt = typedArray.getColor(R.styleable.CircularFillProgressView_fill_progress_background_color, Color.GRAY)
        maxProgress = typedArray.getInt(R.styleable.CircularFillProgressView_fill_progress_max_progress, 100)
        typedArray.recycle()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //This is used as bounds of what we will draw
        rect.top = 0f
        rect.right = width.toFloat()
        rect.bottom = height.toFloat()
        rect.left = 0f

        //Set the variables
        fillPaint.style = Paint.Style.FILL
        fillPaint.color = fillColorInt
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.color = backgroundColorInt

        //draw fill
        canvas?.drawOval(rect, backgroundPaint)
        //calculate angle for progress
        val progressPercent = (progress*1.0f/maxProgress)*100
        //draw the inner disc
//        if(progressPercent <= 50) {
            //startAngle ranges from 90 deg to 0 deg for 0% to 50%
            val startAngle = ((50-progressPercent)/50.0f) * 90
            //sweepAngle ranges from 180 deg to 0 deg for start angle 0 deg to 90 deg
            val sweepAngle = ((90-startAngle)/90.0f) * 180
        val tempPaint = Paint()
        tempPaint.style = Paint.Style.FILL
        tempPaint.color = Color.BLUE
//        canvas?.drawRect(0.0f, height*(1-progressPercent/100.0f), width.toFloat(), height.toFloat(), tempPaint)
        canvas?.drawArc(rect, startAngle, sweepAngle, false, fillPaint)
//        } else {
//            //startAngle ranges from 90 deg to 0 deg for 0% to 50%
//            val startAngle = ((50-progressPercent)/50.0f) * 90
//            //sweepAngle ranges from 180 deg to 0 deg for start angle 0 deg to 90 deg
//            val sweepAngle = ((90-startAngle)/90.0f) * 180
//            canvas?.drawArc(rect, startAngle, sweepAngle, false, fillPaint)
//        }


    }






}