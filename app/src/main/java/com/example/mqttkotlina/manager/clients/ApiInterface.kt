package com.fg.mdp.fwgfacilitiesfinder.clients


import com.example.mqttkotlina.model.detect.request.json
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("v2detect.php")
    fun getDetect(@Body JSON: RequestBody) : Call<json>

     fun getSendDetect( @Body data: RequestBody)
    : Observable<Response<JsonObject>>

}