import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CurrProfile extends JFrame {
    private ImageIcon icon;
    private static int ok1 = 1, ok2 = 1;
    private void setOk1(int val){
        ok1 = val;
    }
    private int getOk1(){
        return ok1;
    }
    private void setOk2(int val){
        ok2 = val;
    }
    private int getOk2(){
        return ok2;
    }


    public void initIcon(){
        icon = new ImageIcon(getClass().getResource("LUAlumni.png"));
        setIconImage(icon.getImage());
    }
    public CurrProfile(String name, String email, String mobile, String address, String myEmail, String batch, byte[] img, String techStack, String currentCompany, String jobTitle, String linkedIn, String github) {
        super("LU Alumni Network");
        initIcon();

        setSize(600, 600);
        setLocationRelativeTo(null); //Middle position
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Font arialB16 = new Font("Arial", Font.BOLD, 16);
        Font cambriaB23 = new Font("Cambria", Font.BOLD, 23);

////Go to Profile List
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(80, 235, 173));
        headerPanel.setBounds(0, 5, 600, 50);
        headerPanel.setLayout(null);
        add(headerPanel);

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

////Header: Name
        JPanel headerPanel1 = new JPanel();
        headerPanel1.setBackground(new Color(199, 227, 240));
        headerPanel1.setBounds(0, 50, 600, 50);
        headerPanel1.setLayout(new GridBagLayout());
        add(headerPanel1);

        JLabel headerLabel = new JLabel(name);
        headerLabel.setFont(cambriaB23);
        headerLabel.setBounds(10, 5, 490, 30);
        headerPanel1.add(headerLabel);

////Profile Details
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(199, 227, 240));
        infoPanel.setBounds(0, 100, 400, 300);
        infoPanel.setLayout(null);
        add(infoPanel);

//Pic
        JPanel picPanel = new JPanel();
        picPanel.setBackground(new Color(199, 227, 240));
        picPanel.setBounds(400, 100, 200, 300);
        picPanel.setLayout(null);
        add(picPanel);

        JLabel picLabel = new JLabel();
        picLabel.setFont(arialB16);
        picLabel.setBounds(0, 10, 150, 180);

        ImageIcon image = new ImageIcon(img);
        Image im = image.getImage();
        Image myImg = im.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(myImg);
        picLabel.setIcon(newImage);
        picPanel.add(picLabel);

//Email
        JLabel emailLabel = new JLabel("Email: " +email);
        emailLabel.setFont(arialB16);
        emailLabel.setBounds(10, 5, 400, 30);
        infoPanel.add(emailLabel);

//Mobile
        JLabel mobileLabel = new JLabel("Mobile: " + mobile);
        mobileLabel.setFont(arialB16);
        mobileLabel.setBounds(10, 35, 400, 30);
        infoPanel.add(mobileLabel);

//Address
        JLabel addressLabel = new JLabel("Address: " +address);
        addressLabel.setFont(arialB16);
        addressLabel.setBounds(10, 65, 400, 30);
        infoPanel.add(addressLabel);

//Batch
        JLabel batchLabel = new JLabel("Batch: " +batch);
        batchLabel.setFont(arialB16);
        batchLabel.setBounds(10, 95, 400, 30);
        infoPanel.add(batchLabel);

//Tech Stack
        JLabel techStackLabel = new JLabel("Tech Stack: " +techStack);
        techStackLabel.setFont(arialB16);
        techStackLabel.setBounds(10, 125, 400, 30);
        infoPanel.add(techStackLabel);

//Current Company
        JLabel currentCompanyLabel = new JLabel("Current Company: " +currentCompany);
        currentCompanyLabel.setFont(arialB16);
        currentCompanyLabel.setBounds(10, 155, 400, 30);
        infoPanel.add(currentCompanyLabel);

//Job Title
        JLabel jobTitleLabel = new JLabel("Job Title: " +jobTitle);
        jobTitleLabel.setFont(arialB16);
        jobTitleLabel.setBounds(10, 185, 400, 30);
        infoPanel.add(jobTitleLabel);


////Buttons & Links
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(80, 235, 173));
        buttonPanel.setBounds(0, 400, 600, 200);
        buttonPanel.setLayout(null);
        add(buttonPanel);

        JButton linkedInBtn = new JButton("LinkedIn");
        linkedInBtn.setBounds(125, 25, 100, 30);
        linkedInBtn.setBackground(new Color(73, 104, 140));
        linkedInBtn.setFont(arialB16);
        linkedInBtn.setFocusable(false);
        linkedInBtn.setForeground(Color.WHITE);
        linkedInBtn.setBorder(new LineBorder(Color.BLUE));
        buttonPanel.add(linkedInBtn);

        JButton githubBtn = new JButton("Github");
        githubBtn.setBounds(350, 25, 100, 30);
        githubBtn.setBackground(new Color(73, 104, 140));
        githubBtn.setFont(arialB16);
        githubBtn.setFocusable(false);
        githubBtn.setForeground(Color.WHITE);
        githubBtn.setBorder(new LineBorder(Color.BLUE));
        buttonPanel.add(githubBtn);

