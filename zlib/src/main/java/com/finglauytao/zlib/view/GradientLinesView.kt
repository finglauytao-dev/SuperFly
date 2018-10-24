package com.finglauytao.zlib.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.finglauytao.zlib.R
import com.finglauytao.zlib.util.MathUtil

/**
 * Create on 2018/6/27
 *
 * @author finglauytao
 * @version 1.0.0
 *
 * 渐变线
 **/
class GradientLinesView : View {

    private val mColorArray = resources.getStringArray(R.array.gradient_lines_colors)
    private var mScreenWidth = 0
    private var mCurrentColorIndex = 0;
    private var mHandler = Handler();

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
        mCurrentColorIndex = MathUtil.randomInt(mColorArray.size - 1);
        var colorString = "";
        if (mCurrentColorIndex == 0) {
            colorString = mColorArray.get(0);
        } else {
            colorString = mColorArray.get(mCurrentColorIndex)
        }
        val rect = Rect();
        getWindowVisibleDisplayFrame(rect)
        mScreenWidth = rect.width();


        runAnim(colorString);
    }


    override fun onDraw(canvas: Canvas?) {
    }

    private fun runAnim(colorString: String) {
        val vAnim = makeColorAnim(colorString);
        vAnim.setDuration(1200)
        val anticipateInterpolator = AccelerateDecelerateInterpolator()
        vAnim.interpolator = anticipateInterpolator;
        vAnim.setTarget(this)
        vAnim.start();
    }

    private fun makeColorAnim(color: String): ValueAnimator {
        setBackgroundColor(Color.parseColor(color))
        val valueAnimator = ValueAnimator.ofInt(mScreenWidth)
        val valueListener = object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animValue = animation!!.animatedValue as Int;
                if (layoutParams != null) {
                    layoutParams.width = animValue;
                    requestLayout()
                }
            }

        }
        val animListener = object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                mCurrentColorIndex += 1;
                if (mCurrentColorIndex >= mColorArray.size) mCurrentColorIndex = 0;
                var colorString = "";
                if (mCurrentColorIndex == 0) {
                    colorString = mColorArray.get(0);
                } else {
                    colorString = mColorArray.get(mCurrentColorIndex)
                }
                mHandler.postDelayed(object :Runnable{
                    override fun run() {
                        runAnim(colorString);
                    }

                }, 500)
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }
        }
        valueAnimator.addUpdateListener(valueListener)
        valueAnimator.addListener(animListener);

        return valueAnimator;
    }


}