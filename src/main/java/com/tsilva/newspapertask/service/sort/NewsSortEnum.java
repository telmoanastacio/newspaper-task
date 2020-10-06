package com.tsilva.newspapertask.service.sort;

/**
 * Created by Telmo Silva on 06.10.2020.
 */

public enum NewsSortEnum
{
    NAME("appName"),
    TIME_STAMP("updateTs");

    private String filterName;

    NewsSortEnum(String filterName)
    {
        this.filterName = filterName;
    }

    public String getFilterName()
    {
        return filterName;
    }

    public static NewsSortEnum getNewsSortEnum(String sort)
    {
        if(sort != null && sort.toUpperCase().equals(TIME_STAMP.filterName.toUpperCase()))
        {
            return TIME_STAMP;
        }
        else
        {
            return NAME;
        }
    }
}
