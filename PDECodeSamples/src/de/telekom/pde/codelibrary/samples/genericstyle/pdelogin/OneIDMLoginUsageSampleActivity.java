/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2013. Neuland Multimedia GmbH.
 *
 * kdanner - 26.06.13 : 14:15 
 */
package de.telekom.pde.codelibrary.samples.genericstyle.pdelogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.helpers.PDEDictionary;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.modules.login.PDEOneIDMLoginScreenActivity;

/**
 * @brief How to use the PDEOneIDMLoginScreenActivity and the PDEOneIDMModule.
 *
 * This example screen shows you how you can use the login module in PDECodeLibrary.
 * At first the screen shows only one button. If pressed the PDEOneIDMLoginScreenActivity is started and the result
 * returned in onActivityResult. When a refresh token is received the second button appears. If the second button is
 * pressed the PDEOneIDMModule instance is used to request a new access token using the refresh token.
 */
public class OneIDMLoginUsageSampleActivity extends PDEActionBarActivity {

    private final static String LOG_TAG = OneIDMLoginUsageSampleActivity.class.getName();

    /**
     * startActivityForResult ID
     */
    private final static int INTENT_REQUEST_CODE_ONE_IDM = 0;
    /**
     * ClientID assigned by OneIDM
     */
    private final static String ONE_IDM_CLIENT_ID = "10TVL0SAM30000004901CODELIBLOGIN00000000";
    /**
     * OneIDM test server url
     */
    private final static String ONE_IDM_URL = "https://accounts.login00.idm.ver.sul.t-online.de/oauth2/tokens";
    // live server
    //private final static String ONE_IDM_URL = "https://logint3.idm.toon.sul.t-online.de/oauth2/tokens";

    /**
     * scope for the test server
     */
    private final static String SERVICE_SCOPE = "spica";

    PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;



    // private members
    private PDEButton mRefreshButton;
    private String mRefreshToken;
    //private PDEOneIDMModule mOneIDM;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init variables
        mRefreshToken = "";


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

