package com.example.selfmed

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.selfmed.model.PatientDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class DetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val UID = FirebaseAuth.getInstance().currentUser!!.uid


        val databaseref = FirebaseDatabase.getInstance().reference
        val interview_id: TextView = findViewById(R.id.interview_id)
        val next_1: Button = findViewById(R.id.next_btn_1)
        val key = databaseref.push().key

        interview_id.text = "Interview ID: " + key.toString()
        next_1.setOnClickListener {
            val hashMap = HashMap<String, Any>()
            hashMap["interview_id"] = key.toString()
            hashMap["userid"] = UID


            databaseref.child("interviews").child(UID).child(key.toString()).updateChildren(hashMap)
            val intent = Intent(this@DetailsActivity, TermsActivity::class.java)
            intent.putExtra("interview_id", key.toString())
            startActivity(intent)
        }


    }
}