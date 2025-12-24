package com.example.moviebooking

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviebooking.ui.theme.MovieBookingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieBookingTheme {
                app()
            }
        }
    }
}

@Composable
fun app() {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var seat by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Row {
            Text("Movie name is Hello")
            Text("  Price: 120")
        }

        Row {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter name") }
            )
        }

        Row {
            TextField(
                value = seat,
                onValueChange = { seat = it },
                label = { Text("Enter seats") }
            )
        }

        Row {
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Enter amount") }
            )
        }

        Row {
            Button(onClick = {
                val seatInt = seat.toIntOrNull()
                val amountInt = amount.toIntOrNull()

                if (
                    name.isNotEmpty() &&
                    seatInt != null &&
                    amountInt != null &&
                    amountInt == seatInt * 120
                ) {
                    val sms = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("smsto:8089861266")
                        putExtra("sms_body", "Movie booked for $name")
                    }
                    context.startActivity(sms)
                } else {
                    Toast.makeText(context, "Enter correct details", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Send")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieBookingTheme {
        app()
    }
}