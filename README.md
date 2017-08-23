# NothingLogin

### 使用Kotlin仿的Nothing的登录页面（练习）

### Demo预览

![image](https://github.com/leiyun1993/NothingLogin/raw/master/screenshot/1.gif)

### 动画实现

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