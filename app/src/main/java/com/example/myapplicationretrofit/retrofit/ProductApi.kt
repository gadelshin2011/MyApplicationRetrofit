package com.example.myapplicationretrofit.retrofit

import com.example.myapplicationretrofit.model.Product
import com.example.myapplicationretrofit.model.SearchCharacterResponse
import com.example.myapplicationretrofit.model.level2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.net.IDN

interface ProductApi {
    @GET("/api/episode/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("/api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int):Result

}