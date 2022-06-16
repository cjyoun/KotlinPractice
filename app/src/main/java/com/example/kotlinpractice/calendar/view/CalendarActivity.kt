package com.example.kotlinpractice.calendar.view

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.example.kotlinpractice.MainActivity
import android.widget.EditText

import android.view.MotionEvent
import com.example.kotlinpractice.BaseActivity
import com.example.kotlinpractice.databinding.ActivityCalendarBinding

class CalendarActivity : BaseActivity() {

    private lateinit var binding: ActivityCalendarBinding

    // 키보드 숨기기  ---------------------------------
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus
        if (view != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && view is EditText && !view.javaClass.name.startsWith(
                "android.webkit."
            )
        ) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x = ev.rawX + view.getLeft() - scrcoords[0]
            val y = ev.rawY + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(
                INPUT_METHOD_SERVICE
            ) as InputMethodManager).hideSoftInputFromWindow(
                this.window.decorView.applicationWindowToken, 0
            )
        }
        return super.dispatchTouchEvent(ev)
    }
    //---------------------------------


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)   // 자동생성된 뷰바인딩 클래스에서의 inflate라는 메서드를 활용해서 액티비티에서 사용할 바인딩클래스의 인스턴스 생성
        setContentView(binding.root)    // 레이아웃 내부의 최상위 위치뷰의 인스턴스를 활용하여 뷰를 액티비티에 표시


        // binding 변수를 사용하여 xml 내부의 id 값을 호출
        // 홈 페이지로 이동
        binding.goHome.setOnClickListener{
            val goHome = Intent(this, MainActivity::class.java);
            startActivity(goHome)
        }


    }

}