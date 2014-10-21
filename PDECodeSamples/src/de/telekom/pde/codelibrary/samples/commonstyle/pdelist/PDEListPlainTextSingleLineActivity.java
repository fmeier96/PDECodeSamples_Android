/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.components.lists.PDEEventListItem;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListItem;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDEArrayAdapter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

//----------------------------------------------------------------------------------------------------------------------
// PDEListPlainTextSingleLineActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief PDEList example with single line text in three different layout sizes.
 *
 * In this Example we have just single line text. The layout sizes (small/medium/large) can be switched.
 */
public class PDEListPlainTextSingleLineActivity extends PDEActionBarActivity {
    // different layout sizes
    private enum sample_size {
        small, medium, large
    }


    // number of list elements
    private final static int NUMBER_OF_LIST_ITEMS_SHOWN = 1000;
    // the pde list view
    private PDEListView mList;
    // store current layout size
    private sample_size mCurrentlyShownSize;
    // Data Set
    ArrayList<String> mDataSet;


    /**
     * @brief onCreate
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.pde_list_activity);

        // create data set
        createDataSet();

        // get pde list view
        mList = (PDEListView) findViewById(R.id.pde_list);

        // default size
        mCurrentlyShownSize = sample_size.medium;

        // Creation of Adapter is moved to the onResume() method, so we can restore the adapter in the current layout
        // size after rotation of the device

        // create callback in order to be informed when a list item was clicked.
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // for now the content of the list element is very simple - just a string
                Object object = mList.getItemAtPosition(position);
                String element = "";
                // check if object is really a string
                if (object instanceof String) {
                    element = (String) object;
                }
                // show selected item
                Toast.makeText(PDEListPlainTextSingleLineActivity.this, "Selected : " + element,
                               Toast.LENGTH_SHORT).show();
            }
        });

        // add listener
        mList.addListener(this, "onPDEListItemClicked");
    }


    /**
     * @brief Create adapter for current size on resume.
     */
    @Override
    protected void onResume() {
        super.onResume();
        // create new adapter
        setAdapterForSize(mCurrentlyShownSize);
    }


    /**
     * @brief Creates and sets an Adapter for the currently selected layout size.
     *
     * @param size The new layout size that should be set.
     */
    private void setAdapterForSize(sample_size size) {
        int layout_row_id = 0;

        // find fitting layout for wanted size
        switch (size) {
            case small:
                layout_row_id = R.layout.pde_list_plain_text_single_line_small_row;
                break;
            case medium:
                layout_row_id = R.layout.pde_list_plain_text_single_line_medium_row;
                break;
            case large:
                layout_row_id = R.layout.pde_list_plain_text_single_line_large_row;
                break;
        }

        // create new list adapter with current settings
        PDEArrayAdapter<String> adapter = new PDEArrayAdapter<String>(this, layout_row_id, R.id.PDEList_ItemText, mDataSet);

        // Set the adapter in our list
        mList.setAdapter(adapter);

        // remember current size
        mCurrentlyShownSize = size;
    }


//---------------------------------------------------------------------------------------------------------------------
// ----- Creation of data sets ----------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------


    /**
     * @brief Helper that creates the whole data set with the configured number of items.
     */
    protected void createDataSet() {
        mDataSet = new ArrayList<String>(NUMBER_OF_LIST_ITEMS_SHOWN);
        for (int i = 0; i < NUMBER_OF_LIST_ITEMS_SHOWN; i++) {
            int mod;
            String text = i + ") ";

            mod = i % 4;
            if (mod == 0) {
                text += "Lorem ipsum dolor";
            } else if (mod == 1) {
                text += "Conseteur sadipscing";
            } else if (mod == 2) {
                text += "Nonumy eirmod sed diam";
            } else if (mod == 3) {
                text += "Tempor invidunt ut";
            }
            mDataSet.add(text);
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// ----- Changing of layout size ----------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------

// All code beneath this point only serves the purpose to switch between the layout sizes of the list elements. This
// prevents us from writing a lot of classes with nearly 100% redundant code, which would have to be maintained. But
// the following code is no real part of the example that shows how to get a pde list running.


    /**
     * @brief Create options menu that allows to change the layout size of the list items.
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_samples_sizes, menu);
        return true;
    }


    /**
     * @brief Shows / hides option menu entries depending on actual layout size selection.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuSmall, menuMedium, menuLarge;

        // find menus
        menuSmall = menu.findItem(R.id.menu_list_samples_size_small);
        menuMedium = menu.findItem(R.id.menu_list_samples_size_medium);
        menuLarge = menu.findItem(R.id.menu_list_samples_size_large);

        // get all menu entries and make them visible
        if (menuSmall != null) menuSmall.setVisible(true);
        if (menuMedium != null) menuMedium.setVisible(true);
        if (menuLarge != null) menuLarge.setVisible(true);

        // hide the menu entry that matches the currently active setting
        if (mCurrentlyShownSize == sample_size.small && menuSmall != null) {
            menuSmall.setVisible(false);
        } else if (mCurrentlyShownSize == sample_size.medium && menuMedium != null) {
            menuMedium.setVisible(false);
        } else if (mCurrentlyShownSize == sample_size.large && menuLarge != null) {
            menuLarge.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    /**
     * @brief Listener for clicked option menu item.
     *
     * @param item The item of the option menu which was selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list_samples_size_small:
                setAdapterForSize(sample_size.small);
                return true;
            case R.id.menu_list_samples_size_medium:
                setAdapterForSize(sample_size.medium);
                return true;
            case R.id.menu_list_samples_size_large:
                setAdapterForSize(sample_size.large);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * @brief Store current layout size before device rotation.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // store layout size setting
        savedInstanceState.putString("Size", mCurrentlyShownSize.name());
        super.onSaveInstanceState(savedInstanceState);
    }


    /**
     * @brief Restore current layout size after device rotation.
     */
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore layout size setting
        String size = savedInstanceState.getString("Size");
        mCurrentlyShownSize = sample_size.valueOf(size);
    }


    /**
     * @brief Listener for PDE List events.
     */
    @SuppressWarnings("unused")
    public void onPDEListItemClicked(PDEEvent event) {
        PDEEventListItem listEvent = (PDEEventListItem) event;

        // just for debug
        if (TextUtils.equals(listEvent.getType(), PDEListItem.PDE_LIST_ITEM_EVENT_ACTION_SELECTED)) {
            Log.d("PDEListPlainTextSingleLineActivity", "List Item " + listEvent.getListPosition() + " SELECTED");
        } else if (TextUtils.equals(listEvent.getType(), PDEListItem.PDE_LIST_ITEM_EVENT_ACTION_WILL_BE_SELECTED)) {
            Log.d("PDEListPlainTextSingleLineActivity",
                  "List Item " + listEvent.getListPosition() + " WILL BE SELECTED");
        }
    }

}

