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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created with IntelliJ IDEA.
 * User: akitzinger
 * Date: 31.10.12
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class BlurTestShapeView extends View {

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
//    GradientDrawable mDrawable;
//    Rect mRect;


    public BlurTestShapeView(Context context){
        super(context);
//        mDrawable = new GradientDrawable(
//                GradientDrawable.Orientation.TL_BR, new int[] { 0xFFFF0000,
//                                                                0xFF00FF00, 0xFF0000FF });
//        mDrawable.setShape(GradientDrawable.RECTANGLE);
//        mDrawable.setGradientRadius((float) (Math.sqrt(2) * 60));
//        setBackgroundDrawable(mDrawable);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
    }

    public BlurTestShapeView(Context context, AttributeSet attrs){
        super(context,attrs);
//            mDrawable = new PDEDrawableShapedInnerShadow();
//            setBackgroundDrawable(mDrawable);
    }

    public BlurTestShapeView (Context context, AttributeSet attrs, int defStyle){
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



    public void setType(String type){
        mType = type;
    }
//
//    @Override
//    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
//        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
//        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
//        this.setMeasuredDimension((int)(mBoundingRect.width()+4*mBlurRadius), (int)(mBoundingRect.height()+4*mBlurRadius));
//    }


    @Override
    public void onDraw(Canvas canvas){
        Bitmap bitmap;
        RectF smallRect;
        float l,t,r,b;
        Paint mPaint = new Paint();
        Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        Path mPath = new Path();
        mPaint.setAlpha(mAlpha);
        int alpha;

        mPaint.setAntiAlias(true);

        // security
        if (mBoundingRect.width() <= 0 || mBoundingRect.height() <= 0) {
            return;
        }
        l = mBoundingRect.left+mBoundingRect.width()/6;
        t = mBoundingRect.top+mBoundingRect.height()/6;
        r = mBoundingRect.right-mBoundingRect.width()/6;
        b = mBoundingRect.bottom-mBoundingRect.height()/6;
        smallRect = new RectF(l,t,r,b);

        // first create a bitmap with the size, shape and the color of the shadow
        bitmap = Bitmap.createBitmap((int) mBoundingRect.width(), (int) mBoundingRect.height(),
                                     Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        mPaint.setAntiAlias(true);
       /* mPaint.setStyle(Paint.Style.STROKE);
        int numberOfPasses = (int)mBlurRadius;
        for(int i= 0; i<=numberOfPasses; i++){
            mPath.reset();
            mPath.addRoundRect(new RectF(mBoundingRect.left+i,mBoundingRect.top+i,mBoundingRect.right-i,
                                         mBoundingRect.bottom-i) , 30.0f-i,
                               30.0f-i,
                               Path.Direction.CW);
            mPath.close();
            float test= (float)(((float)numberOfPasses-(float)i)/(float)numberOfPasses);
            alpha=(int)  ( test*255.0f);
            mPaint.setARGB(alpha,0,0,0);
            c.drawPath(mPath,mPaint);
            if(i>numberOfPasses/2) break;
        } */


        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

        mPath.reset();
        mPath.addRoundRect(mBoundingRect, 30.0f, 30.0f, Path.Direction.CW);
        mPath.addRoundRect(smallRect,30.0f,30.0f,Path.Direction.CW);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        mPath.close();

//        mPaint.setShader(new LinearGradient( (mBoundingRect.right-mBoundingRect.left)/2, mBoundingRect.top,
//                                             (mBoundingRect.right-mBoundingRect.left)/2, mBoundingRect.bottom,
//                                             Color.BLACK,
//                                             Color.TRANSPARENT, Shader.TileMode.MIRROR));
        //mPaint.setShadowLayer(mBlurRadius,0.0f,0.0f,Color.BLACK);
        c.drawPath(mPath,mPaint);


//        if(mType==TYPE_WHITE_FRAME_BLACK_CENTER){
//            mPaint.setColor(Color.WHITE);
//            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
//            mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            mPaint2.setColor(Color.BLACK);
//            mPaint2.setMaskFilter(getBlurMaskFilter());
//            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint2);
////            mPaint.setColor(Color.BLACK);
////            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
////            mPaint.setColor(Color.WHITE);
////            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint);
//        } else if(mType==TYPE_TRANSPARENT_FRAME_BLACK_CENTER){
//            mPaint.setColor(Color.TRANSPARENT);
//            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
//            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//            mPaint.setColor(Color.BLACK);
//            mPaint.setMaskFilter(getBlurMaskFilter());
//            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint);
//        } else if(mType==TYPE_BLACK_FRAME_TRANSPARENT_CENTER){
//            mPaint.setColor(Color.argb(255,0,0,0));
//            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
//            mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
//            mPaint2.setColor(Color.TRANSPARENT);
//            mPaint2.setMaskFilter(getBlurMaskFilter());
//            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint2);
//            if(mCutInnerRoundedRect){
//                mPaint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
//                mPaint3.setColor(Color.TRANSPARENT);
//                c.drawRoundRect(new RectF(smallRect.left+mBlurRadius-5,smallRect.top+mBlurRadius-5,
//                                          smallRect.right-mBlurRadius+5,smallRect.bottom-mBlurRadius+5),mCornerRadius-15,
//                                mCornerRadius-15,mPaint3);
//            }
//        }  else if(mType==TYPE_BLACK_FRAME_WHITE_CENTER){
//            mPaint.setColor(Color.BLACK);
//            c.drawRoundRect(mBoundingRect, mCornerRadius, mCornerRadius, mPaint);
//            mPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            mPaint2.setColor(Color.WHITE);
//            mPaint2.setMaskFilter(getBlurMaskFilter());
//            c.drawRoundRect(smallRect,mCornerRadius,mCornerRadius,mPaint2);
//        }

        canvas.drawBitmap(bitmap,0,0,mPaint);



    }


}
