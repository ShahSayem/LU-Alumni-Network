import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Vector;

public class AlumniNetwork extends JFrame {
    private ImageIcon icon;
    public void initIcon(){
        icon = new ImageIcon(getClass().getResource("LUAlumni.png"));
        setIconImage(icon.getImage());
    }
    private int op = 1;
    private void setOp(int val){
        op = val;
    }
    private int getOp(){
        return op;
    }
    public AlumniNetwork(String myEmail) {
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

        JLabel headerLabel = new JLabel("Profiles: ");
        headerLabel.setFont(arialB16);
        headerLabel.setBounds(10, 5, 490, 30);
        headerPanel.add(headerLabel);

        JButton myProBtn = new JButton("My Profile");
        myProBtn.setBounds(200, 8, 120, 25);
        myProBtn.setBackground(new Color(73, 104, 140));
        myProBtn.setFont(arialB16);
        myProBtn.setFocusable(false);
        myProBtn.setForeground(Color.WHITE);
        myProBtn.setBorder(new LineBorder(Color.BLUE));
        headerPanel.add(myProBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(330, 8, 120, 25);
        logoutBtn.setBackground(new Color(73, 104, 140));
        logoutBtn.setFont(arialB16);
        logoutBtn.setFocusable(false);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBorder(new LineBorder(Color.BLUE));
        headerPanel.add(logoutBtn);

////Header-1
        JPanel headerPanel1 = new JPanel();
        headerPanel1.setBackground(new Color(199, 227, 240));
        headerPanel1.setBounds(0, 55, 500, 100);
        headerPanel1.setLayout(null);
        // mainPanel.add(headerPanel1);

        JLabel searchLabel = new JLabel("Search by: ");
        searchLabel.setFont(arialB16);
        searchLabel.setBounds(10, 5, 490, 25);
        headerPanel1.add(searchLabel);

        String options[] = {"Name", "Location", "Tech Stack"};
        JComboBox searchList = new JComboBox(options);
        searchList.setEditable(true);
        searchList.setBounds(10, 35, 100, 25);
        headerPanel1.add(searchList);

        JTextField searchTxt = new JTextField("Search here");
        searchTxt.setToolTipText("Enter name/location/tech stack");
        searchTxt.setFont(arialB16);
        searchTxt.setBounds(130, 35, 200, 25);
        headerPanel1.add(searchTxt);
        mainPanel.add(headerPanel1);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(350, 35, 100, 25);
        searchBtn.setBackground(new Color(73, 104, 140));
        searchBtn.setFont(arialB16);
        searchBtn.setFocusable(false);
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setBorder(new LineBorder(Color.BLUE));
        headerPanel1.add(searchBtn);

////Profiles
        JPanel proPanel = new JPanel();
        proPanel.setBackground(new Color(199, 227, 240));
        proPanel.setBounds(0, 150, 500, 2000);
        proPanel.setLayout(null);
        mainPanel.add(proPanel);

//Get the preffered profile
        String dataTable = "SELECT * FROM `newregister`";
        DBConnect db = new DBConnect(myEmail);
        Vector <JButton> profile = new Vector<JButton>();
        profile = db.ProfileCreate(dataTable, proPanel);

        for (Integer i = 0; i < profile.size(); i++) {
            Integer finalI = i;
            Vector<JButton> finalProfile = profile;
            profile.get(i).addActionListener(new ActionListener() {
                @Override //Work when profile[i] button pressed
                public void actionPerformed(ActionEvent e) {
                    String curr = finalProfile.get(finalI).getText();
                    String[] words = curr.split("\\s"); //splits the string based on whitespace

                    String email = words[words.length-1];
                    String queryEmail = "SELECT * FROM `newregister`";
                    DBConnect db2 = new DBConnect(myEmail);
                    db2.ShowProfile(queryEmail, email);
                    dispose();
                }
            });
        }

        searchList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  selectedOption = (String) searchList.getSelectedItem();
                int val;

                if (Objects.equals(selectedOption, "Name"))
                    setOp(1);
                else if (Objects.equals(selectedOption, "Location"))
                    setOp(2);
                else //Tech Stack
                    setOp(3);
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = searchTxt.getText();
                //System.out.println(op+"  "+key);
                dispose();
                new Search(op, key, myEmail);
            }
        });

        myProBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String queryMyEmail = "SELECT * FROM `newregister`";
                DBConnect db3 = new DBConnect(myEmail);
                dispose();
                db3.ShowProfile(queryMyEmail, myEmail);
            }
        });

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm();
            }
        });

        // Scrolling Feature
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setContentPane(scrollPane);

        setVisible(true);
    }
}
