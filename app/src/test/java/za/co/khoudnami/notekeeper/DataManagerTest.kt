package za.co.khoudnami.notekeeper

import android.provider.ContactsContract
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DataManagerTest {

    @Before
    fun setUp() {
        DataManager.notes.clear()
        DataManager.initializeNotes()
    }


    @Test
    fun addNote() {
        //get the input to use in the addNote() method
        val course = DataManager.courses.get("android_async")!!
        val noteTitle = "This is a test note"
        val noteText = "This is the body of my first test note"

        //add note to notes ArrayList<NoteInfo> and store its position
        val index = DataManager.addNote(course, noteTitle, noteText)

        //get the note you just added to notes ArrayList<NoteInfo> using the index obtained above
        val note = DataManager.notes[index]


        assertEquals(course, note.course) //(E,A)
        assertEquals(noteTitle, note.title)
        assertEquals(noteText, note.text)

    }

    @Test
    fun findSimilarNote() {
        //create the input to use in the findNote() method
        val course = DataManager.courses.get("android_async")!!
        val noteTitle = "This is a test note"
        val noteText1 = "This is the body of my first test note"
        val noteText2 = "This is the body of my second test note"

        //add the notes to notes ArrayList<NoteInfo> and store their positions
        val index1 = DataManager.addNote(course, noteTitle, noteText1)
        val index2 = DataManager.addNote(course, noteTitle, noteText2)

        //use findNote() to look for the note you just added (the one with noteText1)
        val note1 = DataManager.findNote(course, noteTitle, noteText1)
        //store the index of the above note if you find it
        val foundIndex1 = DataManager.notes.indexOf(note1)
        // compare the index you obtained when you added the note with addNote() to the one
        // you obtained when you called notes.indexOf() with the note obtained using findNote()
        // as a parameter
        assertEquals(index1, foundIndex1)

        //use findNote() to look for the note you just added (the one with noteText2)
        val note2 = DataManager.findNote(course, noteTitle, noteText2)
        //store the index of the above note if you find it
        val foundIndex2 = DataManager.notes.indexOf(note2)
        // compare the index you obtained when you added the note with addNote() to the one
        // you obtained when you called notes.indexOf() with the note obtained using findNote()
        // as a parameter
        assertEquals(index2, foundIndex2)
    }
}