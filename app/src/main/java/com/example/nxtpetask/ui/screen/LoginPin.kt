package com.example.nxtpetask.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nxtpetask.R

@Preview(showBackground = true)
@Composable
fun SetUpPinPage() {
    var pinValue by remember {
        mutableStateOf("")
    }
    var confirmValue by remember {
        mutableStateOf("")
    }
    var visible by remember {
        mutableStateOf(true)
    }
    var mSelectedText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxSize(),

        ) {
        Column(modifier = Modifier.weight(2f)) {

//            Image(
//                painter = rememberAsyncImagePainter(""),
//                contentDescription = null,
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(top = 40.dp)
//                    .width(250.dp)
//                    .height(70.dp),
//                contentScale = ContentScale.FillBounds
//            )
            Card( modifier = Modifier.padding(top = 24.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row {
                        Divider(
                            color = Color(android.graphics.Color.parseColor("#E18347")),
                            modifier = Modifier
                                .height(20.dp)
                                .width(2.dp)
                        )
                        Text(
                            text = "SETUP LOGIN PIN",
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                        )
                    }

                    Text(
                        text = "Enter PIN",
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        BasicTextField(value = pinValue, onValueChange = {
                            pinValue = it
                        },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword
                            ),
                            decorationBox = {
                                Row(horizontalArrangement = Arrangement.Center) {
                                    repeat(6)
                                    {

                                        var char = when {
                                            it >= pinValue.length -> ""
                                            else -> pinValue[it].toString()
                                        }
                                        val isFocused = pinValue.length == it
                                        Card(
                                            modifier = Modifier.padding(6.dp),
                                        ) {
                                            Text(
                                                text = char,
                                                modifier = Modifier
                                                    .width(45.dp)
                                                    .height(55.dp)
                                                    .border(
                                                        if (isFocused) 2.dp else 1.dp,
                                                        if (isFocused) Color.DarkGray
                                                        else Color.Transparent,

                                                        )
                                                    .padding(8.dp),
                                                color = Color.Black,
                                                textAlign = TextAlign.Center,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontSize = 30.sp
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                        }

                                    }

                                }
                            }
                        )

                    }
                    Text(
                        text = "Confirm PIN",
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        BasicTextField(value = confirmValue, onValueChange = {
                            confirmValue = it
                        },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword
                            ),
                            decorationBox = {
                                Row(horizontalArrangement = Arrangement.Center) {
                                    repeat(6)
                                    {

                                        var char = when {
                                            it >= confirmValue.length -> ""
                                            else -> confirmValue[it].toString()
                                        }
                                        val isFocused = confirmValue.length == it
                                        Card(
                                            modifier = Modifier.padding(6.dp),
                                        ) {
                                            Text(
                                                text = char,
                                                modifier = Modifier
                                                    .width(45.dp)
                                                    .height(55.dp)
                                                    .border(
                                                        if (isFocused) 2.dp else 1.dp,
                                                        if (isFocused) Color.DarkGray
                                                        else Color.Transparent,

                                                        )
                                                    .padding(8.dp),
                                                color = Color.Black,
                                                textAlign = TextAlign.Center,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontSize = 30.sp
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                        }

                                    }

                                }

                            }
                        )

                    }


                }
            }

            Card(modifier = Modifier.padding(top = 24.dp)) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Row {
                        Divider(
                            color = Color(android.graphics.Color.parseColor("#E18347")),
                            modifier = Modifier
                                .height(20.dp)
                                .width(2.dp)
                        )
                        Text(
                            text = "SECRET QUESTIONS",
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                        )

                    }
//                    ExposedDropdownMenuBox("Select Question")
//                    AnimatedVisibility(true, modifier = Modifier
//                        .fillMaxWidth()
//                        .height(90.dp)) {
//                        TextField(
//                            value = mSelectedText, onValueChange = { mSelectedText = it },
//                            colors = TextFieldDefaults.textFieldColors(
//                                backgroundColor = Color.White,
//                                focusedIndicatorColor = Color.Transparent,
//                                unfocusedIndicatorColor = Color.Transparent,
//                                disabledIndicatorColor = Color.Transparent
//                            ), modifier = Modifier
//                                .padding(top = 12.dp)
//                                .shadow(16.dp)
//                        )
//                    }


                }
            }
        }


        Button(
            onClick = {
//                        navController.navigate("question_page")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(
//                    android.graphics.Color.parseColor(
//                        "#E18347"
//                    )
//                )
            )
        ) {
            Text(
                text = "Proceed",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(R.font.poppins_regular)
                )
            )
        }
    }
}