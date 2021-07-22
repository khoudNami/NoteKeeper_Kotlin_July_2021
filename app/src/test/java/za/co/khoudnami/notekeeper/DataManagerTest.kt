package za.co.khoudnami.notekeeper

import org.junit.Test

import org.junit.Assert.*

class DataManagerTest {

    @Test
    fun addNote() {
        val course = DataManager.courses.get("android_async")!!
        val noteTitle = "This is a test note"
        val noteText = "This is the body of my test note"

        //add note and bring back its position
        val index = DataManager.addNote(course, noteTitle, noteText)

        val note = DataManager.notes[index]

        assertEquals(course, note.course)
        assertEquals(noteTitle, note.title)
        assertEquals(noteText, note.text)
        assertSame(course, note.course) //why does this pass if I am comparing different references??
    }

    @Test
    fun findNote() {
        val course = DataManager.courses.get("android_async")!!
        val noteTitle =
    }
}