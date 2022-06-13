package com.example.kotlinpractice.choiceLaunch.view
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R
import com.example.kotlinpractice.choiceLaunch.entity.PersonEntity

class PersonListViewHolder(v: View) : RecyclerView.ViewHolder(v){
    var view : View = v
    // View는 Adaptor에서 선언한 아이템에 대한 xml이고 해당 xml에 있는 id값을 가져옴
    var txt:TextView = view.findViewById(R.id.favoritName)
    var txtVal:TextView = view.findViewById(R.id.favoritValue)

    fun bind(item: PersonEntity) {
        txt.text = "즐겨찾기"+item.seq          // 생성되는 즐겨찾기 버튼 명
        txtVal.text = item.personListTxt     // 즐겨찾기 버튼에 저장되는 사람들
    }
}