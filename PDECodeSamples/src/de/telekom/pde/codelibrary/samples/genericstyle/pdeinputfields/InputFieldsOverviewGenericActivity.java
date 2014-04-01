/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdeinputfields;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.ScrollView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputFieldEvent;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


public class InputFieldsOverviewGenericActivity extends PDEActionBarActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = InputFieldsOverviewGenericActivity.class.getName();

    // well known state strings
    //
    private final String MailFieldStateEmailValid = "valid";
    private final String MailFieldStateEmailInvalid = "invalid";

    // inputField sample variables
    private PDEInputField mSearchInputField;
    private PDEInputField mMailInputField;
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    //Enum for the mail validation
    enum MailFieldValidationState {
        MailFieldValidationStateCheckFirstLetter,
        MailFieldValidationStateCheckAt,
        MailFieldValidationStateCheckLettersBehindAt,
        MailFieldValidationStateCheckPoint,
        MailFieldValidationStateCheckFirstLetterBehindPoint,
        MailFieldValidationStateCheckSecondLetterBehindPoint,
        MailFieldValidationStateCheckIsValid,
        MailFieldValidationStateCheckIsInvalid
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (!TextUtils.isEmpty(text)){
                if (PDEString.contains(text.toUpperCase(), "haptic".toUpperCase()))  {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(), "flat".toUpperCase())) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            setContentView(R.layout.inputfields_overview_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Inputfields overview");
        } else {
            setContentView(R.layout.inputfields_overview_flat_screen);
            getSupportActionBar().setTitle("Flat style/Inputfields overview");
        }

        //get the root view and set background color (different when darkstyle is on or of in library)
        ScrollView rootView = (ScrollView)findViewById(R.id.inputfield_root_scrollview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //
        //--------------------------------------------------------------------------------------------------------------
        // Get our search inputField to react on keyboard search button
        //--------------------------------------------------------------------------------------------------------------
        mSearchInputField  = (PDEInputField)findViewById(R.id.search_pdeInputField);
        mSearchInputField.addListener(this,"onInputFieldEventFromAgentController",
                PDEInputField.PDE_INPUTFIELD_EVENT_MASK_ACTION);


        //
        //--------------------------------------------------------------------------------------------------------------
        // Get our e-mail inputField and set colors for email checking
        //--------------------------------------------------------------------------------------------------------------

        mMailInputField = (PDEInputField)findViewById(R.id.mail_pdeInputField);
        // add parameter information for validation of email input
        mMailInputField.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalRed", MailFieldStateEmailInvalid);
        mMailInputField.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalGreen", MailFieldStateEmailValid);
        mMailInputField.addListener(this, "onInputFieldEventFromAgentController", PDEInputField.PDE_INPUTFIELD_EVENT_MASK_ACTION);
    }


    /**
     *@brief called on changes from AgentController
     */
    @SuppressWarnings("unused")
    public void onInputFieldEventFromAgentController(PDEEvent event) {
        PDEInputFieldEvent inputEvent = (PDEInputFieldEvent)event;

        //check which field was sending the events
        if (mSearchInputField == inputEvent.getSender()) {
            // if text did changed check if its a valid mail or not
            if (inputEvent.isType(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_SHOULD_DO_EDITOR_ACTION)) {
                if (inputEvent.getActionId() == EditorInfo.IME_ACTION_SEARCH) {
                    //do your search here!!!
                    inputEvent.setShouldDoAction(true);
                }
            }
        } else if (mMailInputField == inputEvent.getSender()) {
            // if text did changed check if its a valid mail or not
            if (inputEvent.isType(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_AFTER_TEXT_CHANGED)) {
                // if there is a text -> validate and set new stat
                if (inputEvent.getCurrentText().length() > 0) {
                    if (checkValidMail(inputEvent.getCurrentText())) {
                        mMailInputField.setMainState(MailFieldStateEmailValid);
                    } else {
                        mMailInputField.setMainState(MailFieldStateEmailInvalid);
                    }
                } else {
                    // there is no text, go back to default state
                    mMailInputField.setMainState(PDEButton.PDEButtonStateDefault);
                }
            } else if (inputEvent.isType(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_DID_CLEAR_TEXT)) {
                // if text was cleared, go back to default state
                mMailInputField.setMainState(PDEButton.PDEButtonStateDefault);
            }
        }
    }


    /**
     *@brief Check if a String is a valid Email Address
     */
    private boolean checkValidMail(CharSequence inputString) {
        // valid?
        if (inputString == null) return false;

        int i;
        MailFieldValidationState state = MailFieldValidationState.MailFieldValidationStateCheckFirstLetter;
        int stringLength=inputString.length();
        char letter;

        for (i = 0; i < stringLength; i++) {

            letter = inputString.charAt(i);

            switch (state) {
                //first Letter must not be a ' ' or a '@'
                case MailFieldValidationStateCheckFirstLetter:
                    if (letter == ' ') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else if (letter == '@') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else {
                        state = MailFieldValidationState.MailFieldValidationStateCheckAt;
                    }
                    break;
                case MailFieldValidationStateCheckAt:
                    //there can be several more letters before the '@'
                    if (letter == '@') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckLettersBehindAt;
                    } else if (letter == ' ') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    }
                    break;
                //after ‘@‘ a letter has to follow it
                case MailFieldValidationStateCheckLettersBehindAt:
                    if (letter =='@') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else if (letter == ' ') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else if (letter == '.') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else {
                        state = MailFieldValidationState.MailFieldValidationStateCheckPoint;
                    }
                    break;
                //there can be several more letters before the '.'
                case MailFieldValidationStateCheckPoint:
                    if (letter == '@') {
                        state= MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else if (letter == ' ') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else if (letter == '.') {
                        state = MailFieldValidationState.MailFieldValidationStateCheckFirstLetterBehindPoint;
                    }
                    break;
                //after the '.' there must follow at least two letters out of a...z and A...Z
                case MailFieldValidationStateCheckFirstLetterBehindPoint:
                    // Test if letter is Ascii A...Z or a...z
                    if (!((letter > 64 && letter < 91) || (letter > 96 && letter < 123))) {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else {
                        state = MailFieldValidationState.MailFieldValidationStateCheckSecondLetterBehindPoint;
                    }
                    break;
                case MailFieldValidationStateCheckSecondLetterBehindPoint:
                    // Test if letter is Ascii A...Z or a...z
                    if (!((letter > 64 && letter < 91) || (letter > 96 && letter < 123))) {
                        state= MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    } else {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsValid;
                    }
                    break;
                case MailFieldValidationStateCheckIsValid:
                    // Test if letter is Ascii A...Z or a...z
                    if (!((letter > 64 && letter < 91) || (letter > 96 && letter < 123))) {
                        state = MailFieldValidationState.MailFieldValidationStateCheckIsInvalid;
                    }
                    break;
                case MailFieldValidationStateCheckIsInvalid:
                    return false;
                default:
                    break;
            }
        }

        return (state == MailFieldValidationState.MailFieldValidationStateCheckIsValid);
    }
}