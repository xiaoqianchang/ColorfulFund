package com.zritc.colorfulfund.data;

import org.json.JSONException;

/**
 * Data interface. Generally, each data class should implements this interface.
 * 
 * @author gufei
 * @version 1.0
 * @createDate 2014-07-23
 * @lastUpdate 2014-07-23
 */
public abstract class ZRDataCell {
    public abstract String getID();

    public abstract void setID(String id);

    public abstract ZRDataCell initFromJsonString(String str)
            throws JSONException;

    public abstract String getJsonString() throws JSONException;
}
