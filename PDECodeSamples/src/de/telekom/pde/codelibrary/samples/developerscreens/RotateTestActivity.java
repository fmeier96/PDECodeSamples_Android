package de.telekom.pde.codelibrary.samples.developerscreens;

import android.os.Bundle;
import android.view.View;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

/*
 * Copyright (c) 2012. Neuland Multimedia GmbH. All rights reserved.
 * 
 * de.telekom.pde.codelibrary.samples.developerscreens
 */

public class RotateTestActivity extends PDEActivity {

    private   PDEButton mPDECheckbox = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_rotate_testscreen);

        mPDECheckbox = (PDEButton )findViewById(R.id.rotatetestscreen_pde_checkbox);
        mPDECheckbox.addListener(this,"buttonCheckboxPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        mPDECheckbox.setTag(Integer.valueOf(1));

    }

    @SuppressWarnings("unused")
    public void buttonCheckboxPressed(PDEEvent event){
        int index;

        // determine which checkbox
        index = ((Integer)((View)event.getSender()).getTag());

        // action depending on checkbox
        if(index==0){
            mPDECheckbox.setSelected(!mPDECheckbox.isSelected());
        } else {
            mPDECheckbox.setSelected(!mPDECheckbox.isSelected());
        }
    }
}