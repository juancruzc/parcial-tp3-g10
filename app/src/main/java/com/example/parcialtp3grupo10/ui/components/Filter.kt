package com.example.parcialtp3grupo10.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(isOpen: Boolean, onDismiss: () -> Unit) {
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var selectedBrands by remember { mutableStateOf(setOf<String>()) }

    if (isOpen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                border = BorderStroke(2.dp, Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                        Text(
                            "Filters",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(48.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        FilterSection(
                            title = "Categories",
                            items = listOf("Eggs", "Noodles & Pasta", "Chips & Crisps", "Fast Food"),
                            selectedItems = selectedCategories,
                            onItemSelect = { selectedCategories = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        FilterSection(
                            title = "Brand",
                            items = listOf("Individual Collection", "Cocola", "Ifad", "Kazi Farmas"),
                            selectedItems = selectedBrands,
                            onItemSelect = { selectedBrands = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Apply Filter", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun FilterSection(
    title: String,
    items: List<String>,
    selectedItems: Set<String>,
    onItemSelect: (Set<String>) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF2F3F2))
    ) {
        FilterCheckboxGroup(
            items = items,
            selectedItems = selectedItems,
            onItemSelect = onItemSelect
        )
    }
}

@Composable
fun FilterCheckboxGroup(
    items: List<String>,
    selectedItems: Set<String>,
    onItemSelect: (Set<String>) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = item in selectedItems,
                    onCheckedChange = { checked ->
                        if (checked) {
                            onItemSelect(selectedItems + item)
                        } else {
                            onItemSelect(selectedItems - item)
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF53B175),
                        uncheckedColor = Color.LightGray
                    )
                )
                Text(
                    text = item,
                    modifier = Modifier.padding(start = 8.dp),
                    color = if (item in selectedItems) Color(0xFF53B175) else Color.Black
                )
            }
        }
    }
}