/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


//imports
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.helpers.PDEFontHelpers;
import de.telekom.pde.codelibrary.ui.helpers.PDETypeface;


/**
 * Created with IntelliJ IDEA.
 * User: kdanner
 * Date: 22.10.12
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class FontTestView extends RelativeLayout {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = FontTestView.class.getName();
    final static boolean DEBUGPARAMS = false;

    private TextView mTextView;
    //String text = "Top-Üger";
    String text = "Button";
    //String text = "D";
    PDETypeface defaultFont = PDETypeface.sDefaultFont;
    //Typeface defaultFont = Typeface.DEFAULT;


    public FontTestView(Context context) {
        super(context);
        init (context);
    }

    public FontTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init (context);
    }

    public FontTestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init (context);
    }

    private void init (Context context) {
        setBackgroundColor(0xffffffff);
        mTextView = new TextView(context);
        mTextView.setBackgroundResource(0);
        addView(mTextView);

        mTextView.setTextAppearance(context, R.style.FontTestViewTextStyle);
        mTextView.setText(text);
        mTextView.setTypeface(defaultFont.getTypeface());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.

        //Typeface defaultFont = PDEFontHelpers.sDefaultFont;
        //Typeface defaultFont = Typeface.DEFAULT;
        float textSize = 18;
        float offsetY = 20;
        float offsetX = 20;
        float capHeight;
        //float ascender;
        Rect bounds = new Rect();
        Rect origBounds = new Rect();
        Rect capsHeightBounds;
        //Rect ascenderBounds;

        Paint paint = new Paint();
        Paint paintRect = new Paint();
        //Paint ascPaint = new Paint();


        paint.setTypeface(defaultFont.getTypeface());
        paint.setAntiAlias(true);
        paint.setColor(0xff000000);

        for (int i = 0; i < 6; i++) {

            paintRect.setColor(getContext().getResources().getColor(R.color.KDTestHalf3));

            paint.setTextSize(textSize);
            paint.getTextBounds(text, 0, text.length(),bounds);
            paint.getTextBounds(text, 0, text.length(),origBounds);

            bounds.top += offsetY;
            bounds.bottom += offsetY;
            bounds.left += offsetX;
            bounds.right += offsetX;

            if ( i == 5 ) {
                LayoutParams lp = (LayoutParams) mTextView.getLayoutParams();
                lp.leftMargin = (int)(offsetX + 100);
                lp.topMargin = (int)offsetY;
                lp.width = PDEBuildingUnits.roundUpToScreenCoordinates(bounds.width() + origBounds.left);
                //lp.height = -bounds.top + bounds.bottom;
                lp.height = (int) PDEFontHelpers.getHeight(defaultFont, textSize);
                mTextView.setLayoutParams(lp);
                mTextView.setBackgroundColor(0xff8888FF);
                mTextView.setTextScaleX(1.0f);
                mTextView.setTypeface(defaultFont.getTypeface());

                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                if(DEBUGPARAMS){
                    Log.d(LOG_TAG, "" + textSize + " " + mTextView.getTextSize());
                }
            }



            canvas.drawRect(bounds, paintRect);



            capHeight = PDEFontHelpers.getCapHeight(defaultFont, textSize);
            capsHeightBounds = new Rect(bounds);
            capsHeightBounds.top = (int)offsetY;
            capsHeightBounds.bottom = (int)(offsetY + capHeight);
            capsHeightBounds.left += 10;
            capsHeightBounds.right += 10;
            paintRect.setColor(getContext().getResources().getColor(R.color.KDTestHalf));
            canvas.drawRect(capsHeightBounds, paintRect);


//            ascender = PDEFontHelpers.getAscender(defaultFont,textSize);
//            ascenderBounds = new Rect(bounds);
//            ascenderBounds.top = (int)offsetY;
//            ascenderBounds.bottom = (int)(offsetY + ascender);
//            ascenderBounds.left += 20;
//            ascenderBounds.right += 20;
//            ascPaint.setColor(getContext().getResources().getColor(R.color.KDTestHalf2));
//            canvas.drawRect(ascenderBounds, ascPaint);



            String printText = String.format("%.2f (%d,%d-%d,%d) bh: %d ch: %.1f height: %.2f", textSize, origBounds.left, origBounds.top, origBounds.right, origBounds.bottom, bounds.height(), capHeight, PDEFontHelpers.getHeight(defaultFont, textSize));

            canvas.drawText(text, offsetX, offsetY, paint);

            paint.setTextSize(14);

            canvas.drawText(printText, offsetX+bounds.width()+20, offsetY, paint);




            textSize += 3;
            offsetY += textSize * 2.3f;
        }



    }
}
