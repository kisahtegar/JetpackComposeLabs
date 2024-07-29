package com.kisahcode.jetpackcomposelabs

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * State holder class for managing the navigation drawer, snackbar, and coroutine scope in a Jetpack
 * Compose application.
 *
 * This class encapsulates the state and provides methods for handling user interactions with
 * the navigation drawer, displaying snackbars, and responding to back press events.
 *
 * @property drawerState The state of the drawer, represented by [DrawerState].
 * @property scope The coroutine scope for launching asynchronous operations.
 * @property snackbarHostState The state of the snackbar host, represented by [SnackbarHostState].
 * @property context The current context for accessing resources and displaying toasts.
 */
class MyNavDrawerState (
    val drawerState: DrawerState,
    private val scope: CoroutineScope,
    val snackbarHostState: SnackbarHostState,
    private val context: Context
) {

    /**
     * Handles the menu button click to toggle the drawer state.
     *
     * This function is responsible for opening the drawer if it is currently closed, and closing
     * the drawer if it is currently open. It uses a coroutine scope to launch the drawer state
     * change operation.
     */
    fun onMenuClick() {
        scope.launch {
            if (drawerState.isClosed) {
                drawerState.open()
            } else {
                drawerState.close()
            }
        }
    }

    /**
     * Handles the selection of a navigation drawer item.
     *
     * This function closes the navigation drawer and shows a snackbar with an optional action. If
     * the action is clicked, a Toast message is displayed. It uses a coroutine scope to launch the
     * operations.
     *
     * @param item The selected navigation drawer item.
     */
    fun onItemSelected(item: MenuItem) {
        scope.launch {
            // Close the navigation drawer.
            drawerState.close()

            // Show a snackbar with an optional action.
            val snackbarResult = snackbarHostState.showSnackbar(
                message = context.resources.getString(R.string.coming_soon, item.title),
                actionLabel = context.resources.getString(R.string.subscribe_question),
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )

            // Show a Toast if the user clicks the snackbar action.
            if (snackbarResult == SnackbarResult.ActionPerformed) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.subscribed_info),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    /**
     * Handles the back press event to close the navigation drawer.
     *
     * This function checks if the navigation drawer is open and closes it if true. It uses a
     * coroutine scope to perform the operation asynchronously.
     */
    fun onBackPress() {
        if (drawerState.isOpen) {
            scope.launch {
                drawerState.close()
            }
        }
    }
}

/**
 * Creates and remembers an instance of [MyNavDrawerState].
 *
 * This function provides a state holder for managing the navigation drawer, snackbar, and coroutine
 * scope within a Composable. It initializes the necessary states and returns an instance of [MyNavDrawerState].
 *
 * @param drawerState The state of the drawer, default is closed.
 * @param coroutinesScope The coroutine scope for launching asynchronous operations.
 * @param snackbarHostState The state of the snackbar host.
 * @param context The current context, typically provided by [LocalContext].
 * @return An instance of [MyNavDrawerState] with the provided states.
 */
@Composable
fun rememberMyNavDrawerState(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutinesScope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    context: Context = LocalContext.current,
): MyNavDrawerState =
    remember(drawerState, coroutinesScope, snackbarHostState, context) {
        MyNavDrawerState(drawerState, coroutinesScope, snackbarHostState, context)
    }