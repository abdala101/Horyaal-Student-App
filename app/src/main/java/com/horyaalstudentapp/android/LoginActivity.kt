package com.horyaalstudentapp.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = MyDatabase(this)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login_btn)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val student = database.studentDAO().getStudentByEmailAndPassword(email, password)
                    runOnUiThread {
                        if (student != null) {
                            Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_LONG).show()
                            // Navigate to NewStudentActivity
                            val intent = Intent(this@LoginActivity, NewStudentActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Invalid email or password.", Toast.LENGTH_LONG).show()
                        }

                    }
                }
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
