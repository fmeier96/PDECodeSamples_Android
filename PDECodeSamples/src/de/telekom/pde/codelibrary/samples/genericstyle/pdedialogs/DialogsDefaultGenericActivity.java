/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdedialogs;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.components.dialog.PDEDialog;
import de.telekom.pde.codelibrary.ui.components.lists.PDEEventListItem;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListItem;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import java.util.ArrayList;


//----------------------------------------------------------------------------------------------------------------------
// DialogsDefaultGenericActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief This example lists all predefined default dialogs.
 */
public class DialogsDefaultGenericActivity extends PDEActionBarActivity {
    // the pde list view
    private PDEListView mList;
    // make array with ids of our target views (sub views of the list item layout)
    private int[] mTargetViewIDs = new int[] {R.id.PDEList_ItemText};
    // list of dialog titles
    private ArrayList<String> mTitleList = new ArrayList<String>();
    // style flag
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
    // the dialog
    PDEDialog mDialog;

    /**
     * @brief onCreate
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // find out current style
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

        // adapt actionbar to current style
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Dialogs");
        } else {
            getSupportActionBar().setTitle("Flat style/Dialogs");
        }

        // init
        mDialog = null;

        // set content view
        setContentView(R.layout.pde_list_activity);
        // get pde list view
        mList = (PDEListView) findViewById(R.id.pde_list);
        // add Listener
        mList.addListener(this,"onPDEListItemClicked");
    }


    /**
     * @brief Create adapter for current size on resume.
     */
    @Override
    protected void onResume (){
        super.onResume();
        // create new adapter
        if (mList.getAdapter() == null){
            setAdapter();
        }
    }


    /**
     * @brief Creates and sets an Adapter.
     *
     */
    private void setAdapter() {
        // fill title array if empty
        if (mTitleList.size() == 0){
            for (String key: PDEDialog.getPDEDialogIdentifiers().keySet()) {
                mTitleList.add(key);
            }
        }

        // create new adapter
        DialogsDefaultGenericListAdapter adapter = new DialogsDefaultGenericListAdapter(
                this, R.layout.pde_list_plain_text_single_line_medium_row, mTargetViewIDs, mTitleList);

        // Set the adapter in our list
        mList.setAdapter(adapter);
    }


