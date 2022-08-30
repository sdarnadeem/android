package com.nasyxnadeem.capstoneapp.screens.login

import EmailInput
import PasswordInput
import ReaderLogo
import SubmitButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nasyxnadeem.capstoneapp.navigation.ReaderScreens

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginScreenViewModel = viewModel()) {
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ReaderLogo()
            if (showLoginForm.value) {
                UserForm(loading = false, isCreateAccount = false) { email, password ->

                   viewModel.signInWithEmailAndPassword(email, password) {
                       navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                   }
                }
            } else {
                UserForm(
                    loading = false,
                    isCreateAccount = true
                ) { email, password ->
                    viewModel.createUserWithEmailAndPassword(email, password) {
                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text1 = if (showLoginForm.value) "Sign Up" else "Login"
                val text2 = if (showLoginForm.value) "New User" else "Already have an account"

                Text(text = text2)
                Text(
                    text = text1, modifier = Modifier.clickable {
                        showLoginForm.value = !showLoginForm.value
                    }.padding(start = 5.dp), fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant
                )
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email, password -> }
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }

    val password = rememberSaveable {
        mutableStateOf("")
    }

    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }

    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier =
        Modifier.height(250.dp).background(MaterialTheme.colors.background).verticalScroll(
            rememberScrollState()
        )

    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (isCreateAccount) {
            Text(text = "Please enter a valid email and password")
        }
        EmailInput(
            modifier = Modifier,
            emailState = email,
            enabled = !loading,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            }
        )

        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
            }
        )

        SubmitButton(
            textId = if (isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }

    }
}



