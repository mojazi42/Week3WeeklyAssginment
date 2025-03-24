package com.example.countrycityexplorer.data.model

data class ApiResponse<T>(
    val error: Boolean,
    val msg: String,
    val data: T
)
