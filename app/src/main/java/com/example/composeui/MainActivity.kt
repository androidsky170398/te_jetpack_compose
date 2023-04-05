//from new branch switched

package com.example.composeui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Size
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Calling the composable function
            // to display element and its contents
            MyContent()
        }
    }
}

// Creating a composable
// function to display Top Bar
@Composable
fun MainContent() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("GFG | Date Picker", color = Color.White) }, backgroundColor = Color(
            0xFF0AB060
        )
        ) },
        content = { MyContent() }
    )
}

// Creating a composable function to
// create two Images and a spacer between them
// Calling this function as content
// in the above function
@Composable
fun MyContent(){
    val defaultSpacerSize = 16.dp

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )
// Value for storing time as a string
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val mTime = remember { mutableStateOf("") }
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(onClick = {
            mDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF673AB7)) ) {
            Text(text = "Open Date Picker", color = Color(0xFFEEE9E9))
        }

        // Adding a space of 100dp height
        Spacer(modifier = Modifier.size(10.dp))

        // Displaying the mDate value in the Text
        Text(text = "Selected Date: ${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)


        val imageModifier = Modifier
            .heightIn(min = 10.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = painterResource(id = R.drawable.androidimg),
            contentDescription = "Image put", // decorative
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier.padding(0.dp)

        )

        val inputvalue1 = remember { mutableStateOf(TextFieldValue()) }


        OutlinedTextField(
            value = inputvalue1.value,
            onValueChange = { inputvalue1.value = it},
            label = { Text(text = "Phone Number") },
            placeholder = { Text(text = "Phone Number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.padding(1.dp))

        val inputvalue2 = remember { mutableStateOf(TextFieldValue()) }


        OutlinedTextField(
            value = inputvalue2.value,
            onValueChange = { inputvalue2.value = it},
            label = { Text(text = "Email Address") },
            placeholder = { Text(text = "Email Address") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.padding(5.dp))
        var a="shailendra"
        val mContext = LocalContext.current

        Button(
            onClick = {
                if (inputvalue1.value.equals("9140")) {
                    Toast.makeText(mContext, "Please enter valid Phone number", Toast.LENGTH_SHORT).show()
                } else if (inputvalue2.value.equals("hereshailendra@gmail.com")) {
                    Toast.makeText(mContext, "Please enter valid Mail-ID", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(mContext, "Logged Successfully!", Toast.LENGTH_SHORT).show()
                    mContext.startActivity(Intent(mContext, SecondActivity::class.java))

                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            enabled = true,
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.White))
        ) {
            Text(
                text = "Login", fontSize = 25.sp,
                color = Color(0xFFF5EAEA)
            )
        }
    }


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {

        // On button click, TimePicker is
        // displayed, user can select a time
        Button(onClick = { mTimePickerDialog.show() }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(
            0xFF929097
        )
        )) {
            Text(
                text = "Open Time Picker",
                color = Color.White,
                modifier = Modifier
                           .padding(5.dp)
            )
        }

        // Add a spacer of 100dp
        Spacer(modifier = Modifier.size(10.dp))

        // Display selected time
        Text(text = "Selected Time: ${mTime.value}", fontSize = 30.sp)
    }

}

// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyContent()
}