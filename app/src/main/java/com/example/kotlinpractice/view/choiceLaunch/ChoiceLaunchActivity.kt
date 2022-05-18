package com.example.kotlinpractice.view.choiceLaunch

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.kotlinpractice.MainActivity
import com.example.kotlinpractice.databinding.ActivityChoiceLaunchBinding
import com.example.kotlinpractice.model.choiceLaunch.Database
import com.example.kotlinpractice.viewModel.choiceLaunch.ViewModel
import android.widget.EditText

import android.view.MotionEvent
import android.widget.Toast


class ChoiceLaunchActivity : AppCompatActivity() {

    private lateinit var binding:ActivityChoiceLaunchBinding

    private lateinit var viewModel: ViewModel

    private var personListText = ""

    private var number:Long = 0;

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
        binding = ActivityChoiceLaunchBinding.inflate(layoutInflater)   // 자동생성된 뷰바인딩 클래스에서의 inflate라는 메서드를 활용해서 액티비티에서 사용할 바인딩클래스의 인스턴스 생성
        setContentView(binding.root)    // 레이아웃 내부의 최상위 위치뷰의 인스턴스를 활용하여 뷰를 액티비티에 표시



        // binding 변수를 사용하여 xml 내부의 id 값을 호출
        // 홈 페이지로 이동
        binding.goHome.setOnClickListener{
            val goHome = Intent(this, MainActivity::class.java);
            startActivity(goHome)
        }

        viewModel = ViewModel(Database())   // Database의 데이터들을 담은 ViewMoel 객체 생성

        // 메뉴 고르는 사람 버튼 클릭
        binding.personRandomBtn.setOnClickListener{
            Log.d("onClick = " , "랜덤으로 인원 선택")

            // Database에 선언된 PersonList의 값이 있는지 없는지 여부 체크
            var check = viewModel.getPersonList();
            if( TextUtils.isEmpty(check.toString()) || check.size==0 ){
                Toast.makeText(this,"사람을 추가해주세요.",Toast.LENGTH_SHORT).show()
            } else{
                Log.d("onClick = " , "랜덤으로 인원 선택")
                viewModel.getUser() // ViewModel에서 랜덤으로 User를 선택하여 selector 값 넣기
                binding.personName.text = viewModel.getSelector()   // 선택된 유저 텍스트로 보여주기
            }
        }


        // 사람 입력 후 추가
        binding.plusPerson.setOnClickListener {
            Log.d("plus click = " , "사람 추가 버튼 클릭")
            var inputPersonTxt = binding.inputPerson.text
            var replaceTxt = inputPersonTxt.trim()  // 공백 제거
            if(!TextUtils.isEmpty(replaceTxt)){  // 공백 제거한 글자를 가지고 체크하기
                personListText = "$personListText$inputPersonTxt  "
                binding.inputPersonText.text = personListText   // 텍스트 출력

                viewModel.setPersonList(number,inputPersonTxt.toString())
                number++
            }

            binding.inputPerson.text = null     // editText 값 초기화
        }

        binding.inputPersonText.movementMethod = ScrollingMovementMethod()  // textView scrollbar 가능하게 하기 xml에 해당 부분에 andriod:scrollbars="vertical" 추가

    }

}