package com.group4.app.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.group4.app.model.ActionInput;
import com.group4.app.model.IController;

public class ActionController implements IController {
    private BlockingQueue<ActionInput<?>> actionQueue = new LinkedBlockingQueue<>();
    private static ActionController instance = null;

    private ActionController() { }

    public static ActionController getInstance() {
        if (instance == null) {
            instance = new ActionController();
        }
        return instance;
    }

    public void clearEventQueue() {
        actionQueue.clear();
    }

    /**
     * Enqueue an action to be performed
     * @param action
     */
    public void queueAction(ActionInput<?> action) {
        actionQueue.offer(action);
    }

    /**
     * Get the next queued action
     */
    @Override
    public ActionInput<?> getActionInput() {
        try {
            return actionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return null;
        }
    }
    
}
