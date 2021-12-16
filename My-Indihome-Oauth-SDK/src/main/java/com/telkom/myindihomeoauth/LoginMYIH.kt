package com.telkom.myindihomeoauth

import android.content.Context
import com.telkom.myindihomeoauth.dialog.DialogUtils
import com.telkom.myindihomeoauth.helper.LoginMYIHCallback
import com.telkom.myindihomeoauth.model.Data

class LoginMYIH {

    private lateinit var url: String
    private lateinit var clientId: String
    private lateinit var appName: String
    private lateinit var redirectUrl: String
    private var data: Data = Data()

    fun doLogin(context: Context, callback: LoginMYIHCallback<String>): LoginMYIH {
        DialogUtils.showDialogWebView(context, data, callback)
        return this
    }

    fun setURL(url: String?): LoginMYIH {
        url?.let {
            this.url = it
            this.data.url = it
        }
        return this
    }

    fun setClientID(clientId: String?): LoginMYIH {
        clientId?.let {
            this.clientId = it
            this.data.clientId = it
        }
        return this
    }

    fun setAppName(appName: String?): LoginMYIH {
        appName?.let {
            this.appName = it
            this.data.appName = it
        }
        return this
    }

    fun setRedirectURL(redirectUrl: String?): LoginMYIH {
        redirectUrl?.let {
            this.redirectUrl = it
            this.data.redirectUrl = it
        }
        return this
    }

}