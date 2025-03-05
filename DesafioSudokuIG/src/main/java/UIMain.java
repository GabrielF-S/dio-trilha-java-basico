import ui.custom.frame.MainFrame;
import ui.custom.painel.MainPanel;
import ui.custom.scream.MainScreen;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UIMain {
    public static void main(String[] args) {
       final Map<String, String> gameConfig = Stream.of(args)
               .collect(Collectors.toMap( k -> k.split(";")[0],
                       v-> v.split(";")[1]));

        MainScreen mainScreen = new MainScreen(gameConfig);
        mainScreen.builMainScreen();

    }
}
