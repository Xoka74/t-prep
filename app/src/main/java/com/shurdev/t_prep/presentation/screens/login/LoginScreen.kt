package com.shurdev.t_prep.presentation.screens.login

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shurdev.t_prep.R
import com.shurdev.t_prep.data.managers.AccountManager
import com.shurdev.t_prep.presentation.components.ErrorView
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginIdleState
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginLoadingState
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginSuccessState
import com.shurdev.t_prep.presentation.screens.login.viewModel.LoginViewModel
import com.shurdev.t_prep.presentation.screens.login.composables.SignInWithGoogleButton
import com.shurdev.t_prep.presentation.components.layout.Center
import com.shurdev.t_prep.ui.theme.TPrepTheme
import com.shurdev.t_prep.utils.runSuspendCatching
import kotlinx.coroutines.launch


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
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val accountManager = remember {
        AccountManager(context as ComponentActivity)
    }

    fun onLoginClick() {
        coroutineScope.launch {
            runSuspendCatching {
                val response = accountManager.login()
                viewModel.login(response)
            }
        }
    }

    LoginContent(
        onLoginClick = ::onLoginClick,
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
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.app_icon),
            contentDescription = stringResource(R.string.start_screen),
            modifier = Modifier.size(200.dp, 150.dp)
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