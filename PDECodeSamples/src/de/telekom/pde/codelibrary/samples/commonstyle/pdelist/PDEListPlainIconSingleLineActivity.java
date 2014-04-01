/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;

//----------------------------------------------------------------------------------------------------------------------
// PDEListPlainIconSingleLineActivity
//----------------------------------------------------------------------------------------------------------------------

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;


/**
 * @brief PDEList example with list items that carry an icon and a single line of text.
 *
 * In this example the list items show a left aligned icon and to the right of the icon a single lined text.
 * The used icons are part of the icon font. The layout sizes (small/medium/large) can be switched.
 */
public class PDEListPlainIconSingleLineActivity extends PDEActionBarActivity {
    // different layout sizes
    private enum sample_size {small, medium, large}
    // number of list elements
    private final static int NUMBER_OF_LIST_ITEMS_SHOWN = 1000;
    // the pde list view
    private PDEListView mList;
    // make array with ids of our target views (sub views of the list item layout)
    private int[] targetViewIDs = new int[] {R.id.PDEList_ItemIcon, R.id.PDEList_ItemText};
    // store current layout size
    private sample_size mCurrentlyShownSize;


    /**
     * @brief onCreate
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.pde_list_activity);

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
                // for now the content of the list element is very simple - just a string
                Object o = mList.getItemAtPosition(position);
                String element = (String) o;
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
    protected void onResume (){
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
                layout_row_id = R.layout.pde_list_plain_icon_single_line_small_row;
                break;
            case medium:
                layout_row_id = R.layout.pde_list_plain_icon_single_line_medium_row;
                break;
            case large:
                layout_row_id = R.layout.pde_list_plain_icon_single_line_large_row;
                break;
        }

        // create new adapter
        PDEListPlainIconSingleLineAdapter adapter = new PDEListPlainIconSingleLineAdapter(
                this, layout_row_id, targetViewIDs);
        // create the desired amount of dummy list elements
        adapter.setItemCount(NUMBER_OF_LIST_ITEMS_SHOWN);
        // Set the adapter in our list
        mList.setAdapter(adapter);

        // remember current size
        mCurrentlyShownSize = size;
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
        // get all menu entries and make them visible
        menu.findItem(R.id.menu_list_samples_size_small).setVisible(true);
        menu.findItem(R.id.menu_list_samples_size_medium).setVisible(true);
        menu.findItem(R.id.menu_list_samples_size_large).setVisible(true);

        // hide the menu entry that matches the currently active setting
        if (mCurrentlyShownSize == sample_size.small) {
            menu.findItem(R.id.menu_list_samples_size_small).setVisible(false);
        } else if (mCurrentlyShownSize == sample_size.medium) {
            menu.findItem(R.id.menu_list_samples_size_medium).setVisible(false);
        } else if (mCurrentlyShownSize == sample_size.large) {
            menu.findItem(R.id.menu_list_samples_size_large).setVisible(false);
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
//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        // get selected size and update adapter corresponding to this size
//        switch (item.getItemId()) {
//            case R.id.menu_list_samples_size_small:
//                setAdapterForSize(sample_size.small);
//                return true;
//            case R.id.menu_list_samples_size_medium:
//                setAdapterForSize(sample_size.medium);
//                return true;
//            case R.id.menu_list_samples_size_large:
//                setAdapterForSize(sample_size.large);
//                return true;
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }
//

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
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore layout size setting
        String size = savedInstanceState.getString("Size");
        mCurrentlyShownSize = sample_size.valueOf(size);
    }


}

