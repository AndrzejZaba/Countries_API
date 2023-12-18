package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firstapp.details.DetailsActivity
import com.example.firstapp.ui.theme.FirstAppTheme
import com.example.firstapp.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getData()


        setContent {
            FirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    MainView(
                        viewModel = viewModel,
                        onClick = {name -> navigateToDetailsActivity(name)})
                }
            }
        }
    }

    fun navigateToDetailsActivity( name: String){
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra("CUSTOM_NAME", name)
        startActivity(detailsIntent)
    }

}


@Composable
fun MainView(viewModel:MainViewModel, onClick: (String) -> Unit, modifier: Modifier = Modifier) {

    //val countries by viewModel.immutableCountriesData.observeAsState(emptyList())
    val uiState by viewModel.immutableCountriesData.observeAsState(UiState())

    when {
        uiState.isLoading -> {
            // wywołaj funkcję która pokaże loader dla użytkownika
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp)
            )
        }
        uiState.error != null -> {
            // wywołaj funckję która pokaże komponent typu Snackbar
            Toast.makeText(LocalContext.current, "${uiState.error}", Toast.LENGTH_SHORT).show()
        }

        uiState.countries != null -> {
            uiState.countries?.let{
            // wywołaj funkcję do krajów
                LazyColumn {
                    items(uiState.countries!!) {country ->
                        ShowBlock(
                            name = country.name.common,
                            capital = country.capital?.firstOrNull(),
                            continent = country.continents?.firstOrNull(),
                            imageUrl = country.flags.png,
                            onClick = { name -> onClick.invoke(country.name.common)}
                        )
                    }
                }
            }

        }
        else -> {
            Log.e("MainView", "żaden stan widoku nie został zdefiniowany $uiState")
        }
    }

    // LOGS with countries data
//    if (countries.isNotEmpty()){
//        countries.forEachIndexed {index, country ->
//            //ShowBlock(name = country.name?.common)
//            Log.d("Main", "$index ${country.name.common}, ${country.name.official}") //
//        }
//    }
}



@Composable
fun ShowBlock(name: String?, capital: String?, continent: String?, imageUrl: String, onClick: (String) -> Unit,  modifier: Modifier = Modifier){



    Card(modifier = Modifier
        .padding(all = 10.dp)
        .fillMaxWidth()
        .clickable {
            onClick.invoke(name?: "")
        }
    ) {

        Column(modifier = Modifier.width(400.dp) ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color(35, 37, 45))
                    .fillMaxWidth()) {

                Row {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Flag of {$name}",
                        placeholder = painterResource( id = R.drawable.placeholderimage),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(150.dp)
                            .height(75.dp)
                            .align(Alignment.CenterVertically)
                            //.size(150.dp)
//                            .clip(CircleShape)
//                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Column (
                        Modifier
                            .width(200.dp)
                            .padding(10.dp, 0.dp, 10.dp, 0.dp)) {
                        Box( modifier =  Modifier.width(200.dp),
                            contentAlignment = Alignment.Center){
                            Text(text = "$name",
                                textAlign = TextAlign.Center,
                                fontSize = 28.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row (modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp)) {
                            Text(text = "Continent: ",
                                color = Color.White,

                                )
                            Text(text = "$continent",
                                color = Color.White,)
                        }
                        Row (modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp)) {
                            Text(text = "Capital: ",
                                color = Color.White,

                                )
                            Text(text = "$capital",
                                color = Color.White,)
                        }
                    }

                }

            }
        } // end of block

    }



}



/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstAppTheme {
        //Showcase("Android", viewModel = )
    }
}
 */

