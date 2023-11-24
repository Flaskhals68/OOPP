// package com.group4.app.view;

// import java.awt.Color;
// import java.awt.Font;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

// import com.group4.app.controller.HudController;



// public class AttackButton extends ActionButton {
//     private boolean isEnabled;

//     public AttackButton(String text, Font font, HudController controller) {
//         super(text, font, controller);
//         this.setBackground(Color.RED);

//         this.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseClicked(java.awt.event.MouseEvent evt) {
//                 getController().enterAttackState();
//                 // updateState();
//             }
//         });
//         this.isEnabled = true;
//     }

//     public void setDisabled() {
//         this.setEnabled(false);
//         this.setBackground(Color.GRAY);
//     }

//     public void setEnabled() {
//         this.setEnabled(true);
//         this.setBackground(Color.RED);
//     }

//     public void toggle() {
//         if (this.isEnabled) {
//             this.setDisabled();
//         } else {
//             this.setEnabled();
//         }
//     }

//     // public void updateState() {
//     //     boolean isEnabled = getController().attackAllowed();
//     //     this.setEnabled(isEnabled);

//     //     if (isEnabled) {
//     //         this.setBackground(Color.RED);
//     //     } else {
//     //         this.setBackground(Color.GRAY);
//     //     }
//     // }
    

// }
