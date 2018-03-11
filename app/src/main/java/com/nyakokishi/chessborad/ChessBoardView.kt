package com.nyakokishi.chessborad

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout

/**
 ** Created by nyakokishi on 2017/8/12.
 */
class ChessBoardView : FrameLayout, View.OnTouchListener {

    companion object {
        const val VIEW_TAG_INDICATOR = "VIEW_TAG_INDICATOR"
        private val INITIAL_STATE = mapOf(
            Actor.BP to setOf("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"),
            Actor.BR to setOf("a8", "h8"),
            Actor.BN to setOf("b8", "g8"),
            Actor.BB to setOf("c8", "f8"),
            Actor.BQ to setOf("d8"),
            Actor.BK to setOf("e8"),

            Actor.WP to setOf("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"),
            Actor.WR to setOf("a1", "h1"),
            Actor.WN to setOf("b1", "g1"),
            Actor.WB to setOf("c1", "f1"),
            Actor.WQ to setOf("d1"),
            Actor.WK to setOf("e1")
        )
    }

    private var darkSquareColor: Int = Color.BLACK
    private var lightSquareColor: Int = Color.WHITE

    private var lastMovedChessmanPosition: String = ""
    private var nextChessmanPosition: String = ""

    private var isChessmanMoving = false

    private var squareWidth: Int = -1

    private lateinit var chessBoardDrawable: ChessBoardDrawable

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        val ta = context.obtainStyledAttributes(attrs, R.styleable.ChessBoardView)

        darkSquareColor = ta.getColor(R.styleable.ChessBoardView_dark_color, Color.BLACK)
        lightSquareColor = ta.getColor(R.styleable.ChessBoardView_light_color, Color.WHITE)
        chessBoardDrawable = ChessBoardDrawable(darkSquareColor, lightSquareColor)

        View(context).apply {
            background = ta.getDrawable(R.styleable.ChessBoardView_indicator)
                ?: ContextCompat.getDrawable(context, R.drawable.default_indicator)
            visibility = View.GONE
            tag = VIEW_TAG_INDICATOR
            addView(this)
        }

        ta.recycle()

        setOnTouchListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)

        background ?: run {
            chessBoardDrawable.setBounds(0, 0, width, width)
            background = chessBoardDrawable
        }

        if (squareWidth == -1) squareWidth = width / 8
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {

                if (!isChessmanMoving && lastMovedChessmanPosition.isNotEmpty()) {

                    val newX = 1 + (event.x / squareWidth).toInt()
                    val newY = 8 - (event.y / squareWidth).toInt()
                    nextChessmanPosition = ChessUtil.convertPositionToTag(newX, newY)
                }

                return true
            }

            MotionEvent.ACTION_UP -> {

                if (!isChessmanMoving && nextChessmanPosition.isNotEmpty())
                    moveChessman()

                return true
            }

            else -> return super.onTouchEvent(event)
        }
    }

    private fun moveChessman() {

        val chessmanView = findViewWithTag<ChessmanView>(lastMovedChessmanPosition) ?: return

        isChessmanMoving = true

        findViewWithTag<View>(VIEW_TAG_INDICATOR).visibility = View.GONE

        val nextX = 1f * squareWidth * (ChessUtil.getXPositionFromTag(nextChessmanPosition) - 1)
        val nextY = 1f * squareWidth * (8 - ChessUtil.getYPositionFromTag(nextChessmanPosition))

        val xAxisAnim =
            ObjectAnimator.ofFloat(chessmanView, "translationX", chessmanView.translationX, nextX)
        val yAxisAnim =
            ObjectAnimator.ofFloat(chessmanView, "translationY", chessmanView.translationY, nextY)

        AnimatorSet().apply {
            playTogether(xAxisAnim, yAxisAnim)
            duration = 200L
            interpolator = LinearInterpolator()
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationCancel(animation: Animator?) = Unit
                override fun onAnimationStart(animation: Animator?) = Unit
                override fun onAnimationRepeat(animation: Animator?) = Unit
                override fun onAnimationEnd(animation: Animator?) {
                    isChessmanMoving = false
                }
            })
            start()
        }

        lastMovedChessmanPosition = nextChessmanPosition
        nextChessmanPosition = ""
    }

    private fun addChessman(chessman: Chessman) {

        val chessmanView = ChessmanView(context, chessman)
        chessmanView.translationX = 1f * squareWidth * (chessman.xPosition() - 1)
        chessmanView.translationY = 1f * squareWidth * (8 - chessman.yPosition())

        addView(chessmanView, LayoutParams(squareWidth, squareWidth))

        chessmanView.setOnClickListener {
            if (isChessmanMoving) return@setOnClickListener

            lastMovedChessmanPosition = it.tag as String

            with(findViewWithTag<View>(VIEW_TAG_INDICATOR)) {
                this.layoutParams = it.layoutParams as LayoutParams
                this.translationX = it.translationX
                this.translationY = it.translationY
                this.visibility = View.VISIBLE
            }
        }
    }

    fun init() {
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                INITIAL_STATE.forEach { (actor, set) ->
                    set.forEach { pos ->
                        addChessman(Chessman(pos, actor))
                    }
                }
            }
        })
    }

}