//Delete your Profile
        JButton delBtn = new JButton("Delete");
        JButton updateBtn = new JButton("Update");
        String options[] = {"Name", "Email", "Password", "Mobile", "Address", "Batch", "Tech Stack", "Current Company", "Job Title", "LinkedIn", "Github"};
        JComboBox updateList = new JComboBox(options);
        JTextField updateTxt = new JTextField("Enter update Value");
        if (myEmail.equals(email)) {
            JLabel delLabel = new JLabel("Want to delete your profile: ");
            delLabel.setFont(arialB16);
            delLabel.setBounds(10, 70, 490, 30);
            buttonPanel.add(delLabel);

            //Delete Button
            delBtn.setBounds(240, 73, 100, 30);
            delBtn.setBackground(new Color(73, 104, 140));
            delBtn.setFont(arialB16);
            delBtn.setFocusable(false);
            delBtn.setForeground(Color.WHITE);
            delBtn.setBorder(new LineBorder(Color.BLUE));
            buttonPanel.add(delBtn);

            //Update your Profile
            JLabel updateLabel = new JLabel("Want to update your profile: ");
            updateLabel.setFont(arialB16);
            updateLabel.setBounds(10, 110, 490, 30);
            buttonPanel.add(updateLabel);

            updateList.setBounds(230, 113, 100, 30);
            updateList.setEditable(true);
            buttonPanel.add(updateList);

            updateTxt.setFont(arialB16);
            updateTxt.setBounds(340, 113, 150, 30);
            buttonPanel.add(updateTxt);
            add(buttonPanel);

            updateBtn.setBounds(503, 113, 70, 25);
            updateBtn.setBackground(new Color(73, 104, 140));
            updateBtn.setFont(arialB16);
            updateBtn.setFocusable(false);
            updateBtn.setForeground(Color.WHITE);
            updateBtn.setBorder(new LineBorder(Color.BLUE));
            buttonPanel.add(updateBtn);
            add(buttonPanel);
        }

        if (linkedIn.isEmpty()){
            setOk1(0);
        }
        else {
            setOk1(1);
        }
        linkedInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ok1 == 1) {
                    try {
                        java.awt.Desktop.getDesktop().browse(java.net.URI.create(linkedIn));
                    } catch (java.io.IOException err) {
                        JOptionPane.showMessageDialog(null, "ERROR: " + err, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "LinkedIn profile link not set correctly", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        if (github.isEmpty()){
            setOk2(0);
        }
        else {
            setOk2(1);
        }
        githubBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ok2 == 1) {
                    try {
                        java.awt.Desktop.getDesktop().browse(java.net.URI.create(github));
                    } catch (java.io.IOException err) {
                        JOptionPane.showMessageDialog(null, "ERROR: " + err, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Github profile link not set correctly", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        proBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AlumniNetwork(myEmail);
            }
        });

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnect db = new DBConnect(myEmail);
                db.delProfile(myEmail);

                dispose();
                JOptionPane.showMessageDialog(null, "Your Profile deleted","Information", JOptionPane.INFORMATION_MESSAGE);
                new RegisterForm();
            }
        });

        updateList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  selectedOption = (String) updateList.getSelectedItem();
                if (Objects.equals(selectedOption, "Name"))
                {
                    new Update(2, "username");
                }
                else if (Objects.equals(selectedOption, "Email"))
                {
                    new Update(2, "email");
                }
                else if (Objects.equals(selectedOption, "Password"))
                {
                    new Update(2, "password");
                }
                else if (Objects.equals(selectedOption, "Mobile"))
                {
                    new Update(2, "mobile");
                }
                else if (Objects.equals(selectedOption, "Address"))
                {
                    new Update(2, "address");
                }
                else if (Objects.equals(selectedOption, "Batch"))
                {
                    new Update(2, "batch");
                }
                else if (Objects.equals(selectedOption, "Tech Stack"))
                {
                    new Update(2, "techStack");
                }
                else if (Objects.equals(selectedOption, "Current Company"))
                {
                    new Update(2, "currentCompany");
                }
                else if (Objects.equals(selectedOption, "Job Title")) {
                    new Update(2, "jobTitle");
                }
                else if (Objects.equals(selectedOption, "LinkedIn")) {
                    updateTxt.setToolTipText("Example: https://www.linkedin.com/xyz");
                    new Update(2, "linkedIn");
                }
                else {
                    updateTxt.setToolTipText("Example: https://www.github.com/xyz");
                    new Update(2, "github");
                }
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updateValue = updateTxt.getText();
                new Update(3, updateValue);
                Update obj = new Update();
                String udpateType = obj.getUpdateType();
                String udpateVal= obj.getUpdateVal();
                DBConnect db = new DBConnect(myEmail);
                dispose();

                db.updateProfile(myEmail, udpateType, udpateVal);
            }
        });

        setVisible(true);
    }
}
