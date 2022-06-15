package com.example.kotlinpractice.choicelunch.entity

import androidx.room.*

// ROOM에서 사용할 테이블 생성
// Entity 어노테이션을 class 위에 추가하고 테이블 이름을 tb_person으로 지정
// @PrimaryKey 어노테이션과 autoGenerate=true를 이용해서 PersonEntity가 새로 생길때마다 seq를 자동으로 올라감
@Entity(tableName = "tb_personList")
data class PersonEntity (
    @PrimaryKey(autoGenerate = true) val seq:Long
    , var id: String
    , var personListTxt: String
)



