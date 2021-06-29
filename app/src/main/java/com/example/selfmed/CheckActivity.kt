package com.example.selfmed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        val databaseref = FirebaseDatabase.getInstance().reference


        val intent = intent
        val id = intent.getStringExtra("interview_id").toString()
        Log.e("tag", id)
        val myself: CheckBox = findViewById(R.id.myself)
        val someone_else: CheckBox = findViewById(R.id.someone_else)
        val male: CheckBox = findViewById(R.id.checkBox)
        val female: CheckBox = findViewById(R.id.checkBox2)
        val next_check: Button = findViewById(R.id.next_btn_check)
        val back_check: Button = findViewById(R.id.button_back_check)
        var check_for: String = ""
        var gender: String = ""
        back_check.setOnClickListener {
            startActivity(Intent(this, TermsActivity::class.java))
        }
        myself.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                check_for = "myself"
            }

        }
        someone_else.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                check_for = "someone_else"
            }

        }
        male.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                gender = "male"
            }

        }
        female.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                gender = "female"
            }

        }

        Log.e("gender", gender)
        Log.e("check", check_for)
        next_check.setOnClickListener {
            when {
                !(myself.isChecked || someone_else.isChecked) -> {
                    Toast.makeText(this, "Please fill out the fields", Toast.LENGTH_SHORT).show()
                }
                !(male.isChecked || female.isChecked) -> {
                    Toast.makeText(this, "Please fill out the fields", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val hashMap = HashMap<String, Any>()
                    hashMap["check_for"] = check_for
                    hashMap["gender"] = gender
                    databaseref.child("interviews")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid).child(id)
                        .updateChildren(hashMap)
                    val next_intent = Intent(this, AgeActivity::class.java)
                    next_intent.putExtra("interview_id", id)
                    startActivity(next_intent)
                }
            }
        }

    }
}