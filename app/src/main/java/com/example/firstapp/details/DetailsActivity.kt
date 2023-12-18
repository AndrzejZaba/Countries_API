package com.example.firstapp.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.firstapp.MainView
import com.example.firstapp.R
import com.example.firstapp.ui.theme.FirstAppTheme

class DetailsActivity : ComponentActivity() {

    private val viewModel:DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("CUSTOM_NAME") ?: "error"

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

        viewModel.loadDetailsData(name)

        setContent {
            FirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    DetailsView(
                        viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun DetailsView(viewModel: DetailsViewModel){

    val country by viewModel.liveData.observeAsState(null)


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(35, 37, 45))
            .fillMaxWidth()) {

        Row {
            AsyncImage(
                model = country?.flags?.png,
                contentDescription = "Flag of {${country?.name}}",
                placeholder = painterResource(id = R.drawable.placeholderimage),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(75.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}