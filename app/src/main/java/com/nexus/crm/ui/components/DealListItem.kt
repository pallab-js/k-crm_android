package com.nexus.crm.ui.components

import androidx.compose.foundation.BorderStroke
import com.nexus.crm.ui.theme.DarkBorder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexus.crm.data.model.Deal
import com.nexus.crm.ui.theme.DarkBackground
import com.nexus.crm.ui.theme.SupabaseGreen
import com.nexus.crm.ui.theme.MidGray
import com.nexus.crm.ui.theme.OffWhite
import com.nexus.crm.ui.theme.LightGray
import java.text.NumberFormat
import java.util.Locale

@Composable
fun DealListItem(
    deal: Deal,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = DarkBackground,
        shape = RoundedCornerShape(12.dp), border = BorderStroke(1.dp, DarkBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = deal.title,
                    color = OffWhite,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
                if (deal.contactName.isNotEmpty()) {
                    Text(
                        text = deal.contactName,
                        color = LightGray,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                DealStageBadge(stage = deal.stage)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = currencyFormat.format(deal.value),
                color = SupabaseGreen,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}