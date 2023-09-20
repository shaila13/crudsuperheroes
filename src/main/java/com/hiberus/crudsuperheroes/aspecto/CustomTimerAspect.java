package com.hiberus.crudsuperheroes.aspecto;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.hiberus.crudsuperheroes.controller.SuperHeroeController;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class CustomTimerAspect {

    private long startTime;

    @Before("@annotation(com.hiberus.crudsuperheroes.anotacion.CustomTimer)")
    public void startTimer(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }

    @After("@annotation(com.hiberus.crudsuperheroes.anotacion.CustomTimer)")
    public void stopTimer(JoinPoint joinPoint) {
        long endTime = System.currentTimeMillis();
        log.info("El método " + joinPoint.getSignature().toShortString() + " tardó " + (endTime - startTime) + " ms en ejecutarse.");
    }
}