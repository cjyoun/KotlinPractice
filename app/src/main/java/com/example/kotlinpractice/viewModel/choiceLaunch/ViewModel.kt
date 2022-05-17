package com.example.kotlinpractice.viewModel.choiceLaunch

import android.util.Log
import androidx.databinding.BaseObservable
import com.example.kotlinpractice.model.choiceLaunch.Database
import com.example.kotlinpractice.model.choiceLaunch.Database.DatabaseListener

class ViewModel : BaseObservable {

    private var database: Database
    private lateinit var selector: String

    // 생성자
    constructor(database: Database){
        this.database = database

        this.database.setOnDatabaseListener(object : DatabaseListener {
            override fun onChanged() {
                selector = ""
                selector = database.getSelector()
                notifyChange()
            }
        })

    }

    fun getUser(){
        Log.d("ViewModel getUser - " , "DB에게 user(selector)를 달라고 요청")
        database.getUser()
    }

    fun getSelector(): String{
        Log.d("VM getSelector - " , "selector 반환 $selector")
        return selector
    }

}