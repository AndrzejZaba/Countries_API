package com.example.firstapp.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firstapp.MainView
import com.example.firstapp.R
import com.example.firstapp.ui.theme.FirstAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : ComponentActivity() {

    private val viewModel:DetailsViewModel by viewModel()

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
fun DetailsView(viewModel: DetailsViewModel) {

    val country by viewModel.immutableCountriesData.observeAsState()//null)


    if (country?.isLoading == true) {
        // Display loading indicator
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(16.dp)

        )
    } else {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(35, 37, 45))
                .fillMaxWidth()
        ) {

            Box( modifier =  Modifier.width(350.dp).padding(top = 50.dp),
                contentAlignment = Alignment.Center){
                Text(text = "${country?.data?.name?.common}",
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.padding(top = 20.dp)) {
                AsyncImage(
                    model = country?.data?.flags?.png,
                    contentDescription = "Flag of {${country?.data?.name}}",
                    placeholder = painterResource(id = R.drawable.placeholderimage),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(260.dp)
                        .height(150.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Row(modifier = Modifier.padding(top = 20.dp)){
                Text(text = "Continent: ",
                    color = Color.White,
                    fontSize = 24.sp)
                Text(text = "${country?.data?.continents?.firstOrNull()}",
                    color = Color.White,
                    fontSize = 24.sp)
            }
            Row(modifier = Modifier.padding(top = 20.dp)){
                Text(text = "Capital: ",
                    color = Color.White,
                    fontSize = 24.sp)
                Text(text = "${country?.data?.capital?.firstOrNull()}",
                    color = Color.White,
                    fontSize = 24.sp)
            }
            Row(modifier = Modifier.padding(top = 20.dp)){
                Text(text = "Population: ",
                    color = Color.White,
                    fontSize = 24.sp)
                Text(text = "${country?.data?.population.toString()}",
                    color = Color.White,
                    fontSize = 24.sp)
            }
            Row(modifier = Modifier.padding(top = 20.dp)){
                Text(text = "Area: ",
                    color = Color.White,
                    fontSize = 24.sp)
                Text(text = "${country?.data?.area.toString()}",
                    color = Color.White,
                    fontSize = 24.sp)
            }
            Row(modifier = Modifier.padding(top = 20.dp)){
                Text(text = "Region: ",
                    color = Color.White,
                    fontSize = 24.sp)
                Text(text = "${country?.data?.region}",
                    color = Color.White,
                    fontSize = 24.sp)
            }
            Row(modifier = Modifier.padding(top = 20.dp)){
                Text(text = "Subregion: ",
                    color = Color.White,
                    fontSize = 24.sp)
                Text(text = "${country?.data?.subregion}",
                    color = Color.White,
                    fontSize = 24.sp)
            }
        }
    }
}