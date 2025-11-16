package com.shurdev.t_prep.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.shurdev.t_prep.data.helpers.GoogleAuthCallbackHolder
import com.shurdev.t_prep.ui.theme.TPrepTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var googleAuthCallbackHolder: GoogleAuthCallbackHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleAuthCallbackHolder.updateCallback(this)
        setContent {
            enableEdgeToEdge()
            setContent {
                TPrepTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                        TPrepApp()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        googleAuthCallbackHolder.clearCallback()
        super.onDestroy()
    }
}