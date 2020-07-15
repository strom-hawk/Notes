package com.demoapps.notes.view;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.demoapps.notes.R;
import com.demoapps.notes.utils.ApplicationConstants;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewNoteActivityTest {
    private String newNoteTitle = "Testing Note Title.";
    private String newNoteText = "Testing Note Text.";

    private String updatedNoteTitle = "Updated Note Title.";
    private String updatedNoteText = "Updated Note Text.";

    @Rule
    public ActivityTestRule<HomeScreenActivity> homeScreenActivityActivityTestRule = new ActivityTestRule<HomeScreenActivity>(HomeScreenActivity.class);

    private void moveToNewNoteActivity() {
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON ADD NEW LAYOUT ON HOMESCREEN
        Espresso.onView(withId(R.id.newNote)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON NEW NOTE LAYOUT
        Espresso.onView(withId(R.id.notes_layout)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    @Test
    @Ignore
    public void checkBlankTitle() {
        moveToNewNoteActivity();

        //CLICK ON SAVE NOTE BUTTON
        Espresso.onView(withId(R.id.doneImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.empty_title_error_msg)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

    }


    @Test
    @Ignore
    public void checkNoteSaveSuccess() {
        moveToNewNoteActivity();

        //INPUT NOTE TITLE
        Espresso.onView(withId(R.id.newNoteEditText)).perform(typeText(newNoteTitle));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //INPUT NOTE TEXT
        Espresso.onView(withId(R.id.noteEditText)).perform(typeText(newNoteText));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON SAVE NOTE BUTTON
        Espresso.onView(withId(R.id.doneImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.new_note_noted)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    @Test
    @Ignore
    public void checkSameNoteSavingError() {
        moveToNewNoteActivity();

        //INPUT NOTE TITLE
        Espresso.onView(withId(R.id.newNoteEditText)).perform(typeText(newNoteTitle));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //INPUT NOTE TEXT
        Espresso.onView(withId(R.id.noteEditText)).perform(typeText(newNoteText));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON SAVE NOTE BUTTON
        Espresso.onView(withId(R.id.doneImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.same_note_error_msg)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    @Test
    @Ignore
    public void checkDeleteNoteSuccess() {
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON NOTES RECYCLER VIEW
        Espresso.onView(withId(R.id.notesRecyclerView)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON DELETE ICON
        Espresso.onView(withId(R.id.deleteImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.note_deleted)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

    }

    @Test
    public void checkUpdateNoteSuccess() {
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON NOTES RECYCLER VIEW
        Espresso.onView(withId(R.id.notesRecyclerView)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLEAN NOTE TITLE
        Espresso.onView(withId(R.id.newNoteEditText)).perform(clearText());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //INPUT NOTE TITLE
        Espresso.onView(withId(R.id.newNoteEditText)).perform(typeText(updatedNoteTitle));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLEAN NOTE TEXT
        Espresso.onView(withId(R.id.noteEditText)).perform(clearText());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //INPUT NOTE TEXT
        Espresso.onView(withId(R.id.noteEditText)).perform(typeText(updatedNoteText));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON SAVE NOTE BUTTON
        Espresso.onView(withId(R.id.doneImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.note_updated)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

    }

}
