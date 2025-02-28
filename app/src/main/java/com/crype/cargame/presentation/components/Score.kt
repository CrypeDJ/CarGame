package com.crype.cargame.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crype.cargame.presentation.viewmodel.GameViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Score(
    viewModel: GameViewModel = koinViewModel()
) {
    val score by viewModel.score.collectAsState()

    Text(
        text = score.toString(),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .padding(16.dp)
    )
}