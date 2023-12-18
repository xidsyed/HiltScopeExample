package com.example.hiltscopeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hiltscopeexample.ui.theme.HiltScopeExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var stateA: StateA

    @Inject
    lateinit var stateB: StateB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltScopeExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val valueA: Int by remember { mutableStateOf(stateA.value) }
                    val valueB: Int by remember { mutableStateOf(stateB.value) }
                    ShowState(valueA, valueB)
                }

            }
        }
    }
}

@Composable
fun ShowState(valueA: Int, valueB: Int) {
    var showStateVal by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Rotate the screen to destroy activity and re-instantiate un-scoped bindings!",
            modifier = Modifier.padding(horizontal = 64.dp)
        )
        Box(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
        ) {
            if (showStateVal) {
                Text(
                    text =
                    "State A Value (Scoped)   : $valueA \n" +
                            "State B Value (Unscoped) : $valueB",
                    maxLines = 2,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Button(
            onClick = { showStateVal = !showStateVal }
        ) {
            val string = if (showStateVal) "hide state values" else "show state values"
            Text(string)
        }
    }
}
