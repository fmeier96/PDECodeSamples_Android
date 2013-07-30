/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdebutton;


// imports

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.R.id;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity class to show all button types on one screen below each other.
 */
public class ButtonShowcase2Activity extends PDEActivity {

    private PDEButton mButtonMultiState = null;
    private PDEButton mButtonIconFontMultiState = null;
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
    private int mIconFontMultiState = 0;

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = ButtonShowcase2Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.button_showcase2_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        ScrollView rootView = (ScrollView)findViewById(R.id.buttonshowcase2_rootview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //get container view which contains the buttons
        RelativeLayout containerView = (RelativeLayout)findViewById(R.id.buttonshowcase2_container);

        //variable for linear layout params
        RelativeLayout.LayoutParams btnRelativeLayoutParams = null;

        //set button size and gaps
        float buttonWidth = PDEBuildingUnits.pixelFromBU(18);
        float buttonHeight = PDEBuildingUnits.pixelFromBU(3);
        float gapX = PDEBuildingUnits.pixelFromBU(1.5f);
        float gapY = PDEBuildingUnits.pixelFromBU(1.5f);

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
        //  We're also showing an Icon in the button to demonstrate how the Icon changes color along with the text when
        //  using colored icons.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        mButtonSelectable = new PDEButton(this);
        mButtonSelectable.setId(R.id.button_selectable);
        mButtonSelectable.setTitle("Selectable");
        mButtonSelectable.mergeParameter(PDEButton.PDEButtonParameterColor,"DTMagenta", PDEButton.PDEButtonStateSelected);
        mButtonSelectable.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center), true );
        mButtonSelectable.addListener(this,"buttonSelectablePressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);


        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0, PDEBuildingUnits.pixelFromBU(2.0f), 0, (int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(mButtonSelectable, btnRelativeLayoutParams );



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
        //  We're also showing an Icon in the button to demonstrate how the Icon changes color along with the text when
        //  using colored icons.
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        mButtonMultiState = new PDEButton(this);
        mButtonMultiState.setId(R.id.button_multistate);
        mButtonMultiState.setTitle("Multiple States");
        mButtonMultiState.setIcon( getResources().getDrawable(R.drawable.synchronize_generic_plain_center), true );
        mButtonMultiState.setColorWithString("DTUIInteractive");
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalGreen", "state1");
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalRed", "state2");
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalBlue", "state4");
        mButtonMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalYellow", "state3");
        mButtonMultiState.addListener(this,"buttonMultiStatePressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonSelectable.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(mButtonMultiState, btnRelativeLayoutParams);


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
        //  We're also showing an IconFont in the button to demonstrate how the IconFont changes color along with the text
        //
        //------------------------------------------------------------------------------------------------------------------

        // create and configure
        mButtonIconFontMultiState = new PDEButton(this);
        mButtonIconFontMultiState.setId(id.button_iconfont_multistate);
        mButtonIconFontMultiState.setTitle("Multiple IconFont");
        mButtonIconFontMultiState.setIcon("#s",true);
        mButtonIconFontMultiState.setColorWithString("DTUIInteractive");
        mButtonIconFontMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalGreen", "state1");
        mButtonIconFontMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalRed", "state2");
        mButtonIconFontMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalBlue", "state4");
        mButtonIconFontMultiState.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalYellow", "state3");
        mButtonIconFontMultiState.addListener(this,"buttonMultiIconFontStatePressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonMultiState.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(mButtonIconFontMultiState, btnRelativeLayoutParams);


        // create and configure
        mButtonCheckbox1 = new PDEButton(this);
        mButtonCheckbox1.setId(R.id.button_checkbox1);
        mButtonCheckbox1.setTitle("Checkbox 1");
        mButtonCheckbox1.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonCheckbox1.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mButtonCheckbox1.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        mButtonCheckbox1.setTag(Integer.valueOf(0));
        mButtonCheckbox1.addListener(this,"buttonCheckboxPressed",
                                     PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonIconFontMultiState.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(mButtonCheckbox1, btnRelativeLayoutParams);



        // create and configure
        mButtonCheckbox2 = new PDEButton(this);
        mButtonCheckbox2.setId(R.id.button_checkbox2);
        mButtonCheckbox2.setTitle("Checkbox 2");
        mButtonCheckbox2.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonCheckbox2.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mButtonCheckbox2.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        mButtonCheckbox2.setTag(Integer.valueOf(1));
        mButtonCheckbox2.addListener(this, "buttonCheckboxPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonCheckbox1.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(mButtonCheckbox2, btnRelativeLayoutParams);



        // create and configure
        mButtonRadio1 = new PDEButton(this);
        mButtonRadio1.setId(R.id.button_radio1);
        mButtonRadio1.setTitle("Radio 1");
        mButtonRadio1.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonRadio1.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayRadio);
        mButtonRadio1.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        mButtonRadio1.setTag(Integer.valueOf(0));
        mButtonRadio1.addListener(this, "buttonRadioPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonCheckbox2.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(mButtonRadio1, btnRelativeLayoutParams);


        // create and configure
        mButtonRadio2 = new PDEButton(this);
        mButtonRadio2.setId(R.id.button_radio2);
        mButtonRadio2.setTitle("Radio 2");
        mButtonRadio2.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonRadio2.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayRadio);
        mButtonRadio2.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentLeft);
        mButtonRadio2.setTag(Integer.valueOf(1));
        mButtonRadio2.addListener(this, "buttonRadioPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonWidth,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,0,(int)gapY);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonRadio1.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //add button to view
        containerView.addView(mButtonRadio2, btnRelativeLayoutParams);


        mRadioState = 0;
        mButtonRadio1.setSelected(true);

        // create and configure
        mButtonCheckbox3 = new PDEButton(this);
        mButtonCheckbox3.setId(R.id.button_checkbox3);
        mButtonCheckbox3.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonCheckbox3.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mButtonCheckbox3.setCheckboxAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        mButtonCheckbox3.addListener(this, "buttonCheckboxSmallPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonHeight,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,(int)gapX,0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonRadio2.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, mButtonRadio2.getId());
        //btnRelativeLayoutParams.addRule(RelativeLayout.ALIGN_LEFT);
        //add button to view
        containerView.addView(mButtonCheckbox3, btnRelativeLayoutParams);



        // create and configure
        mButtonCheckbox4 = new PDEButton(this);
        mButtonCheckbox4.setId(R.id.button_checkbox4);
        mButtonCheckbox4.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonCheckbox4.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mButtonCheckbox4.setCheckboxAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        mButtonCheckbox4.addListener(this, "buttonCheckboxSmallPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonHeight,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,(int)gapX,0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonRadio2.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF,mButtonCheckbox3.getId());
        //add button to view
        containerView.addView(mButtonCheckbox4, btnRelativeLayoutParams);



        // create and configure
        mButtonCheckbox5 = new PDEButton(this);
        mButtonCheckbox5.setId(R.id.button_checkbox5);
        mButtonCheckbox5.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonCheckbox5.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mButtonCheckbox5.setCheckboxAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        mButtonCheckbox5.addListener(this, "buttonCheckboxSmallPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonHeight,(int)buttonHeight);
        btnRelativeLayoutParams.setMargins(0,0,(int)gapX,0);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonRadio2.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF,mButtonCheckbox4.getId());
        //add button to view
        containerView.addView(mButtonCheckbox5, btnRelativeLayoutParams);



        // create and configure
        mButtonCheckbox6 = new PDEButton(this);
        mButtonCheckbox6.setId(R.id.button_checkbox6);
        mButtonCheckbox6.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundNone);
        mButtonCheckbox6.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mButtonCheckbox6.setCheckboxAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        mButtonCheckbox6.addListener(this,"buttonCheckboxSmallPressed",
                                     PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        //set some linear layout parameter to have correct position and size of button
        btnRelativeLayoutParams = new RelativeLayout.LayoutParams((int)buttonHeight,(int)buttonHeight);
        btnRelativeLayoutParams.addRule(RelativeLayout.BELOW, mButtonRadio2.getId());
        btnRelativeLayoutParams.addRule(RelativeLayout.RIGHT_OF,mButtonCheckbox5.getId());
        //add button to view
        containerView.addView(mButtonCheckbox6, btnRelativeLayoutParams);




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
        if (mMultiState >= 5) mMultiState = 0;

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


    /**
     * @brief Listener on multiiconfontstate button.
     *
     * Count the states, wraparound at the end. Set the button's state accordingly.
     */
    public void buttonMultiIconFontStatePressed(PDEEvent event)
    {
        // count state, wraparound
        mIconFontMultiState = mIconFontMultiState + 1;
        if (mIconFontMultiState >= 5) mIconFontMultiState = 0;

        // set the state
        switch (mIconFontMultiState) {
            case 0: mButtonIconFontMultiState.setMainState(PDEButton.PDEButtonStateDefault);
                break;
            case 1: mButtonIconFontMultiState.setMainState("state1");
                break;
            case 2: mButtonIconFontMultiState.setMainState("state2");
                break;
            case 3: mButtonIconFontMultiState.setMainState("state3");
                break;
            case 4: mButtonIconFontMultiState.setMainState("state4");
                break;
        }
    }


    public void buttonCheckboxPressed(PDEEvent event){
        int index;

        // determine which checkbox
        index = ((Integer)((View)event.getSender()).getTag());

        // action depending on checkbox
        if(index==0){
            mButtonCheckbox1.setSelected(!mButtonCheckbox1.isSelected());
        } else {
            mButtonCheckbox2.setSelected(!mButtonCheckbox2.isSelected());
        }
    }

    public void buttonRadioPressed(PDEEvent event){


        // get RadioState from tag
        mRadioState = ((Integer)((View)event.getSender()).getTag());

        // set Radio button states
        mButtonRadio1.setSelected(mRadioState==0);
        mButtonRadio2.setSelected(mRadioState==1);
    }


    public void buttonCheckboxSmallPressed(PDEEvent event){
       ((PDEButton)event.getSender()).setSelected(!((PDEButton) event.getSender()).isSelected());
    }
}
