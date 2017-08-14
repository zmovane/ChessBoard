package com.nyakokishi.chessborad

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Created by nyakokishi on 2017/8/12.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val chessboard = findViewById(R.id.chessborad) as ChessBoardView

        chessboard.addChessman(Chessman(1, 7, Actor.bp))
        chessboard.addChessman(Chessman(2, 7, Actor.bp))
        chessboard.addChessman(Chessman(3, 7, Actor.bp))
        chessboard.addChessman(Chessman(4, 7, Actor.bp))
        chessboard.addChessman(Chessman(5, 7, Actor.bp))
        chessboard.addChessman(Chessman(6, 7, Actor.bp))
        chessboard.addChessman(Chessman(7, 7, Actor.bp))
        chessboard.addChessman(Chessman(8, 7, Actor.bp))


        chessboard.addChessman(Chessman(1, 8, Actor.bR))
        chessboard.addChessman(Chessman(8, 8, Actor.bR))
        chessboard.addChessman(Chessman(2, 8, Actor.bN))
        chessboard.addChessman(Chessman(7, 8, Actor.bN))
        chessboard.addChessman(Chessman(3, 8, Actor.bB))
        chessboard.addChessman(Chessman(6, 8, Actor.bB))
        chessboard.addChessman(Chessman(4, 8, Actor.bQ))
        chessboard.addChessman(Chessman(5, 8, Actor.bK))

        chessboard.addChessman(Chessman(1, 2, Actor.wp))
        chessboard.addChessman(Chessman(2, 2, Actor.wp))
        chessboard.addChessman(Chessman(3, 2, Actor.wp))
        chessboard.addChessman(Chessman(4, 2, Actor.wp))
        chessboard.addChessman(Chessman(5, 2, Actor.wp))
        chessboard.addChessman(Chessman(6, 2, Actor.wp))
        chessboard.addChessman(Chessman(7, 2, Actor.wp))
        chessboard.addChessman(Chessman(8, 2, Actor.wp))


        chessboard.addChessman(Chessman(1, 1, Actor.wR))
        chessboard.addChessman(Chessman(8, 1, Actor.wR))
        chessboard.addChessman(Chessman(2, 1, Actor.wN))
        chessboard.addChessman(Chessman(7, 1, Actor.wN))
        chessboard.addChessman(Chessman(3, 1, Actor.wB))
        chessboard.addChessman(Chessman(6, 1, Actor.wB))
        chessboard.addChessman(Chessman(4, 1, Actor.wQ))
        chessboard.addChessman(Chessman(5, 1, Actor.wK))

    }
}