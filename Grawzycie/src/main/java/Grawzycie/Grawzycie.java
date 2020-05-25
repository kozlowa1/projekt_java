/**/
package Grawzycie;

import java.awt.EventQueue;

public class Grawzycie {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame();
            }
        });
    }

}
