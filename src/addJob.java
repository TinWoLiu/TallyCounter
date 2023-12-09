import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class addJob implements ActionListener {
    private JButton btu_confirm;
    private JTextField companyName_txt, position_txt, salary_txt;
    private ArrayList<Company> addCompanyToCSV;
    private String now, newID;
    JFrame frame;
    private startScreen StartScreen;
    public addJob() {
        ///// JFrame setup
        JFrame frame = new JFrame();
        frame.setTitle("Add a Job");
        frame.setLayout(new FlowLayout());

        ///// JPanel set up
        JPanel addJobPanel = new JPanel();
        addJobPanel.setBackground(Color.YELLOW);
        Border title_Border = BorderFactory.createTitledBorder("Fill in the details: ");
        addJobPanel.setBorder(title_Border);

        ///// item setup
        JLabel companyName = new JLabel("Company name: ");
        companyName_txt = new JTextField(20);
        JLabel position = new JLabel("Position: ");
        position_txt = new JTextField(30);
        JLabel salary = new JLabel("Salary: ");
        salary_txt = new JTextField(6);

        btu_confirm = new JButton("Confirm");
        btu_confirm.addActionListener(this);

        addCompanyToCSV = new ArrayList<>();

        ///// Adding items to Panels and Frame
        addJobPanel.add(companyName);
        addJobPanel.add(companyName_txt);
        addJobPanel.add(position);
        addJobPanel.add(position_txt);
        addJobPanel.add(salary);
        addJobPanel.add(salary_txt);
        frame.add(addJobPanel);
        frame.add(btu_confirm);

        frame.setSize(830, 140);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btu_confirm) {
            String addCompanyName = companyName_txt.getText();
            String addPosition = position_txt.getText();
            String addSalary = salary_txt.getText();
            try (PrintWriter out = new PrintWriter(new File("src\\Jobs.csv"))){
            out.println(newID + "," + addCompanyName + "," + addPosition + "," + addSalary + "," + currentDate());
            } catch (FileNotFoundException exception){
                JOptionPane.showMessageDialog(frame,"Cannot find file!", "Warning!" , JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // a method using the "time" class with this utility, it is possible to format and get the current time
    public String currentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        now = LocalDateTime.now().format(formatter);
        return now;
    }

    public void getID(String id) {
        int tempID = Integer.parseInt(id) + 1;
        newID = Integer.toString(tempID);
    }

}
