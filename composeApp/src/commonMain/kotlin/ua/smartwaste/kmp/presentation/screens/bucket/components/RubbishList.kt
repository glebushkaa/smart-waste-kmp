@file:OptIn(ExperimentalFoundationApi::class)

package ua.smartwaste.kmp.presentation.screens.bucket.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun RubbishList(
    modifier: Modifier = Modifier,
    rubbishList: ImmutableList<Rubbish> = persistentListOf(),
    decreaseClicked: (String) -> Unit = {},
    increaseClicked: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = SmartTheme.offset.height.regular,
        ),
    ) {
        item {
            Text(
                text = "Bucket",
                style = SmartTheme.typography.headlineMedium,
                color = SmartTheme.palette.onBackground,
            )
        }
        items(
            items = rubbishList,
            key = { item -> item.name },
        ) { bucketItem ->
            RubbishItem(
                modifier = Modifier.animateItemPlacement(),
                item = bucketItem,
                decreaseCountClicked = {
                    decreaseClicked(bucketItem.name)
                },
                increaseCountClicked = {
                    increaseClicked(bucketItem.name)
                },
            )
        }
    }
}
