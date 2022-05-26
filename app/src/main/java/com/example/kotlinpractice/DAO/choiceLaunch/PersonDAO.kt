package com.example.kotlinpractice.DAO.choiceLaunch

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinpractice.entity.choiceLaunch.PersonEntity

// interface 위에 어노테이션 Dao 선언
@Dao
interface PersonDAO {

    // Query 어노테이션으로 해당 값 가져오기
    @Query("SELECT * FROM tb_personList")
    fun getAll(): List<PersonEntity>

    // Query 어노테이션으로 해당 값 가져오기
    @Query("SELECT * FROM tb_personList WHERE id = :btnSeq")
    fun getFavoritTxt(btnSeq: String): List<PersonEntity>

    // 여러개 insert 할 수도 있기 때문에 vararg로 선언
    @Insert
    fun insertAll(vararg personEntity: PersonEntity)

    @Delete
    fun delets(personEntity: PersonEntity)

}