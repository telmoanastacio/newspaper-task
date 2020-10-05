package com.tsilva.newspapertask.controller.rest.validator;

/**
 * Created by Telmo Silva on 05.10.2020.
 */

public interface XsdValidator
{
    /**
     * @return true if xml is valid against xsd
     */
    public abstract boolean validate(String xmlStr);
}
