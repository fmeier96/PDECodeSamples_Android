/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdebutton;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;

/**
 * @brief Activity class for the sizing test screen.
 */
public class ButtonResizeGenericActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonResizeGenericActivity.class.getName();


    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Point defaultSize;
        Point defaultOffset;
        PDEButton pdeButton;

        super.onCreate(savedInstanceState);


        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (!TextUtils.isEmpty(text)){
                if (PDEString.contains(text.toUpperCase(), "haptic".toUpperCase()))  {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(), "flat".toUpperCase())) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }


        // *************************
        // Insert simple PDEButton with icon
        // *************************
        pdeButton = new PDEButton(this);
        pdeButton.setTitle("Top-Üger");
        pdeButton.setIcon("#s", true);

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            pdeButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
            getSupportActionBar().setTitle("Haptic style/Button resizing");
        } else {
            pdeButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundFlat);
            getSupportActionBar().setTitle("Flat style/Button resizing");
        }

        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams btnLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(pdeButton, btnLinearLayoutParams);

        defaultOffset = new Point(PDEBuildingUnits.BU()-pdeButton.getNeededPadding().left,PDEBuildingUnits.BU()-pdeButton.getNeededPadding().top);
        defaultSize = new Point(PDEBuildingUnits.pixelFromBU(17),PDEBuildingUnits.pixelFromBU(3));

        setOptionalBoundsVisibilityPadding(pdeButton.getNeededPadding());
        setContainerOffset(defaultOffset);
        setContainerSize(defaultSize);
    }
}
