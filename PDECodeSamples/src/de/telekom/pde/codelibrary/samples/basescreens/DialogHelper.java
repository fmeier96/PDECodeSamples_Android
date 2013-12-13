/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2013. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.basescreens;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import de.telekom.pde.codelibrary.samples.R;

import java.util.ArrayList;


/**
 * @brief Dialog helper class to handle simple lists in a dialog.
 */
public class DialogHelper {

    // Private variables
    private Context mContext;
    private Dialog mDialog;
    private Button mInvokingButton;
    private ChoiceListOnItemClickListener mListViewOnItemClickListener;
    private ArrayList<String> mListViewArrayList;
    private ListView mListView;


    /**
     * @brief Helper interface for the click listener for the chooser dialog.
     */
    public interface ChoiceListOnItemClickListener {
        public void onListItemClicked(String itemContentString);
    }

    /**
     * @brief Constructor.
     */
    public DialogHelper(Context context, Button invokingButton) {
        Button cancelButton;

        mContext = context;
        mDialog = new Dialog(context);
        mInvokingButton = invokingButton;
        mListViewOnItemClickListener = null;
        mListViewArrayList = null;

        // create custom dialog
        getDialog().setContentView(R.layout.resize_base_choose_layout);

        // get list view and set choice mode
        mListView = (ListView) getDialog().findViewById(R.id.sizing_basescreen_choice_listview);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // set item click listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                sendItemClickedEvent(position);
                getDialog().dismiss();
            }
        });

        // get the cancel button and listen to clicks
        cancelButton  = (Button)getDialog().findViewById(R.id.choiceDialogButtonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    public void show() {
        mDialog.show();
    }

    /**
     * @brief Get the used dialog by this helper.
     */
    public Dialog getDialog() {
        return mDialog;
    }


    /**
     * @brief Set the click listener for the list view.
     */
    public void setChoiceListOnItemClickListener(ChoiceListOnItemClickListener listener) {
        //set new listener
        mListViewOnItemClickListener = listener;
    }


    /**
     * @brief Get the listview click listener.
     */
    public ChoiceListOnItemClickListener getChoiceListOnItemClickListener() {
        return mListViewOnItemClickListener;
    }


    /**
     * @brief Set the array with strings to show in the listview.
     */
    public void setArrayList(ArrayList<String> list) {
        //set new adapter to listview with this new list
        mListViewArrayList = list;
        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_checked, list);
        mListView.setAdapter(arrayAdapter);
    }


    /**
     * @brief Get the arraylist with strings used in the listview.
     */
    public ArrayList<String> getArrayList() {
        return mListViewArrayList;
    }


    /**
     * @brief Get button which cause/invokes this dialog to show.
     */
    public Button getInvokingButton() {
        return mInvokingButton;
    }


    /**
     * @brief Set the selection of a listview item .
     */
    public void setSelectionPos(int selectionPos) {
        mListView.setItemChecked(selectionPos,true);
        sendItemClickedEvent(selectionPos);
    }


    /**
     * @brief Send event to listener that item was clicked.
     */
    private void sendItemClickedEvent(int position) {
        //check pointer
        if(getChoiceListOnItemClickListener()!=null) {
            getChoiceListOnItemClickListener().onListItemClicked(getArrayList().get(position));
        }
    }
}
