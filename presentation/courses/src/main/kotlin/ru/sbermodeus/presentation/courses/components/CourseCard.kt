package ru.sbermodeus.presentation.courses.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sbermodeus.domain.model.Course

@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = course.name)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = course.description)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Навыки для прохождения:", modifier = Modifier.padding(bottom = 2.dp))
            course.requiredSkillLevels.forEach {
                Text("— ${it.name}: уровень ${it.level}/5")
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("Навыки после курса:", modifier = Modifier.padding(bottom = 2.dp))
            course.givenSkillLevels.forEach {
                Text("— ${it.name}: уровень ${it.level}/5")
            }
        }
    }
}