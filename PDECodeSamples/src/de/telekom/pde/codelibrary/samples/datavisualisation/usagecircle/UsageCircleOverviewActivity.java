/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */


package de.telekom.pde.codelibrary.samples.datavisualisation.usagecircle;

import android.content.res.Configuration;
import android.os.Bundle;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.usagecircle.PDEUsageCircle;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


//----------------------------------------------------------------------------------------------------------------------
//  UsageCircleOverviewActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief UsageCircleOverviewActivity is a screen for a usage circle sample.
 */
public class UsageCircleOverviewActivity extends PDEActionBarActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = UsageCircleOverviewActivity.class.getName();


    // private variables
    private PDEButton startButton;
    private PDEUsageCircle[] usageCircle;


    /**
     * @brief Create the Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usageCircle = new PDEUsageCircle[6];

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.usagecircle_defaults_portrait);
        } else {
            setContentView(R.layout.usagecircle_defaults_landscape);
        }

        usageCircle[0] = ((PDEUsageCircle) findViewById(R.id.circle0));
        usageCircle[1] = ((PDEUsageCircle) findViewById(R.id.circle1));
        usageCircle[2] = ((PDEUsageCircle) findViewById(R.id.circle2));
        usageCircle[3] = ((PDEUsageCircle) findViewById(R.id.circle3));
        usageCircle[4] = ((PDEUsageCircle) findViewById(R.id.circle4));
        usageCircle[5] = ((PDEUsageCircle) findViewById(R.id.circle5));

        usageCircle[0].setStartAnimationAtOnceEnabled(true);

        //add start button
        startButton = ((PDEButton) findViewById(R.id.button_start));
        startButton.addListener(this, "buttonStartPressed",
                                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
    }


    /**
     * @brief Callback function for the start button will be selected event.
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void buttonStartPressed(PDEEvent event) {
        if (event.getSender() == startButton) {
            for (int i = 0; i < 6; i++) {
                float target = usageCircle[i].getTargetFillValue();
                usageCircle[i].setCurrentFillValue(0);
                usageCircle[i].setTargetFillValue(target);
            }
        }
    }

}
