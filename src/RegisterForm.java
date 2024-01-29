import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class RegisterForm extends JFrame {
    private ImageIcon icon, img;
    private JLabel imgLabel;
    String s;
    public void initIcon(){
        icon = new ImageIcon(getClass().getResource("LUAlumni.png"));
        setIconImage(icon.getImage());
    }
    public void initBackgroundImage()
    {
        img = new ImageIcon((getClass().getResource("image1.jpg")));
        imgLabel = new JLabel(img);
        imgLabel.setBounds(600, 200, img.getIconWidth(), img.getIconHeight());
        add(imgLabel);
    }
    public RegisterForm() {
        super("LU Alumni Network");
        initIcon();

        setSize(1000, 800);
        setLocationRelativeTo(null); //Middle position
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        initBackgroundImage();

        Font arialB16 = new Font("Arial", Font.BOLD, 16);
        Font cambriaB23 = new Font("Cambria", Font.BOLD, 23);

        JPanel headerPanel1 = new JPanel();
        headerPanel1.setBackground(new Color(80, 235, 173));
        headerPanel1.setBounds(0, 5, 1000, 50);
        headerPanel1.setLayout(null);
        add(headerPanel1);

////Have an account, than login
        JLabel loginLabel = new JLabel("Already have an account, than login: ");
        loginLabel.setFont(arialB16);
        loginLabel.setBounds(10, 5, 490, 30);
        headerPanel1.add(loginLabel);

//Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(300, 10, 100, 30);
        loginBtn.setBackground(new Color(73, 104, 140));
        loginBtn.setFont(arialB16);
        loginBtn.setFocusable(false);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBorder(new LineBorder(Color.BLUE));
        headerPanel1.add(loginBtn);

////Registration
        JPanel headerPanel2 = new JPanel();
        headerPanel2.setBackground(Color.white);
        headerPanel2.setBounds(0, 55, 1000, 35);
        headerPanel2.setLayout(null);
        add(headerPanel2);

        JLabel headerLabel = new JLabel("Registration Form");
        headerLabel.setFont(cambriaB23);
        headerLabel.setForeground(new Color(116, 2, 230));
        headerLabel.setBounds(170, 5, 200, 35);
        headerPanel2.add(headerLabel);

// Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.setBounds(0, 80, 1000, 700);
        inputPanel.setLayout(null);
        add(inputPanel);

//Name
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(arialB16);
        nameLabel.setBounds(129, 25, 100, 25);
        inputPanel.add(nameLabel);

        JTextField nameTxt = new JTextField();
        nameTxt.setFont(arialB16);
        nameTxt.setBounds(200, 25, 200, 25);
        inputPanel.add(nameTxt);

//Email
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(arialB16);
        emailLabel.setBounds(129, 70, 100, 25);
        inputPanel.add(emailLabel);

        JTextField emailTxt = new JTextField();
        emailTxt.setFont(arialB16);
        emailTxt.setBounds(200, 70, 200, 25);
        inputPanel.add(emailTxt);

//Password
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setFont(arialB16);
        passLabel.setBounds(97, 115, 100, 25);
        inputPanel.add(passLabel);

        JTextField passTxt = new JPasswordField();
        passTxt.setFont(arialB16);
        passTxt.setBounds(200, 115, 200, 25);
        inputPanel.add(passTxt);

//Confirm Password
        JLabel conPassLabel = new JLabel("Confirm Password: ");
        conPassLabel.setFont(arialB16);
        conPassLabel.setBounds(32, 160, 170, 25);
        inputPanel.add(conPassLabel);

        JTextField conPassTxt = new JPasswordField();
        conPassTxt.setFont(arialB16);
        conPassTxt.setBounds(200, 160, 200, 25);
        inputPanel.add(conPassTxt);

//Mobile
        JLabel mobileLabel = new JLabel("Mobile: ");
        mobileLabel.setFont(arialB16);
        mobileLabel.setBounds(120, 205, 100, 25);
        inputPanel.add(mobileLabel);

        JTextField mobileTxt = new JTextField();
        mobileTxt.setFont(arialB16);
        mobileTxt.setBounds(200, 205, 200, 25);
        inputPanel.add(mobileTxt);

//Address
        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(arialB16);
        addressLabel.setBounds(110, 250, 100, 25);
        inputPanel.add(addressLabel);

        JTextField addressTxt = new JTextField();
        addressTxt.setFont(arialB16);
        addressTxt.setBounds(200, 250, 200, 25);
        inputPanel.add(addressTxt);

//Batch
        JLabel batchLabel = new JLabel("Batch: ");
        batchLabel.setFont(arialB16);
        batchLabel.setBounds(127, 295, 100, 25);
        inputPanel.add(batchLabel);

        JTextField batchTxt = new JTextField();
        batchTxt.setFont(arialB16);
        batchTxt.setBounds(200, 295, 200, 25);
        inputPanel.add(batchTxt);

//Optional
        JLabel optionalLabel = new JLabel("(Optionals)");
        optionalLabel.setFont(arialB16);
        optionalLabel.setForeground(new Color(2, 179, 108));
        optionalLabel.setBounds(110, 340, 100, 25);
        inputPanel.add(optionalLabel);

//Propic Btn
        JButton picBtn = new JButton("Add Profile pic");
        picBtn.setBounds(200, 337, 150, 30);
        picBtn.setBackground(new Color(73, 104, 140));
        picBtn.setFont(arialB16);
        picBtn.setFocusable(false);
        picBtn.setForeground(Color.WHITE);
        picBtn.setBorder(new LineBorder(Color.BLUE));
        inputPanel.add(picBtn);

//Tech Stack
        JLabel techStackLabel = new JLabel("Tech Stack: ");
        techStackLabel.setFont(arialB16);
        techStackLabel.setBounds(87, 385, 100, 25);
        inputPanel.add(techStackLabel);

        JTextField techStackTxt = new JTextField();
        techStackTxt.setFont(arialB16);
        techStackTxt.setBounds(200, 385, 200, 25);
        inputPanel.add(techStackTxt);

//Current Company
        JLabel currentCompanyLabel = new JLabel("Current Company: ");
        currentCompanyLabel.setFont(arialB16);
        currentCompanyLabel.setBounds(38, 430, 150, 25);
        inputPanel.add(currentCompanyLabel);

        JTextField currentCompanyTxt = new JTextField();
        currentCompanyTxt.setFont(arialB16);
        currentCompanyTxt.setBounds(200, 430, 200, 25);
        inputPanel.add(currentCompanyTxt);

//Job Title
        JLabel jobTitleLabel = new JLabel("Job Title: ");
        jobTitleLabel.setFont(arialB16);
        jobTitleLabel.setBounds(105, 475, 150, 25);
        inputPanel.add(jobTitleLabel);

        JTextField jobTitleTxt = new JTextField();
        jobTitleTxt.setFont(arialB16);
        jobTitleTxt.setBounds(200, 475, 200, 25);
        inputPanel.add(jobTitleTxt);

//LinkedIn
        JLabel linkedInLabel = new JLabel("LinkedIn Profile: ");
        linkedInLabel.setFont(arialB16);
        linkedInLabel.setBounds(50, 520, 150, 25);
        inputPanel.add(linkedInLabel);

        JTextField linkedInTxt = new JTextField();
        linkedInTxt.setFont(arialB16);
        linkedInTxt.setToolTipText("Example: https://www.linkedin.com/xyz");
        linkedInTxt.setBounds(200, 520, 200, 25);
        inputPanel.add(linkedInTxt);

//Github
        JLabel githubLabel = new JLabel("Github Profile: ");
        githubLabel.setFont(arialB16);
        githubLabel.setBounds(63, 565, 150, 25);
        inputPanel.add(githubLabel);

        JTextField githubTxt = new JTextField();
        githubTxt.setFont(arialB16);
        githubTxt.setToolTipText("Example: https://www.github.com/xyz");
        githubTxt.setBounds(200, 565, 200, 25);
        inputPanel.add(githubTxt);

//Reg Button
        JButton regBtn = new JButton("Register");
        regBtn.setBounds(300, 620, 100, 30);
        regBtn.setBackground(new Color(73, 104, 140));
        regBtn.setFont(arialB16);
        regBtn.setFocusable(false);
        regBtn.setForeground(Color.WHITE);
        regBtn.setBorder(new LineBorder(Color.BLUE));
        inputPanel.add(regBtn);


        picBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
                fileChooser.addChoosableFileFilter(filter);
                int result = fileChooser.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    s = path;
                }
                else if(result == JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(null, "No image selected","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm();
            }
        });

        regBtn.addActionListener(new ActionListener() {
            int duplicate = 0;
            @Override //Work when Reg button pressed
            public void actionPerformed(ActionEvent e) {
                String username = nameTxt.getText();
                String email = emailTxt.getText();
                String pass = passTxt.getText();
                String conPass = conPassTxt.getText();
                String mobile = mobileTxt.getText();
                String address = addressTxt.getText();
                String batch = batchTxt.getText();
                String techStack = techStackTxt.getText();
                String currentCompany = currentCompanyTxt.getText();
                String jobTitle = jobTitleTxt.getText();
                String linkedIn = linkedInTxt.getText();
                String github = githubTxt.getText();

                String duplicateEmailCheckQ = "SELECT * FROM `newregister`";
                DBConnect db = new DBConnect(email);
                duplicate = db.DuplicateEmailCheck(duplicateEmailCheckQ, email);
////Validation
                String userNameRegex = "^[a-z A-Z]{3,30}$";
                String emailRegex = "^[a-z0-9]+@[a-z]+.[a-z]+$";
                String passRegex = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_?])).{6,20}$";
                String mobileRegex = "^(\\+88)?01[2-9]\\d{8}$";
                String addressRegex = "^[a-z A-Z0-9,.]{3,80}$";
                String batchRegex = "^[0-9]{1,5}$";
                String otherRegex = "^[A-Z a-z,#.]{0,20}$"; // not mandatory
                String lnikedInRegex = "^https:\\/\\/[a-z]{0,3}\\.linkedin\\.com\\/.*$";
                String githubRegex =  "^https:\\/\\/[a-z]{0,3}\\.github\\.com\\/.*$";

                if (!Pattern.matches(userNameRegex, username)){
                    JOptionPane.showMessageDialog(null, "Username should be within 3-30 character","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(emailRegex, email)){
                    JOptionPane.showMessageDialog(null, "Invalid email","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (duplicate == 1){
                    JOptionPane.showMessageDialog(null, "Duplicate email found!!!","Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (!Pattern.matches(passRegex, pass)){
                    JOptionPane.showMessageDialog(null, "Password should contain at least: length between 6-20, 1 digit, 1 lowecase letter, 1 uppercase letter, 1 special character (!@#$%^&*_?)","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!conPass.equals(pass)){
                    JOptionPane.showMessageDialog(null, "Password not matched with Confirm Password","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(mobileRegex, mobile)){
                    JOptionPane.showMessageDialog(null, "Invalid mobile number","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(addressRegex, address)){
                    JOptionPane.showMessageDialog(null, "Invalid address","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(batchRegex, batch)){
                    JOptionPane.showMessageDialog(null, "Batch should contain only digit","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(otherRegex, techStack)){
                    JOptionPane.showMessageDialog(null, "Tech Stack should be within 0-20 characters","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(otherRegex, currentCompany)){
                    JOptionPane.showMessageDialog(null, "Current Company should be within 0-20 characters","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(otherRegex, jobTitle)){
                    JOptionPane.showMessageDialog(null, "Job Title should be within 0-20 characters","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(lnikedInRegex, linkedIn) && !linkedIn.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Invalid LinkedIn link","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!Pattern.matches(githubRegex, github) && !github.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Invalid Github link","Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int id = db.lastID();
                    InputStream is = null;
                    try {
                        is = new FileInputStream(new File(s));
                    }
                    catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Image not found","Error", JOptionPane.ERROR_MESSAGE);
                    }

                    DBConnect db2 = new DBConnect(email);
                    int x = db2.RegisterInsert(id+1, username, email, pass, mobile, address, batch, is, techStack, currentCompany, jobTitle, linkedIn, github);

                    if (x == 1) {
                        dispose();
                        new LoginForm();
                    }
                    else {
                        dispose();
                        new RegisterForm();
                    }
                }
            }
        });

        setVisible(true);
    }
}
