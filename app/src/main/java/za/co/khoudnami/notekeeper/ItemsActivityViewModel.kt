package za.co.khoudnami.notekeeper

import android.os.Bundle
import androidx.lifecycle.ViewModel

class ItemsActivityViewModel : ViewModel() {
    val navDrawerDisplaySelectionName =
        "za.co.khoudnami.notekeeper.navDrawerDisplaySelection"

    val recentlyViewedNoteIdsName =
        "za.co.khoudnami.notekeeper.recentlyViewedNoteIds"

    var isNewlyCreated = true

    //default selection when activity is first created is nav_notes
    var navDrawerDisplaySelection = R.id.nav_notes

    private val maxRecentlyViewNotes = 5
    val recentlyViewedNotes = ArrayList<NoteInfo>(maxRecentlyViewNotes)

    fun addToRecentlyViewNotes(note: NoteInfo) {
        val existingIndex = recentlyViewedNotes.indexOf(note)
        if (existingIndex == -1) {
            recentlyViewedNotes.add(0, note)
            for (index in recentlyViewedNotes.lastIndex downTo maxRecentlyViewNotes)
                recentlyViewedNotes.removeAt(index)
        } else {
            for (index in (existingIndex - 1) downTo 0)
                recentlyViewedNotes[index + 1] = recentlyViewedNotes[index]
            recentlyViewedNotes[0] = note
        }
    }

    fun saveState(outState: Bundle) {
        outState.putInt(navDrawerDisplaySelectionName, navDrawerDisplaySelection)
        val noteIds = DataManager.noteIdsAsIntArray(recentlyViewedNotes)
        outState.putIntArray(recentlyViewedNoteIdsName, noteIds)
    }

    fun restoreState(savedInstanceState: Bundle) {
        navDrawerDisplaySelection =
            savedInstanceState.getInt(navDrawerDisplaySelectionName)
        val noteIds = savedInstanceState.getIntArray(recentlyViewedNoteIdsName)
        val noteList = DataManager.loadNotes(*noteIds)
        recentlyViewedNotes.addAll(noteList)

    }
}