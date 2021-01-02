import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.lightGray;
import static java.awt.Color.white;

public class DailyProfits {
    // Icons
    Icon home = new ImageIcon("Icons/home.png");
    // Labels
    JLabel currentUserLabel = new JLabel("Logged in as: ");
    JLabel currentUser = new JLabel();
    JLabel branchNameLabel = new JLabel("Branch: ");
    JLabel branchName = new JLabel();
    // Buttons
    JButton toDashboardPage = new JButton("Back to Home",home);

    DailyProfits(){
// Initialising main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setLayout(null);

// Adding the back to home button
        JPanel backToHome = new JPanel();
        backToHome.setBackground(white);
        backToHome.setBounds(20,12,140,30);

        toDashboardPage.setPreferredSize(new Dimension(135,25));
        toDashboardPage.setBorderPainted(false);
        toDashboardPage.setFocusPainted(false);
        toDashboardPage.setContentAreaFilled(true);
        toDashboardPage.setBackground(lightGray);

        backToHome.add(toDashboardPage);
        mainPanel.add(backToHome);

// Current User Details
        JPanel crrntUserDet = new JPanel();
        crrntUserDet.setLayout(new GridLayout(2,2));
        crrntUserDet.setBounds(580,10,180,30);
        crrntUserDet.setBackground(white);

        JLabel[] crrntUserDetLbl = {currentUserLabel,branchNameLabel};
        JLabel[] crrntUserDetName = {currentUser,branchName};

        for(int i=0;i<crrntUserDetLbl.length;i++){
            crrntUserDetLbl[i].setFont(new Font(null,Font.BOLD,10));
            crrntUserDetLbl[i].setForeground(new Color(51,171,240));
            crrntUserDetName[i].setFont(new Font(null,Font.PLAIN,10));
            crrntUserDetName[i].setForeground(new Color(92,180,68));
            crrntUserDet.add(crrntUserDetLbl[i]);
            crrntUserDet.add(crrntUserDetName[i]);
        }

        mainPanel.add(crrntUserDet);

// Initialising frame
        JFrame frame = new JFrame("Phab Pharmacies - Find in Store");
        frame.setSize(800,530);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel.setBackground(white);
        frame.setBackground(white);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setResizable(false);

// Back to home button
        toDashboardPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardPage dashboardPage = new DashboardPage();
                dashboardPage.currentUser.setText(currentUser.getText());
                dashboardPage.branchName.setText(branchName.getText());
                frame.setVisible(false);
            }
        });
    }
}
