/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdesectionedbutton;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Locale;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.samples.basescreens.DefaultResizeActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.sectionedbuttons.PDESectionedButton;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;

//----------------------------------------------------------------------------------------------------------------------
//  SectionedButtonResizeGenericActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief SectionedButtonResizeGenericActivity for some resize sample of the sectioned button.
 */
public class SectionedButtonResizeGenericActivity extends DefaultResizeActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = SectionedButtonResizeGenericActivity.class.getName();


    // screen variables
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity where the user can select the type of PDEButton he wants.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point defaultSize;
        Point defaultOffset;
        PDESectionedButton pdeSectionedButton;

        // get the content style mode
        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (text != null && text.length() != 0) {
                if (PDEString.contains(text.toUpperCase(Locale.US), "haptic".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(Locale.US), "flat".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        // *************************
        // Insert simple PDESectionedButton
        // *************************
        pdeSectionedButton = new PDESectionedButton(this);
        pdeSectionedButton.insertSectionsFromResourceArray(R.array.SectionedButton_sample_default);

        // *************************
        // Configure the activity depending on the content style
        // *************************

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            pdeSectionedButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
            getSupportActionBar().setTitle("Haptic style/Sectioned button resizing");
        } else {
            pdeSectionedButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundFlat);
            getSupportActionBar().setTitle("Flat style/Sectioned button resizing");
        }

        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams
                btnLinearLayoutParams
                = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                  ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(pdeSectionedButton, btnLinearLayoutParams);

        defaultOffset = new Point(PDEBuildingUnits.BU() - pdeSectionedButton.getNeededPadding().left,
                                  PDEBuildingUnits.BU() - pdeSectionedButton.getNeededPadding().top);
        defaultSize = new Point(PDEBuildingUnits.pixelFromBU(17), PDEBuildingUnits.pixelFromBU(3));

        // some initial positioning/sizing
        setOptionalBoundsVisibilityPadding(pdeSectionedButton.getNeededPadding());
        setContainerOffset(defaultOffset);
        setContainerSize(defaultSize);
    }
}
