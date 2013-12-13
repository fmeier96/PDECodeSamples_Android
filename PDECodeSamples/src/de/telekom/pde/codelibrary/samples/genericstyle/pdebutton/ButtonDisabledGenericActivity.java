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
import android.util.Log;
import android.widget.ScrollView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


public class ButtonDisabledGenericActivity extends PDESherlockActivity {

    private PDEButton mButtonTest = null;
    private PDEButton mButtonCheckbox = null;
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonDisabledGenericActivity.class.getName();

    @Override
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
            setContentView(R.layout.button_disabled_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Button disabled");
        } else {
            setContentView(R.layout.button_disabled_flat_screen);
            getSupportActionBar().setTitle("Flat style/Button disabled");
        }



        //get the root view and set background color (different when darkstyle is on or of in library)
        ScrollView rootView = (ScrollView)findViewById(R.id.button_disabled_rootview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());




        // create and configure
        mButtonTest = (PDEButton)findViewById(R.id.button_disabled_test);
        mButtonTest.addListener(this, "buttonTestPressed",
                                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);



        // add button listeners
        mButtonCheckbox = (PDEButton)findViewById(R.id.button_checkbox);
        mButtonCheckbox.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

    }


    /**
     * @brief Listener on test button.
     *
     */
    @SuppressWarnings("unused")
    public void buttonTestPressed(PDEEvent event)
    {
        Log.d(LOG_TAG, "Button Pressed");
    }



    /**
     * @brief Listener on checkbox button.
     *
     * Change enabled state with this listener.
     */
    @SuppressWarnings("unused")
    public void buttonCheckboxPressed(PDEEvent event) {
        // toggle enabled state
        mButtonTest.setEnabled(!mButtonTest.isEnabled());

        // update checkbox button state
        ((PDEButton)event.getSender()).setSelected(mButtonTest.isEnabled());
    }


}