package com.nyakokishi.chessborad

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by nyakokishi on 2017/8/12.
 */
class ChessmanView : ImageView {

    var chessman: Chessman? = null
        set(value) {
            field = value
            val resId =
                    when (value?.actor) {
                        Actor.wp -> R.drawable.wp
                        Actor.wB -> R.drawable.wb
                        Actor.wK -> R.drawable.wk
                        Actor.wN -> R.drawable.wn
                        Actor.wQ -> R.drawable.wq
                        Actor.wR -> R.drawable.wr

                        Actor.bp -> R.drawable.bp
                        Actor.bB -> R.drawable.bb
                        Actor.bK -> R.drawable.bk
                        Actor.bN -> R.drawable.bn
                        Actor.bQ -> R.drawable.bq
                        Actor.bR -> R.drawable.br
                        else -> 0
                    }
            setBackgroundDrawable(ContextCompat.getDrawable(context, resId))
        }

    constructor(context: Context) : super(context) {
        ChessmanView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

}