package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    //the following code shows how to get a reference to the scene root and create two Scene objects from the layout files

    /*
    * Understaning the difference between ViewGroup and Scene:
    * A viewGroup is an invisible container that defines the layout structure for View and other ViewGroup objects. A scene is a viewController that is part of the specific sequence
    * Understanding the purpose of private lateinit var: The lateinit keyword allows one to avoid initializing a property when an object is constructed. If your property is referenced before being initialized, Kotlin throws an error
    * */
    //we want to initialize the function in the instance the onCreate method gets called
    private lateinit var fadeTransition: Transition
    lateinit var sceneRoot: ViewGroup
    private lateinit var keyframe1Scene: Scene
    private lateinit var keyframe2Scene: Scene
    private lateinit var constraintLayout:ConstraintLayout
    //declare additional global variables (extract the newly defined views from xml file in keyframe2.xml)
    //Also note that initially, aside from guess #1, all other text views will be invisible
    //
    //private lateinit var editText: EditText  //initialize the textView variable, we will use this to keep track of the changes that is placed in the text

    //define the submit and reset buttons
    //private lateinit var submitButton : Button
    private lateinit var resetButton: Button  //note that this button isn't visible until all three guessCount has been used up

    //define the guess check text views that will be made visible corresponding to the resulting output accuracy (note that this is set to invisible initially, so we will need to make it visible)
    private lateinit var GuessCheck1: TextView
    private lateinit var GuessCheck2: TextView
    private lateinit var GuessCheck3: TextView

    //define the textViews that will be displayed based on the user input
    private lateinit var guessOutput1: TextView
    private lateinit var guessOutput2: TextView
    private lateinit var guessOutput3: TextView

    //define the text Views that will display the comparison of the user input to the actual word (note that this is set to invisible initially, so we will need to make it visible)
    private lateinit var ResultingOutput1 : TextView
    private lateinit var ResultingOutput2: TextView
    private lateinit var  ResultingOutput3: TextView

    //initialize the textView to display the correct answer
    private lateinit var correctAnswer : TextView

    //we will use the variable below to instantiate our object
    private lateinit var fourLetterWordList: FourLetterWordList
    private lateinit var resultingOutputContent: String  //this variable will be used to store the output of the function checkGuess
    private lateinit var wordToGuess : String  //this is the actual word that was extracted from the getRandomFourLetterWord method of fourLetterWordList object
    private lateinit var displayCorrectAnswer : String  //this will hold a concactenated string to display the correct answer

    //val constraintSet=ConstraintSet()
    //constraintSet.clone(R.layout.keyframe2)
    //constraintSet.applyTo(myView)
    //TransitionManager.beginDelayedTransition(myView)
    //update MainActivity.kt file with the tutorial from official android studio documentation (reference link: https://developer.android.com/develop/ui/views/layout/constraint-layout#kts)

    /*
    * In android development, setContentView(layout) is a method used to set the content view of an activity to a specified layout
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FourLetterWordList RandomFourLetterWord = new FourLetterWordList(); --> this is the java method of instantiating objects, not kotlin, below is how to instantiate object in kotlin
        fourLetterWordList = FourLetterWordList()
        //retrieve a four letter word
        wordToGuess = fourLetterWordList.getRandomLetterWord()

        var guessCounter=3  //initialize an integer value for the guessCounter
        setContentView(R.layout.activity_main)  //at the start of the app, set the content view to activity_main --> adjust as needed
        //initialize the fade animation
        fadeTransition=Fade()
        //initialize your scene root and scenes here
        sceneRoot = findViewById(R.id.scene_root)  //this is causing issues
        keyframe1Scene=Scene.getSceneForLayout(sceneRoot, R.layout.keyframe1, this)
        keyframe2Scene=Scene.getSceneForLayout(sceneRoot, R.layout.keyframe2, this)

        //transition to the initial scene
        TransitionManager.go(keyframe1Scene)

        constraintLayout = findViewById(R.id.constraint_layout)  //will initially result an error since the xml file hasn't been defined yet --> member variable

        //set up button clikc listener to transition to the next scene
        val button : Button = findViewById(R.id.button2)  //select the start button from keyframe1
        button.setOnClickListener{  //specify the action to take place upon the button being clicked
            //animateToKeyframeTwo
            TransitionManager.go(keyframe2Scene, fadeTransition)
            setupKeyframe2Listeners(keyframe2Scene, sceneRoot)
        }
        //setContentView(R.layout.keyframe2)  --> this will otherwise override the transition view
        //upon being sent to keyframe2, implement the following logic, retrieve the views by their id first

        /**
        *
        * Content that was initially present here, in regards to the UI views, has been moved to the function body of private function setupKeyframe2Listeners
         *
        **/

    }

    //define a seperate function to setup keyframe2 listeners
    private fun setupKeyframe2Listeners(scene: Scene, root: ViewGroup) {
        //now that we are in keyframe 2, setup listeners and UI interactions here
        //Ensure that this method is called after the Scene transitions to keyframe 2 has completed in order to avoid any form of null references
        val submitButton: Button = root.findViewById(R.id.buttonSubmit)
        val resetButton : Button = root.findViewById(R.id.resetButton)
        val editText : EditText = root.findViewById(R.id.edit)

        //retrieve the guess columns that are invisible initially on the left
        var guess2 = findViewById<TextView>(R.id.guess2)
        var guess3 = findViewById<TextView>(R.id.guess3)

        //retrieve the guessCheck Text views
        var GuessCheck1 = findViewById<TextView>(R.id.guessChecker1)
        var GuessCheck2 = findViewById<TextView>(R.id.guessChecker2)
        var GuessCheck3 = findViewById<TextView>(R.id.guessChecker3)

        //retrieve the GuessOutput views
        var guessOutput1 = findViewById<TextView>(R.id.guessOutput1)
        var guessOutput2 = findViewById<TextView>(R.id.guessOutput2)
        var guessOutput3 = findViewById<TextView>(R.id.guessOutput3)

        //retrieve the resulting output values
        var ResultingOutput1 = findViewById<TextView>(R.id.ResultingOutput1)
        var ResultingOutput2 = findViewById<TextView>(R.id.ResultingOutput2)
        var ResultingOutput3 = findViewById<TextView>(R.id.ResultingOutput3)

        //extract the textView that will show the correct answer
        var correctAnswer = findViewById<TextView>(R.id.actualOutput)
        var guessCounter = 3  //initialize this value to 3
        displayCorrectAnswer="Actual Output\n".plus(wordToGuess)  //set this to the text content within the correctAnswer text view

        //define the logic for when the submit button is clicked
        submitButton.setOnClickListener{
            //get Text from editText name view
            var userInput = editText.getText().toString().uppercase()  //retrieve the text and convert it into a string, ensure that the retrieved input is in all uppercase
            //add a conditional statement to check what guessCounter value is at
            if (guessCounter == 3) {
                guessOutput1.text = userInput  //set the user input to GuessOutput 1
                guessOutput1.visibility=View.VISIBLE  //make the textView visible
                //also make the textView for GuessCheck and ResultingOutput becomes visible
                GuessCheck1.visibility=View.VISIBLE
                /**To Do: Implement the logic for comparing the input word to the actual word*/
                var resultingOutputContent=checkGuess(userInput)  //this will return how accurate the user's prediction was
                ResultingOutput1.setText(resultingOutputContent)
                ResultingOutput1.visibility=View.VISIBLE  // --> note that additional conditional statement here is needed to check how the word matches, we will need to retrieve the randomly generated word and iterate through the string, compare the characters, and set the appropriate values x, + or 0

                //display a toast message to indicate what happened
                Toast.makeText(applicationContext, "Guess Counter went down by 1", Toast.LENGTH_SHORT).show()
                guessCounter--  //decrement guessCounter by 1
            } else if (guessCounter == 2) {
                guess2.visibility=View.VISIBLE  //make the left side of the guess visible
                guessOutput2.text = userInput  //set user input to GuessOutput 2
                guessOutput2.visibility=View.VISIBLE  //make the textView visible
                GuessCheck2.visibility=View.VISIBLE  //make the GuessCheck2 text view visible
                resultingOutputContent=checkGuess(userInput)  //this will return how accurate the user's prediction was
                ResultingOutput2.text = resultingOutputContent
                ResultingOutput2.visibility=View.VISIBLE  //display the resulting output based on the comparison of the input word and the actual word

                //display another toast message to indicate what happened
                Toast.makeText(applicationContext, "Guess Counter went down by 1", Toast.LENGTH_SHORT).show()
                guessCounter--  //decrement guess counter by 1
            } else {  //in the instance that guessCounter == 1
                guess3.visibility=View.VISIBLE
                guessOutput3.text = userInput  //set user input to GuessOutput 3
                guessOutput3.visibility=View.VISIBLE  //make the textView visible
                GuessCheck3.visibility=View.VISIBLE  //make the GuessCheck3 text view visible
                resultingOutputContent=checkGuess(userInput)  //this will return how accurate the user's prediction was
                ResultingOutput3.text = resultingOutputContent
                ResultingOutput3.visibility=View.VISIBLE  //display the resultng output based on the comparison of the input word and the actual word

                guessCounter--  //again decrement guess Counter
                //Show the Toast message indicating that reset counter have went down to 0
                Toast.makeText(applicationContext, "Guess counter has reached 0", Toast.LENGTH_SHORT).show()

                //display the correct answer
                correctAnswer.setText(displayCorrectAnswer)
                correctAnswer.visibility=View.VISIBLE

                //make the reset button visible
                resetButton.visibility=View.VISIBLE

                //display the TextView with the correct word


            }
        }
        //now determine the logic for what would happen once resetButton gets clikced (hint: use setOnClickListener
        resetButton.setOnClickListener{
            Toast.makeText(applicationContext, "Guess Counter Reset!", Toast.LENGTH_SHORT).show()
            guessCounter = 3  //reset the value of guessCounter to 3

            //revert all the views to be set as invisible
            resetButton.visibility=View.INVISIBLE
            guessOutput1.visibility=View.INVISIBLE
            guessOutput2.visibility=View.INVISIBLE
            guessOutput3.visibility=View.INVISIBLE
            guess2.visibility=View.INVISIBLE
            guess3.visibility=View.INVISIBLE
            GuessCheck1.visibility=View.INVISIBLE
            GuessCheck2.visibility=View.INVISIBLE
            GuessCheck3.visibility=View.INVISIBLE
            ResultingOutput1.visibility=View.INVISIBLE
            ResultingOutput2.visibility=View.INVISIBLE
            ResultingOutput3.visibility=View.INVISIBLE
            correctAnswer.visibility=View.INVISIBLE
        }
    }
    //define the checkGuess function here instead
    private fun checkGuess(guess: String) : String {  //here, within the parameter, we are specifying what type of data checkGuess (syntactically this is similar to typescript) and outside, we are specifying the output this function returns
        var result = ""  //initialize result as an empty string
        for (i in 0..3)  //checking the characters of the four letter word, ranging from 0..3
        {
            result += if (guess[i]==wordToGuess[i])  //wordToGuess has been defined as a public variable, note that wordToGuess should be changed to
            {  //this conditional checks if the guess matches the word input
                "0"
            } else if (guess[i] in wordToGuess) {  //check if the guess word happens to be in a different position than wordToGuess
                "+"  //set the position of result to +
            } else {  //if the guessed word isn't in the string at all, change result to X
                "X"
            }
        }
        return result
    }

}

//here, in regards to the text views that gets updated, we are primarily concerned with ensuring that the text View shows up only after the userInput has been confirmed (we can use setOnClickListener to implement this).
//understanding member variable in android studio: In object-oriented programming, a member variable (sometimes called a member field) is a variable that is associated with a specfic object, and accessible for all it's methods (member functions)