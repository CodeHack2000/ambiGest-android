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
import retrofit2.Response
import org.json.JSONObject
import android.util.Log

class AuthViewModel: ViewModel() {
    private val apiService = AmbiGestApi.retrofitService

    private var _authToken = MutableLiveData<String>()
    val authToken: LiveData<String> = _authToken

    private var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private var _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    private var _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    init {
        reinitializaData()
    }

    fun reinitializaData() {
        setEmail("")
        setPassword("")
        setUsername("Tester")
    }

    fun setUsername(value: String) {
        _username.value = value
    }

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun doLogin() {
        if(email.value?.isEmpty() == true || password.value?.isEmpty() == true){
            _messageError.postValue("Email and password are required!")

        }else {
            val loginRequest = LoginRequest(email.value!!, password.value!!)

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = apiService.postLogin(loginRequest)

                    if (response.code() == 200) {
                        val responseBody: ResponseBody? = response.body()
                        val json = responseBody?.string()

                        if (!json.isNullOrEmpty()) {
                            val jsonObject = JSONObject(json)
                            val authToken = jsonObject.optString("token")

                            _authToken.postValue(authToken)
                            _isLoggedIn.postValue(true)
                        }
                    }

                    if(response.code() == 400){
                        _messageError.postValue("Bad Request")
                    }
                    else {
                        _messageError.postValue("Server Error")
                    }
                } catch (e: Error) {
                    _messageError.postValue(e.message)
                }
            }
        }
    }
}