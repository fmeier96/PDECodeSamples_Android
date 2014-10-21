/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */


package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.elements.complex.PDEActivityIndicator;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


public class PDEActivityIndicatorActivity extends PDEActionBarActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = PDEActivityIndicatorActivity.class.getName();

    private PDEButton startButton;
    private PDEButton triggerButton;
    private PDEActivityIndicator activityIndicator;


    /**
     * @brief Create the Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout rootView;
        DisplayMetrics displaymetrics;
        int windowWidth;

        setContentView(R.layout.metaphors_screen);

        // get the root view and set background color (different when dark-style is on or of in library)
        rootView = (RelativeLayout) findViewById(R.id.metaphores_showcase_rootlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //calculate widths
        displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        windowWidth = displaymetrics.widthPixels;

        activityIndicator = new PDEActivityIndicator(this);

        //add circle
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(windowWidth,
                                                                             ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(PDEBuildingUnits.BU(), PDEBuildingUnits.BU(), PDEBuildingUnits.BU(), PDEBuildingUnits.BU());
        params.addRule(RelativeLayout.ALIGN_TOP);
        activityIndicator.setLayoutParams(params);
        rootView.addView(activityIndicator);

        //add start button
        startButton = new PDEButton(this);
        startButton.setText("Stop");
        startButton.addListener(this, "buttonStartPressed",
                                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        RelativeLayout.LayoutParams buttonparams = new RelativeLayout.LayoutParams(12 * PDEBuildingUnits.BU(),
                                                                                   3 * PDEBuildingUnits.BU());
        buttonparams.setMargins(PDEBuildingUnits.BU(),
                                PDEBuildingUnits.BU(),
                                PDEBuildingUnits.BU(),
                                PDEBuildingUnits.BU());
        buttonparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        startButton.setLayoutParams(buttonparams);

        rootView.addView(startButton);

        //add trigger button
        triggerButton = new PDEButton(this);
        triggerButton.setText("Trigger");
        triggerButton.addListener(this, "buttonTriggerPressed",
                                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        RelativeLayout.LayoutParams triggerbuttonparams = new RelativeLayout.LayoutParams(12 * PDEBuildingUnits.BU(),
                                                                                   3 * PDEBuildingUnits.BU());
        triggerbuttonparams.setMargins(PDEBuildingUnits.BU(),
                                PDEBuildingUnits.BU(),
                                PDEBuildingUnits.BU(),
                                PDEBuildingUnits.BU());
        triggerbuttonparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        triggerbuttonparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        triggerButton.setLayoutParams(triggerbuttonparams);

        rootView.addView(triggerButton);
    }


    @SuppressWarnings("unused")
    public void buttonStartPressed(PDEEvent event) {
        if (event.getSender() == startButton) {
            if (activityIndicator.getMode() != PDEActivityIndicator.PDEActivityIndicatorMode.PDEActivityIndicatorModeSpinning) {
                activityIndicator.setMode(PDEActivityIndicator.PDEActivityIndicatorMode.PDEActivityIndicatorModeSpinning);
            }
               if (activityIndicator.isRunning()) {
                   activityIndicator.stop();
                   startButton.setText("Start");
               } else {
                   activityIndicator.start();
                   startButton.setText("Stop");
               }
        }
    }

    @SuppressWarnings("unused")
    public void buttonTriggerPressed(PDEEvent event) {
        if (event.getSender() == triggerButton) {
            if (activityIndicator.getMode() != PDEActivityIndicator.PDEActivityIndicatorMode.PDEActivityIndicatorModeTrigger) {
                activityIndicator.setMode(PDEActivityIndicator.PDEActivityIndicatorMode.PDEActivityIndicatorModeTrigger);
            }

            if (activityIndicator.isRunning()) {
                activityIndicator.stop();
            }

            startButton.setText("Start");

            float pct = activityIndicator.getTriggerPercentage();


            if (activityIndicator.getTriggerPercentage() == 1.0f) {
                pct = 0;
            } else {
                pct += 0.1f;
            }

            activityIndicator.setTriggerPercentage(pct);
        }
    }
}
