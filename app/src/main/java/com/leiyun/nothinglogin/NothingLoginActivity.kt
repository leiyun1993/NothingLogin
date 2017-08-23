package com.leiyun.nothinglogin

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_nothing_login.*
import kotlinx.android.synthetic.main.layout_login.*
import org.jetbrains.anko.imageResource


fun Activity.getScreenWidth(): Int = this.resources.displayMetrics.widthPixels


fun Activity.getScreenHeight(): Int = this.resources.displayMetrics.heightPixels

class NothingLoginActivity : AppCompatActivity() {

    var isShowing: Boolean = false
    var isSelectLogin: Boolean = true

    private val images = intArrayOf(R.mipmap.image1, R.mipmap.image2, R.mipmap.image3)
    val imageViews: MutableList<ImageView> = ArrayList(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_nothing_login)
        for (resID in images) {
            val imageView = ImageView(this)
            val params = ViewPager.LayoutParams()
            imageView.layoutParams = params
            imageView.imageResource = resID
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setOnClickListener {
                if (isShowing) {
                    hindSoftInput()
                    showOrHide(false)
                }
            }
            imageViews.add(imageView)
        }
        viewPager.adapter = MyPageAdapter()
        ViewPagerIndicator(this, viewPager, dotLayout, imageViews.size)
        lookBtn.setOnClickListener {
            onBackPressed()
        }
        loginBtn.setOnClickListener {
            if (!isShowing) {
                if (!isSelectLogin) {
                    showLoginOrRegister(true)
                }
                showOrHide(true)
            } else {
                if (!isSelectLogin) {
                    showLoginOrRegister(true)
                }
            }
        }
        registerBtn.setOnClickListener {
            if (!isShowing) {
                if (isSelectLogin) {
                    showLoginOrRegister(false)
                }
                showOrHide(true)
            } else {
                if (isSelectLogin) {
                    showLoginOrRegister(false)
                }
            }
        }
        loginBottomLayout.setOnTouchListener(object : View.OnTouchListener {
            var lastX: Float = -1F
            var lastY: Float = -1F
            override fun onTouch(view: View?, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastY = event.y
                        lastX = event.x
                        Log.d("ACTION_DOWN", "x:$lastX,y:$lastY")
                    }
                    MotionEvent.ACTION_UP -> {
                        if (Math.abs(event.x - lastX) < 30 && event.y - lastY > 30) {
                            if (isShowing)
                                showOrHide(false)
                        }
                        Log.d("ACTION_UP", "x:${event.x},y:${event.y}")
                    }
                }

                return true
            }

        })
    }

    private fun hindSoftInput() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive && currentFocus != null) {
            if (currentFocus!!.windowToken != null) {
                imm.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

    private fun showLoginOrRegister(isLogin: Boolean) {
        isSelectLogin = isLogin
        loginTag.visibility = if (isLogin) View.VISIBLE else View.INVISIBLE
        registerTag.visibility = if (!isLogin) View.VISIBLE else View.INVISIBLE
        loginLayout.visibility = if (isLogin) View.VISIBLE else View.INVISIBLE
        registerLayout.visibility = if (!isLogin) View.VISIBLE else View.INVISIBLE
    }


    fun showOrHide(isShow: Boolean) {
        isShowing = isShow
        val valueAnimator: ValueAnimator = if (isShow) {
            ValueAnimator.ofInt(0, (getScreenHeight() * 2f / 3f).toInt())
        } else {
            ValueAnimator.ofInt((getScreenHeight() * 2f / 3f).toInt(), 0)
        }
        valueAnimator.addUpdateListener { a ->
            val value = a.animatedValue as Int
            val params = loginBottomLayout.layoutParams
            params.height = value
            loginBottomLayout.layoutParams = params
            Log.d("addUpdateListener", value.toString())
        }
        valueAnimator.duration = 500
        valueAnimator.setTarget(loginBottomLayout)
        valueAnimator.start()
    }

    inner class MyPageAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(imageViews[position])
            return imageViews[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
            container.removeView(imageViews[position])
        }

        override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

        override fun getCount(): Int = imageViews.size

    }
}
