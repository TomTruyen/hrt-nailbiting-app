package com.tomtruyen.cusp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AfterInterruptScreen(onSave: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "How strong is the urge now?",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            EmojiOption("Gone", "🙂", MaterialTheme.colorScheme.secondaryContainer)
            EmojiOption("Better", "😐", MaterialTheme.colorScheme.tertiaryContainer)
            EmojiOption("Still strong", "😣", MaterialTheme.colorScheme.errorContainer)
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "What were you doing?",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ChipOption(modifier = Modifier.weight(1f), label = "Working", selected = true)
                ChipOption(modifier = Modifier.weight(1f), label = "Watching TV", selected = false)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ChipOption(modifier = Modifier.weight(1f), label = "Phone", selected = false)
                ChipOption(modifier = Modifier.weight(1f), label = "Driving", selected = false)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ChipOption(modifier = Modifier.weight(1f), label = "Reading", selected = false)
                ChipOption(modifier = Modifier.weight(1f), label = "Gaming", selected = false)
            }
            ChipOption(modifier = Modifier.fillMaxWidth(), label = "Other", selected = false)
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "How were you feeling?",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ChipOption(modifier = Modifier.weight(1f), label = "Calm", selected = false)
                ChipOption(modifier = Modifier.weight(1f), label = "Bored", selected = true)
                ChipOption(modifier = Modifier.weight(1f), label = "Stressed", selected = false)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ChipOption(modifier = Modifier.weight(1f), label = "Tired", selected = false)
                ChipOption(modifier = Modifier.weight(1f), label = "Focused", selected = false)
                ChipOption(modifier = Modifier.weight(1f), label = "Anxious", selected = false)
            }
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Save",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun EmojiOption(label: String, emoji: String, color: androidx.compose.ui.graphics.Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 32.sp) 
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ChipOption(modifier: Modifier, label: String, selected: Boolean) {
    val bgColor = if (selected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
    val textColor = if (selected) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
    Box(
        modifier = modifier
            .background(bgColor, RoundedCornerShape(12.dp))
            .clickable { }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }
}
