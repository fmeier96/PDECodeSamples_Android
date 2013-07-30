/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdetext;


// imports
import android.graphics.Point;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;


/**
 * @brief Activity class for the sizing test screen.
 */
public class InputFieldResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = InputFieldResizeActivity.class.getName();

    private Point mDefaultSize;
    PDEInputField mPDEInputField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDefaultSize = new Point(PDEBuildingUnits.pixelFromBU(17),PDEBuildingUnits.pixelFromBU(3));

        // *************************
        // Insert simple PDEInputField
        // *************************
        mPDEInputField= new PDEInputField(this);
        mPDEInputField.setFontSize(PDEInputField.PDEInputFieldParameterValueSizeAutomatic);
        mPDEInputField.setLeftIcon("#s");
        //set some linear layout parameter to have correct position and size of inputfield
        RelativeLayout.LayoutParams btnLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add inputfield to view
        addViewToResizeContainer(mPDEInputField,btnLinearLayoutParams);
        setContainerSize(mDefaultSize);
    }
}
