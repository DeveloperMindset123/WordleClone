package com.example.myapplication
//note that this is helper code provided by codepath
class FourLetterWordList {
    lateinit var wordToGuess : String  //declare a public string named wordToGuess, which we will be setting out editTextInput to as well
    //List of the most common 4 letter words from https://7esl.com/4-letter-words/
    //hardcode the list of all possible four letter values
    val fourLetterWords = "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    //return a list of four letter words as a list
    fun getAllFourLetterWords(): List<String> {  //define a getter method to retrieve the list of four letter words
        return fourLetterWords.split(",")  //purpose of the split function: Splits this char sequence to a list of strings around occurences of the specified "delimiters"
        /**
         how the split function is defined according to the documentation
         fun CharSequence.split (
            vararg delimiters: String
            ignoreCase: Boolean
            limit: Int = 0
         ): List<String>

         * parameters:
          * delimiters: One or more strings to be used as delimiters
          * ignoreCase: set to true to ignore case when matching a delimiter. By default, the value is false.
          * Limit: The maximum number of substring to return. Zero by default which means no limit is set
         */
    }

    //Returns a random four letter word from the list in all CAPS
    fun getRandomLetterWord(): String {  //define a getter that will return a String is of length 4
        val allWords = getAllFourLetterWords()  //call on this fetter to retrieve the modified string
        val randomNumber = (0..allWords.size).shuffled().last()  //randomly select a index value between 0 and [allWords.size - 1]
        return allWords[randomNumber].uppercase()  //change the returned string to uppercase
    }

    //define a private function that will check the guesses that are made to the string value
    /**
    * Parameters/Fields
    *   wordToGuess: String -- the target word the user is trying to guess
    *   guess: String -- what the user entered as their guess
    *
    * Returns a string of '0', '+', and 'X' where --> note that for the stetch feature, change the view for this
    * '0' represents the right letter in the right place
    * '+' represents the right letter in the wrong place
    * 'X' represents a letter not in the target word
    *
    **/



    //important note here: set wordToGuess to the output from getRandomLetterWord
}