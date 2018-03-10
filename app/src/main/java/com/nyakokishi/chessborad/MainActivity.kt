package com.nyakokishi.chessborad

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity


/**
 ** Created by nyakokishi on 2017/8/12.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val chessboard = findViewById<ChessBoardView>(R.id.chessborad)

        Handler().postDelayed({
            chessboard.init()
        }, 200)
    }
}