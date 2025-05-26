package me.fernandesleite.mahoulist.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ContentScaffold(
    modifier: Modifier = Modifier,
    enableDefaultVerticalPadding: Boolean = false,
    topBar: @Composable () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
    ) { contentPadding ->
        val contentPaddingWithDefaultSpacing = getContentPaddingWithDefaultSpacing(
            contentPadding = contentPadding,
            enableDefaultVerticalPadding = enableDefaultVerticalPadding
        )
        content(
            Modifier
                .consumeWindowInsets(contentPaddingWithDefaultSpacing)
                .padding(contentPaddingWithDefaultSpacing)
        )
    }
}

@Composable
private fun getContentPaddingWithDefaultSpacing(
    contentPadding: PaddingValues,
    enableDefaultVerticalPadding: Boolean,
): PaddingValues {
    return PaddingValues(
        start = calculatePadding(
            originalPadding = contentPadding.calculateStartPadding(
                LocalLayoutDirection.current
            ), additionalPadding = 8.dp
        ),
        top = calculatePadding(
            originalPadding = contentPadding.calculateTopPadding(),
            enableDefaultVerticalPadding = enableDefaultVerticalPadding,
            additionalPadding = 16.dp
        ),
        end = calculatePadding(
            originalPadding = contentPadding.calculateEndPadding(
                LocalLayoutDirection.current
            ), additionalPadding = 8.dp
        ),
        bottom = calculatePadding(
            originalPadding = contentPadding.calculateBottomPadding(),
            enableDefaultVerticalPadding = enableDefaultVerticalPadding,
            additionalPadding = 16.dp
        )
    )
}

@Composable
private fun calculatePadding(
    originalPadding: Dp,
    enableDefaultVerticalPadding: Boolean = true,
    additionalPadding: Dp,
): Dp {
    return if (enableDefaultVerticalPadding) {
        originalPadding.plus(additionalPadding)
    } else {
        originalPadding
    }
}