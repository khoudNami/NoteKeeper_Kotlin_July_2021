package za.co.khoudnami.notekeeper

import android.os.Bundle
import androidx.lifecycle.ViewModel

class ItemsActivityViewModel : ViewModel() {
    val navDrawerDisplaySelectionName =
        "za.co.khoudnami.notekeeper.navDrawerDisplaySelection"

    //default selection when activity is first created is nav_notes
    var navDrawerDisplaySelection = R.id.nav_notes

    private val maxRecentlyVIewNotes = 5
    val recentlyViewNotes = ArrayList<NoteInfo>(maxRecentlyVIewNotes)

    fun addToRecentlyViewNotes(note: NoteInfo) {
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

    fun saveState(outState: Bundle) {
        outState.putInt(navDrawerDisplaySelectionName, navDrawerDisplaySelection)
    }

    fun restoreState(savedInstanceState: Bundle) {
        navDrawerDisplaySelection =
            savedInstanceState.getInt(navDrawerDisplaySelectionName)
    }
}