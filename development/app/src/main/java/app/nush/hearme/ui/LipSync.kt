package app.nush.hearme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.nush.hearme.R
import app.nush.hearme.ui.theme.HearMeTheme

@Preview
@Composable
fun LipSync() {
    val image = painterResource(id = R.drawable.ic_launcher_background)

    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth(0.5f),
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "Deepfake Lip Sync",
                color = HearMeTheme.colors.primary
            )

            Image(painter = image, contentDescription = "Deepfake lip sync")
        }
    }
}