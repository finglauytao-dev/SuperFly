package com.finglauytao.zlib.anim

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Create on 2018/6/29
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class MatrixAnim : Animation {

    private var mSize = Point()
    private val MAXT_VALUE = 500;

    constructor() : super() {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {


    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mSize.x = width
        mSize.y = height
    }

    /**
     * interpolatedTime -MaxInt   -1.0f
     * matrix pre post set
     *        pre  动画执行队列前一位   post  整成队列顺序  set 以上动画全部失效
     */
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val matrix = t!!.matrix
        Log.e("MMMM--MM", "interpolatedTime=="+interpolatedTime)
        matrix.postRotate(interpolatedTime*MAXT_VALUE)
        matrix.postScale(interpolatedTime, interpolatedTime, mSize.x / 2f, mSize.y / 2f)



    }
}