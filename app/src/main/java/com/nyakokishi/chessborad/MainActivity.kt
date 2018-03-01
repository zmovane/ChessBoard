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

        val chessboard = findViewById<ChessBoardView>(R.id.chessborad)

        chessboard.addChessman(Chessman(1, 7, Actor.BP))
        chessboard.addChessman(Chessman(2, 7, Actor.BP))
        chessboard.addChessman(Chessman(3, 7, Actor.BP))
        chessboard.addChessman(Chessman(4, 7, Actor.BP))
        chessboard.addChessman(Chessman(5, 7, Actor.BP))
        chessboard.addChessman(Chessman(6, 7, Actor.BP))
        chessboard.addChessman(Chessman(7, 7, Actor.BP))
        chessboard.addChessman(Chessman(8, 7, Actor.BP))


        chessboard.addChessman(Chessman(1, 8, Actor.BR))
        chessboard.addChessman(Chessman(8, 8, Actor.BR))
        chessboard.addChessman(Chessman(2, 8, Actor.BN))
        chessboard.addChessman(Chessman(7, 8, Actor.BN))
        chessboard.addChessman(Chessman(3, 8, Actor.BB))
        chessboard.addChessman(Chessman(6, 8, Actor.BB))
        chessboard.addChessman(Chessman(4, 8, Actor.BQ))
        chessboard.addChessman(Chessman(5, 8, Actor.BK))

        chessboard.addChessman(Chessman(1, 2, Actor.WP))
        chessboard.addChessman(Chessman(2, 2, Actor.WP))
        chessboard.addChessman(Chessman(3, 2, Actor.WP))
        chessboard.addChessman(Chessman(4, 2, Actor.WP))
        chessboard.addChessman(Chessman(5, 2, Actor.WP))
        chessboard.addChessman(Chessman(6, 2, Actor.WP))
        chessboard.addChessman(Chessman(7, 2, Actor.WP))
        chessboard.addChessman(Chessman(8, 2, Actor.WP))


        chessboard.addChessman(Chessman(1, 1, Actor.WR))
        chessboard.addChessman(Chessman(8, 1, Actor.WR))
        chessboard.addChessman(Chessman(2, 1, Actor.WN))
        chessboard.addChessman(Chessman(7, 1, Actor.WN))
        chessboard.addChessman(Chessman(3, 1, Actor.WB))
        chessboard.addChessman(Chessman(6, 1, Actor.WB))
        chessboard.addChessman(Chessman(4, 1, Actor.WQ))
        chessboard.addChessman(Chessman(5, 1, Actor.WK))

    }
}