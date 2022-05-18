package com.example.kotlinpractice.model.choiceLaunch

import android.util.Log

class Database {

    private var personList = ArrayList<Person>()
    private lateinit var selector:String
    private lateinit var databaseListener: DatabaseListener

    constructor() {
        Log.d("Database - " ,"Model인 Database 생성")
//        personList.add( Person(0,"김다유") )
//        personList.add( Person(1,"김보성") )
//        personList.add( Person(2,"채재윤") )
//        personList.add( Person(3,"최소연") )
//        personList.add( Person(4,"최원혁") )
    }

    fun getUser() {
        Log.d("getUser() = " , "당첨자 획득")
        selector = personList.get( ((Math.random()*personList.size).toInt()) ).getName()  // 랜덤으로 데이터베이스에 적힌 값 가져오기

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

    fun setPersonList(i:Long , name: String){
        personList.add(Person(i,name))
    }

    fun getPersonList(): ArrayList<Person> {
        return personList
    }

    interface DatabaseListener {
        fun onChanged()
    }


}