package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startquizbtn.setOnClickListener{
            if(nametext.text.toString().isEmpty()){
                Toast.makeText(this,"write name",Toast.LENGTH_SHORT).show()
            }
            else if(agetext.text.toString().isEmpty()){
                Toast.makeText(this,"write age",Toast.LENGTH_SHORT).show()
            }
            else if(citytext.text.toString().isEmpty()){
                Toast.makeText(this,"write city",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this, questionsActivity::class.java)
                intent.putExtra("Username",nametext.text.toString())
                intent.putExtra("Age",agetext.text.toString())
                intent.putExtra("City",citytext.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }


}