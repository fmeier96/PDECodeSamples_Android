package de.telekom.pde.codelibrary.samples.datavisualisation.usagecircle;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.*;


import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.PDEUsageEvent;
import de.telekom.pde.codelibrary.ui.components.datavisualisation.usagecircle.PDEUsageCircle;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;




//----------------------------------------------------------------------------------------------------------------------
//  UsageCircleResizingSample
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief UsageCircleResizingSample activity for a usage circle sample.
 */
@SuppressWarnings("unused")
public class UsageCircleResizingSample extends ResizeBaseActivity {


    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = UsageCircleResizingSample.class.getName();


    // image view as drawable container
    PDEUsageCircle mUsageCircle;
    PDEButton startButton;


    /**
     * @brief Create the activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set button
        getLayoutInflater().inflate(R.layout.usagecircle_resizing_screen,
                                    (ViewGroup) findViewById(R.id.resize_base_screen_bottom_menu_container));


        startButton = (PDEButton)findViewById(R.id.button_start);
        startButton.addListener(this, "buttonStartPressed",
                                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        mUsageCircle = new PDEUsageCircle(this);
        mUsageCircle.setTotalFillValue(6.0f);
        mUsageCircle.setTargetFillValue(2.5f);
        mUsageCircle.setFillDurationToTotalFillValue(2000);
        mUsageCircle.setNumberOfDecimalPlaces(1);
        mUsageCircle.setUnitText("kg");
        mUsageCircle.setCircleStyle(PDEUsageCircle.PDEUsageCircleStyle.PDEUsageCircleStyleSegmented);

        mUsageCircle.addListener(this, "onProgressChanged");

        //set some linear layout parameter to have correct position and size of usage circle
        RelativeLayout.LayoutParams
                primitivesLinearLayoutParams
                = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                  ViewGroup.LayoutParams.MATCH_PARENT);
        //add to view
        addViewToResizeContainer(mUsageCircle, primitivesLinearLayoutParams);


        setContainerSize(1000, 1000);
    }


    /**
     * @brief Callback function for the start button will be selected event.
     * @param event Received event.
     */
    @SuppressWarnings("unused")
    public void buttonStartPressed(PDEEvent event) {
        if (event.getSender() == startButton) {
            float target = mUsageCircle.getTargetFillValue();
            mUsageCircle.setCurrentFillValue(0);
            mUsageCircle.setTargetFillValue(target);
            mUsageCircle.startAnimation();
        }
    }

    /**
     * Callback for the progress value changed event
     * @param event The received event.
     */
    @SuppressWarnings("unused")
    public void onProgressChanged(PDEEvent event) {
        PDEUsageEvent usageEvent = (PDEUsageEvent) event;

        if (event.isType(PDEUsageEvent.EVENT_ACTION_ANIMATION_FINISHED)) {
            String messageString = event.getType() + ", currFillValue: " + String.format("%f",
                                                                                         usageEvent.getCurrentFillValue())
                                   +", total value: " + String.format("%f", usageEvent.getTotalValue());
            Log.d(LOG_TAG, messageString);
        }
    }


}
