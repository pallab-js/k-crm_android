package com.nexus.crm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexus.crm.ui.theme.StatusCustomer
import com.nexus.crm.ui.theme.StatusLead
import com.nexus.crm.ui.theme.StatusOpportunity
import com.nexus.crm.ui.theme.TextPrimary
import com.nexus.crm.data.model.ContactStatus

@Composable
fun StatusBadge(
    status: ContactStatus,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, text) = when (status) {
        ContactStatus.LEAD -> StatusLead to "LEAD"
        ContactStatus.CUSTOMER -> StatusCustomer to "CUSTOMER"
        ContactStatus.OPPORTUNITY -> StatusOpportunity to "OPPORTUNITY"
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(9999.dp),
        color = backgroundColor.copy(alpha = 0.15f)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = backgroundColor,
            letterSpacing = 0.5.sp
        )
    }
}

@Composable
fun PriorityBadge(
    priority: com.nexus.crm.data.model.Priority,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, text) = when (priority) {
        com.nexus.crm.data.model.Priority.HIGH -> com.nexus.crm.ui.theme.PriorityHigh to "HIGH"
        com.nexus.crm.data.model.Priority.MEDIUM -> com.nexus.crm.ui.theme.PriorityMedium to "MEDIUM"
        com.nexus.crm.data.model.Priority.LOW -> com.nexus.crm.ui.theme.PriorityLow to "LOW"
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(9999.dp),
        color = backgroundColor.copy(alpha = 0.15f)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = backgroundColor
        )
    }
}