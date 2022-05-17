package com.example.kotlinpractice.model.choiceLaunch

import android.util.Log

class Database {
    private lateinit var instance:Database
    private var personList = ArrayList<Person>()
    private lateinit var selector:String
    private lateinit var databaseListener: DatabaseListener

    constructor() {
        Log.d("Database - " ,"Model인 Database 생성")
        personList.add( Person(0,"김다유") )
        personList.add( Person(1,"김보성") )
        personList.add( Person(2,"채재윤") )
        personList.add( Person(3,"최소연") )
        personList.add( Person(4,"최원혁") )
    }

//    fun getInstance(): Database{
//        Log.d("getInstance() - " , "Model에 접근 할 수 있도록 DB 인스턴스 값 요청")
//        if(instance == null){
//            instance = Database()
//        }
//        return instance
//    }

    fun getUser() {
        Log.d("getUser() = " , "당첨자 획득")
        selector = personList.get( ((Math.random()*5).toInt()) ).getName()

        notifyChange()

    }

    private fun notifyChange() {
        if(databaseListener != null){
            Log.d("notifyChange - ", "Model | Data 변경되어 notify 하라고 알림")
            databaseListener.onChanged()
        }
    }

    fun setOnDatabaseListener(databaseListener: DatabaseListener){
        this.databaseListener = databaseListener
    }

    fun getSelector(): String{
        return selector
    }

    interface DatabaseListener {
        fun onChanged()
    }


}