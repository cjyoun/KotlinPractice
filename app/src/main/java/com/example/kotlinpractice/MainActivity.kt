package com.example.kotlinpractice

import android.content.Intent
import android.os.Bundle
import com.example.kotlinpractice.calendar.view.CalendarActivity
import com.example.kotlinpractice.databinding.ActivityMainBinding
import com.example.kotlinpractice.choicelunch.view.ChoiceLunchActivity
import com.example.kotlinpractice.navermap.view.NaverMapActivity


class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding    // 전역으로 바인딩 객체 생성 (Activity별 자동 생성된 Binding클래스 매칭)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)        // 기존 setContentView 제거
        binding = ActivityMainBinding.inflate(layoutInflater)   //자동생성된 뷰바인딩 클래스에서의 inflate라는 메서드를 활용해서 액티비티에서 사용할 바인딩클래스의 인스턴스 생성
        setContentView(binding.root)    // 레이아웃 내부의 최상위 위치뷰의 인스턴스를 활용하여 뷰를 액티비티에 표시



        // binding 변수를 사용하여 xml 내부의 id 값을 호출
        // 점심 고르기 페이지로 이동
        binding.goChoiceLunch.setOnClickListener{
            val goChoiceLunchActivity = Intent(this, ChoiceLunchActivity::class.java);
            startActivity(goChoiceLunchActivity)
        }

        // 달력 보기 페이지로 이동
        binding.goCalendar.setOnClickListener{
            val goCalendarActivity = Intent(this, CalendarActivity::class.java);
            startActivity(goCalendarActivity)
        }


        // 네이버 지도 보기 페이지로 이동
        binding.goNaverMap.setOnClickListener{
            val goNaverMapActivity = Intent(this, NaverMapActivity::class.java);
            startActivity(goNaverMapActivity)
        }


    }



}