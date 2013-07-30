/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdelist;

//----------------------------------------------------------------------------------------------------------------------
// PDEListPlainTextSingleLineSmallActivity
//----------------------------------------------------------------------------------------------------------------------

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


/**
 * @brief Reference example of a PDE List example activity.
 *
 * There are other ways to do it, but this is the intended way that uses most comfort features.
 */
public class PDEListPlainGraphicMultiLineActivity  extends PDESherlockActivity {

    private enum sample_number_of_lines {two, multiple}

    private final static int NUMBER_OF_LIST_ITEMS_SHOWN = 1000;

    private PDEListView mList;
    // make array with ids of our target views (sub views of the list item layout)
    private int[] mTargetViewIDs = new int[] {R.id.PDEList_ItemImage,R.id.PDEList_ItemText,R.id.PDEList_ItemSubText};

    private sample_number_of_lines mCurrentlyShownNumberOfLines;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // set content view
        setContentView(R.layout.pde_list_activity);

        // get pde list view
        mList = (PDEListView) findViewById(R.id.pde_list);

        // default
        mCurrentlyShownNumberOfLines = sample_number_of_lines.multiple;

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
                Toast.makeText(PDEListPlainGraphicMultiLineActivity.this, "Selected : " + element,
                               Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * @brief Create adapter for current number of lines on resume.
     */
    @Override
    protected void onResume (){
        super.onResume();
        setAdapterForNumberOfLines(mCurrentlyShownNumberOfLines);
    }


    /**
     * @brief Handles the selection of a new number of shown lines.
     *
     * @param numberOfLines The number of shown lines that should be set.
     */
    private void setAdapterForNumberOfLines(sample_number_of_lines numberOfLines) {
        int layout_row_id = 0;
        boolean two = false;

        // find fitting layout for wanted size
        switch (numberOfLines) {
            case two:
                layout_row_id = R.layout.pde_list_plain_graphic_two_line_large_row;
                two = true;
                break;
            case multiple:
                layout_row_id = R.layout.pde_list_plain_graphic_multi_line_row;
                two = false;
                break;
        }

        // create new adapter
        PDEListPlainGraphicMultiLineAdapter adapter = new PDEListPlainGraphicMultiLineAdapter(
                this, layout_row_id, mTargetViewIDs);
        // create the desired amount of dummy list elements
        adapter.setItemCount(NUMBER_OF_LIST_ITEMS_SHOWN);
        // how much text?
        adapter.setJustTwoLines(two);
        // Set the adapter in our list
        mList.setAdapter(adapter);

        // remember current size
        mCurrentlyShownNumberOfLines = numberOfLines;
    }


//---------------------------------------------------------------------------------------------------------------------
// ----- Changing of number of lines ----------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------

// All code beneath this point only serves the purpose to switch between the number of lines of the list elements. This
// prevents us from writing a lot of classes with nearly 100% redundant code, which would have to be maintained. But
// the following code is no real part of the example that shows how to get a pde list running.

    /**
     * @brief Create options menu that allows to change the number of lines of the list items.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getSupportMenuInflater().inflate(R.menu.menu_list_samples_number_of_lines, menu);
        return true;
    }


    /**
     * @brief Shows / hides option menu entries depending on actual number of lines selection.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_list_samples_number_of_lines_two).setVisible(true);
        menu.findItem(R.id.menu_list_samples_number_of_lines_multiple).setVisible(true);

        if (mCurrentlyShownNumberOfLines == sample_number_of_lines.two) {
            menu.findItem(R.id.menu_list_samples_number_of_lines_two).setVisible(false);
        } else if (mCurrentlyShownNumberOfLines == sample_number_of_lines.multiple) {
            menu.findItem(R.id.menu_list_samples_number_of_lines_multiple).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * @brief Listener for clicked option menu item.
     *
     * @param featureId
     * @param item The item of the option menu which was selected.
     * @return
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list_samples_number_of_lines_two:
                setAdapterForNumberOfLines(sample_number_of_lines.two);
                return true;
            case R.id.menu_list_samples_number_of_lines_multiple:
                setAdapterForNumberOfLines(sample_number_of_lines.multiple);
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }




    /**
     * @brief Store current number of lines setting before device rotation.
     *
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // store number of lines setting
        savedInstanceState.putString("NumberOfLines", mCurrentlyShownNumberOfLines.name());
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * @brief Restore current number of lines setting after device rotation.
     *
     * @param savedInstanceState
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore number of lines setting
        String numberOfLines = savedInstanceState.getString("NumberOfLines");
        mCurrentlyShownNumberOfLines = sample_number_of_lines.valueOf(numberOfLines);
    }





}

