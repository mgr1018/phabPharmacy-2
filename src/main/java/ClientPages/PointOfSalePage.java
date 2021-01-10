package ClientPages;

import ServletCommunications.ReturnDetails;
import ServletCommunications.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.text.DecimalFormat;

import static java.awt.Color.lightGray;
import static java.awt.Color.white;

public class PointOfSalePage {
    // Logging
    private static final Logger log= Logger.getLogger(PointOfSalePage.class.getName());
    // All components
    // Icons
    Icon add = new ImageIcon("Icons/plus.png");
    Icon remove = new ImageIcon("Icons/minus.png");
    Icon backArrow = new ImageIcon("Icons/home.png");
    Icon search = new ImageIcon("Icons/search.png");
    Icon save = new ImageIcon("Icons/check.png");
    Icon cancel = new ImageIcon("Icons/close.png");
    // Labels
    JLabel title = new JLabel("Phab Pharmacies");
    JLabel currentUserLabel = new JLabel("Logged in as: ");
    JLabel currentUser = new JLabel();
    JLabel branchNameLabel = new JLabel("Branch: ");
    JLabel branchName = new JLabel();
    JLabel productBarcodeLabel = new JLabel("Product Barcode");
    JLabel brandNameLabel = new JLabel("Brand");
    JLabel brandName = new JLabel();
    JLabel productNameLabel = new JLabel("Name");
    JLabel productName = new JLabel();
    JLabel totalDoseLabel = new JLabel("Total Dose");
    JLabel totalDose = new JLabel();
    JLabel saleLimitLabel = new JLabel("Sale Limit");
    JLabel saleLimit = new JLabel();
    JLabel unitPriceLabel = new JLabel("Unit Price");
    JLabel unitPrice = new JLabel();
    JLabel purchaseQuantityLabel = new JLabel("Purchase Quantity");
    JLabel saleTotalLabel = new JLabel("Total:");
    JLabel currencyGBP = new JLabel("GBP");
    JLabel saleTotal = new JLabel("0.00");
    // Text fields
    JFormattedTextField productBarcode = new JFormattedTextField(NumberFormat.getInstance());
    // Spinners
    JSpinner purchaseQuantity = new JSpinner();
    // Buttons
    JButton searchBarcode = new JButton(search);
    JButton addToSale = new JButton("Add",add);
    JButton deleteFromSale = new JButton("Remove",remove);
    JButton finishSale = new JButton("Finish/Save Sale",save);
    JButton cancelSale = new JButton("Cancel Sale",cancel);
    JButton toDashboardPage = new JButton("Back to Home",backArrow);


    PointOfSalePage(){

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setLayout(null);

// Current Sale Products Table set-up
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable currentSaleProducts = new JTable(tableModel);
        tableModel.addColumn("Brand");
        tableModel.addColumn("Product");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Total");

        currentSaleProducts.setShowGrid(false);
        currentSaleProducts.setShowHorizontalLines(false);
        currentSaleProducts.setShowVerticalLines(false);
        currentSaleProducts.getTableHeader().setBackground(new Color(173,216,232));
        currentSaleProducts.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(173,216,232)));

        JScrollPane tabScrPne = new JScrollPane(currentSaleProducts);
        tabScrPne.setBounds(390,180,370,250);
        tabScrPne.getViewport().setBackground(white);
        tabScrPne.setBorder(BorderFactory.createLineBorder(new Color(189, 210, 231)));

        mainPanel.add(tabScrPne);

// Product Details set-up
        JPanel productDetails = new JPanel();
        productDetails.setBackground(white);
        Border titBorderProdDet = BorderFactory.createTitledBorder(null,"Product Details",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,new Font(null,Font.BOLD,14));

        JLabel[] prodDetLabels = {brandNameLabel,productNameLabel,totalDoseLabel,saleLimitLabel,unitPriceLabel};
        JLabel[] prodDet = {brandName,productName,totalDose,saleLimit,unitPrice};
        for(int i=0; i<prodDetLabels.length; i++){
            prodDetLabels[i].setFont(new Font("Arial",Font.BOLD,12));
            prodDet[i].setFont(new Font("Arial",Font.PLAIN,12));
        }

        productDetails.setLayout(new GridLayout(5,2));
        productDetails.setBorder(new CompoundBorder(titBorderProdDet,new EmptyBorder(2,10,2,10)));
        productDetails.setVisible(true);
        productDetails.setBounds(20,180,330,150);

        for(int i=0; i<prodDetLabels.length; i++){
            productDetails.add(prodDetLabels[i]);
            productDetails.add(prodDet[i]);
        }

        mainPanel.add(productDetails);

// Search product with barcode panel
        JPanel barcodeSrchPnl = new JPanel();
        barcodeSrchPnl.setBackground(white);

        Border titBorderCodeSrch = BorderFactory.createTitledBorder(null,"Add with Barcode",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,new Font(null,Font.BOLD,14));
        barcodeSrchPnl.setBorder(new CompoundBorder(titBorderCodeSrch,new EmptyBorder(0,0,0,0)));
        barcodeSrchPnl.setVisible(true);
        barcodeSrchPnl.setBounds(20,100,330,70);

        searchBarcode.setBorderPainted(false);
        searchBarcode.setFocusPainted(false);
        searchBarcode.setContentAreaFilled(true);
        searchBarcode.setBackground(Color.lightGray);

        searchBarcode.setPreferredSize(new Dimension(25,25));
        productBarcode.setPreferredSize(new Dimension(165,25));

        barcodeSrchPnl.add(productBarcodeLabel);
        barcodeSrchPnl.add(productBarcode);
        barcodeSrchPnl.add(searchBarcode);

        mainPanel.add(barcodeSrchPnl);
