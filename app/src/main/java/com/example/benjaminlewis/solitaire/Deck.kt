package com.example.benjaminlewis.solitaire

import java.util.*

/**
 * Created by benjaminlewis on 2/18/18.
 */
class Deck {

    val cards = Array(52, { Card(it % 13, getSuit(it)) })
    var cardsInDeck = cards.toMutableList()

    fun drawCard() = cardsInDeck.removeAt(0)

    fun reset() {
        cardsInDeck = cards.toMutableList()
        Collections.shuffle(cardsInDeck)
    }

    private fun getSuit(i: Int) = when (i / 13) {
        0 -> clubs
        1 -> diamonds
        2 -> hearts
        else -> spades
    }
}