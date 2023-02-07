package com.example.core.web;

import com.example.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger;
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String testid) {
        //MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service Id = " + testid);
    }
}
