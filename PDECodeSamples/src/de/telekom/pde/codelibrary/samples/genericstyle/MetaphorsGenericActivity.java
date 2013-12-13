/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

/**
 * @brief Activity for sample screen to show different modes of the metaphores.
 */
package de.telekom.pde.codelibrary.samples.genericstyle;

import java.util.Locale;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.metaphors.PDEFilmMetaphorView;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.metaphors.PDEMusicMetaphorView;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.metaphors.PDEPhotoFrameView;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.metaphors.PDEVideoMetaphorView;
import de.telekom.pde.codelibrary.ui.helpers.PDEString;


public class MetaphorsGenericActivity extends PDESherlockActivity {

    /**
     * @brief Global tag for log outputs.
     */
    @SuppressWarnings("unused")
    private final static String LOG_TAG = MetaphorsGenericActivity.class.getName();
    
    private PDEConstants.PDEContentStyle mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;


    /**
     * @brief Create the Activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        RelativeLayout rootView;

        super.onCreate(savedInstanceState);

        Intent callIntent = getIntent();
        if (callIntent != null) {
            String text = callIntent.getStringExtra(PDECodeSamplesActivity.PDE_CODELIB_SAMPLE_EXTRA_PREFIX);
            if (!TextUtils.isEmpty(text)){
                if (PDEString.contains(text.toUpperCase(Locale.US), "haptic".toUpperCase(Locale.US)))  {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleHaptic;
                } else if (PDEString.contains(text.toUpperCase(Locale.US), "flat".toUpperCase(Locale.US))) {
                    mStyle = PDEConstants.PDEContentStyle.PDEContentStyleFlat;
                }
            }
        }

        setContentView(R.layout.metaphors_screen);

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            getSupportActionBar().setTitle("Haptic style/Metaphors");
        } else {
            getSupportActionBar().setTitle("Flat style/Metaphors");
        }

        // get the root view and set background color (different when dark-style is on or of in library)
        rootView = (RelativeLayout)findViewById(R.id.metaphores_showcase_rootlayout);
        rootView.setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());


        //calculate widths
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int windowHeight = displaymetrics.heightPixels;
        int windowWidth = displaymetrics.widthPixels;
        int rowSize = Math.round((float)(Math.min(windowHeight, windowWidth) - 3* PDEBuildingUnits.BU()) /2);


        PDEMusicMetaphorView musicMetaphor;
        PDEFilmMetaphorView filmMetaphor;
        PDEPhotoFrameView photoFrame;
        PDEVideoMetaphorView videoMetaphor;

        //create music metaphor
        musicMetaphor = new PDEMusicMetaphorView(this);
        musicMetaphor.setId(1);
        musicMetaphor.setPictureDrawable(getResources().getDrawable(R.drawable.baum2008));

        //create film metaphor
        filmMetaphor = new PDEFilmMetaphorView(this);
        filmMetaphor.setId(2);
        filmMetaphor.setPictureDrawable(getResources().getDrawable(R.drawable.baum2008));

        //create photo frame
        photoFrame = new PDEPhotoFrameView(this);
        photoFrame.setId(3);
        photoFrame.setPictureDrawable(getResources().getDrawable(R.drawable.kids));

        //create video metaphor
        videoMetaphor = new PDEVideoMetaphorView(this);
        videoMetaphor.setId(4);
        videoMetaphor.setPictureDrawable(getResources().getDrawable(R.drawable.baum2008));
        videoMetaphor.setTimeString("3:11");

        if (mStyle == PDEConstants.PDEContentStyle.PDEContentStyleHaptic) {
            musicMetaphor.setContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);
            musicMetaphor.setShadowEnabled(true);

            filmMetaphor.setShadowEnabled(true);
            filmMetaphor.setContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);

            photoFrame.setShadowEnabled(true);
            photoFrame.setContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);

            videoMetaphor.setShadowEnabled(true);
            videoMetaphor.setContentStyle(PDEConstants.PDEContentStyle.PDEContentStyleHaptic);
        }


        //add video metaphor
        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(rowSize, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params4.setMargins(PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU());
        params4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        videoMetaphor.setLayoutParams(params4);
        rootView.addView(videoMetaphor);


        //add photo frame
        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(rowSize, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params3.setMargins(PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            params3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        } else {
            params3.addRule(RelativeLayout.BELOW, 4);
            params3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }

        photoFrame.setLayoutParams(params3);
        rootView.addView(photoFrame);



        //add film metaphor
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(rowSize, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params2.setMargins(PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params2.addRule(RelativeLayout.BELOW, 4);
        } else {
            params2.addRule(RelativeLayout.RIGHT_OF, 4);
            params2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }

        filmMetaphor.setLayoutParams(params2);
        rootView.addView(filmMetaphor);

        //add music metaphor
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(rowSize, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.setMargins(PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU(),PDEBuildingUnits.BU());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            params1.addRule(RelativeLayout.BELOW, 3);
            params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        } else {
            params1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params1.addRule(RelativeLayout.RIGHT_OF, 2);
        }
        musicMetaphor.setLayoutParams(params1);
        rootView.addView(musicMetaphor);




    }

}
