
package com.example.twitchclone.I_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.example.twitchclone.R
import com.example.twitchclone.data.Transmission
import com.example.twitchclone.rv_activity.RecyclerViewActivity
import com.example.twitchclone.utils.showToast



class MainActivity : ComponentActivity() {

    private val postList = arrayListOf<Transmission>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillPostList()
        setContent {
            MainScreen(postList) { navItem ->
                when (navItem) {
                    "RecyclerView" -> startActivity(Intent(this, RecyclerViewActivity::class.java))
                    else -> showToast(navItem)
                }
            }
        }
    }

    private fun fillPostList() {
        val titles = listOf(
            "Ninja", "Ibai", "auronplay", "Rubius", "KaiCenat", "xQc", "TheGrefg", "Tfue",
            // ...
        )
        val imageResIds = listOf(
            R.drawable.ninja,
            R.drawable.ibai,
            R.drawable.auronplay,
            R.drawable.rubius,
            R.drawable.kaicenat,
            // ...
        )
        for (i in titles.indices) {
            postList.add(Transmission(imageResIds[i], titles[i]))
        }
    }
}


@Composable
fun MainScreen(postList: List<Transmission>, onNavItemClick: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {

        Header()
        CategorySection()
        ChannelGrid(postList)
        BottomNavBar(onNavItemClick)
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icons8_search_50),
            contentDescription = "Search icon",
            modifier = Modifier.size(25.dp),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text("Buscar", fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun CategorySection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        ) {
            Text("Categorías", fontSize = 12.sp)
        }
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        ) {
            Text("Canales en vivo", fontSize = 12.sp)
        }
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        ) {
            Text("Filtro", fontSize = 12.sp)
        }
    }
}


@Composable
fun ChannelGrid(postList: List<Transmission>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.padding(8.dp)) {
        items(postList.size) { index ->
            val post = postList[index]
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { /* Click action */ }
            ) {
                Image(
                    painter = painterResource(id = post.imageResId),
                    contentDescription = post.title,
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.DarkGray),
                    contentScale = ContentScale.Crop
                )
                Text(post.title, color = Color.White, fontSize = 12.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun BottomNavBar(onNavItemClick: (String) -> Unit) {
    var selectedItem by remember { mutableStateOf("Home") } // Estado para el ítem seleccionado

    BottomNavigation(
        backgroundColor = Color.DarkGray,
        contentColor = Color.White,
        modifier = Modifier.height(56.dp)
    ) {
        val items = listOf("Home", "RecyclerView", "Activity", "Explore", "Profile")
        items.forEach { item ->
            BottomNavigationItem(
                icon = {R.drawable.rubius },
                label = { Text(item) },
                selected = selectedItem == item, // Marca el ítem como seleccionado
                onClick = {
                    selectedItem = item // Actualiza el ítem seleccionado
                    onNavItemClick(item)
                }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(icon: () -> Unit, label: @Composable () -> Unit, selected: Boolean, onClick: () -> Unit) {

}

@Composable
fun BottomNavigation(backgroundColor: Color, contentColor: Color, modifier: Modifier, content: @Composable () -> Unit) {

}

