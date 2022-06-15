package com.example.kotlinpractice.choicelunch.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable
import com.example.kotlinpractice.choicelunch.model.PersonDatabase
import com.example.kotlinpractice.choicelunch.model.PersonDatabase.DatabaseListener
import com.example.kotlinpractice.choicelunch.model.Person

class ViewModel : BaseObservable {

    private var personDatabase: PersonDatabase
    private lateinit var selector: String

    // 생성자
    constructor(personDatabase: PersonDatabase){
        this.personDatabase = personDatabase

        this.personDatabase.setOnDatabaseListener(object : DatabaseListener {
            override fun onChanged() {
                selector = ""
                selector = personDatabase.getSelector()
                notifyChange()
            }
        })

    }

    fun getUser(){
        Log.d("ViewModel getUser - " , "DB에게 user(selector)를 달라고 요청")
        personDatabase.getUser()
    }

    fun getSelector(): String{
        Log.d("VM getSelector - " , "selector 반환 $selector")
        return selector
    }

    fun setReadyPersonList(i:Long , name: String) {
        personDatabase.setReadyPersonList(i, name)
    }

    fun getReadyPersonList(): ArrayList<Person> {
        return personDatabase.getReadyPersonList()
    }

    fun removeReadyPersonList(){
        personDatabase.removeReadyPersonList()
    }

}