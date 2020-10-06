package com.tsilva.newspapertask.service;

import com.tsilva.newspapertask.controller.rest.contract.request.EpaperRequest;
import com.tsilva.newspapertask.persistence.entity.DeviceInfo;
import com.tsilva.newspapertask.persistence.entity.GetPages;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

public interface INewsService
{
    public abstract void saveNews(DeviceInfo deviceInfo, GetPages getPages);

    /**
     *
     * @param sort type (appName or updateTs)
     * @param filter value matching sort parameter
     * @return 10 results
     */
    @Nullable
    public abstract List<EpaperRequest> getNews(@Nullable String sort, @Nullable Integer page, @Nullable String filter);
}
