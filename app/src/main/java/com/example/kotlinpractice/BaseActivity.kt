package com.example.kotlinpractice

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.kotlinpractice.navermap.view.NaverMapActivity
import com.naver.maps.map.LocationTrackingMode


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPhonePermission()

    }

    private fun checkPhonePermission(){
        val locationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        val locationCoarsePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)

        if (locationPermission != PackageManager.PERMISSION_GRANTED){
            // ACCESS_FINE_LOCATION permission이 승인이 아닌 경우 권한 요청
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),99)
        } else if (locationCoarsePermission != PackageManager.PERMISSION_GRANTED){
            // PERMISSION_GRANTED permission이 승인이 아닌 경우 권한 요청
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),99)
        } else{

        }

    }

    // 권환 여부 체크
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            99 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){

                } else{
                    Log.d("권한체크 99 else","권한체크 실패 ")
                }
            }
        }
    }

}