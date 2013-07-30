package de.telekom.pde.codelibrary.samples.developerscreens;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.ResizeBaseActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


public class NativeLayoutResizeActivity extends ResizeBaseActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = NativeLayoutResizeActivity.class.getName();
    PDEButton mPDEButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout.LayoutParams btnLinearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        View layout = RelativeLayout.inflate(this, R.layout.developer_nativelayout_resize,null);
        addViewToResizeContainer(layout,btnLinearLayoutParams);
    }

    @SuppressWarnings("unused")
    public void buttonPressed(PDEEvent event){
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            if(mPDEButton.isSelected()){
                mPDEButton.setSelected(false);
            } else {
                mPDEButton.setSelected(true);
            }
        }
    }
}
