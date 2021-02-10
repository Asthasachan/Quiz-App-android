package com.example.quizapp

object questioncontroller {
    fun getquestions():ArrayList<questionmodel> {
        val questionlist = ArrayList<questionmodel>()

        val q1 = questionmodel(1, "What is part of a database that holds only one type of input?",
                "Report", "Field", "Record", "File", "2")
        questionlist.add(q1)

        val q2 = questionmodel(2, "What is the full form of OS?",
                "Opt System", "Open Software", "Operating System", "Order System", "3")
        questionlist.add(q2)
        val q3 = questionmodel(3, "Which among these is a javascript library?",
                "React js", "Laravel", "Django", "Flask", "1")

        questionlist.add(q3)
        return questionlist
    }
}