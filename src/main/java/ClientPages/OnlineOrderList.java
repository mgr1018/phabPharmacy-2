package ClientPages;

import ServletCommunications.*;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;

import static java.awt.Color.lightGray;
import static java.awt.Color.white;


public class OnlineOrderList {
    // Icons
    Icon home = new ImageIcon("Icons/home.png");
    Icon checklist = new ImageIcon("Icons/checklist.png");

    Icon map = new ImageIcon("Icons/map.png");
    Icon aMap = new ImageIcon("Maps/a-map.png");
    Icon bMap = new ImageIcon("Maps/b-map.png");
    Icon cMap = new ImageIcon("Maps/c-map.png");
    Icon dMap = new ImageIcon("Maps/d-map.png");
    Icon eMap = new ImageIcon("Maps/e-map.png");
    Icon fMap = new ImageIcon("Maps/f-map.png");
    // Buttons
    JButton toDashboardPage = new JButton("Back to Home",home);
    // Labels
    JLabel currentUserLabel = new JLabel("Logged in as: ");
    JLabel currentUser = new JLabel();
    JLabel branchNameLabel = new JLabel("Branch: ");
    JLabel branchName = new JLabel();
    JLabel onlineOrderCustomerLbl = new JLabel("Online Order Customer List");
    JLabel onlineOrderProductLbl = new JLabel("Customer's Order List");
    // Buttons
    JButton findInStore = new JButton("Find Product in Store",map);
    JButton checkOffProduct = new JButton("Check-off Product",checklist);

    OnlineOrderList(){
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

// Online customer list
        onlineOrderCustomerLbl.setFont(new Font(null,Font.BOLD,16));

        JTable onlineOrderCustomers = new JTable(0,5);
        onlineOrderCustomers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int cstmrTableWidth = 0;
        String[] cstmrColumnNames = {"CID","First Name","Last Name","Contact No.","Postal Address"};
        for(int i=0; i<onlineOrderCustomers.getColumnCount() ; i++){
            onlineOrderCustomers.getColumnModel().getColumn(i).setHeaderValue(cstmrColumnNames[i]);
            int minWidth = (cstmrColumnNames[i].length()+3)*6;
            cstmrTableWidth = cstmrTableWidth + minWidth;
            onlineOrderCustomers.getColumnModel().getColumn(i).setMinWidth(minWidth);
        }

        onlineOrderCustomers.setShowGrid(false);
        onlineOrderCustomers.setShowHorizontalLines(false);
        onlineOrderCustomers.setShowVerticalLines(false);
        onlineOrderCustomers.getTableHeader().setBackground(new Color(173,216,232));
        onlineOrderCustomers.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(173,216,232)));

        JScrollPane cstmrTabScrPne = new JScrollPane(onlineOrderCustomers);
        cstmrTabScrPne.setBounds(20,80,cstmrTableWidth,180);
        cstmrTabScrPne.getViewport().setBackground(white);
        cstmrTabScrPne.setBorder(BorderFactory.createLineBorder(new Color(189, 210, 231)));

        JPanel customerListPnl = new JPanel();
        customerListPnl.setBackground(white);
        customerListPnl.setLayout(new GridLayout(1,1,0,0));
        customerListPnl.setBounds(20,55,cstmrTableWidth,20);
        customerListPnl.add(onlineOrderCustomerLbl);

        mainPanel.add(customerListPnl);
        mainPanel.add(cstmrTabScrPne);

// Customer's order list
        onlineOrderProductLbl.setFont(new Font(null,Font.BOLD,16));

        JTable onlineOrderProducts = new JTable(0,5);
        onlineOrderProducts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        String[] prdctColumnNames = {"Barcode","Brand","Product","Quantity","Drug Category"};
        int totalLength = 0;
        for(int i=0;i<onlineOrderProducts.getColumnCount();i++){
            totalLength = totalLength + prdctColumnNames[i].length()+3;
        }
        for(int i=0;i<onlineOrderProducts.getColumnCount();i++){
            onlineOrderProducts.getColumnModel().getColumn(i).setHeaderValue(prdctColumnNames[i]);
            int columnLength = prdctColumnNames[i].length();
            float minWidth = ((columnLength+3)*cstmrTableWidth)/totalLength;
            onlineOrderProducts.getColumnModel().getColumn(i).setMinWidth(Math.round(minWidth)+1);
        }

        onlineOrderProducts.setShowGrid(false);
        onlineOrderProducts.setShowHorizontalLines(false);
        onlineOrderProducts.setShowVerticalLines(false);
        onlineOrderProducts.getTableHeader().setBackground(new Color(173,216,232));
        onlineOrderProducts.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(173,216,232)));

        JScrollPane prdctTabScrPne = new JScrollPane(onlineOrderProducts);
        prdctTabScrPne.setBounds(20,295,cstmrTableWidth,180);
        prdctTabScrPne.createHorizontalScrollBar();
        prdctTabScrPne.getViewport().setBackground(white);
        prdctTabScrPne.setBorder(BorderFactory.createLineBorder(new Color(189, 210, 231)));

        JPanel productListPnl = new JPanel();
        productListPnl.setBackground(white);
        productListPnl.setLayout(new GridLayout(1,1,0,0));
        productListPnl.setBounds(20,270,cstmrTableWidth,20);
        productListPnl.add(onlineOrderProductLbl);

        mainPanel.add(productListPnl);
        mainPanel.add(prdctTabScrPne);

