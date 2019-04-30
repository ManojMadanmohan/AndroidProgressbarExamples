package com.manoj.circularprogressbarexamples

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View

class CircularDiscProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        attrs?.let { setAttributes(it, context) }
    }

    var outerRingColorInt: Int = 0
    var innerDiscColorInt: Int = 0
    var outerRingThickness: Int = 0
    var maxProgress: Int = 100

    val outerRingPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val innerDiscPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    val rect = RectF()

    //dummy
    var progress = 75
    set(value) {
        field = value
        invalidate()
    }

    private fun setAttributes(attrs: AttributeSet, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularDiscProgressView)
        outerRingColorInt = typedArray.getColor(R.styleable.CircularDiscProgressView_disc_progress_outer_ring_color, Color.GREEN)
        innerDiscColorInt = typedArray.getColor(R.styleable.CircularDiscProgressView_disc_progress_inner_disc_color, Color.GREEN)
        outerRingThickness = typedArray.getDimensionPixelSize(R.styleable.CircularDiscProgressView_disc_progress_outer_ring_thickness, 2)
        maxProgress = typedArray.getInt(R.styleable.CircularDiscProgressView_disc_progress_max_progress, 100)
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
        outerRingPaint.color = outerRingColorInt
        outerRingPaint.strokeWidth = outerRingThickness.toFloat()
        outerRingPaint.style = Paint.Style.STROKE
        innerDiscPaint.color = innerDiscColorInt
        innerDiscPaint.style = Paint.Style.FILL

        //draw outer ring
        canvas?.drawOval(rect, outerRingPaint)
        //calculate angle for progress
        val angle = (progress*1.0f/maxProgress)*360
        //draw the inner disc
        canvas?.drawArc(rect, -90.0f, angle, true, innerDiscPaint)
    }






}