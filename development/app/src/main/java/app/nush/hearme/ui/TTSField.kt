package app.nush.hearme.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TTSField(text: MutableState<String>) {
    val scroll = rememberScrollState(0)

    /*
    val transcript = remember { mutableStateOf("""
        Good Morning Sir, would you be willing to participate in a survey on the government's efforts to combat COVID-19?
        
        Yes sure.
        
        On a scale of 1 - 5, with 5 being the strongly agree and 1 being strongly disagree, do you agree the government has provided sufficient support for people who have lost their jobs due to COVID-19?
        
    """.trimIndent()) }
     */

    Text(
        text = text.value,
        modifier = Modifier.verticalScroll(scroll).padding(10.dp)
    )
}
