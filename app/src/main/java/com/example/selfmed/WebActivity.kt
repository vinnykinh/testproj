package com.example.selfmed

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener


class WebActivity : AppCompatActivity() {

    private lateinit var webview: WebView
    var message = "Loading..."
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var progressDialog: ProgressDialog

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        val toolBar = findViewById<Toolbar>(R.id.web_toolbar)
        setSupportActionBar(toolBar)
        supportActionBar!!.title = "Buy Medicine"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener {
            finish()
        }

        swipe = findViewById<View>(R.id.swipeContainer) as SwipeRefreshLayout
        swipe.setOnRefreshListener(OnRefreshListener {
            setUpWebView()
            swipe.setRefreshing(false);
        })
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")

        progressDialog.show()

        webview = findViewById(R.id.webView)
        setUpWebView()


    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed()
        }
    }

    /** setUpWebView method to setUp webView settings */
    fun setUpWebView() {
        /** getting webView setting into webSettings variable  */
        val webSettings: WebSettings = webview.getSettings()
        /** enabling javascript enabled to show javascript content flexibly  */
        webSettings.javaScriptEnabled = true
        /** initializing webView client  */
        webview.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                swipe.setRefreshing(false);

            }
        })
        /** webView allow to get current location to sites who need this property  */
        webSettings.setGeolocationEnabled(true)
        /** enabling open window to java script when content want to open it  */
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        /** condition for enabling media playback require when mobile version is greater then jellybean */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.mediaPlaybackRequiresUserGesture = true
        }
        /** enabling webview to load images automatically without getting permission from user  */
        webSettings.loadsImagesAutomatically = true
        /** improve settings for fast browsing   */
        webSettings.useWideViewPort = true
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSettings.setAppCacheEnabled(true)
        webSettings.domStorageEnabled = true
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSettings.useWideViewPort = true
        webSettings.savePassword = true
        webSettings.saveFormData = true
        webSettings.setEnableSmoothTransition(true)
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        try {
            /** loading webpage through URL  */
            webview.loadUrl("https://mydawa.com/pharmacy/prescription-only")
            progressDialog.dismiss()
        } catch (e: Exception) {
            /** for if webpage is not load  */
            webview.setWebChromeClient(object : WebChromeClient() {
                override fun onGeolocationPermissionsShowPrompt(
                    origin: String,
                    callback: GeolocationPermissions.Callback
                ) {
                    callback.invoke(origin, true, false)
                }
            })
            message = "Scanning Location..."
            /** load default page to show user that your page is not loading  */
            webview.loadUrl("file:///android_asset/myfile.html")
            Toast.makeText(this, "Network problem, try again", Toast.LENGTH_SHORT).show()
            progressDialog.dismiss()
        }
    }


}
