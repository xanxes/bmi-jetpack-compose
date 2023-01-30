package br.senai.sp.jandira.bmicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicompose.ui.theme.BMIComposeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BMICalculator()
                }
            }
        }
    }
}

@Preview
@Composable
fun BMICalculator() {

    var weightState by rememberSaveable {
        mutableStateOf("")
    }

    var heightState by rememberSaveable {
        mutableStateOf("")
    }

    //Content
    Column (
        modifier =
        Modifier.fillMaxSize()
    ) {
        //Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bmi),
                contentDescription = "Ícone da aplicação",
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = stringResource(id = R.string.app_title),
                color = Color.Blue,
                fontSize = 32.sp,
                letterSpacing = 4.sp

            )
        }
        //Form
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp)) {
                Text(
                    text = stringResource(id = R.string.Weight),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                    OutlinedTextField(
                        value = weightState, onValueChange = {weightState = it},
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp)
                    )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = stringResource(id = R.string.height),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                    OutlinedTextField(
                        value = heightState, onValueChange = {heightState = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 22.dp),
                        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp)
                    )
            
                Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(Color(100,149,237))
                ) {
                    Text(text = stringResource(id = R.string.button_calculate),
                        color = Color.White
                    )
                }

        }

        //Footer
        Column(modifier = Modifier.padding(top = 32.dp)) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = stringResource(id = R.string.your_score),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(text = "0.00",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )

                        Text(text = "Congratulations! Your Weight is ideal!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row() {
                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(Color(106,90,205))) {
                                Text(text = stringResource(id = R.string.reset))
                            }
                            Spacer(modifier = Modifier.width(16.dp))

                            Button(onClick = { /*TODO*/ },
                                colors = ButtonDefaults.buttonColors(Color(106,90,205))) {
                                Text(text = stringResource(id = R.string.share))
                            }
                        }
                     }
                }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BMICalculatorPreview() {
    BMICalculator()
}