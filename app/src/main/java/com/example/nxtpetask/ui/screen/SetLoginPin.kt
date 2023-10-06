package com.example.nxtpetask.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.nxtpetask.R
import com.example.nxtpetask.ui.theme.Grey
import com.example.nxtpetask.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SetLoginPin() {
    var isBtnEnabled by remember { mutableStateOf(false) }
    var secretQuestionAnswer by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp)
            .scrollable(scrollState, Orientation.Vertical)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pin_img),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth()
        )

        Card(
            shape = RoundedCornerShape(2.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {

                SubHeading(text = "SETUP LOGIN PIN")

                PinField(heading = "Enter PIN")

                PinField(heading = "Confirm PIN")
            }
        }

        Card(
            shape = RoundedCornerShape(2.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {

                SubHeading(text = "SECRET QUESTIONS")

                CustomDropDown(Modifier.padding(bottom = 16.dp, top = 16.dp))

                TextField(
                    value = secretQuestionAnswer,
                    onValueChange = { secretQuestionAnswer = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Orange
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                )
            }
        }
    }

//    Button(
//        onClick = {},
//        enabled = isBtnEnabled,
//        shape = RoundedCornerShape(4.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Orange,
//            contentColor = Color.White
//        ),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Text(
//            text = "Proceed",
//            fontSize = 18.sp,
//            color = Color.White,
//            modifier = Modifier.padding(vertical = 16.dp)
//        )
//    }
}

@Composable
fun SubHeading(text: String) {
    Row {
        Spacer(
            modifier = Modifier
                .padding(end = 8.dp)
                .background(Orange)
                .size(2.5.dp, 24.dp)
        )

        Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinField(heading: String) {
    val noOfDigits = 6
    val otpValues = remember { mutableStateListOf("", "", "", "", "", "") }
    val focusManager = LocalFocusManager.current
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        Row(Modifier.fillMaxWidth()) {

            Text(
                text = heading,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .weight(2f)
                    .padding(bottom = 12.dp)
            )

            Icon(
                painter = if (isPasswordVisible) painterResource(id = R.drawable.eye) else painterResource(
                    id = R.drawable.hide
                ),
                contentDescription = "Toggle Visibility",
                tint = Grey,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { isPasswordVisible = !isPasswordVisible }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(6) {
                Card(
                    shape = RoundedCornerShape(2.dp),
                    elevation = CardDefaults.elevatedCardElevation(4.dp),
                    modifier = if (it == noOfDigits - 1) Modifier.weight(1f)
                    else Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                ) {
                    TextField(
                        value = otpValues[it], onValueChange = { value ->
                            otpValues[it] = if (value.isNotEmpty()) value.last().toString() else ""
                            if (value.isNotEmpty()) focusManager.moveFocus(FocusDirection.Right)
                            else focusManager.moveFocus(FocusDirection.Left)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = if (it == noOfDigits - 1) ImeAction.Done else ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (it < noOfDigits - 1) {
                                    focusManager.moveFocus(FocusDirection.Right)
                                }
                            }
                        ),
                        singleLine = true,
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(
                            mask = '*'
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Orange
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDown(modifier: Modifier = Modifier) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var dropDownText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val dropDownIcon =
        if (isDropDownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    val dataList = listOf(
        "Mother last name",
        "Father last name",
        "Pet name",
        "Home town"
    )

    TextField(
        value = dropDownText,
        onValueChange = { dropDownText = it },
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = RoundedCornerShape(4.dp))
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            }
            .background(Color.White),
        readOnly = true,
        placeholder = {
            Text(
                "Select Question",
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            )
        },

        trailingIcon = {
            Icon(dropDownIcon, "contentDescription",
                Modifier.clickable { isDropDownExpanded = !isDropDownExpanded })
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )

    DropdownMenu(
        expanded = isDropDownExpanded,
        onDismissRequest = { isDropDownExpanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            .background(Color.White)
            .clickable { isDropDownExpanded = !isDropDownExpanded }
    ) {
        dataList.forEach {
            DropdownMenuItem(
                onClick = {
                    dropDownText = it
                    isDropDownExpanded = false
                },
                text = {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = Modifier.background(Color.White)
                    )
                },
                colors = MenuDefaults.itemColors()
            )
        }
    }
}