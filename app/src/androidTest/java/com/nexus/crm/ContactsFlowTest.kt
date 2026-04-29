package com.nexus.crm

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ContactsFlowTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAddContactFlow() {
        // Navigate to Contacts
        composeTestRule.onNodeWithContentDescription("Contacts").performClick()
        
        // Click Add FAB (Assuming it has content description "Add Contact" or similar)
        // Let's check ContactsScreen.kt for the FAB content description
        composeTestRule.onNodeWithContentDescription("Add Contact").performClick()
        
        // Fill form
        composeTestRule.onNodeWithText("Full Name").performTextInput("Jane Smith")
        composeTestRule.onNodeWithText("Email Address").performTextInput("jane@smith.com")
        
        // Save
        composeTestRule.onNodeWithText("Save Contact").performClick()
        
        // Verify back on Contacts list and new contact exists
        composeTestRule.onNodeWithText("Contacts").assertExists()
        composeTestRule.onNodeWithText("Jane Smith").assertExists()
    }
}
