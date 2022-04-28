package com.example.gps_background_test

data class BaseResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)