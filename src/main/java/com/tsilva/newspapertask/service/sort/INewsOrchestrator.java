package com.tsilva.newspapertask.service.sort;

import org.springframework.data.domain.Sort;

/**
 * Created by Telmo Silva on 06.10.2020.
 */

public interface INewsOrchestrator<T>
{
    public abstract Sort getSort();

    public abstract T getValue(String filter) throws RuntimeException;
}
