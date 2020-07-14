package com.demoapps.notes.utils;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class AppDatabaseTest {
    private Context context = Mockito.mock(Context.class);


    @Test
    public void testAppDatabaseInstance() {
        AppDatabase testAppDatabase = AppDatabase.getInstance(context);
        Assert.assertEquals(AppDatabase.getInstance(context), testAppDatabase);
    }

}
