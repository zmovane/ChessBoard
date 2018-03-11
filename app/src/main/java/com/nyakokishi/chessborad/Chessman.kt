package com.nyakokishi.chessborad

/**
 ** Created by nyakokishi on 2017/8/14.
 */
data class Chessman(
        private var position: String,
        val actor: Actor
) {

    fun xPosition(): Int = ChessUtil.convertLetterToPosition(position[0])

    fun yPosition(): Int = Integer.valueOf(position[1].toString())

    fun getTag() = position

}

