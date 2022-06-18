package com.example.kotlinpractice.picture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinpractice.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPictureBinding    // 전역으로 바인딩 객체 생성 (Activity별 자동 생성된 Binding클래스 매칭)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_main)        // 기존 setContentView 제거
        binding = ActivityPictureBinding.inflate(layoutInflater)   //자동생성된 뷰바인딩 클래스에서의 inflate라는 메서드를 활용해서 액티비티에서 사용할 바인딩클래스의 인스턴스 생성
        setContentView(binding.root)    // 레이아웃 내부의 최상위 위치뷰의 인스턴스를 활용하여 뷰를 액티비티에 표시

    }
}