    /**
     * @brief Listener for PDE List events.
     */
    @SuppressWarnings("unused")
    public void onPDEListItemClicked(PDEEvent event){
        PDEEventListItem listEvent = (PDEEventListItem) event;

        // open the selected dialog
        if (TextUtils.equals(listEvent.getType(), PDEListItem.PDE_LIST_ITEM_EVENT_ACTION_SELECTED)){
            // check if there is already a dialog shown and dismiss it
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            // get key that shows the type of dialog that should be opened
            String key = (String)mList.getItemAtPosition(listEvent.getListPosition());
            // construct the desired type of dialog
            mDialog = PDEDialog.constructDialog(key).setStyleCustom(mStyle)
                // set standard android click listener for dialog button 1
                .setOnClickListenerButton1(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogsDefaultGenericActivity.this, "Selected : Button 1",
                                       Toast.LENGTH_SHORT).show();
                    }
                })
                // set standard android click listener for dialog button 2
                .setOnClickListenerButton2(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogsDefaultGenericActivity.this, "Selected : Button 2",
                                       Toast.LENGTH_SHORT).show();
                    }
                })
                // set standard android dialog click listener for android hardware back-button
                .setOnClickListenerAndroidHardwareBackButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogsDefaultGenericActivity.this, "Selected : Android Hardware Back-Button",
                                       Toast.LENGTH_SHORT).show();
                    }
                });
            // some default dialog types have wildcards within their texts; fill in proper values for the wildcards.
            addExampleDialogParameters(mDialog,key);
            // after all the configuration work show the dialog
            mDialog.show(this);
        }
    }


    /**
     * @brief Set proper values for the wildcards within the dialog texts.
     *
     * Some dialogs have wildcards within their titles / messages in order to be able to fill in values which are
     * adequate for the given situation. For the purpose of this example screen we fill in some example values here.
     *
     * @param dialog the dialog object
     * @param key the key that describes the dialog type
     */
    protected void addExampleDialogParameters(PDEDialog dialog, String key){
        // check if the current dialog type is one of those with wildcards and fill in proper values if needed.
        if (PDEString.isEqual(key,"ITEM_NOT_FOUND")){
           dialog.setTitleFormatParameters(PDEUtils.loadStringFromResources("ITEM_NOT_FOUND_title_parameter"));
           dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("ITEM_NOT_FOUND_message_parameter"));
        } else if (PDEString.isEqual(key,"CONNECTION_TO_FAILED")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("CONNECTION_TO_FAILED_message_parameter"));
        } else if (PDEString.isEqual(key,"LOGIN_ERROR_ACCOUNT_BLOCKED")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("LOGIN_ERROR_ACCOUNT_BLOCKED_message_parameter"));
        } else if (PDEString.isEqual(key,"APP_UPDATE_REQUIRED")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("APP_UPDATE_REQUIRED_message_parameter1"),
                                              PDEUtils.loadStringFromResources("APP_UPDATE_REQUIRED_message_parameter2"));
        } else if (PDEString.isEqual(key,"LOGIN_UNAVAILABLE_TARPIT_TIME")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("LOGIN_UNAVAILABLE_TARPIT_TIME_message_parameter"));
        } else if (PDEString.isEqual(key,"MISSING_FLASH")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("MISSING_FLASH_message_parameter"));
        } else if (PDEString.isEqual(key,"INVALID_CHARACTERS_USED")){
            dialog.setTitleFormatParameters(PDEUtils.loadStringFromResources("INVALID_CHARACTERS_USED_title_parameter"));
        } else if (PDEString.isEqual(key,"VALID_CHARACTER_SET")){
            dialog.setTitleFormatParameters(PDEUtils.loadStringFromResources("VALID_CHARACTER_SET_title_parameter"));
        } else if (PDEString.isEqual(key,"LOGIN_FAILED_TARPIT_TIME")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("LOGIN_FAILED_TARPIT_TIME_message_parameter"));
        } else if (PDEString.isEqual(key,"APP_LOAD_NETWORK_ERROR")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("APP_LOAD_NETWORK_ERROR_message_parameter"));
        } else if (PDEString.isEqual(key,"LOGIN_FAILED_ACCOUNT_LOCKED")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("LOGIN_FAILED_ACCOUNT_LOCKED_message_parameter"));
        } else if (PDEString.isEqual(key,"ITEM_EXISTS")){
            dialog.setTitleFormatParameters(PDEUtils.loadStringFromResources("ITEM_EXISTS_title_parameter"));
        } else if (PDEString.isEqual(key,"EMPTY_ITEM_NAME")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("EMPTY_ITEM_NAME_message_parameter"));
        } else if (PDEString.isEqual(key,"NETWORK_CHANGED")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("NETWORK_CHANGED_message_parameter"));
        } else if (PDEString.isEqual(key,"TECHNICAL_ERROR")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("TECHNICAL_ERROR_message_parameter1"),
                                              PDEUtils.loadStringFromResources("TECHNICAL_ERROR_message_parameter2"));
        } else if (PDEString.isEqual(key,"APP_GET_FROM_STORE")){
            dialog.setTitleFormatParameters(PDEUtils.loadStringFromResources("APP_GET_FROM_STORE_title_parameter1"),
                                            PDEUtils.loadStringFromResources("APP_GET_FROM_STORE_title_parameter2"));
        } else if (PDEString.isEqual(key,"APP_FUNCTION_NOT_POSSIBLE")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("APP_FUNCTION_NOT_POSSIBLE_message_parameter"));
        } else if (PDEString.isEqual(key,"APP_LOAD_ERROR")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("APP_LOAD_ERROR_message_parameter"));
        }  else if (PDEString.isEqual(key,"SERVICE_TEMPORARILY_UNAVAILABLE")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("SERVICE_TEMPORARILY_UNAVAILABLE_message_parameter"));
        } else if (PDEString.isEqual(key,"FUNCTION_UNAVAILABLE_BECAUSE")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("FUNCTION_UNAVAILABLE_BECAUSE_message_parameter"));
        } else if (PDEString.isEqual(key,"EMAIL_RECIPIENT_UNKNOWN")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("EMAIL_RECIPIENT_UNKNOWN_message_parameter1"),
                                              PDEUtils.loadStringFromResources("EMAIL_RECIPIENT_UNKNOWN_message_parameter2"));
        } else if (PDEString.isEqual(key,"SEND_EMAIL_FAILED")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("SEND_EMAIL_FAILED_message_parameter"));
        } else if (PDEString.isEqual(key,"MISSING_MEMORY_CARD")){
            dialog.setMessageFormatParameters(PDEUtils.loadStringFromResources("MISSING_MEMORY_CARD_message_parameter"));
        }

    }
}