package joshb.android.flipdroid

import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_game_field.*

class GameField : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_field)

        startNewGame()
    }

    // When touchDisabled is "true," this prevents any touch events from being handled by the views in the activity
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return if (!touchDisabled) {
            super.dispatchTouchEvent(ev)
        } else {
            touchDisabled
        }
    }

    private var touchDisabled: Boolean = false

    // Instantiates a new game session (Game object), fills the deck with Card objects, shuffles the deck,
    // then "deals" them by associated each card ImageButton with an event handler
    private fun startNewGame () {
        val currentGame = Game()
        currentGame.fillDeck()
        currentGame.deck.shuffle()
        setCardButtonHandlers(currentGame)
    }

    private fun displayResultHandler () {
        // TODO: CREATE ALERT DIALOG THAT WILL ANNOUNCE A VICTORY THEN
        // TODO: OFFER THE CHOICE OF STARTING A NEW GAME OR QUITTING BACK TO THE MAIN MENU
        val resultAlert = AlertDialog.Builder(this)
        resultAlert.setTitle("All cards matched!")
        resultAlert.setMessage("Do you want to play again?")
        resultAlert.setPositiveButton("YES") {
                dialogInterface: DialogInterface, i: Int -> startNewGame()
        }
        resultAlert.setNegativeButton("NO") {
                dialogInterface: DialogInterface, i: Int ->
                    run {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
        }
        resultAlert.show()
    }

    // Assigns an event handler to each card ImageButton. Each handler is associated with one of the dealt Card objects.
    private fun setCardButtonHandlers (currentGame: Game) {
        // Each card in the deck generates the ID of its ImageButton, according to its index position in the deck
        for ((i, card) in currentGame.deck.withIndex()) {
            card.assignButton(i)

            // A reference to the ImageButton is stored, located using the ID generated above
            val cardButton = findViewById<ImageButton>(resources.getIdentifier
                (card.button, "id", this.getPackageName()))

            // Click event handler for the card ImageButton
            cardButton.setOnClickListener {
                // Temporarily disables all touch events in the activity while the current event's game logic finishes executing
                touchDisabled = true

                // Flip the card associated with the triggering ImageButton
                flipCard(card, cardButton)

                // Check to see if the flipped cards are a matched pair
                val result = currentGame.checkMatch(card, cardButton)

                if (result == "no") {
                    // If no match, reset for next round after brief delay
                    delayTouch(currentGame)
                } else if (result == "yes") {
                    // If yes match, the matched cards remain face up but transparent
                    currentGame.firstButton?.alpha = Card.FADED_ALPHA
                    currentGame.secondButton?.alpha = Card.FADED_ALPHA

                    // Increment number of matches obtained
                    currentGame.matches += 1

                    // Check for victory condition and either end game session or reset for next round
                    if (currentGame.matches == Game.MATCHES_POSSIBLE) {
                        resetCardImages()
                        touchDisabled = false
                        displayResultHandler()
                    } else {
                        touchDisabled = false

                        currentGame.resetRound()
                    }
                } else {
                    touchDisabled = false
                }

            }
        }
    }

    // Flips card state then sets button interactivity and displayed image as appropriate
    private fun flipCard (card: Card?, cardButton: ImageButton?) {
        card?.flip()

        if (card?.faceUp == true) {
            cardButton?.isClickable = false
            cardButton?.setImageResource(resources.getIdentifier
                (card.faceImage, "drawable", getPackageName()))
        } else if (card?.faceUp == false){
            cardButton?.isClickable = true
            cardButton?.setImageResource(resources.getIdentifier
                (Card.backImage, "drawable", getPackageName()))
        }
    }

    // Induces a slight delay to ensure the player has time to see the second flipped card before
    // the round resets on a failed match
    private fun delayTouch(currentGame: Game) {
        val timer = object: CountDownTimer(1000, 500) {
            // No tick notification needed, thus empty code block for implementation only
            override fun onTick(millisUntilFinished: Long){}

            override fun onFinish() {
                flipCard(currentGame.firstFlip, currentGame.firstButton)
                flipCard(currentGame.secondFlip, currentGame.secondButton)
                currentGame.resetRound()
                touchDisabled = false
            }

        }

        timer.start()
    }

    // Returns all the card ImageButtons to their normal face down display
    private fun resetCardImages() {
        var buttonContainer: ViewGroup = findViewById(R.id.gameFieldLayout)

        var imageButtons = ArrayList<ImageButton>()

        var count: Int = buttonContainer.getChildCount() - 1

        for (i in 0..count) {
            var view: View = buttonContainer.getChildAt(i)
            if (view is ImageButton) {
                imageButtons.add(view)
            }
        }

        for (button in imageButtons) {
            button.setImageResource(resources.getIdentifier
                (Card.backImage, "drawable", getPackageName()))
            button.alpha = Card.SOLID_ALPHA
        }
    }

    // TODO: DO ALL NAVIGATION BUTTONS ON THIS ACTIVITY NEED CODE TO ENSURE GAME AND CARD OBJECTS ARE DESTROYED BEFORE
    // TODO: LOADING NEXT ACTIVITY, OR WILL KOTLIN GARBAGE COLLECTION DO THIS AUTOMATICALLY WHEN THIS ACTIVITY IS ABANDONED?

    fun backButtonHandler (view: View) {
        val intent= Intent(this, MainActivity:: class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.act_main->{
                val intent= Intent(this, MainActivity:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }

            R.id.act_scoreboard->{
                val intent= Intent(this, Scoreboard:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }

            R.id.act_help->{
                val intent= Intent(this, Help:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            R.id.act_settings->{
                val intent=Intent(this, Settings:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            else->return super.onOptionsItemSelected(item)
        }

    }
}