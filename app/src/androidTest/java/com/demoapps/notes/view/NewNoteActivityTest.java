package com.demoapps.notes.view;

import android.annotation.SuppressLint;
import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.demoapps.notes.R;
import com.demoapps.notes.utils.ApplicationConstants;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewNoteActivityTest {
    private String newNoteTitle;
    private String newNoteText;

    private String secondNoteTitle;
    private String secondNoteText;

    private String updatedNoteTitle;
    private String updatedNoteText;

    private String sameNoteTitle;
    private String sameNoteText;

    @Rule
    public ActivityTestRule<HomeScreenActivity> homeScreenActivityActivityTestRule = new ActivityTestRule<HomeScreenActivity>(HomeScreenActivity.class);

    @Before
    public void initData(){
        newNoteTitle = "Note on " + getCurrentDate();
        newNoteText = "Note Text on " + getCurrentDate();

        secondNoteTitle = "Second Note on " + getCurrentDate();
        secondNoteText = "Second Note on " + getCurrentDate();

        updatedNoteTitle = "Updated Note on " + getCurrentDate();
        updatedNoteText = "Updated Note Text on " + getCurrentDate();

        sameNoteTitle = newNoteTitle;
        sameNoteText = newNoteText;
    }

    private void moveToNewNoteActivity() {
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON ADD NEW LAYOUT ON HOMESCREEN
        Espresso.onView(withId(R.id.newNote)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON NEW NOTE LAYOUT
        Espresso.onView(withId(R.id.notes_layout)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    public String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    @Test
    public void testBlankTitle() {
        moveToNewNoteActivity();

        //CLICK ON SAVE NOTE BUTTON
        Espresso.onView(withId(R.id.doneImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.empty_title_error_msg)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

    }


    @Test
    public void testNoteAddSuccess() {
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
    public void testNoteAddSuccessTwice() {
        moveToNewNoteActivity();

        //INPUT NOTE TITLE
        Espresso.onView(withId(R.id.newNoteEditText)).perform(typeText(secondNoteTitle));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //INPUT NOTE TEXT
        Espresso.onView(withId(R.id.noteEditText)).perform(typeText(secondNoteText));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON SAVE NOTE BUTTON
        Espresso.onView(withId(R.id.doneImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.new_note_noted)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    @Test
    public void testNoteAddWithSameTitleError() {
        moveToNewNoteActivity();

        //INPUT NOTE TITLE
        Espresso.onView(withId(R.id.newNoteEditText)).perform(typeText(sameNoteTitle));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //INPUT NOTE TEXT
        Espresso.onView(withId(R.id.noteEditText)).perform(typeText(sameNoteText));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON SAVE NOTE BUTTON
        Espresso.onView(withId(R.id.doneImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.same_note_error_msg)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    @Test
    public void testNoteDeleteSuccess() {
        //SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON THE NOTE ON THE SPECIFIC POSITION
        Espresso.onView(withId(R.id.notesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON DELETE ICON
        Espresso.onView(withId(R.id.deleteImageButton)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CHECK FOR THE MESSAGE
        Espresso.onView(withId(R.id.dialog_message)).check(matches(withText(R.string.note_deleted)));
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

    }

    @Test
    public void testUpdateNoteSuccess() {
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON THE NOTE ON THE SPECIFIC POSITION
        Espresso.onView(withId(R.id.notesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
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
