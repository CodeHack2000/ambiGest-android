package com.example.ambigest.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ambigest.network.AmbiGestApi
import com.example.ambigest.network.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject

class AuthViewModel: ViewModel() {
    private val apiService = AmbiGestApi.retrofitService

    private var _authToken = MutableLiveData<String>()
    val authToken: LiveData<String> = _authToken

    private var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    init {
        reinitializaData()
    }

    fun reinitializaData() {
        setEmail("")
        setPassword("")
    }

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun doLogin() {
        if (email.value?.isNotEmpty() == true && password.value?.isNotEmpty() == true) {
            val loginRequest = LoginRequest(email.value!!, password.value!!)

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = apiService.postLogin(loginRequest)
                    if (response.code() == 200) {
                        val responseBody: ResponseBody? = response.body()
                        val json = responseBody?.string()

                        if (!json.isNullOrEmpty()) {
                            val jsonObject = JSONObject(json)
                            _authToken.value = jsonObject.optString("token")

                            _isLoggedIn.value = true
                        }
                    } else {
                        _isLoggedIn.value = false
                    }
                } catch (e: Error) {
                    _isLoggedIn.value = false
                    e.printStackTrace()
                }
            }
        }
    }
}