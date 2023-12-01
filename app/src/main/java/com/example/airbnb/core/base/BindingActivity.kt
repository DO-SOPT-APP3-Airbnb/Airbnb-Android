package com.example.airbnb.core.base

import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.airbnb.core.util.context.hideKeyboard

abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
) : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        if(Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
        initView()
    }

    protected abstract fun initView()

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus ?: View(this))
        return super.dispatchTouchEvent(ev)
    }
}