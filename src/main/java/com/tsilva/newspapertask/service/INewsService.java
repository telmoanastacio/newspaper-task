package com.tsilva.newspapertask.service;

import com.tsilva.newspapertask.persistence.entity.DeviceInfo;
import com.tsilva.newspapertask.persistence.entity.GetPages;
import org.springframework.lang.Nullable;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

public interface INewsService
{
    public abstract void saveNews(DeviceInfo deviceInfo, GetPages getPages);

    /**
     *
     * @param sort type (alphanumeric or date)
     * @param filter value matching sort parameter
     */
    public abstract void getNews(String sort, int page, @Nullable String filter);
}
