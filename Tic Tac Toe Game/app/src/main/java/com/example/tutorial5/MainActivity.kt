package com.example.tutorial5

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var table: TableLayout
    private var userClickedButtons = mutableListOf<IntArray>()
    private var computerClickedButtons = mutableListOf<IntArray>()
    private lateinit var computerPlayer: IntelligentPlayer
    private var computerMoves =0
    private var numberOfGames = 1
    private var userScore =0
    private var computerScore=0
    private lateinit var resultView : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        table = findViewById(R.id.id_table_view)
        resultView=findViewById(R.id.id_resultView)

        resultView.text="User : $userScore    Computer : $computerScore"

        createNewGame()
    }

    private fun createNewGame(){
        computerPlayer = IntelligentPlayer()

        userClickedButtons.clear()
        computerClickedButtons.clear()
        computerMoves=0

        Toast.makeText(applicationContext,"Round $numberOfGames ",Toast.LENGTH_SHORT).show()

        updateGame()
    }

    private fun updateGame(){

        table.removeAllViews()


        for (i in 1..3) {
            val tableRow = TableRow(this)
            table.addView(tableRow)

            for (j in 1..3) {
                val button = Button(this)
                button.setBackgroundResource(R.drawable.box_background)
                tableRow.addView(button)
                button.textSize = 40F

                for (q in 0 until userClickedButtons.size){
                    if (userClickedButtons[q][0]==i && userClickedButtons[q][1]==j) {
                        button.text = "X"
                        button.setTextColor(Color.BLUE)
                    }
                }
                for (q in 0 until computerClickedButtons.size){
                    if (computerClickedButtons[q][0]==i && computerClickedButtons[q][1]==j) {
                        button.text = "O"
                        button.setTextColor(Color.RED)
                    }
                }

                button.setOnClickListener {

                        if (button.text!=""){
                            Toast.makeText(applicationContext,"Invalid move !",Toast.LENGTH_SHORT).show()

                        }else{
                            val userArr = IntArray(2)
                            userArr[0]=i
                            userArr[1]=j
                            userClickedButtons.add(userArr)
                            computerPlayer.addToExistingArray(userArr)
                            computerPlayer.addToUserArray(userArr)

                            if(checkWin(userClickedButtons)){
                                userScore++
                                resultView.text="User : $userScore    Computer : $computerScore"
                                Toast.makeText(applicationContext,"You Won", Toast.LENGTH_SHORT).show()
                                nextRound()
                            }


//                            var str = ""
//                            for (e in userClickedButtons.indices)
//                                str+=userClickedButtons[e][0].toString()+" "+userClickedButtons[e][1].toString()+" / "
//                            Log.d("win",str)

                            if (computerMoves<4) {
                                val computerArr = IntArray(2)
                                val arr = computerPlayer.generatePosition()
                                computerArr[0] = arr[0]
                                computerArr[1] = arr[1]
                                computerClickedButtons.add(computerArr)
                                computerMoves++

//                                for (l in computerClickedButtons.indices)
//                                    Log.d(
//                                        "my",
//                                        computerClickedButtons[l][0].toString() + " " + computerClickedButtons[l][1].toString()
//                                    )

                                if(checkWin(computerClickedButtons)) {
                                    computerScore++
                                    resultView.text="User : $userScore    Computer : $computerScore"
                                    Toast.makeText(applicationContext,"Computer Won", Toast.LENGTH_SHORT).show()
                                    nextRound()
                                }


                                updateGame()


                            }else{
                                updateGame()
                                Toast.makeText(applicationContext,"Game Draw", Toast.LENGTH_SHORT).show()
                                //not going inside the computer draw because computer draws are finished, so another update
                                // is needed to sow the last user selection


                                nextRound()



                            }


                        }


                }


            }
        }

    }

    private fun nextRound(){
        numberOfGames++

        if (numberOfGames<=3)
            Handler().postDelayed({createNewGame()},2000)

        else {
            Toast.makeText(applicationContext,"GAME OVER !",Toast.LENGTH_SHORT).show()
            var winner = "YOU WON"
            if (computerScore>userScore)
                winner= "YOU LOST"
            else if (computerScore==userScore)
                winner= "DRAW"
            Handler().postDelayed({
                val results = Intent(applicationContext, EndGame::class.java)
                results.putExtra("winner",winner)
                startActivity(results)
            }, 3000)
        }
    }

    private fun checkWin(array: MutableList<IntArray>) : Boolean {
        var win =false
        val topRow = mutableListOf(intArrayOf(1,1), intArrayOf(1,2), intArrayOf(1,3))
        val middleRow = mutableListOf(intArrayOf(2,1), intArrayOf(2,2), intArrayOf(2,3))
        val lastRow = mutableListOf(intArrayOf(3,1), intArrayOf(3,2), intArrayOf(3,3))
        val leftClm = mutableListOf(intArrayOf(1,1), intArrayOf(2,1), intArrayOf(3,1))
        val middleClm = mutableListOf(intArrayOf(1,2), intArrayOf(2,2), intArrayOf(3,2))
        val rightClm = mutableListOf(intArrayOf(1,3), intArrayOf(2,3), intArrayOf(3,3))
        val dimension1 = mutableListOf(intArrayOf(1,1), intArrayOf(2,2), intArrayOf(3,3))
        val dimension2 = mutableListOf(intArrayOf(1,3), intArrayOf(2,2), intArrayOf(3,1))

        Log.d("n",topRow[0][0].toString()+" "+topRow[0][1].toString())

        for (x in array.indices) {
            for (y in array.indices) {
                for (z in array.indices) {
                    if (array[x].contentEquals(topRow[0]) && array[y].contentEquals(topRow[1]) && array[z].contentEquals(topRow[2]))
                        win = true
                    if (array[x].contentEquals(middleRow[0]) && array[y].contentEquals(middleRow[1]) && array[z].contentEquals(middleRow[2]))
                        win = true
                    if (array[x].contentEquals(lastRow[0]) && array[y].contentEquals(lastRow[1]) && array[z].contentEquals(lastRow[2]))
                        win = true
                    if (array[x].contentEquals(leftClm[0]) && array[y].contentEquals(leftClm[1]) && array[z].contentEquals(leftClm[2]))
                        win = true
                    if (array[x].contentEquals(middleClm[0]) && array[y].contentEquals(middleClm[1]) && array[z].contentEquals(middleClm[2]))
                        win = true
                    if (array[x].contentEquals(rightClm[0]) && array[y].contentEquals(rightClm[1]) && array[z].contentEquals(rightClm[2]))
                        win = true
                    if (array[x].contentEquals(dimension1[0]) && array[y].contentEquals(dimension1[1]) && array[z].contentEquals(dimension1[2]))
                        win = true
                    if (array[x].contentEquals(dimension2[0]) && array[y].contentEquals(dimension2[1]) && array[z].contentEquals(dimension2[2]))
                        win = true


                }
            }
        }
        Log.d("win",win.toString())

        return win

    }




}