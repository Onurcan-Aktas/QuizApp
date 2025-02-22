package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding


    private val questions= arrayOf("Where is the Capital city of Türkiye?","What is smallest country?",
        "What is the most populated country?")

    private val options= arrayOf(arrayOf("Istanbul","Ankara","Sivas"), arrayOf("Vatican","San Marino","Liechtenstein"),
        arrayOf("China","India","Russia"))

    private val correctAnswers= arrayOf(1,0,1)

    private var currentQuestionIndex=0
    private var score=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
       binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displaysQuestions()

        binding.Option1Buttton.setOnClickListener{
            checkAnswer(0)
        }

        binding.Option2Buttton.setOnClickListener{
            checkAnswer(1)
        }

        binding.Option3Buttton.setOnClickListener{
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener{
            restarQuiz()
        }

    }

    private fun correctButtonColors(buttonIndex:Int){
        when(buttonIndex){
            0->binding.Option1Buttton.setBackgroundColor(Color.GREEN)
            1->binding.Option2Buttton.setBackgroundColor(Color.GREEN)
            2->binding.Option3Buttton.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex:Int){
        when(buttonIndex){
            0->binding.Option1Buttton.setBackgroundColor(Color.RED)
            1->binding.Option2Buttton.setBackgroundColor(Color.RED)
            2->binding.Option3Buttton.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.Option1Buttton.setBackgroundColor(Color.rgb(50,59,96))
        binding.Option2Buttton.setBackgroundColor(Color.rgb(50,59,96))
        binding.Option3Buttton.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this,"Your score:$score out of ${questions.size}",Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled=true
    }

    private fun displaysQuestions(){
        binding.questionText.text=questions[currentQuestionIndex]
        binding.Option1Buttton.text=options[currentQuestionIndex][0]
        binding.Option2Buttton.text=options[currentQuestionIndex][1]
        binding.Option3Buttton.text=options[currentQuestionIndex][2]
        resetButtonColors()
    }
    private fun checkAnswer(selectedAnswerIndex:Int){

        val correctAnswerIndex=correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex==correctAnswerIndex){
            score++
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex<questions.size-1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displaysQuestions()},1000)
        }else{
            showResults()
        }
    }

    private fun restarQuiz(){
        currentQuestionIndex=0
        score=0
        displaysQuestions()
        binding.restartButton.isEnabled=false
    }
}