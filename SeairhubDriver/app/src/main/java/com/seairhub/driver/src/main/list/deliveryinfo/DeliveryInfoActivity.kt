package com.seairhub.driver.src.main.list.deliveryinfo

import android.os.Bundle
import android.view.MenuItem
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(F : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl, F).commitAllowingStateLoss()
    }

    private fun set() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}