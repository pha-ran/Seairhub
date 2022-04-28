package com.example.gps_background_test

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                        val lat_ = location_.latitude
                        val lon_ = location_.longitude

                        val postInterface = MainActivity.sRetrofit.create(postInterface::class.java)
                        postInterface.post(locationData(
                            lat_,
                            lon_
                        )).enqueue(object : Callback<BaseResponse> {
                            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                                println("1000")
                            }

                            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                                println("4000")
                            }
                        })

                        println("1000 lat : $lat_, lon : $lon_")
                    }
                } else {
                    println("3000 fail")
                }
            }
        } catch (err : SecurityException) {
            println("4000 SecurityException")
        }
    }


    fun onPostLocationSuccess(response : BaseResponse) {
        println(1000)
    }

    fun onPostLocationFailure(message: String) {
        println(4000)
    }
}