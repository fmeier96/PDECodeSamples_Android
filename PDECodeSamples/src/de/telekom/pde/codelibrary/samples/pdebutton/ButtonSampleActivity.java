/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdebutton;


// imports

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity for sample screen to compare PDEButton and Android native Button.
 */
public class ButtonSampleActivity extends PDEActivity implements View.OnClickListener {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = ButtonSampleActivity.class.getName();

    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity which compares PDEButton with android native button.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.button_sample_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.buttonsample_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        LinearLayout containerView = (LinearLayout)findViewById(R.id.buttonsample_container);

        //variable for linear layout params
        LinearLayout.LayoutParams btnLinearLayoutParams = null;

        //set button size and gaps
        float buttonWidth = PDEBuildingUnits.pixelFromBU(17);
        float buttonHeight = PDEBuildingUnits.pixelFromBU(3);
        float gapY = PDEBuildingUnits.pixelFromBU(5);


        // *************************
        // Insert simple PDEButton
        // *************************
        PDEButton buttonDefault = new PDEButton(this);
        buttonDefault.setId(R.id.button_default);
        buttonDefault.setTitle( "PDEButton" );
        // Add a button listener. The used event system is more sophisticated (and meets the styleguide requirements)
        // than the Android event system.
        // Here one event type (will_be_selected) is requested.
        // The function void dtButtonPressed(PDEEvent event) will be called.
        buttonDefault.addListener(this, "dtButtonPressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnLinearLayoutParams.setMargins(0, 0, 0, (int)gapY-buttonDefault.getNeededPadding().bottom);
        //add button to view
        containerView.addView(buttonDefault, btnLinearLayoutParams );


        // *************************
        // Insert standard Button
        // *************************
        Button standardButton = new Button(this);
        standardButton.setText( "Button" );
        standardButton.setOnClickListener(this);
        //set some linear layout parameter to have correct position and size of button
        btnLinearLayoutParams = new LinearLayout.LayoutParams((int)buttonWidth, (int)buttonHeight);
        //add button to view
        containerView.addView(standardButton, btnLinearLayoutParams);
    }


    /**
     * @brief Called when the native button was clicked
     */
    @Override
    public void onClick(View view) {
        Log.d(LOG_TAG, "Native Android Button (Button) was pressed!");
    }


    /**
     * @brief Called on changes from agentController
     */
    @SuppressWarnings("unused")
    public void dtButtonPressed(PDEEvent event)
    {
        Log.d(LOG_TAG,"Telekom CodeComponent Button (PDEButton) was pressed!");
    }
}
