package joshb.android.flipdroid

class Game {
    // Constants representing the total number of cards our deck can hold and, by extension, the number of matched pairs possible
    private val TOTAL_CARDS: Int = 12
    private val MATCHES_POSSIBLE: Int = TOTAL_CARDS / 2

    // The names of our various cards, stored in a basic array
    val CARD_NAMES = arrayOf("floppy_disk", "floppy_disk", "vhs", "vhs", "cd", "cd",  "data_tape",
        "data_tape", "audio_cassette", "audio_cassette", "usb_thumb", "usb_thumb")

    // The Game Mode, "Fewest Flips" or "Fastest Time" that was chosen in "Settings" and saved in SharedPreferences
    private var gameMode: String = "flips" //TODO: EVENTUALLY THIS MUST BE DETERMINED BY LOADED PREFERENCES

    // TODO: IMPLEMENT A SELECTION STATEMENT, BASED ON MODE, WHICH EITHER ZEROES(OR NULLS) FLIP SCORE OR
    // TODO: SETS UP THE TIMER. RELEVANT HIGH SCORE WILL ALSO NEED TO BE DISPLAYED. THIS ALL PASSED INTO CONSTRUCTOR.

    // Stores the names of the two cards that are flipped over each round
    private var firstFlip: String? = null
    private var secondFlip: String? = null

    // Number of successful matches made in this game session
    private var matches: Int = 0

    // The ArrayList that will hold the "deck" of Card objects that will be instantiated
    private var deck = ArrayList<String>()

    /*
    TODO: IMPLEMENT IMPROVED CONSTRUCTOR METHOD
    constructor(NEEDS GAME MODE AND HIGH SCORE) {
    }
    */

    private fun setCardButtonHandlers () {
        // TODO: HANDLE WIRING OF ONCLICK EVENT HANDLERS FOR IMAGE BUTTONS
        // TODO: Game needs to wire each individual handler to each buttons, while generating each
        // TODO: Card object, that way the handler can accept that object as parameter
    }

}