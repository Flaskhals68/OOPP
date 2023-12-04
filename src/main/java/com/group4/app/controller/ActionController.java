package com.group4.app.controller;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


import com.group4.app.model.ActionInput;
import com.group4.app.model.IController;

public class ActionController implements IController {
    private BlockingQueue<ActionInput<?>> actionQueue = new LinkedBlockingQueue<>();
    private Timer timer;

    private ActionController() {
        
    }

    public void queueAction(ActionInput<?> action) {
        actionQueue.add(action);
    }

    @Override
    public ActionInput<?> getActionInput() {
        CountDownLatch latch = new CountDownLatch(1);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!actionQueue.isEmpty()) {
                    latch.countDown();
                }
            }
        }, 100);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return actionQueue.poll();
    }
    
}