// ****************************************TEST TEST TEST TEST*********************************************
        DefaultTableModel model = (DefaultTableModel) onlineOrderCustomers.getModel();
       // CustomerListSize s1 = new CustomerListSize();
        ReturnCustomers rc = new ReturnCustomers();
        for (int i=1; i<=rc.getCustomers().size(); i++){
            ReturnCustomer c = new ReturnCustomer(i);
            model.addRow(new Object[]{c.getCID(), c.getFirstName(), c.getLastName(),c.getContactNo(),c.getPostalAddress()});
        }


        /*DefaultTableModel mode = (DefaultTableModel) onlineOrderCustomers.getModel();
        mode.addRow(new Object[]{"001", "Martin", "Holloway","+44 7XXXXXXXXX","SW7 2AZ"});*/

// ********************************************************************************************************
// Customer Details titled border
        JPanel customerDetails = new JPanel();
        customerDetails.setLayout(new GridLayout(5,2));
        customerDetails.setBackground(white);
        customerDetails.setBorder(new CompoundBorder(BorderFactory.createTitledBorder(null,"Customer Details",0,0,new Font(null,Font.BOLD,14)),
                BorderFactory.createEmptyBorder(0,5,2,5)));
        customerDetails.setBounds((40+cstmrTableWidth),70,(800-(70+cstmrTableWidth)),170);

        JLabel[] customerDetailLbl = {new JLabel(cstmrColumnNames[0]),new JLabel(cstmrColumnNames[1]),new JLabel(cstmrColumnNames[2]),new JLabel(cstmrColumnNames[3]),new JLabel(cstmrColumnNames[4])};
        JLabel[] customerDetailVal = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};

        onlineOrderCustomers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel aModel = (DefaultTableModel)onlineOrderCustomers.getModel();
                int aSelectedRowIndex = onlineOrderCustomers.rowAtPoint(e.getPoint());
                for (int i = 0; i < customerDetailVal.length; i++) {
                    customerDetailVal[i].setFont(new Font(null,Font.PLAIN,12));
                    customerDetailVal[i].setText(model.getValueAt(aSelectedRowIndex, i).toString());

                }
            }
        });

        String custid = customerDetailVal[0].getText();
        DefaultTableModel bModel = (DefaultTableModel) onlineOrderProducts.getModel();
        ReturnCustOrders co = new ReturnCustOrders(Integer.parseInt(custid));
        for (int j=1; j<=co.getCustorders().size() ; j++){
            ReturnOrderedProduct p = new ReturnOrderedProduct(j);
            bModel.addRow(new Object[]{p.getBarcode(), p.getBrand(), p.getName(),p.getQty(),p.getCategory()});
        }

        for(int i=0;i<customerDetailLbl.length;i++){
            customerDetails.add(customerDetailLbl[i]);
            customerDetails.add(customerDetailVal[i]);
        }

        mainPanel.add(customerDetails);

