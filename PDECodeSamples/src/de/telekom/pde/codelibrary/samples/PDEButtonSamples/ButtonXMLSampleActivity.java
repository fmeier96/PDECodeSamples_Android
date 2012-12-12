/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */
package de.telekom.pde.codelibrary.samples.PDEButtonSamples;


// imports
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity for sample screen to compare Deutsche Telekom Button and Android native Button.
 */
public class ButtonXMLSampleActivity extends Activity implements View.OnClickListener {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = ButtonXMLSampleActivity.class.getName();

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

        setContentView(R.layout.button_xml_sample_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.buttonsample_relativelayout);
        rootView.setBackgroundColor(PDEColor.valueOf("DTUIBackground").getIntegerColor());

        //get container view which contains the buttons
        LinearLayout containerView = (LinearLayout)findViewById(R.id.buttonsample_container);

        PDEButton buttonDefault = (PDEButton)containerView.findViewById(R.id.telekomButton);
        buttonDefault.addListener(this, "dtButtonPressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        Button standardButton = (Button)containerView.findViewById(R.id.androidButton);
        standardButton.setOnClickListener(this);
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
    public void dtButtonPressed(PDEEvent event)
    {
        Log.d(LOG_TAG,"Telekom CodeComponent Button (PDEButton) was pressed!");
    }
}
