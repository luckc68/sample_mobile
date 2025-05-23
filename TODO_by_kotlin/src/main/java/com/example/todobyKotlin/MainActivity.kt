package com.example.todobyKotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todobyKotlin.ui.theme.TODObyKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODObyKotlinTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TodoApp()
                }
            }
        }
    }
}

@Composable
fun TodoApp() {
    var todos by remember { mutableStateOf(listOf<String>()) }
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )
            Button(onClick = {
                if (text.isNotEmpty()) {
                    todos = todos + text
                    text = ""
                }
            }) {
                Text("Add")
            }
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(todos.size) { index ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(todos[index], modifier = Modifier.weight(1f))
                    Button(onClick = {
                        todos = todos.toMutableList().also { it.removeAt(index) }
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
