package com.nyakokishi.chessborad

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * Created by nyakokishi on 2017/8/12.
 */
class ChessBoardDrawable(positiveColor: Int, negativeColor: Int) : Drawable() {

    val positivePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val negativePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        positivePaint.color = positiveColor
        negativePaint.color = negativeColor
    }

    override fun draw(canvas: Canvas?) {

        if (bounds.width() <= 0 || bounds.height() <= 0)
            return

        val bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.RGB_565)
        val c = Canvas(bitmap)
        var i = 0
        val cellWidth = bitmap.width / 8

        while (i < 64) {
            val x = (i % 8) * cellWidth
            val y = (i / 8) * cellWidth
            c.drawRect(Rect(x, y, x + cellWidth, y + cellWidth),
                    if (i % 8 % 2 == 0 && i / 8 % 2 != 0
                            || i % 8 % 2 != 0 && i / 8 % 2 == 0) positivePaint else negativePaint)
            i++
        }
        canvas?.drawBitmap(bitmap, 0f, 0f, Paint())
    }

    override fun setAlpha(alpha: Int) {
        bitmapPaint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        bitmapPaint.colorFilter = colorFilter
    }

}