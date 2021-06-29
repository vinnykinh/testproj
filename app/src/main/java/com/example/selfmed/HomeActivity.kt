package com.example.selfmed

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.widgets.ConstraintWidget.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import com.airbnb.lottie.LottieAnimationView

import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum
import com.nightonke.boommenu.BoomButtons.HamButton
import com.nightonke.boommenu.BoomButtons.OnBMClickListener
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton
import com.nightonke.boommenu.BoomMenuButton
import com.nightonke.boommenu.ButtonEnum
import com.nightonke.boommenu.Piece.PiecePlaceEnum
import org.w3c.dom.Text
import java.util.regex.Pattern
import javax.annotation.meta.When

private var LISTENER: Int? = null

class HomeActivity : AppCompatActivity() {
    private val PASSWORD_PATTERN: Pattern =
        Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    ".{6,}" +
                    "$"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val layout: ConstraintLayout = findViewById(R.id.constrant_layout)
        val databaseRef = FirebaseDatabase.getInstance()

        val lottie: LottieAnimationView = findViewById(R.id.imageView2)
        val login_btn: Button = findViewById(R.id.login_btn)
        var passcode: EditText = findViewById(R.id.passcode)
        var email: EditText = findViewById(R.id.login_username)
        val create_link: TextView = findViewById(R.id.create_account_txt)
        create_link.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        lottie.animate()
        lottie.loop(true)














        login_btn.setOnClickListener {

            loginAccount()


        }

    }

    private fun loginAccount() {

        val passcode: EditText = findViewById(R.id.passcode)
        val address: EditText = findViewById(R.id.login_username)
        val email = address.text.toString()
        val password = passcode.text.toString()
        when {

            !validateEmail() -> {
            }
            !validatePassword() -> {

            }

            else -> {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Logging in Account")
                progressDialog.setMessage("please wait...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth = FirebaseAuth.getInstance()
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            progressDialog.dismiss()
                            Toast.makeText(
                                this,
                                "Account logged in successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this, DetailsActivity::class.java)
                            startActivity(intent)
                        }

                    }.addOnFailureListener { taskId ->
                        progressDialog.dismiss()
                        Toast.makeText(this, taskId.message!!, Toast.LENGTH_SHORT).show()
                    }


            }

        }
    }

    private fun validateEmail(): Boolean {
        var passcode: EditText = findViewById(R.id.passcode)
        var address: EditText = findViewById(R.id.login_username)
        val email = address.text.toString()
        val password = passcode.text.toString()
        return when {
            TextUtils.isEmpty(email) -> {
                address.error = "Field can't be empty"
                return false
            }
            !(Patterns.EMAIL_ADDRESS.matcher(email).matches()) -> {
                address.error = "Enter a valid Email Adress"
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun validatePassword(): Boolean {
        var passcode: EditText = findViewById(R.id.passcode)
        var address: EditText = findViewById(R.id.login_username)
        val email = address.text.toString()
        val password = passcode.text.toString()
        return when {
            TextUtils.isEmpty(password) -> {
                passcode.setError("Field can't be empty")
                false
            }
            !(PASSWORD_PATTERN.matcher(password).matches()) -> {
                passcode.setError("Should contain atleast 1 uppercase,1 lowercase, 1 digit")
                false
            }
            else -> {
                true
            }
        }
    }

    /*override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            Toast.makeText(
                this,
                "Account logged in successfully",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

    }*/
}