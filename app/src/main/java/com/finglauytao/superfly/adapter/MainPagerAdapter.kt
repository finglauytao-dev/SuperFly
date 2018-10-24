package com.finglauytao.superfly.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.view.ViewGroup
import com.finglauytao.superfly.util.Constant
import com.finglauytao.zlib.base.ZBaseFragment

/**
 * Create on 2018/6/25
 *
 * @author finglauytao
 * @version 1.0.0
 **/
class MainPagerAdapter(fm: FragmentManager, frags: ArrayList<ZBaseFragment>) : FragmentStatePagerAdapter(fm) {

    private var mFragList = ArrayList<ZBaseFragment>()

    init {
        mFragList = frags
    }

    override fun getCount(): Int {
        return mFragList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragList.get(position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        Log.e(Constant.TAG, "MainPagerAdapter position==" + position + "  destory")
        super.destroyItem(container, position, `object`)
    }

}