package com.ibrahimengin.was.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.WASTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimengin.was.R
import com.ibrahimengin.was.ScreenHolder
import com.ibrahimengin.was.viewmodel.SharedViewModel

@Composable
fun QuestionsScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val selectedAnswer = remember { mutableStateOf("") }
    val answerExpandState = remember { mutableStateOf(false) }
    val fieldQuestionList = listOf(
        stringResource(R.string.professionalAndSolidarityAssociation),
        stringResource(R.string.educationalResearchAssociations),
        stringResource(R.string.healthcareAssociations),
        stringResource(R.string.sportsAndSportsRelatedAssociation),
        stringResource(R.string.cultureArtAndTourismAssociation),
        stringResource(R.string.animalProtectionAssociations),
        stringResource(R.string.disabilityAssociations),
        stringResource(R.string.IamNotInterested)
    )
    val context = LocalContext.current
    val successMessage = stringResource(R.string.successful)


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(stringResource(R.string.fieldQuestion), style = MaterialTheme.typography.subtitle1)
            Dropdown(
                selectedAnswer, { selectedAnswer.value = it }, answerExpandState,
                fieldQuestionList, ""
            )
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonLeadingIcon(
                    { navController.popBackStack() },
                    stringResource(R.string.goBack),
                    Icons.Filled.ArrowBack,
                    stringResource(R.string.goBack),
                    colorInput = Color.Gray
                )
                ButtonTrailingIcon(
                    {
                        val accessSVM = sharedViewModel.user!!
                        accessSVM.fieldQuestion = selectedAnswer.value

                        Firebase.firestore.collection("users").document(accessSVM.email)
                            .set(accessSVM).addOnSuccessListener {
                                Firebase.auth.createUserWithEmailAndPassword(
                                    accessSVM.email,
                                    accessSVM.password
                                ).addOnSuccessListener {
                                    Toast.makeText(context, successMessage, Toast.LENGTH_LONG)
                                        .show()
                                    navController.navigate(ScreenHolder.LoginScreen.route) {
                                        popUpTo(ScreenHolder.LoginScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }//TODO Failure ve butona tekrar tıklamayı önle loginde de aynı
                            }
                    },
                    stringResource(R.string.signup),
                    Icons.Filled.Done,
                    stringResource(R.string.signup),
                    enableInput = (selectedAnswer.value.isNotEmpty())
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuestionsScreen() {
    WASTheme {
        QuestionsScreen(rememberNavController(), SharedViewModel())
    }
}