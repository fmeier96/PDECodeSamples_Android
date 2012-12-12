/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created with IntelliJ IDEA.
 * User: akitzinger
 * Date: 30.10.12
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */

public class BlurTestSimpleRectView extends View {
    float mBlurRadius;
    RectF mBoundingRect;
    float mCornerRadius;
    int mAlpha;
    String mBlurType;
    int mColor;
    boolean mEmbossed = false;
    float[] mDirection;
    float mAmbient;
    float mSpecular;


        public BlurTestSimpleRectView(Context context){
            super(context);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
        }

        public BlurTestSimpleRectView(Context context, AttributeSet attrs){
            super(context,attrs);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
        }

        public BlurTestSimpleRectView (Context context, AttributeSet attrs, int defStyle){
            super(context,attrs,defStyle);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
        }

//        public PDEDrawableShapedInnerShadow getDrawable(){
//            return mDrawable;
//        }
//
//        public void setDrawable(PDEDrawableShapedInnerShadow drawable){
//            if (drawable != null){
//                mDrawable = drawable;
//                setBackgroundDrawable(mDrawable);
//            }
//        }
//
//        public void setShapeOpacity (float opacity){
//            mDrawable.setShapeOpacity(opacity);
//        }
//
//        public float getShapeOpacity(){
//            return mDrawable.getShapeOpacity();
//        }
//
//
//        public void setShapeColor (PDEColor color){
//            mDrawable.setShapeColor(color);
//        }
//
//        public PDEColor getShapeColor(){
//            return mDrawable.getShapeColor();
//        }
//
//        public void setShapeOffset (PointF offset){
//            PDEAbsoluteLayout.LayoutParams lp = (PDEAbsoluteLayout.LayoutParams) getLayoutParams();
//            if(lp!= null){
//                lp.x = (int)offset.x;
//                lp.y = (int)offset.y;
//                setLayoutParams(lp);
//            }
//        }
//
//        public PointF getShapeOffset(){
//            PDEAbsoluteLayout.LayoutParams lp = (PDEAbsoluteLayout.LayoutParams) getLayoutParams();
//            return new PointF(lp.x,lp.y);
//        }


//        public void setBlurRadius (float radius){
//            mBlurRadius= radius;
//        }
//
//        public float getBlurRadius(){
//            return mBlurRadius;
//        }

//        public void setShapePath (Path path){
//            mDrawable.setShapePath(path);
//        }
//
//        public Path getShapePath(){
//            return mDrawable.getShapePath();
//        }
//
//        public void setShapeRect(RectF rect){
//            mDrawable.setShapeRect(rect);
//        }

        public void setShapeRoundedRect(RectF rect, float cornerRadius){
//            mDrawable.setShapeRoundedRect(rect,cornerRadius);
            mBoundingRect = rect;
            mCornerRadius = cornerRadius;
        }

//        public void setShapeOval(RectF rect){
//            setShapeOval(rect);
//        }

        public void setAlpha(int alpha) {
            mAlpha = alpha;
        }

    public void setColor(int color){
        mColor = color;
    }

    public void setBlurFilter(float radius,String type){
        mBlurRadius = radius;
        mBlurType = type;
        mEmbossed = false;
    }

    public void setEmbossFilter(float[] direction,float ambient,float specular,float radius){
        mDirection = direction;
        mBlurRadius = radius;
        mAmbient = ambient;
        mSpecular = specular;
        mEmbossed = true;
    }

    @Override
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension((int)(mBoundingRect.width()+4*mBlurRadius), (int)(mBoundingRect.height()+4*mBlurRadius));
    }

    @Override
     public void onDraw(Canvas canvas){
        BlurMaskFilter blur;
        Paint mPaint = new Paint();
        mPaint.setAlpha(mAlpha);

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
        if(!mEmbossed){
            if(mBlurType == "inner"){
                blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.INNER);
            } else if(mBlurType == "normal"){
                blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.NORMAL);
            } else if(mBlurType == "outer"){
                blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.OUTER);
            } else {
                blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.SOLID);
            }
            mPaint.setMaskFilter(blur);
        } else {
            EmbossMaskFilter emb = new EmbossMaskFilter(mDirection,mAmbient,mSpecular,mBlurRadius);
            mPaint.setMaskFilter(emb);
        }

        RectF test = new RectF(mBoundingRect.left+2*mBlurRadius,mBoundingRect.top+2*mBlurRadius,
                               mBoundingRect.right+2*mBlurRadius,mBoundingRect.bottom+2*mBlurRadius);

        canvas.drawRoundRect(test, mCornerRadius, mCornerRadius, mPaint);
     }

}
