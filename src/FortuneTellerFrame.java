import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Main window for the Fortune Teller app.
 * Top: title + image. Middle: scrolling list of fortunes given so far.
 * Bottom: buttons to get a fortune or quit.
 */
public class FortuneTellerFrame extends JFrame {

    // Cheeky "Confucius says" fortunes -- at least 12 required, a few extra for good luck.
    private final String[] fortunes = {
            "Confucius says: Man who skip leg day at gym, walk funny forever.",
            "Confucius says: He who checks phone during nap, not really napping.",
            "Confucius says: Man who microwave fish in office kitchen, make no friends.",
            "Confucius says: He who reheats coffee five times, question own life choices.",
            "Confucius says: Man who says 'one more episode,' see sunrise instead.",
            "Confucius says: He who trips on flat ground, blame ground anyway.",
            "Confucius says: Man who leaves last slice of pizza, is very good liar.",
            "Confucius says: He who argues with GPS, still arrives late.",
            "Confucius says: Man who studies night before exam, learn art of panic.",
            "Confucius says: He who wears socks with sandals, fear no one's opinion.",
            "Confucius says: Man who names WiFi 'FBI Surveillance Van,' truly wise.",
            "Confucius says: He who laughs at own joke first, laughs alone.",
            "Confucius says: Man who says 'just five more minutes,' lie to self and clock.",
            "Confucius says: He who returns shopping cart, hero of parking lot.",
            "Confucius says: Man who codes without saving, learn patience the hard way."
    };

    private int lastIndex = -1;                 // index of the last fortune shown, so we don't repeat it
    private final Random random = new Random();

    // Separate fonts for each part of the UI, as required.
    private final Font titleFont = new Font("SansSerif", Font.BOLD, 40);
    private final Font buttonFont = new Font("SansSerif", Font.PLAIN, 20);
    private final Font fortuneFont = new Font("Monospaced", Font.PLAIN, 18);

    private JTextArea fortuneArea;

    public FortuneTellerFrame() {
        setTitle("Confucius Walken Says");
        setLayout(new BorderLayout());

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildMiddlePanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        sizeAndCenterOnScreen();
    }

    // Top panel: title label with an image icon above/below the text.
    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel();

        // Optional image -- drop a file named fortune_teller.png into the src folder
        // (IntelliJ copies it to the output folder automatically) and it will show up here.
        ImageIcon icon = null;
        java.net.URL imgURL = getClass().getResource("/fortune_teller.png");
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        }

        JLabel titleLabel = new JLabel("Confucius Walken Says", icon, SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLabel.setVerticalTextPosition(SwingConstants.BOTTOM); // text sits below the image
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        topPanel.add(titleLabel);
        return topPanel;
    }

    // Middle panel: scrollable text area where fortunes pile up, one per line.
    private JScrollPane buildMiddlePanel() {
        fortuneArea = new JTextArea();
        fortuneArea.setFont(fortuneFont);
        fortuneArea.setEditable(false);
        fortuneArea.setLineWrap(true);
        fortuneArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(fortuneArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    // Bottom panel: the two buttons.
    private JPanel buildBottomPanel() {
        JPanel bottomPanel = new JPanel();

        JButton readButton = new JButton("Read My Walken Fortune!");
        readButton.setFont(buttonFont);
        readButton.addActionListener(e -> {
            fortuneArea.append(getRandomFortune() + "\n");
            fortuneArea.setCaretPosition(fortuneArea.getDocument().getLength());
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(buttonFont);
        quitButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(readButton);
        bottomPanel.add(quitButton);
        return bottomPanel;
    }

    // Picks a random fortune that isn't the same one shown last time.
    private String getRandomFortune() {
        int index;
        if (fortunes.length == 1) {
            index = 0;
        } else {
            do {
                index = random.nextInt(fortunes.length);
            } while (index == lastIndex);
        }
        lastIndex = index;
        return fortunes[index];
    }

    // Sizes the frame to 3/4 of the screen width/height and centers it, using Toolkit.
    private void sizeAndCenterOnScreen() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int frameWidth = (int) (screenSize.width * 0.75);
        int frameHeight = (int) (screenSize.height * 0.75);
        setSize(frameWidth, frameHeight);

        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;
        setLocation(x, y);
    }
}
