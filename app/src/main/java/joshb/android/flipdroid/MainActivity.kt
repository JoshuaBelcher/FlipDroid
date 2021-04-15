package joshb.android.flipdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import android.content.SharedPreferences
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Class variables for Loading Preferences & High Scores
    private val PREF_Name = "savedPrefs"
    private var nightModeOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set handle for saved preferences
        var loadedPref: SharedPreferences = getSharedPreferences(PREF_Name, 0)

        // check loaded preferences for theme data
        if (loadedPref.contains("nightMode")) {
            nightModeOn = loadedPref.getBoolean("nightMode", false)
        }

        // if the user has a saved preference for Night Mode being ON, then it will activated now
        if (nightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

    }

    fun playButtonHandler (view: View) {
        var intent= Intent(this, GameField:: class.java)
        startActivity(intent)
    }

    fun scoreboardButtonHandler (view: View) {
        var intent= Intent(this, Scoreboard:: class.java)
        startActivity(intent)
    }

    fun settingsButtonHandler (view: View) {
        var intent= Intent(this, Settings:: class.java)
        intent.putExtra("nightModeOn", nightModeOn)
        startActivity(intent)
    }

    fun helpButtonHandler (view: View) {
        var intent= Intent(this, Help:: class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            R.id.act_scoreboard->{var intent= Intent(this, Scoreboard:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }

            R.id.act_gamefield->{var intent= Intent(this, GameField:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }

            R.id.act_help->{var intent= Intent(this, Help:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            R.id.act_settings->{var intent=Intent(this, Settings:: class.java)
                intent.putExtra("nightModeOn", nightModeOn)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            else->return super.onOptionsItemSelected(item)
        }

    }

}