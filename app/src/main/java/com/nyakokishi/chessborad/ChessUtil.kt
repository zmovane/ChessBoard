package com.nyakokishi.chessborad

/**
 ** Created by nyakokishi on 18-3-11.
 */
object ChessUtil{

    fun convertLetterToPosition(letter: Char) =
            listOf('-', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h').indexOf(letter)

    fun convertPositionToLetter(position: Int) =
            listOf('-', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')[position]

    fun convertPositionToTag(x: Int, y: Int): String = "${convertPositionToLetter(x)}$y"

    fun getXPositionFromTag(tag: String): Int = convertLetterToPosition(tag[0])

    fun getYPositionFromTag(tag: String): Int = Integer.valueOf(tag[1].toString())
}