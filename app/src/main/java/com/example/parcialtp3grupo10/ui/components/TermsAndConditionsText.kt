package com.example.parcialtp3grupo10.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TermsAndConditionsText(modifier: Modifier = Modifier) {
    Text(
        text = buildAnnotatedString {
            append("By continuing you agree to our ")
            pushStyle(SpanStyle(color = Color(0xFF53B175)))
            append("Terms of Service")
            pop()
            append(" and ")
            pushStyle(SpanStyle(color = Color(0xFF53B175)))
            append("Privacy Policy")
            pop()
            append(".")
        },
        fontSize = 14.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}
