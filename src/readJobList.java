import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class readJobList implements ActionListener, ListSelectionListener {
    private DefaultTableModel companyList;
    private JTable myTable;
    private ArrayList<Company> arrayList;
    private JTextArea jta;
    private JButton btu_populateList;
    private JTextField searchBar;
    private JFrame frame;
    private TableRowSorter<TableModel> rowSorter; // Row sorter class for the filter function
                                                    // to make search function easy
    public readJobList() {
        ///// JFrame setup
        frame = new JFrame();
        frame.setTitle("Jobs you have applied");
        frame.setLayout(new FlowLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.LIGHT_GRAY);
        Border search_Border = BorderFactory.createTitledBorder("Search");
        searchPanel.setBorder(search_Border);

        ///// arraylist setup
        arrayList = new ArrayList<>();

        companyList = new DefaultTableModel();

        ///// JTable setup
        myTable = new JTable(companyList);

        myTable.getSelectionModel().addListSelectionListener(this);

        rowSorter = new TableRowSorter<>(myTable.getModel()); // initialise the row sorter

        myTable.setRowSorter(rowSorter); // adding the row sorter function to the table
        // Create the columns for JTable
        companyList.addColumn("ID");
        companyList.addColumn("Company");
        companyList.addColumn("Position");
        companyList.addColumn("Salary");
        companyList.addColumn("Date");
        // scroll pane for JTable
        JScrollPane scroll = new JScrollPane(myTable);
        scroll.setPreferredSize(new Dimension(530, 300));

        jta = new JTextArea();
        jta.setPreferredSize(new Dimension(500, 50));

        jta.setLineWrap(true);          // line wrap
        jta.setWrapStyleWord(true);     // word wrap

        btu_populateList = new JButton("Populate table");
        btu_populateList.addActionListener(this);

        searchBar = new JTextField(25);

        ///// frame adding objects
        frame.add(btu_populateList);
        frame.add(scroll);
        frame.add(jta);
        searchPanel.add(searchBar);
        frame.add(searchPanel);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        ///// making the row sorter function work
        searchBar.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchBar.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            // necessary function
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchBar.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }


        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == btu_populateList) {
                companyList.setRowCount(0);
                File selectedFile = new File("src\\Jobs.csv");
                try {
                    Scanner scanner_file = new Scanner(selectedFile);
                    scanner_file.nextLine();
                    while (scanner_file.hasNextLine()) {

                        String row = scanner_file.nextLine();

                        String[] parts = row.split(",");

                        String ID = parts[0];
                        String Company = parts[1];
                        String Position = parts[2];
                        String Salary = parts[3];
                        String Date = parts[4];

                        companyList.addRow(new Object[]{ID, Company, Position, Salary, Date});
                        Company list = new Company(ID, Company, Position, Salary, Date);
                        arrayList.add(list);
                    }
                } catch (FileNotFoundException e2) {
                    throw new RuntimeException(e2);
                }
                catch (NoSuchElementException e2) {
                    // catch exception when the csv file is empty and scanner cannot run .nextLine
                    JOptionPane.showMessageDialog(frame,"The Job list is empty!", "Warning!" , JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = myTable.getSelectedRow();
            if (selectedRow != -1) { // Check if a valid row is selected
                jta.setText(arrayList.get(selectedRow).toString());
            }
        }
    }
}
