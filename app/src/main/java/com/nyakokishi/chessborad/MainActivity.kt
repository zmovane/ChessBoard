package com.nyakokishi.chessborad

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity


/**
 * Created by nyakokishi on 2017/8/12.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chessboard = findViewById(R.id.chessborad) as ChessBoardView

        val bb = ContextCompat.getDrawable(this, R.drawable.bb)
        val bk = ContextCompat.getDrawable(this, R.drawable.bk)
        val bn = ContextCompat.getDrawable(this, R.drawable.bn)
        val bp = ContextCompat.getDrawable(this, R.drawable.bp)
        val bq = ContextCompat.getDrawable(this, R.drawable.bq)
        val br = ContextCompat.getDrawable(this, R.drawable.br)

        val wb = ContextCompat.getDrawable(this, R.drawable.wb)
        val wk = ContextCompat.getDrawable(this, R.drawable.wk)
        val wn = ContextCompat.getDrawable(this, R.drawable.wn)
        val wp = ContextCompat.getDrawable(this, R.drawable.wp)
        val wq = ContextCompat.getDrawable(this, R.drawable.wq)
        val wr = ContextCompat.getDrawable(this, R.drawable.wr)

        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 1; y = 7 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 2; y = 7 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 3; y = 7 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 4; y = 7 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 5; y = 7 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 6; y = 7 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 7; y = 7 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bp); x = 8; y = 7 })

        chessboard.addChessman(ChessView(this).apply { setImageDrawable(br); x = 1; y = 8 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(br); x = 8; y = 8 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bn); x = 2; y = 8 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bn); x = 7; y = 8 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bb); x = 3; y = 8 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bb); x = 6; y = 8 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bq); x = 4; y = 8 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(bk); x = 5; y = 8 })

        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 1; y = 2 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 2; y = 2 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 3; y = 2 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 4; y = 2 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 5; y = 2 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 6; y = 2 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 7; y = 2 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wp); x = 8; y = 2 })

        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wr); x = 1; y = 1 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wr); x = 8; y = 1 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wn); x = 2; y = 1 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wn); x = 7; y = 1 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wb); x = 3; y = 1 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wb); x = 6; y = 1 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wq); x = 4; y = 1 })
        chessboard.addChessman(ChessView(this).apply { setImageDrawable(wk); x = 5; y = 1 })


    }
}