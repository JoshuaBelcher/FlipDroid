package joshb.android.flipdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import android.content.SharedPreferences
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {
    // Class variables for Saving/Loading Preferences
    private val PREF_Name = "savedPrefs"
    private var editor: SharedPreferences.Editor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // set reference for saved preferences
        var loadedPref: SharedPreferences = getSharedPreferences(PREF_Name, 0)

        // set handle for preferences editor and assign preferences to be edited
        editor = (loadedPref as SharedPreferences).edit()

        // get Night Mode status passed from MainActivity
        var intent = getIntent()
        var nightModeOn = intent.getBooleanExtra("nightModeOn", false)

        // toggle theme switch on if Night Mode status was previously saved as activated
        // NOTE: this ensures consistent switch operation regardless of previously saved status, i.e.,
        // if Night Mode was turned on by loaded preferences, the switch will not sit erroneously in
        // the "Off" position
        if (nightModeOn) {
            switch_theme.isChecked = true
        }

        // default selected game mode to "Fewest Flips"
        var gameMode = "flips"

        // check loaded preferences for game mode data
        if (loadedPref.contains("gameMode")) {
            gameMode = loadedPref.getString("gameMode", "flips").toString()
        }

        // check the radio button corresponding to loaded game mode so that they match
        if (gameMode == "flips") {
            rdo_flip_mode.isChecked = true
        } else if (gameMode == "speed") {
            rdo_speed_mode.isChecked = true
        }

    }

    /*****************************************************************EVENT HANDLERS FOR WIDGETS AND MENU****************************************************/

    // toggle Day/Night Mode and use editor to save choice in Shared Preferences
    fun themeToggleHandler (view: View) {
        if(switch_theme.isChecked!!){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor?.putBoolean("nightMode", true)
            editor?.commit()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor?.putBoolean("nightMode", false)
            editor?.commit()
        }
    }

    // toggle Fewest Flips/Fastest Time Game Mode and use editor to save choice in Shared Preferences
    fun gameToggleHandler (view: View) {
        if (rdo_flip_mode.isChecked) {
            editor?.putString("gameMode", "flips")
            editor?.commit()
        } else if (rdo_speed_mode.isChecked) {
            editor?.putString("gameMode", "speed")
            editor?.commit()
        }
    }

    fun backButtonHandler (view: View) {
        var intent= Intent(this, MainActivity:: class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            R.id.act_main->{var intent= Intent(this, MainActivity:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }

            R.id.act_scoreboard->{var intent= Intent(this, Scoreboard:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }

            R.id.act_help->{var intent= Intent(this, Help:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            R.id.act_gamefield->{var intent=Intent(this, GameField:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            else->return super.onOptionsItemSelected(item)
        }

    }
}