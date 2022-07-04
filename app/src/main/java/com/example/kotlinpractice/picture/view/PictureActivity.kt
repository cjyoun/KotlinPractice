package com.example.kotlinpractice.picture.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinpractice.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPictureBinding    // 전역으로 바인딩 객체 생성 (Activity별 자동 생성된 Binding클래스 매칭)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureBinding.inflate(layoutInflater)   //자동생성된 뷰바인딩 클래스에서의 inflate라는 메서드를 활용해서 액티비티에서 사용할 바인딩클래스의 인스턴스 생성
        setContentView(binding.root)    // 레이아웃 내부의 최상위 위치뷰의 인스턴스를 활용하여 뷰를 액티비티에 표시


        val image1 = binding.chickenImage1
        image1.setOnClickListener {
            Toast.makeText(this,"1번 치킨",Toast.LENGTH_SHORT).show()

            var intent = Intent(this, Chicken1Activity::class.java)
            startActivity(intent)


        }

    }
}