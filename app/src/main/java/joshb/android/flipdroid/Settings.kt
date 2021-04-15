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
    // Class variables for Saving/Loading Preferences & High Scores
    private val PREF_Name = "savedPrefs"
    private var editor: SharedPreferences.Editor?=null
    private var loadedPref: SharedPreferences?=null

    // Class variables for referencing widgets
    private var themeSwitch: Switch? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // get reference to widgets
        themeSwitch = findViewById<Switch>(R.id.switch_theme)

        // get Night Mode status passed from MainActivity
        var intent = getIntent()
        var nightModeOn = intent.getBooleanExtra("nightModeOn", false)

        // toggle theme switch on if Night Mode status was previously saved as activated
        // NOTE: this ensures consistent switch operation regardless of previously saved status, i.e.,
        // if Night Mode was turned on by loaded preferences, the switch will not sit erroneously in
        // the "Off" position
        if (nightModeOn) {
            themeSwitch?.isChecked = true
        }

        // set reference for saved preferences
        loadedPref = getSharedPreferences(PREF_Name, 0)

        // set handle for preferences editor and assign preferences to be edited
        editor = (loadedPref as SharedPreferences).edit()

    }

    fun themeToggleHandler (view: View) {
        if(themeSwitch?.isChecked!!){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor?.putBoolean("nightMode", true)
            editor?.commit()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor?.putBoolean("nightMode", false)
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