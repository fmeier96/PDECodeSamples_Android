/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.Locale;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.boxes.PDEDrawableCutoutHaptic;
import de.telekom.pde.codelibrary.ui.components.boxes.PDEDrawableStageFlat;
import de.telekom.pde.codelibrary.ui.components.boxes.PDEDrawableStageHaptic;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;
import de.telekom.pde.codelibrary.ui.helpers.PDEUtils;


public class StageGenericActivity extends PDEActionBarActivity {

    private View mStageView = null;
    private View mCutoutView = null;
    private Drawable mCutoutDrawable;
    private Drawable mStageDrawable;

    // style flag
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            setContentView(R.layout.stage_cutout_haptic_screen);
            getSupportActionBar().setTitle("Haptic style/Stage and cutout");
        } else {
            setContentView(R.layout.stage_flat_screen);
            getSupportActionBar().setTitle("Flat style/Stage");
        }

        //get the root view and set background color (different when darkstyle is on or of in library)
        ScrollView rootView = (ScrollView) findViewById(R.id.headers_rootview);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());

        // fetch views from layout
        mStageView = findViewById(R.id.stage);
        // only haptic style has a cutout
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            mCutoutView = findViewById(R.id.cutout);
        }

        // --- Stage ---
        // increase stage view size
        ViewGroup.LayoutParams lp = mStageView.getLayoutParams();
        if (lp != null) {
            lp.width = PDEBuildingUnits.BU() * 21;
            lp.height = PDEBuildingUnits.BU() * 12;
            mStageView.setLayoutParams(lp);
        }

        // create stage drawable
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            mStageDrawable = new PDEDrawableStageHaptic();
        } else {
            mStageDrawable = new PDEDrawableStageFlat();
        }

        // set stage drawable as background of the view
        PDEUtils.setViewBackgroundDrawable(mStageView, mStageDrawable);

        // only haptic style has a cutout
        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            // --- Cutout ---
            // increase cutout view size
            lp = mCutoutView.getLayoutParams();
            if (lp != null) {
                lp.width = PDEBuildingUnits.BU() * 20;
                lp.height = PDEBuildingUnits.BU() * 11;
                mCutoutView.setLayoutParams(lp);
            }

            // create cutout
            mCutoutDrawable = new PDEDrawableCutoutHaptic();

            // set cutout drawable as background of the view
            PDEUtils.setViewBackgroundDrawable(mCutoutView, mCutoutDrawable);
        }
    }


}