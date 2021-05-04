package joshb.android.flipdroid

class Card(name: String) {

    companion object {
        // Constants representing the opacity value (alpha attribute) to be used for modifying the display of matched card ImageButtons
        // that are "out-of-play"
        val FADED_ALPHA: Float = 0.25f
        val SOLID_ALPHA: Float = 1f
        // Resource string ID of image that will be displayed on the ImageButton when flipped face down
        val backImage: String = "cardback"
    }
    // Name of the card, taken from the array in the Game object
    var cardName: String? = name
        private set
    // Resource string ID of image that will be displayed on the ImageButton when flipped face up
    var faceImage: String? = null
        private set
    // Boolean representing whether card is "face up" or not
    var faceUp: Boolean = false
        private set
    // String ID of the ImageButton that will correspond to this Card object
    var button: String? = null
        private set

    init {
        assignImage(cardName!!)
    }

    // Change card state between face up and face down
    fun flip() {
        faceUp = !faceUp
    }

    // Decide the id of ImageButton to be used, based on the Card object's
    // location index in the deck array
    fun assignButton(deckIndex: Int) {
        when (deckIndex){
            0 -> this.button = "imageButton1"
            1 -> this.button = "imageButton2"
            3 -> this.button = "imageButton4"
            4 -> this.button = "imageButton5"
            2 -> this.button = "imageButton3"
            5 -> this.button = "imageButton6"
            6 -> this.button = "imageButton7"
            7 -> this.button = "imageButton8"
            8 -> this.button = "imageButton9"
            9 -> this.button = "imageButton10"
            10 -> this.button = "imageButton11"
            11 -> this.button = "imageButton12"
        }
    }

    // Decide the image resource location string used to represent this Card object
    // based on the Card's name property
    private fun assignImage(cardName: String) {
        when (cardName) {
            "floppy_disk" -> this.faceImage = "floppydisk"
            "vhs" -> this.faceImage = "vhstape"
            "cd" -> this.faceImage = "compactdisc"
            "data_tape" -> this.faceImage = "datatape"
            "audio_cassette" -> this.faceImage = "cassette"
            "usb_thumb" -> this.faceImage = "usbflash"
        }
    }
}