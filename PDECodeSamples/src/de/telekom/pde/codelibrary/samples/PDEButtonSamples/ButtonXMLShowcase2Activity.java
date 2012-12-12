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

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity class to show all button types on one screen below each other.
 */
public class ButtonXMLShowcase2Activity extends Activity{

    private PDEButton mButtonMultiState = null;
    private PDEButton mButtonSelectable = null;
    private PDEButton mButtonCheckbox1 = null;
    private PDEButton mButtonCheckbox2 = null;
    private PDEButton mButtonCheckbox3 = null;
    private PDEButton mButtonCheckbox4 = null;
    private PDEButton mButtonCheckbox5 = null;
    private PDEButton mButtonCheckbox6 = null;
    private PDEButton mButtonRadio1 = null;
    private PDEButton mButtonRadio2 = null;
    private int mRadioState = 0;

    private int mMultiState = 0;

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = ButtonXMLShowcase2Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.button_xml_showcase2_screen);

        // init variable to match initial state
        mMultiState = 0;

        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a selectable active button
        //
        //  A selectable active button has two states - "default" and "selected". If no color for the default
        //  state is set, the styleguide default is used, so only the selected state has to be set (we use the
        //  magenta active in this case). Note that we need to merge the parameter (we cannot simply set
        //  a subkey directly, this would clear out all other keys which might be already set).
        //
        //  Buttons do not react by themselves, so we need to add a listener and change the state on user input.
        //  We also have to keep a reference to the button to be able to access it later. We react on the willBeSelected
        //  event to get immediate response. The selected event gets sent when the animation is done, it is intended
        //  for interaction where e.g. screen changes happen so the user gets a proper response on his action before
        //  something happens. This is not necessary here, since the button is still visible after user interaction.
        //
        //  We're also showing an icon in the button to demonstrate how the icon changes color along with the text when
        //  using colored icons.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        mButtonSelectable = (PDEButton)findViewById(R.id.button_selectable);
        // todo: -> xml
        mButtonSelectable.mergeParameter(PDEButton.PDEButtonParameterColor,"DTMagenta", PDEButton.PDEButtonStateSelected);
        mButtonSelectable.addListener(this, "buttonSelectablePressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);


        //------------------------------------------------------------------------------------------------------------------
        //
        //  Insert a button changing between more than one state.
        //
        //  Buttons can have an arbitrary number of states with arbitrary names. In this case, we build a button with
        //  different states for colors and cycle through them. We also set the default state explicitly (which would not
        //  be necessary here, but just as an example). The symbolic color "DTUIInteractive" is set automatically to the
        //  light or dark button interactive color depending on the library setting.
        //
        //  It's necessary to keep track of the button state locally, so we use a variable for this.
        //
        //  We're also showing an icon in the button to demonstrate how the icon changes color along with the text when
        //  using colored icons.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        mButtonMultiState = (PDEButton)findViewById(R.id.button_multistate);
        // 2do: find way to do this in xml
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalGreen", "state1");
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalRed", "state2");
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalBlue", "state4");
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalYellow", "state3");
        mButtonMultiState.addListener(this, "buttonMultiStatePressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        // add button listeners
        mButtonCheckbox1 = (PDEButton)findViewById(R.id.button_checkbox1);
        mButtonCheckbox1.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonCheckbox2 = (PDEButton)findViewById(R.id.button_checkbox2);
        mButtonCheckbox2.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonCheckbox3 = (PDEButton)findViewById(R.id.button_checkbox3);
        mButtonCheckbox3.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonCheckbox4 = (PDEButton)findViewById(R.id.button_checkbox4);
        mButtonCheckbox4.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonCheckbox5 = (PDEButton)findViewById(R.id.button_checkbox5);
        mButtonCheckbox5.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonCheckbox6 = (PDEButton)findViewById(R.id.button_checkbox6);
        mButtonCheckbox6.addListener(this,"buttonCheckboxPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonRadio1 = (PDEButton)findViewById(R.id.button_radio1);
        mButtonRadio1.addListener(this,"buttonRadioPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonRadio2 = (PDEButton)findViewById(R.id.button_radio2);
        mButtonRadio2.addListener(this,"buttonRadioPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);




    }


    /**
     * @brief Listener on selectable button.
     *
     * Toggle the selectable state on press. We are only added to one button and one event, so we do not need
     * to check event type or origin.
     */
    public void buttonSelectablePressed(PDEEvent event)
    {
        if( mButtonSelectable!=null ){
            mButtonSelectable.setSelected( !mButtonSelectable.isSelected() );
        }
    }


    /**
     * @brief Listener on multistate button.
     *
     * Count the states, wraparound at the end. Set the button's state accordingly.
     */
    public void buttonMultiStatePressed(PDEEvent event)
    {
        // count state, wraparound
        mMultiState = mMultiState + 1;
        if (mMultiState >= 5) {
            mMultiState = 0;
        }

        // set the state
        switch (mMultiState) {
            case 0: mButtonMultiState.setMainState(PDEButton.PDEButtonStateDefault);
                    break;
            case 1: mButtonMultiState.setMainState("state1");
                    break;
            case 2: mButtonMultiState.setMainState("state2");
                    break;
            case 3: mButtonMultiState.setMainState("state3");
                    break;
            case 4: mButtonMultiState.setMainState("state4");
                    break;
        }
    }

    public void buttonCheckboxPressed(PDEEvent event) {
        ((PDEButton)event.getSender()).setSelected(!((PDEButton) event.getSender()).isSelected());
    }

    public void buttonRadioPressed(PDEEvent event){
        Object tag = ((View)event.getSender()).getTag();
        if (tag == null ) return;

        // get RadioState from tag
        mRadioState = new Integer(""+tag);

        // set Radio button states
        mButtonRadio1.setSelected(mRadioState==0);
        mButtonRadio2.setSelected(mRadioState==1);
    }
}
