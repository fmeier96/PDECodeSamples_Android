/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;


/**
 * @brief Activity class for the sizing test screen.
 */
public class SizingTestScreenActivity extends Activity{

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = SizingTestScreenActivity.class.getName();
    ImageView mThumbView;
    PDEButton mButton;
    float mRawXAtStart;
    float mRawYAtStart;
    float mThumbXAtStart;
    float mThumbYAtStart;
    float mButtonWidthAtStart;
    float mButtonHeightAtStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.developer_sizing_test_screen);


        mButton = ((PDEButton) findViewById(R.id.sizingtestscreen_dtbutton));
//        mButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundBeveled);
//         mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundRect);
//         mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundPlate);
//         mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundFlat);
         mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundEmbossed);
//         mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundIndicative);
        //mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButton.PDEButtonLayerType.BackgroundEmbossed);
        //mButton.setColorWithString("DTMagenta");
        //mButton.setColorWithString("DTFunctionalGreen");
        //mButton.setColorWithString("DTGrey100");
        //mButton.setFontSize(50);
        mButton.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentRight);


        mThumbView = new ImageView(this);
        mThumbView.setImageDrawable(getResources().getDrawable(R.drawable.circle_thumb));
        mThumbView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();


                if (action == MotionEvent.ACTION_DOWN) {
                    //Log.d(LOG_TAG, "ACTION_DOWN "+event.getRawX()+", "+event.getRawY());
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mThumbView.getLayoutParams();
                    mThumbXAtStart = lp.leftMargin;
                    mThumbYAtStart = lp.topMargin;
                    mRawXAtStart = event.getRawX();
                    mRawYAtStart = event.getRawY();
                    mButtonWidthAtStart = mButton.getWidth();
                    mButtonHeightAtStart = mButton.getHeight();

                    return true;
                } else if (action == MotionEvent.ACTION_MOVE) {
                    //Log.d(LOG_TAG, "ACTION_MOVE "+event.getRawX()+", "+event.getRawY());
                    float deltaX = event.getRawX() - mRawXAtStart;
                    float deltaY = event.getRawY() - mRawYAtStart;
                    float newButtonWidth = mButtonWidthAtStart + deltaX;
                    float newButtonHeight = mButtonHeightAtStart + deltaY;

                    // limit the size to one BU and positive values
                    if (newButtonWidth < PDEBuildingUnits.exactBU()) {
                        deltaX = PDEBuildingUnits.exactBU() - mButtonWidthAtStart;
                        newButtonWidth = PDEBuildingUnits.exactBU();
                    }
                    if (newButtonHeight < PDEBuildingUnits.exactBU()) {
                        deltaY = PDEBuildingUnits.exactBU() - mButtonHeightAtStart;
                        newButtonHeight = PDEBuildingUnits.exactBU();
                    }


                    // set ThumbView position
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mThumbView.getLayoutParams();
                    lp.leftMargin = (int) (mThumbXAtStart + deltaX);
                    lp.topMargin = (int) (mThumbYAtStart + deltaY);
                    mThumbView.setLayoutParams(lp);

                    //Log.d(LOG_TAG, "new button size: "+newButtonWidth+" , "+newButtonHeight);
                    // set button size
                    RelativeLayout.LayoutParams lpButton = (RelativeLayout.LayoutParams) mButton.getLayoutParams();
                    // minimum size 1 BU
                    lpButton.width = (int) newButtonWidth;
                    lpButton.height = (int) newButtonHeight ;
                    mButton.setLayoutParams(lpButton);

                    return true;
                } else if (action == MotionEvent.ACTION_UP) {
                    //Log.d(LOG_TAG, "ACTION_UP "+event.getRawX()+", "+event.getRawY());
                    return true;
                } else if (action == MotionEvent.ACTION_CANCEL) {
                    //Log.d(LOG_TAG, "ACTION_CANCEL "+event.getRawX()+", "+event.getRawY());
                    return true;
                }

                return false;
            }
        });



        RelativeLayout.LayoutParams buttonLP = (RelativeLayout.LayoutParams)mButton.getLayoutParams();


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(90, 90);
        lp.leftMargin = buttonLP.width+50;
        lp.topMargin = buttonLP.height+50;




        ((RelativeLayout) findViewById(R.id.sizingtestscreen_relativelayout)).addView(mThumbView, lp);

    }
}
