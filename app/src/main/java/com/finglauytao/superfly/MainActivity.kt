package com.finglauytao.superfly

import android.support.v4.view.ViewPager
import com.finglauytao.superfly.adapter.MainPagerAdapter
import com.finglauytao.superfly.frag.FragCamera
import com.finglauytao.superfly.frag.FragRecorder
import com.finglauytao.zlib.base.ZBaseFragment
import com.finglauytao.zlib.base.ZBaseFragmentActivity

class MainActivity : ZBaseFragmentActivity() {

    override fun initLayout(): Int {
        return R.layout.activity_main;
    }

    override fun initView() {
        val fragList = arrayListOf<ZBaseFragment>()
        val fragCamera = FragCamera();
        val fragRecorder = FragRecorder();

        //Testing
        val fragTest = FragTest();
        fragList.add(fragTest)


        fragList.add(fragCamera)
        fragList.add(fragRecorder)
        val pageAdapter = MainPagerAdapter(supportFragmentManager, fragList);
        val viewPager: ViewPager = findViewById(R.id.main_view_pager)
        viewPager.adapter = pageAdapter
    }

    private fun initData() {
//        var zBaseModel = ViewModelProviders.of(this).get(ZBaseModel.class as Java)
    }


}
