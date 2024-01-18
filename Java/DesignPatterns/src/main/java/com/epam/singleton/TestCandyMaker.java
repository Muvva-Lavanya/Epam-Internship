package com.epam.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCandyMaker
{
	public static void createInstance(){
        CandyMaker candyMaker = CandyMaker.getGlobalInstance();
        System.out.println(candyMaker);
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(TestCandyMaker::createInstance);
        service.submit(TestCandyMaker::createInstance);
        service.shutdown();

    }
}