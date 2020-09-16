package com.gmail.redballtoy.FotoOfDayNasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Error

class MainActivity : AppCompatActivity() {

    private val retrofitImpl: RetrofitImpl = RetrofitImpl()
    private val api_key: String = "yY7C5lRllfsLe81634NAJ1PWBDEfDRyvt2KBMLkO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //send request to server
        sendServerRequst()

    }

    private fun sendServerRequst() {
        retrofitImpl.getRetrofit().getPictureOfTheDay(api_key)
            .enqueue(object : Callback<DataModel> { //asynchronous data request on the internet

                //received a response from the server in the form DataModel
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    //checking if the response is successful (no errors)
                    // and if there is data in the response
                    if (response.isSuccessful && response.body() != null) {
                        renderData(response.body(), null)
                    } else {
                        renderData(null, Throwable("Ответ от сервера пустой"))
                    }
                }

                //will be called if the request fails
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    renderData(null, t)
                }
            })
    }

    private fun renderData(dataModel: DataModel?, error: Throwable?) {
        if (dataModel == null || error != null) {
            //Error
        } else {
            val url: String? = dataModel.url
            tv_explanation.text=url

            }
        }

}


//data model into which we will convert json server response
data class DataModel(
    val explanation: String?,
    val hdurl: String?,
    val media_type: String?,
    val title: String?,
    val url: String?
)

//description of the server query interface
//https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY
interface PictureOfTheDayAPI {
    @GET("planetary/apod") //format of query
    //pass the key as an argument api_key=key_devekjper, and returns data model DataModel
    fun getPictureOfTheDay(@Query("api_key") api_key: String): Call<DataModel>
}

//implementation of retrofit class
//create query and send to server
class RetrofitImpl {

    fun getRetrofit(): PictureOfTheDayAPI {
        //a method that returns our interface
        val podRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(
                //will form a class from the json response that will receive from the server
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build() //create an instance of the retrofit class
        //we indicate that we need to create this class for java
        return podRetrofit.create(PictureOfTheDayAPI::class.java)
    }
}