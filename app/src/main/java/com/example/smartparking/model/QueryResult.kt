package com.example.smartparking.model

data class QueryResult(
    val availability: Int = 0,
    val availableSpots: String = "-",
    val customerType: String = "-",
    val currentSpotType: String = "-",
    val currentSpot: String = "-",
    val startTime: String = "-",
    val totalParkingTime: String = "-",
    val timeRemaining: String = "-",
    val warnings: String = ""
)
