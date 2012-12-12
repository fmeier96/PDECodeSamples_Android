/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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

public class BlurTestDualRectView extends View {

    public static final String TYPE_TRANSPARENT_FRAME_BLACK_CENTER = "transparent_black";
    public static final String TYPE_WHITE_FRAME_BLACK_CENTER = "white_black";
    public static final String TYPE_BLACK_FRAME_TRANSPARENT_CENTER = "black_transparent";
    public static final String TYPE_BLACK_FRAME_WHITE_CENTER = "black_white";

    float mBlurRadius;
    RectF mBoundingRect;
    float mCornerRadius;
    int mAlpha = 255;
    String mBlurType;
    int mColor;
    boolean mEmbossed = false;
    float[] mDirection;
    float mAmbient;
    float mSpecular;
    String mType = TYPE_WHITE_FRAME_BLACK_CENTER;
    public boolean mCutInnerRoundedRect = false;
    public boolean mMasking = false;
    public boolean mMasking2 = false;


    public BlurTestDualRectView(Context context){
        super(context);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
    }

    public BlurTestDualRectView(Context context, AttributeSet attrs){
        super(context,attrs);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
    }

    public BlurTestDualRectView (Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
    }



    public void setShapeRoundedRect(RectF rect, float cornerRadius){
//            mDrawable.setShapeRoundedRect(rect,cornerRadius);
        mBoundingRect = rect;
        mCornerRadius = cornerRadius;
    }



    public void setAlpha(int alpha) {
        mAlpha = alpha;
    }

//    public void setColor(int color){
//        mColor = color;
//    }

    public void setBlurFilter(float radius,String type){
        mBlurRadius = radius;
        mBlurType = type;
        mEmbossed = false;
    }

    /*
    public void setEmbossFilter(float[] direction,float ambient,float specular,float radius){
        mDirection = direction;
        mBlurRadius = radius;
        mAmbient = ambient;
        mSpecular = specular;
        mEmbossed = true;
    } */

    public void setType(String type){
        mType = type;
    }
  /*
    @Override
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension((int)(mBoundingRect.width()+4*mBlurRadius), (int)(mBoundingRect.height()+4*mBlurRadius));
    }
    */

    @Override
    public void onDraw(Canvas canvas){
        Bitmap bitmap;
        RectF smallRect;
        RectF maskRect;
        float l,t,r,b;
        Paint mPaint = new Paint();
        Paint mPaint2 = new Paint();
        Paint mPaint3 = new Paint();
        Paint mPaint4 = new Paint();
        Path mPath = new Path();
        mPaint.setAlpha(mAlpha);

        mPaint.setAntiAlias(true);
        mPaint2.setAntiAlias(false);
        mPaint3.setAntiAlias(false);
        mPaint4.setAntiAlias(false);

        // security
        if (mBoundingRect.width() <= 0 || mBoundingRect.height() <= 0) {
            return;
        }
        l = mBoundingRect.left+mBoundingRect.width()/6;
        t = mBoundingRect.top+mBoundingRect.height()/6;
        r = mBoundingRect.right-mBoundingRect.width()/6;
        b = mBoundingRect.bottom-mBoundingRect.height()/6;
        smallRect = new RectF(l,t,r,b);

        l = mBoundingRect.left+mBoundingRect.width()/8;
        t = mBoundingRect.top+mBoundingRect.height()/8;
        r = mBoundingRect.right-mBoundingRect.width()/8;
        b = mBoundingRect.bottom-mBoundingRect.height()/8;
        maskRect = new RectF(l,t,r,b);




        // first create a bitmap with the size, shape and the color of the shadow
        bitmap = Bitmap.createBitmap((int) mBoundingRect.width(), (int) mBoundingRect.height(),
                                         Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        if(mType==TYPE_WHITE_FRAME_BLACK_CENTER){
            mPaint.setColor(Color.WHITE);
            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
            mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            mPaint2.setColor(Color.BLACK);
            mPaint2.setMaskFilter(getBlurMaskFilter());
            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint2);
//            mPaint.setColor(Color.BLACK);
//            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
//            mPaint.setColor(Color.WHITE);
//            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint);
        } else if(mType==TYPE_TRANSPARENT_FRAME_BLACK_CENTER){
            mPaint.setColor(Color.TRANSPARENT);
            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            mPaint.setColor(Color.BLACK);
            mPaint.setMaskFilter(getBlurMaskFilter());
            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint);
        } else if(mType==TYPE_BLACK_FRAME_TRANSPARENT_CENTER){


            mPaint.setColor(Color.argb(255,0,0,0));
            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
            mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            mPaint2.setColor(Color.TRANSPARENT);
            mPaint2.setMaskFilter(getBlurMaskFilter());
            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint2);
            if(mCutInnerRoundedRect){
                mPaint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
                mPaint3.setColor(Color.TRANSPARENT);
                c.drawRoundRect(new RectF(smallRect.left+mBlurRadius-5,smallRect.top+mBlurRadius-5,
                                      smallRect.right-mBlurRadius+5,smallRect.bottom-mBlurRadius+5),mCornerRadius-15,
                                      mCornerRadius-15,mPaint3);
            }

            if(mMasking){
                mPaint4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
                mPaint4.setColor(Color.TRANSPARENT);
                mPaint4.setStyle(Paint.Style.FILL);

                mPath.reset();
                mPath.addRoundRect(mBoundingRect, mCornerRadius-5.0f, mCornerRadius-5.0f,
                                   Path.Direction.CW);
                mPath.addRoundRect(maskRect,mCornerRadius,mCornerRadius,Path.Direction.CW);
                mPath.setFillType(Path.FillType.EVEN_ODD);
                mPath.close();
                c.drawPath(mPath,mPaint4);
//                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            }

            if(mMasking2){
                mPaint4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
                mPaint4.setColor(Color.TRANSPARENT);
                mPaint4.setStyle(Paint.Style.FILL);

                mPath.reset();
                mPath.addRoundRect(mBoundingRect, mCornerRadius-5.0f, mCornerRadius-5.0f,
                                   Path.Direction.CW);
                mPath.addRoundRect(smallRect,mCornerRadius,mCornerRadius,Path.Direction.CW);
                mPath.setFillType(Path.FillType.EVEN_ODD);
                mPath.close();
                c.drawPath(mPath,mPaint4);
//                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            }


        }  else if(mType==TYPE_BLACK_FRAME_WHITE_CENTER){
            mPaint.setColor(Color.BLACK);
            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
            mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            mPaint2.setColor(Color.WHITE);
            mPaint2.setMaskFilter(getBlurMaskFilter());
            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint2);
        }

        canvas.drawBitmap(bitmap,0,0,mPaint);



    }

    private BlurMaskFilter getBlurMaskFilter(){
        BlurMaskFilter blur;

        if(mBlurType == "inner"){
            blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.INNER);
        } else if(mBlurType == "normal"){
            blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.NORMAL);
        } else if(mBlurType == "outer"){
            blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.OUTER);
        } else {
            blur = new BlurMaskFilter(mBlurRadius,BlurMaskFilter.Blur.SOLID);
        }
        return blur;
    }

}
