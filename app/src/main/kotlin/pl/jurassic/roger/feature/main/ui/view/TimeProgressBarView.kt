package pl.jurassic.roger.feature.main.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import pl.jurassic.roger.R
import pl.jurassic.roger.getColor

class TimeProgressBarView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr)  {

    companion object {
        private const val RING_STROKE_WITH = 2f
        private const val INNER_CIRCLE_SHADOW_RADIUS = 10f
    }

    private val outerCircleRectF = RectF()
    private val innerCircleRectF = RectF()

    private val innerCircleWhitePaint = Paint()
    private val innerCircleShadowPaint = Paint()
    private val ringBluePaint = Paint()
    private val ringStrokePaint = Paint()

    init {
        initPaints()
    }

    private fun initPaints() {
        innerCircleWhitePaint.color = getColor(R.color.pale_grey)
        innerCircleWhitePaint.style = Paint.Style.FILL
        innerCircleWhitePaint.isAntiAlias = true

        innerCircleShadowPaint.color = getColor(R.color.white)
        innerCircleShadowPaint.setShadowLayer(INNER_CIRCLE_SHADOW_RADIUS, 0f, 0f, getColor(R.color.black16))
        innerCircleShadowPaint.isAntiAlias = true

        ringBluePaint.color = getColor(R.color.lightish_blue)
        ringBluePaint.isAntiAlias = true

        ringStrokePaint.color = getColor(R.color.lightish_blue)
        ringStrokePaint.style = Paint.Style.STROKE
        ringStrokePaint.strokeWidth = RING_STROKE_WITH
        ringStrokePaint.isAntiAlias = true

        setLayerType(LAYER_TYPE_SOFTWARE, innerCircleShadowPaint)
    }



    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        //TODO clear that code
        super.onLayout(changed, left, top, right, bottom)
        val someValue = (right - left)/6
        outerCircleRectF.set(0f, 0f, (right - left).toFloat(), (bottom - top).toFloat())
        innerCircleRectF.set(0f + someValue, 0f + someValue, (right - left - someValue).toFloat(), (bottom - top - someValue).toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawOval(outerCircleRectF,  innerCircleWhitePaint)
        canvas.drawOval(outerCircleRectF,  ringStrokePaint)

        for(i in 0..8) {
            val tmp = 45f * i
            canvas.drawArc(outerCircleRectF, tmp, 0.5f, true, ringBluePaint)
        }
        canvas.drawOval(innerCircleRectF, innerCircleShadowPaint)


        super.onDraw(canvas)
    }
}