package com.example.selfmed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class TermsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)
        val next_terms: Button = findViewById(R.id.next_btn_terms)
        val back_temrs: Button = findViewById(R.id.button_back_terms)

        val check_box_1: CheckBox = findViewById(R.id.textView7)
        val check_box_2: CheckBox = findViewById(R.id.agreement_1)
        back_temrs.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }


        next_terms.setOnClickListener {
            when {
                !check_box_1.isChecked -> {
                    Toast.makeText(
                        this@TermsActivity,
                        "To proceed agree to the terms",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                !check_box_2.isChecked -> {
                    Toast.makeText(
                        this@TermsActivity,
                        "To proceed, please agree to the terms",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val intent = intent
                    val intnt = Intent(this@TermsActivity, CheckActivity::class.java)
                    var key = intent.getStringExtra("interview_id").toString()
                    Log.e("tag", key)
                    intnt.putExtra("interview_id", key)
                    startActivity(intnt)
                }

            }
        }
    }
}