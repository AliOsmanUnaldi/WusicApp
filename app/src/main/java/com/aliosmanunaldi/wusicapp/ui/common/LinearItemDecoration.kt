package com.aliosmanunaldi.wusicapp.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aliosmanunaldi.wusicapp.R


class LinearItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = view.context.resources.getDimensionPixelSize(R.dimen.item_character_spacing)


        outRect.left = spacing
        outRect.right = spacing
        outRect.bottom = spacing
        outRect.top = spacing
    }
}