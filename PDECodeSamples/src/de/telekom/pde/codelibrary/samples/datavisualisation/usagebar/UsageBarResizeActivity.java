/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.datavisualisation.usagebar;


// imports
import android.graphics.Point;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.usagebar.PDEUsageBar;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


//----------------------------------------------------------------------------------------------------------------------
//  UsageBarResizeActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief Activity class for the sizing test screen.
 */
public class UsageBarResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
	private final static String LOG_TAG = UsageBarResizeActivity.class.getName();


    // screen variables
    private PDEUsageBar mUsageBar;
    private PDEButton mSwitchTypeButton;
    private PDEButton mReduceUsageButton;
    private PDEButton mRaiseUsageButton;


    /**
     * @brief Create the Activity.
     * @param savedInstanceState The saved instance state to recreate.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point defaultSize;

        // set bottom menu
        getLayoutInflater().inflate(R.layout.usagebar_resize_screen,(ViewGroup)findViewById(R.id.resize_base_screen_bottom_menu_container));

        // set default size
        defaultSize = new Point(PDEBuildingUnits.pixelFromBU(20),PDEBuildingUnits.pixelFromBU(4));

        // get the buttons and add listeners
        mSwitchTypeButton = (PDEButton)findViewById(R.id.button_switchType);
        if (mSwitchTypeButton!=null) {
            mSwitchTypeButton.addListener(this,"onSwitchTypeButtonClicked", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        }
        mReduceUsageButton = (PDEButton)findViewById(R.id.button_reduceUsage);
        if (mReduceUsageButton!=null) {
            mReduceUsageButton.addListener(this,"onReduceUsageButtonClicked", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        }
        mRaiseUsageButton = (PDEButton)findViewById(R.id.button_raiseUsage);
        if (mRaiseUsageButton!=null) {
            mRaiseUsageButton.addListener(this,"onRaiseUsageButtonClicked", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        }

        // create usage bar for resizing
        mUsageBar = new PDEUsageBar(this);
        mUsageBar.setTotalFillValue(100.0f);
        mUsageBar.setCurrentFillValue(70.0f);
        mUsageBar.setUnitText("MB");

        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams btnLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(mUsageBar, btnLinearLayoutParams);
        setContainerSize(defaultSize);
        updateMinimumSize();

    }


    /**
     * @brief Helper function to update the minimum size.
     */
    private void updateMinimumSize() {
        Point minimumSize;
        if (mUsageBar.getBarStyle()== PDEUsageBar.PDEUsageBarStyle.PDEUsageBarStyleSmallBar) {
            minimumSize = new Point(PDEBuildingUnits.BU(),PDEBuildingUnits.pixelFromBU(5));
        } else {
            minimumSize = new Point(PDEBuildingUnits.BU(),PDEBuildingUnits.pixelFromBU(4));
        }
        setMinimumSize(minimumSize);
        setContainerSize(new Point(getContainerSize().x,Math.max(getContainerSize().y,minimumSize.y)));
    }


    /**
     * @brief Callback for the type switch button.
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onSwitchTypeButtonClicked(PDEEvent event) {
        // check type
        if (event.getSender()==mSwitchTypeButton) {
            if (mUsageBar.getBarStyle() == PDEUsageBar.PDEUsageBarStyle.PDEUsageBarStyleSmallBar) {
                mUsageBar.setBarStyle(PDEUsageBar.PDEUsageBarStyle.PDEUsageBarStyleFullBar);
                mSwitchTypeButton.setTitle("Small");
            } else {
                mUsageBar.setBarStyle(PDEUsageBar.PDEUsageBarStyle.PDEUsageBarStyleSmallBar);
                mSwitchTypeButton.setTitle("Full");
            }
            updateMinimumSize();
        }
    }


    /**
     * @brief Callback for the raise usage button.
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onRaiseUsageButtonClicked(PDEEvent event) {
        // check type
        if (event.getSender()==mRaiseUsageButton) {
            mUsageBar.setTargetFillValue(mUsageBar.getTargetFillValue() + 10.0f);
            mUsageBar.startAnimation();
        }
    }


    /**
     * @brief Callback for the reduce usage button.
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onReduceUsageButtonClicked(PDEEvent event) {
        // check type
        if (event.getSender()==mReduceUsageButton) {
            mUsageBar.setTargetFillValue(mUsageBar.getTargetFillValue() - 10.0f);
            mUsageBar.startAnimation();
        }
    }
}
