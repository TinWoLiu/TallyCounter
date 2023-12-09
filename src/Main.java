public class Main {
    public static void main(String[] args) {
        startScreen start = new startScreen();
        readJobList read = new readJobList();
        addJob add = new addJob();

        start.passReadJobList(read);
        start.passAddJob(add);
    }

}