        // inflate the xml
        setContentView(R.layout.login_one_idm_usage_sample);

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/OneIDM login");
        } else {
            getSupportActionBar().setTitle("Flat style/OneIDM login");
        }


        // add onClick listener
        PDEButton oneIDMButton = ((PDEButton)findViewById(R.id.experimental_login_chooser_button_oneidm));

        oneIDMButton.addListener(this, "oneIDMButtonClicked", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            oneIDMButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
        }

        // get refresh button and set on click listener
        mRefreshButton = (PDEButton)findViewById(R.id.experimental_login_chooser_button_oneidm_refresh);
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            mRefreshButton.setButtonBackgroundLayerWithLayerType(PDEButton.PDEButtonLayerType.BackgroundHaptic);
        }
        mRefreshButton
                .addListener(this,
                        "oneIDMRefreshButtonClicked",
                        PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);

        findViewById(R.id.experimental_login_chooser_root_view).setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());
    }

    /**
     * @brief onClick function for the IDMButton.
     * @param event Button event
     */
    @SuppressWarnings("unused")
    public void oneIDMButtonClicked(PDEEvent event) {
        Log.d(LOG_TAG, "oneIDMButtonClicked");

        Intent intent = new Intent(this, PDEOneIDMLoginScreenActivity.class);
        // mandatory:
        intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_CLIENT_ID, ONE_IDM_CLIENT_ID);
        intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_SCOPE, SERVICE_SCOPE);
        // might be necessary (I don't know yet):
        intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_URL, ONE_IDM_URL);
        //optional:
        // true is the default... - but we want to show it with the checkbox
        intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_SHOW_STAY_SIGNED_IN_CHECKBOX, true);
        // intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_SHOW_T_BRAND_LOGO, true);
        // intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_USERNAME, "username");
        // intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_USER_PASSWORD, "password");

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            // haptic
            intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_CONTENTSTYLE,
                PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_CONTENTSTYLE_VALUE_HAPTIC);
        } else {
            // flat
            intent.putExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_CONTENTSTYLE,
                    PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_INTENT_EXTRA_CONTENTSTYLE_VALUE_FLAT);
        }
        startActivityForResult(intent, INTENT_REQUEST_CODE_ONE_IDM);
    }


    /**
     * @brief onClick function for the IDM-RefreshToken-Button.
     * @param event Button event
     */
    @SuppressWarnings("unused")
    public void oneIDMRefreshButtonClicked(PDEEvent event) {
        Log.d(LOG_TAG, "oneIDMRefreshButtonClicked");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OneIDMLoginUsageSampleActivity.this, "Start refresh the token with OneIDM!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // since the OneIDMModule is not approved by the Group for Internet Security (GIS) we cannot show it!!

//        boolean result;
//
//        if (mOneIDM == null) {
//            mOneIDM = new PDEOneIDMModule(ONE_IDM_CLIENT_ID, ONE_IDM_URL);
//        }
//
//        mOneIDM.addListener(this, "onReceiveToken");
//        try {
//            result = mOneIDM.requestAccessToken(mRefreshToken, SERVICE_SCOPE);
//            if (!result) {
//                final String fMessage = "Request already active";
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(OneIDMLoginUsageSampleActivity.this);
//
//                        builder.setTitle("Error!")
//                                .setMessage(fMessage)
//                                .setCancelable(false)
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                });
//                        AlertDialog alert = builder.create();
//                        alert.show();
//                    }
//                });
//            }
//        } catch (NetworkErrorException e) {
//            final String fMessage = "No network connection available";
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(OneIDMLoginUsageSampleActivity.this);
//
//                    builder.setTitle("Error!")
//                            .setMessage(fMessage)
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.cancel();
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();
//                }
//            });
//        }
    }


    /**
     * @brief Function is called when the startActivityForResult is returned.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check if it is the right result
        if (requestCode == INTENT_REQUEST_CODE_ONE_IDM) {
            mRefreshToken = "";
            String output = "";

            if(resultCode == RESULT_OK){
                Object result = data.getSerializableExtra(PDEOneIDMLoginScreenActivity.ONE_IDM_LOGIN_SCREEN_RETURNED_INTENT_EXTRA_RESULT);
                if (result instanceof PDEDictionary) {

                    PDEDictionary dict = (PDEDictionary)result;

                    for (String key : dict.keySet()) {
                        output += key +": "+dict.get(key)+"\n";
                        if (TextUtils.equals(key, "refresh_token")) {
                            mRefreshToken = (String) dict.get(key);
                        }
                    }
                }
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                output = "Canceled";
            }

            // do something with the result (here it is only written into a TextView
            ((TextView)findViewById(R.id.experimental_login_chooser_result_text)).setText(output);

            // enable or disable the refresh button - depending if the result contained a refresh
            // token
            if (TextUtils.isEmpty(mRefreshToken)) {
                mRefreshButton.setVisibility(View.GONE);
            } else {
                mRefreshButton.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     * @brief Callback function for PDEOneIDMMode - which is called to return the new token(s) or some error.
     */
    @SuppressWarnings("unused")
    public void onReceiveToken(PDEEvent event) {
        Log.d(LOG_TAG,"onReceiveToken");

        mRefreshToken = "";

        String output = "";

        Object result = event.getResult();

        if (result instanceof PDEDictionary) {

            PDEDictionary dict = (PDEDictionary)result;

            for (String key : dict.keySet()) {
                output += key +": "+dict.get(key)+"\n";
                if (TextUtils.equals(key, "refresh_token")) {
                    mRefreshToken = (String) dict.get(key);
                }
            }
        }

        final String fOutput = output;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.experimental_login_chooser_result_text)).setText(fOutput);
            }
        });
    }
}
