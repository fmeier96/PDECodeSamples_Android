/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdebutton;


// imports

import android.graphics.Point;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;


/**
 * @brief Activity class for the sizing test screen.
 */
public class ButtonResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
	private final static String LOG_TAG = ButtonResizeActivity.class.getName();

    private Point mDefaultOffset;
    private Point mDefaultSize;
    private PDEButton mPDEButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *************************
        // Insert simple PDEButton with icon
        // *************************
        mPDEButton = new PDEButton(this);
        mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundEmbossed);
        mPDEButton.setTitle("Top-Üger");
        mPDEButton.setIcon("#s",true);
        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams btnLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(mPDEButton, btnLinearLayoutParams);

        mDefaultOffset = new Point(PDEBuildingUnits.BU()-mPDEButton.getNeededPadding().left,PDEBuildingUnits.BU()-mPDEButton.getNeededPadding().top);
        mDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(17),PDEBuildingUnits.pixelFromBU(3));

        setOptionalBoundsVisibilityPadding(mPDEButton.getNeededPadding());
        setContainerOffset(mDefaultOffset);
        setContainerSize(mDefaultSize);
    }
}
