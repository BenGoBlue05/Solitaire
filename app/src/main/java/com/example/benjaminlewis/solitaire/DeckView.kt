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

val cardBackDrawable = R.drawable.cardback_green5
val emptyPileDrawable = R.drawable.cardback_blue1

class DeckView(context: Context) : ImageView(context) {
    init {
        imageResource = R.drawable.cardback_green5
        onClick {
            GamePresenter.onDeckTap()
        }
    }

    fun update() {
        val cards = GameModel.deck.cardsInDeck
        imageResource = if (cards.size > 0) cardBackDrawable else emptyPileDrawable
    }
}

fun ViewManager.deckView(init: DeckView.() -> Unit = {}) =
        ankoView({DeckView(it)}, 0, init)

