/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.example.team_watch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.*

import com.example.team_watch.R
import com.example.team_watch.presentation.theme.TEAM_WatchTheme
import com.example.team_watch.viewmodel.StopwatchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<StopwatchViewModel>()
            val timerState by viewModel.timerState.collectAsStateWithLifecycle()
            val stopwatchText by viewModel.stopwatchText.collectAsStateWithLifecycle()
            // Scaffold: show common ui-elements where they are supposed to
            Scaffold(

            ) {

            }
            Stopwatch(
                state = timerState,
                text = stopwatchText,
                onToggleRunning = viewModel::toggleIsRunning,
                onReset = viewModel::resetTimer,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun Stopwatch(
    state:TimerState,
    text:String,
    onToggleRunning: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onToggleRunning) {
                Icon(
                    painter = if (state == TimerState.RUNNING) {
                        painterResource(id = R.drawable.pause_24px)
                    } else {
                        painterResource(id = R.drawable.play_24px)
                    },
                    contentDescription = "Toggle stopwatch",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onReset,
                enabled = state != TimerState.RESET,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.stop_24px),
                    contentDescription = "Reset stopwatch",
                    tint = Color.White
                )
            }
        }
    }
}

