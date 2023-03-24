package com.example.smartparking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.smartparking.model.QueryResult
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "SmartPark"
    }

    fun onClickRefresh(view: View) {
        val plate = findViewById<EditText>(R.id.plate).text.toString()

        val queue = Volley.newRequestQueue(this)
        val url = "http://54.201.156.57:5000/query?plate=$plate"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response -> responseHandler(response) },
            { error -> errorHandler(error) }
        )
        queue.add(jsonObjectRequest)
    }

    private fun responseHandler(response: JSONObject) {
        val result = QueryResult(
            availability = response.optInt("availability"),
            availableSpots = response.optString("availableSpots"),
            customerType = response.optString("customerType"),
            currentSpotType = response.optString("currentSpotType"),
            currentSpot = response.optString("currentSpot"),
            startTime = response.optString("startTime"),
            totalParkingTime = response.optString("totalParkingTime"),
            timeRemaining = response.optString("timeRemaining"),
            warnings = response.optString("warnings")
        )
        findViewById<TextView>(R.id.availability).text = result.availability.toString()
        findViewById<TextView>(R.id.available_spots).text = result.availableSpots
        findViewById<TextView>(R.id.customer_type).text = result.customerType
        findViewById<TextView>(R.id.current_spot_type).text = result.currentSpotType
        findViewById<TextView>(R.id.current_spot).text = result.currentSpot
        findViewById<TextView>(R.id.start_time).text = result.startTime
        findViewById<TextView>(R.id.total_parking_time).text = result.totalParkingTime
        findViewById<TextView>(R.id.time_remaining).text = result.timeRemaining
        findViewById<TextView>(R.id.warnings).text = result.warnings
    }

    private fun errorHandler(error: VolleyError) {
        error.stackTrace
    }
}
