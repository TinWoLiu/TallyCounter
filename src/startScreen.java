import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class startScreen implements ActionListener {
    private JButton btu_add, btu_jobFound, btu_update;
    private JTextField countNum_txt;
    private String Counter;
    private addJob addJobScreen;
    public startScreen() {
        ///// JFrame set up
        JFrame frame = new JFrame();
        frame.setTitle("Tally Counter");
        frame.setLayout(new FlowLayout());

        ///// JPanel set up
        JPanel title = new JPanel();
        title.setBackground(Color.CYAN);
        Border title_Border = BorderFactory.createTitledBorder("");
        title.setBorder(title_Border);
        title.setPreferredSize(new Dimension(550, 50));

        JPanel counterMain = new JPanel();
        counterMain.setPreferredSize(new Dimension(550, 170));

        ///// Font set up
        Font font1 = new Font("Arial", Font.BOLD, 20);
        Font font2 = new Font("Comic Sans MS", Font.BOLD, 25);

        ///// Items set up
        JLabel topText = new JLabel("Tally Counter! See how many jobs you have applied!", SwingConstants.CENTER);
        topText.setFont(font1);

        countNum_txt = new JTextField(Counter);
        countNum_txt.setPreferredSize(new Dimension(150,150));
        countNum_txt.setFont(font2);
        countNum_txt.setHorizontalAlignment(JTextField.CENTER);
        countNum_txt.setEditable(false);

        btu_update = new JButton("Update");
        btu_update.addActionListener(this);

        btu_add = new JButton("Add job");
        btu_add.addActionListener(this);

        btu_jobFound = new JButton("Check applied jobs");
        btu_jobFound.addActionListener(this);

        ///// Adding items to Panels and Frame
        title.add(topText);
        frame.add(title);
        frame.add(btu_update);
        counterMain.add(countNum_txt);
        frame.add(counterMain);
        frame.add(btu_add); frame.add(btu_jobFound);

        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btu_add) {
            addJob addJob = new addJob();
        }
        if(e.getSource() == btu_jobFound) {
            readJobList jobList = new readJobList();
        }
        if(e.getSource() == btu_update) {
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
    }

//    public void counterNum(ArrayList<Company> counterNum_list) {
//        int MaxCounter = 0;
//        for (int i = 0; i < counterNum_list.size() ; i++) {
//            if(MaxCounter < counterNum_list.get(i).getId()){
//                MaxCounter = counterNum_list.get(i).getId();
//            }
//        }
//        Counter = Integer.toString(MaxCounter);
//    }
}