/* Set up search button, takes id/barcode entered by user and uses ReturnDetails class to make
call to our servlet which gets pulls values from our database and makes them accessible to us
 */
        searchBarcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long value = (long)productBarcode.getValue();
                int id = (int)value;
                ReturnDetails details = new ReturnDetails(id);
                productName.setText(details.getName());
                brandName.setText(details.getBrand());
                saleLimit.setText(details.getSaleLimit());
                unitPrice.setText(Float.toString(details.getUnitPrice()));
                totalDose.setText(details.getAmount());
                log.info("Accessed Servlet and Database for product details");
            }
        });



// Adding the quantity spinner
        JPanel reqQuantity = new JPanel();
        reqQuantity.setBackground(white);
        reqQuantity.setBounds(35,340,312,27);
        reqQuantity.setLayout(new GridLayout());

        purchaseQuantity.setPreferredSize(new Dimension(40,25));
        purchaseQuantity.setBackground(lightGray);

        reqQuantity.add(purchaseQuantityLabel);
        reqQuantity.add(purchaseQuantity);

        mainPanel.add(reqQuantity);

// Adding the buttons
        JPanel actionButtons = new JPanel();
        actionButtons.setBackground(white);
        actionButtons.setLayout(new GridLayout(2,2,5,5));
        actionButtons.setBounds(20,380,330,50);

        JButton[] bttnArray = {addToSale,deleteFromSale,finishSale,cancelSale};
        for(int i=0;i<bttnArray.length;i++){
            bttnArray[i].setPreferredSize(new Dimension(160,25));
            bttnArray[i].setBorderPainted(false);
            bttnArray[i].setFocusPainted(false);
            bttnArray[i].setContentAreaFilled(true);
            bttnArray[i].setBackground(Color.lightGray);
            actionButtons.add(bttnArray[i]);
        }
        mainPanel.add(actionButtons);
/* Sets up a list which will contain the products added by the user when the add to sale button is
pressed. Also adds these products to table to be displayed to the user. Finally the total cost of the
product list is calculated. Removal button also implemented.
 */
        DecimalFormat df = new DecimalFormat("0.00");
        List<Product> products = new ArrayList<>();
        addToSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                products.add(new Product(productName.getText(), brandName.getText(), Float.parseFloat(unitPrice.getText()), (int)purchaseQuantity.getValue()));
                String tableCost = df.format((int)purchaseQuantity.getValue()*Float.parseFloat(unitPrice.getText()));
                tableModel.insertRow(tableModel.getRowCount(), new Object[] {productName.getText(), brandName.getText(),String.valueOf(purchaseQuantity.getValue()),tableCost});
                float total = Float.parseFloat(saleTotal.getText()) + (int)purchaseQuantity.getValue()*Float.parseFloat(unitPrice.getText());
                saleTotal.setText(String.valueOf(df.format(total)));
                log.info("Added product details to product list");
            }
        });

        deleteFromSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = currentSaleProducts.getSelectedRow();
                float sub = products.get(row).getQuantity()*products.get(row).getUnitPrice();
                saleTotal.setText(String.valueOf(Float.parseFloat(saleTotal.getText())-sub));
                products.remove(row);
                tableModel.removeRow(row);
                log.info("Removed product details from product list");
            }
        });

/* Sets up finish sale button so that when the user presses the button, the online database is updated
with the appropriate quantities
 */
        finishSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = products.size()-1; i>-1; i--){
                    String name = "'" + products.get(i).getName() + "'";
                    String brand = "'" + products.get(i).getBrand() + "'";
                    int change = -products.get(i).getQuantity();
                    UpdateQuantity query = new UpdateQuantity(name, brand, change);
                    log.info("Accessed server and database to update product details");
                    System.out.println(products.get(i).getName());
                    products.remove(i);
                    tableModel.setRowCount(0);
                    saleTotal.setText("0.00");
                }
            }
        });

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

// Total Cost of Sale
        JPanel totalCost = new JPanel();
        totalCost.setBackground(white);
        totalCost.setBounds(410,100,370,70);
        totalCost.setLayout(new GridLayout(1,3));

        currencyGBP.setFont(new Font(null,Font.BOLD,39));
        currencyGBP.setForeground(new Color(118,27,38));
        saleTotalLabel.setFont(new Font(null,Font.BOLD,40));
        saleTotalLabel.setForeground(new Color(54,58,101));
        saleTotal.setFont(new Font(null,Font.BOLD,40));
        saleTotal.setForeground(new Color(118,27,38));

        totalCost.add(saleTotalLabel);
        totalCost.add(currencyGBP);
        totalCost.add(saleTotal);

        mainPanel.add(totalCost);

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

        JFrame frame = new JFrame("Phab Pharmacies - Point of Sale");
        frame.setSize(800,530);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        //Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\craig\\Documents\\PRG3\\UI\\Icons\\home.png");
        //frame.setIconImage(icon);

        mainPanel.setBackground(white);
        frame.setBackground(white);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setResizable(false);

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
