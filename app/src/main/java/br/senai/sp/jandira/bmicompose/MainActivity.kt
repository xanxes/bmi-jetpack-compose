package br.senai.sp.jandira.bmicompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import br.senai.sp.jandira.bmicompose.utils.bmiCalculate
import br.senai.sp.jandira.bmicompose.utils.getColor
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

    var expandState by remember {
        mutableStateOf(false)
    }

    var bmiScoreState by remember {
        mutableStateOf(0.0)
    }

    var isWeightError by remember {
        mutableStateOf(false)
    }

    var isHeighError by remember {
        mutableStateOf(false)
    }




    //Objeto que controla a requisicao de foco
    val weightFocusRequester = FocusRequester()

    //Content
    Column(
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.Weight),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = weightState, onValueChange = { newWeight ->
                    var lastChar = if
                                           (newWeight.isEmpty()) {
                        isWeightError = true
                        newWeight

                    } else {
                        newWeight.get(newWeight.length - 1)
                        isWeightError = false
                    }
                    var newValue = if (lastChar == '.' || lastChar == ',')
                        newWeight.dropLast(1)
                    else newWeight
                    weightState = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(weightFocusRequester),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.MonitorWeight, contentDescription = "Email")
                },

                trailingIcon = {
                    if (isWeightError) Icon(imageVector = Icons.Rounded.Warning, contentDescription = "Oi")
                },

                isError = isWeightError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            if (isWeightError) {
                Text(
                    text = stringResource(id = R.string.weight_error),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    textAlign = TextAlign.End
                )

            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = stringResource(id = R.string.height),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = heightState, onValueChange = { newHeight ->
                    var lastChar = if (newHeight.isEmpty()) {
                        isHeighError = true
                        newHeight

                    } else {
                        newHeight.get(newHeight.length - 1)
                        isHeighError = false
                    }
                    var newValue = if (lastChar == '.' || lastChar == ',')
                        newHeight.dropLast(1)
                    else newHeight
                    heightState = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 22.dp),
                isError = isHeighError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Height, contentDescription = "Email")
                },

                trailingIcon = {
                    if (isHeighError) Icon(imageVector = Icons.Rounded.Warning, contentDescription = "Oi")
                },
            )

            if (isHeighError) {
                Text(
                    text = stringResource(id = R.string.height_error),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    textAlign = TextAlign.End,

                    )
            }



            Button(
                    onClick = {
                        isWeightError = weightState.length == 0
                        isHeighError = heightState.length == 0
                        if (isHeighError == false && isWeightError == false) {
                            bmiScoreState =
                                bmiCalculate(weightState.toInt(), heightState.toDouble())
                            expandState = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(Color(100, 149, 237))
                ) {
                    Text(
                        text = stringResource(id = R.string.button_calculate),
                        color = Color.White
                    )
                }

            }



        //Footer
            AnimatedVisibility(
                visible = expandState,
                enter = fadeIn(initialAlpha = 0.4f),
                exit = shrinkVertically(animationSpec = tween()) { fullHeight ->
                    fullHeight / 2
                },
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(top = 22.dp),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    backgroundColor = getColor(bmiScoreState)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(id = R.string.your_score),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = String.format("%.2f", bmiScoreState),
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Congratulations! Your Weight is ideal!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row() {
                            Button(
                                onClick = {
                                    expandState = false
                                    weightState = ""
                                    heightState = ""
                                    weightFocusRequester.requestFocus()
                                },
                                colors = ButtonDefaults.buttonColors(Color(106, 90, 205))
                            ) {
                                Text(text = stringResource(id = R.string.reset))
                            }
                            Spacer(modifier = Modifier.width(16.dp))

                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(Color(106, 90, 205))
                            ) {
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