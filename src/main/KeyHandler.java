// handles keyboard input #2
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {
    // #13 link with gp
    GamePanel gp;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // titleState
        if (gp.gameState == gp.titleState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        // playState
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {   // #13
                gp.gameState = gp.pauseState;
                }
            }

        // pauseState
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_SPACE) {   // #13
                gp.gameState = gp.playState;
            }
        }

//        if (gp.ui1.stageClear) {
//            if (code == KeyEvent.VK_ENTER) {
//                gp.setupGame();
//                gp.startGameThread();
//            }
//        }

        if (gp.ui1.staminaOut) {                // # 37 (Seamus' original implementation)
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState;
                gp.ui1.staminaOut = false;
                gp.player.setDefaultValues();
                gp.stopMusic();
                gp.ui1.playTime = 0;

                gp.setupGame();
                gp.startGameThread();
            }
        }

        if (gp.ui1.stageClear) {                // # 37
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.titleState;
                gp.ui1.stageClear = false;
                gp.player.setDefaultValues();
                gp.stopMusic();
                gp.ui1.playTime = 0;

                gp.setupGame();
                gp.startGameThread();
            }
        }
        }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
