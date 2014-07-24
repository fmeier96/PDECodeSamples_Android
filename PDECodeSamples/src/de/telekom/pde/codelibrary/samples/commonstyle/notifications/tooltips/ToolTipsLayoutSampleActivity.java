/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.notifications.tooltips;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.notification.PDEToolTip;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableNotificationFrame.TrianglePosition;
import de.telekom.pde.codelibrary.ui.elements.boxes.PDEDrawableNotificationFrame.TriangleSide;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;


//----------------------------------------------------------------------------------------------------------------------
//  ToolTipsOverviewActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief ToolTipsLayoutSampleActivity for some sample tooltip layouting.
 */
public class ToolTipsLayoutSampleActivity extends PDEActionBarActivity implements OnGlobalLayoutListener {


    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = ToolTipsLayoutSampleActivity.class.getName();


    // layout sample variables
    private PDEButton mShowHideButton = null;
    private PDEToolTip mToolTipTop = null;
    private PDEToolTip mToolTipBottom = null;
    private ViewGroup mRootView = null;


    /**
     * Create the Activity.
     *
     * @param savedInstanceState The saved instance state to recreate.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.tooltips_layoutsample_screen);

        // get the root view and set background color (different when darkstyle is on or of in library)
        mRootView = (ViewGroup) findViewById(R.id.tooltips_layoutsample_root);
        mRootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // *************************
        // Create the show / hide button
        // *************************

        mShowHideButton = (PDEButton) findViewById(R.id.showHideButton);

        // Add a button listener. The used event system is more sophisticated (and meets the styleguide requirements)
        // than the Android event system.
        // Here one event type (will_be_selected) is requested.
        // The function void dtButtonPressed(PDEEvent event) will be called.
        mShowHideButton.addListener(this, "onShowHideButtonPressed",
                                    PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);

        // *************************
        // Create infoflag above the show / hide button
        // *************************

        mToolTipTop = new PDEToolTip(this);
        mToolTipTop.setMessageText("Messgage");
        mRootView.addView(mToolTipTop);


        // *************************
        // Create infoflag below the show / hide button
        // *************************

        mToolTipBottom = new PDEToolTip(this);
        mToolTipBottom.setMessageText("Message");
        mToolTipBottom.setTrianglePredefinedPosition(TrianglePosition.Left, TriangleSide.SideTop);
        mRootView.addView(mToolTipBottom);

        // get the view tree observer and add listener
        ViewTreeObserver vto = mRootView.getViewTreeObserver();
        if (vto != null) {
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
        int x, y, width, height;

        // get the view tree observer and remove this listener
        vto = ToolTipsLayoutSampleActivity.this.mRootView.getViewTreeObserver();
        removeOnGlobalLayoutListener(vto, this);

        // Get position of the button
        x = mShowHideButton.getLeft();
        y = mShowHideButton.getTop();

        // get the max width / height of the screen
        width = ToolTipsLayoutSampleActivity.this.mRootView.getWidth();
        height = ToolTipsLayoutSampleActivity.this.mRootView.getHeight();

        // get current layout params and set margins for correct measurement
        lp = (RelativeLayout.LayoutParams) mToolTipTop.getLayoutParams();
        if (lp != null) {
            mToolTipTop.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                                MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
            // We know the width now, lets set the correct position
            lp.setMargins((width - mToolTipTop.getMeasuredWidth()) / 2,
                          y - mToolTipTop.getMeasuredHeight(),
                          lp.rightMargin,
                          lp.bottomMargin);
            mToolTipTop.setLayoutParams(lp);
        }

        // get current layout params and set margins for correct measurement
        lp = (RelativeLayout.LayoutParams) mToolTipBottom.getLayoutParams();
        if (lp != null) {
            lp.setMargins(x, y + mShowHideButton.getMeasuredHeight(), PDEBuildingUnits.pixelFromBU(2.0f), 0);
            mToolTipBottom.measure(MeasureSpec.makeMeasureSpec(width - x - PDEBuildingUnits.pixelFromBU(2.0f),
                                                               MeasureSpec.AT_MOST),
                                   MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
            // We know the width now, lets set the correct position
            lp.setMargins(lp.leftMargin, y + mShowHideButton.getMeasuredHeight(), lp.rightMargin, lp.bottomMargin);
            mToolTipBottom.setLayoutParams(lp);
        }
    }


    /**
     * Listener for configuration changes (only orientation or scree nsize change in this sample)
     *
     * @param newConfig The new configuration
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // orientation changed so -> get the view tree observer and add listener
        ViewTreeObserver vto = mRootView.getViewTreeObserver();
        if (vto != null) {
            vto.addOnGlobalLayoutListener(this);
        }
    }


    /**
     * @brief Called on changes from agentController
     */
    @SuppressWarnings("unused")
    public void onShowHideButtonPressed(PDEEvent event) {
        // show/hide top tooltip
        if (mToolTipTop.isShowing()) {
            mToolTipTop.hide();
        } else {
            mToolTipTop.show();
        }

        // show/hide bottom tooltip
        if (mToolTipBottom.isShowing()) {
            mToolTipBottom.hide();
        } else {
            mToolTipBottom.show();
        }
    }


    /**
     * @brief Helper function to set OnGlobalLayoutListener if a specific ViewTreeObserver (avoid warnings -> use reflection).
     *
     * removeGlobalOnLayoutListener is marked as deprecated in api level 16, in order to avoid the warning use reflection.
     */
    private void removeOnGlobalLayoutListener(ViewTreeObserver vto, ViewTreeObserver.OnGlobalLayoutListener listener) {
        Method method;

        // valid?
        if (vto == null) return;

        try {
            //try to use the removeOnGlobalLayoutListener function which was introduced in android 4.1 (api level 16)
            method = vto.getClass().getMethod("removeOnGlobalLayoutListener",
                                              new Class[]{ViewTreeObserver.OnGlobalLayoutListener.class});
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
                                              new Class[]{ViewTreeObserver.OnGlobalLayoutListener.class});
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
        Log.e(LOG_TAG,
              "there is no removeOnGlobalLayoutListener or removeGlobalOnLayoutListener function for this object:" + vto
                      .getClass()
                      .toString());
    }
}
