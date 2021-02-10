package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finalactivity.*

class finalactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalactivity)
        val username=intent.getStringExtra("Username")
        val age=intent.getStringExtra("Age")
        val city=intent.getStringExtra("City")
        val totalque=intent.getIntExtra("total",0)
        val correctans=intent.getIntExtra("correct",0)
        val totalscore=intent.getIntExtra("Score",0)

        userspace.text="Name: $username"
        agespace.text="Age: $age"
        cityspace.text="City: $city"
        score.text="Total questions attempted: $correctans out of $totalque"
        scorei.text="Final Score: $totalscore"
        finishbutton.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}