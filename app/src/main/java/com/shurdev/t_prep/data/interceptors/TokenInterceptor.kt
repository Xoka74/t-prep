package com.shurdev.t_prep.data.interceptors

import com.shurdev.t_prep.data.constants.Headers
import com.shurdev.t_prep.data.dataSource.AuthDataSource
import com.shurdev.t_prep.utils.bearer
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val authDataSource: AuthDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().also { request ->
            val accessToken = authDataSource.accessToken() ?: return@also

            request.addHeader(Headers.AUTHORIZATION, accessToken.bearer)
        }

        return chain.proceed(newRequest.build())
    }
}