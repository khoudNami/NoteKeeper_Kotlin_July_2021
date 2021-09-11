package za.co.khoudnami.notekeeper

/**
 * Annotation imports from JUnit
 */
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
/**
 * Test runner imports
 */
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule

import org.junit.Assert.*

import org.hamcrest.Matchers.*

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*

/**
 * Imports for testing NavigationDrawer and RecyclerView
 * Requires a contrib related gradle dependency:
 * implementation 'androidx.test.espresso:espresso-contrib:3.4.0'
 */

import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions


@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @Rule
    @JvmField
    val itemsActivity = ActivityScenarioRule(ItemsActivity::class.java)

    @Test
    fun selectNoteAfterNavigationDrawerChange() {
        onView(
            withId(R.id.drawer_layout)
        ).perform(
            DrawerActions.open()
        )

        onView(
            withId(R.id.nav_view)
        ).perform(
            NavigationViewActions.navigateTo(R.id.nav_courses)
        )

        var coursePosition = 0
        onView(
            withId(R.id.listItems)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<CourseRecyclerAdapter.CustomViewHolder>(
                coursePosition,
                click()
            )
        )

        onView(
            withId(R.id.drawer_layout)
        ).perform(
            DrawerActions.open()
        )

        onView(
            withId(R.id.nav_view)
        ).perform(
            NavigationViewActions.navigateTo(R.id.nav_notes)
        )

        val notePosition = 0
        onView(
            withId(R.id.listItems)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<NoteRecyclerAdapter.ViewHolder>(
                notePosition,
                click()
            )
        )

        val note = DataManager.notes[0]
        onView(withId(R.id.spinnerCourses))
            .check(
                matches(
                    withSpinnerText(note.course?.title)
                )
            )


    }

}