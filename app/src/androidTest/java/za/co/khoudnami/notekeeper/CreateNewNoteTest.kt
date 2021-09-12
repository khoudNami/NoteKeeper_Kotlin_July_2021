package za.co.khoudnami.notekeeper

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.*


@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest {

    @Rule
    @JvmField
    val noteListActivity = ActivityScenarioRule(NoteListActivity::class.java)

    @Test
    fun createNewNote() {
        val course = DataManager.courses["android_async"]
        val noteTitle = "Test note title"
        val noteText = "This is the body of our test note"

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.spinnerCourses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())

        onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.textNoteText)).perform(
            typeText(noteText),
            ViewActions.closeSoftKeyboard() //use the one from ViewActions
        )

        pressBack()// how can i verify that it is being pressed? & why not use the one from ViewActions

        // Verifying app logic during UI interaction. verifying the note creation behavior by
        // checking if what i put in is actually what i get out. * We are checking if the newly
        // created note is actually in the DataManager.notes list. We are verifying if the user
        // interaction actually created the new note.
        // E -expected value(what i put in) /A -actual value to check against(what i got out)
        val newlyCreatedNote = DataManager.notes.last()
        assertEquals(course, newlyCreatedNote.course)
        assertEquals(noteTitle, newlyCreatedNote.title)
        assertEquals(noteText, newlyCreatedNote.text)
    }
}