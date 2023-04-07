package com.codelab.basics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basics.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    Surface(modifier = modifier) {
        when (shouldShowOnBoarding) {
            true -> OnboardingScreen(onContinueClick = { shouldShowOnBoarding = false })
            else -> Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClick
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    Surface(
        modifier = modifier.padding(vertical = 4.dp),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items(names) {
                Greeting(it)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        CardContent(name = name)
    }
}

@Composable
fun CardContent(name: String) {
    val expended = remember { mutableStateOf(false) }
    val description = ("Composem ipsum color sit lazy, " +
        "padding theme elit, sed do bouncy. ").repeat(4)

    Row(modifier = Modifier.padding(12.dp)) {
        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .animateContentSize()
            .padding(12.dp)
        ) {
            Text(text = "Hello")
            Text(text = name, style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.ExtraBold
            ))
            if(expended.value) {
                Text(text = description)
            }
        }
        MoreButton(
            onClick = { expended.value = !expended.value },
            icon = if(expended.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescriptor = if(expended.value)
                stringResource(id = R.string.show_less)
            else
                stringResource(id = R.string.show_more)
        )
    }
}

@Composable
fun MoreButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescriptor: String
) {
    IconButton(
        modifier = modifier, onClick = onClick
    ) {
        Icon(imageVector = icon, contentDescription = contentDescriptor)
    }
}

@Composable
fun ElevatedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = ""
) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 20.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 15.dp,
            focusedElevation = 10.dp
        ),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.background),
        shape = RoundedCornerShape(50),
        modifier = modifier
    ) {
        if(text.isNotBlank())
            Text(text)
    }
}

@Preview(showBackground = true, widthDp = 320, name = "OnBoarding")
@Composable
fun OnBoardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClick = {})
    }
}

@Preview(showBackground = true, widthDp = 320, name = "OnBoardingDark", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingDarkPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClick = {})
    }
}

@Preview(showBackground = true, widthDp = 320, name = "Greetings")
@Composable
fun GreetingsPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, name = "Greetings", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingsDarkPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}