package com.example.firstapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.Gravity
import android.webkit.*
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web2.*

class WebActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web2)
        setSupportActionBar(findViewById(R.id.toolbar))

        // webclient js 调用kotlin
        webview.webViewClient = MyWebViewClient()
        webview.webChromeClient = MyWebChromeClient()

//        this.loadLocalHtml()
        this.loadGoogle()
        //        设置WebView可以与JS交互 这里必须设置
        webview.settings.javaScriptEnabled = true
        //        设置允许JS中的弹窗
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
    }


    fun loadWebView(){

        //        然后加载JS代码
        webview.loadUrl("file:///android_asset/web.html")
        //        调用JS无参方法
        android_btn.setOnClickListener {
            webview.post {
                run {
                    //第一种方法 通过loadUrl调用JS代码
                    //调用无参JS方法
                    webview.loadUrl("javascript:clickJS()")
                    //调用有参JS方法
                    webview.loadUrl("javascript:clickJS('我调用了JS的方法')")
                }
            }
        }

    }
    fun loadLocalHtml(){

        //        然后加载JS代码
        webview.loadUrl("file:///android_asset/web.html")
        webview.addJavascriptInterface(JsObject(),"android")

        //        调用JS无参方法
        android_btn.setOnClickListener {
            webview.evaluateJavascript("javascript:clickJSTwo('android调用了JS的方法')",object : ValueCallback<String>{
                override fun onReceiveValue(value: String?) {
                    //   这里返回JS的结果
                    println(value)
                }
            })
        }


    }

    fun loadGoogle(){

        webview.loadUrl("https://www.baidu.com")
        android_btn.setOnClickListener{


            var script = "function hello(){ javascript:document.getElementsByClassName(\"gLFyf gsfi\")[0].value = 'abc';return 'Hello world!'; } hello();"
            script = "(function() { return 1337 })()"
            script = "(function() {document.getElementsByTagName(\"input\")[0].value = \"android\" ;document.getElementsByTagName(\"button\")[0].click()})()"
            webview.evaluateJavascript(script,object : ValueCallback<String>{
                override fun onReceiveValue(value: String?) {
                    //   这里返回JS的结果
                    println(value)
                }
            })
        }

    }
    fun setWebView(){
        val myWebView = WebView(this)
        setContentView(myWebView)
        myWebView.loadUrl("https://www.google.com/")
    }


    inner class JsObject {
        @JavascriptInterface
        fun jsAndroid(msg : String){
            //点击html的Button调用Android的Toast代码
            //我这里让Toast居中显示了
            val makeText = Toast.makeText(this@WebActivity, msg,Toast.LENGTH_LONG)
            makeText.setGravity(Gravity.CENTER,0,0)
            makeText.show()
        }
    }


    inner class MyWebChromeClient : WebChromeClient(){
        override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
            val makeText = Toast.makeText(this@WebActivity, message, Toast.LENGTH_LONG)
            makeText.setGravity(Gravity.CENTER,0,0)
            makeText.show()
            return super.onJsPrompt(view, url, message, defaultValue, result)
        }
    }


    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//      获取Uri  这里的URL是我们在JS方法中写的URL协议"js://webview?name=zhangsan&age=20&sex=0"
            val uri = Uri.parse(url)
            if (uri.scheme == "js") {
                if (uri.authority == "webview") {
                    val makeText = Toast.makeText(this@WebActivity, url, Toast.LENGTH_LONG)
                    makeText.setGravity(Gravity.CENTER, 0, 0)
                    makeText.show()
                }
                return true
            }
            return false
        }
    }


}


// reference https://www.jianshu.com/p/826a39ed81e6
