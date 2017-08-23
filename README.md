# NothingLogin

### 使用Kotlin仿的Nothing的登录页面（练习）

### 1、Demo预览

![image](https://github.com/leiyun1993/NothingLogin/raw/master/screenshot/1.gif)

### 2、动画实现

```kotlin
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
```

### 3、下滑关闭登录或注册

```kotlin
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
                if (Math.abs(event.x - lastX) < 30 && event.y - lastY > 30) {       //向下滑动逻辑，
                    if (isShowing)
                        showOrHide(false)           //关闭登录或注册
                }
                Log.d("ACTION_UP", "x:${event.x},y:${event.y}")
            }
        }

        return true
    }

})
```