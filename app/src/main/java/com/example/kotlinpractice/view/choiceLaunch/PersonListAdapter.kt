package com.example.kotlinpractice.view.choiceLaunch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.R
import com.example.kotlinpractice.entity.choiceLaunch.PersonEntity

class PersonListAdapter (private val itemList : List<PersonEntity>) : RecyclerView.Adapter<PersonListViewHolder>()  {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListViewHolder {
        // layout을 리사이클러뷰에 보여줄 아이템에 대한 xml 파일 명 입력.
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.activity_choice_launch_favorit, parent, false)
        return PersonListViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PersonListViewHolder, position: Int) {
        val item = itemList[position]

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.apply {
            bind(item)  // 아이템 값으로 ViewHolder에서 바인딩 처리
        }
    }

    //ClickListener
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

}