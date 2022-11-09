package com.example.cryptocurrencyapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptocurrencyapp.presentation.view.fragments.CryptoListFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4 ::class)
internal class MainActivityTest
@Test
fun nav_detail() {
    val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )
    val titleScenario = launchFragmentInContainer<CryptoListFragment>()
    titleScenario.onFragment{ fragment ->
        navController.setGraph(R.navigation.nav_graph)

        Navigation.setViewNavController(fragment.requireView(),navController)
    }

    Espresso.onView(ViewMatchers.withId(R.id.box_crypto_item)).perform(ViewActions.click())
    //assertThat(navController.currentDestination?.id).isEqualsTo(R.id.detailCoinFragment)
}
