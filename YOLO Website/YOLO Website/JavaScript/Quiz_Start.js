const questions = [
    {
        question: "What is the commercial Capital of Sri Lanka ?",
        optionA: "Colombo",
        optionB: "Kandy",
        optionC: "Anuradhapura",
        optionD: "Jaffna",
        correctOption: "optionA",
		correctOptionID: "option-one-label",
	image : "Images/Q1.jpg"
    },

    {
        question: "What is the currency of Sri Lanka ?",
        optionA: "Escudo",
        optionB: "Rupee",
        optionC: "Pound",
        optionD: "Rupiah",
        correctOption: "optionB",
		correctOptionID: "option-two-label",
	image: "Images/Q2.jpg"
	
    },

    {
        question: "What is the Main Religion in Sri Lanka ?",
        optionA: "Buddhism",
        optionB: "Islam",
        optionC: "Hinduism",
        optionD: "Christian",
        correctOption: "optionA",
		correctOptionID: "option-one-label",
	image : "Images/Q3.jpg"
    },

    {
        question: "What is/are the official Language(s) is Sri Lanka ?",
        optionA: "English",
        optionB: "Tamil",
        optionC: "Tamil and Sinhala",
        optionD: "Sinhala",
        correctOption: "optionC",
		correctOptionID: "option-three-label",
	image : "Images/Q4.jpg"
    },

    {
        question: "Cricket is the most popular sport in Sri Lanka. Whom did they beat in 1996 to win the World Cup?",
        optionA: "Pakistan",
        optionB: "England",
        optionC: "Ausrtalia",
        optionD: "South Africa",
        correctOption: "optionC",
		correctOptionID: "option-three-label",
	image : "Images/Q5.jpg"
    },

    {
        question: "What colour is the animal on the flag of Sri Lanka?" ,
        optionA: "Orange",
        optionB: "Dark Red",
        optionC: "Green",
        optionD: "Gold",
        correctOption: "optionD",
		correctOptionID: "option-four-label",
	image : "Images/Q6.jpg"
    },

    {
        question: " What animal appears on the flag of Sri Lanka?",
        optionA: "Lion",
        optionB: "Tiger",
        optionC: "Panther",
        optionD: "Elephant",
        correctOption: "optionA",
		correctOptionID: "option-one-label",
	image : "Images/Q7.jpg"
    },

    {
        question: "Sri Lanka has various regional styles of cooking depending on local produce. Which area would produce very hot curries ?",
        optionA: "Kandy(Sinhalese)",
        optionB: "Colombo",
        optionC: "Galle",
        optionD: "Jaffna(Tamil)",
        correctOption: "optionD",
		correctOptionID: "option-four-label",
	image : "Images/Q8.jpg"
    },

    {
        question: "How many monsoon seasons are there in Sri Lanka ?",
        optionA: "Four",
        optionB: "One",
        optionC: "Two",
        optionD: "Three",
        correctOption: "optionC",
		correctOptionID: "option-three-label",
	image : "Images/Q9.jpg"
    },

    {
        question: "When did Sri Lanka achieve home rule, becoming a dominion of the UK? ",
        optionA: "May 1952",
        optionB: "Feburary 1948",
        optionC: "October 1979",
        optionD: "Nobember 1979",
        correctOption: "optionB",
		correctOptionID: "option-two-label",
	image : "Images/Q10.jpg"
    }

    

]


let shuffledQuestions = [] //empty array to hold shuffled selected questions

function handleQuestions() { 
    //function to shuffle and push 10 questions to shuffledQuestions array
    while (shuffledQuestions.length <= 9) {
        const random = questions[Math.floor(Math.random() * questions.length)]
        if (!shuffledQuestions.includes(random)) {
            shuffledQuestions.push(random)
        }
    }
}


let questionNumber = 1
let playerScore = 0  
let wrongAttempt = 0 
let indexNumber = 0
const questionStatus = [];

// function for displaying next question in the array to dom
function NextQuestion(index) {
    handleQuestions()
    const currentQuestion = shuffledQuestions[index]
    
    document.getElementById("display-question").innerHTML = currentQuestion.question;
    document.getElementById("option-one-label").innerHTML = currentQuestion.optionA;
    document.getElementById("option-two-label").innerHTML = currentQuestion.optionB;
    document.getElementById("option-three-label").innerHTML = currentQuestion.optionC;
    document.getElementById("option-four-label").innerHTML = currentQuestion.optionD;
    document.getElementById("game-question-image").src = currentQuestion.image;
    document.getElementById(questionNumber).style.color = "green"
}
//gets the choosen option value
function getRadioValue( radioArray ){
	var i;
	for(i=0; i<radioArray.length; i++){
		if (radioArray[i].checked){
			return radioArray[i].value;
		}
	}
	return"";
}
// gets the choosen option number
function getRadioOption( radioArray ){
	var i;
	for(i=0; i<radioArray.length; i++){
		if (radioArray[i].checked){
			return i;
		}
	}
	return"";
}

