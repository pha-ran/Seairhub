package com.seairhub.driver.src.main.list.deliveryinfo

import android.os.Bundle
import android.view.View
import com.seairhub.driver.R
import com.seairhub.driver.config.BaseFragment
import com.seairhub.driver.databinding.FragmentDeliveryInfoBinding

class DeliveryInfoFragment : BaseFragment<FragmentDeliveryInfoBinding>(FragmentDeliveryInfoBinding::bind, R.layout.fragment_delivery_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        set()
    }

    private fun set() {

    }
}