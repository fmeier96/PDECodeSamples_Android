/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;

//----------------------------------------------------------------------------------------------------------------------
// PDEListPlainTextMultiLineActivity
//----------------------------------------------------------------------------------------------------------------------

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


/**
 * @brief PDEList example with multiple lines of text.
 *
 * In this example we have a main text and a subtext. So we have at least two lines of text. The subtext can be
 * switched to have more than one line of text.
 */
public class PDEListPlainTextMultiLineActivity extends PDEActionBarActivity {
    // number of text lines, two or multiple.
    private enum sample_number_of_lines {
        two, multiple
    }


    // private class definition for data set
    private class StringStringHashMap extends HashMap<String, String> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5916624274088761447L;
    }


    // hash map key constants
    public final static String ITEM_TITLE = "title";
    public final static String ITEM_TEXT = "text";

    // number of list elements
    private final static int NUMBER_OF_LIST_ITEMS_SHOWN = 1000;
    // the pde list view
    private PDEListView mList;
    // make array with ids of our target views (sub views of the list item layout)
    private int[] targetViewIDs = new int[]{R.id.PDEList_ItemText, R.id.PDEList_ItemSubText};
    // store number of lines we want to show (two/multiple)
    private sample_number_of_lines mCurrentlyShownNumberOfLines;
    // data set for single line content text
    List<StringStringHashMap> mDataSet = new LinkedList<StringStringHashMap>();
    // data set for multi line content text
    List<StringStringHashMap> mDataSetMultiple = new LinkedList<StringStringHashMap>();


    /**
     * @brief onCreate
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.pde_list_activity);

        // get pde list view
        mList = (PDEListView) findViewById(R.id.pde_list);

        // clear data sets
        mDataSet.clear();
        mDataSetMultiple.clear();
        // create data sets
        createDataSet(sample_number_of_lines.multiple);
        createDataSet(sample_number_of_lines.two);

        // default number of lines
        mCurrentlyShownNumberOfLines = sample_number_of_lines.multiple;

        // Creation of Adapter is moved to the onResume() method, so we can restore the adapter with the current
        // number of lines setting after rotation of the device

        // create callback in order to get informed when a list item was clicked.
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // the content of a list item should be a hash map that carries (amongst others) a string title as payload
                // get list item object
                Object object = mList.getItemAtPosition(position);
                String element = "";
                // check if list item object is really a hash map
                if (object instanceof StringStringHashMap) {
                    // extract the title from the hash map
                    element = ((StringStringHashMap) object).get(ITEM_TITLE);
                }

                // show selected item
                Toast.makeText(PDEListPlainTextMultiLineActivity.this, "Selected : " + element,
                               Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * @brief Create adapter for current number of lines on resume.
     */
    @Override
    protected void onResume() {
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

        // find fitting layout for wanted number of lines
        switch (numberOfLines) {
            case two:
                layout_row_id = R.layout.pde_list_plain_text_two_line_large_row;
                two = true;
                break;
            case multiple:
                layout_row_id = R.layout.pde_list_plain_text_multi_line_row;
                two = false;
                break;
        }

        // create new list adapter with current settings
        PDESimpleAdapter simpleAdapter = new PDESimpleAdapter(this,
                                                              (two) ? mDataSet : mDataSetMultiple,
                                                              layout_row_id,
                                                              new String[]{ITEM_TITLE, ITEM_TEXT},
                                                              targetViewIDs);

        // Set the adapter in our list
        mList.setAdapter(simpleAdapter);

        // remember current size
        mCurrentlyShownNumberOfLines = numberOfLines;
    }


//---------------------------------------------------------------------------------------------------------------------
// ----- Creation of data sets ----------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------


    /**
     * @brief Helper that creates and returns one item of our data set.
     *
     * @param title title of the list item
     * @param text content text of the list item
     *
     * @return item of the data set
     */
    public StringStringHashMap createItem(String title, String text) {
        StringStringHashMap item = new StringStringHashMap();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_TEXT, text);
        return item;
    }


    /**
     * @brief Helper that creates the whole data set with the configured number of items.
     *
     * There are also two layout variants. In one of them there is just one line of content text, in the other one
     * there are multiple lines of content text. The amount of content text which is stored within the data set
     * depends on the chosen layout. So we produce two data sets with this method. The decision which one is produced
     * is regulated by the parameter
     *
     * @param numberOfLines Decides for which layout the data set is produced.
     */
    protected void createDataSet(sample_number_of_lines numberOfLines) {
        String title;
        int mod;

        for (int i = 0; i < NUMBER_OF_LIST_ITEMS_SHOWN; i++) {
            title = i + ") ";
            mod = i % 4;
            if (mod == 0) {
                title += "Lorem ipsum dolor";
            } else if (mod == 1) {
                title += "Conseteur sadipscing";
            } else if (mod == 2) {
                title += "Nonumy eirmod sed diam";
            } else if (mod == 3) {
                title += "Tempor invidunt ut";
            }
            if (numberOfLines == sample_number_of_lines.multiple) {
                // for the layout with multiple content lines we produce a lot of text.
                mDataSetMultiple.add(createItem(title, "Tempor invidunt ut labore et dolore magna aliquyam " +
                                                       "erat, sed diam voluptua. At vero eos et accusam et justo " +
                                                       "duo dolores et ea rebum."));
            } else {
                // if the layout should show only one content line we also need less text in the data set
                mDataSet.add(createItem(title, "Tempor invidunt ut labore"));
            }
        }
    }


//---------------------------------------------------------------------------------------------------------------------
// ----- Changing of number of lines ----------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------

// All code beneath this point only serves the purpose to switch between the number of lines of the list elements. This
// prevents us from writing a lot of classes with nearly 100% redundant code, which would have to be maintained. But
// the following code is no real part of the example that shows how to get a pde list running.


    /**
     * @brief Create options menu that allows to change the number of lines of the list items.
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_samples_number_of_lines, menu);
        return true;
    }


    /**
     * @brief Shows / hides option menu entries depending on actual number of lines selection.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // set correct visibility for all menu items
        if (menu != null) {
            MenuItem item;
            item = menu.findItem(R.id.menu_list_samples_number_of_lines_two);
            if (item != null) {
                if (mCurrentlyShownNumberOfLines == sample_number_of_lines.two) {
                    item.setVisible(false);
                } else {
                    item.setVisible(true);
                }
            }
            item = menu.findItem(R.id.menu_list_samples_number_of_lines_multiple);
            if (item != null) {
                if (mCurrentlyShownNumberOfLines == sample_number_of_lines.multiple) {
                    item.setVisible(false);
                } else {
                    item.setVisible(true);
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }


    /**
     * @param item The item of the option menu which was selected.
     * @brief Listener for clicked option menu item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list_samples_number_of_lines_two:
                setAdapterForNumberOfLines(sample_number_of_lines.two);
                return true;
            case R.id.menu_list_samples_number_of_lines_multiple:
                setAdapterForNumberOfLines(sample_number_of_lines.multiple);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * @brief Store current number of lines setting before device rotation.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // store number of lines setting
        savedInstanceState.putString("NumberOfLines", mCurrentlyShownNumberOfLines.name());
        super.onSaveInstanceState(savedInstanceState);
    }


    /**
     * @brief Restore current number of lines setting after device rotation.
     */
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore number of lines setting
        String numberOfLines = savedInstanceState.getString("NumberOfLines");
        mCurrentlyShownNumberOfLines = sample_number_of_lines.valueOf(numberOfLines);
    }

}

