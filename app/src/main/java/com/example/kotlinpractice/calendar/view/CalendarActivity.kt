package com.example.kotlinpractice.calendar.view

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.example.kotlinpractice.MainActivity
import android.widget.EditText

import android.view.MotionEvent
import com.example.kotlinpractice.BaseActivity
import com.example.kotlinpractice.databinding.ActivityCalendarBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

        // 달력 날짜 선택 시
        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->

            // 선택한 날짜 yyyy/MM/dd 형식으로 파싱
            val format = SimpleDateFormat("yyyy/MM/dd")
            val date = format.parse("$year/${month+1}/$dayOfMonth")
            val dateTime = date.time
            Log.d("time1", date.toString())
            // 선택한 날짜가 무슨 요일인지 구하기
            val simpleDateFormat = SimpleDateFormat("E요일", Locale.KOREAN) // 요일 나오게 하는 패턴 (한글로 변경)
            val dayName: String = simpleDateFormat.format(date) // 선택한 날의 요일일

            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            val selectDay = String.format("%d / %d / %d", year, month + 1, dayOfMonth) + " ($dayName)"
            Log.d("time2", selectDay)
            binding.dDay.text = selectDay

            // 오늘 날짜 0시0분으로 맞추기
            val today = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY,0)
                set(Calendar.MINUTE,0)
                set(Calendar.SECOND,0)
                set(Calendar.MILLISECOND,0)
            }
            val oneDay:Long = 60 * 60 * 24 * 1000   // type을 명시해주어야만 계산이 가능함 타입추론으로 Int값이 Long값으로 변하게 되면 다른 값이 계산될 수 있음
            val ingDay =((today.time.time - dateTime) / oneDay ) + 1    // 오늘과 선택한 날의 차이를 통해 몇일이 지났는지 계산, 첫날부터 1일로 치기 때문에 +1

            Log.d("time3", "${today.time.time} / $dateTime / $oneDay / $ingDay")

            // 100일 단위로 10000일까지 구하기
            var str = ""
            for(i in 100 .. 10000 step 100){
                val addDay = dateTime + ((i-1) * oneDay)
                date.time = addDay
                val afterDay = format.format(date) // 선택한 날의 100일 후
                val afterDayName = simpleDateFormat.format(date) // 선택한 날의 100일 후 요일
                str = "$str$i 일 - $afterDay ($afterDayName)\n"
            }

            // 선택한 날짜 이후의 날들을 출력
            binding.dayList.text = "$ingDay 일 째 \n$str"
        }

        binding.dayList.movementMethod = ScrollingMovementMethod()  // textView scrollbar 가능하게 하기 xml에 해당 부분에 andriod:scrollbars="vertical" 추가

    }

}