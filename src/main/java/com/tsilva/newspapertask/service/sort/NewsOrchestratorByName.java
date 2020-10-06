package com.tsilva.newspapertask.service.sort;

import org.springframework.data.domain.Sort;

/**
 * Created by Telmo Silva on 06.10.2020.
 */

public class NewsOrchestratorByName implements INewsOrchestrator<String>
{
    @Override
    public Sort getSort()
    {
        return Sort.by(NewsSortEnum.NAME.getFilterName());
    }

    @Override
    public String getValue(String filter) throws RuntimeException
    {
        return filter;
    }
}
