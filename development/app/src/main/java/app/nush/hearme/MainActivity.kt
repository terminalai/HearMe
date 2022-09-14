package app.nush.hearme

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.nush.hearme.ui.CallBar
import app.nush.hearme.ui.LipSync
import app.nush.hearme.ui.SmallSettings
import app.nush.hearme.ui.TTSField
import app.nush.hearme.ui.theme.HearMeTheme
import com.jamal.composeprefs.ui.PrefsScreen
import com.jamal.composeprefs.ui.prefs.SwitchPref
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HearMeTheme {
                Content()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun Content() {
    val navController = rememberNavController()
    val useTTS = remember { mutableStateOf(true) }
    val useLipSync = remember { mutableStateOf(true) }

    Column {
        CallBar()
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                Column(
                    modifier = Modifier.padding(10.dp).wrapContentWidth()
                ) {
                    Divider()

                    if (useTTS.value) {
                        TTSField()

                        Divider()
                    }

                    Row {
                        if (useLipSync.value)
                            LipSync()

                        SmallSettings { navController.navigate("settings") }
                    }
                }
            }

            composable("settings") {
                Column {
                    Row(modifier = Modifier.fillMaxHeight(0.87f)) {
                        PrefsScreen(dataStore = LocalContext.current.dataStore) {
                            //prefsItem { TextPref(title = "Settings", summary = "Customise your experience :D") }

                            prefsGroup("Settings") {
                                prefsItem {
                                    SwitchPref(
                                        key = "tts",
                                        title = "Use Text to Speech",
                                        defaultChecked = true,
                                        onCheckedChange = {
                                            useTTS.value = it
                                        }
                                    )
                                }
                                prefsItem {
                                    SwitchPref(
                                        key = "lip_sync",
                                        title = "Use Deepfake Lip Sync",
                                        defaultChecked = true,
                                        onCheckedChange = {
                                            useLipSync.value = it
                                        }
                                    )
                                }
                            }
                        }
                    }

                    FloatingActionButton(
                        onClick = { navController.navigate("main") },
                        backgroundColor = HearMeTheme.colors.primary,
                        modifier = Modifier.padding(10.dp).align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            }
        }
    }
}