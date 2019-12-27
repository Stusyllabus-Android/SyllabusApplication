package com.stu.syllabus.login.login;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.stu.syllabus.R;

import static org.junit.Assert.*;

/**
 * yuan
 * 2019/12/25
 **/
@RunWith(AndroidJUnit4.class)
@LargeTest //允许测试需要较大消耗
public class LoginActivityTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(LoginActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {

    }

    @Test
    public void getContentView() {
    }

    @Test
    public void setLogin() {
        Espresso.onView(ViewMatchers.withId(R.id.accountEditText)).perform(ViewActions.typeText("17sfyuan"));
        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText)).perform(ViewActions.typeText("xxxxxxxx"));
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());
    }

    @Test
    public void toMainView() {
    }

    @Test
    public void showLoading() {
    }

    @Test
    public void showFailMessage() {
    }
}