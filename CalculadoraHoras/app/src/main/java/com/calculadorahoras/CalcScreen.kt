package com.calculadorahoras

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.w3c.dom.Text


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcScreen(navController: NavHostController){
    var entradaHoras by remember { mutableStateOf("") }
    var entradaMinutos by remember { mutableStateOf("") }
    var saidaAlmocoHoras by remember { mutableStateOf("") }
    var saidaAlmocoMinutos by remember { mutableStateOf("") }
    var voltaAlmocoHoras by remember { mutableStateOf("") }
    var voltaAlmocoMinutos by remember { mutableStateOf("") }
    var saidaHoras by remember { mutableStateOf("") }
    var saidaMinutoss by remember { mutableStateOf("") }

    var resultadoExibido by remember { mutableStateOf("") }
    var avisoExibido by remember { mutableStateOf("") }






    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleCalc("Calculadora Horas")


        Button(modifier = Modifier.align(Alignment.Start),
            onClick = {
                navController.navigate("CalcAutomatic")

            }) {
            Text(text = "Preenc. Automatico")

        }

        //entrada
        Text(text = "Entrada")
        Row() {
            
            TextField(modifier = Modifier
                .width(200.dp)
                .padding(end = 5.dp),
                value = entradaHoras,
                onValueChange = { entradaHoras = it },
                label = { Text("HORAS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            TextField(
                value = entradaMinutos,
                onValueChange = { entradaMinutos = it },
                label = { Text("MINUTOS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

        }
        //almoço
        Text(text = "Saída para Almoço")
        Row() {

            TextField(modifier = Modifier
                .width(200.dp)
                .padding(end = 5.dp),
                value = saidaAlmocoHoras,
                onValueChange = { saidaAlmocoHoras = it },
                label = { Text("HORAS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            TextField(
                value = saidaAlmocoMinutos,
                onValueChange = { saidaAlmocoMinutos = it },
                label = { Text("MINUTOS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

        }
        //volta do almoço
        Text(text = "Volta do Almoço")
        Row() {

            TextField(modifier = Modifier
                .width(200.dp)
                .padding(end = 5.dp),
                value = voltaAlmocoHoras,
                onValueChange = { voltaAlmocoHoras = it },
                label = { Text("HORAS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            TextField(
                value = voltaAlmocoMinutos,
                onValueChange = { voltaAlmocoMinutos = it },
                label = { Text("MINUTOS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

        }
        //Saída
        Text(text = "Saída")
        Row() {

            TextField(modifier = Modifier
                .width(200.dp)
                .padding(end = 5.dp),
                value = saidaHoras,
                onValueChange = { saidaHoras = it },
                label = { Text("HORAS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            TextField(
                value = saidaMinutoss,
                onValueChange = { saidaMinutoss = it },
                label = { Text("MINUTOS") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

        }
        Button(onClick = {
            var resultadoDaSoma = ""

            //checkalmoco
            if (saidaAlmocoHoras != "" && voltaAlmocoHoras != "") {
                avisoExibido = CheckIntervaloAlmoco(
                    saidaAlmocoHoras, saidaAlmocoMinutos,
                    voltaAlmocoHoras, voltaAlmocoMinutos
                )
            }

            val checkCampos = verificarCampos(
                entradaHoras, entradaMinutos,
                saidaAlmocoHoras, saidaAlmocoMinutos,
                voltaAlmocoHoras, voltaAlmocoMinutos,
                saidaHoras, saidaMinutoss
            )
            if (checkCampos == false){
                resultadoDaSoma =   RealizarCalculo(
                    entradaHoras, entradaMinutos,
                    saidaAlmocoHoras, saidaAlmocoMinutos,
                    voltaAlmocoHoras, voltaAlmocoMinutos,
                    saidaHoras, saidaMinutoss
                )


            } else{
                resultadoDaSoma = AlertaCampos()

            }





            resultadoExibido = resultadoDaSoma
        }) {
            Text(text = "Calcular", fontSize = 25.sp)

        }

        Text(text = resultadoExibido, fontSize = 30.sp)
        Text(text = avisoExibido, fontSize = 30.sp, color = Color.Red)



    }


}




@Composable
fun TitleCalc(text: String){
    Text(text = text, fontSize = 40.sp)
}


fun AlertaCampos(): String{

    return  "Preencha todos campos!"

}

fun verificarCampos(
    entradaHoras: String,
    entradaMinutos:String,
    saidaAlmocoHoras:String,
    saidaAlmocoMinutos: String,
    voltaAlmocoHoras: String,
    voltaAlmocoMinutos: String,
    saidaHoras: String,
    saidaMinutos: String

): Boolean{
   var alerta = false

    if(entradaHoras == ""){
        alerta = true
    }
    if(entradaMinutos == ""){
        alerta = true
    }
    if(saidaAlmocoHoras == ""){
        alerta = true
    }
    if(saidaAlmocoMinutos == ""){
        alerta = true
    }
    if(voltaAlmocoHoras == ""){
        alerta = true
    }

    if(voltaAlmocoMinutos == ""){
        alerta = true
    }
    if(saidaHoras == ""){
        alerta = true
    }
    if(saidaMinutos == ""){
        alerta = true
    }

    return alerta


}

fun RealizarCalculo(
    entradaHoras: String,
    entradaMinutos:String,
    saidaAlmocoHoras:String,
    saidaAlmocoMinutos: String,
    voltaAlmocoHoras: String,
    voltaAlmocoMinutos: String,
    saidaHoras: String,
    saidaMinutos: String,
    ): String {


//somar todas horas
    var totalEmHoras = ((-1 * entradaHoras.toInt()) + saidaAlmocoHoras.toInt()) + ((-1* voltaAlmocoHoras.toInt()) + saidaHoras.toInt())

//somar dos minutos
    var totalminutos = ((-1 * entradaMinutos.toInt()) + saidaAlmocoMinutos.toInt()) + ((-1 * voltaAlmocoMinutos.toInt()) + saidaMinutos.toInt())



    var totalEmMinutos = (totalEmHoras * 60) + totalminutos

    var resultadoConvertido = minutosParaHorasEMinutos(totalEmMinutos)


    return "Você trabalhou ${resultadoConvertido}"


}


fun minutosParaHorasEMinutos(totalMinutos: Int): String {
    val horas = totalMinutos / 60
    val minutos = totalMinutos % 60
    return String.format("%02d:%02d", horas, minutos)
}