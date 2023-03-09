package com.example.myapplicationretrofit.model

import com.example.myapplicationretrofit.model.level2.Info
import com.example.myapplicationretrofit.model.level2.Result

data class SearchCharacterResponse(
    val info: Info,
    val results: List<Result>
)
