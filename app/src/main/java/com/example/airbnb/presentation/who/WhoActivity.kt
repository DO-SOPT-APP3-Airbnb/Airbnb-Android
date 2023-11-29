package com.example.airbnb.presentation.who

import android.graphics.Paint
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.databinding.ActivityWhoBinding

class WhoActivity : BindingActivity<ActivityWhoBinding>(R.layout.activity_who) {

    lateinit var whoViewModel: WhoViewModel

    override fun initView() {
        whoViewModel = ViewModelProvider(this).get(WhoViewModel::class.java)

        binding.itemWhoPet.tvWhoDescription.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        setCountObserver(
            "adult",
            binding.itemWhoAdult.tvWhoCount,
            binding.itemWhoAdult.ibWhoCountIncrease,
            binding.itemWhoAdult.ibWhoCountDecrease
        )
        setCountObserver(
            "kids",
            binding.itemWhoKids.tvWhoCount,
            binding.itemWhoKids.ibWhoCountIncrease,
            binding.itemWhoKids.ibWhoCountDecrease
        )
        setCountObserver(
            "toddler",
            binding.itemWhoToddler.tvWhoCount,
            binding.itemWhoToddler.ibWhoCountIncrease,
            binding.itemWhoToddler.ibWhoCountDecrease
        )
        setCountObserver(
            "pet",
            binding.itemWhoPet.tvWhoCount,
            binding.itemWhoPet.ibWhoCountIncrease,
            binding.itemWhoPet.ibWhoCountDecrease
        )
    }

    private fun setCountObserver(
        type: String,
        countTextView: TextView,
        increaseButton: ImageButton,
        decreaseButton: ImageButton
    ) {
        whoViewModel.getCount(type).observe(this, Observer {
            countTextView.text = it.toString()
        })

        increaseButton.setOnClickListener {
            whoViewModel.increaseCount(type)
        }

        decreaseButton.setOnClickListener {
            whoViewModel.decreaseCount(type)
        }
    }
}