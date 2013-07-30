/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;


/**
 * @brief Activity class for the sizing test screen.
 */
public class SizingTestScreenActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = SizingTestScreenActivity.class.getName();
    PDEButton mPDEButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *************************
        // Insert simple PDEButton with icon
        // *************************
        mPDEButton = new PDEButton(this);
        mPDEButton.setTitle("Top-Üger");
        mPDEButton.setFontSize("auto");
        //mPDEButton.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center) ,true);
        //mPDEButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundBeveled);
        //mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundRect);
        //mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundPlate);
        //mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundFlat);
        mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundEmbossed);
        // mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundIndicative);
        //mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButton.PDEButtonLayerType.BackgroundEmbossed);
        //mPDEButton.setColorWithString("DTMagenta");
        //mPDEButton.setColorWithString("DTFunctionalGreen");
        //mPDEButton.setColorWithString("DTGrey100");
        //mPDEButton.setTextSize(50);
        //mPDEButton.setIconAlignment(PDEButton.PDEButtonIconAlignment.PDEButtonIconAlignmentLeft);
        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams btnLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(mPDEButton, btnLinearLayoutParams);
    }
}
