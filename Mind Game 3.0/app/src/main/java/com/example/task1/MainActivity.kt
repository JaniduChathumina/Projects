package com.example.task1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var colouredArray : Array<IntArray>
    var totalQuestions=0
    var correctQuestions=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val score = findViewById<TextView>(R.id.score)


        fun createNewGame() {

            var attempts = 0
            var targetColorCells = randomNumBtw(4, 6)
            var message = findViewById<TextView>(R.id.id_messages)
            message.text= "💎"
            totalQuestions+=targetColorCells
            score.text="$correctQuestions / $totalQuestions"


            val rows = randomNumBtw(3, 5)
            val columns = randomNumBtw(3, 5)
            val table: TableLayout = findViewById(R.id.id_table)

            table.removeAllViews()

            colouredArray = Array(targetColorCells) { IntArray(2) }


            var x = 0
            var existingList = mutableListOf<String>()

            while (x < targetColorCells) {
                val cellRow = randomNumBtw(1, rows)
                val cellColumn = randomNumBtw(1, columns)

                if(!existingList.contains("$cellRow$cellColumn")){
                    colouredArray[x][0] = cellRow
                    colouredArray[x][1] = cellColumn
                    existingList.add("$cellRow$cellColumn")
                    x++
                }

            }



            for (i in 1..rows) {
                var tableRow = TableRow(this)
                table.addView(tableRow)
                for (j in 1..columns) {
                    var button = Button(this)
                    tableRow.addView(button)
                    button.setBackgroundResource(R.drawable.border)
                    Handler().postDelayed({
                        message.text = "Remember this !"

                        for (m in 0 until targetColorCells) {
                            if (colouredArray[m][0] == i && colouredArray[m][1] == j) {
                                button.setBackgroundResource(R.drawable.green_color)
                                Handler().postDelayed({
                                    button.setBackgroundResource(R.drawable.border)

                                    message.text = "Let's go !"


                                }, 5000)
                            }
                        }

                    }, 3000)


                    button.setOnClickListener {

                        if (attempts>=targetColorCells-1){
                            Handler().postDelayed({
                                createNewGame()
                            }, 2000)

                        }
                        attempts++

                        var result = checkButton(i, j)
                        if (result) {
                            button.setBackgroundColor(Color.GREEN)
                            correctQuestions++
                            score.text="$correctQuestions / $totalQuestions"
                            Handler().postDelayed({
                                button.setBackgroundResource(R.drawable.border)
                            }, 2000)

                        } else {
                            button.setBackgroundColor(Color.RED)
                            button.text = "X"
                            Handler().postDelayed({
                                button.setBackgroundResource(R.drawable.border)
                                button.text = ""
                            }, 2000)
                        }

                    }

                }
            }

        }


        //call the function to create the game
        createNewGame()




    }

    private fun checkButton(i: Int, j: Int) :Boolean{
        var result = false
        for(x in colouredArray.indices){
            if (colouredArray[x][0]==i && colouredArray[x][1]==j){
                result=true
            }
        }
        return result
    }


    private fun randomNumBtw(min: Int, max: Int): Int {
        return (Math.random()*(max-min+1)).toInt() +min
    }
}