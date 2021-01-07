import ServletCommunications.CheckProfit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.Color.lightGray;
import static java.awt.Color.white;

public class DailyProfits {
    // Icons
    Icon home = new ImageIcon("Icons/home.png");
    Icon search = new ImageIcon("Icons/search.png");
    // Labels
    JLabel currentUserLabel = new JLabel("Logged in as: ");
    JLabel currentUser = new JLabel();
    JLabel branchNameLabel = new JLabel("Branch: ");
    JLabel branchName = new JLabel();
    JLabel dailyProfitLbl = new JLabel("Today's Daily Profit:");
    JLabel currencyGBP = new JLabel("GBP");
    JLabel dailyProfit = new JLabel("0.00");
    JButton searchDate = new JButton(search);
    JFormattedTextField date = new JFormattedTextField(createFormatter("####-##-##"));

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

// Daily Profit header
        JPanel dailyProfitPnl = new JPanel();
        dailyProfitPnl.setBackground(white);
        dailyProfitPnl.setBounds(95,70,600,70);

        currencyGBP.setFont(new Font(null,Font.BOLD,39));
        currencyGBP.setForeground(new Color(118,27,38));
        dailyProfitLbl.setFont(new Font(null,Font.BOLD,40));
        dailyProfitLbl.setForeground(new Color(54,58,101));
        dailyProfit.setFont(new Font(null,Font.BOLD,40));
        dailyProfit.setForeground(new Color(118,27,38));

// Set up daily profit inputs
        dailyProfitPnl.add(dailyProfitLbl);
        dailyProfitPnl.add(currencyGBP);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
// Get current date and use that to call CheckProfit class to access db and find today's profit
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        CheckProfit query0 = new CheckProfit("'" + currentDate + "'");
        dailyProfit.setText(String.valueOf(query0.profit));
        dailyProfitPnl.add(dailyProfit);

        mainPanel.add(dailyProfitPnl);

        JPanel dateSearch = new JPanel();
        dateSearch.setBackground(white);

        Border titBorderCodeSrch = BorderFactory.createTitledBorder(null,"Add date (yyyy-mm-dd)", TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,new Font(null,Font.BOLD,14));
        dateSearch.setBorder(new CompoundBorder(titBorderCodeSrch,new EmptyBorder(0,0,0,0)));
        dateSearch.setVisible(true);
        dateSearch.setBounds(100,180,330,70);

        searchDate.setBorderPainted(false);
        searchDate.setFocusPainted(false);
        searchDate.setContentAreaFilled(true);
        searchDate.setBackground(Color.lightGray);

        searchDate.setPreferredSize(new Dimension(25,25));
        date.setPreferredSize(new Dimension(165,25));

        dateSearch.add(date);
        dateSearch.add(searchDate);

        mainPanel.add(dateSearch);

// Previous days profits table
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable previousProfits = new JTable(tableModel);
        tableModel.addColumn("Date");
        tableModel.addColumn("Profit");
        tableModel.insertRow(tableModel.getRowCount(), new Object[] {dateFormat.format(currentDate), query0.profit});

        // Set up button to read date entered by user and make a CheckProfit query to return that days profit
        searchDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckProfit query = new CheckProfit("'" + date.getText() + "'");
                tableModel.insertRow(tableModel.getRowCount(), new Object[] {date.getText(), query.getProfit()});
                System.out.println(date.getText());
            }
        });

        previousProfits.setShowGrid(false);
        previousProfits.setShowHorizontalLines(false);
        previousProfits.setShowVerticalLines(false);
        previousProfits.getTableHeader().setBackground(new Color(173,216,232));
        previousProfits.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(173,216,232)));


        JScrollPane tabScrPne = new JScrollPane(previousProfits);
        tabScrPne.setBounds(95,260,600,100);
        tabScrPne.getViewport().setBackground(white);
        tabScrPne.setBorder(BorderFactory.createLineBorder(new Color(189, 210, 231)));

        mainPanel.add(tabScrPne);

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
// This method creates the date format mask for the user input
// Reference 1 https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
// end of reference 1
}
