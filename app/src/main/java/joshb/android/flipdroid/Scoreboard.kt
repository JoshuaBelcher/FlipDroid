package joshb.android.flipdroid

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_scoreboard.*

class Scoreboard : AppCompatActivity() {

    // Class variables for Loading Game Mode and High Scores
    private val PREF_Name = "savedPrefs"
    private var highScore: Int? = null
    private var bestTime: String? = null
    private var loadedPref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        // set handles for saved preferences
        loadedPref = getSharedPreferences(PREF_Name, 0)

        // set handle for preferences editor and assign preferences to be edited
        editor = (loadedPref as SharedPreferences).edit()

        // load the high scores from SharedPreferences and display them in the activity views
        if (loadedPref?.contains("flipScore") == true) {
            highScore = loadedPref!!.getInt("flipScore", 30)
        }

        txt_flips_1.text = highScore.toString()

        if (loadedPref?.contains("timeScore") == true) {
            bestTime = loadedPref!!.getString("timeScore", "30:00")
        }

        txt_time_1.text = bestTime
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

            R.id.act_gamefield->{var intent= Intent(this, GameField:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }

            R.id.act_help->{var intent= Intent(this, Help:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            R.id.act_settings->{var intent=Intent(this, Settings:: class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            else->return super.onOptionsItemSelected(item)
        }

    }
}