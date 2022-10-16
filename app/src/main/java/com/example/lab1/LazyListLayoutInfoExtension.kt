package com.example.lab1

import androidx.compose.foundation.lazy.LazyListLayoutInfo

fun LazyListLayoutInfo.normilizedItemPosition(key:Any):Float =
    visibleItemsInfo.firstOrNull(){it.key==key}
        ?.let{
            val center = (viewportEndOffset + viewportStartOffset - it.size)/2f
            (it.offset.toFloat()-center)/center
        }
        ?:0F