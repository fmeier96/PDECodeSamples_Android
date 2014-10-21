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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDESimpleAdapter;


//----------------------------------------------------------------------------------------------------------------------
// PDEListPlainIconSingleLineActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief PDEList example with list items that carry an icon and a single line of text.
 *
 * In this example the list items show a left aligned icon and to the right of the icon a single lined text.
 * The used icons are part of the icon font. The layout sizes (small/medium/large) can be switched.
 */
public class PDEListPlainIconSingleLineActivity extends PDEActionBarActivity {
    // different layout sizes
    private enum sample_size {
        small, medium, large
    }


    // private class definition for data set
    private class StringObjectHashMap extends HashMap<String, Object> {
    	/**
		 * 
		 */
		private static final long serialVersionUID = -8523494787404431125L;
    }


    // hash map key constants
    public final static String ITEM_TITLE = "title";
    public final static String ITEM_ICON = "icon";

    // number of list elements
    private final static int NUMBER_OF_LIST_ITEMS_SHOWN = 1000;
    // the pde list view
    private PDEListView mList;
    // make array with ids of our target views (sub views of the list item layout)
    private int[] targetViewIDs = new int[]{R.id.PDEList_ItemIcon, R.id.PDEList_ItemText};
    // store current layout size
    private sample_size mCurrentlyShownSize;
    // data set
    List<StringObjectHashMap> mDataSet = new LinkedList<StringObjectHashMap>();


    /**
     * @brief onCreate
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.pde_list_activity);

        // clear data set
        mDataSet.clear();
        // create data set
        createDataSet();

        // get pde list view
        mList = (PDEListView) findViewById(R.id.pde_list);

        // default size
        mCurrentlyShownSize = sample_size.medium;

        // Creation of Adapter is moved to the onResume() method, so we can restore the adapter in the current layout
        // size after rotation of the device

        // create callback in order to get informed when a list item was clicked.
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // the content of a list item should be a hash map that carries (amongst others) a string title as payload
                // get list item object
                Object object = mList.getItemAtPosition(position);
                String element = "";
                // check if list item object is really a hash map
                if (object instanceof StringObjectHashMap) {
                    // extract the title from the hash map
                    element = (String) ((StringObjectHashMap) object).get(ITEM_TITLE);
                }
                // show selected item
                Toast.makeText(PDEListPlainIconSingleLineActivity.this, "Selected : " + element,
                               Toast.LENGTH_SHORT).show();
            }
        });
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
        PDESimpleAdapter adapter;

        // find fitting layout for wanted size
        switch (size) {
            case small:
                layout_row_id = R.layout.pde_list_plain_icon_single_line_small_row;
                break;
            case medium:
                layout_row_id = R.layout.pde_list_plain_icon_single_line_medium_row;
                break;
            case large:
                layout_row_id = R.layout.pde_list_plain_icon_single_line_large_row;
                break;
        }

        // create new list adapter with current settings
        adapter = new PDESimpleAdapter(this,
                                       mDataSet,
                                       layout_row_id,
                                       new String[]{ITEM_ICON, ITEM_TITLE},
                                       targetViewIDs);

        // Set the adapter in our list
        mList.setAdapter(adapter);

        // remember current size
        mCurrentlyShownSize = size;
    }


//---------------------------------------------------------------------------------------------------------------------
// ----- Creation of data sets ----------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------


    /**
     * @brief Helper that creates and returns one item of our data set.
     *
     * @param title title of the list item
     * @param iconChar character that represents a distinct icon
     *
     * @return item of the data set
     */
    public StringObjectHashMap createItem(String title, String iconChar) {
        StringObjectHashMap item = new StringObjectHashMap();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_ICON, iconChar);
        return item;
    }


    /**
     * @brief Helper that creates the whole data set with the configured number of items.
     */
    protected void createDataSet() {
        for (int i = 0; i < NUMBER_OF_LIST_ITEMS_SHOWN; i++) {
            int mod;
            // select blind text & icon font
            mod = i % 4;
            if (mod == 0) {
                mDataSet.add(createItem(i + ") Lorem ipsum dolor", "#O"));
            } else if (mod == 1) {
                mDataSet.add(createItem(i + ") Conseteur sadipscing", "#m"));
            } else if (mod == 2) {
                mDataSet.add(createItem(i + ") Nonumy eirmod sed diam", "#H"));
            } else if (mod == 3) {
                mDataSet.add(createItem(i + ") Tempor invidunt ut", "#F"));
            }
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
        // get selected size and update adapter corresponding to this size
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

}

