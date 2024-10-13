import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcialtp3grupo10.R
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.components.Header

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindProductsScreen() {
    var showFilters by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TopAppBar(
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Header("Find Products")
                }
            },
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            SearchBar(onFilterClick = { showFilters = true })
            ProductCategories()
        }

        BottNavigationBar()
    }

    if (showFilters) {
        FilterBottomSheet(onDismiss = { showFilters = false })
    }
}

@Composable
fun Header(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SearchBar(onFilterClick: () -> Unit) {
    var searchText by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.LightGray.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                decorationBox = { innerTextField ->
                    Box {
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Search Store",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                }
            )
            IconButton(onClick = onFilterClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Filter",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ProductCategories() {
    val categories = listOf(
        Triple("Fresh Fruits & Vegetable", R.drawable.fresh_fruit_vegetable, Color(0xFFE8F5E9)),
        Triple("Cooking Oil & Ghee", R.drawable.cooking_oil_ghee, Color(0xFFFFF3E0)),
        Triple("Meat & Fish", R.drawable.meat_fish, Color(0xFFFFEBEE)),
        Triple("Bakery & Snacks", R.drawable.backery_snacks, Color(0xFFF3E5F5)),
        Triple("Dairy & Eggs", R.drawable.dairy_eggs, Color(0xFFFFFDE7)),
        Triple("Beverages", R.drawable.beverages, Color(0xFFE3F2FD))
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { (name, imageRes, backgroundColor) ->
            CategoryItem(name, imageRes, backgroundColor)
        }
    }
}

@Composable
fun CategoryItem(name: String, imageRes: Int, backgroundColor: Color) {
    val borderColor = backgroundColor.copy(alpha = 0.5f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, borderColor, RoundedCornerShape(18.dp))
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(onDismiss: () -> Unit) {
    var selectedCategories by remember { mutableStateOf(setOf("Eggs")) }
    var selectedBrands by remember { mutableStateOf(setOf("Cocola")) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxHeight(0.6f),
        containerColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Filters", style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Categories", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(8.dp))
                FilterCheckboxGroup(
                    items = listOf("Eggs", "Noodles & Pasta", "Chips & Crisps", "Fast Food"),
                    selectedItems = selectedCategories,
                    onItemSelect = { selectedCategories = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text("Brand", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(8.dp))
                FilterCheckboxGroup(
                    items = listOf("Individual Collection", "Cocola", "Ifad", "Kazi Farmas"),
                    selectedItems = selectedBrands,
                    onItemSelect = { selectedBrands = it }
                )

                Spacer(modifier = Modifier.height(80.dp)) // Space for the button
            }

            Button(
                onClick = { /* TODO: Apply filters */ onDismiss() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175))
            ) {
                Text("Apply Filter", color = Color.White)
            }
        }
    }
}

@Composable
fun FilterCheckboxGroup(
    items: List<String>,
    selectedItems: Set<String>,
    onItemSelect: (Set<String>) -> Unit
) {
    Column {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
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
                Text(item, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
