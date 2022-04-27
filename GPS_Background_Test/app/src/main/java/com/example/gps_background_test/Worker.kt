package com.example.gps_background_test

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Worker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val context_ = context
    lateinit var location : FusedLocationProviderClient

    override fun doWork() : Result {
        return try {
            checkDistance()
            Result.success()
        } catch (err : Exception) {
            Result.failure()
        }
    }

    private fun checkDistance() {
        try {
            location = LocationServices.getFusedLocationProviderClient(context_)
            location.lastLocation.addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    it.result?.let { location_ ->
                        val lat = location_.latitude
                        val lon = location_.longitude

                        println("1000 lat : $lat, lon : $lon")
                    }
                } else {
                    println("3000 fail")
                }
            }
        } catch (err : SecurityException) {
            println("4000 SecurityException")
        }
    }
}