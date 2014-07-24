/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdedialogs;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Locale;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDECodeLibrary;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.dialog.PDEDialog;
import de.telekom.pde.codelibrary.ui.components.dialog.PDEEventDialog;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;


//----------------------------------------------------------------------------------------------------------------------
// DialogsProgrammingSampleGenericActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief DialogsProgrammingSampleGenericActivity - Programming examples for the use of PDEDialogs.
 */
public class DialogsProgrammingSampleGenericActivity extends PDEActionBarActivity {

    // buttons
    private PDEButton mDefaultButton1, mDefaultButton2, mDefaultButton3;
    private PDEButton mCustomButton1, mCustomButton2, mCustomButton3;

    // style
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    /**
     * @brief onCreate
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // find out current style
        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (text != null && !TextUtils.isEmpty(text)) {
                // doubled check - stupid, but removes warning
                if (PDEString.contains(text.toUpperCase(Locale.US), "haptic".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(Locale.US), "flat".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        // set content view
        setContentView(R.layout.dialogs_programming_sample_generic_screen);


        // load buttons
        mDefaultButton1 = (PDEButton) findViewById(R.id.button_default_1);
        mDefaultButton2 = (PDEButton) findViewById(R.id.button_default_2);
        mDefaultButton3 = (PDEButton) findViewById(R.id.button_default_3);
        mCustomButton1 = (PDEButton) findViewById(R.id.button_custom_1);
        mCustomButton2 = (PDEButton) findViewById(R.id.button_custom_2);
        mCustomButton3 = (PDEButton) findViewById(R.id.button_custom_3);

        // set listener for buttons
        mDefaultButton1.addListener(this,
                                    "onClickButton",
                                    PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        mDefaultButton2.addListener(this,
                                    "onClickButton",
                                    PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        mDefaultButton3.addListener(this,
                                    "onClickButton",
                                    PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        mCustomButton1.addListener(this,
                                   "onClickButton",
                                   PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        mCustomButton2.addListener(this,
                                   "onClickButton",
                                   PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        mCustomButton3.addListener(this,
                                   "onClickButton",
                                   PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);

        // adapt to current style
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Dialog programming sample");
            mDefaultButton1.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
            mDefaultButton2.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
            mDefaultButton3.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
            mCustomButton1.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
            mCustomButton2.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
            mCustomButton3.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
        } else {
            getSupportActionBar().setTitle("Flat style/Dialog programming sample");
        }
    }


    /**
     * @brief button listener
     */
    @SuppressWarnings("unused")
    public void onClickButton(PDEEvent event) {
        Object sender = event.getSender();

        // security
        if (sender == null) return;

        if (event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)) {
            Resources res = getApplicationContext().getResources();
            if (sender == mDefaultButton1) {
                // Create dialog & deliver default dialog type
                PDEDialog.constructDialog("NETWORK_ERROR")
                        // deliver current style
                        .setStyleCustom(mStyle)
                                // set standard android click listener for first dialog button
                        .setOnClickListenerButton1(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the first button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_BUTTON1) {
                                    showToast("Selected : Button1");
                                }
                            }
                        })
                                // set standard android click listener for second dialog button
                        .setOnClickListenerButton2(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the second button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_BUTTON2) {
                                    showToast("Selected : Button2");
                                }
                            }
                        })
                                // set standard android click listener for android hardware back button
                        .setOnClickListenerAndroidHardwareBackButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the android hardware back-button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_ANDROID_HARDWARE_BACK_BUTTON) {
                                    showToast("Selected : Android hardware Back-Button");
                                }
                            }
                        })
                                // start to show the configured dialog
                        .show(this);
            } else if (sender == mDefaultButton2) {
                // Create dialog & deliver default dialog type
                PDEDialog.constructDialog("LOGIN_FAILED_TARPIT_TIME")
                        // deliver current style
                        .setStyleCustom(mStyle)
                                // the default dialog has a wildcard within the text in order to be able to insert variable text at this position.
                                // set the current text for the wildcard here
                        .setMessageFormatParameters(PDEUtils.loadStringFromResources(
                                "LOGIN_UNAVAILABLE_TARPIT_TIME_message_parameter"))
                                // set standard android click listener for first dialog button
                        .setOnClickListenerButton1(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the first button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_BUTTON1) {
                                    showToast("Selected : Button1");
                                }
                            }
                        })
                                // set standard android click listener for android hardware back button
                        .setOnClickListenerAndroidHardwareBackButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the android hardware back-button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_ANDROID_HARDWARE_BACK_BUTTON) {
                                    showToast("Selected : Android hardware Back-Button");
                                }
                            }
                        })
                                // start to show the configured dialog
                        .show(this);
            } else if (sender == mDefaultButton3) {
                // Create dialog & deliver default dialog type
                PDEDialog.constructDialog("APP_UPDATE_REQUIRED")
                        // deliver current style
                        .setStyleCustom(mStyle)
                                // set texts for the wildcards (like in the example above)
                        .setMessageFormatParameters(PDEUtils.loadStringFromResources(
                                                            "APP_UPDATE_REQUIRED_message_parameter1"),
                                                    PDEUtils.loadStringFromResources(
                                                            "APP_UPDATE_REQUIRED_message_parameter2"))
                                // start to show the configured dialog
                        .show(this)
                                // add click listener that operates with our PDEEvents
                        .addListener(this, "onPDEDialogClickResult");
            } else if (sender == mCustomButton1) {
                // Create empty dialog
                PDEDialog.constructDialog()
                        // deliver current style
                        .setStyleCustom(mStyle)
                                // set just a title, no message in this example
                        .setTitleCustom(res.getString(R.string.Custom_Example_1_title))
                                // set also just one button
                        .setButton1TextCustom(res.getString(R.string.Custom_Example_1_button1_label))
                                // set standard android click listener for first dialog button
                        .setOnClickListenerButton1(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the first button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_BUTTON1) {
                                    showToast("Selected : Button1");
                                }
                            }
                        })
                                // set standard android click listener for android hardware back button
                        .setOnClickListenerAndroidHardwareBackButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the android hardware back-button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_ANDROID_HARDWARE_BACK_BUTTON) {
                                    showToast("Selected : Android hardware Back-Button");
                                }
                            }
                        })
                                // start to show the configured dialog
                        .show(this);
            } else if (sender == mCustomButton2) {
                // Create dialog & deliver all texts and labels
                PDEDialog.constructDialog(res.getString(R.string.Custom_Example_2_title),
                                          res.getString(R.string.Custom_Example_2_message),
                                          res.getString(R.string.Custom_Example_2_button1_label),
                                          res.getString(R.string.Custom_Example_2_button2_label)
                )
                        // deliver current style
                        .setStyleCustom(mStyle)
                                // set standard android click listener for first dialog button
                        .setOnClickListenerButton1(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the first button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_BUTTON1) {
                                    showToast("Selected : Button1");
                                }
                            }
                        })
                                // set standard android click listener for second dialog button
                        .setOnClickListenerButton2(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the second button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_BUTTON2) {
                                    showToast("Selected : Button2");
                                }
                            }
                        })
                                // set standard android click listener for android hardware back button
                        .setOnClickListenerAndroidHardwareBackButton(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // check if the android hardware back-button was clicked
                                if (which == PDEDialog.PDE_DIALOG_RESULT_ANDROID_HARDWARE_BACK_BUTTON) {
                                    showToast("Selected : Android hardware Back-Button");
                                }
                            }
                        })
                                // start to show the configured dialog
                        .show(this);
            } else if (sender == mCustomButton3) {
                // Create dialog & deliver all texts and labels
                PDEDialog.constructDialog(res.getString(R.string.Custom_Example_3_title),
                                          res.getString(R.string.Custom_Example_3_message),
                                          res.getString(R.string.Custom_Example_3_button1_label)
                )
                        // deliver current style
                        .setStyleCustom(mStyle)
                                // add a predefined default button
                        .setButton2DefaultButton("dialog_btn_close")
                                // start to show the configured dialog
                        .show(this)
                                // add click listener that operates with our PDEEvents
                        .addListener(this, "onPDEDialogClickResult");
            }
        }
    }


    /**
     * @param event PDEEvent
     * @brief Listener for PDEEvents sent by the dialogs.
     */
    @SuppressWarnings("unused")
    public void onPDEDialogClickResult(PDEEvent event) {
        PDEEventDialog evt = (PDEEventDialog) event;
        String labelID = evt.getButtonResultLabelID();
        if (evt.getButtonResult() == PDEDialog.PDE_DIALOG_RESULT_BUTTON1) {
            showToast("Button1", labelID);
        } else if (evt.getButtonResult() == PDEDialog.PDE_DIALOG_RESULT_BUTTON2) {
            showToast("Button2", labelID);
        } else if (evt.getButtonResult() == PDEDialog.PDE_DIALOG_RESULT_ANDROID_HARDWARE_BACK_BUTTON) {
            showToast("Android Hardware Back-Button", labelID);
        }
    }


    /**
     * @param text the text that should be shown by the toast.
     * @brief Internal helper for showing toasts.
     */
    protected void showToast(String text) {
        Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }


    /**
     * @param button  which button was pressed
     * @param labelID label ID of the pressed button
     * @brief Internal helper for showing toasts.
     */
    protected void showToast(String button, String labelID) {
        String text, label;

        text = "Selected Button: \n";
        text += "Button: " + button + "\n";
        text += "Button Label ID: " + labelID + "\n";
        label = PDEUtils.loadStringFromResources(labelID);
        text += "Button Label: " + label;
        Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
