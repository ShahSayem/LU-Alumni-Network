import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class Search extends JFrame{
    private ImageIcon icon;
    public void initIcon(){
        icon = new ImageIcon(getClass().getResource("LUAlumni.png"));
        setIconImage(icon.getImage());
    }
    public Search(int op, String key, String myEmail) {
        super("LU Alumni Network");
        initIcon();

        setSize(500, 600);
        setLocationRelativeTo(null); //Middle position
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Font arialB16 = new Font("Arial", Font.BOLD, 16);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(200, 2000)); // for Scrolling. (Set the preferred size of the panel to be larger than the viewport)

////Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(80, 235, 173));
        headerPanel.setBounds(0, 5, 500, 50);
        headerPanel.setLayout(null);
        mainPanel.add(headerPanel);

        JLabel proLabel = new JLabel("Want to go to profile list: ");
        proLabel.setFont(arialB16);
        proLabel.setBounds(10, 8, 490, 30);
        headerPanel.add(proLabel);

//Profile Button
        JButton proBtn = new JButton("Profiles");
        proBtn.setBounds(210, 10, 100, 30);
        proBtn.setBackground(new Color(73, 104, 140));
        proBtn.setFont(arialB16);
        proBtn.setFocusable(false);
        proBtn.setForeground(Color.WHITE);
        proBtn.setBorder(new LineBorder(Color.BLUE));
        headerPanel.add(proBtn);

////Header-1
        JPanel headerPanel1 = new JPanel();
        headerPanel1.setBackground(new Color(199, 227, 240));
        headerPanel1.setBounds(0, 55, 500, 50);
        headerPanel1.setLayout(null);
        mainPanel.add(headerPanel1);

        JLabel resultLabel = new JLabel("Results: ");
        resultLabel.setFont(arialB16);
        resultLabel.setBounds(10, 5, 490, 30);
        headerPanel1.add(resultLabel);

////Results
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(199, 227, 240));
        resultPanel.setBounds(0, 105, 500, 2000);
        resultPanel.setLayout(null);
        mainPanel.add(resultPanel);

        proBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AlumniNetwork(myEmail);
            }
        });

//Get the preffered results
        String dataTable = "SELECT * FROM `newregister`";
        DBConnect db = new DBConnect(myEmail);
        Vector<JButton> results = new Vector<JButton>();
        results = db.SearchProfile(dataTable, op, key, resultPanel);

        for (Integer i = 0; i < results.size(); i++) {
            Integer finalI = i;
            Vector<JButton> finalResults = results;
            results.get(i).addActionListener(new ActionListener() {
                @Override //Work when results[i] button pressed
                public void actionPerformed(ActionEvent e) {
                    String curr = finalResults.get(finalI).getText();
                    String[] words = curr.split("\\s"); //splits the string based on whitespace

                    String email = words[words.length-1];
                    String queryEmail = "SELECT * FROM `newregister`";
                    DBConnect db2 = new DBConnect(myEmail);
                    db2.ShowProfile(queryEmail, email);
                    dispose();
                }
            });
        }

        // Scrolling Feature
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setContentPane(scrollPane);

        setVisible(true);
    }
}
