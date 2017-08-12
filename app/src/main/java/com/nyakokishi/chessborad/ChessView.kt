package com.nyakokishi.chessborad

import android.content.Context
import android.support.annotation.IntRange
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by nyakokishi on 2017/8/12.
 */
class ChessView : ImageView {

    var x: Int = 1
        set(@IntRange(from = 1, to = 8) value) {
            field = value
        }
    var y: Int = 1
        set(@IntRange(from = 1, to = 8) value) {
            field = value
        }

    constructor(context: Context) : super(context) {
        ChessView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

}