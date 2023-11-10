package com.example.a30daysofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysofwellness.model.ProgrammingConcept
import com.example.a30daysofwellness.model.programmingConcepts
import com.example.a30daysofwellness.ui.theme._30DaysOfWellnessTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysOfWellnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProgrammingConceptsApp(programmingConcepts)
                }
            }
        }
    }
}

@Composable
fun ProgrammingConceptsApp(programmingConcepts: List<ProgrammingConcept>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "30 Days of Wellness",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(programmingConcepts) { concept ->
                ProgrammingConceptCard(concept = concept)
                Spacer(modifier = Modifier.height(16.dp)) // Add some spacing between cards
            }
        }
    }
}

@Composable
fun ProgrammingConceptCard(concept: ProgrammingConcept) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Day ${concept.day}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = stringResource(concept.titleRes),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            )

            Image(
                painter = painterResource(concept.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    }
            ) {
                Text(
                    text = if (isExpanded) "Show Less" else "Show More",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Text(
                    text = stringResource(id = concept.summaryRes),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProgrammingConceptCardPreview() {
    _30DaysOfWellnessTheme {
        val programmingConcept = ProgrammingConcept(
            day = 1,
            titleRes = R.string.variables,
            imageRes = R.drawable.variable_image,
            summaryRes = R.string.variables_summary,
            details = ""
        )
        ProgrammingConceptCard(concept = programmingConcept)
    }
}
