package com.example.benjaminlewis.solitaire

import android.content.Context
import android.view.ViewManager
import android.widget.ImageView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by benjaminlewis on 2/19/18.
 */
class WastePileView(context: Context) : ImageView(context) {
    init {
        imageResource = emptyPileDrawable
        onClick {
            GamePresenter.onWasteTap()
        }
    }

    fun update() {
        val cards = GameModel.wastePile
        imageResource = if (cards.size > 0) getCardResId(cards.last()) else emptyPileDrawable
    }
}

fun ViewManager.wastePileView(init: WastePileView.() -> Unit = {}) =
        ankoView({ WastePileView(it) }, 0, init)
