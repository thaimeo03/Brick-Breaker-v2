
package BB;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

    public static JFrame frame = new JFrame("Brick Breaker");

    //Theme 1 (Main Theme)
    public static Color bg_color;
    public static Color border_color;
    public static Color block_color;
    public static Color ball_color;
    public static Color player_color;

    public static MenuItem level_1 = new MenuItem("Level 1");
    public static MenuItem level_2 = new MenuItem("Level 2");
    public static MenuItem level_3 = new MenuItem("Level 3");
    public static MenuItem level_4 = new MenuItem("Level 4");

    public static int selected_level = 1;

    public static void theme1() {
        bg_color = Color.decode("#EAEAEA");
        block_color = Color.decode("#252A34");
        ball_color = Color.decode("#08D9D6");
        border_color = Color.decode("#FF2E63");
        player_color = Color.decode("#FF2E63");
    }

    public static void theme2() {
        bg_color = Color.decode("#252A34");
        block_color = Color.decode("#EAEAEA");
    }

    private static void call_level_1() {
        GamePlay.totalBricks = 21;
        MapGenerator.brickHeight = 50;
        GamePlay.map = new MapGenerator(3, 7);
    }

    private static void call_level_2() {
        GamePlay.totalBricks = 28;
        MapGenerator.brickHeight = 50;
        GamePlay.map = new MapGenerator(4, 7);
    }

    private static void call_level_3() {
        GamePlay.totalBricks = 40;
        MapGenerator.brickHeight = 50;
        GamePlay.map = new MapGenerator(5, 8);
    }

    private static void call_level_4() {
        GamePlay.totalBricks = 48;
        MapGenerator.brickHeight = 50;
        GamePlay.map = new MapGenerator(6, 8);
    }

    public static void select_level() {
        GamePlay.play = false;
        int result = JOptionPane.showConfirmDialog(frame, "Want to start new game", "Restart Game", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            GamePlay.ballposY = 400;
            GamePlay.ballXdir = -1;
            GamePlay.ballYdir = -2;
            GamePlay.score = 0;
            switch (selected_level) {
                case 1:
                    call_level_1();
                    break;
                case 2:
                    call_level_2();
                    break;
                case 3:
                    call_level_3();
                    break;
                case 4:
                    call_level_4();
                    break;
                default:
                    throw new AssertionError();
            }
        }
        GamePlay.play = true;
    }

    public static void main(String[] args) {
        GamePlay gamePlay = new GamePlay();

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Themes");
        Menu menu2 = new Menu("Levels");
        MenuItem menu_theme1 = new MenuItem("Light");
        MenuItem menu_theme2 = new MenuItem("Dark");
        menuBar.add(menu1);
        menuBar.add(menu2);
        menu1.add(menu_theme1);
        menu1.add(menu_theme2);
        menu2.add(level_1);
        menu2.add(level_2);
        menu2.add(level_3);
        menu2.add(level_4);

        //light theme
        menu_theme1.addActionListener(action -> theme1());
        //dark theme
        menu_theme2.addActionListener(action -> theme2());
        //default theme
        theme1();

        //levels
        level_1.addActionListener(action -> {
            if (!GamePlay.play) {
                call_level_1();
            } else {
                selected_level = 1;
                select_level();
            }
        });
        level_2.addActionListener(action -> {
            if (!GamePlay.play) {
                call_level_2();
            } else {
                selected_level = 2;
                select_level();
            }
        });
        level_3.addActionListener(action -> {
            if (!GamePlay.play) {
                call_level_3();
            } else {
                selected_level = 3;
                select_level();
            }
        });
        level_4.addActionListener(action -> {
            if (!GamePlay.play) {
                call_level_4();
            } else {
                selected_level = 4;
                select_level();
            }
        });
        //default level
        call_level_1();

        //frame adding components
        frame.setMenuBar(menuBar);
        frame.add(gamePlay);

        //frame set-up
        frame.setBounds(500, 75, 710, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
