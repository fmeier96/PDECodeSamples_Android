/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdetext;


// imports
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.R.layout;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputFieldEvent;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


/**
 * @brief Activity for sample screen to show different modes of inputfield via xml attributes.
 */
public class InputFieldXmlShowcaseActivity extends PDEActivity {

    /**
     * @brief Global tag for log outputs.
     */
	@SuppressWarnings("unused")
    private final static String LOG_TAG = InputFieldXmlShowcaseActivity.class.getName();

    // well known state strings
    //
    private final String InputFieldStateEmailValid = "valid";
    private final String InputFieldStateEmailInvalid = "invalid";

    // inputfield sample variables
    private PDEInputField mSearchInputField;
    private PDEInputField mMailInputField;


    //Enum for the mail validation
   enum InputFieldValidationState {
        InputFieldValidationStateCheckFirstLetter,
        InputFieldValidationStateCheckAt,
        InputFieldValidationStateCheckLettersBehindAt,
        InputFieldValidationStateCheckPoint,
        InputFieldValidationStateCheckFirstLetterBehindPoint,
        InputFieldValidationStateCheckSecondLetterBehindPoint,
        InputFieldValidationStateCheckIsValid,
        InputFieldValidationStateCheckIsInvalid
    }

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

        setContentView(layout.inputfield_xml_showcase_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.inputfield_showcase_relativelayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        //
        //--------------------------------------------------------------------------------------------------------------
        // Get our search inputField to react on keyboard search button
        //--------------------------------------------------------------------------------------------------------------
        mSearchInputField  = (PDEInputField)findViewById(R.id.search_pdeInputField);
        mSearchInputField.addListener(this,"onInputFieldEventFromAgentController",PDEInputField.PDEInputFieldEventMask);


        //
        //--------------------------------------------------------------------------------------------------------------
        // Get our e-mail inputField and set colors for email checking
        //--------------------------------------------------------------------------------------------------------------

        mMailInputField = (PDEInputField)findViewById(R.id.mail_pdeInputField);
        // add parameter information for validation of email input
        mMailInputField.mergeParameter(PDEButton.PDEButtonParameterColor,"DTFunctionalRed",InputFieldStateEmailInvalid);
        mMailInputField.mergeParameter(PDEButton.PDEButtonParameterColor,"DTFunctionalGreen",InputFieldStateEmailValid);
        mMailInputField.addListener(this, "onInputFieldEventFromAgentController", PDEInputField.PDEInputFieldEventMask);
    }


    /**
     *@brief called on changes from AgentController
     */
    public void onInputFieldEventFromAgentController(PDEEvent event) {
        PDEInputFieldEvent inputEvent = (PDEInputFieldEvent)event;

        //check field sended the events
        if(mSearchInputField==inputEvent.getSender()) {
            // if text did changed check if its a valid mail or not
            if( inputEvent.isType(PDEInputField.PDEInputFieldEventActionShouldDoEditorAction)) {
                if(inputEvent.getActionId()== EditorInfo.IME_ACTION_SEARCH) {
                    //do your search here!!!
                    inputEvent.setShouldDoAction(true);
                }
            }
        }
        else  if(mMailInputField==inputEvent.getSender()) {
            // if text did changed check if its a valid mail or not
            if( inputEvent.isType(PDEInputField.PDEInputFieldEventActionAfterTextChanged)) {
                // if there is a text -> validate and set new stat
                if(inputEvent.getCurrentText().length()>0) {
                    if(checkValidMail(inputEvent.getCurrentText())) {
                        mMailInputField.setMainState(InputFieldStateEmailValid);
                    } else {
                        mMailInputField.setMainState(InputFieldStateEmailInvalid);
                    }
                }
                // there is no text, go back to default state
                else {
                    mMailInputField.setMainState(PDEButton.PDEButtonStateDefault);
                }
            }
            // if text was cleared, go back to default state
            else if (inputEvent.isType(PDEInputField.PDEInputFieldEventActionDidClearText)) {
                mMailInputField.setMainState(PDEButton.PDEButtonStateDefault);
            }
        }
    }


    /**
     *@brief Check if a String is a valid Email Address
     */
    private boolean checkValidMail(CharSequence inputString) {
        // valid?
        if(inputString==null) return false;

        int i;
        InputFieldValidationState state = InputFieldValidationState.InputFieldValidationStateCheckFirstLetter;
        int stringLength=inputString.length();
        char letter;

        for (i=0; i<stringLength; i++) {

            letter=inputString.charAt(i);

            switch (state) {
                //first Letter must not be a ' ' or a '@'
                case InputFieldValidationStateCheckFirstLetter:
                    if(letter == ' ')state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else if(letter == '@')state = InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else state= InputFieldValidationState.InputFieldValidationStateCheckAt;
                    break;
                //there can be several more letters before the '@'
                case InputFieldValidationStateCheckAt:
                    if(letter =='@')state= InputFieldValidationState.InputFieldValidationStateCheckLettersBehindAt;
                    else if(letter ==' ')state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    break;
                //after ‘@‘ a letter has to follow it
                case InputFieldValidationStateCheckLettersBehindAt:
                    if(letter =='@')state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else if(letter ==' ')state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else if(letter=='.')state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else state= InputFieldValidationState.InputFieldValidationStateCheckPoint;
                    break;
                //there can be several more letters before the '.'
                case InputFieldValidationStateCheckPoint:
                    if(letter =='@')state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else if(letter ==' ')state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else if(letter=='.')state= InputFieldValidationState.InputFieldValidationStateCheckFirstLetterBehindPoint;
                    break;
                //after the '.' there must follow at least two letters out of a...z and A...Z
                case InputFieldValidationStateCheckFirstLetterBehindPoint:
                    //Test if letter is Ascii A...Z or a...z
                    if(!((letter>64&&letter<91)||(letter>96&&letter<123)))state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else state= InputFieldValidationState.InputFieldValidationStateCheckSecondLetterBehindPoint;
                    break;
                case InputFieldValidationStateCheckSecondLetterBehindPoint:
                    //Test if letter is Ascii A...Z or a...z
                    if(!((letter>64&&letter<91)||(letter>96&&letter<123)))state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    else state = InputFieldValidationState.InputFieldValidationStateCheckIsValid;
                    break;

                case InputFieldValidationStateCheckIsValid:
                    //Test if letter is Ascii A...Z or a...z
                    if(!((letter>64&&letter<91)||(letter>96&&letter<123)))state= InputFieldValidationState.InputFieldValidationStateCheckIsInvalid;
                    break;
                case InputFieldValidationStateCheckIsInvalid:
                    return false;
                default:
                    break;
            }
        }

        if(state == InputFieldValidationState.InputFieldValidationStateCheckIsValid) return true;
        else return false;
    }

}
