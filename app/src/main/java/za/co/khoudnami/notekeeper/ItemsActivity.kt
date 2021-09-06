package za.co.khoudnami.notekeeper

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView

import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.app_bar_items.*
import kotlinx.android.synthetic.main.content_note_list.*


class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private val noteLayoutManager by lazy { LinearLayoutManager(this) }

    private val noteRecyclerAdapter by lazy { NoteRecyclerAdapter(this, DataManager.notes) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_items)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, NoteActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        displayNotes()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun displayNotes() {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_notes).isChecked = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
        // It will always be more efficient to use more specific change events if you can. Rely on notifyDataSetChanged as a last resort.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_notes -> {
                displayNotes()
            }
            R.id.nav_courses -> {
                handleSelection("Courses")
            }
            R.id.nav_share -> {
                handleSelection("Don't you think you've share enough")
            }
            R.id.nav_send -> {
                handleSelection("Send")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(listItems, message, Snackbar.LENGTH_LONG).show()
    }

}