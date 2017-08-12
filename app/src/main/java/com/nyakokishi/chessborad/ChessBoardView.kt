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

    var mChessman: ChessView? = null
    var cellWidth: Int? = null

    var negativeColor: Int = Color.BLACK
    var positiveColor: Int = Color.WHITE
    var selector: View? = null
    var isEnable = true

    var chessBoardDrawable: ChessBoardDrawable? = null

    constructor(context: Context) : super(context) {
        ChessBoardView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ChessBoardView)
        negativeColor = ta.getColor(R.styleable.ChessBoardView_negativeColor, Color.BLACK)
        positiveColor = ta.getColor(R.styleable.ChessBoardView_positiveColor, Color.WHITE)
        chessBoardDrawable = ChessBoardDrawable(negativeColor, positiveColor)
        ta.recycle()

        setOnTouchListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        chessBoardDrawable?.setBounds(0, 0, width, width)
        cellWidth = width / 8
        setBackgroundDrawable(chessBoardDrawable)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (isEnable && mChessman != null) {
                    val newX = 1 + (event.x / cellWidth!!).toInt()
                    val newY = 8 - (event.y / cellWidth!!).toInt()
                    mChessman?.x = newX
                    mChessman?.y = newY
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (isEnable && mChessman != null)
                    moveChessman()
                return true
            }
            else -> return super.onTouchEvent(event)
        }
    }


    fun moveChessman() {
        isEnable = false
        selector?.visibility = View.GONE

        val chessmanLayoutParams = mChessman?.layoutParams as FrameLayout.LayoutParams

        val xAnim = ValueAnimator.ofInt(chessmanLayoutParams.leftMargin, cellWidth!! * (mChessman?.x!! - 1))
        val yAnim = ValueAnimator.ofInt(chessmanLayoutParams.topMargin, cellWidth!! * (8 - mChessman?.y!!))
        xAnim.addUpdateListener {
            animation ->
            val layoutParams = mChessman?.layoutParams as FrameLayout.LayoutParams
            layoutParams.leftMargin = animation?.animatedValue as Int
            mChessman?.layoutParams = layoutParams
        }
        yAnim.addUpdateListener {
            animation ->
            val layoutParams = mChessman?.layoutParams as FrameLayout.LayoutParams
            layoutParams.topMargin = animation?.animatedValue as Int
            mChessman?.layoutParams = layoutParams
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
                mChessman = null
                isEnable = true
            }
        })
        animSet.start()
    }

    fun addChessman(chessView: ChessView) {
        cellWidth ?: run {
            Handler().postDelayed({ addChessman(chessView) }, 100)
            return
        }
        val chessmanLayoutParams = FrameLayout.LayoutParams(cellWidth!!, cellWidth!!)
        chessmanLayoutParams.leftMargin = cellWidth!! * (chessView.x - 1)
        chessmanLayoutParams.topMargin = cellWidth!! * (8 - chessView.y)
        addView(chessView, chessmanLayoutParams)
        chessView.setOnClickListener {
            if (!isEnable) return@setOnClickListener
            mChessman = chessView

            if (selector == null) {
                selector = View(context).apply {
                    setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.frame))
                }
                addView(selector, chessmanLayoutParams)
            } else {
                selector?.visibility = View.VISIBLE
                selector?.layoutParams = chessmanLayoutParams
            }

        }
    }

}