package com.nasyxnadeem.triviaapp.component

import android.util.Log
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nasyxnadeem.triviaapp.screens.QuestionsViewModel

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
    } else {
        Log.d("Loading", "Questions: ...Loading Stopped")
        questions?.forEach {
            Log.d("Question", "Questions: ...${it.question}")

        }
    }
}

@Preview
@Composable
fun SingleQuestion() {

}