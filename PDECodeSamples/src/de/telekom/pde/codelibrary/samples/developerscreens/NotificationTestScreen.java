/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.notification.PDENotificationBaseView;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

import android.os.Bundle;
import android.view.View;

public class NotificationTestScreen extends PDEActionBarActivity {

    PDENotificationBaseView mCurrentNotificationView;
    private PDEButton mButtonRadio1 = null;
    private PDEButton mButtonRadio2 = null;
    private int mRadioState;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.developer_notification_test_screen);




        PDEButton buttonShow, buttonShowTime, buttonHide;

        buttonShow = (PDEButton)findViewById(R.id.telekomButtonShow);
        buttonShowTime = (PDEButton)findViewById(R.id.telekomButtonShowTime);
        buttonHide = (PDEButton)findViewById(R.id.telekomButtonHide);
        mCurrentNotificationView = getSelectedNotificationView();

        buttonShow.addListener(this, "onClickButtonShow", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonShowTime.addListener(this, "onClickButtonShowTime", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);
        buttonHide.addListener(this, "onClickButtonHide", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED);

        mButtonRadio1 = (PDEButton)findViewById(R.id.button_radio1);
        mButtonRadio1.addListener(this,"buttonRadioPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mButtonRadio2 = (PDEButton)findViewById(R.id.button_radio2);
        mButtonRadio2.addListener(this,"buttonRadioPressed",PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
    }


    public void onResume(){
        super.onResume();
        if (mButtonRadio1.isSelected()) mRadioState = 0;
        else mRadioState = 1;
    }

    @SuppressWarnings("unused")
    public void onClickButtonShow(PDEEvent event) {
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            changeCheck();
            mCurrentNotificationView.show();
        }
    }

    @SuppressWarnings("unused")
    public void onClickButtonShowTime(PDEEvent event) {
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            changeCheck();
            mCurrentNotificationView.showNotification(mCurrentNotificationView.getTitleText(),mCurrentNotificationView.getMessageText(),3000);
        }
    }

    @SuppressWarnings("unused")
    public void onClickButtonHide(PDEEvent event) {
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            mCurrentNotificationView.hide();
        }
    }

    @SuppressWarnings("unused")
    public void buttonRadioPressed(PDEEvent event){
        Object tag = ((View)event.getSender()).getTag();
        if (tag == null ) return;

        // get RadioState from tag
        mRadioState = Integer.valueOf(""+tag);

        // set Radio button states
        mButtonRadio1.setSelected(mRadioState==0);
        mButtonRadio2.setSelected(mRadioState==1);
    }

    protected PDENotificationBaseView getSelectedNotificationView(){
        if(mRadioState == 0) return (PDENotificationBaseView)findViewById(R.id.ToolTip);
        else return (PDENotificationBaseView)findViewById(R.id.InfoFlag);
    }

    protected void changeCheck(){
        if (mCurrentNotificationView != getSelectedNotificationView()){
            if (mCurrentNotificationView.isShowing()) mCurrentNotificationView.hide();
            mCurrentNotificationView = getSelectedNotificationView();
        }
    }
}
