/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdebutton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ScrollView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


public class ButtonsOverviewGenericActivity extends PDEActionBarActivity {

    private PDEButton mButtonCheckbox1 = null;
    private PDEButton mButtonCheckbox2 = null;
    private PDEButton mButtonRadio1 = null;
    private PDEButton mButtonRadio2 = null;
    private int mRadioState = 0;
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    public void onCreate(Bundle savedInstanceState) {
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

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            setContentView(R.layout.buttons_overview_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Buttons overview");
        } else {
            setContentView(R.layout.buttons_overview_flat_screen);
            getSupportActionBar().setTitle("Flat style/Buttons overview");
        }



        //get the root view and set background color (different when darkstyle is on or of in library)
        ScrollView rootView = (ScrollView)findViewById(R.id.headers_rootview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // add button listeners
        mButtonCheckbox1 = (PDEButton)findViewById(R.id.button_checkbox1);
        if (mButtonCheckbox1 != null) {
            mButtonCheckbox1.addListener(this,"buttonCheckboxPressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        }

        mButtonCheckbox2 = (PDEButton)findViewById(R.id.button_checkbox2);
        if (mButtonCheckbox2 != null) {
            mButtonCheckbox2.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        }
        mButtonRadio1 = (PDEButton)findViewById(R.id.button_radio1);
        if (mButtonRadio1 != null) {
            mButtonRadio1.addListener(this,"buttonRadioPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        }
        mButtonRadio2 = (PDEButton)findViewById(R.id.button_radio2);
        if (mButtonRadio2 != null) {
            mButtonRadio2.addListener(this,"buttonRadioPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        }


    }


    @SuppressWarnings("unused")
    public void buttonCheckboxPressed(PDEEvent event) {
        ((PDEButton)event.getSender()).setSelected(!((PDEButton) event.getSender()).isSelected());
    }



    @SuppressWarnings("unused")
    public void buttonRadioPressed(PDEEvent event){
        Object tag = ((View)event.getSender()).getTag();
        if (tag == null ) return;

        // get RadioState from tag
        mRadioState = Integer.valueOf(""+tag);

        // set Radio button states
        mButtonRadio1.setSelected(mRadioState==0);
        mButtonRadio2.setSelected(mRadioState==1);
    }
}
