package com.finglauytao.zlib.anim.star

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Create on 2018/8/20
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class SixStarSprite : View {

    private val paint = Paint()
    private var size = Point()
    private var centerPoint = Point()

    constructor(context: Context) : super(context) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {
        paint.strokeWidth = 3f
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = Color.parseColor("#ff0000")//默认红色
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        size.set(width, height)
        centerPoint.set(size.x / 2, size.y / 2)
        val rValue = Math.min(size.x, size.y)

        var i = 0
        val pointList = ArrayList<Point>()
        while (i < 6) {
            val angleValue = 60 * i
            pointList.add(findPointInCircle(centerPoint, rValue, angleValue))
            i++
        }
        val path = Path()
        path.moveTo(pointList[0].x.toFloat(), pointList[0].y.toFloat())
        path.lineTo(pointList[2].x.toFloat(), pointList[2].y.toFloat())
        path.lineTo(pointList[4].x.toFloat(), pointList[4].y.toFloat())
        path.close()

        val path1 = Path()
        path1.moveTo(pointList[1].x.toFloat(), pointList[1].y.toFloat())
        path1.lineTo(pointList[3].x.toFloat(), pointList[3].y.toFloat())
        path1.lineTo(pointList[5].x.toFloat(), pointList[5].y.toFloat())
        path1.close()
        canvas!!.drawPath(path, paint)
        canvas!!.drawPath(path1, paint)
    }

    private fun findPointInCircle(centerPoint: Point, rValue: Int, angle: Int): Point {
        return Point((centerPoint.x + rValue * Math.cos(angle * 3.14 / 180)).toInt(),
                (centerPoint.y + rValue * Math.sin(angle * 3.14 / 180)).toInt())
    }

}