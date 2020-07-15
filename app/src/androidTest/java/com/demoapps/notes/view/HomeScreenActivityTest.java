package com.demoapps.notes.view;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.demoapps.notes.R;
import com.demoapps.notes.utils.ApplicationConstants;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HomeScreenActivityTest {
    private boolean notesPresent = false;

    @Rule
    public ActivityTestRule<HomeScreenActivity> homeScreenActivityActivityTestRule = new ActivityTestRule<HomeScreenActivity>(HomeScreenActivity.class);

    @Test
    public void testHomeScreenButtons() {

        if(!notesPresent) {
            //CLICK ON ADD NEW LAYOUT ON HOMESCREEN
            Espresso.onView(withId(R.id.addNewNoteLayout)).perform(click());
            SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

            //PRESS BACK
            Espresso.pressBack();
            SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
        }

        //CLICK ON THE ADD NEW BUTTON
        Espresso.onView(withId(R.id.newNote)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //PRESS BACK
        Espresso.pressBack();
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    @Test
    public void testAddNewCheckList() {
        //CLICK ON ADD NEW LAYOUT ON HOMESCREEN
        Espresso.onView(withId(R.id.newNote)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON CHECKLIST LAYOUT
        Espresso.onView(withId(R.id.checkList_layout)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }

    @Test
    public void testAddNewNote() {
        //CLICK ON ADD NEW LAYOUT ON HOMESCREEN
        Espresso.onView(withId(R.id.newNote)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);

        //CLICK ON NEW NOTE LAYOUT
        Espresso.onView(withId(R.id.notes_layout)).perform(click());
        SystemClock.sleep(ApplicationConstants.SYSTEM_CLOCK_SLEEP_TIME);
    }
}
