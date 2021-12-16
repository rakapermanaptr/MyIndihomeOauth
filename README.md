# MyIndihome Oauth SDK
MyIndihome Oauth SDK

# Instalation

Add the dependency

    dependencies {
        ...
        implementation 'com.github.rakapermanaptr:MyIndihomeOauth:0.0.2'
    }   

Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://www.jitpack.io' }
        }
    }
    
    
# Usages
Register the callback from doLogin(). 'this' for the context.

    binding.btnLogin.setOnClickListener {
        LoginMYIH()
            .setAppName(APP_NAME)
            .setClientID(CLIENT_ID)
            .setRedirectURL(REDIRECT_URL)
            .setURL(URL)
            .doLogin(this, object : LoginMYIHCallback<String> {
                override fun onSuccess(data: String) {
                    // Result is Code
                }

                override fun onFailure(message: String) {
                    // Message for failure
                }
            })
    }
    
 
