package com.leiyun.nothinglogin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginBtn.setOnClickListener {

            startActivity<NothingLoginActivity>()
        }
        toolBar.title = resources.getString(R.string.app_name)
        toolBar.backgroundColor = resources.getColor(R.color.colorPrimary)
    }
}
