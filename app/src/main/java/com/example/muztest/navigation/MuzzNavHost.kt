import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.muztest.navigation.Screen
import com.example.muztest.ui.screen.ChatScreen

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class,)
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


