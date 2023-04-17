package com.codelabs.basicstatecodelab

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codelabs.basicstatecodelab.ui.theme.BasicStateCodelabTheme

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

@Composable
fun WellnessTaskList(
    modifier: Modifier = Modifier,
    onCloseTask: (WellnessTask) -> Unit,
    list: List<WellnessTask> = remember { getWellnessTasks() }
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) {
            WellnessTaskItem(
                taskName = it.label,
                onCloseTask = { onCloseTask(it) }
            )
        }
    }
}

@Preview
@Composable
fun WellnessTaskListPreview() {
    BasicStateCodelabTheme {
        WellnessTaskList(
            onCloseTask = {},
            list = getWellnessTasks()
        )
    }
}