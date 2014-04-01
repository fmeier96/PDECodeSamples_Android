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
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


/**
 * @brief Activity class for the sizing test screen.
 */
public class InputFieldResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = InputFieldResizeActivity.class.getName();


    private PDEInputField mPDEInputField;

    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Point defaultSize;
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
        mPDEInputField = new PDEInputField(this);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            mPDEInputField.setInputFieldBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundTextHaptic);
        }
        mPDEInputField.setLeftIcon("#q");
        //set some linear layout parameter to have correct position and size of inputField
        RelativeLayout.LayoutParams btnLinearLayoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        //add inputField to view
        addViewToResizeContainer(mPDEInputField, btnLinearLayoutParams);
        setContainerSize(defaultSize);
    }
}
