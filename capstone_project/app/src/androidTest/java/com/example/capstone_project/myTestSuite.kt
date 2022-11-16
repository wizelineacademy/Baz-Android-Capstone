package com.example.capstone_project

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.capstone_project.presentation.ui.view.CriptoListFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class myTestSuite {

    private lateinit var scenario: FragmentScenario<CriptoListFragment>

    @Test
    fun testEventFragment() {
        val scenario = launchFragmentInContainer<CriptoListFragment>(
            initialState = Lifecycle.State.INITIALIZED
        )
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Capstone_project)
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Loading_State() {
        // TODO
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Empty_Actors_List_State() {
        // TODO
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Actors_List_State() {
        // TODO
    }
}
