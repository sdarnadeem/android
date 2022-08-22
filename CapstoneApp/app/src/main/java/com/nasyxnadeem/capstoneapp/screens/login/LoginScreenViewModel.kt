package com.nasyxnadeem.capstoneapp.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.nasyxnadeem.capstoneapp.model.MUser
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    //    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading




    fun signInWithEmailAndPassword(email: String, password: String, home:() -> Unit) = viewModelScope.launch {

        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        home()
                    } else {
                        Log.d("login", "signInWithEmailAndPassword: ${"something went wrong"}")
                    }
                }
        } catch (e: Exception) {
            Log.d("User Name", "createUserWithEmailAndPassword: ${e.message}")
        }
    }



    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        if (_loading.value ==false) {
            _loading.value = true

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        val displayName = it.result?.user?.email?.split("@")?.get(0)
                        createUser(displayName)

                        home()
                    } else {
                        Log.d("TAG", "createUserWithEmailAndPassword: ")
                    }
                    _loading.value = false;
                }
        }

    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(userId = userId.toString(), displayName = displayName.toString(), avatarUrl = "", quote = "Life's great", profession = "Android Developer", id = null).toMap()


        FirebaseFirestore.getInstance().collection("users").add(user)
    }
}


