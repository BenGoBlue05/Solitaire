package com.example.benjaminlewis.solitaire

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.jetbrains.anko.*

val Context.cardWidth: Int
    get() = (displayMetrics.widthPixels - dip(8)) / 7
val Context.cardHeight: Int
    get() = cardWidth * 190 / 140

fun View.getCardResId(card: Card): Int {
    val resourceName = "card${card.suit}${cardsMap[card.value]}".toLowerCase()
    return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = MainActivityUI()
        ui.setContentView(this)
        GamePresenter.setGameView(ui)
        GameModel.resetGame()
    }


    class MainActivityUI : AnkoComponent<MainActivity>, GameView {

        private var deckView: DeckView? = null
        var wastePileView: WastePileView? = null
        val foundationPileViews: Array<FoundationPileView?> = arrayOfNulls(4)
        val tableauPileViews: Array<TableauPileView?> = arrayOfNulls(7)

        override fun createView(ui: AnkoContext<MainActivity>) = with(ui)
        {
            verticalLayout {
                leftPadding = dip(4)
                rightPadding = dip(4)
                topPadding = dip(8)

                linearLayout {
                    deckView = deckView().lparams(ctx.cardWidth, ctx.cardHeight)
                    wastePileView = wastePileView().lparams(ctx.cardWidth, ctx.cardHeight)

                    view().lparams(ctx.cardWidth, 0)
                    for (i in 0..3) {
                        foundationPileViews[i] = foundationPileView(i).lparams(ctx.cardWidth, ctx.cardHeight)
                    }
                }
                linearLayout {
                    for (i in 0..6) {
                        tableauPileViews[i] = tableauPileView(i).lparams(ctx.cardWidth, matchParent)
                    }
                }.lparams(height = matchParent) { topMargin = ctx.cardHeight / 2 }
            }
        }

        override fun update(model: GameModel) {
            deckView!!.update()
            wastePileView!!.update()
            foundationPileViews.forEach { it!!.update() }
            tableauPileViews.forEach { it!!.update() }
        }

    }
}
