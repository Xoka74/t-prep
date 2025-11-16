package com.shurdev.t_prep.presentation.screens.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shurdev.t_prep.presentation.components.ErrorView
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginIdleState
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginLoadingState
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginSuccessState
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginViewModel
import com.shurdev.t_prep.presentation.screens.login.composables.SignInWithGoogleButton
import com.shurdev.t_prep.presentation.components.layout.Center
import com.shurdev.t_prep.ui.theme.TPrepTheme


@Composable
fun LoginScreen(
    onSuccessLogin: () -> Unit,
) {
    val viewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is LoginSuccessState) {
            onSuccessLogin()
        }
    }

    val showLoading = uiState is LoginLoadingState || uiState is LoginSuccessState
    val error = (uiState as? LoginIdleState)?.error

    LoginContent(
        onLoginClick = viewModel::login,
        isLoading = showLoading,
        error = error,
    )
}

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    isLoading: Boolean,
    error: String? = null,
) {
    Center {
        AsyncImage(
            model = "https://avatar.iran.liara.run/public/15",
            contentDescription = null,
        )

        Spacer(Modifier.height(10.dp))

        SignInWithGoogleButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = onLoginClick,
            isLoading = isLoading,
        )

        if (error != null) {
            ErrorView(
                error = error,
                onRetry = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    TPrepTheme {
        Surface {
            LoginScreen(
                onSuccessLogin = {},
            )
        }
    }
}