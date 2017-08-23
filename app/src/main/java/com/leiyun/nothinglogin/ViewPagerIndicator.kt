package com.leiyun.nothinglogin

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import java.util.ArrayList

class ViewPagerIndicator(context: Context, viewPager: ViewPager, dotLayout: LinearLayout, private val size: Int) : ViewPager.OnPageChangeListener {
    private val img1 = R.mipmap.ic_point_select
    private val img2 = R.mipmap.ic_point_normal
    private val imgSize = 48
    private val dotViewLists = ArrayList<ImageView>()

    init {
        viewPager.addOnPageChangeListener(this)
        for (i in 0..size - 1) {
            val imageView = ImageView(context)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            //为小圆点左右添加间距
            params.leftMargin = 10
            params.rightMargin = 10
            //手动给小圆点一个大小
            params.height = imgSize
            params.width = imgSize
            if (i == 0) {
                imageView.setBackgroundResource(img1)
            } else {
                imageView.setBackgroundResource(img2)
            }
            //为LinearLayout添加ImageView
            dotLayout.addView(imageView, params)
            dotViewLists.add(imageView)
        }

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        for (i in 0..size - 1) {
            //选中的页面改变小圆点为选中状态，反之为未选中
            if (position % size == i) {
                dotViewLists[i].setBackgroundResource(img1)
            } else {
                dotViewLists[i].setBackgroundResource(img2)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}  