/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.notifications.infoflags;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.notification.PDEInfoFlag;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableNotificationFrame.TrianglePosition;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableNotificationFrame.TriangleSide;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


//----------------------------------------------------------------------------------------------------------------------
//  InfoFlagsLayoutSampleActivity
//----------------------------------------------------------------------------------------------------------------------



/**
 * @brief InfoFlagsOverviewActivity for some info flag layout sample.
 */
public class InfoFlagsLayoutSampleActivity extends PDEActionBarActivity implements OnGlobalLayoutListener{


    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = InfoFlagsLayoutSampleActivity.class.getName();


    // layout sample variables
    private PDEButton mShowHideButton = null;
    private PDEInfoFlag mInfoFlagTop = null;
    private PDEInfoFlag mInfoFlagBottom = null;
    private ViewGroup mRootView = null;


    /**
     * Create the Activity.
     * @param savedInstanceState The saved instance state to recreate
     */
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.infoflags_layoutsample_screen);

        // get the root view and set background color (different when dark style is on or of in library)
        mRootView = (ViewGroup)findViewById(R.id.infoflags_layoutsample_root);
        mRootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        mInfoFlagTop = new PDEInfoFlag(this);
        mInfoFlagBottom = new PDEInfoFlag(this);
        mShowHideButton = (PDEButton)findViewById(R.id.showHideButton);

        // Add a button listener. The used event system is more sophisticated (and meets the styleguide requirements)
        // than the Android event system.
        // Here one event type (will_be_selected) is requested.
        // The function void dtButtonPressed(PDEEvent event) will be called.
        mShowHideButton.addListener(this, "onShowHideButtonPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);


        // Create infoflag above the show / hide button
        mInfoFlagTop.setTitleText("Centered with button");
        mInfoFlagTop.setMessageText(getResources().getString(R.string.common_lorem_ipsum_text));
        mRootView.addView(mInfoFlagTop);


        // Create infoflag below the show / hide button
        mInfoFlagBottom.setTitleText("Left button edge");
        mInfoFlagBottom.setMessageText(getResources().getString(R.string.common_lorem_ipsum_text));
        mInfoFlagBottom.setTrianglePredefinedPosition(TrianglePosition.Left,TriangleSide.SideTop);
        mRootView.addView(mInfoFlagBottom);

        // get the view tree observer and add listener
        ViewTreeObserver vto = mRootView.getViewTreeObserver();
        if(vto!=null) {
            vto.addOnGlobalLayoutListener(this);
        }
    }


    /**
     * Listener for layout events.
     * Calculate the correct positions of the tool tips here to demonstrate some complex usage.
     */
    @Override
    public void onGlobalLayout() {
        RelativeLayout.LayoutParams lp;
        ViewTreeObserver vto;
        int x,y, width, height;

        vto = InfoFlagsLayoutSampleActivity.this.mRootView.getViewTreeObserver();
        if(vto!=null) {
            removeOnGlobalLayoutListener(vto, this);
        }

        // Get position of the button
        x = mShowHideButton.getLeft();
        y = mShowHideButton.getTop();

        // get the max width / height of the screen
        width = InfoFlagsLayoutSampleActivity.this.mRootView.getWidth();
        height = InfoFlagsLayoutSampleActivity.this.mRootView.getHeight();

        // get current layout params and set margins for correct measurement
        lp = (RelativeLayout.LayoutParams)mInfoFlagTop.getLayoutParams();
        if (lp!=null) {
            lp.setMargins(PDEBuildingUnits.pixelFromBU(2.0f), 0, PDEBuildingUnits.pixelFromBU(2.0f), 0);
            mInfoFlagTop.measure(MeasureSpec.makeMeasureSpec(width - PDEBuildingUnits.pixelFromBU(4.0f), MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
            // We know the height now, lets set the correct position
            lp.setMargins(lp.leftMargin, y - mInfoFlagTop.getMeasuredHeight(), lp.rightMargin, lp.bottomMargin);
            mInfoFlagTop.setLayoutParams(lp);
        }
        // get current layout params and set margins for correct measurement
        lp = (RelativeLayout.LayoutParams)mInfoFlagBottom.getLayoutParams();
        if (lp!=null) {
            lp.setMargins(x, y + mShowHideButton.getMeasuredHeight(), PDEBuildingUnits.pixelFromBU(2.0f), 0);
            mInfoFlagBottom.measure(MeasureSpec.makeMeasureSpec(width - x - PDEBuildingUnits.pixelFromBU(2.0f), MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
            // We know the height now, lets set the correct position
            lp.setMargins(lp.leftMargin, y + mShowHideButton.getMeasuredHeight(), lp.rightMargin, lp.bottomMargin);
            mInfoFlagBottom.setLayoutParams(lp);
        }
    }


    /**
     * Listener for configuration changes (only orientation or screen size change in this sample)
     * @param newConfig the new configuration
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // orientation changed so -> get the view tree observer and add listener
        ViewTreeObserver vto = mRootView.getViewTreeObserver();
        if (vto!=null) {
            vto.addOnGlobalLayoutListener(this);
        }
    }


    /**
     * @brief Called on changes from agentController
     */
    @SuppressWarnings("unused")
    public void onShowHideButtonPressed(PDEEvent event)
    {
        // show/hide top tooltip
        if (mInfoFlagTop.isShowing()) {
            mInfoFlagTop.hide();
        } else {
            mInfoFlagTop.show();
        }

        // show/hide bottom tooltip
        if (mInfoFlagBottom.isShowing()) {
            mInfoFlagBottom.hide();
        } else {
            mInfoFlagBottom.show();
        }
    }


    /**
     * @brief Helper function to set OnGlobalLayoutListener if a specific ViewTreeObserver (avoid warnings -> use reflection).
     *
     * removeGlobalOnLayoutListener is marked as deprecated in api level 16, in order to avoid the warning use reflection.
     */
    private void removeOnGlobalLayoutListener(ViewTreeObserver vto, ViewTreeObserver.OnGlobalLayoutListener  listener) {
        Method method;

        // valid?
        if(vto==null) return;

        try {
            //try to use the removeOnGlobalLayoutListener function which was introduced in android 4.1 (api level 16)
            method = vto.getClass().getMethod("removeOnGlobalLayoutListener",
                    new Class[] {ViewTreeObserver.OnGlobalLayoutListener.class});
            method.invoke(vto, listener);
            return;
        } catch (NoSuchMethodException e) {
            // function not available
        } catch (IllegalAccessException e) {
            // function not available
        } catch (InvocationTargetException e) {
            // function not available
        }

        // removeOnGlobalLayoutListener function not found -> use old one (removeGlobalOnLayoutListener)
        try {
            //try to use the removeGlobalOnLayoutListener which is deprecated in android 4.1
            method = vto.getClass().getMethod("removeGlobalOnLayoutListener",
                    new Class[] {ViewTreeObserver.OnGlobalLayoutListener.class});
            method.invoke(vto, listener);
            return;
        } catch (NoSuchMethodException e) {
            // function not available
        } catch (IllegalAccessException e) {
            // function not available
        } catch (InvocationTargetException e) {
            // function not available
        }

        // everything goes wrong -> SHOULD NOT HAPPEN
        Log.e(LOG_TAG, "there is no removeOnGlobalLayoutListener or removeGlobalOnLayoutListener function for this object:"+vto.getClass().toString());
    }
}
