package de.telekom.pde.codelibrary.samples.developerscreens;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.*;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.basescreens.DialogHelper;
import de.telekom.pde.codelibrary.samples.basescreens.SimpleTriangleDrawable;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.elements.text.PDELayerText;
import de.telekom.pde.codelibrary.ui.elements.wrapper.PDELayerTextView;
import de.telekom.pde.codelibrary.ui.elements.wrapper.PDEPhotoFrameView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: pschmidt
 * Date: 19.06.13
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
public class LayerTextBugfixScreen extends PDEActivity {

    private final static String LOG_TAG = PDELayerTextView.class.getName();

    // private variables
    PDELayerTextView mListText;
    PDELayerTextView mListSubtext;
    PDEPhotoFrameView mListPhoto;
    Button mSmallerButton;
    Button mBiggerButton;


    /**
     * @brief Create the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layertext_bugfix_screen);

        mListText = (PDELayerTextView) findViewById(R.id.PDEList_ItemText);
        mListSubtext = (PDELayerTextView) findViewById(R.id.PDEList_ItemSubText);
        mListPhoto = (PDEPhotoFrameView) findViewById(R.id.PDEList_ItemImage);
        int imgRes = R.drawable.kids;
        mListPhoto.setPhotoFromID(imgRes);
        mListText.setText("Lorem ipsum dolor");
        mListSubtext.setText("Tempor invidunt ut labore et dolore magna aliquam "
          +  "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.");
        mSmallerButton = (Button) findViewById(R.id.smaller_button);
        mBiggerButton = (Button) findViewById(R.id.bigger_button);

    }


    public void clickSmaller(View v) {
        float newtextsize = mListSubtext.getTextSize() - 5;
        Log.d(LOG_TAG, "textsize: " + newtextsize);

        int width = PDEBuildingUnits.roundUpToScreenCoordinates(mListSubtext.getElementWidth());
        int height = PDEBuildingUnits.roundUpToScreenCoordinates(mListSubtext.getTextHeight());


        mListSubtext.setTextSize(newtextsize);

    }

    public void clickBigger(View v) {
        float newtextsize = mListSubtext.getTextSize() + 5;
        Log.d(LOG_TAG, "textsize: " + newtextsize);

        int width = PDEBuildingUnits.roundUpToScreenCoordinates(mListSubtext.getElementWidth());
        int height = PDEBuildingUnits.roundUpToScreenCoordinates(mListSubtext.getTextHeight());

        mListSubtext.setTextSize(newtextsize);

    }




















}
