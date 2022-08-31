package app.nush.hearme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.nush.hearme.ui.CallBar
import app.nush.hearme.ui.LipSync
import app.nush.hearme.ui.SmallSettings
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

                    Divider()

                    TTSField()

                    Divider()

                    Row {
                        LipSync()

                        SmallSettings()
                    }
                }
            }
        }
    }
}