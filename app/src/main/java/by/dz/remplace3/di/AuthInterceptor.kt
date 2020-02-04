package by.dz.remplace3.di

import by.dz.remplace3.data.local.prefs.user.IUserPrefsUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val prefs: IUserPrefsUtils) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        if (request.header(NO_AUTH_HEADER_KEY) == null) {
            // needs credentials
            if (prefs.isEmptyToken()) {
                throw RuntimeException("Session token should be defined for auth apis")
            } else {
                requestBuilder.addHeader(HEADER_AUTH, prefs.getToken())
            }
        }
        val response = chain.proceed(requestBuilder.build())
        val token = response.header(HEADER_AUTH) ?: ""
        if (token.isNotEmpty()) {
            prefs.saveToken(token)
        }
        return response
    }

}