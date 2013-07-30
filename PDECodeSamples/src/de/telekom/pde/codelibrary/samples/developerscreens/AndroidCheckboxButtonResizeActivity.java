package de.telekom.pde.codelibrary.samples.developerscreens;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


public class AndroidCheckboxButtonResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = AndroidCheckboxButtonResizeActivity.class.getName();

    PDEButton mPDEButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *************************
        // Insert simple PDEButton with icon
        // *************************
        mPDEButton = new PDEButton(this);
        // needed to restore the checkbox state
        mPDEButton.setId(R.id.developer_checkbox_resize_screen_checkbox);

        mPDEButton.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center), true);

        //mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundPlate);
        mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundPlate);
        //mPDEButton.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mPDEButton.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayRadio);
        mPDEButton.setTitle("Radio");
        //mPDEButton.setTitle("Checkbox");
        mPDEButton.setSelected(true);
        mPDEButton.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        //mPDEButton.setParameter(PDEButton.PDEButtonParameterCheckboxAlignment, PDEConstants.PDEAlignmentStringRight);
        //mPDEButton.setParameter(PDEButton.PDEButtonParameterCheckboxAlignment, PDEConstants.PDEAlignmentStringCenter);
        mPDEButton.setParameter(PDEButton.PDEButtonParameterRadioAlignment, PDEConstants.PDEAlignmentStringLeft);
        //mPDEButton.setParameter(PDEButton.PDEButtonParameterRadioAlignment, PDEConstants.PDEAlignmentStringRight);
        //mPDEButton.setParameter(PDEButton.PDEButtonParameterRadioAlignment, PDEConstants.PDEAlignmentStringCenter);
        //mPDEButton.setParameter(PDEButton.PDEButtonParameterCheckboxAlignment, PDEConstants.PDEAlignmentStringLeft);
        mPDEButton.addListener(this,"buttonPressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_MASK_ACTION);
        //  mPDEButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.B);

        //set some linear layout parameter to have correct position and size of button
        RelativeLayout.LayoutParams btnLinearLayoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        //add button to view
        addViewToResizeContainer(mPDEButton, btnLinearLayoutParams);
    }

    public void buttonPressed(PDEEvent event){
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            if(mPDEButton.isSelected()){
                mPDEButton.setSelected(false);
            } else {
                mPDEButton.setSelected(true);
            }
        }
    }
}
