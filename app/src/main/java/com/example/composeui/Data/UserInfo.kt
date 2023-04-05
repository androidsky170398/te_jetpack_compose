package com.example.composeui.Data

import java.io.Serializable

class UserInfo : Serializable {
    private lateinit var picDate: String
    private lateinit var picTime: String
    fun getDate(): String? {
        return picDate
    }
    @JvmName("picDate")
    fun setDate(picDate: String?) {
        this.picDate = picDate!!
    }
    fun getTime(): String? {
        return picTime
    }
    @JvmName("picTime")
    fun setTime(picTime: String?) {
        this.picTime = picTime!!
    }
}