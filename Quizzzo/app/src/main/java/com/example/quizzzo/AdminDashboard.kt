package com.example.quizzzo

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzzo.R
import com.example.quizzzo.database.Query

class AdminDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)


        val questionsList = findViewById<ListView>(R.id.lvQuestions)
        val questionID = findViewById<EditText>(R.id.tfQuestionID)
        val questionText = findViewById<EditText>(R.id.tfQuestionDescription)
        val ans1 = findViewById<EditText>(R.id.tfNewAns1)
        val ans2 = findViewById<EditText>(R.id.tfNewAns2)
        val ans3 = findViewById<EditText>(R.id.tfNewAns3)
        val ans4 = findViewById<EditText>(R.id.tfNewAns4)

        val addQuestionButton = findViewById<Button>(R.id.btnAddQuestion)
        val editQuestionButton = findViewById<Button>(R.id.btnEditQuestion)
        val deleteQuestionButton = findViewById<Button>(R.id.btnDeleteQuestion)
        val logoutButton = findViewById<Button>(R.id.btnLogout)

        val spinner = findViewById<Spinner>(R.id.spinner)
        var categories = arrayOf("Math","Java","Machine Learning")

        var chosenCategory = -1
        var counter = 1
        val listOfQuestions = ArrayList<String>()
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,categories)
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenCategory = position
                updateQuestionsList(chosenCategory, listOfQuestions, questionsList)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        addQuestionButton.setOnClickListener {
            if (questionText.text.isEmpty() || ans1.text.isEmpty() || ans2.text.isEmpty() || ans3.text.isEmpty() || ans4.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Required Field", Toast.LENGTH_SHORT).show()
            }
            else {
                val contentValues = ContentValues()
                contentValues.put("Category_id", chosenCategory)
                contentValues.put("Question_desc", questionText.text.toString())
                contentValues.put("ans1", ans1.text.toString())
                contentValues.put("ans2", ans2.text.toString())
                contentValues.put("ans3", ans3.text.toString())
                contentValues.put("ans4", ans4.text.toString())

                val status = Query(this).insert("Questions", contentValues)
                if (status > -1) {
                    questionID.text.clear()
                    questionText.text.clear()
                    ans1.text.clear()
                    ans2.text.clear()
                    ans3.text.clear()
                    ans4.text.clear()
                    updateQuestionsList(chosenCategory, listOfQuestions, questionsList)
                    Toast.makeText(this, "Question added Successfully :)", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Failed adding the question :(", Toast.LENGTH_SHORT).show()
                }
            }
        }

        editQuestionButton.setOnClickListener {
            if (questionID.text.isEmpty() || questionText.text.isEmpty() || ans1.text.isEmpty() || ans2.text.isEmpty() || ans3.text.isEmpty() || ans4.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Required Field", Toast.LENGTH_SHORT).show()
            }
            else {
                val columns = arrayOf("Category_id", "Question_desc", "ans1", "ans2", "ans3", "ans4")
                val values = arrayOf(chosenCategory.toString(), questionText.text.toString(), ans1.text.toString(), ans2.text.toString(), ans3.text.toString(), ans4.text.toString())
                val status = Query(this).update("Questions", columns , values,"Question_id=${questionID.text}")

                if (status) {
                    questionID.text.clear()
                    questionText.text.clear()
                    ans1.text.clear()
                    ans2.text.clear()
                    ans3.text.clear()
                    ans4.text.clear()
                    updateQuestionsList(chosenCategory, listOfQuestions, questionsList)
                    Toast.makeText(this, "Question updated Successfully :)", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Failed updating the question :(", Toast.LENGTH_SHORT).show()
                }
            }
        }

        deleteQuestionButton.setOnClickListener {
            if (questionID.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Required Field", Toast.LENGTH_SHORT).show()
            }
            else {
                val status = Query(this).delete("Questions", "Question_id=${questionID.text}")
                if (status) {
                    questionID.text.clear()
                    questionText.text.clear()
                    ans1.text.clear()
                    ans2.text.clear()
                    ans3.text.clear()
                    ans4.text.clear()
                    updateQuestionsList(chosenCategory, listOfQuestions, questionsList)
                    Toast.makeText(this, "Question deleted Successfully :)", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Failed deleting the question :(", Toast.LENGTH_SHORT).show()
                }
            }
        }

        logoutButton.setOnClickListener {
            onBackPressed()
        }
    }

    fun updateQuestionsList(chosenCategory:Int, listOfQuestions: ArrayList<String>, questionsList: ListView){
        listOfQuestions.clear()
        val storedQuestions = Query(this@AdminDashboard).select("Questions", arrayOf("Question_id","Question_desc"),"Category_id=$chosenCategory")
        if(storedQuestions.count>0) {
            while (storedQuestions.moveToNext())
                listOfQuestions.add(
                    storedQuestions.getString(0) + ") " + storedQuestions.getString(1)
                )
        }
        val adapter = ArrayAdapter(this@AdminDashboard,android.R.layout.simple_list_item_1,listOfQuestions)
        adapter.notifyDataSetChanged()
        questionsList.adapter = adapter
    }
}
