package com.nasyxnadeem.triviaapp.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.PathEffect

import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nasyxnadeem.triviaapp.TriviaApplication_GeneratedInjector
import com.nasyxnadeem.triviaapp.model.QuestionItem
import com.nasyxnadeem.triviaapp.screens.QuestionsViewModel
import com.nasyxnadeem.triviaapp.util.AppColors

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

    val questionIndexState = remember {
        mutableStateOf(0)
    }

    if (viewModel.data.value.loading == true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            CircularProgressIndicator(modifier = Modifier.size(70.dp))
        }
    } else {
        val question = try {
            questions?.get(questionIndexState.value)
        } catch (ex: Exception) {
            null
        }

        if (question != null) {
            SingleQuestion(
                question = questions?.get(questionIndexState.value),
                questionIndex = questionIndexState,
                viewModel = viewModel,
                onNextClicked = {
                    questionIndexState.value++
                })
        }
    }
}

//@Preview
@Composable
fun SingleQuestion(
    question: QuestionItem?,
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
    onNextClicked: () -> Unit

) {


    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val outOf = viewModel.data.value.data?.size

    Surface(modifier = Modifier.fillMaxSize(), color = AppColors.mDarkPurple) {

        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            QuestionTracker(counter = questionIndex.value + 1, outOf = outOf)
            DottedLine(pathEffect)
            QuestionDetails(
                question = question,
                onNextClicked = onNextClicked
            )
        }
    }

}

@Composable
@Preview
fun QuestionTracker(counter: Int = 10, outOf: Int? = 100) {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = AppColors.mLightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp
                )
            ) {
                append("Question $counter/")
                withStyle(
                    style = SpanStyle(
                        color = AppColors.mLightGray,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                )
                {
                    append(outOf.toString())
                }
            }

        }
    }, modifier = Modifier.padding(20.dp))
}


@Composable
fun DottedLine(pathEffect: PathEffect) {
    Canvas(modifier = Modifier.fillMaxWidth().height(1.dp)) {
        drawLine(
            color = AppColors.mLightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, y = 0f),
            pathEffect = pathEffect
        )
    }
}

@Composable
fun QuestionDetails(
    question: QuestionItem?,
    onNextClicked: () -> Unit
) {
    val choicesState = remember(question) {
        question?.choices?.toMutableList()
    }
    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }
    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState?.get(it) == question?.answer
        }
    }

    Column {
        Text(
            text = question?.question.toString(),
            modifier = Modifier.padding(6.dp)
                .align(alignment = Alignment.Start)
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp,
            color = AppColors.mOffWhite
        )

        choicesState?.forEachIndexed { index, answerText ->
            Row(
                modifier = Modifier.padding(3.dp).fillMaxWidth().height(45.dp).border(
                    width = 4.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AppColors.mOffDarkPurple,
                            AppColors.mOffDarkPurple
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                ).clip(
                    RoundedCornerShape(
                        topStartPercent = 50,
                        topEndPercent = 50,
                        bottomEndPercent = 50,
                        bottomStartPercent = 50
                    )
                ).background(
                    Color.Transparent
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(

                    selected = (answerState.value == index), onClick = {
                        onNextClicked()
                        updateAnswer(index)
                    }, modifier = Modifier.padding(start = 16.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                )
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Light,
                            color = if (correctAnswerState.value == true && index == answerState.value) {
                                Color.Green
                            } else if (correctAnswerState.value == false && index == answerState.value) {
                                Color.Red
                            } else {
                                AppColors.mOffWhite
                            },
                            fontSize = 17.sp
                        )
                    ) {
                        append(answerText)
                    }
                }
                Text(text = annotatedString, modifier = Modifier.padding(6.dp))

            }
        }
        Button(modifier = Modifier.padding(3.dp).align(alignment = Alignment.CenterHorizontally),
            shape = RoundedCornerShape(34.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColors.mLightBlue
            ),
            onClick = {
                onNextClicked()

            }) {
            Text(
                text = "Next",
                modifier = Modifier.padding(4.dp),
                color = AppColors.mOffWhite,
                fontSize = 17.sp
            )
        }
    }
}