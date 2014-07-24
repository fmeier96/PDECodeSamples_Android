/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.genericstyle.pdedialogs;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListBaseAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListItem;

import android.content.Context;

import java.util.ArrayList;


//----------------------------------------------------------------------------------------------------------------------
// DialogsDefaultGenericListAdapter
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief The list adapter for the DialogsDefaultGenericActivity example.
 */
public class DialogsDefaultGenericListAdapter extends PDEListBaseAdapter {

    // a string array list that holds the keys of the various predefined default dialogs
    private final ArrayList<String> mDialogTitles;

    /**
     * @brief constructor
     *
     * @param context activity context
     * @param layoutTemplateResourceID Resource ID of the list item layout.
     * @param targetViewIDs Resource IDs of the subviews of the item view. Deliver all IDs of the subviews of which
     *                      you want to change the content later on.
     * @param objects a string array list that holds the keys of the various predefined default dialogs
     */
    public DialogsDefaultGenericListAdapter(Context context, int layoutTemplateResourceID, int[] targetViewIDs,
                                            ArrayList<String> objects){
        super(context,layoutTemplateResourceID,targetViewIDs);
        // remember dialog keys
        mDialogTitles = objects;
        // remember size of list
        setItemCount(mDialogTitles.size());
    }


    /**
     * Get the data item associated with the specified position in the data set.
     *
     * We don't carry any real data in this sample list. So we deliver just a debug String.
     *
     * @param position Position of the item whose data we want within the adapter's
     * data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mDialogTitles.get(position);
    }


    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * @brief Fill list item with actual data
     *
     * @param listItem the list item whose data should be updated.
     */
    @Override
    protected void fillListItem(PDEListItem listItem){
        int position;

        // get the current list position of this item
        position = listItem.getListPosition();
        // update R.id.PDEList_ItemText which is a PDETextView
        listItem.setTargetViewContent(R.id.PDEList_ItemText, mDialogTitles.get(position));
    }

}
