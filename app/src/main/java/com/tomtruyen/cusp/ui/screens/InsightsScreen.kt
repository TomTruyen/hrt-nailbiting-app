package com.tomtruyen.cusp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.tomtruyen.cusp.R

@Composable
fun InsightsScreen(onNavigateToWeeklyReview: () -> Unit, viewModel: SharedViewModel? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        // Top Bar Simulation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Insights",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            TextButton(onClick = onNavigateToWeeklyReview) {
                Text("Weekly Review")
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Date Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painterResource(R.drawable.ic_chevron_left), contentDescription = "Previous week")
            }
            Text(
                text = "May 15 - May 21",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painterResource(R.drawable.ic_chevron_right), contentDescription = "Next week")
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Overview Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OverviewCard(
                modifier = Modifier.weight(1f),
                value = "72",
                label = "Total check-ins",
                color = MaterialTheme.colorScheme.secondary
            )
            OverviewCard(
                modifier = Modifier.weight(1f),
                value = "48",
                label = "Urges caught",
                color = MaterialTheme.colorScheme.error
            )
            OverviewCard(
                modifier = Modifier.weight(1f),
                value = "21m",
                label = "Avg. awareness after starting",
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Most common triggers",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Donut Chart with Library
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val color1 = MaterialTheme.colorScheme.secondary
                val color2 = MaterialTheme.colorScheme.tertiary
                val color3 = MaterialTheme.colorScheme.error
                val color4 = MaterialTheme.colorScheme.primary
                
                val pieChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice("Working", 58f, color1),
                        PieChartData.Slice("TV", 24f, color2),
                        PieChartData.Slice("Phone", 9f, color3),
                        PieChartData.Slice("Other", 9f, color4)
                    ),
                    plotType = co.yml.charts.common.model.PlotType.Donut
                )
                val pieChartConfig = PieChartConfig(
                    isAnimationEnable = true,
                    showSliceLabels = false,
                    strokeWidth = 30f,
                    activeSliceAlpha = 1f,
                    backgroundColor = MaterialTheme.colorScheme.surface
                )
                
                DonutPieChart(
                    modifier = Modifier.size(100.dp),
                    pieChartData = pieChartData,
                    pieChartConfig = pieChartConfig
                )
                
                Spacer(modifier = Modifier.width(32.dp))
                
                // Legend
                Column {
                    LegendItem("Working", "58%", color1)
                    Spacer(modifier = Modifier.height(8.dp))
                    LegendItem("TV", "24%", color2)
                    Spacer(modifier = Modifier.height(8.dp))
                    LegendItem("Phone", "9%", color3)
                    Spacer(modifier = Modifier.height(8.dp))
                    LegendItem("Other", "9%", color4)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Most challenging time",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clock), 
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "15:00 - 18:00",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = "32% of urges",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 28.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                val barChartData = BarChartData(
                    chartData = listOf(
                        BarData(Point(0f, 10f), color = MaterialTheme.colorScheme.secondary),
                        BarData(Point(1f, 15f), color = MaterialTheme.colorScheme.secondary),
                        BarData(Point(2f, 32f), color = MaterialTheme.colorScheme.tertiary),
                        BarData(Point(3f, 20f), color = MaterialTheme.colorScheme.secondary),
                        BarData(Point(4f, 12f), color = MaterialTheme.colorScheme.secondary),
                        BarData(Point(5f, 5f), color = MaterialTheme.colorScheme.secondary)
                    ),
                    xAxisData = co.yml.charts.axis.AxisData.Builder()
                        .axisStepSize(30.dp)
                        .steps(5)
                        .bottomPadding(4.dp)
                        .backgroundColor(MaterialTheme.colorScheme.surface)
                        .build(),
                    yAxisData = co.yml.charts.axis.AxisData.Builder()
                        .steps(4)
                        .backgroundColor(MaterialTheme.colorScheme.surface)
                        .build(),
                    barStyle = BarStyle(
                        paddingBetweenBars = 8.dp,
                        barWidth = 16.dp,
                        cornerRadius = 4.dp
                    )
                )
                
                BarChart(
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    barChartData = barChartData
                )
            }
        }
        
        Spacer(modifier = Modifier.height(100.dp)) // padding for bottom nav
    }
}

@Composable
fun OverviewCard(modifier: Modifier, value: String, label: String, color: Color) {
    Card(
        modifier = modifier.aspectRatio(1f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun LegendItem(label: String, percentage: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = percentage,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
