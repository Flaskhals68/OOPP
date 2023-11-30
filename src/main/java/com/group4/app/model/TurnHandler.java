package com.group4.app.model;
import java.util.LinkedList;
import java.util.Queue;

public class TurnHandler {
    private Queue<ITurnTaker> order;
    private ITurnTaker current;

    public TurnHandler(){
        this.order = new LinkedList<ITurnTaker>();
        this.current = null;
    }

    public void add(ITurnTaker turnTaker){
        this.order.add(turnTaker);
    }

    public void remove(ITurnTaker turnTaker){
        this.order.remove(turnTaker);
    }

    public void nextTurn(){
        startTurn();
        this.current.takeTurn();
        endTurn();
    }

    private void startTurn(){
        if (this.current == null){
            this.current = this.order.remove();
        }
        this.current.refillAp();
    }

    private void endTurn(){
        this.order.add(this.current);
        this.current = this.order.remove();
    }
}
