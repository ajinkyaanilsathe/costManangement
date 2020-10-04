package com.example.costmanagement

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private lateinit var mDialog: ProgressDialog
    private lateinit var auth: FirebaseAuth
    private val TAG: String = MainActivity::class.java.getSimpleName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailEdt = findViewById<EditText>(R.id.emailEdtView_main)
        val passwordEdt = findViewById<EditText>(R.id.passwordEdtView_main)
        val loginBtn = findViewById<Button>(R.id.loginBtn_main)
        val goToRegister = findViewById<TextView>(R.id.goToRegister_main)
        mDialog = ProgressDialog(this)
        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            val emailId = emailEdt.getText().toString().trim()
            val password = passwordEdt.getText().toString().trim()
            mDialog.setMessage("Processing .....")
            mDialog.show()
            auth.signInWithEmailAndPassword(emailId, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        mDialog.dismiss()
                        Log.d(TAG, "signInWithEmail:success")

                     //   val intent = Intent(this, Home::class.java)
                     //   startActivity(intent)
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        mDialog.dismiss()
                       Log.w(TAG, "signInWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            baseContext, "Authentication failed.",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        updateUI(null)
                    }
                }

        }

        goToRegister.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        } else {
            Log.w(TAG, "signInWithEmail:failure")
            Toast.makeText(
                baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}