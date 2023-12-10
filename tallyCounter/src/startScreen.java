import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class startScreen implements ActionListener {
    private JButton btu_update;
    private JTextField countNum_txt;
    private ArrayList<Company> arrayList;
    private String maxID;
    private readJobList ReadJobList; private addJob AddJob;

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

        countNum_txt = new JTextField();
        countNum_txt.setPreferredSize(new Dimension(150,150));
        countNum_txt.setFont(font2);
        countNum_txt.setHorizontalAlignment(JTextField.CENTER);
        countNum_txt.setEditable(false);

        btu_update = new JButton("Update");
        btu_update.addActionListener(this);

        arrayList = new ArrayList<>();
        ///// Adding items to Panels and Frame
        title.add(topText);
        frame.add(title);
        frame.add(btu_update);
        counterMain.add(countNum_txt);
        frame.add(counterMain);

        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btu_update) {
            try {
                arrayList.clear();
                File selectedFile = new File("src\\Jobs.csv");
                Scanner scanner_file = new Scanner(selectedFile);
                //scanner_file.nextLine();
                while (scanner_file.hasNextLine()) {

                    String row = scanner_file.nextLine();

                    String[] parts = row.split(",");

                    String ID = parts[0];
                    String Company = parts[1];
                    String Position = parts[2];
                    String Salary = parts[3];
                    String Date = parts[4];

                    Company list = new Company(Integer.parseInt(ID), Company, Position, Salary, Date);
                    arrayList.add(list);
                }
                appendTheList(arrayList);
                ReadJobList.getTable(arrayList);
                AddJob.getArrayList(arrayList);     // pass for the current arraylist to readJob and addJob classes
                AddJob.getID(maxID);
            }   catch(FileNotFoundException e2){
                throw new RuntimeException(e2);
            }
        }
    }
    public void appendTheList(ArrayList<Company> listForJTable) {
        int tempMaxID = 0;
        for (int i = 1; i <= listForJTable.size() ; i++) {
            tempMaxID = i;
        }
        maxID = Integer.toString(tempMaxID);    // the current number for the textfield for the counter is based on
                                                // the ID
        countNum_txt.setText(maxID);
    }
    public void passReadJobList(readJobList ReadJobList){ this.ReadJobList = ReadJobList; }

    public void passAddJob(addJob AddJob){ this.AddJob = AddJob; }

}

