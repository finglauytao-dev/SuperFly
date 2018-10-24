package com.finglauytao.superfly

import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.finglauytao.superfly.util.MIntentUtil
import com.finglauytao.zlib.base.ZBaseActivity
import com.finglauytao.zlib.net.ResponseData
import com.finglauytao.zlib.net.ZNetCallBack
import com.finglauytao.zlib.net.ZNetManager
import okhttp3.Response
import org.json.JSONObject
import java.lang.Exception

/**
 * Create on 2018/6/21
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class SplashActivity : ZBaseActivity() , ZNetCallBack {

    override fun onSuccess(response: Response?, responseData: ResponseData?, data: String?) {
        Log.e("1111111111111111", data)
    }

    override fun onFailed(responseData: ResponseData?, errMsg: String?, e: Exception?) {
        Log.e("1111111111111111", errMsg)
    }

    override fun iniLayout(): Int {
        return R.layout.activity_splash
    }


    override fun initView() {
        loadAnim();
        openMainActivityDelayed();


        //TEST  http://apppre.fotilestyle.com:666/fotile-api-0.0.2/index/greatest/v430
        val testUrl = "http://apppre.fotilestyle.com:666/fotile-api-0.0.2/index/greatest/v430";
        val parmers = JSONObject();
        parmers.put("parmers", "513087")


        ZNetManager.getInstance().postEnqueueRequest(testUrl, parmers.toString(), lifecycle, this)
    }

    private fun loadAnim() {
        val contentView = findViewById<View>(R.id.splash_content_view)
        val inAnim = AnimationUtils.loadAnimation(this, R.anim.splash_in_anim);
        contentView.startAnimation(inAnim)
    }

    override fun onBackPressed() {
        //Do nothing
    }

    fun openMainActivityDelayed() {
//        val handler = Handler();
//        handler.postDelayed(Runnable {
//            MIntentUtil.openMainActivity(this)
//            finish()
//        }, 3000);

    }

}