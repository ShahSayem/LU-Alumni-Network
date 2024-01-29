import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
public class LoginForm extends JFrame {
    private ImageIcon icon;
    public void initIcon(){
        icon = new ImageIcon(getClass().getResource("LUAlumni.png"));
        setIconImage(icon.getImage());
    }
    public LoginForm() {
        super("LU Alumni Network");
        initIcon();

        setSize(500, 380);
        setLocationRelativeTo(null); //Middle position
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Font arialB16 = new Font("Arial", Font.BOLD, 16);
        Font cambriaB23 = new Font("Cambria", Font.BOLD, 23);

        JPanel headerPanel1 = new JPanel();
        headerPanel1.setBackground(new Color(80, 235, 173));
        headerPanel1.setBounds(0, 5, 500, 50);
        headerPanel1.setLayout(null);
        add(headerPanel1);

////Don't an account, than register
        JLabel regLabel = new JLabel("Don't have an account, than Register: ");
        regLabel.setFont(arialB16);
        regLabel.setBounds(10, 5, 500, 30);
        headerPanel1.add(regLabel);

//Register Button
        JButton regBtn = new JButton("Register");
        regBtn.setBounds(310, 10, 100, 30);
        regBtn.setBackground(new Color(73, 104, 140));
        regBtn.setFont(arialB16);
        regBtn.setFocusable(false);
        regBtn.setForeground(Color.WHITE);
        regBtn.setBorder(new LineBorder(Color.BLUE));
        headerPanel1.add(regBtn);

////Login
        JPanel headerPanel2 = new JPanel();
        headerPanel2.setBackground((new Color(199, 227, 240)));
        headerPanel2.setBounds(0, 55, 500, 60);
        headerPanel2.setLayout(null);
        add(headerPanel2);

        JLabel headerLabel = new JLabel("Login Form");
        headerLabel.setFont(cambriaB23);
        headerLabel.setBounds(180, 20, 200, 30);
        headerPanel2.add(headerLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(199, 227, 240));
        inputPanel.setBounds(0, 115, 500, 525);
        inputPanel.setLayout(null);
        add(inputPanel);

//Email
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(arialB16);
        emailLabel.setBounds(120, 20, 100, 25);
        inputPanel.add(emailLabel);

        JTextField emailTxt = new JTextField();
        emailTxt.setFont(arialB16);
        emailTxt.setBounds(200, 20, 200, 25);
        inputPanel.add(emailTxt);

//Password
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setFont(arialB16);
        passLabel.setBounds(90, 75, 100, 25);
        inputPanel.add(passLabel);

        JTextField passTxt = new JPasswordField();
        passTxt.setFont(arialB16);
        passTxt.setBounds(200, 75, 200, 25);
        inputPanel.add(passTxt);

//Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(300, 125, 100, 30);
        loginBtn.setBackground(new Color(73, 104, 140));
        loginBtn.setFont(arialB16);
        loginBtn.setFocusable(false);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBorder(new LineBorder(Color.BLUE));
        inputPanel.add(loginBtn);

        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterForm();
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override //Work when login button pressed
            public void actionPerformed(ActionEvent e) {
                String email = emailTxt.getText();
                String pass = passTxt.getText();

                String queryLogin = "SELECT * FROM `newregister`";
                DBConnect db = new DBConnect(email);
                int val = db.LoginMatch(queryLogin, email, pass);

                if (val == 1) {
                    dispose();
                    new AlumniNetwork(email);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong email or invalid pass","Error", JOptionPane.WARNING_MESSAGE);
                    dispose();
                    new LoginForm();
                }
            }
        });

        setVisible(true);
    }
}
