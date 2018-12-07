package com.rubenexposito.contactsmarvelapp.common

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.IntRange
import androidx.annotation.NonNull

class AvatarPlaceholder @JvmOverloads constructor(
    name: String?,
    @param:IntRange private var textSizePercentage: Int = DEFAULT_TEXT_SIZE_PERCENTAGE,
    @NonNull defaultString: String = DEFAULT_PLACEHOLDER_STRING
) :
    Drawable() {

    private val textPaint: Paint
    private val backgroundPaint: Paint
    private var placeholderBounds: RectF? = null

    private val avatarText: String
    private val defaultString: String

    private var textStartXPoint: Float = 0.toFloat()
    private var textStartYPoint: Float = 0.toFloat()

    init {
        this.defaultString = resolveStringWhenNoName(defaultString)
        this.avatarText = convertNameToAvatarText(name)

        textPaint = Paint()
        textPaint.isAntiAlias = true
        textPaint.color = Color.parseColor("white")
        textPaint.typeface = Typeface.create("sans-serif-light", Typeface.NORMAL)

        backgroundPaint = Paint()
        backgroundPaint.isAntiAlias = true
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.color = Color.parseColor(convertStringToColor(name))
    }

    override fun draw(@NonNull canvas: Canvas) {
        if (placeholderBounds == null) {
            placeholderBounds = RectF(0f, 0f, bounds.width().toFloat(), bounds.height().toFloat())
            setAvatarTextValues()
        }
        val size = Math.min(bounds.width().toFloat(), bounds.height().toFloat())
        val r = size / 2f
        canvas.drawCircle(r, r, r, backgroundPaint)
        canvas.drawText(avatarText, textStartXPoint, textStartYPoint, textPaint)
    }

    override fun setAlpha(alpha: Int) {
        textPaint.alpha = alpha
        backgroundPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        textPaint.colorFilter = colorFilter
        backgroundPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    private fun setAvatarTextValues() {
        textPaint.textSize = calculateTextSize()
        textStartXPoint = calculateTextStartXPoint()
        textStartYPoint = calculateTextStartYPoint()
    }

    private fun calculateTextStartXPoint(): Float {
        val stringWidth = textPaint.measureText(avatarText)
        return bounds.width() / 2f - stringWidth / 2f
    }

    private fun calculateTextStartYPoint(): Float {
        return bounds.height() / 2f - (textPaint.ascent() + textPaint.descent()) / 2f
    }

    private fun resolveStringWhenNoName(stringWhenNoName: String?): String {
        return if (!stringWhenNoName.isNullOrEmpty()) stringWhenNoName else DEFAULT_PLACEHOLDER_STRING
    }

    private fun convertNameToAvatarText(name: String?): String {
        return if (!name.isNullOrEmpty()) name.substring(0, 1).toUpperCase() else defaultString
    }

    private fun convertStringToColor(text: String?): String {
        return if (text.isNullOrEmpty())
            DEFAULT_PLACEHOLDER_COLOR
        else
            String.format(COLOR_FORMAT, 0xFFFFFF and text.hashCode())
    }

    private fun calculateTextSize(): Float {
        if (textSizePercentage < 0 || textSizePercentage > 100) {
            textSizePercentage =
                    DEFAULT_TEXT_SIZE_PERCENTAGE
        }
        return bounds.height() * textSizePercentage.toFloat() / 100
    }

    companion object {
        const val DEFAULT_PLACEHOLDER_STRING = "-"
        private const val DEFAULT_PLACEHOLDER_COLOR = "#45b1fe"
        private const val COLOR_FORMAT = "#BF%06X"
        const val DEFAULT_TEXT_SIZE_PERCENTAGE = 33
    }
}