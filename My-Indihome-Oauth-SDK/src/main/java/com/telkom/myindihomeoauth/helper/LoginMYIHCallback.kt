package com.telkom.myindihomeoauth.helper

interface LoginMYIHCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(message: T)
}