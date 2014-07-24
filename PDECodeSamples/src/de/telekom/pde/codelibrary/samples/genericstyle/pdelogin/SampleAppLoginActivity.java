/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2013. Neuland Multimedia GmbH.
 */
package de.telekom.pde.codelibrary.samples.genericstyle.pdelogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputField;
import de.telekom.pde.codelibrary.ui.components.inputfields.PDEInputFieldEvent;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;
import de.telekom.pde.codelibrary.ui.modules.login.PDEBaseLoginScreenActivity;


/**
 * @brief Sample activity which shows a login screen based on PDEBaseLoginScreenActivity.
 *
 * The screen features client side test of a email address. This test is only intended to show how a feedback of the
 * user input can be implemented with the PDEInputField, the algorithm for the email check is sophisticated enough to
 * use it as it is in a released product.
 * The test of the password only checks that there are 6 characters entered.
 * No user information is shown to give the user some hint what is wrong.
 */
public class SampleAppLoginActivity extends PDEBaseLoginScreenActivity {

    //Enum for the mail validation
    //
    protected enum EmailValidationState {
        EmailValidationStateCheckFirstLetter,
        EmailValidationStateCheckAt,
        EmailValidationStateCheckLettersBehindAt,
        EmailValidationStateCheckPoint,
        EmailValidationStateCheckFirstLetterBehindPoint,
        EmailValidationStateCheckSecondLetterBehindPoint,
        EmailValidationStateCheckIsValid,
        EmailValidationStateCheckIsInvalid
    }


    // email state strings
    //
    protected final String EmailStateEmailValid = "valid";
    protected final String EmailStateEmailInvalid = "invalid";

    // password state strings
    //
    protected final String PasswordStateValid = "valid";
    protected final String PasswordStateInvalid = "invalid";


