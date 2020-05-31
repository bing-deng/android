package com.example.firstapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*


private lateinit var fusedLocationClient: FusedLocationProviderClient

class MotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion)

        this.getSpf(R.string.user_pwd)
        this.getShareSpf(R.string.user_pwd2)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                println(location)
                this.getAddress(location)
                // Got last known location. In some rare situations this can be null.
            }
    }

    private fun getAddress(location: Location?): String? {
        //用来接收位置的详细信息
//        location?.latitude = 35.613488;
//        location?.longitude = 139.730032;
        var result: List<Address?>? = null
        val addressLine = ""
        try {
            if (location != null) {
                val gc = Geocoder(this, Locale.getDefault())
                result = gc.getFromLocation(
                    location.latitude,
                    location.longitude, 1
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (result != null && result[0] != null) {

            val country = result.get(0)?.countryName
            println(country)
            println(addressLine)
        }
        return addressLine
    }
    /**
     * 保存键值对数据
     *
     */


    fun getSpf(key:Int):String{

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        var value = sharedPref.getString(getString(key), "defaultValue")
        return value?:""
    }

    fun getShareSpf(key:Int):String{

        val sharedPref = this?.getSharedPreferences(getString(R.string.share_preference_01),Context.MODE_PRIVATE)
        var value = sharedPref.getString(getString(key), "defaultValue")
        return value?:""
    }

}