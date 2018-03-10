package com.nyakokishi.chessborad

/**
 ** Created by nyakokishi on 2017/8/14.
 */
data class Chessman(
        private var x: Char,
        private var y: Int,
        val actor: Actor
) {

    fun xPosition(): Int = ChessUtil.convertLetterToPosition(x)

    fun yPosition(): Int = y

    fun getTag() = "$x$y"

}

