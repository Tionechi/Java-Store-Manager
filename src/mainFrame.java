import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder; 
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.util.Date;

public class mainFrame<TableModel> extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_5;
    private JComboBox<String> userCred;
    private JLabel lblNewLabel;
    private JButton loginBtn;
    private JTable cus_view;
    private JButton btnNewButton;
    private JLabel lblNewLabel_1;
    private user currentUser; //Variable that keeps track of the user currently logged in
    private JButton btnNewButton_1;
    private JTable basket;
    private JScrollPane scrollPane_1;
    private JLabel lblNewLabel_2;
    private JButton showBtn;
    private JTextField barField;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_9;
    private JLabel lblNewLabel_10;
    private JTextField cardNo;
    private JTextField sCode;
    private JLabel lblNewLabel_11;
    private JTextField email;
    private JTable admin_view;
    private List<product> currentList; // Variable meant to keep track of what list selected products are extracted from
    private JButton refreshBtn2;
    private JLabel lblNewLabel_12;
    private JTextField barcode;
    private JButton searchBtn2;
    private JTextField bCode;
    private JTextField brand;
    private JTextField color;
    private JTextField qty;
    private JTextField oCost;
    private JTextField rCost;
    private JTextField mBtns;
    private JLabel lblNewLabel_20;
    private JLabel lblNewLabel_21;
    private JLabel lblNewLabel_22;
    private JLabel lblNewLabel_23;
    private JComboBox dTypeBox;
    private JLabel lblNewLabel_24;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainFrame<Object> frame = new mainFrame<Object>();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws FileNotFoundException 
     */
    public mainFrame() throws FileNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        
        contentPane.setLayout(null);
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 11, 428, 254);
        contentPane.add(tabbedPane);
        
        panel_1 = new JPanel();
        tabbedPane.addTab("Login", null, panel_1, null);
        panel_1.setLayout(null);
        
        userCred = new JComboBox<>();
        userCred.setBounds(82, 98, 234, 22);
        
     // Load users from file
        loadUsers userList = new loadUsers();
        List<user> users = userList.loadUsersFromFile("UserAccounts.txt");
        for (user p1 : users) {
            userCred.addItem(p1.getUsername());
        }
        
     // Load products from file
        inventory stock =  new inventory();
        List<product> products = stock.loadProducts("Stock.txt");
        for (product item : products) {
            stock.addItem(item);
        }
        List<product> cusView = stock.customerView();
        List<product> adminView = stock.adminView();
    	
        panel_1.add(userCred);
        
        lblNewLabel = new JLabel("Select User");
        lblNewLabel.setBounds(169, 73, 55, 14);
        panel_1.add(lblNewLabel);
        
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	
                // Get the selected username from the JComboBox
                String selectedUsername = (String) userCred.getSelectedItem();

                // Find the corresponding user object from the list
                user selectedUser = null;
                for (user u : users) {
                    if (u.getUsername().equals(selectedUsername)) {
                        selectedUser = u;
                        break;
                    }
                }
            
                // Check if a user is selected
                if (selectedUser != null) {
                    // Determine the type of the selected user
                    if (selectedUser.getType() == userType.customer ) {
                        // Display alert for customer login
                        String message = "Customer logged in: " + selectedUser.getName();
                        JOptionPane.showMessageDialog(contentPane, message);
                        //assign cusView to currentList 
                		currentList = cusView;
                        // Navigate to the customer tab
                        tabbedPane.setSelectedIndex(1);
                    } else if (selectedUser.getType() == userType.admin) {
                        // Display alert for admin login
                        String message = "Admin logged in: " + selectedUser.getName();
                        JOptionPane.showMessageDialog(contentPane, message);
                        //assign cusView to currentList 
                		currentList = adminView;
                        // Navigate to the admin tab
                        tabbedPane.setSelectedIndex(2);
                    } else {
                        // Invalid user type
                        String message = "Invalid user type";
                        JOptionPane.showMessageDialog(contentPane, message);
                    }
                } else {
                    // No user selected
                    String message = "No user selected";
                    JOptionPane.showMessageDialog(contentPane, message);
                }
                
                
                // Find the corresponding user object from the list
                for (user u : users) {
                    if (u.getUsername().equals(selectedUsername)) {
                        currentUser = u; // Set the current user
                        break;
                    }
                }
                
                // Assign the selectedUser to currentUser
                currentUser = selectedUser;
                
                
             
            }
        });
        loginBtn.setBounds(161, 148, 89, 23);
        panel_1.add(loginBtn);
     
        
        panel_2 = new JPanel();
        tabbedPane.addTab("Shop", null, panel_2, null);
        panel_2.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 11, 335, 215);
        panel_2.add(scrollPane);
        
        cus_view = new JTable();
     
        
     // Creating the table model with column names and data
     String[] columnNames = {"Barcode", "Product category","Device Type", "Brand", "Color", "Connectivity", "Quantity", "Price", "Layout/Buttons"};
     Object[][] data = new Object[cusView.size()][columnNames.length];
     

     // Populate the data array with the data from the cusView list
     for (int i = 0; i < cusView.size(); i++) {
         product item = cusView.get(i);
         data[i][0] = item.getBarcode();
         data[i][1] = item.getcategory();
         data[i][3] = item.getBrand();
         data[i][4] = item.getColor();
         data[i][5] = item.getConnectivity();
         data[i][6] = item.getQuantityInStock();
         data[i][7] = item.getRetailPrice();
         if (item instanceof keyboard) {
             keyboard keyboardItem = (keyboard) item;
             data[i][2] = keyboardItem.getdType();
             data[i][8] = keyboardItem.getLayout(); 
         } else if (item instanceof mouse) {
             mouse mouseItem = (mouse) item;
             data[i][2] = mouseItem.getdType();
             data[i][8] = mouseItem.getBtns();
         }
     }

     DefaultTableModel model = new DefaultTableModel(data, columnNames);

     // Set the table model
     cus_view.setModel(model);
     scrollPane.setViewportView(cus_view);
        
        
        
        btnNewButton = new JButton("Add");
        btnNewButton.setBounds(345, 151, 68, 19);
     // Inside your ActionListener for the "Add" button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
  
                // Check if a user is logged in
                if (currentUser != null) {
                   
                    if (currentUser.getType() == userType.customer) {
                        // Cast the current user to customer
                        customer currentCustomer = (customer) currentUser;
                        
                        // Check if an item is selected in the table
                        int selectedRow = cus_view.getSelectedRow();
                        if (selectedRow != -1 && selectedRow < cus_view.getRowCount()) {
                            // Get the selected product from the table
                            product selectedProduct = currentList.get(selectedRow);
                            

                            // Prompt the user for the quantity
                            String quantityString = JOptionPane.showInputDialog(contentPane, "Enter the quantity:");
                            
                            // Check if the user clicked cancel or closed the dialog
                            if (quantityString != null && !quantityString.isEmpty()) {
                                try {
                                    int quantity = Integer.parseInt(quantityString);
                                    
                                    // check quantity does not exceed quantity in stock
                                    if (quantity <= selectedProduct.getQuantityInStock()) {
                                    	 //Assign the quantity to the selected product
                                        if (selectedProduct instanceof keyboard) {
                                            keyboard keyboardItem = (keyboard) selectedProduct;
                                            keyboardItem.setNoItems(quantity); 
                                        } else if (selectedProduct instanceof mouse) {
                                            mouse mouseItem = (mouse) selectedProduct;
                                            mouseItem.setNoItems(quantity);
                                        }
                                    // Add the selected item to the user's basket
                                    currentCustomer.addBasket(selectedProduct);
                                    // Notify the user that the item has been added to the basket
                                    JOptionPane.showMessageDialog(contentPane, "Item added to basket.");
                                    
                                    } else {
                                    	JOptionPane.showMessageDialog(contentPane, "Amount exceeds available stock. Please select a new amount");
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(contentPane, "Invalid quantity. Please enter a valid number.");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Please select an item from the table.");
                        }
                    } 
                } else {
                    // Notify the user to log in first
                    String message = "Please log in first";
                    JOptionPane.showMessageDialog(contentPane, message);
                }
            }
        });
        panel_2.add(btnNewButton);
        
        lblNewLabel_1 = new JLabel("Add to basket");
        lblNewLabel_1.setBounds(345, 131, 77, 19);
        panel_2.add(lblNewLabel_1);
        
        btnNewButton_1 = new JButton("View Basket");
        btnNewButton_1.setBounds(324, 181, 109, 23);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Navigate to the basket tab
                tabbedPane.setSelectedIndex(3);
        	}
        });
        panel_2.add(btnNewButton_1);
        
        barField = new JTextField();
        barField.setBounds(337, 51, 85, 20);
        panel_2.add(barField);
        barField.setColumns(10);
        
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(337, 76, 85, 23);
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = barField.getText();
                try {
                    int searchTerm = Integer.parseInt(inputText);
                    List<product> results = stock.barcodeSearch(searchTerm);
                    //Assign results to currentList to ensure proper selection of search results
                    currentList = results;

                    
                    if (results.isEmpty()) {
                        JOptionPane.showMessageDialog(contentPane, "No products match this Barcode.");
                        return; // Exit the method if no results are found
                    }
                 // Clear the current table data
                    DefaultTableModel model = (DefaultTableModel) cus_view.getModel();
                    model.setRowCount(0);

                    // Populate the data array with the data from the search results
                    for (product item : results) {
                        Object[] rowData = new Object[columnNames.length];
                        rowData[0] = item.getBarcode();
                        rowData[1] = item.getcategory();
                        rowData[3] = item.getBrand();
                        rowData[4] = item.getColor();
                        rowData[5] = item.getConnectivity();
                        rowData[6] = item.getQuantityInStock();
                        rowData[7] = item.getRetailPrice();
                        if (item instanceof keyboard) {
                            keyboard keyboardItem = (keyboard) item;
                            rowData[2] = keyboardItem.getdType();
                            rowData[8] = keyboardItem.getLayout();
                        } else if (item instanceof mouse) {
                            mouse mouseItem = (mouse) item;
                            rowData[2] = mouseItem.getdType();
                            rowData[8] = mouseItem.getBtns();
                        }
                        model.addRow(rowData);
                    }

                    // Refresh the table to display changes
                    cus_view.setModel(model);
                    cus_view.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Invalid barcode. Please enter a valid number.");
                }
            }
        });

        panel_2.add(searchBtn);
        
        JLabel lblNewLabel_3 = new JLabel("Barcode search");
        lblNewLabel_3.setBounds(337, 37, 96, 14);
        panel_2.add(lblNewLabel_3);
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Re-assign cusView to currentList upon refreshing the table
        		currentList = cusView;
        		// Clear the current table data
                DefaultTableModel model = (DefaultTableModel) cus_view.getModel();
                model.setRowCount(0);

                // Repopulate the table with the original data
                for (int i = 0; i < cusView.size(); i++) {
                    product item = cusView.get(i);
                    Object[] rowData = new Object[columnNames.length];
                    rowData[0] = item.getBarcode();
                    rowData[1] = item.getcategory();
                    rowData[3] = item.getBrand();
                    rowData[4] = item.getColor();
                    rowData[5] = item.getConnectivity();
                    rowData[6] = item.getQuantityInStock();
                    rowData[7] = item.getRetailPrice();
                    if (item instanceof keyboard) {
                        keyboard keyboardItem = (keyboard) item;
                        rowData[2] = keyboardItem.getdType();
                        rowData[8] = keyboardItem.getLayout();
                    } else if (item instanceof mouse) {
                        mouse mouseItem = (mouse) item;
                        rowData[2] = mouseItem.getdType();
                        rowData[8] = mouseItem.getBtns();
                    }
                    model.addRow(rowData);
                }

                // Refresh the table to display changes
                cus_view.setModel(model);
                cus_view.repaint();
        	}
        });
        refreshBtn.setBounds(337, 0, 85, 23);
        panel_2.add(refreshBtn);

        
        panel_3 = new JPanel();
        tabbedPane.addTab("Admin_view", null, panel_3, null);
        panel_3.setLayout(null);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 11, 330, 215);
        panel_3.add(scrollPane_2);
        
        admin_view = new JTable();
       
     // Creating the table model with column names and data
        String[] columnNames2 = {"Barcode", "Product category", "Device Type","Brand", "Color", "Connectivity", "Quantity", "Retail-Price","Original-Price", "Layout/Buttons"};
        Object[][] data2 = new Object[adminView.size()][columnNames2.length];
        

        // Populate the data array with the data from the cusView list
        for (int i = 0; i < adminView.size(); i++) {
            product item = adminView.get(i);
            data2[i][0] = item.getBarcode();
            data2[i][1] = item.getcategory();
            data2[i][3] = item.getBrand();
            data2[i][4] = item.getColor();
            data2[i][5] = item.getConnectivity();
            data2[i][6] = item.getQuantityInStock();
            data2[i][7] = item.getRetailPrice();
            data2[i][8] = item.getOriginalCost();
            if (item instanceof keyboard) {
                keyboard keyboardItem = (keyboard) item;
                data2[i][2] = keyboardItem.getdType();
                data2[i][9] = keyboardItem.getLayout(); 
            } else if (item instanceof mouse) {
                mouse mouseItem = (mouse) item;
                data2[i][2] = mouseItem.getdType();
                data2[i][9] = mouseItem.getBtns();
            }
        }

        DefaultTableModel model2 = new DefaultTableModel(data2, columnNames2);

        // Set the table model
        admin_view.setModel(model2);
        scrollPane_2.setViewportView(admin_view);
        
        JButton newProdBtn = new JButton("Add");
        newProdBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Navigate to the basket tab
                tabbedPane.setSelectedIndex(5);
        	
        	}
        });
        newProdBtn.setBounds(342, 179, 81, 23);
        panel_3.add(newProdBtn);
        
        JButton increaseBtn = new JButton("Increase");
        increaseBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		  // Check if a user is logged in
                if (currentUser != null) {
                   
                    if (currentUser.getType() == userType.admin) {
                        // Cast the current user to admin
                        admin currentAdmin = (admin) currentUser;
                        
                        // Check if an item is selected in the table
                        int selectedRow = admin_view.getSelectedRow();
                        if (selectedRow != -1 && selectedRow < admin_view.getRowCount()) {
                            // Get the selected product from the table
                            product selectedProduct = currentList.get(selectedRow);
                            

                            // Prompt the user for the quantity
                            String quantityString = JOptionPane.showInputDialog(contentPane, "Enter the quantity to be added on to the product:");
                            
                            // Check if the user clicked cancel or closed the dialog
                            if (quantityString != null && !quantityString.isEmpty()) {
                                try {
                                    int quantity = Integer.parseInt(quantityString);
                                    // Increase the products quantity the given quantity
                                    currentAdmin.increaseQuant(adminView, selectedProduct, quantity);
                                    //Write the changes into the file
                                    stock.writeIntoFile(adminView, "Stock.txt");
                                   
                                    // Notify the user that the item quantity has been increased
                                    JOptionPane.showMessageDialog(contentPane, "Item quantity has been icreased");
                                 
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(contentPane, "Invalid quantity. Please enter a valid number.");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Please select an item from the table.");
                        }
                    } 
                } else {
                    // Notify the user to log in first
                    String message = "Please log in first";
                    JOptionPane.showMessageDialog(contentPane, message);
                }
                //Ensure currentList is set back to adminView after an increment 
                currentList = adminView;
        	}
        });
        increaseBtn.setBounds(342, 126, 81, 23);
        panel_3.add(increaseBtn);
        
        JLabel lblNewLabel_5 = new JLabel("Increase");
        lblNewLabel_5.setBounds(350, 97, 63, 14);
        panel_3.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Quantity");
        lblNewLabel_6.setBounds(350, 111, 63, 14);
        panel_3.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Add new");
        lblNewLabel_7.setBounds(350, 154, 63, 14);
        panel_3.add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Product");
        lblNewLabel_8.setBounds(350, 165, 63, 14);
        panel_3.add(lblNewLabel_8);
        
        refreshBtn2 = new JButton("Refresh");
        refreshBtn2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Clear the current table data
                DefaultTableModel model = (DefaultTableModel) admin_view.getModel();
                model.setRowCount(0);

                // Repopulate the table with the original data
                for (int i = 0; i < adminView.size(); i++) {
                    product item = adminView.get(i);
                    Object[] rowData = new Object[columnNames2.length];
                    rowData[0] = item.getBarcode();
                    rowData[1] = item.getcategory();
                    rowData[3] = item.getBrand();
                    rowData[4] = item.getColor();
                    rowData[5] = item.getConnectivity();
                    rowData[6] = item.getQuantityInStock();
                    rowData[7] = item.getRetailPrice();
                    rowData[8] = item.getOriginalCost();
                    if (item instanceof keyboard) {
                        keyboard keyboardItem = (keyboard) item;
                        rowData[2] = keyboardItem.getdType();
                        rowData[9] = keyboardItem.getLayout();
                    } else if (item instanceof mouse) {
                        mouse mouseItem = (mouse) item;
                        rowData[2] = mouseItem.getdType();
                        rowData[9] = mouseItem.getBtns();
                    }
                    model.addRow(rowData);
                }

                // Refresh the table to display changes
                admin_view.setModel(model);
                admin_view.repaint();
        	}
        });
        refreshBtn2.setBounds(338, 0, 85, 23);
        panel_3.add(refreshBtn2);
        
        lblNewLabel_12 = new JLabel("Barcode search");
        lblNewLabel_12.setBounds(338, 37, 96, 14);
        panel_3.add(lblNewLabel_12);
        
        barcode = new JTextField();
        barcode.setColumns(10);
        barcode.setBounds(338, 51, 85, 20);
        panel_3.add(barcode);
        
        searchBtn2 = new JButton("Search");
        searchBtn2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String inputText = barcode.getText();
                try {
                    int searchTerm = Integer.parseInt(inputText);
                    List<product> results = stock.barcodeSearch(searchTerm);
                    //Assign results to currentList to ensure proper selection of search results
                    currentList = results;

                    
                    if (results.isEmpty()) {
                        JOptionPane.showMessageDialog(contentPane, "No products match this Barcode.");
                        return; // Exit the method if no results are found
                    }
                 // Clear the current table data
                    DefaultTableModel model = (DefaultTableModel) admin_view.getModel();
                    model.setRowCount(0);

                    // Populate the data array with the data from the search results
                    for (product item : results) {
                        Object[] rowData = new Object[columnNames2.length];
                        rowData[0] = item.getBarcode();
                        rowData[1] = item.getcategory();
                        rowData[3] = item.getBrand();
                        rowData[4] = item.getColor();
                        rowData[5] = item.getConnectivity();
                        rowData[6] = item.getQuantityInStock();
                        rowData[7] = item.getRetailPrice();
                        rowData[8] = item.getOriginalCost();
                        if (item instanceof keyboard) {
                            keyboard keyboardItem = (keyboard) item;
                            rowData[2] =keyboardItem.getdType();
                            rowData[9] = keyboardItem.getLayout();
                        } else if (item instanceof mouse) {
                            mouse mouseItem = (mouse) item;
                            rowData[2] = mouseItem.getdType();
                            rowData[9] = mouseItem.getBtns();
                        }
                        model.addRow(rowData);
                    }

                    // Refresh the table to display changes
                    admin_view.setModel(model);
                    admin_view.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Invalid barcode. Please enter a valid number.");
                }
        	}
        });
        searchBtn2.setBounds(338, 76, 85, 23);
        panel_3.add(searchBtn2);
        
        panel = new JPanel();
        tabbedPane.addTab("Basket", null, panel, null);
        panel.setLayout(null);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 0, 298, 204);
        panel.add(scrollPane_1);
        
        basket = new JTable();
        showBtn = new JButton("Update");
        
        showBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	if(currentUser != null && currentUser.getType() == userType.customer) { 
            		customer current = (customer) currentUser;
            		List<product> baskItems = current.getBasket();
            		// Creating the table model with column names and data
            		String[] columnNames1 = {"Barcode", "Product category", "Brand", "Price", "Quantity"};
            		Object[][] data1 = new Object[baskItems.size()][columnNames1.length];
            
            		for (int i = 0; i < baskItems.size(); i++) {
            			product item = baskItems.get(i);
            			data1[i][0] = item.getBarcode();
            			data1[i][1] = item.getcategory();
            			data1[i][2] = item.getBrand();
            			data1[i][3] = item.getRetailPrice();
            			 if (item instanceof keyboard) {
            	             keyboard keyboardItem = (keyboard) item;
            	             data1[i][4] = keyboardItem.getNoItems(); 
            	         } else if (item instanceof mouse) {
            	             mouse mouseItem = (mouse) item;
            	             data1[i][4] = mouseItem.getNoItems();
            	         }
            			
            		}
            		DefaultTableModel model1 = new DefaultTableModel(data1, columnNames1);
            		// Set the table model
            		basket.setModel(model1);
            		scrollPane_1.setViewportView(basket);
            		
            	}
            	 
            }
        });
        showBtn.setBounds(324, 32, 89, 23);
        panel.add(showBtn);
    	
        JButton clearBas = new JButton("Clear Basket");
        clearBas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(currentUser != null && currentUser.getType() == userType.customer) { 
            		customer current = (customer) currentUser;
            		current.clearBasket();
        		}
        	}
        });
        clearBas.setBounds(310, 181, 113, 23);
        panel.add(clearBas);
     
        
        lblNewLabel_2 = new JLabel("Basket Total");
        lblNewLabel_2.setBounds(337, 78, 76, 14);
        panel.add(lblNewLabel_2);
        
        JTextPane total = new JTextPane();
        total.setBounds(337, 103, 61, 20);
        showBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (currentUser.getType() == userType.customer ) {
        			customer currentCustomer = (customer) currentUser;
        			double userTotal = currentCustomer.basketTotal();
        			total.setText("£"+ String.format("%.2f", userTotal));
        		}
        	}
        });
        panel.add(total);
        
        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Navigate to the checkout tab
                tabbedPane.setSelectedIndex(4);
        	}
        });
        checkoutBtn.setBounds(324, 147, 89, 23);
        panel.add(checkoutBtn);
        
        lblNewLabel_4 = new JLabel("View items");
        lblNewLabel_4.setBounds(345, 11, 53, 14);
        panel.add(lblNewLabel_4);
        
       
        panel_4 = new JPanel();
        tabbedPane.addTab("Checkout", null, panel_4, null);
        panel_4.setLayout(null);
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(212, 25, 14, 201);
        panel_4.add(separator);
        
        JCheckBox payPalBox = new JCheckBox("PayPal");
        payPalBox.setBounds(236, 25, 99, 23);
        panel_4.add(payPalBox);
        
        JCheckBox cardBox = new JCheckBox("Credit Card");
        cardBox.setBounds(10, 25, 99, 23);
        panel_4.add(cardBox);
        
        JButton btnNewButton_2 = new JButton("Pay!");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		address fullAddress = new address(currentUser.getHouseNumber(), currentUser.getPostcode(),  currentUser.getCity());
        		Date today = new Date();
        		if(cardBox.isSelected()) {
        			try {
        				int cardNum = Integer.parseInt(cardNo.getText());
                		int cvc = Integer.parseInt(sCode.getText());
        				if(cardNo.getText().length() != 6) {
        					JOptionPane.showMessageDialog(contentPane, "Invalid Card Number");
                            return; // Exit if Card Number is not valid
        				}
        				else if(sCode.getText().length() != 3) {
        					JOptionPane.showMessageDialog(contentPane, "Invalid Security Code");
                            return; // Exit if Security code is not valid
        				}
        				else {
        					creditCard userCard = new creditCard(cardNum,cvc,today);
        					if (currentUser.getType() == userType.customer ) {
        	        			customer currentCustomer = (customer) currentUser;
        	        			double totalAmount = currentCustomer.basketTotal();
        	        			receipt receipt = userCard.processPayment(totalAmount, fullAddress);
        	        			//check that the basket is not empty
        	        			if(currentCustomer.getBasket().size() < 1) {
        	        				JOptionPane.showMessageDialog(contentPane, "Please add items to basket!");
                                    return; // Exit if basket is empty
        	        			}
        	        			
        	        			for(product item: currentCustomer.getBasket()) {
        	        				if (item instanceof keyboard) {
        	            	             keyboard keyboardItem = (keyboard) item;
        	            	             stock.updateProductQuantity(item, keyboardItem.getNoItems());
        	            	            
        	            	         } else if (item instanceof mouse) {
        	            	             mouse mouseItem = (mouse) item;
        	            	             stock.updateProductQuantity(item, mouseItem.getNoItems());
        	            	            
        	            	             
        	            	         }
        	        				
        	        			}
        	        			//write the updated stock back into the file
        	        			 List<product> invent = stock.getStock();
	            	             stock.writeIntoFile(invent,"Stock.txt");
	            	             JOptionPane.showMessageDialog(contentPane,"£" + receipt.getAmount()+ " Paid by Credit card using "+ userCard.getCardNo() + " on " + userCard.getDate() +",and the delivery address is " + receipt.getFullAddress() );
	            	             
	            	             //clear the user Basket and text fields 
	            	             currentCustomer.clearBasket();
	            	             cardNo.setText("");
	            	             sCode.setText("");
	            	             email.setText("");
        					} else {
        						JOptionPane.showMessageDialog(contentPane, "Only customers can perform this action");
        					}
        				}
        			}catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(contentPane, "Invalid character in security code or card number.");
                    } catch (ConcurrentModificationException ex) {
                    	
                    }
        			
        		}else if(payPalBox.isSelected()) {
        			String Email = email.getText();
        			payPal payPalUser = new payPal(Email,today);
        			if(payPalUser.isValidEmail(Email)) {
        				if (currentUser.getType() == userType.customer ) {
    	        			customer currentCustomer = (customer) currentUser;
    	        			double totalAmount = currentCustomer.basketTotal();
    	        			receipt receipt = payPalUser.processPayment(totalAmount, fullAddress);
    	        			//check that the basket is not empty
    	        			if(currentCustomer.getBasket().size() < 1) {
    	        				JOptionPane.showMessageDialog(contentPane, "Please add items to basket!");
                                return; // Exit if basket is empty
    	        			}
    	        			
    	        			for(product item: currentCustomer.getBasket()) {
    	        				if (item instanceof keyboard) {
    	            	             keyboard keyboardItem = (keyboard) item;
    	            	             stock.updateProductQuantity(item, keyboardItem.getNoItems());
    	            	            
    	            	         } else if (item instanceof mouse) {
    	            	             mouse mouseItem = (mouse) item;
    	            	             stock.updateProductQuantity(item, mouseItem.getNoItems());
    	            	         }
    	        				
    	        			}
    	        			//write the updated stock back into the file
    	        			 List<product> invent = stock.getStock();
            	             stock.writeIntoFile(invent,"Stock.txt");
            	             JOptionPane.showMessageDialog(contentPane,"£" + receipt.getAmount()+ " Paid by PayPal using "+ payPalUser.getEmail() + " on " + payPalUser.getDate() +",and the delivery address is " + receipt.getFullAddress() );
            	             
            	             //clear the user Basket and text fields 
            	             currentCustomer.clearBasket();
            	             cardNo.setText("");
            	             sCode.setText("");
            	             email.setText("");
    					}else {
    						JOptionPane.showMessageDialog(contentPane, "Only customers can perform this action");
    					}
        				
        			} else {
        				JOptionPane.showMessageDialog(contentPane, "Invalid email address, please try again" );
        			}
        			
        			
        		}
        				
        	}
        });
        btnNewButton_2.setBounds(324, 176, 89, 23);
        panel_4.add(btnNewButton_2);
        
        lblNewLabel_9 = new JLabel("Card Number");
        lblNewLabel_9.setBounds(14, 55, 69, 14);
        panel_4.add(lblNewLabel_9);
        
        lblNewLabel_10 = new JLabel("Security code");
        lblNewLabel_10.setBounds(10, 80, 79, 14);
        panel_4.add(lblNewLabel_10);
        
        cardNo = new JTextField();
        cardNo.setBounds(110, 52, 96, 20);
        panel_4.add(cardNo);
        cardNo.setColumns(10);
        
        sCode = new JTextField();
        sCode.setBounds(110, 77, 62, 20);
        panel_4.add(sCode);
        sCode.setColumns(10);
        
        lblNewLabel_11 = new JLabel("Email");
        lblNewLabel_11.setBounds(227, 55, 48, 14);
        panel_4.add(lblNewLabel_11);
        
        email = new JTextField();
        email.setBounds(260, 52, 135, 20);
        panel_4.add(email);
        email.setColumns(10);
        
        panel_5 = new JPanel();
        tabbedPane.addTab("New Product", null, panel_5, null);
        panel_5.setLayout(null);
        
        JLabel lblNewLabel_13 = new JLabel("Addition of new product");
        lblNewLabel_13.setBounds(159, 0, 155, 14);
        panel_5.add(lblNewLabel_13);
        
        JComboBox categoryBox = new JComboBox(ProductCategory.values());
        categoryBox.setBounds(107, 25, 97, 22);
        panel_5.add(categoryBox);
        
        JLabel lblNewLabel_14 = new JLabel("Product Category");
        lblNewLabel_14.setBounds(10, 29, 91, 14);
        panel_5.add(lblNewLabel_14);
        
        JComboBox connectBox = new JComboBox(ConnectivityType.values());
        connectBox.setBounds(107, 58, 97, 22);
        panel_5.add(connectBox);
        
        JLabel lblNewLabel_15 = new JLabel("Connectivity");
        lblNewLabel_15.setBounds(10, 62, 87, 14);
        panel_5.add(lblNewLabel_15);
        
        bCode = new JTextField();
        bCode.setBounds(108, 87, 96, 20);
        panel_5.add(bCode);
        bCode.setColumns(10);
        
        brand = new JTextField();
        brand.setBounds(108, 114, 96, 20);
        panel_5.add(brand);
        brand.setColumns(10);
        
        color = new JTextField();
        color.setBounds(108, 145, 96, 20);
        panel_5.add(color);
        color.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Barcode");
        lblNewLabel_16.setBounds(10, 90, 91, 14);
        panel_5.add(lblNewLabel_16);
        
        JLabel lblNewLabel_17 = new JLabel("Brand");
        lblNewLabel_17.setBounds(10, 117, 87, 14);
        panel_5.add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("Color");
        lblNewLabel_18.setBounds(10, 145, 91, 14);
        panel_5.add(lblNewLabel_18);
        
        qty = new JTextField();
        qty.setBounds(365, 26, 48, 20);
        panel_5.add(qty);
        qty.setColumns(10);
        
        JLabel lblNewLabel_19 = new JLabel("Quantity");
        lblNewLabel_19.setBounds(259, 29, 79, 14);
        panel_5.add(lblNewLabel_19);
        
        oCost = new JTextField();
        oCost.setBounds(365, 59, 48, 20);
        panel_5.add(oCost);
        oCost.setColumns(10);
        
        rCost = new JTextField();
        rCost.setBounds(365, 87, 48, 20);
        panel_5.add(rCost);
        rCost.setColumns(10);
        
        mBtns = new JTextField();
        mBtns.setBounds(365, 117, 48, 20);
        panel_5.add(mBtns);
        mBtns.setColumns(10);
        
        JComboBox kLayout = new JComboBox(layout.values());
        kLayout.setBounds(365, 146, 48, 22);
        panel_5.add(kLayout);
        
        lblNewLabel_20 = new JLabel("Original cost");
        lblNewLabel_20.setBounds(259, 62, 79, 14);
        panel_5.add(lblNewLabel_20);
        
        lblNewLabel_21 = new JLabel("Retail Price");
        lblNewLabel_21.setBounds(259, 90, 79, 14);
        panel_5.add(lblNewLabel_21);
        
        lblNewLabel_22 = new JLabel("Mouse buttons");
        lblNewLabel_22.setBounds(259, 120, 91, 14);
        panel_5.add(lblNewLabel_22);
        
        lblNewLabel_23 = new JLabel("Keyboard layout");
        lblNewLabel_23.setBounds(259, 151, 96, 14);
        panel_5.add(lblNewLabel_23);
        
        dTypeBox = new JComboBox(deviceType.values());
        dTypeBox.setBounds(334, 180, 79, 22);
        panel_5.add(dTypeBox);
        
        lblNewLabel_24 = new JLabel("Device Type");
        lblNewLabel_24.setBounds(259, 184, 79, 14);
        panel_5.add(lblNewLabel_24);
        
        JButton btnNewButton_3 = new JButton("Finish");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ProductCategory category = (ProductCategory) categoryBox.getSelectedItem();
        		ConnectivityType connectivity = (ConnectivityType) connectBox.getSelectedItem();
        		layout layout = (layout) kLayout.getSelectedItem();
        		deviceType dType = (deviceType) dTypeBox.getSelectedItem();
        		String barcode = bCode.getText();
        		String b_rand = brand.getText();
        		String c_olor = color.getText();
        		String quantity = qty.getText();
        		String rPrice = rCost.getText();
        		String oPrice = oCost.getText();
        		String buttons = mBtns.getText();
        		
        		// Check if the current User is logged in
        		if (currentUser == null) {
        		    JOptionPane.showMessageDialog(contentPane, "Please log in to complete this action");
        		} else {
        		    // Cast the current user to admin if they are an admin
        		    if (currentUser.getType() == userType.admin) {
        		    	 // Cast the current user to admin
                        admin currentAdmin = (admin) currentUser;
                        if(barcode.length() == 6) {
        		        try {
        		            // Parse input values
        		            int BCode = Integer.parseInt(barcode);
        		            int num = Integer.parseInt(quantity);
        		            double oPrice1 = Double.parseDouble(oPrice);
        		            double rPrice1 = Double.parseDouble(rPrice);
        		         // Check if the barcode already exists in the entire stock
        		            boolean barcodeExists = false;
        		            for (product inList : stock.getStock()) {
        		                if (inList.getBarcode() == BCode) {
        		                    barcodeExists = true;
        		                    break; // Exit the loop once a matching barcode is found
        		                }
        		            }

        		            if (!barcodeExists) {
        		                // Barcode doesn't exist in the stock, proceed to add the new product
        		                if (category == ProductCategory.mouse) {
        		                    // Parse the input for number of Mouse buttons
        		                    int mButtons = Integer.parseInt(buttons);
        		                    // Create and add new product
        		                    List<product> newProduct = currentAdmin.newMouse(adminView, BCode, b_rand, dType, c_olor, connectivity, num, oPrice1, rPrice1, category, mButtons);
        		                    stock.writeIntoFile(newProduct, "Stock.txt");
        		                    // Alert the user that the new product has been added to the stock
        		                    JOptionPane.showMessageDialog(contentPane, "New mouse successfully added!");
        		                    //clear the input fields
        		                    color.setText("");
        		                    bCode.setText("");
        		                    brand.setText("");
        		                    qty.setText("");
        		                    rCost.setText("");
        		                    oCost.setText("");
        		                    mBtns.setText("");
        		                      
        		                } else if (category == ProductCategory.keyboard) {
        		                    // Create and add new product
        		                    List<product> newProduct = currentAdmin.newKeyboard(adminView, BCode, b_rand, dType, c_olor, connectivity, num, oPrice1, rPrice1, category, layout);
        		                    stock.writeIntoFile(newProduct, "Stock.txt");
        		                    // Alert the user that the new product has been added to the stock
        		                    JOptionPane.showMessageDialog(contentPane, "New Keyboard successfully added!");
        		                    //clear the input fields
        		                    color.setText("");
        		                    bCode.setText("");
        		                    brand.setText("");
        		                    qty.setText("");
        		                    rCost.setText("");
        		                    oCost.setText("");
        		                    mBtns.setText("");
        		                }
        		            } else {
        		                // Barcode already exists in the stock
        		                JOptionPane.showMessageDialog(contentPane, "The barcode already exists in Stock");
        		            }

        		        } catch (NumberFormatException ex) {
        		            JOptionPane.showMessageDialog(contentPane, "Invalid input. Please enter valid numbers.");
        		        }
        		    } else {
        		    	//Notify the use the barcode is invalid
        		    	JOptionPane.showMessageDialog(contentPane, "Invalid barcode");
        		    }
                        
        		    }
                        else {
        		        // Notify the user that only admins can carry out this action
        		        JOptionPane.showMessageDialog(contentPane, "Only admins can add items");
        		    }
        		}

        		
        		
        	}
        });
        btnNewButton_3.setBounds(10, 180, 89, 23);
        panel_5.add(btnNewButton_3);
    }

   
}
