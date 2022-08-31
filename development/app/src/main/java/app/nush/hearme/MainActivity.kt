package app.nush.hearme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.nush.hearme.ui.CallBar
import app.nush.hearme.ui.TTSField
import app.nush.hearme.ui.theme.HearMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HearMeTheme {
                Column(
                    modifier = Modifier.padding(10.dp).wrapContentWidth()
                ) {
                    CallBar()

                    TTSField()
                }
            }
        }
    }
}