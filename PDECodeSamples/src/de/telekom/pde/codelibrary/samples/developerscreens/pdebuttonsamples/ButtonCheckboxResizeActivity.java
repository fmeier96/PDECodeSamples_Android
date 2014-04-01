/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens.pdebuttonsamples;

// imports
import android.graphics.Point;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity class for the sizing test screen.
 */
public class ButtonCheckboxResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonCheckboxResizeActivity.class.getName();

    private Point mDefaultOffset;
    private Point mDefaultSize;
    private PDEButton mPDEButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *************************
        // Insert simple PDEButton
        // *************************
        mPDEButton = new PDEButton(this);
        mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundNone);
        mPDEButton.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckboxHaptic);
        mPDEButton.setTitle("Checkbox");
        mPDEButton.setSelected(true);
        mPDEButton.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        mPDEButton.setParameter(PDEButton.PDEButtonParameterCheckboxAlignment, PDEConstants.PDEAlignmentStringRight);
        mPDEButton.addListener(this,"buttonPressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_MASK_ACTION);
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

    @SuppressWarnings("unused")
    public void buttonPressed(PDEEvent event){
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            if(mPDEButton.isSelected()){
                mPDEButton.setSelected(false);
            } else {
                mPDEButton.setSelected(true);
            }
        }
    }
}
