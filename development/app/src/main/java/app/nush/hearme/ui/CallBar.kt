package app.nush.hearme.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.nush.hearme.ui.theme.HearMeTheme

@Preview
@Composable
fun CallBar() {
    val threadStarted = remember { mutableStateOf(false) }
    val callTime = remember { mutableStateOf(0) }
    val callRunning = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        backgroundColor = HearMeTheme.colors.primary,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                Icons.Rounded.Call,
                contentDescription = "Localized description",
                tint = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Text(
                text="${(callTime.value/60).toString().padStart(2, '0')}:" +
                        (callTime.value%60).toString().padStart(2, '0'),
                color=Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(Modifier.weight(3f))

            OutlinedButton(
                onClick = {
                    callRunning.value = !callRunning.value
                    if (!threadStarted.value) {
                        Thread {
                            while (true) {
                                if (callRunning.value) callTime.value++
                                else callTime.value = 0
                                Thread.sleep(1000)
                            }
                        }.start()
                    }
                }
            ) {
                Text(if (callRunning.value) "End call" else "Start call")
            }
        }
    }
}