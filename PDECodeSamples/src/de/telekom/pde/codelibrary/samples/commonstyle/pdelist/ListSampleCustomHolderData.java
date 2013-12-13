/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;

//----------------------------------------------------------------------------------------------------------------------
//  ListSampleCustomHolderData
//----------------------------------------------------------------------------------------------------------------------

/**
 * @brief Just a data helper class for ListSampleCustomHolderActivity.
 *
 * Stores the list item data for the ListSampleCustomHolder example.
 */
public class ListSampleCustomHolderData {
    // text & image resource ID
    String mTitle;
    int mIconResource;

    /**
     * @brief Set text of text label.
     *
     * @param title list item title
     */
    public void setTitle(String title){
        mTitle = title;
    }


    /**
     * @brief Set resource id for icon.
     *
     * @param res icon resource
     */
    public void setIconResource(int res){
        mIconResource = res;
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
     * @brief Get resource id of icon.
     *
     * @return resource id of icon.
     */
    public int getIconResource(){
        return mIconResource;
    }
}
