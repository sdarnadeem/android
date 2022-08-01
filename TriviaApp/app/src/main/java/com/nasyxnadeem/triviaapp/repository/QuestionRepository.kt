package com.nasyxnadeem.triviaapp.repository

import com.nasyxnadeem.triviaapp.data.DataOrException
import com.nasyxnadeem.triviaapp.model.QuestionItem
import com.nasyxnadeem.triviaapp.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionAPI

) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()

            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }
        } catch (e: Exception) {
            dataOrException.loading = false
            dataOrException.e = e
        }
        return dataOrException
    }


}