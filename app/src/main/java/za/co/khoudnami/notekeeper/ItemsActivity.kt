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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.app_bar_items.*
import kotlinx.android.synthetic.main.content_note_list.*


class ItemsActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    NoteRecyclerAdapter.OnNoteSelectedListener {


    private val noteLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    private val noteRecyclerAdapter by lazy {
        val adapter = NoteRecyclerAdapter(this, DataManager.notes)
        adapter.setOnSelectedListener(this)
        adapter
    }

    private val maxRecentlyVIewNotes = 5
    private val recentlyViewNotes = ArrayList<NoteInfo>(maxRecentlyVIewNotes)

    private val recentlyViewNoteRecyclerAdapter by lazy {
        val adapter = NoteRecyclerAdapter(this, recentlyViewNotes)
        adapter.setOnSelectedListener(this) //Tell it to listen because recentlyViewedNotes list wont update if I dont tell recentlyViewedNotesAdapter to listen
        adapter
    }

    private fun displayRecentlyViewNotes() {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = recentlyViewNoteRecyclerAdapter
        nav_view.menu.findItem(R.id.nav_recent_notes).isChecked = true
    }

    override fun onNoteSelected(note: NoteInfo) {
        addToRecentlyViewNotes(note)
    }

    private fun addToRecentlyViewNotes(note: NoteInfo) {
        val existingIndex = recentlyViewNotes.indexOf(note)
        if (existingIndex == -1) {
            recentlyViewNotes.add(0, note)
            for (index in recentlyViewNotes.lastIndex downTo maxRecentlyVIewNotes)
                recentlyViewNotes.removeAt(index)
        } else {
            for (index in (existingIndex - 1) downTo 0)
                recentlyViewNotes[index + 1] = recentlyViewNotes[index]
            recentlyViewNotes[0] = note
        }
    }

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

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
        // It will always be more efficient to use more specific change events if you can. Rely on notifyDataSetChanged as a last resort.
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_notes -> {
                displayNotes()
            }
            R.id.nav_courses -> {
                displayCourses()
            }
            R.id.nav_recent_notes -> {
                displayRecentlyViewNotes()
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

    private fun displayNotes() {
        listItems.layoutManager = noteLayoutManager
        listItems.adapter = noteRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_notes).isChecked = true
    }

    private fun displayCourses() {// could have made it lazy properties
        listItems.layoutManager = GridLayoutManager(this, 2)
        listItems.adapter = CourseRecyclerAdapter(this, DataManager.courses.values.toList())
        nav_view.menu.findItem(R.id.nav_courses).isChecked = true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(listItems, message, Snackbar.LENGTH_LONG).show()
    }

}