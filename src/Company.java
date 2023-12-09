import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Company {
    private String companyName, position, date, salary;
    private int id;


    public Company(int id, String companyName, String position, String salary, String date) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.salary = salary;
        this.date = date;
    }

    public String getCompanyName() {return companyName;}

    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public String getPosition() {return position;}

    public void setPosition(String position) {this.position = position;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
    public String AddNewListCSV() {
        return id + "," + companyName + "," + position + "," + date;
    }

    private void NewJobToCSV(List<Company> newJobList) {
        try {
            PrintWriter out = new PrintWriter("src\\Jobs.csv");

            for (Company NewJob : newJobList) {
                out.println(NewJob.AddNewListCSV());
            }
            System.out.println("New Job added");
            out.close();
        }   catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
