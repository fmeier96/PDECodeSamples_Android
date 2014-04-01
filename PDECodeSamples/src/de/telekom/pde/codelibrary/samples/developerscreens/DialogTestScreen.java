/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.PDECodeLibrary;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.dialog.PDEDialog;
import de.telekom.pde.codelibrary.ui.components.dialog.PDEEventDialog;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;


public class DialogTestScreen extends PDEActionBarActivity {

//    private static final int DIALOG_ALERT = 10;
//    private static final int DIALOG_ALERT2 = 20;

    PDEDialog mDialog;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.dialog_test_screen);




        PDEButton button1, button2;

        button1 = (PDEButton)findViewById(R.id.button_default_1);
        button2 = (PDEButton)findViewById(R.id.button_default_2);

        button1.addListener(this, "onClickButton1", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        button2.addListener(this, "onClickButton2", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);

        mDialog = new PDEDialog("NETWORK_ERROR");
        //mDialog.addListener(this,"onPDEDialogClickResult");
    }




    @SuppressWarnings("unused")
    public void onClickButton1(PDEEvent event) {
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mDialog.setOnClickListenerButton1(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == PDEDialog.PDE_DIALOG_RESULT_BUTTON1){
                        Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(), "Selected : Button1",
                                       Toast.LENGTH_SHORT).show();
                    }
                }
            }).setOnClickListenerButton2(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == PDEDialog.PDE_DIALOG_RESULT_BUTTON2){
                        Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(), "Selected : Button2",
                                       Toast.LENGTH_SHORT).show();
                    }
                }
            }).show(this);
        }
    }



    @SuppressWarnings("unused")
    public void onClickButton2(PDEEvent event) {
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            PDEDialog.constructDialog("LOGIN_FAILED_TARPIT_TIME").setMessageFormatParameters("30 Sekunden")
                    .setOnClickListenerButton1(
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == PDEDialog.PDE_DIALOG_RESULT_BUTTON1) {
                                        Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(),
                                                "Selected : Button1", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).show(this);


        }
    }


    public void onPDEDialogClickResult(PDEEvent event){
        PDEEventDialog evt = (PDEEventDialog) event;
        if(evt.getButtonResult() == PDEDialog.PDE_DIALOG_RESULT_BUTTON1){
            Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(), "Selected : Button1",
                           Toast.LENGTH_SHORT).show();
        } else if(evt.getButtonResult() == PDEDialog.PDE_DIALOG_RESULT_BUTTON2){
            Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(), "Selected : Button2",
                           Toast.LENGTH_SHORT).show();
        } else if(evt.getButtonResult() == PDEDialog.PDE_DIALOG_RESULT_BUTTON3){
            Toast.makeText(PDECodeLibrary.getInstance().getApplicationContext(), "Selected : Button3",
                           Toast.LENGTH_SHORT).show();
        }
    }



//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case DIALOG_ALERT:
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage("This will end the activity");
//                builder.setCancelable(true);
//                builder.setPositiveButton("I agree", new OkOnClickListener());
//                //builder.setNegativeButton("No, no", new CancelOnClickListener());
//                AlertDialog dialog = builder.create();
//                dialog.show();
//        }
//        return super.onCreateDialog(id);
//    }


//    private final class OkOnClickListener implements
//            DialogInterface.OnClickListener {
//        public void onClick(DialogInterface dialog, int which) {
//            Toast.makeText(getApplicationContext(), "OK pushed",
//                           Toast.LENGTH_LONG).show();
//        }
//    }
}
