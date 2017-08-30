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
                        Actor.WP -> R.drawable.wp
                        Actor.WB -> R.drawable.wb
                        Actor.WK -> R.drawable.wk
                        Actor.WN -> R.drawable.wn
                        Actor.WQ -> R.drawable.wq
                        Actor.WR -> R.drawable.wr

                        Actor.BP -> R.drawable.bp
                        Actor.BB -> R.drawable.bb
                        Actor.BK -> R.drawable.bk
                        Actor.BN -> R.drawable.bn
                        Actor.BQ -> R.drawable.bq
                        Actor.BR -> R.drawable.br
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