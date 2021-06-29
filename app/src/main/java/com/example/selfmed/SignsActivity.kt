package com.example.selfmed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignsActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    var checkAccumulator: Int = 0
    val upload_map = HashMap<String, Any>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signs)
        val nextSigns: Button = findViewById(R.id.next_signs)

        val interview_id = intent.getStringExtra("interview_id").toString()
        val yes_cough: CheckBox = findViewById(R.id.checkBox3)
        val yes_weightloss: CheckBox = findViewById(R.id.checkBox8)
        val yes_chestPain: CheckBox = findViewById(R.id.checkBox6)
        val yes_fever: CheckBox = findViewById(R.id.checkBox14)
        val yes_nightSweats: CheckBox = findViewById(R.id.checkBox12)
        val yes_fatigue: CheckBox = findViewById(R.id.checkBox10)
        val yes_blood_cough: CheckBox = findViewById(R.id.checkBox4)
        val yes_contact: CheckBox = findViewById(R.id.yes_contact)

        val no_blood_cough: CheckBox = findViewById(R.id.checkBox7)
        val no_cough: CheckBox = findViewById(R.id.checkBox5)
        val no_chestPain: CheckBox = findViewById(R.id.checkBox9)
        val no_weightloss: CheckBox = findViewById(R.id.checkBox11)
        val no_fatigue: CheckBox = findViewById(R.id.checkBox13)
        val no_nightSweats: CheckBox = findViewById(R.id.checkBox15)
        val no_fever: CheckBox = findViewById(R.id.checkBox16)
        val nocontact: CheckBox = findViewById(R.id.nocontact)

        var list = mutableListOf<Int>()


        yes_blood_cough.setOnCheckedChangeListener(this)
        yes_chestPain.setOnCheckedChangeListener(this)
        yes_cough.setOnCheckedChangeListener(this)
        yes_fatigue.setOnCheckedChangeListener(this)
        yes_fever.setOnCheckedChangeListener(this)
        yes_contact.setOnCheckedChangeListener(this)
        yes_nightSweats.setOnCheckedChangeListener(this)
        yes_weightloss.setOnCheckedChangeListener(this)


        no_blood_cough.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                list.add(0)
            }
        }
        nextSigns.setOnClickListener {
            when {
                !(yes_blood_cough.isChecked || no_blood_cough.isChecked) -> {
                    Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT)
                        .show()
                }
                !(yes_chestPain.isChecked || no_chestPain.isChecked) -> {
                    Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT)
                        .show()
                }
                !(yes_cough.isChecked || no_cough.isChecked) -> {
                    Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT)
                        .show()
                }
                !(yes_fatigue.isChecked || no_fatigue.isChecked) -> {
                    Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT)
                        .show()
                }
                !(yes_fever.isChecked || no_fever.isChecked) -> {
                    Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT)
                        .show()
                }
                !(yes_nightSweats.isChecked || no_nightSweats.isChecked) -> {
                    Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT)
                        .show()
                }
                !(yes_weightloss.isChecked || no_weightloss.isChecked) -> {
                    Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {

                    if (checkAccumulator in 0..2) {
                        upload_map["tb_risk"] = "No TB"
                        upload_map["Recommendation"] =
                            "You have a lower risk of having TB, check with your GP"
                        upload_map["details"] =
                            "A check for Cough or other respiratory illnesses would be recommended"
                    } else if (checkAccumulator in 3..5) {
                        upload_map["tb_risk"] = "Likely TB symptoms"
                        upload_map["Recommendation"] =
                            "You have a average risk of having TB, check with your GP"
                        upload_map["details"] = "A check for Cough or other respiratory illnesses"
                    } else if (checkAccumulator in 6..9) {
                        upload_map["tb_risk"] = "You may be suffering from TB"
                        upload_map["Recommendation"] =
                            "You have a high risk of having TB, To be sure, your doctor will examine you and do a chest x-ray. You may need other tests to see if you have latent TB infection or active TB disease" + "\n" + " Recommended Medicine" + "\n" + "Isoniazid.\n" +
                                    "Rifampin (Rifadin, Rimactane)\n" +
                                    "Ethambutol (Myambutol)\n" +
                                    "Pyrazinamide."
                        upload_map["details"] =
                            "A “positive” TB blood test result means you probably have TB germs in your body. Most people with a positive TB blood test have latent TB infection. "

                    }

                    val signRef = FirebaseDatabase.getInstance().reference
                    signRef.child("interviews")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .child(interview_id).updateChildren(upload_map)
                    startActivity(Intent(this, ResultsActivity::class.java))
                }


            }

        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        countCheck(isChecked)
        Log.i("MAIN", checkAccumulator.toString());

    }


    private fun countCheck(isChecked: Boolean) {
        checkAccumulator += if (isChecked) 1 else 0
    }


}