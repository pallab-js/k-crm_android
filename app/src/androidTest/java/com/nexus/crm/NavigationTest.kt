package com.nexus.crm

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testBottomNavigation() {
        // Starts on Dashboard
        composeTestRule.onNodeWithText("Dashboard").assertExists()
        
        // Navigate to Contacts
        composeTestRule.onNodeWithContentDescription("Contacts").performClick()
        composeTestRule.onNodeWithText("Contacts").assertExists()
        composeTestRule.onNodeWithContentDescription("Contacts").assertIsSelected()

        // Navigate to Deals
        composeTestRule.onNodeWithContentDescription("Deals").performClick()
        composeTestRule.onNodeWithText("Deals").assertExists()
        composeTestRule.onNodeWithContentDescription("Deals").assertIsSelected()

        // Navigate to Tasks
        composeTestRule.onNodeWithContentDescription("Tasks").performClick()
        composeTestRule.onNodeWithText("Tasks").assertExists()
        composeTestRule.onNodeWithContentDescription("Tasks").assertIsSelected()

        // Navigate back to Dashboard
        composeTestRule.onNodeWithContentDescription("Dashboard").performClick()
        composeTestRule.onNodeWithText("Dashboard").assertExists()
        composeTestRule.onNodeWithContentDescription("Dashboard").assertIsSelected()
    }
}
