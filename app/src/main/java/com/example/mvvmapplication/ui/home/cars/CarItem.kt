package com.example.mvvmapplication.ui.home.cars

import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.db.entities.Car
import com.example.mvvmapplication.databinding.ItemCarBinding
import com.xwray.groupie.databinding.BindableItem

class CarItem(
   private val car: Car
) : BindableItem<ItemCarBinding>() {
    override fun bind(viewBinding: ItemCarBinding, position: Int) {
        viewBinding.car = car
    }

    override fun getLayout() = R.layout.item_car
}