function checkForAnswer() {
    const currentQuestion = shuffledQuestions[indexNumber] //gets current Question 
    const currentQuestionAnswer = currentQuestion.correctOption //gets current Question's answer
    const options = document.getElementsByName("option"); //gets all radio inputs with name of 'option'
    
	const choosenOption = getRadioValue(options) //gets the choosen option
	const choosenOptionNumber = getRadioOption(options) // gets the choosen option number(index)
	
	//checking to make sure a radio input has been checked
	if (options[0].checked === false && options[1].checked === false && options[2].checked === false && options[3].checked == false) {
		document.getElementById('option-modal').style.display = "flex"
	
    
	} else if(choosenOption===currentQuestionAnswer){
		//checks if the answer is correct or wrong
		
	    document.getElementById(currentQuestion.correctOptionID).style.backgroundColor = "green"
        playerScore++
        indexNumber++
	    questionStatus[questionNumber]="Correct" ;
		questionNumber++
        
    }else if(choosenOption!==currentQuestionAnswer){
		
		//sets the choosen option id to wrong option id(used only when chooesn option is wrong)
	switch (choosenOptionNumber){
		case 0:
			wrongOptionID ="option-one-label"
			break;
		case 1:
			wrongOptionID ="option-two-label"
			break;
		case 2:
			wrongOptionID ="option-three-label"
			break;
		case 3:
			wrongOptionID ="option-four-label"
			break;
	}

	    document.getElementById(wrongOptionID).style.backgroundColor = "red"
        document.getElementById(currentQuestion.correctOptionID).style.backgroundColor = "green"
        wrongAttempt++
        indexNumber++
	    questionStatus[questionNumber]="Incorrect" ;
        questionNumber++
    }
   
   
    
    
}



//called when the next button is called
function handleNextQuestion() {
    checkForAnswer()
    unCheckRadioButtons()
    //delays next question displaying for a second
    setTimeout(() => {
        if (indexNumber <= 9) {
            NextQuestion(indexNumber)
 
        }
        else {
            handleEndGame()
        }
        resetOptionBackground()
    }, 1000);
}

//sets options background back to null after display the right/wrong colors
function resetOptionBackground() {
    document.getElementById("option-one-label").style.backgroundColor = ""
    document.getElementById("option-two-label").style.backgroundColor = ""
	document.getElementById("option-three-label").style.backgroundColor = ""
	document.getElementById("option-four-label").style.backgroundColor = ""
}

// unchecking all radio buttons for next question
function unCheckRadioButtons() {
    const options = document.getElementsByName("option");
    for (let i = 0; i < options.length; i++) {
        options[i].checked = false;
    }
}

// function when all questions are answered
function handleEndGame() {
    stopTimer()
    let remark = null
    const playerGrade =(2*playerScore)-(1*wrongAttempt) ;

    // condition check for player remark and remark color
    if (playerGrade <= 8) {
        remark = "Bad Grades, Keep Practicing."
        document.getElementById("grade").style.backgroundColor = "red";
    }
    else if (playerGrade>=8 && playerGrade<14) {
        remark = "Average Grades, You can do better."
        document.getElementById("grade").style.backgroundColor = "orange";
    }
    else if (playerGrade >= 14) {
        remark = "Excellent, Keep the good work going."
        document.getElementById("grade").style.backgroundColor = "green";
    }
    

    //data to display to score board
    document.getElementById('remarks').innerHTML = remark
    document.getElementById('player-score').innerHTML = playerGrade
    document.getElementById('wrong-answers').innerHTML = wrongAttempt
    document.getElementById('right-answers').innerHTML = playerScore
    document.getElementById('score-modal').style.display = "flex"

}

// function to display the question summary
function displaySummary() {
    document.getElementById('score-modal').style.display = "none"
    //data to display in summary
    document.getElementById('Q1').innerHTML = questionStatus[1];
    document.getElementById('Q2').innerHTML = questionStatus[2];
    document.getElementById('Q3').innerHTML = questionStatus[3];
    document.getElementById('Q4').innerHTML = questionStatus[4];
    document.getElementById('Q5').innerHTML = questionStatus[5];
    document.getElementById('Q6').innerHTML = questionStatus[6];
    document.getElementById('Q7').innerHTML = questionStatus[7];
    document.getElementById('Q8').innerHTML = questionStatus[8];
    document.getElementById('Q9').innerHTML = questionStatus[9];
    document.getElementById('Q10').innerHTML = questionStatus[10];
    document.getElementById('summary-modal').style.display = "flex"

}

//back - goes back to score modal
function backToScoreModal() {
    document.getElementById('summary-modal').style.display = "none"
    handleEndGame()
}


//function to close warning modal
function closeOptionModal() {
    document.getElementById('option-modal').style.display = "none"
	
}