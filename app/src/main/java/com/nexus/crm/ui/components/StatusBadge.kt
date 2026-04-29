package com.nexus.crm.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexus.crm.data.model.ContactStatus
import com.nexus.crm.data.model.DealStage
import com.nexus.crm.data.model.Priority
import com.nexus.crm.ui.theme.*

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
fun DealStageBadge(
    stage: DealStage,
    modifier: Modifier = Modifier
) {
    val text = stage.name.replace("_", " ")
    val color = when (stage) {
        DealStage.APPOINTMENT -> StatusLead
        DealStage.QUALIFIED -> StatusOpportunity
        DealStage.PRESENTATION -> StatusOpportunity
        DealStage.PROPOSAL -> StatusOpportunity
        DealStage.NEGOTIATION -> StatusOpportunity
        DealStage.CLOSED_WON -> StatusCustomer
        DealStage.CLOSED_LOST -> PriorityHigh
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(9999.dp),
        color = color.copy(alpha = 0.1f)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold,
            color = color,
            letterSpacing = 0.4.sp
        )
    }
}

@Composable
fun PriorityBadge(
    priority: Priority,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, text) = when (priority) {
        Priority.HIGH -> PriorityHigh to "HIGH"
        Priority.MEDIUM -> PriorityMedium to "MEDIUM"
        Priority.LOW -> PriorityLow to "LOW"
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