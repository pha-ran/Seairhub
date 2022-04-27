package com.example.gps_background_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: ViewModel
    private lateinit var workManager: WorkManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        activity?.let {
            workManager = WorkManager.getInstance(it)
        }

        viewModel.startWorkRequests(workManager)

        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}