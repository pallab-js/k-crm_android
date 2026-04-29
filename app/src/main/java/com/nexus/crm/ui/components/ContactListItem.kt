package com.nexus.crm.ui.components

import androidx.compose.foundation.BorderStroke
import com.nexus.crm.ui.theme.DarkBorder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexus.crm.data.model.Contact
import com.nexus.crm.ui.theme.DarkBackground
import com.nexus.crm.ui.theme.SupabaseGreen
import com.nexus.crm.ui.theme.MidGray
import com.nexus.crm.ui.theme.OffWhite
import com.nexus.crm.ui.theme.LightGray

@Composable
fun ContactListItem(
    contact: Contact,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
import androidx.compose.foundation.BorderStroke
import com.nexus.crm.ui.theme.DarkBorder

// ...

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = DarkBackground,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, DarkBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(SupabaseGreen.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = contact.name.take(1).uppercase(),
                    color = SupabaseGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = contact.name,
                    color = OffWhite,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                if (contact.company.isNotEmpty()) {
                    Text(
                        text = contact.company,
                        color = LightGray,
                        fontSize = 14.sp
                    )
                }
            }

            StatusBadge(status = contact.status)
        }
    }
}