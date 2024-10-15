package com.example.parcialtp3grupo10.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(isOpen: Boolean,
                      onDismiss: () -> Unit,
                      navController: NavController) {
    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var selectedBrands by remember { mutableStateOf(setOf<String>()) }

    if (isOpen) {
        Dialog(onDismissRequest = onDismiss,
                properties = DialogProperties(usePlatformDefaultWidth = false) ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
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
                        onClick = {navController.navigate("search")
                                  onDismiss()},
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
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundedSquareCheckbox(
                    checked = item in selectedItems,
                    onCheckedChange = { checked ->
                        if (checked) {
                            onItemSelect(selectedItems + item)
                        } else {
                            onItemSelect(selectedItems - item)
                        }
                    }
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

@Composable
fun RoundedSquareCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(8.dp)
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(shape)
            .border(2.dp, if (checked) Color(0xFF53B175) else Color.Gray, shape)
            .background(if (checked) Color(0xFF53B175) else Color.Transparent)
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                Icons.Default.Check,
                contentDescription = "Checked",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}