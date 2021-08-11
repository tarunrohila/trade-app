/*
 * Copyright (c) 2019 Rohila Technologies, All Rights Reserved.
 *
 * This code is confidential to Rohila Technologies and shall not be disclosed outside the
 * organisation without the prior written permission of the IT Director of the organisation.
 *
 * In the event that such disclosure is permitted the code shall not be copied or disclosed other
 * than a need-to-know basis and any recipients may be required to sign a confidentiality
 * undertaking in favour of Rohila Technologies.
 */
package com.rohila.api.exception;

import com.rohila.api.util.JsonUtils;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract core exception handler
 *
 * @author Tarun Rohila
 */
public abstract class AbstractCoreExceptionHandler implements HandlerExceptionResolver, Ordered {

    /**
     * Method to handle Exception
     *
     * @param request  - httpServletRequest
     * @param response - httpServletResponse
     * @param handler  - handler
     * @param ex       - exception
     * @return object
     */
    protected abstract Object handleException(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex);

    /**
     * Method to create view
     *
     * @return view
     */
    protected View creatView() {
        MappingJackson2JsonView view = new MappingJackson2JsonView(JsonUtils.getObjectMapper());
        view.setExtractValueFromSingleKeyModel(true);
        return view;
    }

    /**
     * Method to resolve Exception
     *
     * @param request  - httpServletRequest
     * @param response - httpServletResponse
     * @param handler  - handler
     * @param e        - e
     * @return modelAndView
     */
    @Override
    public ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ModelAndView mav = new ModelAndView(creatView());
        Object httpResponse = handleException(request, response, handler, e);
        if (httpResponse != null) {
            return mav.addObject(httpResponse);
        }
        return null;
    }
}
