package com.finglauytao.zlib.anim.star

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

/**
 * Create on 2018/8/20
 *
 * @author finglauytao
 * @version 1.0.0
 * 星光动画精灵
 **/
class StarSprite : View {

    private var paint = Paint()
    private var sidesNum = 3                //形状边数(最小3边形)
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

        val random = Random()
        sidesNum = random.nextInt(6) + sidesNum;
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        size.set(width, height)
        centerPoint.set(size.x / 2, size.y / 2)
        val rValue = Math.min(size.x, size.y)
        val random = Random()
        val startAngle = random.nextInt(360)
        val oneAngle = 360 / sidesNum
        var path = Path()
        when (sidesNum) {
            3 -> {
                var i = 0
                while (i < sidesNum) {
                    val angleValue = startAngle + oneAngle * i
                    val point = findPointInCircle(centerPoint, rValue, angleValue)
                    if (i == 0) {
                        path.moveTo(point.x.toFloat(), point.y.toFloat())
                    } else {
                        path.lineTo(point.x.toFloat(), point.y.toFloat())
                    }
                    i++
                }
                path.close()
                canvas!!.drawPath(path, paint)
            }
            4 -> {
                var i = 0
                while (i < sidesNum) {
                    val angleValue = startAngle + oneAngle * i
                    val point = findPointInCircle(centerPoint, rValue, angleValue)
                    if (i == 0) {
                        path.moveTo(point.x.toFloat(), point.y.toFloat())
                    } else {
                        path.lineTo(point.x.toFloat(), point.y.toFloat())
                    }
                    i++
                }
                path.close()
                canvas!!.drawPath(path, paint)
            }
            5 -> {
                var i = 0

                val pointList = ArrayList<Point>()
                while (i < sidesNum) {
                    val angleValue = startAngle + oneAngle * i
                    pointList.add(findPointInCircle(centerPoint, rValue, angleValue))
                    i++
                }
                path.moveTo(pointList[0].x.toFloat(), pointList[0].y.toFloat())
                path.lineTo(pointList[2].x.toFloat(), pointList[2].y.toFloat())
                path.lineTo(pointList[4].x.toFloat(), pointList[4].y.toFloat())
                path.lineTo(pointList[1].x.toFloat(), pointList[1].y.toFloat())
                path.lineTo(pointList[3].x.toFloat(), pointList[3].y.toFloat())
                path.lineTo(pointList[0].x.toFloat(), pointList[0].y.toFloat())
                canvas!!.drawPath(path, paint)
            }

            6 -> {
                var i = 0
                val pointList = ArrayList<Point>()
                while (i < sidesNum) {
                    val angleValue = startAngle + oneAngle * i
                    pointList.add(findPointInCircle(centerPoint, rValue, angleValue))
                    i++
                }
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

            7 -> {
                var i = 0
                while (i < sidesNum) {
                    val angleValue = startAngle + oneAngle * i
                    val point = findPointInCircle(centerPoint, rValue, angleValue)
                    if (i == 0) {
                        path.moveTo(point.x.toFloat(), point.y.toFloat())
                    } else {
                        path.lineTo(point.x.toFloat(), point.y.toFloat())
                    }
                    i++
                }
                path.close()
                canvas!!.drawPath(path, paint)
            }
            8 -> {
                var i = 0
                while (i < sidesNum) {
                    val angleValue = startAngle + oneAngle * i
                    val point = findPointInCircle(centerPoint, rValue, angleValue)
                    if (i == 0) {
                        path.moveTo(point.x.toFloat(), point.y.toFloat())
                    } else {
                        path.lineTo(point.x.toFloat(), point.y.toFloat())
                    }
                    i++
                }
                path.close()
                canvas!!.drawPath(path, paint)
            }
            9 -> {
                var i = 0
                while (i < sidesNum) {
                    val angleValue = startAngle + oneAngle * i
                    val point = findPointInCircle(centerPoint, rValue, angleValue)
                    if (i == 0) {
                        path.moveTo(point.x.toFloat(), point.y.toFloat())
                    } else {
                        path.lineTo(point.x.toFloat(), point.y.toFloat())
                    }
                    i++
                }
                path.close()
                canvas!!.drawPath(path, paint)
            }

        }


//        圆点坐标：(x0,y0)
//        半径：r
//        角度：a0
//
//        则圆上任一点为：（x1,y1）
//        x1   =   x0   +   r   *   cos(ao   *   3.14   /180   )
//        y1   =   y0   +   r   *   sin(ao   *   3.14   /180   )


    }

    private fun findPointInCircle(centerPoint: Point, rValue: Int, angle: Int): Point {
        return Point((centerPoint.x + rValue * Math.cos(angle * 3.14 / 180)).toInt(),
                (centerPoint.y + rValue * Math.sin(angle * 3.14 / 180)).toInt())
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


}