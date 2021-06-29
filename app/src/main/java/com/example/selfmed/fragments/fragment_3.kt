package com.example.selfmed.fragments

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.selfmed.R

class fragment_3 : Fragment() {

    private lateinit var webview: WebView
    var message = "Loading..."
    private lateinit var progressDialog: ProgressDialog
    private  lateinit var swipe: SwipeRefreshLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_3, container, false)
        swipe = root?.findViewById<View>(R.id.swipeContainer_frag3) as SwipeRefreshLayout
        swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            setUpWebView()
            swipe.isRefreshing = false;
        })

    progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait")
        progressDialog.setTitle("Loading")

        progressDialog.show()


        webview = root.findViewById(R.id.learnmore_webview)
        setUpWebView()

        return root
    }

    /** setUpWebView method to setUp webView settings */
    @SuppressLint("SetJavaScriptEnabled")
    fun setUpWebView() {
        /** getting webView setting into webSettings variable  */
        val webSettings: WebSettings = webview.settings
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
        webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        try {
            /** loading webpage through URL  */
            webview.loadUrl("https://www.cdc.gov/tb/publications/factsheets/general/tb.htm")
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
            progressDialog.dismiss()
        }
    }


}