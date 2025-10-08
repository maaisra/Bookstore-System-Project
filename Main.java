import Interface.Login;
import java.awt.*;

public class Main {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Login().setVisible(true));
    }
}
