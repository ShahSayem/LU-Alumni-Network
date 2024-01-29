import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Vector;
import java.util.regex.Pattern;

public class DBConnect extends JFrame {
    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private int flag = 0;
    Font arialB16 = new Font("Arial", Font.BOLD, 16);
    String myEmail;

    public DBConnect(String email) {
        try {
            myEmail = email;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumninetwork", "root", "");
            st = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int RegisterInsert(int id, String username, String email, String pass, String mobile, String address, String batch, InputStream is, String techStack, String currentCompany, String jobTitle, String linkedIn, String github) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO `newregister`(`id`, `username`, `email`, `password`, `mobile`, `address`, `batch`, `image`, `techStack`, `currentCompany`, `jobTitle`, `linkedIn`, `github`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, mobile);
            ps.setString(6, address);
            ps.setString(7, batch);
            ps.setBlob(8, is);
            ps.setString(9, techStack);
            ps.setString(10, currentCompany);
            ps.setString(11, jobTitle);
            ps.setString(12, linkedIn);
            ps.setString(13, github);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registered!!!", "Congrats", JOptionPane.INFORMATION_MESSAGE);

            flag = 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return flag;
    }

    public int LoginMatch(String queryLogin, String email, String pass) {
        try {
            rs = st.executeQuery(queryLogin);

            while (rs.next()) {
                String tableEmail = rs.getString(3);
                String tablePass = rs.getString(4);

                if (email.equals(tableEmail) && pass.equals(tablePass)) {
                    JOptionPane.showMessageDialog(null, "Welcome to our system ", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                    flag = 1;
                    break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return flag;
    }

    public int lastID() {
        int id = 0;

        String sql = "SELECT * FROM `newregister`";
        try {
            rs = st.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return id;
    }

    public int DuplicateEmailCheck(String duplicateEmailCheckQ, String email) {
        flag = 0;
        try {
            rs = st.executeQuery(duplicateEmailCheckQ);

            while (rs.next()) {
                String tableEmail = rs.getString(3);

                if (email.equals(tableEmail)) {
                    flag = 1;
                    break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return flag;
    }

    public Vector<JButton> ProfileCreate(String dataTable, JPanel proPanel) {
        int y = 0;

        Vector<JButton> profile = new Vector<JButton>();
        try {
            rs = st.executeQuery(dataTable);

            while (rs.next()) {
                String tableName = rs.getString(2);
                String tableEmail = rs.getString(3);
                JButton currProBtn = new JButton(tableName + "    " + tableEmail);
                currProBtn.setBounds(0, y, 500, 60);
                currProBtn.setBackground(new Color(73, 104, 140));
                currProBtn.setFont(arialB16);
                currProBtn.setFocusable(false);
                currProBtn.setForeground(Color.WHITE);
                currProBtn.setBorder(new LineBorder(Color.BLUE));

                profile.add(currProBtn);
                proPanel.add(profile.get(profile.size() - 1));
                y += 60;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return profile;
    }

    public void ShowProfile(String queryEmail, String email) {
        try {
            rs = st.executeQuery(queryEmail);

            while (rs.next()) {
                String tableEmail = rs.getString(3);

                if (email.equals(tableEmail)) {
                    dispose();
                    String name = rs.getString(2);
                    String mobile = rs.getString(5);
                    String address = rs.getString(6);
                    String batch = rs.getString(7);
                    byte[] img = rs.getBytes(8); //8 -> "image"
                    String techStack = rs.getString(9);
                    String currentCompany = rs.getString(10);
                    String jobTitle = rs.getString(11);
                    String linkedIn = rs.getString(12);
                    String github = rs.getString(13);

                    new CurrProfile(name, email, mobile, address, myEmail, batch, img, techStack, currentCompany, jobTitle, linkedIn, github);
                    break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Vector<JButton> SearchProfile(String dataTable, int op, String key, JPanel resultPanel) {
        int y = 0;

        Vector<JButton> results = new Vector<JButton>();
        try {
            rs = st.executeQuery(dataTable);

            int pos;
            if (op == 1) //Name
                pos = 2;
            else if (op == 2) //Location
                pos = 6;
            else  //Tech Stack
                pos = 9;

            while (rs.next()) {
                String tableKey = rs.getString(pos);

                if (key.equals(tableKey)) {
                    String tableName = rs.getString(2);
                    String tableEmail = rs.getString(3);
                    JButton currProBtn = new JButton(tableName + "    " + tableEmail);
                    currProBtn.setBounds(0, y, 500, 60);
                    currProBtn.setBackground(new Color(73, 104, 140));
                    currProBtn.setFont(arialB16);
                    currProBtn.setFocusable(false);
                    currProBtn.setForeground(Color.WHITE);
                    currProBtn.setBorder(new LineBorder(Color.BLUE));

                    results.add(currProBtn);
                    resultPanel.add(results.get(results.size() - 1));
                    y += 60;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return results;
    }

    public void delProfile(String email) {
        try {
            String sql = "DELETE FROM `newregister` WHERE email = '" + email + "'";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateProfile(String email, String columnName, String newValue) { // not comp
        try {
            String userNameRegex = "^[a-z A-Z]{3,30}$";
            String emailRegex = "^[a-z0-9]+@[a-z]+.[a-z]+$";
            String passRegex = "^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_?])).{6,20}$";
            String mobileRegex = "^(\\+88)?01[2-9]\\d{8}$";
            String addressRegex = "^[a-z A-Z0-9,.]{3,80}$";
            String batchRegex = "^[0-9]{1,5}$";
            String othersRegex = "^[A-Z a-z,#.]{0,20}$"; // not mandatory
            String lnikedInRegex = "^https:\\/\\/[a-z]{0,3}\\.linkedin\\.com\\/.*$";
            String githubRegex =  "^https:\\/\\/[a-z]{0,3}\\.github\\.com\\/.*$";

            if (Objects.equals(columnName, "username")) {
                if (Pattern.matches(userNameRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Can't Use this Name", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else if (Objects.equals(columnName, "email")) {
                int duplicate = 0;
                String duplicateEmailCheckQ = "SELECT * FROM `newregister`";
                duplicate = DuplicateEmailCheck(duplicateEmailCheckQ, newValue);

                if ((duplicate == 0) && Pattern.matches(emailRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Can't Use this Email", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else if (Objects.equals(columnName, "password")) {
                if (Pattern.matches(passRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Can't Use this Password", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else if (Objects.equals(columnName, "mobile")) {
                if (Pattern.matches(mobileRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Can't Use this Mobile", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else if (Objects.equals(columnName, "address")) {
                if (Pattern.matches(addressRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Can't Use this Address", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else if (Objects.equals(columnName, "batch")) {
                if (Pattern.matches(batchRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Can't Use this Batch", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else if (Objects.equals(columnName, "linkedIn")) {
                if (Pattern.matches(lnikedInRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid linkedin link", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else if (Objects.equals(columnName, "github")) {
                if (Pattern.matches(githubRegex, newValue)) {
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Github link", "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
            else {
                if (Pattern.matches(othersRegex, newValue)) { //Tech Stack, Current Company, Job Title
                    String sql = "UPDATE `newregister` SET `" + columnName + "`= '" + newValue + "' WHERE email = '" + email + "'";
                    st.executeUpdate(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Successfully Changed", "Updated", JOptionPane.PLAIN_MESSAGE);
                    new LoginForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Can't Use this " + columnName, "Error", JOptionPane.ERROR_MESSAGE);
                    dispose();
                    new AlumniNetwork(myEmail);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}