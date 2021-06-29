package com.example.selfmed

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.selfmed.model.PatientDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.Delegates

class AgeActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
        val text: TextView = findViewById(R.id.text_hide)
        val numberPicker: NumberPicker = findViewById(R.id.numberPicker)
        val next_btn: Button = findViewById(R.id.next_age)
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
        val intent = intent
        val interview_id = intent.getStringExtra("interview_id").toString()

        numberPicker.maxValue = 120
        numberPicker.minValue = 0

        next_btn.setOnClickListener {

            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)


            val ageRef = FirebaseDatabase.getInstance().reference
            val hashmap = HashMap<String, Any>()
            hashmap["age"] = numberPicker.value.toString()
            hashmap["title"] = "Results of check up"
            hashmap["interview_id"] = interview_id
            hashmap["name"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
            hashmap["time"] = formatted

            ageRef.child("interviews").child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child(interview_id).updateChildren(hashmap)
            val ageIntent = Intent(this, SignsActivity::class.java)
            ageIntent.putExtra("interview_id", interview_id)
            startActivity(ageIntent)
        }


    }
}