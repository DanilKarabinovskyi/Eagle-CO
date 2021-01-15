package com.example.eaglecompany.models

data class Worker(
    val success: Boolean,
    val errors: Any,
    val `data`: List<Data>
)

data class Data(
    val avatar: String,
    val name: String,
    val numberOfOrders: Int,
    val numberOfOrdersComplete: Int,
    val ratingPunctuality: Double,
    val ratingSpeed: Double,
    val services: List<Service>,
    val uid: String,
    val workSchedule: WorkSchedule
)

data class Service(
    val id: String,
    val label: String,
    val number: Int
)

data class WorkSchedule(
    val dayOfWeek: List<Int>,
    val endTime: String,
    val startTime: String
)