package de.telekom.pde.codelibrary.samples.developerscreens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActivity;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDETextView;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.metaphors.PDEPhotoFrameView;


public class LayerTextBugfixScreen extends PDEActivity {

    private final static String LOG_TAG = PDETextView.class.getName();

    // private variables
    PDETextView mListText;
    PDETextView mListSubtext;
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

        mListText = (PDETextView) findViewById(R.id.PDEList_ItemText);
        mListSubtext = (PDETextView) findViewById(R.id.PDEList_ItemSubText);
        mListPhoto = (PDEPhotoFrameView) findViewById(R.id.PDEList_ItemImage);
        int imgRes = R.drawable.kids;
        mListPhoto.setPhotoFromID(imgRes);
        mListText.setText("Lorem ipsum dolor");
        mListSubtext.setText("Tempor invidunt ut labore et dolore magna aliquam "
          +  "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.");
        mSmallerButton = (Button) findViewById(R.id.smaller_button);
        mBiggerButton = (Button) findViewById(R.id.bigger_button);

    }


    @SuppressWarnings("unused")
    public void clickSmaller(View v) {
        float newtextsize = mListSubtext.getTextSize() - 5;
        Log.d(LOG_TAG, "textsize: " + newtextsize);


        mListSubtext.setTextSize(newtextsize);

    }

    @SuppressWarnings("unused")
    public void clickBigger(View v) {
        float newtextsize = mListSubtext.getTextSize() + 5;
        Log.d(LOG_TAG, "textsize: " + newtextsize);


        mListSubtext.setTextSize(newtextsize);

    }




















}
