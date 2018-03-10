package com.nyakokishi.chessborad

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 ** Created by nyakokishi on 2017/8/12.
 */
class ChessBoardDrawable(lightColor: Int, darkColor: Int) : Drawable() {

    private val lightPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val darkPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        lightPaint.color = lightColor
        darkPaint.color = darkColor
    }

    override fun draw(canvas: Canvas?) {

        if (bounds.width() <= 0 || bounds.height() <= 0)
            return

        val bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.RGB_565)
        val bitmapCanvas = Canvas(bitmap)
        val squareWidth = bitmap.width / 8

        for (row in 0..7) {

            val y = row * squareWidth

            for (column in 0..7) {

                val x = column * squareWidth
                val rect = Rect(x, y, x + squareWidth, y + squareWidth)
                val paint =
                        if (isLightSquare(row, column))
                            lightPaint
                        else
                            darkPaint

                bitmapCanvas.drawRect(rect, paint)
            }
        }

        canvas?.drawBitmap(bitmap, 0f, 0f, Paint())
    }

    private fun isLightSquare(row: Int, column: Int) =
            column % 2 == 0 && row % 2 != 0 || column % 2 != 0 && row % 2 == 0


    override fun setAlpha(alpha: Int) {
        bitmapPaint.alpha = alpha
    }

    override fun getOpacity() = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        bitmapPaint.colorFilter = colorFilter
    }

}