package app.nush.hearme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
import java.util.*


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HearMeTheme {
                onCreate2()
            }
        }
    }

    @Composable
    fun onCreate2() {
        val text = remember { mutableStateOf("") }

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(bundle: Bundle) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(v: Float) {}
            override fun onBufferReceived(bytes: ByteArray) {}
            override fun onEndOfSpeech() {}
            override fun onError(i: Int) {}
            override fun onResults(bundle: Bundle) {
                speechRecognizer.startListening(speechRecognizerIntent)
            }

            override fun onPartialResults(bundle: Bundle) {
                val data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                text.value = text.value + data!![0]
            }

            override fun onEvent(i: Int, bundle: Bundle) {}
        })

        Content(
            text = text,
            startListening = { speechRecognizer.startListening(speechRecognizerIntent) },
            stopListening = { speechRecognizer.stopListening() }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Content(text: MutableState<String>, startListening: () -> Unit, stopListening: () -> Unit) {
    val navController = rememberNavController()
    val useTTS = remember { mutableStateOf(true) }
    val useLipSync = remember { mutableStateOf(true) }

    Column {
        CallBar({ startListening() }, { stopListening() })
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                Column(
                    modifier = Modifier.padding(10.dp).wrapContentWidth()
                ) {
                    Divider()

                    if (useTTS.value) {
                        TTSField(text=text)

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