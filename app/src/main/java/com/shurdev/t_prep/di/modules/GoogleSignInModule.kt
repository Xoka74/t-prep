package com.shurdev.t_prep.di.modules

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.shurdev.t_prep.data.helpers.GoogleAccountConsumer
import com.shurdev.t_prep.data.helpers.GoogleAuthCallbackHolder
import com.shurdev.t_prep.di.qualifiers.ServerClientId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleSignInModule {
    @Provides
    @Singleton
    fun provideGoogleAuthCallbackHolder(): GoogleAuthCallbackHolder {
        return GoogleAuthCallbackHolder()
    }


    @Provides
    @Singleton
    fun provideGoogleAccountConsumer(holder: GoogleAuthCallbackHolder): GoogleAccountConsumer {
        return GoogleAccountConsumer(holder)
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        @ServerClientId serverClientId: String,
    ): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(serverClientId)
            .requestEmail()
            .requestProfile()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }
}