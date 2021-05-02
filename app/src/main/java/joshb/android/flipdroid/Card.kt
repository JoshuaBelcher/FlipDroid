package joshb.android.flipdroid

class Card {
    // Constants representing the opacity value (alpha attribute) to be used for displaying solid and faded card ImageButtons
    private val SOLID_ALPHA: String = "1"
    private val FADED_ALPHA: String = "0.25"

    // Name of the card, taken from the array in the Game object
    private var cardName: String? = null

    // String ID of the ImageButton that will correspond to this Card object
    private var button: String? = null

    // Resource string ID of image that will be displayed on the ImageButton when flipped face up
    private var faceImage: String? = null

    // Resource string ID of image that will be displayed on the ImageButton when flipped face down
    private var backImage: String? = "@drawable/cardback"

    // Boolean representing whether card is "face up" or not
    private var faceUp: Boolean = false

    // Index location of this card once inserted in the Game
    // object's deck array
    var deckIndex: Int? = null


    constructor(name: String) {
        this.cardName = name
        assignImage(cardName!!)
    }

    // Decide the id of ImageButton to be used, based on the Card object's
    // location index in the deck array
    fun assignButton(deckIndex: Int) {
        when (deckIndex){
            0 -> this.button = "@+id/imageButton1"
            1 -> this.button = "@+id/imageButton2"
            2 -> this.button = "@+id/imageButton3"
            3 -> this.button = "@+id/imageButton4"
            4 -> this.button = "@+id/imageButton5"
            5 -> this.button = "@+id/imageButton6"
            6 -> this.button = "@+id/imageButton7"
            7 -> this.button = "@+id/imageButton8"
            8 -> this.button = "@+id/imageButton9"
            9 -> this.button = "@+id/imageButton10"
            10 -> this.button = "@+id/imageButton11"
            11 -> this.button = "@+id/imageButton12"
        }
    }

    // Decide the image resource location string used to represent this Card object
    // based on the Card's name property
    private fun assignImage(cardName: String) {
        when (cardName) {
            "floppy_disk" -> this.faceImage = "@drawable/floppydisk"
            "vhs" -> this.faceImage = "@drawable/vhstape"
            "cd" -> this.faceImage = "@drawable/compactdisc"
            "data_tape" -> this.faceImage = "@drawable/datatape"
            "audio_cassette" -> this.faceImage = "@drawable/cassette"
            "usb_thumb" -> this.faceImage = "@drawable/usbflash"
        }
    }
}