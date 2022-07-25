package com.cwj.composepractice

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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cwj.composepractice.ui.theme.ComposePracticeTheme

/**
 * https://developer.android.com/codelabs/jetpack-compose-basics?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-basics#0
 * Compose 기초 codelab
 */
class StepOneActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    // remember 함수는 컴포저블이 컴포지션에 유지되는 동안 작동. 컴포지션은 컴포저블의 수명주기를 관리하는 무언가(컴포저블의 트리 구조)
    // 때문에, 화면이 재구성되면 초기값으로 설정이 되는데, rememberSaveable 를 사용하면 해결
    var shouldShowOnBoard by rememberSaveable { mutableStateOf(true) }
    if(shouldShowOnBoard)
        OnBoardingScreen {
            shouldShowOnBoard = !shouldShowOnBoard
        }
    else
        Greetings()
}

@Composable
fun OnBoardingScreen(onContinueClick: ()->(Unit)){
    Surface{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClick
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun Greetings(names: List<String> = List(1000){ "$it" }){ // it is index
    // 뷰를 재사용하지않는다. 컴포저블을 방출하는것은 뷰를 인스턴스하는것보다 가볍기에, 그냥 방출해버린다
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp),
    ) {
        items(items = names){ name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String){
    Card( // card 가 내부적으로 surface 를 호출한다 -> surface 가 어느정도 지정되어있는게 card
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
fun CardContent(name: String){
    val expanded = remember { mutableStateOf(false) }
//    val extraPadding by animateDpAsState(
//        if(expanded.value) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
//                    .padding(bottom = extraPadding.coerceAtLeast(0.dp)) //패딩이 음수가 되지 않도록 해야 합니다. 패딩이 음수가 되면 앱이 다운될 수 있습니다. 이로 인해 미세한 애니메이션 버그가 발생하며 이 버그는 추후 설정 완료에서 수정할 예정입니다.
        ) {
            Text(text = "Hello,")
            Text(text = name, style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ))
            if(expanded.value){
                Text(text = stringResource(id = R.string.content))
            }
        }

        IconButton(
            onClick = {
                expanded.value = expanded.value.not()
            }
        ) {
            Icon(
                imageVector = if(expanded.value) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                contentDescription = if(expanded.value) stringResource(id = R.string.show_less) else stringResource(id = R.string.show_more)
            )
        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "StepOnePreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun StepOnePreview() {
    ComposePracticeTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    ComposePracticeTheme {
        OnBoardingScreen(onContinueClick = {})
    }
}