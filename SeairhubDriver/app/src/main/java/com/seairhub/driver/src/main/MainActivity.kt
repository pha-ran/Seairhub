package com.seairhub.driver.src.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.seairhub.driver.R
import com.seairhub.driver.config.BaseActivity
import com.seairhub.driver.databinding.ActivityMainBinding
import com.seairhub.driver.src.main.list.ListFragment
import com.seairhub.driver.src.main.list.models.ListItemData
import com.seairhub.driver.src.main.myinfo.MyInfoFragment
import com.seairhub.driver.src.main.notifications.NotificationsFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        set()
    }

    fun replaceFragment(F : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl, F).commitAllowingStateLoss()
    }

    private fun set() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bnv_menu_list -> {
                    replaceFragment(ListFragment())
                    true
                }
                R.id.bnv_menu_notifications -> {
                    replaceFragment(NotificationsFragment())
                    true
                }
                R.id.bnv_menu_person -> {
                    replaceFragment(MyInfoFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

        binding.bnvMain.selectedItemId = R.id.bnv_menu_list

        setSupportActionBar(binding.toolbar)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_tb_back)
    }
}