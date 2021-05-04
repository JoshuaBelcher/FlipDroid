package joshb.android.flipdroid

import android.os.CountDownTimer
import android.widget.ImageButton

class Game {
    // Constants representing the total number of cards our deck can hold and, by extension, the number of matched pairs possible
    companion object{
        private val TOTAL_CARDS: Int = 12
        val MATCHES_POSSIBLE: Int = TOTAL_CARDS / 2
    }

    // The names of our various cards, stored in a basic array
    private val CARD_NAMES = arrayOf("floppy_disk", "floppy_disk", "vhs", "vhs", "cd", "cd",  "data_tape",
        "data_tape", "audio_cassette", "audio_cassette", "usb_thumb", "usb_thumb")

    // The Game Mode, "Fewest Flips" or "Fastest Time" that was chosen in "Settings" and saved in SharedPreferences
    private var gameMode: String = "flips" //TODO: EVENTUALLY THIS MUST BE DETERMINED BY LOADED PREFERENCES

    // Number of successful matches made in this game session
    var matches: Int = 0

    // Stores reference to the two cards that are flipped over each round and their corresponding buttons
    var firstFlip: Card? = null
        private set
    var secondFlip: Card? = null
        private set

    var firstButton: ImageButton? = null
        private set
    var secondButton: ImageButton? = null
        private set

    // The ArrayList that will hold the "deck" of Card objects that will be instantiated
    var deck = ArrayList<Card>()
        private set

    /*
    TODO: IMPLEMENT IMPROVED CONSTRUCTOR METHOD THAT INCORPORATES GAME MODE AND HIGH SCORES
    constructor() {
        // TODO: IMPLEMENT A SELECTION STATEMENT, BASED ON MODE, WHICH EITHER ZEROES(OR NULLS) FLIP SCORE OR
        // TODO: SETS UP THE TIMER. RELEVANT HIGH SCORE AND CURRENT SCORE (FLIPS OR TIME) WILL ALSO NEED TO BE DISPLAYED.
    }
    */

    // Instantiates Card objects using the provided names then adds them into the deck ArrayList
    fun fillDeck () {
        for (name in CARD_NAMES) {
            deck.add(Card(name))
        }
    }

    // Stores the result (Card object and associated ImageButton) of both consecutive flips
    // then compares the Card names to see if they match
    fun checkMatch (card: Card, button: ImageButton): String {
        var matched: String = "waiting"

        if (firstFlip == null) {
            firstFlip = card
            firstButton = button
        } else {
            secondFlip = card
            secondButton = button

            matched = if (firstFlip!!.cardName == secondFlip!!.cardName) {
                "yes"
            } else {
                "no"
            }
        }
        return matched
    }

    // Resets the stored flip results for the next round
    fun resetRound () {
        firstFlip = null
        firstButton = null
        secondFlip = null
        secondButton = null
    }

}