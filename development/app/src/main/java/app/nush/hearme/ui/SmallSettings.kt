package app.nush.hearme.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SmallSettings() {
    val noiseSuppression = remember { mutableStateOf(0.0f) }

    Column(
        modifier = Modifier.padding(10.dp).fillMaxWidth(1f)
    ) {
        Text("Noise Suppression")

        Slider(
            value = noiseSuppression.value,
            onValueChange = { noiseSuppression.value = it },
            valueRange = 0.0f .. 1.0f
        )

        TextButton(onClick = {}) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(Icons.Default.Settings, "Settings Icon")

                Text("Settings")
            }
        }
    }
}