package com.example.learncompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import com.example.learncompose.lessons.LessonOne

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Login() {
    val context = LocalContext.current
    var user by remember { mutableStateOf("Username") }
    var pass by remember { mutableStateOf("Password") }
    val passwordVisible by rememberSaveable { mutableStateOf(false) }

    val userFocusRequester = remember { FocusRequester() }
    val passFocusRequester = remember { FocusRequester() }

    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color(android.graphics.Color.parseColor("#ffffff"))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.top_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.height(150.dp)
        )
        Text(
            text = "Welcome Back",
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color(android.graphics.Color.parseColor("#7d32a8"))
        )
        TextField(
            value = user,
            onValueChange = { text -> user = text },
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(start = 64.dp, end = 64.dp, top = 8.dp, bottom = 8.dp)
                .border(
                    1.dp, Color(android.graphics.Color.parseColor("#7d32a8")),
                    shape = RoundedCornerShape(50)
                )
                .focusRequester(userFocusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused && user == "Username") {
                        user = ""
                    }
                },
            shape = RoundedCornerShape(50),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                color = Color(android.graphics.Color.parseColor("#7d32a8")),
                fontSize = 15.sp
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = LocalContentColor.current
            )
        )
        TextField(
            value = pass,
            onValueChange = { text -> pass = text },
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(start = 64.dp, end = 64.dp, top = 8.dp, bottom = 8.dp)
                .border(
                    1.dp, Color(android.graphics.Color.parseColor("#7d32a8")),
                    shape = RoundedCornerShape(50)
                )
                .focusRequester(passFocusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused && pass == "Password") {
                        pass = ""
                    }
                },
            shape = RoundedCornerShape(50),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                color = Color(android.graphics.Color.parseColor("#7d32a8")),
                fontSize = 15.sp
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = LocalContentColor.current
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = {
                val intent = Intent(context, LessonOne::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(start = 64.dp, end = 64.dp, top = 8.dp, bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(android.graphics.Color.parseColor("#7d32a8"))
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text("Login", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Text(
            text = "Don't Remember Password? Click Here",
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            color = Color(android.graphics.Color.parseColor("#7d32a8")),
            fontSize = 15.sp
        )
        Row {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.twitter),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
        Image(
            painterResource(id = R.drawable.bottom_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}
