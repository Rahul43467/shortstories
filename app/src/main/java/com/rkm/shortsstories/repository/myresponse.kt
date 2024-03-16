package com.rkm.shortsstories.repository

sealed  class myresponse<T> (val data:T?=null,
                             val errorMessage: String? = null,
                             val progress: Int = 0){

    class Loading<T>(private val mProgress: Int = 0) : myresponse<T>(progress = mProgress)

    class Success<T>(private val mData: T?) : myresponse<T>(data = mData)

    class Error<T>(private val mErrMessage: String?) : myresponse<T>(errorMessage = mErrMessage)
}