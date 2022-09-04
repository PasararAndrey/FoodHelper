package com.example.foodhelper.ui.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import com.example.foodhelper.R


class DividerTextView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : androidx.appcompat.widget.AppCompatTextView(context, attr, defStyleAttr) {

    private val mPaint = Paint().apply {
        strokeWidth = 4F
        color = context.getColor(R.color.vulcan)
    }

    private val mBounds = Rect()

    private val mPadding = 16

    init {
        if (!isInEditMode) {
            gravity = Gravity.CENTER
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.getTextBounds(text.toString(), 0, text.length, mBounds)
        canvas.drawLine(0F, (height / 2).toFloat(), (((width - mBounds.right) / 2) - mPadding).toFloat(), (height / 2).toFloat(), mPaint)
        canvas.drawLine((((width + mBounds.right) / 2) + mPadding).toFloat(), (height / 2).toFloat(), width.toFloat(), (height / 2).toFloat(), mPaint)
    }


}