    public void onCreate(Bundle savedInstanceState) {

        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (text != null && text.length() != 0) {
                if (PDEString.contains(text.toUpperCase(Locale.US), "haptic".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(Locale.US), "flat".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        super.onCreate(savedInstanceState);

        setDescriptionHTML(getResources().getString(R.string.sampleapp_login_description));

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Sample login screen");
        } else {
            getSupportActionBar().setTitle("Flat style/Sample login screen");
        }

        ((TextView) findViewById(R.id.LoginScreenUsernameLabel)).setText(getResources()
                                                                                 .getString(R.string.sampleapp_login_username_label));

        mUsernameInputField.mergeParameter(PDEButton.PDEButtonParameterColor,
                                           "DTFunctionalRed",
                                           EmailStateEmailInvalid);
        mUsernameInputField.mergeParameter(PDEButton.PDEButtonParameterColor,
                                           "DTFunctionalGreen",
                                           EmailStateEmailValid);
        mUsernameInputField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        mUsernameInputField.setHint(getResources().getString(R.string.sampleapp_login_username_input_field_hint));

        mPasswordInputField.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalRed", PasswordStateInvalid);
        mPasswordInputField.mergeParameter(PDEButton.PDEButtonParameterColor, "DTFunctionalGreen", PasswordStateValid);

        setRegisterHereHTML(getResources().getString(R.string.login_screen_register_now_link));
        setForgotPasswordHTML(getResources().getString(R.string.login_screen_password_forgotten_link));
        setRegisterAreaVisible(false);
        setTBrandLogoVisible(false);
    }


    /**
     * @param email        entered email address
     * @param password     entered password
     * @param staySignedIn checkbox clicked?
     * @brief Overwrite from PDEBaseLoginScreenActivity - handle the real login check from here.
     */
    @Override
    public void loginButtonClicked(final String email, final String password, final boolean staySignedIn) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SampleAppLoginActivity.this, "login button clicked email: " + email
                                                            + "\n password: " + password + " staySignedIn: " + (
                                       staySignedIn
                                       ? "true"
                                       : "false"),
                               Toast.LENGTH_SHORT)
                     .show();
            }
        });
    }


    /**
     * @brief Callback for email input field changes by AgentController.
     */
    @SuppressWarnings("unused")
    public void onInputFieldEmailChanged(final PDEEvent event) {
        final PDEInputFieldEvent inputEvent = (PDEInputFieldEvent) event;

        // if text did changed check if its a valid mail or not
        if (inputEvent.isType(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_AFTER_TEXT_CHANGED)) {
            // if there is a text -> validate and set new stat
            if (inputEvent.getCurrentText().length() > 0) {
                if (checkValidUsername(inputEvent.getCurrentText().toString())) {
                    mUsernameInputField.setMainState(EmailStateEmailValid);
                } else {
                    mUsernameInputField.setMainState(EmailStateEmailInvalid);
                }
            }
            // there is no text, go back to default state
            else {
                mUsernameInputField.setMainState(PDEButton.PDEButtonStateDefault);
            }
            updateLoginState();
        } else if (inputEvent.isType(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_DID_CLEAR_TEXT)) {
            // if text was cleared, go back to default state
            mUsernameInputField.setMainState(PDEButton.PDEButtonStateDefault);
            updateLoginState();
        }
    }


    /**
     * @brief Callback for password input field changes by AgentController.
     *
     * Called via reflection.
     */
    @SuppressWarnings("unused")
    public void onInputFieldPasswordChanged(final PDEEvent event) {
        final PDEInputFieldEvent inputEvent = (PDEInputFieldEvent) event;

        // if text did changed check if its a valid mail or not
        if (inputEvent.isType(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_AFTER_TEXT_CHANGED)) {
            if (inputEvent.getCurrentText().length() > 0) {
                if (checkValidPassword(inputEvent.getCurrentText().toString())) {
                    mPasswordInputField.setMainState(PasswordStateValid);
                } else {
                    mPasswordInputField.setMainState(PasswordStateInvalid);
                }
            } else {
                mPasswordInputField.setMainState(PDEButton.PDEButtonStateDefault);
            }
            updateLoginState();
        } else if (inputEvent.isType(PDEInputField.PDE_INPUTFIELD_EVENT_ACTION_DID_CLEAR_TEXT)) {
            mPasswordInputField.setMainState(PDEButton.PDEButtonStateDefault);
            // if text was cleared, go back to default state
            updateLoginState();
        }
    }


    /**
     * @brief Check if a string is a valid email address.
     *
     * A more sophisticated algorithm is needed here. This is only an example that some validation is done.
     */
    protected boolean checkValidUsername(final String inputString) {
        // valid?
        if (inputString == null) return false;

        char letter;
        EmailValidationState state;

        // initialize state machine
        state = EmailValidationState.EmailValidationStateCheckFirstLetter;
        final int stringLength = inputString.length();

        // go through the string
        for (int i = 0; i < stringLength; i++) {

            letter = inputString.charAt(i);

            switch (state) {
                case EmailValidationStateCheckFirstLetter:
                    //first Letter must not be a ' ' or a '@'
                    if (letter == ' ') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else if (letter == '@') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else {
                        state = EmailValidationState.EmailValidationStateCheckAt;
                    }
                    break;
                case EmailValidationStateCheckAt:
                    //there can be several more letters before the '@'
                    if (letter == '@') {
                        state = EmailValidationState.EmailValidationStateCheckLettersBehindAt;
                    } else if (letter == ' ') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    }
                    break;
                case EmailValidationStateCheckLettersBehindAt:
                    //after ‘@‘ a letter has to follow it
                    if (letter == '@') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else if (letter == ' ') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else if (letter == '.') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else {
                        state = EmailValidationState.EmailValidationStateCheckPoint;
                    }
                    break;
                case EmailValidationStateCheckPoint:
                    //there can be several more letters before the '.'
                    if (letter == '@') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else if (letter == ' ') {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else if (letter == '.') {
                        state = EmailValidationState.EmailValidationStateCheckFirstLetterBehindPoint;
                    }
                    break;
                case EmailValidationStateCheckFirstLetterBehindPoint:
                    //after the '.' there must follow at least two letters out of a...z and A...Z
                    //Test if letter is Ascii A...Z or a...z
                    if (!((letter > 64 && letter < 91)
                          || (letter > 96 && letter < 123))) {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    } else {
                        state = EmailValidationState.EmailValidationStateCheckSecondLetterBehindPoint;
                    }
                    break;
                case EmailValidationStateCheckSecondLetterBehindPoint:
                    //Test if letter is Ascii A...Z or a...z
                    if (!((letter > 64 && letter < 91)
                          || (letter > 96 && letter < 123))) {
                        state = EmailValidationState.EmailValidationStateCheckPoint;
                    } else {
                        state = EmailValidationState.EmailValidationStateCheckIsValid;
                    }
                    break;
                case EmailValidationStateCheckIsValid:
                    if (letter == '.') {
                        state = EmailValidationState.EmailValidationStateCheckFirstLetterBehindPoint;
                    } else if (!((letter > 64 && letter < 91) //Test if letter is Ascii A...Z or a...z
                                 || (letter > 96 && letter < 123))) {
                        state = EmailValidationState.EmailValidationStateCheckIsInvalid;
                    }
                    break;
                case EmailValidationStateCheckIsInvalid:
                    return false;
                default:
                    break;
            }
        }

        // depending on the state return true or false
        return state == EmailValidationState.EmailValidationStateCheckIsValid;
    }


    /**
     * @brief Check password validity.
     *
     * Currently is is only checked that the username is at least 6 characters long
     */
    protected boolean checkValidPassword(final String inputString) {
        // valid?
        if (TextUtils.isEmpty(inputString)) {
            return false;
        } else {
            return (inputString.length() >= 6);
        }
    }


    /**
     * @brief Update login button state.
     *
     * Login is only allowed if mail is valid, and if password contains at least 6 characters.
     */
    protected void updateLoginState() {
        // check (which is not sophisticated)
        if (checkValidUsername(mUsernameInputField.getText().toString())
            && mPasswordInputField.getText().length() >= 6) {
            // show fully (not animated yet, so set directly)
            mButtonLogin.setEnabled(true);
            PDEUtils.setViewAlpha(mButtonLogin, 1.f);
        } else {
            // hide fully (not animated yet)
            mButtonLogin.setEnabled(false);
            PDEUtils.setViewAlpha(mButtonLogin, .5f);
        }
    }


}