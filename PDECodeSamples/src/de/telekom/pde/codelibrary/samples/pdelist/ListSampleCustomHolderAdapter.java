/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdelist;

//----------------------------------------------------------------------------------------------------------------------
//  ListSampleCustomHolderAdapter
//----------------------------------------------------------------------------------------------------------------------

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListBaseAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListItem;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @brief This small PDEList example just shows, that it's also possible to use your very own holder class for the
 * list adapter.
 *
 * Of course every developer has to implement his own version of a list adapter which fits his own needs in matters
 * of data structure and layout of the particular list item views. But we want the list items to have the highlight /
 * selection behaviour in the way it was defined by the styleguide. In order to do this we have to wrap the list item
 * views (defined in xml) into PDEListItems. The PDEListBaseAdapter handles all this stuff,
 * so it's strongly recommended to derive your own Adapters from PDEListBaseAdapter. That will make developing much
 * easier for you, as you can see in this example. In this example we use a custom Holder class which does not
 * implement PDEHolderInterface. So we have to do all the holder handling on our own and have to turn off the
 * automatic holder creation in order to save performance.
 */
public class ListSampleCustomHolderAdapter extends PDEListBaseAdapter {

//-----  properties ---------------------------------------------------------------------------------------------------
    private ArrayList<ListSampleCustomHolderData> mListData;

    /**
     * @brief constructor
     *
     * @param context
     * @param itemTemplateResourceID Resource ID for the layout of the list item.
     * @param targetViewIDs Resource IDs of the subviews of the item view. Deliver all IDs of the subviews of which
     *                      you want to change the content later on.
     */
    public ListSampleCustomHolderAdapter(Context context, int itemTemplateResourceID, int[] targetViewIDs){
        super(context,itemTemplateResourceID,targetViewIDs);
        // we use a custom holder that does not implement PDEHolderInterface so turn automatic creation off.
        setAutomaticHolderCreation(false);
    }


    /**
     * @brief constructor
     *
     * @param context
     * @param layoutResource Row-Layout-Ressource for the list item.
     * @param listData Array that holds the data of the list.
     */
    public ListSampleCustomHolderAdapter(Context context, int layoutResource, int[] targetViews,
                                         ArrayList<ListSampleCustomHolderData> listData){
        super(context,layoutResource, targetViews);
        mListData = listData;
        // we use a custom holder that does not implement PDEHolderInterface so turn automatic creation off.
        setAutomaticHolderCreation(false);
    }


    /**
     * @brief How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mListData.size();
    }

    /**
     * @brief Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     * data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mListData.get(position);
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
     * @brief Initializes a new list item.
     *
     * After a new list item has been built, it can be initialized by this method. If the built-in PDEHolder
     * is used this step can be skipped, because all the needed initialisation is done automatically. If a custom
     * holder is used, you need to do the initialisation work on your own within this function.
     *
     * @param listItem the listitem that should be initialized
     */
    protected void initListItem(PDEListItem listItem){
        ViewHolder holder;
        View layoutView;

        // get the already inflated version of your xml-layouted list item view.
        layoutView = listItem.getLayoutedView();
        // create your custom holder
        holder = new ViewHolder();
        // resolve the needed subviews of your list item view and store the subviews in your holder for faster access.
        holder.titleView = (TextView) layoutView.findViewById(R.id.title);
        holder.iconView = (ImageView) layoutView.findViewById(R.id.icon);
        // store your custom holder in the tag of your list item view.
        layoutView.setTag(holder);
    }


    /**
     * @brief Fill list item with actual data.
     *
     * @param listItem the list item whose data should be updated.
     */
    protected void fillListItem(PDEListItem listItem) {
        ViewHolder holder;
        int position;

        // get the current list position of this item
        position = listItem.getListPosition();
        // get your custom holder instance from the tag of your list item view.
        holder = (ViewHolder) listItem.getLayoutedView().getTag();

        // access the subviews of your list items with the help of your custom holder and update them with the actual
        // data.

        // set the text of the text label
        holder.titleView.setText(((ListSampleCustomHolderData)mListData.get(position)).getTitle());
        // set the color of the text label
        holder.titleView.setTextColor(PDEColor.DTUITextColor().getIntegerColor());
        // set the resource id for the icon
        holder.iconView.setImageResource(((ListSampleCustomHolderData)mListData.get(position)).getIconResource());
    }


    /**
     * @brief Comfort function for easy creation of Dummy data-sets.
     *
     * @param num The desired amount of dummy data-sets.
     */
    public void createDummyElements(int num, int IconRessource){
        int i;
        ListSampleCustomHolderData dummy;

        // create array if not already done
        if (mListData == null) {
            mListData = new ArrayList<ListSampleCustomHolderData>();
        }
        // clear old data
        mListData.clear();

        // create new data-sets
        for (i = 1; i <= num; i++) {
            dummy = new ListSampleCustomHolderData();
            dummy.setTitle(new String("List line "+i));
            dummy.setIconResource(IconRessource);
            mListData.add(dummy);
        }
    }

    /**
     * @brief Custom Holder Class
     *
     * Stores the subviews of the list item view for faster access.
     */
    static class ViewHolder {
        // text label
        TextView titleView;
        // icon
        ImageView iconView;
    }


}
