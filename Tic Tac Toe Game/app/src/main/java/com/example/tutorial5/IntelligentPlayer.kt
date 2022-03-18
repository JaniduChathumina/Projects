package com.example.tutorial5

import androidx.appcompat.app.AppCompatActivity

class IntelligentPlayer()  {

    private val existingArray = mutableListOf<IntArray>()
    private val userArray = mutableListOf<IntArray>()
    private var computerArr = intArrayOf()


    fun generatePosition() : IntArray{

        computerArr = randomPosition()

        val topRow = mutableListOf(intArrayOf(1,1), intArrayOf(1,2), intArrayOf(1,3))
        val middleRow = mutableListOf(intArrayOf(2,1), intArrayOf(2,2), intArrayOf(2,3))
        val lastRow = mutableListOf(intArrayOf(3,1), intArrayOf(3,2), intArrayOf(3,3))
        val leftClm = mutableListOf(intArrayOf(1,1), intArrayOf(2,1), intArrayOf(3,1))
        val middleClm = mutableListOf(intArrayOf(1,2), intArrayOf(2,2), intArrayOf(3,2))
        val rightClm = mutableListOf(intArrayOf(1,3), intArrayOf(2,3), intArrayOf(3,3))
        val dimension1 = mutableListOf(intArrayOf(1,1), intArrayOf(2,2), intArrayOf(3,3))
        val dimension2 = mutableListOf(intArrayOf(1,3), intArrayOf(2,2), intArrayOf(3,1))

        assignIntelligentPos(topRow)
        assignIntelligentPos(middleRow)
        assignIntelligentPos(lastRow)
        assignIntelligentPos(leftClm)
        assignIntelligentPos(middleClm)
        assignIntelligentPos(rightClm)
        assignIntelligentPos(dimension1)
        assignIntelligentPos(dimension2)


        existingArray.add(computerArr)

        return computerArr
    }

    private fun assignIntelligentPos(topRow: MutableList<IntArray>){
        for (x in userArray.indices) {
            for (y in userArray.indices) {

                if (userArray[x].contentEquals(topRow[0]) && userArray[y].contentEquals(topRow[2])) {
                    if (!containsElement(topRow[1])) computerArr = topRow[1]
                } else if (userArray[x].contentEquals(topRow[0]) && userArray[y].contentEquals(topRow[1])) {
                    if (!containsElement(topRow[2])) computerArr = topRow[2]
                } else if (userArray[x].contentEquals(topRow[1]) && userArray[y].contentEquals(topRow[2])) {
                    if (!containsElement(topRow[0])) computerArr = topRow[0]
                }

            }
        }
    }

    private fun randomPosition() : IntArray {
        val computerArr = IntArray(2)


        computerArr[0] = generateRndButton(1, 3)
        computerArr[1] = generateRndButton(1, 3)
//        Log.d("my",vam.toString())
//        var str =""
//        for (i in computerSelections.indices)
//            str+=computerSelections[0].toString()+" "+computerSelections[1].toString()+" / "
//        Log.d("my",str)
        while (containsElement(computerArr)) {
            computerArr[0] = generateRndButton(1, 3)
            computerArr[1] = generateRndButton(1, 3)
        }


        return computerArr
    }


        //checks whether the given elements are present in the existing list or not
    private fun containsElement(array: IntArray) : Boolean{
        var result = false
        for (i in existingArray.indices){
            if (array[0]==existingArray[i][0] && array[1]==existingArray[i][1])
                result = true
        }
        return result
    }

    fun addToExistingArray(array: IntArray){
        existingArray.add(array)
    }

    fun addToUserArray(array: IntArray){
        userArray.add(array)
    }


    //generates random number including the max and min
    private fun generateRndButton(min: Int, max: Int): Int{
        return (Math.random()*(max-min+1)).toInt()+min
    }


}