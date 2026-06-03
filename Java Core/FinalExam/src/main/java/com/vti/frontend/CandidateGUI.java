package com.vti.frontend;

import com.vti.backend.controller.CandidateController;
import com.vti.entity.Candidate;
import com.vti.entity.ExperienceCandidate;
import com.vti.entity.FresherCandidate;
import com.vti.enums.GraduationRank;
import com.vti.utils.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CandidateGUI extends JFrame {
    private final CandidateController controller = new CandidateController();

    public CandidateGUI() {
        setTitle("Candidate Register & Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Register", buildRegisterPanel());
        tabs.addTab("Login", buildLoginPanel());

        add(tabs);
    }

    private JPanel buildRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblType = new JLabel("Candidate Type:");
        JRadioButton rbExp = new JRadioButton("Experience");
        JRadioButton rbFresher = new JRadioButton("Fresher");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbExp); bg.add(rbFresher);
        rbExp.setSelected(true);

        JLabel lblFirst = new JLabel("First name:");
        JTextField txtFirst = new JTextField(20);

        JLabel lblLast = new JLabel("Last name:");
        JTextField txtLast = new JTextField(20);

        JLabel lblPhone = new JLabel("Phone:");
        JTextField txtPhone = new JTextField(20);

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);

        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField(20);

        // Experience fields
        JLabel lblExp = new JLabel("ExpInYear (0-10):");
        JTextField txtExp = new JTextField(5);
        JLabel lblPro = new JLabel("ProSkill:");
        JTextField txtPro = new JTextField(20);

        // Fresher fields
        JLabel lblRank = new JLabel("GraduationRank:");
        JComboBox<String> cbRank = new JComboBox<>(new String[]{"Excellence","Good","Fair","Poor"});

        // place components
        int y = 0;
        gbc.gridx = 0; gbc.gridy = y; form.add(lblType, gbc);
        JPanel pType = new JPanel(); pType.add(rbExp); pType.add(rbFresher);
        gbc.gridx = 1; gbc.gridy = y++; form.add(pType, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblFirst, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(txtFirst, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblLast, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(txtLast, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblPhone, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(txtPhone, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblPass, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(txtPass, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblExp, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(txtExp, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblPro, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(txtPro, gbc);

        gbc.gridx = 0; gbc.gridy = y; form.add(lblRank, gbc);
        gbc.gridx = 1; gbc.gridy = y++; form.add(cbRank, gbc);

        // Buttons
        JButton btnRegister = new JButton("Register");
        gbc.gridx = 1; gbc.gridy = y++; form.add(btnRegister, gbc);

        // Toggle visibility based on type
        Runnable updateVisibility = () -> {
            boolean exp = rbExp.isSelected();
            lblExp.setVisible(exp);
            txtExp.setVisible(exp);
            lblPro.setVisible(exp);
            txtPro.setVisible(exp);

            lblRank.setVisible(!exp);
            cbRank.setVisible(!exp);
        };
        updateVisibility.run();

        rbExp.addActionListener(e -> updateVisibility.run());
        rbFresher.addActionListener(e -> updateVisibility.run());

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String first = txtFirst.getText().trim();
                String last = txtLast.getText().trim();
                String phone = txtPhone.getText().trim();
                String email = txtEmail.getText().trim();
                String password = new String(txtPass.getPassword());

                if (first.isEmpty() || last.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "First name and Last name required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!ValidationUtil.isValidPhone(phone)) {
                    JOptionPane.showMessageDialog(panel, "Phone must be 9-12 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!ValidationUtil.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(panel, "Email format invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!ValidationUtil.isValidPassword(password)) {
                    JOptionPane.showMessageDialog(panel, "Password must be 6-12 chars and contain at least one uppercase letter.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean ok = false;
                if (rbExp.isSelected()) {
                    int expYear;
                    try { expYear = Integer.parseInt(txtExp.getText().trim()); } catch (Exception ex) { expYear = -1; }
                    if (!ValidationUtil.isValidExpInYear(expYear)) {
                        JOptionPane.showMessageDialog(panel, "ExpInYear must be integer 0-10.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ExperienceCandidate ec = new ExperienceCandidate();
                    ec.setFirstName(first); ec.setLastName(last); ec.setPhone(phone); ec.setEmail(email); ec.setPassword(password);
                    ec.setExpInYear(expYear); ec.setProSkill(txtPro.getText().trim());
                    ok = controller.register(ec);
                } else {
                    String rankStr = (String) cbRank.getSelectedItem();
                    GraduationRank rank = ValidationUtil.parseGraduationRank(rankStr);
                    if (rank == null) {
                        JOptionPane.showMessageDialog(panel, "GraduationRank invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    FresherCandidate fc = new FresherCandidate();
                    fc.setFirstName(first); fc.setLastName(last); fc.setPhone(phone); fc.setEmail(email); fc.setPassword(password);
                    fc.setGraduationRank(rank);
                    ok = controller.register(fc);
                }

                if (ok) {
                    JOptionPane.showMessageDialog(panel, "Register successful. You can login now.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Register failed. Email may be duplicate or input invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(form, BorderLayout.NORTH);
        return panel;
    }

    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(20);
        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField(20);
        JButton btnLogin = new JButton("Login");

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y; panel.add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = y; panel.add(lblPass, gbc);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(txtPass, gbc);

        gbc.gridx = 1; gbc.gridy = y++; panel.add(btnLogin, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String password = new String(txtPass.getPassword());

                if (!ValidationUtil.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(panel, "Email format invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!ValidationUtil.isValidPassword(password)) {
                    JOptionPane.showMessageDialog(panel, "Password must be 6-12 chars and contain at least one uppercase letter.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Candidate c = controller.login(email, password);
                if (c != null) {
                    JOptionPane.showMessageDialog(panel, "Login successful. Welcome " + c.getFirstName() + " " + c.getLastName() + " (" + c.getRole() + ")", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Login failed. Check credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CandidateGUI gui = new CandidateGUI();
            gui.setVisible(true);
        });
    }
}