// Product details titled border
        JPanel productDetails = new JPanel();
        productDetails.setBackground(white);
        productDetails.setLayout(new GridLayout(5,2));
        productDetails.setBorder(new CompoundBorder(BorderFactory.createTitledBorder(null,"Product Details",0,0,new Font(null,Font.BOLD,14)),
                BorderFactory.createEmptyBorder(0,5,2,5)));
        productDetails.setBounds((40+cstmrTableWidth),250,(800-(70+cstmrTableWidth)),170);

        JLabel[] productDetailLbl = {new JLabel(prdctColumnNames[0]),new JLabel(prdctColumnNames[1]),new JLabel(prdctColumnNames[2]),new JLabel(prdctColumnNames[3]),new JLabel(prdctColumnNames[4])};
        JLabel[] productDetailVal = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};

        onlineOrderProducts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel bModel = (DefaultTableModel)onlineOrderProducts.getModel();
                int bSelectedRowIndex = onlineOrderProducts.rowAtPoint(e.getPoint());
                for(int i=0;i<productDetailVal.length;i++) {
                    productDetailVal[i].setFont(new Font(null,Font.PLAIN,12));
                    productDetailVal[i].setText(bModel.getValueAt(bSelectedRowIndex,i).toString());

                }
            }
        });

        for(int i=0;i<productDetailLbl.length;i++){
            productDetails.add(productDetailLbl[i]); productDetails.add(productDetailVal[i]);
        }
        mainPanel.add(productDetails);

// Buttons frame
        JPanel buttons = new JPanel();
        buttons.setBackground(white);
        buttons.setBounds((40+cstmrTableWidth),435,(800-(70+cstmrTableWidth)),40);
        buttons.setLayout(new GridLayout(1,2,5,0));

        JButton[] allButtons = {findInStore,checkOffProduct};
        for(int i=0;i<allButtons.length;i++){
            allButtons[i].setContentAreaFilled(true);
            allButtons[i].setBorderPainted(false);
            allButtons[i].setFocusPainted(false);
            allButtons[i].setBackground(lightGray);
            allButtons[i].setMargin(new Insets(4,3,4,3));
            buttons.add(allButtons[i]);
        }

        mainPanel.add(buttons);

        checkOffProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "'" + productDetailVal[2].getText() + "'";
                String brand = "'" + productDetailVal[1].getText() + "'";
                int change = - Integer.parseInt(productDetailVal[3].getText());
                    UpdateQuant query = new UpdateQuant(name, brand, change);
                    UpdateOrderDB query2 = new UpdateOrderDB(Integer.parseInt(productDetailVal[0].getText()));
                    //log.info("Accessed server and database to update product details");
                    DefaultTableModel bModel = (DefaultTableModel)onlineOrderProducts.getModel();
                    int bRow = onlineOrderProducts.getSelectedRow();
                    bModel.removeRow(bRow);
                    for (int i = 0; i < productDetailVal.length; i++) {
                        productDetailVal[i].setText("");
                    }
                    int a = onlineOrderProducts.getRowCount();
                    if(a==0){
                        DefaultTableModel aModel = (DefaultTableModel)onlineOrderCustomers.getModel();
                        int aRow = onlineOrderCustomers.getSelectedRow();
                        aModel.removeRow(aRow);
                        for (int i = 0; i < customerDetailVal.length; i++) {
                            customerDetailVal[i].setText("");
                        }
                    }
            }
        });

// Initialising frame
        JFrame frame = new JFrame("Phab Pharmacies - Online Orders List");
        frame.setSize(800,530);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel.setBackground(white);
        frame.setBackground(white);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setResizable(false);

// THIS IS THE BIT THAT I ADDED. IT READS THE DRUG CATEGORY AND OPENS UP THE CORRESPONDING MAP FOR THAT SECTION
// WHEN THE FIND IN STORE BUTTON IS CLICKED
        findInStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] sectionNames = {"First Aid","Skincare","Headaches and Pain Relief","Digestion","Allergies","Cold and Flu"};
                Icon[] allMaps = {aMap,bMap,cMap,dMap,eMap,fMap};
                for(int i=0;i<allMaps.length;i++){
                    if(productDetailVal[4].getText() == sectionNames[i]){
                        JPanel mapPanel = new JPanel();
                        mapPanel.add(new JLabel(allMaps[i]));
                        mapPanel.setBounds(0,0,350,450);

                        JFrame mapFrame = new JFrame("Product Location");
                        mapFrame.setSize(350,450);
                        mapFrame.setLocationRelativeTo(null);
                        mapFrame.setLayout(new BorderLayout());

                        mapPanel.setBackground(white);
                        mapFrame.setBackground(white);
                        mapFrame.add(mapPanel, BorderLayout.CENTER);
                        mapFrame.setVisible(true);
                        mapFrame.setResizable(false);
                    }
                }
            }
        });
// END OF THE FUNCTIONS FOR THE FIND IN STORE BUTTON. YOU MAY JUST ADD THE CODE ENCLOSED BETWEEN THESE COMMENTS
// TO THE FINAL VERSION OF THIS PAGE.

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