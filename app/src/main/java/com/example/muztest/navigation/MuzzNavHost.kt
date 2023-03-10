import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.muztest.navigation.Screen
import com.example.muztest.ui.screen.ChatScreen

@Composable
fun MuzzNavHost() {
    val navController = rememberNavController()
    val startDestination = Screen.ChatScreen.route
    val coroutineScope = rememberCoroutineScope()


        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize(),
        ) {

            composable(Screen.ChatScreen.route) {
                ChatScreen()
            }

        }
    }


