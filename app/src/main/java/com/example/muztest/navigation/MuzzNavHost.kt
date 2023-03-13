import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.muztest.navigation.Screen
import com.example.muztest.ui.screen.ChatScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MuzzNavHost() {
    val navController = rememberNavController()
    val startDestination = Screen.ChatScreen.route
    val keyboardController = LocalSoftwareKeyboardController.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
            .fillMaxSize(),
    ) {

        composable(Screen.ChatScreen.route) {
            if (keyboardController != null) {
                ChatScreen(
                    keyboardController = keyboardController
                )
            }
        }

    }
}


