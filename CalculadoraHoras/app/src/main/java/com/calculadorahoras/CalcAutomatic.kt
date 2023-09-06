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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcAutomatic(navController: NavHostController){
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
        TitleCalc("Preenc. Automatico")


Button(modifier = Modifier.align(Alignment.Start),
    onClick = {
    navController.navigate("CalcScreen")

}) {
    Text(text = "Calculadora Horas")

}

    Text(text = "Preencha 3 horarios e clique em Verificar")
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
        Button(modifier = Modifier.padding(top = 5.dp),

            onClick = {

                //checkalmoco
                if (saidaAlmocoHoras != "" && voltaAlmocoHoras != "") {
                    avisoExibido = CheckIntervaloAlmoco(
                        saidaAlmocoHoras, saidaAlmocoMinutos,
                        voltaAlmocoHoras, voltaAlmocoMinutos
                    )
                }


            //condicionais

                if (entradaHoras == ""){
                    resultadoExibido = PreencherEntrada(
                        saidaAlmocoHoras, saidaAlmocoMinutos,
                        voltaAlmocoHoras, voltaAlmocoMinutos,
                        saidaHoras, saidaMinutoss
                    )
                }

                if (saidaAlmocoHoras == ""){
                    resultadoExibido = PreencherSaidaAlmoco(
                        entradaHoras, entradaMinutos,
                        voltaAlmocoHoras, voltaAlmocoMinutos,
                        saidaHoras, saidaMinutoss
                    )
                }

                if (voltaAlmocoHoras == ""){
                    resultadoExibido =  PreencherVoltaAmoloco(
                        entradaHoras, entradaMinutos,
                        saidaAlmocoHoras, saidaAlmocoMinutos,
                        saidaHoras, saidaMinutoss
                    )
                }
                if (saidaHoras == ""){

                    resultadoExibido = PreencherSaida(
                        entradaHoras, entradaMinutos,
                        saidaAlmocoHoras, saidaAlmocoMinutos,
                        voltaAlmocoHoras, voltaAlmocoMinutos
                    )
                }


        }) {

            Text(text = "Verificar", fontSize = 25.sp)

        }
        Text(text = resultadoExibido, fontSize = 30.sp)
        Text(text = avisoExibido, fontSize = 30.sp, color = Color.Red)




    }

}

fun PreencherSaida(
    entradaHoras: String, entradaMinutos: String,
    saidaAlmocoHoras: String, saidaAlmocoMinutos: String,
    voltaAlmocoHoras: String, voltaAlmocoMinutos: String): String{

    var horasTrabalhadas = saidaAlmocoHoras.toInt() - entradaHoras.toInt()

    var totalminutos = ((entradaMinutos.toInt()) + (-1 * saidaAlmocoMinutos.toInt()))


    var horasFaltantes = 8 - horasTrabalhadas


    var saidaHoras = voltaAlmocoHoras.toInt() + horasFaltantes
    var saidaMinutos = totalminutos + voltaAlmocoMinutos.toInt()

    var horarioVoltaMinutos = (saidaHoras * 60) + (saidaMinutos)
    var horarioEmHoras = minutosParaHorasEMinutos(horarioVoltaMinutos)

    return "Horario da saida é $horarioEmHoras"

}

fun PreencherVoltaAmoloco(
    entradaHoras: String,
    entradaMinutos:String,
    saidaAlmocoHoras:String,
    saidaAlmocoMinutos: String,
    saidaHoras: String,
    saidaMinutos: String): String{

    var horasTrabalhadas = saidaAlmocoHoras.toInt() - entradaHoras.toInt()

    var totalminutos = ((entradaMinutos.toInt()) + (-1 * saidaAlmocoMinutos.toInt()))

    var horasFaltantes = 8 - horasTrabalhadas

    var voltaAlmociHoras = saidaHoras.toInt() - horasFaltantes
    var voltaAmocoMinutos = saidaMinutos.toInt() - totalminutos

    var horarioVoltaMintos = (voltaAlmociHoras * 60) + (voltaAmocoMinutos)
    var horarioEmHoras = minutosParaHorasEMinutos(horarioVoltaMintos)

    return "Volta do Almoco $horarioEmHoras"
}

fun PreencherSaidaAlmoco(
    entradaHoras: String,
    entradaMinutos:String,
    voltaAlmocoHoras: String,
    voltaAlmocoMinutos: String,
    saidaHoras: String,
    saidaMinutos: String
): String{
    var horasTrabalhadas = saidaHoras.toInt() - voltaAlmocoHoras.toInt()

    var totalminutos = ((voltaAlmocoMinutos.toInt()) + (-1 * saidaMinutos.toInt()))

    var horasFaltantes = 8 - horasTrabalhadas

    var saidaSaidaAlmocoHoras = entradaHoras.toInt() + horasFaltantes
    var saidaAlmocoMinuts = entradaMinutos.toInt() + totalminutos

    var horarioSaidaAmocoMinuts = (60* saidaSaidaAlmocoHoras) + (saidaAlmocoMinuts)
    var horarioEmHoras = minutosParaHorasEMinutos(horarioSaidaAmocoMinuts)

    return "Saida para Almoco $horarioEmHoras"


}

fun PreencherEntrada(
    saidaAlmocoHoras:String,
    saidaAlmocoMinutos: String,
    voltaAlmocoHoras: String,
    voltaAlmocoMinutos: String,
    saidaHoras: String,
    saidaMinutos: String
): String{
    var horasTrabalhadas = saidaHoras.toInt() - voltaAlmocoHoras.toInt()

    var totalminutos = ((voltaAlmocoMinutos.toInt()) + (-1 * saidaMinutos.toInt()))

    var horasFaltantes = 8 - horasTrabalhadas

    var entradaHoras = saidaAlmocoHoras.toInt() - horasFaltantes

    var entradaMinuts = saidaAlmocoMinutos.toInt() - totalminutos

    var horarioentradaMinuts = (60* entradaHoras) + (entradaMinuts)

    var horarioEmHoras = minutosParaHorasEMinutos(horarioentradaMinuts)



    return "Entrada $horarioEmHoras "
}

fun CheckIntervaloAlmoco(
    saidaAlmocoHoras:String,
    saidaAlmocoMinutos: String,
    voltaAlmocoHoras: String,
    voltaAlmocoMinutos: String

): String{
    var intervaloMinimo = 60
    var intervaloMax = 120

    var horasIntervalo = voltaAlmocoHoras.toInt() - saidaAlmocoHoras.toInt()
    var minutosIntervalo = (-1* saidaAlmocoMinutos.toInt()+ voltaAlmocoMinutos.toInt())
    var totalmiutos = (horasIntervalo * 60) + minutosIntervalo

    var aviso = ""

    if(totalmiutos < intervaloMinimo) {
        aviso = "Almoco de apenas $totalmiutos minutos"
    }
    if(totalmiutos > intervaloMax) {
        var valorPassou = totalmiutos - intervaloMax
        aviso = "Almoco passou $valorPassou minutos."
    }


    return aviso
}