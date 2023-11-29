package com.example.airbnb.presentation.who

import android.graphics.Paint
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.databinding.ActivityWhoBinding
import com.example.airbnb.databinding.ItemWhoCountBinding

class WhoActivity : BindingActivity<ActivityWhoBinding>(R.layout.activity_who) {

    private val whoViewModel by viewModels<WhoViewModel>()

    override fun initView() {
        binding.itemWhoPet.tvWhoDescription.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        setCountObserver("adult", binding.itemWhoAdult)
        setCountObserver("kids", binding.itemWhoKids)
        setCountObserver("toddler", binding.itemWhoToddler)
        setCountObserver("pet", binding.itemWhoPet)
    }

    private fun setCountObserver(
        type: String,
        binding: ItemWhoCountBinding
    ) {
        binding.apply {
            whoViewModel.getCount(type).observe(this@WhoActivity) {
                tvWhoCount.text = it.toString()
            }

            ibWhoCountIncrease.setOnClickListener {
                whoViewModel.increaseCount(type)
            }

            ibWhoCountDecrease.setOnClickListener {
                whoViewModel.decreaseCount(type)
            }
        }
    }
}