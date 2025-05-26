package me.fernandesleite.mahoulist.core.navigation.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTopBar(
    modifier: Modifier = Modifier,
    toggleButtonSheet: () -> Unit,
    title: String,
    onBackPressed: () -> Unit,
    ) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackPressed()
            }) {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onPrimary)
            }
        },
        actions = {
            IconButton(onClick = { toggleButtonSheet() }) {
                Icon(imageVector = Icons.Default.GridOn, contentDescription = "Search")
            }
        },
    )
}