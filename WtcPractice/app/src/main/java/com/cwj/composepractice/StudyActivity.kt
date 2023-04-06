package com.cwj.composepractice

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cwj.composepractice.ui.theme.ComposePracticeTheme
import com.cwj.composepractice.ui.theme.Purple700
import com.cwj.composepractice.ui.theme.Teal200

/**
 * 07.25 월요일 Compose 강의 중 작성 코드
 */

class StudyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginView()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudyPreview() {
    ComposePracticeTheme {
        LoginView()
    }
}

@Composable
fun NamingCard(name: String, date: String) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Teal200)
            .clickable { }
            .padding(16.dp) // clickable 이후에 padding 값을 설정해야 전체 영역이 clickable 해짐
    ) {
        Surface(
            Modifier.size(50.dp),
            shape = CircleShape,
            color = Purple700.copy(alpha = 0.2f)//MaterialTheme.colors.surface.copy(alpha = 0.2f)
        ){

        }

        Column(
            Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically)) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = date,
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Composable
fun LoginView() {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {
        Column(Modifier.align(Alignment.TopCenter)) {
            Text(
                text = "우아한 테크 캠프",
                Modifier.align(CenterHorizontally),
                color = Color.Blue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = email, onValueChange = {
                email = it
            }, Modifier.fillMaxWidth(), placeholder = {
                Text(text = "이메일")
            })
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = pass, onValueChange = {
                pass = it
            }, Modifier.fillMaxWidth(), placeholder = {
                Text(text = "비밀번호")
            })
        }

        Column(Modifier.align(Alignment.BottomCenter)) {
            Button(onClick = {
                Toast.makeText(context, "건너뛰기", Toast.LENGTH_LONG).show()
            },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray
                )
            ) {
                Text(text = "건너뛰기")
            }

            Button(onClick = {
                Toast.makeText(context, "회원가입", Toast.LENGTH_LONG).show()
            },
                Modifier.fillMaxWidth(),
            ) {
                Text(text = "회원가입")
            }
        }
    }
}

/*
예제1 다빈님
Row(modifier = Modifier.padding(12.dp)) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_launcher_background
            ),
            contentDescription = "안드로이드 아이콘",
            modifier = Modifier
                .clip(CircleShape)
                .border(3.dp, MaterialTheme.colors.primary, CircleShape)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column {
            Text(
                text = "문다빈",
                color = MaterialTheme.colors.secondary
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text("1분전")
        }
    }


예제2 태우님
@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Text(
                text = "우아한테크캠프",
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Blue
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "", onValueChange = {}, placeholder = {
                    Text(text = "이메일")
                })
            Spacer(
                modifier = Modifier.width(3.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "", onValueChange = {}, placeholder = {
                    Text(text = "비밀번호")
                })
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text(text = "건너뛰기")
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "회원가입")
            }
        }
    }
}
 */

