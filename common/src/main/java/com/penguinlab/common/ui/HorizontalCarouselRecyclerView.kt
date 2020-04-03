package com.penguinlab.common.ui

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.penguinlab.common.R
import kotlin.math.pow

class HorizontalCarouselRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    private val activeColor by lazy {
        ContextCompat.getColor(
            context,
            R.color.design_default_color_error
        )
    }
    private val inactiveColor by lazy {
        ContextCompat.getColor(
            context,
            R.color.design_default_color_primary_dark
        )
    }
    var viewsToChangeColor: List<Int> = listOf()

    fun <T : ViewHolder> initialize(newAdapter: Adapter<T>): RecyclerView {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        adapter = newAdapter
        return this
    }


    fun onScrollChanged() {
        (0 until childCount).forEach { position ->
            val child = getChildAt(position)
            val childCenterX = (child.left + child.right) / 2
            val scaleValue = getGaussianScale(childCenterX, 0.5F, 0.5F, 200.toDouble())
            child.scaleX = scaleValue
            child.scaleY = scaleValue
            colorView(child, scaleValue)
        }

    }

    private fun colorView(child: View, scaleValue: Float) {
        val saturationPercent = (scaleValue - 1) / 1f
        val alphaPercent = scaleValue / 2f
        val matrix = ColorMatrix()
        matrix.setSaturation(saturationPercent)

        viewsToChangeColor.forEach { viewId ->
            when (val viewToChangeColor = child.findViewById<View>(viewId)) {
                is ImageView -> {
                    viewToChangeColor.colorFilter = ColorMatrixColorFilter(matrix)
                    viewToChangeColor.imageAlpha = (255 * alphaPercent).toInt()
                }
                is TextView -> {
                    val textColor = ArgbEvaluator().evaluate(
                        saturationPercent,
                        inactiveColor,
                        activeColor
                    ) as Int
                    viewToChangeColor.setTextColor(textColor)
                }
            }
        }
    }

    private fun getGaussianScale(
        childCenterX: Int,
        minScaleOffest: Float,
        scaleFactor: Float,
        spreadFactor: Double
    ): Float {
        val recyclerCenterX = (left + right) / 2
        return (Math.E.pow(
            -(childCenterX - recyclerCenterX.toDouble()).pow(2.toDouble()) / (2 * spreadFactor.pow(2.toDouble()))
        ) * scaleFactor + minScaleOffest).toFloat()
    }

}