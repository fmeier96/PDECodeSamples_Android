/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

import android.content.Context;
import android.view.View;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.commonstyle.pdelist.ListCheckboxSampleData;
import de.telekom.pde.codelibrary.ui.agents.PDEAgentController;
import de.telekom.pde.codelibrary.ui.components.buttons.PDEButton;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListBaseAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListItem;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDETextView;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

import java.util.ArrayList;


public class MyPDECheckBoxListTestAdapter extends PDEListBaseAdapter {

    //-----  properties ------------------------------------------------------------------------------------------------
    private ArrayList<ListCheckboxSampleData> mListData;


    /**
     * @brief constructor
     *
     * @param itemTemplateResourceID Resource ID for the layout of the list item.
     * @param targetViewIDs Resource IDs of the subviews of the item view. Deliver all IDs of the subviews of which
     *                      you want to change the content later on.
     */
    public MyPDECheckBoxListTestAdapter(Context context, int itemTemplateResourceID, int[] targetViewIDs){
        super(context,itemTemplateResourceID,targetViewIDs);
        // we use a custom holder that does not implement PDEHolderInterface so turn automatic creation off.
        setAutomaticHolderCreation(false);
    }


    /**
     * @brief constructor
     *
     * @param layoutResource Row-Layout-Resource for the list item.
     * @param listData Array that holds the data of the list.
     */
    @SuppressWarnings("unused")
    public MyPDECheckBoxListTestAdapter(Context context, int layoutResource, int[] targetViews,
                                        ArrayList<ListCheckboxSampleData> listData){
        super(context,layoutResource,targetViews);
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
     * @brief Initialize layout elements of the list item
     *
     * We don't use automatic holder generation, so we have to invest some work here.
     */
    protected void initListItem(PDEListItem listItem){
        ViewHolder holder;
        View layoutView;

        // get xml-layouted list item view
        layoutView = listItem.getLayoutedView();
        // create custom holder
        holder = new ViewHolder();
        // get checkbox subview and remember it
        holder.checkboxBtn = (PDEButton) layoutView.findViewById(R.id.button_checkboxx);
        // add listener for changing checked state when pressed
        holder.checkboxBtn.addListener(this,"buttonCheckboxPressed",
                PDEAgentController.PDE_AGENT_CONTROLLER_EVENT_ACTION_WILL_BE_SELECTED);
        holder.txtView = (PDETextView) layoutView.findViewById(R.id.PDEList_ItemText);
       // holder.testBtn = (PDEButton) layoutView.findViewById(R.id.button_test);

        // remember holder in tag
        layoutView.setTag(holder);
    }

    /**
     * @brief Fill list item with actual data
     */
    protected void fillListItem(PDEListItem listItem){
        ViewHolder holder;
        int position;

        // get our custom holder from tag
        holder = (ViewHolder)listItem.getLayoutedView().getTag();
        // get list position of item
        position = listItem.getListPosition();
        // get the title of the button from data source and set it in the view
        holder.checkboxBtn.setText((mListData.get(position)).getTitle());
        // restore checked state
        holder.checkboxBtn.setSelected((mListData.get(position)).getChecked());
        // remember list position
        holder.checkboxBtn.setTag(Integer.valueOf(position));
        holder.txtView.setText("Mail "+(position+1));
    }



    /**
     * @brief Comfort function for easy creation of Dummy data-sets.
     *
     * @param num The desired amount of dummy data-sets.
     */
    public void createDummyElements(int num){
        int i;
        ListCheckboxSampleData dummy;

        // create array if not already done
        if (mListData == null) {
            mListData = new ArrayList<ListCheckboxSampleData>();
        }
        // clear old data
        mListData.clear();

        // create new data-sets
        for (i = 1; i <= num; i++) {
            dummy = new ListCheckboxSampleData();
            dummy.setTitle("Check "+i);
            dummy.setChecked(false);
            mListData.add(dummy);
        }
    }


    /**
     * @brief Listener for checkbox presses.
     */
    @SuppressWarnings("unused")
    public void buttonCheckboxPressed(PDEEvent event) {
        int position;
        ListCheckboxSampleData elementData;
        // set/reset check of the button
        ((PDEButton)event.getSender()).setSelected(!((PDEButton) event.getSender()).isSelected());
        // get list position of clicked item from tag. (We stored the list position, when we created the element)
        position = ((Integer)((PDEButton)event.getSender()).getTag()).intValue();
        // remember check state in the data-object of our list element, so we can restore the state later on.
        // We need this, because we recycle our list elements for more performance.
        elementData = mListData.get(position);
        elementData.setChecked(((PDEButton) event.getSender()).isSelected());
    }

    /**
     * @brief Custom Holder Class.
     *
     * Stores the subview of the list item view for faster access.
     */
    static class ViewHolder {
        // checkbox button
        PDEButton checkboxBtn;
        PDETextView txtView;
    }


}
