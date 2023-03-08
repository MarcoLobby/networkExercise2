package com.project1.networkexercise2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    fun setDetails(data: Data) {

        var getTextView = findViewById<TextView>(R.id.tv_Dog)

        getTextView.text = getString(R.string.String1, data.message)


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        logging.level = HttpLoggingInterceptor.Level.BODY

        coroutine()

    }

    val logging = HttpLoggingInterceptor()
    val authorizationInterceptor = AuthorizationInterceptor()
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authorizationInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://dog.ceo")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            val apiService = retrofit.create(ApiInterface.ApiServiceInterface::class.java)


    fun coroutine() {

        lifecycleScope.launch {
            try {
                val details = apiService.getData()

                setDetails(details)


            } catch (e: Exception) {

                Log.d("MainActivity", "Error ${e.message}")
            }
        }
    }

}

