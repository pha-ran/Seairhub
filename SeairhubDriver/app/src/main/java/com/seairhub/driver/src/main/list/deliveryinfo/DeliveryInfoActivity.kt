package com.seairhub.driver.src.main.list.deliveryinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.seairhub.driver.R
import com.seairhub.driver.config.BaseActivity
import com.seairhub.driver.databinding.ActivityDeliveryInfoBinding

class DeliveryInfoActivity : BaseActivity<ActivityDeliveryInfoBinding>(ActivityDeliveryInfoBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        set()

        replaceFragment(DeliveryInfoFragment())
    }

    private fun replaceFragment(F : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl, F).commitAllowingStateLoss()
    }

    private fun set() {

    }
}