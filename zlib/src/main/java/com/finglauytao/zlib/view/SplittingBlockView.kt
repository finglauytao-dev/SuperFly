package com.finglauytao.zlib.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

/**
 * Create on 2018/6/28
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class SplittingBlockView : ImageView {

    lateinit var mBitmap: Bitmap
    private var mSize = Point()
    private var mPaint = Paint()
    private var mThreadRunState = false
    private var mCutFinished = false

    private var mLTBitmap: Bitmap? = null
    private var mRTBitmap: Bitmap? = null
    private var mLBitmap: Bitmap? = null
    private var mRBBitmap: Bitmap? = null

    private var mSplittingBlockListener: SplittingBlockListener? = null


    constructor(context: Context) : super(context) {
        initData()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initData()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initData()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mSize.x == 0) mSize.x = width - 2
        if (mSize.y == 0) mSize.y = height - 2

        //计算分割
        canvas!!.drawLine(mSize.x / 2f, 0f, mSize.x / 2f, mSize.y.toFloat(), mPaint)
        canvas.drawLine(0f, mSize.y / 2f, mSize.x.toFloat(), mSize.y / 2f, mPaint)
        runCutThread()

        if (mCutFinished) {
            val matrix = getImageMatrix()
            matrix.setRotate(123f)
            canvas.drawBitmap(mRTBitmap, matrix, mPaint)
            //TODO CHECK THIS
        }
    }

    private fun initData() {
        val bt = drawable as BitmapDrawable;
        mBitmap = bt.bitmap;

        mPaint.color = Color.parseColor("#ff0000")
        mPaint.strokeWidth = 10f;
        mPaint.isAntiAlias = true;
    }

    private fun runCutThread() {
        if (!mThreadRunState) {
            mThreadRunState = true
            val makeThread = object : Thread() {
                override fun run() {
                    super.run()
                    Log.e("MMMM--MM", "start--" + System.currentTimeMillis())
                    cutBit()
                    Log.e("MMMM--MM", "end--" + System.currentTimeMillis())
                    post(object : Runnable {
                        override fun run() {
                            invalidate()
                        }
                    })
                }
            }
            makeThread.start()
        }
        if (!mCutFinished) return
    }

    private fun cutBit() {
        //切割区域计算
        val hCenter = mSize.x / 2
        val vCenter = mSize.y / 2
        val colorArray = ArrayList<Int>();
        for (i in 0..hCenter) {
            for (a in 0..vCenter) {
                colorArray.add(mBitmap.getPixel(i, a))
            }
        }
    /*    //左上角图片
        mLTBitmap = Bitmap.createBitmap(colorArray.toIntArray(), hCenter, vCenter, Bitmap.Config.ARGB_8888)

        for (i in hCenter..mSize.x) {
            for (a in 0..vCenter) {
                colorArray.add(mBitmap.getPixel(i, a))
            }
        }
        //右上角图片
        mRTBitmap = Bitmap.createBitmap(colorArray.toIntArray(), hCenter, vCenter, Bitmap.Config.ARGB_8888)

        for (i in 0..hCenter) {
            for (a in vCenter..mSize.y) {
                colorArray.add(mBitmap.getPixel(i, a))
            }
        }
        //左下角图片
        mLBitmap = Bitmap.createBitmap(colorArray.toIntArray(), hCenter, vCenter, Bitmap.Config.ARGB_8888)

        for (i in hCenter..mSize.x) {
            for (a in vCenter..mSize.y) {
                colorArray.add(mBitmap.getPixel(i, a))
            }
        }
        //右下角图片
        mRBBitmap = Bitmap.createBitmap(colorArray.toIntArray(), hCenter, vCenter, Bitmap.Config.ARGB_8888)*/

        mLTBitmap = Bitmap.createBitmap(mBitmap,  0, 0, hCenter, vCenter);
        mRTBitmap = Bitmap.createBitmap(mBitmap,  hCenter, 0, hCenter, vCenter);
        mLBitmap = Bitmap.createBitmap(mBitmap,  0, vCenter, hCenter, vCenter);
        mRBBitmap = Bitmap.createBitmap(mBitmap,  hCenter, vCenter, hCenter, vCenter);

        val bitmapList = ArrayList<Bitmap>()
        bitmapList.add(mLTBitmap!!)
        bitmapList.add(mRTBitmap!!)
        bitmapList.add(mLBitmap!!)
        bitmapList.add(mRBBitmap!!)
        mSplittingBlockListener!!.onFinish(bitmapList)

        mCutFinished = true
    }

    fun setSplittingBlockListener(listener: SplittingBlockListener) {
        mSplittingBlockListener = listener
    }

    interface SplittingBlockListener {
        fun onStart()
        fun onFinish(bitmaps: ArrayList<Bitmap>)
    }

}