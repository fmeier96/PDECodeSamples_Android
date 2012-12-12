package de.telekom.pde.codelibrary.samples.developerscreens;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class AndroidCheckboxButtonResizeActivity extends Activity{

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = AndroidCheckboxButtonResizeActivity.class.getName();
    ImageView mThumbView;
    PDEButton mButton;
    float mRawXAtStart;
    float mRawYAtStart;
    float mThumbXAtStart;
    float mThumbYAtStart;
    float mButtonWidthAtStart;
    float mButtonHeightAtStart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.developer_android_checkbox_button_resize_screen);

        //get the root view and set background color (different when darkstyle is on or of in library)
        RelativeLayout rootView = (RelativeLayout)findViewById(R.id.button_resize_relativelayout);
        rootView.setBackgroundColor(PDEColor.valueOf("DTUIBackground").getIntegerColor());

        mButton = ((PDEButton) findViewById(R.id.sizingtestscreen_dtbutton));
        //mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundPlate);
        mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.BackgroundPlate);
        mButton.setButtonOverlayLayerWithLayerType(PDEButton.PDEButtonLayerType.OverlayCheckbox);
        mButton.setTitle("Checkbox");
        mButton.setSelected(true);
        mButton.setAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        mButton.setParameter(PDEButton.PDEButtonParameterCheckboxAlignment, PDEConstants.PDEAlignmentStringRight);
        mButton.addListener(this,"buttonPressed", PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_MASK_ACTION);
        //  mButton.setButtonBackgroundLayerWithLayerType( PDEButton.PDEButtonLayerType.B);

        mThumbView = (ImageView)findViewById(R.id.sizingtestscreen_thumb);
        mThumbView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();


                if (action == MotionEvent.ACTION_DOWN) {
                    //Log.d(LOG_TAG, "ACTION_DOWN "+event.getRawX()+", "+event.getRawY());
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mThumbView.getLayoutParams();
                    mThumbXAtStart = lp.leftMargin;
                    mThumbYAtStart = lp.topMargin;
                    mRawXAtStart = event.getRawX();
                    mRawYAtStart = event.getRawY();
                    mButtonWidthAtStart = mButton.getWidth();
                    mButtonHeightAtStart = mButton.getHeight();

                    return true;
                } else if (action == MotionEvent.ACTION_MOVE) {
                    //Log.d(LOG_TAG, "ACTION_MOVE "+event.getRawX()+", "+event.getRawY());
                    float deltaX = event.getRawX() - mRawXAtStart;
                    float deltaY = event.getRawY() - mRawYAtStart;
                    float newButtonWidth = mButtonWidthAtStart + deltaX;
                    float newButtonHeight = mButtonHeightAtStart + deltaY;

                    // limit the size to one BU and positive values
                    if (newButtonWidth < PDEBuildingUnits.exactBU()) {
                        deltaX = PDEBuildingUnits.exactBU() - mButtonWidthAtStart;
                        newButtonWidth = PDEBuildingUnits.exactBU();
                    }
                    if (newButtonHeight < PDEBuildingUnits.exactBU()) {
                        deltaY = PDEBuildingUnits.exactBU() - mButtonHeightAtStart;
                        newButtonHeight = PDEBuildingUnits.exactBU();
                    }


                    // set ThumbView position
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mThumbView.getLayoutParams();
                    lp.leftMargin = (int) (mThumbXAtStart + deltaX);
                    lp.topMargin = (int) (mThumbYAtStart + deltaY);
                    mThumbView.setLayoutParams(lp);

                    //Log.d(LOG_TAG, "new button size: "+newButtonWidth+" , "+newButtonHeight);
                    // set button size
                    RelativeLayout.LayoutParams lpButton = (RelativeLayout.LayoutParams) mButton.getLayoutParams();
                    // minimum size 1 BU
                    lpButton.width = (int) newButtonWidth;
                    lpButton.height = (int) newButtonHeight ;
                    mButton.setLayoutParams(lpButton);

                    return true;
                } else if (action == MotionEvent.ACTION_UP) {
                    //Log.d(LOG_TAG, "ACTION_UP "+event.getRawX()+", "+event.getRawY());
                    return true;
                } else if (action == MotionEvent.ACTION_CANCEL) {
                    //Log.d(LOG_TAG, "ACTION_CANCEL "+event.getRawX()+", "+event.getRawY());
                    return true;
                }

                return false;
            }
        });
    }

    public void buttonPressed(PDEEvent event){
        if(event.getType().equals(PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_SELECTED)){
            if(mButton.isSelected()){
                mButton.setSelected(false);
            } else {
                mButton.setSelected(true);
            }
        }
    }
}
