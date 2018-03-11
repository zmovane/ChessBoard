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
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout

/**
 ** Created by nyakokishi on 2017/8/12.
 */
class ChessBoardView : FrameLayout, View.OnTouchListener {

    companion object {
        const val VIEW_TAG_INDICATOR = "VIEW_TAG_INDICATOR"
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

        val xAxisAnim = ObjectAnimator.ofFloat(chessmanView, "translationX", chessmanView.translationX, nextX)
        val yAxisAnim = ObjectAnimator.ofFloat(chessmanView, "translationY", chessmanView.translationY, nextY)

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

        ChessmanView(context, chessman).apply {

            translationX = 1f * squareWidth * (chessman.xPosition() - 1)
            translationY = 1f * squareWidth * (8 - chessman.yPosition())

            addView(this, FrameLayout.LayoutParams(squareWidth, squareWidth))

            setOnClickListener {

                if (isChessmanMoving) return@setOnClickListener

                lastMovedChessmanPosition = tag as String

                with(findViewWithTag<View>(VIEW_TAG_INDICATOR)){
                    layoutParams = layoutParams as FrameLayout.LayoutParams
                    translationX = translationX
                    translationY = translationY
                    visibility = View.VISIBLE
                }

            }
        }
    }

    fun init() {

        addChessman(Chessman('a', 7, Actor.BP))
        addChessman(Chessman('b', 7, Actor.BP))
        addChessman(Chessman('c', 7, Actor.BP))
        addChessman(Chessman('d', 7, Actor.BP))
        addChessman(Chessman('e', 7, Actor.BP))
        addChessman(Chessman('f', 7, Actor.BP))
        addChessman(Chessman('g', 7, Actor.BP))
        addChessman(Chessman('h', 7, Actor.BP))

        addChessman(Chessman('a', 8, Actor.BR))
        addChessman(Chessman('h', 8, Actor.BR))
        addChessman(Chessman('b', 8, Actor.BN))
        addChessman(Chessman('g', 8, Actor.BN))
        addChessman(Chessman('c', 8, Actor.BB))
        addChessman(Chessman('f', 8, Actor.BB))
        addChessman(Chessman('d', 8, Actor.BQ))
        addChessman(Chessman('e', 8, Actor.BK))

        addChessman(Chessman('a', 2, Actor.WP))
        addChessman(Chessman('b', 2, Actor.WP))
        addChessman(Chessman('c', 2, Actor.WP))
        addChessman(Chessman('d', 2, Actor.WP))
        addChessman(Chessman('e', 2, Actor.WP))
        addChessman(Chessman('f', 2, Actor.WP))
        addChessman(Chessman('g', 2, Actor.WP))
        addChessman(Chessman('h', 2, Actor.WP))

        addChessman(Chessman('a', 1, Actor.WR))
        addChessman(Chessman('h', 1, Actor.WR))
        addChessman(Chessman('b', 1, Actor.WN))
        addChessman(Chessman('g', 1, Actor.WN))
        addChessman(Chessman('c', 1, Actor.WB))
        addChessman(Chessman('f', 1, Actor.WB))
        addChessman(Chessman('d', 1, Actor.WQ))
        addChessman(Chessman('e', 1, Actor.WK))
    }

}