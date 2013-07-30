/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2013. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.basescreens;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import de.telekom.pde.codelibrary.ui.color.PDEColor;


/**
 * @brief Private helper class for grid drawable in the background.
 */
public class SimpleTriangleDrawable extends Drawable {
    private PDEColor mColor;
    private Paint mPaint;
    private int mAlpha;
    private Path mPath;
    private float mRotation;


    /**
     * @brief Constructor.
     */
    public SimpleTriangleDrawable(PDEColor color) {
        this(color,0);
    }


    /**
     * @brief Constructor.
     */
    public SimpleTriangleDrawable(PDEColor color, float rotation) {
        mColor = color;
        mPaint = new Paint();
        mPaint.setColor(mColor.getIntegerColor());
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mAlpha = 255;
        mRotation = rotation;
    }

    /**
     * @brief Calculate the correct aspect ratio bounds.
     */
    private void calcPath(Rect bounds) {
        Matrix mMatrix = new Matrix();
        mPath = new Path();

        mPath.moveTo(bounds.left, bounds.top);
        mPath.lineTo(bounds.right, bounds.top);
        mPath.lineTo(bounds.width()/2,bounds.bottom);
        mPath.lineTo(bounds.left, bounds.top);

        RectF oldBounds = new RectF();
        RectF newBounds = new RectF();
        mPath.computeBounds(oldBounds, true);
        mMatrix.postRotate(mRotation, (bounds.right + bounds.left) / 2, (bounds.bottom + bounds.top) / 2);
        mPath.transform(mMatrix);
        mPath.computeBounds(newBounds, true);
        mMatrix.setRectToRect(newBounds,oldBounds, Matrix.ScaleToFit.FILL);
        mPath.transform(mMatrix);
        mPath.close();
    }

    /**
     * @brief Called when drawable should be drawn.
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        calcPath(bounds);
        // inform listener/parent about changes
        invalidateSelf();
    }

    
    /**
     * @brief Sets the alpha of the drawable (not used).
     */
    @Override
    public void setAlpha(int alpha) {
        if(mAlpha!=alpha) {
            mAlpha=alpha;
            // change paint alpha value
            mPaint.setAlpha((int)((mColor.getAlpha()*255)*((float)mAlpha/255)));
            // inform listener/parent about changes
            invalidateSelf();
        }
    }


    /**
     * @brief Sets the color filter of the drawable (not used).
     */
    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // nothing to do
    }


    /**
     * @brief Gets the opacity of the drawable (default is 0).
     */
    @Override
    public int getOpacity() {
        return 0;
    }
}