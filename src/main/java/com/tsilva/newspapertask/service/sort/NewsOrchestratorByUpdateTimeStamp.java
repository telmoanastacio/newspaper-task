package com.tsilva.newspapertask.service.sort;

import org.springframework.data.domain.Sort;

/**
 * Created by Telmo Silva on 06.10.2020.
 */

public class NewsOrchestratorByUpdateTimeStamp implements INewsOrchestrator<Long>
{
    @Override
    public Sort getSort()
    {
        return Sort.by(NewsSortEnum.TIME_STAMP.getFilterName());
    }

    @Override
    public Long getValue(String filter) throws RuntimeException
    {
        return Long.parseLong(filter);
    }
}
