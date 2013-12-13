/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;

//----------------------------------------------------------------------------------------------------------------------
//  ListCheckboxSampleData
//----------------------------------------------------------------------------------------------------------------------

/**
 * @brief Just a data helper class for ListCheckboxSampleActivity.
 *
 * Stores the list item data for the ListCheckboxSample example.
 */
public class ListCheckboxSampleData {
    // text and status of the checkbox button
    String mTitle;
    boolean mChecked = false;


    /**
     * @brief Set text of text label.
     *
     * @param title title of list element
     */
    public void setTitle(String title){
        mTitle = title;
    }


    /**
     * @brief Set current check status.
     *
     *  Remember if checkbox is currently checked or not.
     *
     * @param check new check status
     */
    public void setChecked(boolean check){
        mChecked = check;
    }


    /**
     * @brief Get text of text label.
     *
     * @return text of text label.
     */
    public String getTitle() {
        return mTitle;
    }


    /**
     * @brief Get current check status.
     *
     *  Returns if checkbox is currently checked or not.
     *
     * @return checkbox status
     */
    public boolean getChecked(){
        return mChecked;
    }
}
