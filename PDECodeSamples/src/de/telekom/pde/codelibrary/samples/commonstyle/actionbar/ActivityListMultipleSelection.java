package de.telekom.pde.codelibrary.samples.commonstyle.actionbar;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.actionbar.PDEActionModeHelper;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarListActivity;


public class ActivityListMultipleSelection extends PDEActionBarListActivity {

    private final String[] items = new String[]{"item 1",
                                                "item 2",
                                                "item 3",
                                                "item 4",
                                                "item 5",
                                                "item 6",
                                                "item 7",
                                                "item 8",
                                                "item 9",
                                                "item 10",
                                                "item 11",
                                                "item 12",
                                                "item 13",
                                                "item 14",
                                                "item 15",
                                                "item 16",
                                                "item 17",
                                                "item 18",
                                                "item 19",
                                                "item 20"};

    private final PDEActionModeHelper actionModeCallback = new PDEActionModeHelper() {
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
            getListView().clearChoices();
            getListView().setAdapter(getListAdapter());
        }


        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            super.onCreateActionMode(mode, menu);
            actionMode = mode;
            getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            return true;
        }

    };


    private class MyArrayAdapter extends ArrayAdapter<String> {

        public MyArrayAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
            super(context, resource, textViewResourceId, objects);
        }


        @Override
        public View getView(int position, View convertView, android.view.ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            if (view != null) {
                if (getListView().getCheckedItemPositions() != null
                    && getListView().getCheckedItemPositions().get(position)) {
                    // if the item is selected...
                    view.setBackgroundResource(R.drawable.multiple_select_listitem_selector_selected);
                } else {
                    view.setBackgroundResource(R.drawable.multiple_select_listitem_selector);
                }
            }
            return view;
        }
    }


    private ActionMode actionMode;
    private ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MyArrayAdapter(this, R.layout.listitem_row, R.id.rowText, items);
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                startSupportActionMode(actionModeCallback);
                return true;
            }
        });
        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (actionMode != null) {
                    adapter.notifyDataSetChanged();
                    //Attention, use actionModeCallback to set the custom title
                    actionModeCallback.setTitle("Selected " + getCheckedItemsCount());
                }
            }
        });
    }


    private int getCheckedItemsCount() {
        //We need this because getCheckedItemCount() is only from API 11
        int count = 0;
        for (int i = 0; i < getListAdapter().getCount(); i++) {
            if (getListView().isItemChecked(i)) {
                count++;
            }
        }
        return count;
    }

}
