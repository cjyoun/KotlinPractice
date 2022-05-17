package com.example.kotlinpractice.view.choiceLaunch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kotlinpractice.MainActivity
import com.example.kotlinpractice.databinding.ActivityChoiceLaunchBinding
import com.example.kotlinpractice.model.choiceLaunch.Database
import com.example.kotlinpractice.viewModel.choiceLaunch.ViewModel

class ChoiceLaunchActivity : AppCompatActivity() {

    private lateinit var binding:ActivityChoiceLaunchBinding

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceLaunchBinding.inflate(layoutInflater)   // 자동생성된 뷰바인딩 클래스에서의 inflate라는 메서드를 활용해서 액티비티에서 사용할 바인딩클래스의 인스턴스 생성
        setContentView(binding.root)    // 레이아웃 내부의 최상위 위치뷰의 인스턴스를 활용하여 뷰를 액티비티에 표시



        // binding 변수를 사용하여 xml 내부의 id 값을 호출
        // 점심 고르기 페이지로 이동
        binding.goHome.setOnClickListener{
            val goHome = Intent(this, MainActivity::class.java);
            startActivity(goHome)
        }

        viewModel = ViewModel(Database())


        binding.personRandomBtn.setOnClickListener{
            Log.d("onClick = " , "랜덤으로 인원 선택")
            viewModel.getUser()
            binding.personName.text = viewModel.getSelector()
        }

    }
}