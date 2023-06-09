import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Preview
@Composable
fun DevicesScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(android.graphics.Color.parseColor("#2f2f4d"))) {
        Column() {
            Text(
                text = stringResource(R.string.devicesTitle),
                fontSize = 25.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 8.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(10) { index ->
                    ClickableImage(
                        resourceId = getImageResourceId(index),
                        contentDescription = "Image $index"
                    ) {
                        // Lógica de manejo de clics aquí
                    }
                }
            }
        }
    }
}

@Composable
fun ClickableImage(
    resourceId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(top = 5.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// TODO: este nombre esta mal (nombre y retorna INT) o esta no es una funcion composable
// "The Compose function:
//      MUST be a noun: DoneButton()
//      NOT a verb or verb phrase: DrawTextField()
//      NOT a nouned preposition: TextFieldWithLink()
//      NOT an adjective: Bright()
//      NOT an adverb: Outside()
//      Nouns MAY be prefixed by descriptive adjectives: RoundIcon()"
@Composable
fun getImageResourceId(index: Int): Int {
    return when (index % 3) {
        0 -> R.drawable.ac
        1 -> R.drawable.puerta
        else -> R.drawable.lampara
    }
}
