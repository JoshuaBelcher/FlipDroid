package joshb.android.flipdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class Scoreboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
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