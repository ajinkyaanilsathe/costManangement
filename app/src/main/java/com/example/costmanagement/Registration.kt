package com.example.costmanagement

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Registration : AppCompatActivity() {
    private lateinit var mDialog: ProgressDialog
    private lateinit var auth: FirebaseAuth
    private val TAG: String = Registration::class.java.getSimpleName()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var emailIdEdt = findViewById<EditText>(R.id.emailEdtView_reg)
        var passwordEdt = findViewById<EditText>(R.id.passwordEdtView_reg)
        var registerBtn = findViewById<Button>(R.id.registerBtn_reg)
        var goToLoginTxt = findViewById<TextView>(R.id.goToLogin_reg)
        mDialog = ProgressDialog(this)
        ///mDialog.show()
        ////////FIREBASE //////////////
        auth = FirebaseAuth.getInstance()
        //auth = Firebase.auth
        registerBtn.setOnClickListener {
            var emailId = emailIdEdt.text.toString()
            var password = passwordEdt.text.toString()

            mDialog.setMessage("Processing .....")
            mDialog.show()
            auth.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(this){
                task ->
                if (task.isSuccessful){
                mDialog.dismiss()
//                val firebaseUser = this.auth.currentUser!!
//                val user = auth.currentUser
                //updateUI(user)
                Toast.makeText(applicationContext,"Registration Successful !!!",Toast.LENGTH_LONG).show();
                //startActivity(Intent(this, MainActivity::class.java))
                //finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Log.d(TAG, "createUserWithEmail:success")
            }else{
                mDialog.dismiss()
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(applicationContext,"Registration Unsuccessful !!!",Toast.LENGTH_LONG).show();
                }

            }

        }

        goToLoginTxt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        //updateUI(currentUser)
//    }


}