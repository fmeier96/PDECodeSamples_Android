/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdelist;

//----------------------------------------------------------------------------------------------------------------------
// PDEListPlainGraphicMultiLineAdapter
//----------------------------------------------------------------------------------------------------------------------

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListBaseAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListItem;

import android.content.Context;

/**
 * @brief Example of an adapter for a list item that consists of a graphic and multiple lines of text.
 *
 * Of course every developer has to implement his own version of a list adapter which fits his own needs in matters
 * of data structure and layout of the particular list item views. But we want the list items to have the highlight /
 * selection behaviour in the way it was defined by the styleguide. In order to do this we have to wrap the list item
 * views (defined in xml) into PDEListItems. The PDEListBaseAdapter handles all this stuff,
 * so it's strongly recommended to derive your own Adapters from PDEListBaseAdapter. That will make developing much
 * easier for you, as you can see in this example.
 */
public class PDEListPlainGraphicMultiLineAdapter extends PDEListBaseAdapter {

    // flag that shows if we want to use just two lines of text or more.
    protected boolean mJustTwoLines;


    /**
     * @brief constructor
     *
     * @param context
     * @param layoutTemplateResourceID Resource ID of the list item layout.
     * @param targetViewIDs Resource IDs of the subviews of the item view. Deliver all IDs of the subviews of which
     *                      you want to change the content later on.
     */
    public PDEListPlainGraphicMultiLineAdapter(Context context, int layoutTemplateResourceID, int[] targetViewIDs){
        super(context,layoutTemplateResourceID,targetViewIDs);
        mJustTwoLines = false;
    }


    /**
     * @brief Get the data item associated with the specified position in the data set.
     *
     * We don't carry any real data in this sample list. So we deliver just a debug String.
     *
     * @param position Position of the item whose data we want within the adapter's
     * data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return String.format("Item %d",position);
    }


    /**
     * @brief Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }



    /**
     * @brief Fill list item with actual data.
     *
     * @param listItem the list item whose data should be updated.
     */
    @Override
    protected void fillListItem(PDEListItem listItem){
        int position, mod;
        // init
        String text = "";
        int imgRes = R.drawable.kids;
        String subText = "";

        // get the current list position of this item
        position = listItem.getListPosition();
        // select blind text & graphic
        mod = position % 4;
        if (mod == 0){
            text = "Lorem ipsum dolor";
            imgRes = R.drawable.kids;
        } else if (mod == 1){
            text = "Conseteur sadipscing";
            imgRes = R.drawable.couple;
        } else if (mod == 2){
            text = "Nonumy eirmod sed diam";
            imgRes = R.drawable.kids;
        } else if (mod == 3){
            text = "Tempor invidunt ut";
            imgRes = R.drawable.couple;
        }

        // adapt amount of subtext to the number of lines setting
        if (mJustTwoLines){
            subText = "Tempor invidunt ut labore";
        } else {
            subText = "Tempor invidunt ut labore et dolore magna aliquyam " +
                      "erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.";
        }


        // update R.id.PDEList_ItemMainText which is a PDELayerTextView
        listItem.setTargetViewContent(R.id.PDEList_ItemText, String.format(text+" (%d)", position));
        // update R.id.PDEList_ItemImage which is a PDEPhotoFrameView
        listItem.setTargetViewContent(R.id.PDEList_ItemImage, imgRes);
        // update R.id.PDEList_ItemSubText which is a PDELayerTextView
        listItem.setTargetViewContent(R.id.PDEList_ItemSubText,subText);
    }


    /**
     * @brief Limits MultiLineAdapter to use just two lines, not more.
     *
     * @param twoLines true -> use just two lines, false -> use multiple lines
     */
    public void setJustTwoLines(boolean twoLines){
        mJustTwoLines = twoLines;
    }

    /**
     * @brief Finds out if we use just two or multiple lines.
     *
     * @return true -> two lines, false -> multiple lines
     */
    public boolean hasJustTwoLines(){
        return mJustTwoLines;
    }

}
