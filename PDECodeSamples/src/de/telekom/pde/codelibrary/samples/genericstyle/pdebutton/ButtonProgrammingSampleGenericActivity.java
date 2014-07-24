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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Locale;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


public class ButtonProgrammingSampleGenericActivity extends PDEActionBarActivity implements View.OnClickListener {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = ButtonProgrammingSampleGenericActivity.class.getName();


    private PDEButton mPDEButton = null;
    private Button mStandardButton = null;
    private final float buttonWidth = PDEBuildingUnits.pixelFromBU(17);
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent callIntent = getIntent();
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

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            setContentView(R.layout.button_programming_sample_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Button programming sample");
        } else {
            setContentView(R.layout.button_programming_sample_flat_screen);
            getSupportActionBar().setTitle("Flat style/Button programming sample");
        }


        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout) findViewById(R.id.buttonxml_layout_container);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // *************************
        // PDEButton
        // *************************
        mPDEButton = (PDEButton) findViewById(R.id.button_pde);
        // Add a button listener. The used event system is more sophisticated (and meets the styleguide requirements)
        // than the Android event system.
        // Here one event type (will_be_selected) is requested.
        // The function void dtButtonPressed(PDEEvent event) will be called.
        mPDEButton.addListener(this, "pdeButtonPressed",
                               PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);


        // *************************
        // Standard Button
        // *************************
        mStandardButton = (Button) findViewById(R.id.button_standard);
        mStandardButton.setOnClickListener(this);


        // adapt sizes
        ViewGroup.LayoutParams lp = mPDEButton.getLayoutParams();
        if (lp != null) {
            lp.width = (int) buttonWidth;
            mPDEButton.setLayoutParams(lp);
        }
        lp = mStandardButton.getLayoutParams();
        if (lp != null) {
            lp.width = (int) buttonWidth;
            mStandardButton.setLayoutParams(lp);
        }
    }


    /**
     * @brief Called when the native button was clicked
     */
    @Override
    public void onClick(View view) {
        if (view == mStandardButton) {
            Log.d(LOG_TAG, "Native Android Button (Button) was pressed!");
        }
    }


    /**
     * @brief Called on changes from agentController
     */
    @SuppressWarnings("unused")
    public void pdeButtonPressed(PDEEvent event) {
        if (event.getSender() == mPDEButton) {
            Log.d(LOG_TAG, "Telekom CodeComponent Button (PDEButton) was pressed!");
        }
    }
}