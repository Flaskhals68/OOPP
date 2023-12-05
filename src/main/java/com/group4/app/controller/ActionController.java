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
    private static final int DELAY = 100;
    private BlockingQueue<ActionInput<?>> actionQueue = new LinkedBlockingQueue<>();
    private static ActionController instance = null;

    private ActionController() { }

    public static ActionController getInstance() {
        if (instance == null) {
            instance = new ActionController();
        }
        return instance;
    }

    public void queueAction(ActionInput<?> action) {
        actionQueue.add(action);
    }

    @Override
    public ActionInput<?> getActionInput() {
        while (actionQueue.isEmpty()) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return actionQueue.poll();
    }
    
}
