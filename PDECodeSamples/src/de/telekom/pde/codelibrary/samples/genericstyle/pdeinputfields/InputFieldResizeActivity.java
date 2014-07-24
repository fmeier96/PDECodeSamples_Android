/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeinputfields;


// imports

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Locale;

import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.samples.basescreens.DefaultResizeActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


/**
 * @brief Activity class for the sizing test screen.
 */
public class InputFieldResizeActivity extends DefaultResizeActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = InputFieldResizeActivity.class.getName();

    // screen variables
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity where the user can resize the inputfield.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // variables
        PDEInputField pdeInputField;
        Point defaultSize;
        Intent callIntent;

        callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (text != null && !TextUtils.isEmpty(text)) {
                // doubled check - stupid, but removes warning
                if (PDEString.contains(text.toUpperCase(Locale.US), "haptic".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(Locale.US), "flat".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        // set the title
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Inputfield resizing");
        } else {
            getSupportActionBar().setTitle("Flat style/Inputfield resizing");
        }

        defaultSize = new Point(PDEBuildingUnits.pixelFromBU(17), PDEBuildingUnits.pixelFromBU(3));

        // *************************
        // Insert simple PDEInputField
        // *************************
        pdeInputField = new PDEInputField(this);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            pdeInputField.setInputFieldBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundTextHaptic);
        }
        pdeInputField.setLeftIcon("#q");
        //set some linear layout parameter to have correct position and size of inputField
        RelativeLayout.LayoutParams btnLinearLayoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.MATCH_PARENT);
        //add inputField to view
        addViewToResizeContainer(pdeInputField, btnLinearLayoutParams);
        setContainerSize(defaultSize);
    }
}
