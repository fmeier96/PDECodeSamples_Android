/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.datavisualisation.usagebar;

import android.os.Bundle;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.usagebar.PDEUsageBar;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


//----------------------------------------------------------------------------------------------------------------------
//  UsageBarProgrammingSampleActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief UsageBarProgrammingSampleActivity for some test of the usage bar.
 */
public class UsageBarProgrammingSampleActivity extends PDEActionBarActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = UsageBarProgrammingSampleActivity.class.getName();


    // screen variables
    private PDEUsageBar mBar;


    /**
     * @brief Create the Activity.
     * @param savedInstanceState The saved instance state to recreate.
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.usagebar_programming_sample_screen);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rootviewUsageBar);


        PDEButton button200, button800, buttonTypeSwitch, buttonReduceUsage, buttonRaiseUsage, buttonLabelSwitch;
        PDEButton buttonTotalTextSwitch, buttonReduceTotal, buttonRaiseTotal, buttonUnitTextSwitch, buttonYellow, buttonDarkBlue;


        button200 = (PDEButton)findViewById(R.id.telekomButton200);
        button800 = (PDEButton)findViewById(R.id.telekomButton800);
        buttonTypeSwitch = (PDEButton)findViewById(R.id.telekomButtonTypeSwitch);
        buttonReduceUsage = (PDEButton)findViewById(R.id.reduceUsage);
        buttonRaiseUsage = (PDEButton)findViewById(R.id.raiseUsage);
        buttonLabelSwitch = (PDEButton)findViewById(R.id.labelSwitch);
        buttonReduceTotal = (PDEButton)findViewById(R.id.reduceTotal);
        buttonRaiseTotal = (PDEButton)findViewById(R.id.raiseTotal);
        buttonUnitTextSwitch = (PDEButton)findViewById(R.id.unitTextSwitch);
        buttonTotalTextSwitch = (PDEButton)findViewById(R.id.totalTextSwitch);
        buttonYellow = (PDEButton)findViewById(R.id.yellow);
        buttonDarkBlue = (PDEButton)findViewById(R.id.darkBlue);

        button200.addListener(this, "onClickButton200", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        button800.addListener(this, "onClickButton800", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonTypeSwitch.addListener(this, "onClickButtonTypeSwitch",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonReduceUsage.addListener(this, "onClickButtonReduceUsage", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonRaiseUsage.addListener(this, "onClickButtonRaiseUsage", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonLabelSwitch.addListener(this, "onClickButtonLabelSwitch",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonReduceTotal.addListener(this, "onClickButtonReduceTotal", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonRaiseTotal.addListener(this, "onClickButtonRaiseTotal", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonUnitTextSwitch.addListener(this, "onClickButtonUnitTextSwitch",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonTotalTextSwitch.addListener(this, "onClickButtonTotalTextSwitch",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonYellow.addListener(this, "onClickButtonYellow",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonDarkBlue.addListener(this, "onClickButtonDarkBlue",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);

        mBar = new PDEUsageBar(this);
        RelativeLayout.LayoutParams param =  new RelativeLayout.LayoutParams(100,20);
        param.width = ViewGroup.LayoutParams.MATCH_PARENT;
        param.height =  ViewGroup.LayoutParams.WRAP_CONTENT;
        param.leftMargin = PDEBuildingUnits.BU();
        param.topMargin = PDEBuildingUnits.BU();
        param.rightMargin = PDEBuildingUnits.BU();
        mBar.setTotalFillValue(1000.0f);
        mBar.setStartAnimationAtOnceEnabled(true);
        mBar.setNumberOfDecimalPlaces(1);
        mBar.setUnitText("MB");
        mBar.setUnitTextEnabled(false);

        // add bar
        layout.addView(mBar,param);
    }


    /**
     * @brief Callback for the 200 button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButton200(PDEEvent event) {
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setTargetFillValue(200.0f);
        }
    }


    /**
     * @brief Callback for the 800 button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButton800(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setTargetFillValue(800.0f);
        }
    }


    // todo: proper wrap content mechanism
    /**
     * @brief Callback for the type switch button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonTypeSwitch(PDEEvent event) {
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            if(mBar.getBarStyle() == PDEUsageBar.PDEUsageBarStyle.PDEUsageBarStyleFullBar){
                mBar.setBarStyle(PDEUsageBar.PDEUsageBarStyle.PDEUsageBarStyleSmallBar);
                ((PDEButton)event.getSender()).setText("Full");
            } else {
                mBar.setBarStyle(PDEUsageBar.PDEUsageBarStyle.PDEUsageBarStyleFullBar);
                ((PDEButton)event.getSender()).setText("Small");
            }
        }
    }


    /**
     * @brief Callback for the reduce usage button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonReduceUsage(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setTargetFillValue(mBar.getTargetFillValue() - 58.0f);
        }
    }


    /**
     * @brief Callback for the raise usage button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonRaiseUsage(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setTargetFillValue(mBar.getTargetFillValue() + 58.0f);
        }
    }


    /**
     * @brief Callback for the label switch button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonLabelSwitch(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setLabelEnabled(!mBar.isLabelEnabled());
            ((PDEButton)event.getSender()).setSelected(mBar.isLabelEnabled());
        }
    }


    /**
     * @brief Callback for the reduce total button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonReduceTotal(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setTotalFillValue(mBar.getTotalFillValue() - 100.0f);
        }
    }


    /**
     * @brief Callback for the raise total button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonRaiseTotal(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setTotalFillValue(mBar.getTotalFillValue() + 100.0f);
        }
    }


    /**
     * @brief Callback for the unit text switch button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonUnitTextSwitch(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setUnitTextEnabled(!mBar.isUnitTextEnabled());
            ((PDEButton)event.getSender()).setSelected(mBar.isUnitTextEnabled());
        }
    }


    /**
     * @brief Callback for the total text switch button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonTotalTextSwitch(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setTotalTextEnabled(!mBar.isTotalTextEnabled());
            ((PDEButton)event.getSender()).setSelected(mBar.isTotalTextEnabled());
        }
    }


    /**
     * @brief Callback for the yellow button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonYellow(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setColorWithString("DTDVYellow");
        }
    }


    /**
     * @brief Callback for the dark blue button.
     *
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void onClickButtonDarkBlue(PDEEvent event) {
        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mBar.setColorWithString("DTDVDarkBlue");
        }
    }
}