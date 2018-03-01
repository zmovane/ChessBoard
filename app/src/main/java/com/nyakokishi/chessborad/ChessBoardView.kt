package com.nyakokishi.chessborad

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout

/**
 * Created by nyakokishi on 2017/8/12.
 */
class ChessBoardView : FrameLayout, View.OnTouchListener {


    private var darkColor: Int = Color.BLACK
    private var lightColor: Int = Color.WHITE

    private var indicator: View? = null
    private var mChessmanView: ChessmanView? = null

    private var squareWidth: Int? = null
    private var isEnableMove = true

    private lateinit var chessBoardDrawable: ChessBoardDrawable

    constructor(context: Context) : super(context) {
        ChessBoardView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ChessBoardView)
        darkColor = ta.getColor(R.styleable.ChessBoardView_negativeColor, Color.BLACK)
        lightColor = ta.getColor(R.styleable.ChessBoardView_positiveColor, Color.WHITE)
        chessBoardDrawable = ChessBoardDrawable(darkColor, lightColor)

        ta.recycle()

        setOnTouchListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)

        squareWidth ?: kotlin.run {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            squareWidth = width / 8
        }

        background ?: kotlin.run {
            chessBoardDrawable.setBounds(0, 0, width, width)
            background = chessBoardDrawable
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (isEnableMove && mChessmanView != null) {
                    val newX = 1 + (event.x / squareWidth!!).toInt()
                    val newY = 8 - (event.y / squareWidth!!).toInt()
                    mChessmanView?.chessman?.x = newX
                    mChessmanView?.chessman?.y = newY
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (isEnableMove && mChessmanView != null)
                    moveChessman()
                return true
            }
            else -> return super.onTouchEvent(event)
        }
    }


    private fun moveChessman() {
        isEnableMove = false
        indicator?.visibility = View.GONE

        val chessmanLayoutParams = mChessmanView?.layoutParams as FrameLayout.LayoutParams

        val xAnim = ValueAnimator.ofInt(chessmanLayoutParams.leftMargin, squareWidth!! * (mChessmanView?.chessman?.x!! - 1))
        val yAnim = ValueAnimator.ofInt(chessmanLayoutParams.topMargin, squareWidth!! * (8 - mChessmanView?.chessman?.y!!))

        xAnim.addUpdateListener { animation ->
            val layoutParams = mChessmanView?.layoutParams as FrameLayout.LayoutParams
            layoutParams.leftMargin = animation?.animatedValue as Int
            mChessmanView?.layoutParams = layoutParams
        }

        yAnim.addUpdateListener { animation ->
            val layoutParams = mChessmanView?.layoutParams as FrameLayout.LayoutParams
            layoutParams.topMargin = animation?.animatedValue as Int
            mChessmanView?.layoutParams = layoutParams
        }

        val animSet = AnimatorSet()

        animSet.playTogether(xAnim, yAnim)
        animSet.duration = 200L
        animSet.interpolator = LinearInterpolator()
        animSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) = Unit
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationEnd(animation: Animator?) {
                mChessmanView = null
                isEnableMove = true
            }
        })

        animSet.start()
    }

    fun addChessman(chessman: Chessman) {

        squareWidth ?: run {
            Handler().postDelayed({ addChessman(chessman) }, 100)
            return
        }

        val chessmanView = ChessmanView(context)
        chessmanView.chessman = chessman

        val chessmanLayoutParams = FrameLayout.LayoutParams(squareWidth!!, squareWidth!!)
        chessmanLayoutParams.leftMargin = squareWidth!! * (chessman.x - 1)
        chessmanLayoutParams.topMargin = squareWidth!! * (8 - chessman.y)
        addView(chessmanView, chessmanLayoutParams)
        chessmanView.setOnClickListener {

            if (!isEnableMove) return@setOnClickListener

            mChessmanView = chessmanView

            indicator?.let {
                indicator?.visibility = View.VISIBLE
                indicator?.layoutParams = chessmanLayoutParams
            } ?: kotlin.run {
                indicator = View(context).apply {
                    background = ContextCompat.getDrawable(context, R.drawable.frame)
                }
                addView(indicator, chessmanLayoutParams)
            }

        }
    }

}