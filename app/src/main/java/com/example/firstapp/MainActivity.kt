package com.example.firstapp

import android.R.attr.data
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.MasterKeys
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


const val EXTRA_MESSAGE = "com.example.firstApp.MESSAGE"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setSpf("123")
        this.getSpf(R.string.user_pwd)

        this.setShareSpf("456")
        this.getShareSpf(R.string.user_pwd2)

        this.setInnerSpace("doc","hello world\n how r u")
        this.deleteInnerSpace("doc")
        this.getInnerSpace("doc")
    }


    fun setSpf( value:String){

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(getString(R.string.user_pwd), value)
            commit()
        }
    }

    fun getSpf(key:Int):String{

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        var value = sharedPref.getString(getString(key), "defaultValue")
        return value?:""
    }


    fun setShareSpf( value:String){

        val sharedPref = this?.getSharedPreferences(getString(R.string.share_preference_01),Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(getString(R.string.user_pwd2), value)
            commit()
        }
    }

    fun getShareSpf(key:Int):String{

        val sharedPref = this?.getSharedPreferences(getString(R.string.share_preference_01),Context.MODE_PRIVATE)
        var value = sharedPref.getString(getString(key), "defaultValue")
        return value?:""
    }


    fun setInnerSpace(filename:String,fileContents:String){
        // ioexception
        val phonefreeSpace: Long = Environment.getDataDirectory().getFreeSpace()

        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }

    fun setInnerCacheSpace(filename:String,fileContents:String){
        // ioexception
        val phonefreeSpace: Long = Environment.getDataDirectory().getFreeSpace()

        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }

    fun deleteInnerSpace(filename:String){

//        val file = File(this.filesDir, filename)
//        val r = file.delete()
//        println(r)
        // 第二种删除方式
        var r = this.deleteFile(filename)
        println(r)

    }
    fun getInnerSpace(filename:String ){
//        val file =   this.openFileInput(filename)
//        println(file.toString())
//        //把文件内容读取进缓冲读取器（use方法会自动对BufferedReader进行关闭）
//        BufferedReader(InputStreamReader(file)).use {
//            var line: String
//            while (true) {
//                line = it.readLine() ?: break //当有内容时读取一行数据，否则退出循环
//                print(line)
//            }
//
//        }

        try {
            val fileInput = openFileInput(filename)
            fileInput.reader().forEachLine {
                println(it)
            }
            fileInput.close()
        }catch (e:Exception){
            println(e)
        }




    }
    fun signIn(view:View){

        val i = Intent(this, SignActivity::class.java)
        startActivity(i)
    }
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = data?.getStringExtra("result")
        println("获取上个页面值:$result")
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun musicList(view: View){
        startActivity(Intent(this,MusicList::class.java))
    }
    //
    fun motionView(view: View){
        startActivity(Intent(this,MotionActivity::class.java))
    }
}
