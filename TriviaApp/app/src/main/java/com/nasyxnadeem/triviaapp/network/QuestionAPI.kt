package com.nasyxnadeem.triviaapp.network

import com.nasyxnadeem.triviaapp.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionAPI {
    @GET("world.json")
    suspend fun getAllQuestions(): Question
}