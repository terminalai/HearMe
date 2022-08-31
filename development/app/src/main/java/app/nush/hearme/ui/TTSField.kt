package app.nush.hearme.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TTSField() {
    val scroll = rememberScrollState(0)
    val transcript = remember { mutableStateOf("""
        Good morning, I am Jed Lim and I will be reporting on Problem 13, Candle Powered Turbine.
        
        The problem statement states that a paper spiral suspended above the candle will start to rotate. We are tasked to optimize this setup for maximum torque. The relevant parameters to investigate are the properties of the paper, the shape of the spiral, the candle’s heat output, the radial position of the candle and the height of the spiral above the candle.
        
        Here is my content for today.
        
        First, let’s take a look at some basics.
        
        Our experimental setup consists of a paper spiral held at the top by a pin. The pin is suspended by the retort stand. The candle is placed below the spiral.
    """.trimIndent()) }

    Text(
        text = transcript.value,
        modifier = Modifier.verticalScroll(scroll).padding(10.dp)
    )
}
