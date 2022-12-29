package com.natiqhaciyef.unitedstatestaxappjetpackcompose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier,
    input: String = "0.0",
    onAction: KeyboardActions = KeyboardActions.Default,
    function: (String) -> Unit
) {
    OutlinedTextField(
        value = input,
        onValueChange = function,
        modifier = modifier,
        shape = MaterialTheme.shapes.small.copy(
            CornerSize(12.dp)
        ),
        label = { Text("Insert budget") },
        placeholder = { Text("0.00") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black,
            focusedBorderColor = Color(0xffa7d6ff)
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AttachMoney,
                contentDescription = "Money"
            )
        },
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun CustomText(
    text: String, modifier: Modifier,
    textAlign: TextAlign = TextAlign.Center,
    fontSize: Int, fontWeight: FontWeight
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        style = MaterialTheme.typography.h4,
        fontWeight = fontWeight,
        fontSize = fontSize.sp
    )
}

@Composable
fun CustomBillText(text: String, modifier: Modifier, fontSize: Int, fontWeight: FontWeight) {
    Text(
        text = "$ $text",
        modifier = modifier,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        fontWeight = fontWeight,
        fontSize = fontSize.sp
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownMenu(
    list: List<String>,
    selectedOption: MutableState<String>
) {
    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .border(1.dp, Color(0xffa7d6ff), shape = RoundedCornerShape(10.dp)),
        expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = selectedOption.value,
            onValueChange = { },
            readOnly = true,
            label = { Text(text = "Categories") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black
            )
        )

        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
            list.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    }) {
                    Text(
                        text = option
                    )
                }
            }
        }

    }
}

@Composable
fun CustomButton(modifier: Modifier, onClickAction: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClickAction,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xffa7d6ff)
        )
    ) {
        Text(text = "Calculate", color = Color.Black, fontSize = 17.sp)
    }
}