package app.nush.hearme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Settings(useTTS: MutableState<Boolean>, useLipSync: MutableState<Boolean>) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(20.dp)) {
        Text("Customise your experience:")

        Row {
            Text("Use Text to Speech: ")
            Switch(checked=useTTS.value, onCheckedChange = { useTTS.value = !useTTS.value })
        }

        Row {
            Text("Use Deepfake Lip Sync: ")
            Switch(checked = useLipSync.value, onCheckedChange = { useLipSync.value = !useLipSync.value })
        }
    }
}