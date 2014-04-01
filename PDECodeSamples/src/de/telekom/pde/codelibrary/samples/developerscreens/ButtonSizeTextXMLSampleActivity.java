/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */
package de.telekom.pde.codelibrary.samples.developerscreens;


// imports

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

import java.util.ArrayList;


/**
 * @brief Activity for sample screen to compare Deutsche Telekom Button and Android native Button.
 */
public class ButtonSizeTextXMLSampleActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonSizeTextXMLSampleActivity.class.getName();

    private ArrayList<PDEButton> mButtonList;

    /**
     * @brief Create the Activity.
     *
     * Creates the  Activity which compares PDEButton with android native button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id;
        String idString;

        setContentView(R.layout.button_sizetest_xml_sample_screen);

        idString = "button_sizetest_xml_sample_button_";
        mButtonList = new ArrayList<PDEButton>(9);
        PDEButton button;

        for (int i = 1; i <= 9; i++) {
            // resolve id
            id = getResources().getIdentifier(idString+i, "id", getPackageName() );
            if (id > 0) {
                button = (PDEButton)findViewById(id);
                button.addListener(this, "buttonPressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
                mButtonList.add(i-1, button);
            }
        }

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.buttonsample_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        //LinearLayout containerView = (LinearLayout)findViewById(R.id.buttonsample_container);

    }

    @SuppressWarnings("unused")
    public void buttonPressed(PDEEvent event) {
        int index = mButtonList.indexOf(event.getSender());

        switch(index) {
            case 0:
              for (PDEButton button : mButtonList) {
                  button.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayNone);
              }
              break;
            case 1:
                for (PDEButton button : mButtonList) {
                    button.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayRadio);
                }
                break;
            case 2:
                for (PDEButton button : mButtonList) {
                    button.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
                }
                break;
            case 3:
                for (PDEButton button : mButtonList) {
                    button.setIcon("#s");
                }
                break;
            case 4:
                for (PDEButton button : mButtonList) {
                    button.setIcon("");
                }
                break;
            case 5:
                for (PDEButton button : mButtonList) {
                    button.setHorizontalPadding(0);
                }
                break;
            case 6:
                for (PDEButton button : mButtonList) {
                    button.setHorizontalPadding(PDEBuildingUnits.BU());
                }
                break;
            case 7:
                for (PDEButton button : mButtonList) {
                    button.setHorizontalPadding(2 * PDEBuildingUnits.BU());
                }
                break;
        }


        Toast.makeText(this,"Index: "+index,Toast.LENGTH_SHORT).show();

    }

}
