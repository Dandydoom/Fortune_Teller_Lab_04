import javax.swing.JFrame;

/**
 * Entry point: just builds the frame and shows it.
 */
public class FortuneTellerViewer {
    public static void main(String[] args) {
        FortuneTellerFrame frame = new FortuneTellerFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
