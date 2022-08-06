package com.mohamed_ramadan.code.cairoweather

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    lateinit var UpdateAt:TextView
    lateinit var Sky:TextView
    lateinit var Degree:TextView
    lateinit var MacTemp:TextView
    lateinit var MinTemp:TextView
    lateinit var Wind:TextView
    lateinit var SunRise:TextView
    lateinit var SunSet:TextView
    lateinit var Preasure:TextView
    lateinit var Humidity:TextView




    val CITY="Cairo"
    val ID="b8fdfddb568c4ba4847223546220905"
    val API ="https://api.weatherapi.com/v1/current.json?key=b8fdfddb568c4ba4847223546220905 &q=$CITY&aqi=no"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UpdateAt =findViewById(R.id.updated_at)
        Sky =findViewById(R.id.status)
        Degree = findViewById(R.id.temp)
        MacTemp = findViewById(R.id.temp_min)
        MinTemp =findViewById(R.id.temp_max)
        Wind =findViewById(R.id.wind)
        SunRise =findViewById(R.id.sunrise)
        SunSet =findViewById(R.id.sunset)
        Preasure =findViewById(R.id.pressure)
        Humidity =findViewById(R.id.humidity)

        GetWeather()



    }


    private fun GetWeather(){


        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.weatherapi.com/v1/forecast.json?key=b8fdfddb568c4ba4847223546220905 &q=cairo&days=1&aqi=no&alerts=no"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->

                val result=""
                val degree= JSONObject(response)


                val current =degree.getJSONObject("current")
                val icon=current.getJSONObject("condition").getString("icon")
                val temp_c=current.getString("temp_c")
                val condition=current.getJSONObject("condition")
                val sky=condition.getString("text")

                val wind_degree =current.getString("wind_degree")
                val pressure_in=current.getString("pressure_in")
                val humidity=current.getString("humidity")

                val forecast=degree.getJSONObject("forecast")
                val forecastday=forecast.getJSONArray("forecastday").getJSONObject(0)

               val day=forecastday.getJSONObject("day")

                val maxtemp=day.getString("maxtemp_c")
                val mintemp=day.getString("mintemp_c")

                val astro=forecastday.getJSONObject("astro")
                val sunrise=astro.getString("sunrise")
                val sunset=astro.getString("sunset")

                val last_updated=current.getString("last_updated")



                UpdateAt.setText(last_updated)
                Degree.setText(temp_c)
                Sky.setText(sky)
                Wind.setText(wind_degree)
                Preasure.setText(pressure_in)
                Humidity.setText(humidity)
                MacTemp.setText("MaxTemp "+maxtemp)
                MinTemp.setText("MinTemp "+mintemp)
                SunRise.setText(sunrise)
                SunSet.setText(sunset)
                // Display the first 500 characters of the response string.
                Toast.makeText(this,temp_c,Toast.LENGTH_SHORT).show()
            },
            {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)


//        val queue=Volley.newRequestQueue(this)
//
//        val stringrequest = StringRequest(Request.Method.GET,API,
//            {response->
//
//                val json=JSONObject(response)
//
//                //get current object
//                val current=json.getJSONObject("current")
//
//                //get values
//                val temp=current.getString("temp_c")
//
//              //  Sky .setText(status)
//                Degree.setText(temp)
//               // MacTemp.setText(maxtemp)
//             //   MinTemp .setText(mintemp)
//               // Wind .setText(wind)
//                //SunRise.setText(sunrise)
//              //  SunSet .setText(sunset)
//              //  Preasure .setText(preasure)
//             //   Humidity .setText(humedity)
//
//
//
//
//
//
//
//        },
//            {
//                Toast.makeText(this,"Error"+it.message, Toast.LENGTH_LONG).show()
//            })
//
//        queue.add(stringrequest)

    }

}