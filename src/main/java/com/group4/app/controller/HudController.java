package com.group4.app.controller;

public class HudController {
    private boolean attackAllowed = true;
    public boolean attackAllowed() {
        // TODO: Implement logic to check if attack is allowed
        System.out.println("Attack allowed : " + attackAllowed);
        return attackAllowed;
    }

    public void enterAttackState() {
        // TODO: Implement logic to enter attack state
        // System.err.println("Enter attack state");
        if (attackAllowed) System.out.println("Enter attack state");
        else System.out.println("Enter attack state");
        attackAllowed = false;
    }
}
