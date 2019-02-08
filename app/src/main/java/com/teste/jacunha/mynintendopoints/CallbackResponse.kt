package com.teste.jacunha.mynintendopoints

interface CallbackResponse<T> {
    fun success(response: T)
}