package com.finglauytao.superfly.util

import android.app.Activity
import android.content.Intent

import com.finglauytao.superfly.MainActivity

/**
 * Create on 2018/6/25
 *
 * @author finglauytao
 * @version 1.0.0
 */
object MIntentUtil {

    /**
     * 打开主页
     */
    fun openMainActivity(activity: Activity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }



}
