public class Main {
    public static void main(String[] args) {
        startScreen start = new startScreen();
        readJobList read = new readJobList();
        addJob add = new addJob();

        start.passReadJobList(read);    // in order to pass the arrays to different classes
        start.passAddJob(add);
    }

}