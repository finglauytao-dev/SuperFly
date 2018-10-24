package com.finglauytao.zlib.view

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.widget.TextView
import java.util.*

/**
 * Create on 2018/6/21
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class ZPathText : TextView {

    private var mTextPath: Path = Path();
    private var mTargetPath: Path = Path();

    private var mTextPathMeasure: PathMeasure = PathMeasure()
    private var mTextLengthSum: Long = 0;

    private var mCurrentProgress: Float = 0.0f;
    private var mCurrentDrawLengthSum: Long = 0;

    private var mPathPaint: Paint = Paint();

    private var mTimerProgress = 0f;

    constructor(context: Context) : super(context) {
        initPathAndPaint()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initPathAndPaint()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPathAndPaint()
    }

    private fun initPathAndPaint() {
        mPathPaint.strokeWidth = 10f;
        mPathPaint.color = Color.parseColor("#ff0000")
        mPathPaint.isAntiAlias = true;
        mPathPaint.style = Paint.Style.STROKE;

        paint.getTextPath(text.toString(), 0, text.length, 0f, textSize, mTextPath)
        mTextPathMeasure.setPath(mTextPath, false);
        while (mTextPathMeasure.nextContour()) {
            mTextLengthSum += (mTextPathMeasure.getLength().toLong())
        }



        runAnim()
    }


    private fun drawPath(progress: Float) {
        mCurrentProgress = progress;
        mCurrentDrawLengthSum = (mTextLengthSum * progress).toLong()

        //重置路径
        mTextPathMeasure.setPath(mTextPath, false)
        mTargetPath.reset();
        mPathPaint.reset();

        //根据进度获取路径
        while (mCurrentDrawLengthSum > mTextPathMeasure.getLength()) {
            mCurrentDrawLengthSum = mCurrentDrawLengthSum - mTextPathMeasure.getLength().toLong();
            mTextPathMeasure.getSegment(0f, mTextPathMeasure.getLength(), mTargetPath, true)
            if (!mTextPathMeasure.nextContour()) {
                break;
            }
        }
        mTextPathMeasure.getSegment(0f, mCurrentDrawLengthSum.toFloat(), mTargetPath, true)

        invalidate()
    }


    private fun runAnim(){
        val timer :Timer = Timer();
        timer.schedule(object: TimerTask(){
            override fun run() {
                mTimerProgress += 0.01f
                mHandler.sendEmptyMessage(0)

            }
        } , 1000, 200)
    }

    var mHandler: Handler = Handler(object: Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            drawPath(mTimerProgress)
            return true;
        }
    });



}