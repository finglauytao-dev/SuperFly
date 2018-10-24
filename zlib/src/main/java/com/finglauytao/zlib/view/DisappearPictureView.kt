package com.finglauytao.zlib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import java.util.*

/**
 * Use This View
 * you must be setDisappearColor
 * default DisappearColor "ffffff"
 *
 * Create on 2018/6/27
 *
 * @author finglauytao
 * @version 1.0.0
 * 消失的ImageView
 **/
class DisappearPictureView : ImageView {

    private val mPaint = Paint();
    private var mDisplayLineHeight = 3;
    private var mViewWidth = 0;
    private var mViewHeight = 0;
    private val mTimer = Timer();

    constructor(context: Context) : super(context) {
        initPaint()
        runTimer();
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initPaint()
        runTimer()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
        runTimer()
    }

    override fun onDraw(canvas: Canvas?) {
        if (mViewWidth == 0) mViewWidth = width
        if (mViewHeight == 0) mViewHeight = height
        canvas!!.drawRect(0f, 0f, mViewWidth.toFloat(), mDisplayLineHeight.toFloat(), mPaint)
        invalidate()
        if (mDisplayLineHeight > mViewHeight) mTimer.cancel()
    }

    private fun initPaint() {
        mPaint.color = Color.parseColor("#ffffff");
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 3f;
        mPaint.isAntiAlias = true
    }

    /**
     * 设置消失的颜色
     */
    fun setDisappearColor(colorStr: String) {
        mPaint.color = Color.parseColor(colorStr);
    }

    private fun runTimer() {
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                mDisplayLineHeight += 3
                post(object : Runnable {
                    override fun run() {
                        invalidate()
                    }

                })
            }

        }, 500, 16)
    }


}