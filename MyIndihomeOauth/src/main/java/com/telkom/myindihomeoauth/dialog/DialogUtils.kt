package com.telkom.myindihomeoauth.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.telkom.myindihomeoauth.helper.LoginMYIHCallback
import com.telkom.myindihomeoauth.model.Data
import com.telkom.myindihomeoauth.databinding.DialogWebviewBinding
import java.net.URLEncoder
import java.util.*

object DialogUtils {

    fun showDialogWebView(context: Context, data: Data, callback: LoginMYIHCallback<String>) {
        val dialog = Dialog(context, android.R.style.Theme_Material_Light_NoActionBar)
        val binding = DialogWebviewBinding.inflate(LayoutInflater.from(context))

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
        Objects.requireNonNull(dialog.window)
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.webview.webViewClient = MYIHWebServiceClient(callback, data.redirectUrl!!, dialog)
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.loadWithOverviewMode = true
        binding.webview.settings.useWideViewPort = true
        binding.webview.settings.builtInZoomControls = true

        val postData = "client_id=${URLEncoder.encode(data.clientId, "UTF-8")}" +
                "&apps_name=${URLEncoder.encode(data.appName, "UTF-8")}" +
                "&redirect_uri=${URLEncoder.encode(data.redirectUrl, "UTF-8")}"

        binding.webview.postUrl(data.url!!, postData.toByteArray())

        dialog.show()
    }
}

class MYIHWebServiceClient(var callback: LoginMYIHCallback<String>, var redirectUri: String, var dialog: Dialog) : WebViewClient() {
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        if (url?.contains(redirectUri)!!) {
            if (url.contains("code")) {
                val code = view?.url?.substringAfterLast("code=")
                callback.onSuccess(code!!)
            } else {
                callback.onFailure("There is something when wrong...")
            }
            dialog.hide()
        }
    }

}