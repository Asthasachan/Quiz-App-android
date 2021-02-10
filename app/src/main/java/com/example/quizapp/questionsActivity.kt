package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_questions.*

class questionsActivity : AppCompatActivity(), View.OnClickListener {
    private var currentposition:Int=1
    private var questionlist:ArrayList<questionmodel>?=null
   private var selectedoptionpos:Int=0
    private var correctans:Int=0
    private var scores:Int=0
    var username:String?=null
    var age:String?=null
    var city:String?=null
    var countDownTimer = object: CountDownTimer(11000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            timetextview.setText("Time: " + millisUntilFinished / 1000);

        }

        override fun onFinish() {
            timetextview.setText("Finished")

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        username=intent.getStringExtra("Username")
        age=intent.getStringExtra("Age")
        city=intent.getStringExtra("City")


        questionlist=questioncontroller.getquestions()

        setquestion()



            option1.setOnClickListener(this)
            option2.setOnClickListener(this)
            option3.setOnClickListener(this)
            option4.setOnClickListener(this)
            submitbtn.setOnClickListener(this)




       // Toast.makeText(this,"${questionlist.size}",Toast.LENGTH_LONG).show()
    }

    fun setquestion(){


        var question:questionmodel=questionlist!![currentposition-1]
        optiondefback()
        if(currentposition==questionlist!!.size){
            submitbtn.text="FINISH"
        }

        progressBar.progress =currentposition
        progresstextview.text="$currentposition/${questionlist?.size}"
        question_text.text=question.questiontext
        option1.text=question.optionone
        option2.text=question.optiontwo
        option3.text=question.optionthree
        option4.text=question.optionfour
        countDownTimer.start()

    }
    fun optiondefback(){
        val optionarraylist=ArrayList<TextView>()
        optionarraylist.add(option1)
        optionarraylist.add(option2)
        optionarraylist.add(option3)
        optionarraylist.add(option4)
        for (options in optionarraylist){
            options.background=ContextCompat.getDrawable(this,R.drawable.option_border)
        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.option1->{
                optionselback(option1,1)
            }
            R.id.option2->{
                optionselback(option2,2)
            }
            R.id.option3->{
                optionselback(option3,3)
            }
            R.id.option4->{
                optionselback(option4,4)
            }
            R.id.submitbtn->{

                if(selectedoptionpos==0 || timetextview.text.toString()=="Finished"){

                    currentposition++
                    when{
                        currentposition<=questionlist!!.size->{
                            setquestion()
                        }
                        else->{
                            val intent= Intent(this,finalactivity::class.java)
                            intent.putExtra("Username",username)
                            intent.putExtra("Age",age)
                            intent.putExtra("City",city)
                            intent.putExtra("correct",correctans)
                            intent.putExtra("total",questionlist!!.size)
                            intent.putExtra("Score",scores)
                            startActivity(intent)
                            finish()
                            //Toast.makeText(this,"test is over!",Toast.LENGTH_LONG).show()
                        }
                    }

                }
                else{
                    //var ques=questionlist?.get(currentposition-1)
                    var ques=questionlist!![currentposition-1]
                    if(ques.correctans != selectedoptionpos.toString()){
                               redgreenhighlight(selectedoptionpos,R.drawable.wronganswer)
                        scores--
                    }
                    else{
                        correctans++
                        scores++
                    }
                    redgreenhighlight(ques.correctans.toInt(),R.drawable.correctanswer)
                    selectedoptionpos=0

                }
            }
        }

    }
    fun redgreenhighlight(answer:Int,drawable: Int){
        when(answer){
            1->{
                option1.background=ContextCompat.getDrawable(this,drawable)
            }
            2->{
                option2.background=ContextCompat.getDrawable(this,drawable)
            }
            3->{
                option3.background=ContextCompat.getDrawable(this,drawable)
            }
            4->{
                option4.background=ContextCompat.getDrawable(this,drawable)
            }
        }
    }
    fun optionselback(tv:TextView,selectedoptionno:Int){
        optiondefback()
        selectedoptionpos=selectedoptionno
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_option)
       // tv.setTextColor(Color.parseColor(("#FFFFFF")))
    }
}