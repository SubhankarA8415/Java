import java.util.*;

class Process{
    String process_id;
    int burst_time;
    int arrival_time;
    int remaining_time;
    int turn_around_time;
    int wait_time;
    int response_time;

    Process(String process_id, int burst_time, int arrival_time){
        this.process_id = process_id;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
        this.remaining_time = burst_time;
    }


}

class GanttChart{
    String process_id;
    int start_time;
    int end_time;

    GanttChart(String process_id, int start_time, int end_time){
        this.process_id = process_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}

public class Scheduling{
    int tq;
    int counter;
    int idle;
    Queue<Process> rq = new LinkedList<>();
    ArrayList<Process> processes = new ArrayList<>();
    ArrayList<GanttChart> gantt_chart = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public Scheduling(int tq){
        this.tq = tq;
        this.counter = 0;
        this.idle = 0;
    }

    void create(){
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
            System.out.print("Enter process id for process "+(i+1)+" : ");
            String pid = sc.next();
            System.out.print("Enter arrival time: ");
            int at = sc.nextInt();
            System.out.print("Enter burst time: ");
            int bt = sc.nextInt();
            processes.add(new Process(pid,bt,at));
        }
    }

    void execute_rr(){
        int min_at = Integer.MAX_VALUE;
        Process p = null;
        for (int i = 0; i < processes.size(); i++){
            if (processes.get(i).arrival_time < min_at){
                min_at = processes.get(i).arrival_time;
                p = processes.get(i);
            }
        }
        //processes.remove(p);
        rq.add(p);
        int a_t = p.arrival_time;
        counter = p.arrival_time;
        idle = counter;
        while (!rq.isEmpty()){
            p = rq.remove();
            if (p.burst_time == p.remaining_time){
                p.response_time = counter - p.arrival_time;
            }
            for (int i = 0; i < tq; i++){
                if (p.remaining_time == 0){
                    p.turn_around_time = counter - p.arrival_time;
                    p.wait_time = p.turn_around_time - p.burst_time;
                    rq.remove(p);
                    break;
                }
                for (int j = 0; j < processes.size(); j++){
                    if (processes.get(j).arrival_time == counter){
                        rq.add(processes.get(j));
                        //processes.remove(processes.get(j));
                    }
                }
                counter++;
                p.remaining_time--;
            }
            if (idle != 0){
                gantt_chart.add(new GanttChart("Idle",0,idle));
                idle = 0;
            }
            gantt_chart.add(new GanttChart(p.process_id,a_t,counter));
            if (p.remaining_time !=0) {
                rq.add(p);
            }
            a_t = counter;
        }

    }

    void execute_fcfs(){

        int min_at = Integer.MAX_VALUE;
        Process p = null;
        gantt_chart.clear();
        for (int i = 0; i < processes.size(); i++){
            if (processes.get(i).arrival_time < min_at){
                min_at = processes.get(i).arrival_time;
                p = processes.get(i);
            }
            processes.get(i).turn_around_time = 0; processes.get(i).wait_time = 0; processes.get(i).response_time = 0;
            processes.get(i).remaining_time = processes.get(i).burst_time;
        }
        //processes.remove(p);
        rq.add(p);
        int a_t = p.arrival_time;
        counter = p.arrival_time;
        idle = counter;
        while (!rq.isEmpty()){
            p = rq.remove();
            if (p.burst_time == p.remaining_time){
                p.response_time = counter - p.arrival_time;
            }
            while (true){
                for (int j = 0; j < processes.size(); j++){
                    if (processes.get(j).arrival_time == counter){
                        rq.add(processes.get(j));
                        //processes.remove(processes.get(j));
                    }
                }
                if (p.remaining_time == 0){
                    p.turn_around_time = counter - p.arrival_time;
                    p.wait_time = p.turn_around_time - p.burst_time;
                    rq.remove(p);
                    break;
                }
                counter++;
                p.remaining_time--;
            }
            if (idle != 0){
                gantt_chart.add(new GanttChart("Idle",0,idle));
                idle = 0;
            }
            gantt_chart.add(new GanttChart(p.process_id,a_t,counter));
            a_t = counter;
        }
    }
    void display_gantt_chart(){
        for (int i = 0; i < gantt_chart.size(); i++){
            if (i != gantt_chart.size() - 1) {
                System.out.print(gantt_chart.get(i).process_id + "(" + gantt_chart.get(i).start_time + "-" + gantt_chart.get(i).end_time + ")-> ");
            } else {
                System.out.print(gantt_chart.get(i).process_id + "(" + gantt_chart.get(i).start_time + "-" + gantt_chart.get(i).end_time + ")");
            }
        }
        System.out.println();
    }

    void display_process_table(){
        for (int i = 0; i < processes.size(); i++) {
            System.out.println("Process (" + processes.get(i).process_id + "), Arrival Time (" + processes.get(i).arrival_time + "), Burst Time (" + processes.get(i).burst_time + "), Turn Around Time (" + processes.get(i).turn_around_time + "), Wait Time (" + processes.get(i).wait_time + "), Response Time (" + processes.get(i).response_time + ")");
        }
    }

    float avg_tat_wt_rt(){
        float avg_tat = 0f, avg_wt = 0f, avg_rt = 0f;
        for (Process i: processes){
            avg_tat += i.turn_around_time;
            avg_wt += i.wait_time;
            avg_rt += i.response_time;
        }
        System.out.println("Average turn around time: "+avg_tat/5);
        System.out.println("Average wait time: "+avg_wt/5);
        System.out.println("Average response time: "+avg_rt/5);

        return avg_wt;
    }

    public static void main(String[] args) {
        System.out.print("Enter time quantum for round robin: ");
        Scheduling s = new Scheduling(sc.nextInt());
        s.create();
        s.execute_rr();
        float wt_rr = 0f, wt_fcfs = 0f;
        System.out.println();
        System.out.println("Round Robin");
        System.out.print("Gantt Chart: ");
        s.display_gantt_chart();

        System.out.println();
        System.out.println("Process Table");
        s.display_process_table();

        System.out.println();
        wt_rr = s.avg_tat_wt_rt();

        System.out.println("\n");
        System.out.println("First Come First Serve");
        System.out.print("Gantt Chart: ");
        s.execute_fcfs();
        s.display_gantt_chart();

        System.out.println();
        System.out.println("Process Table");
        s.display_process_table();

        System.out.println();
        wt_fcfs = s.avg_tat_wt_rt();
        System.out.println();

        if (wt_rr < wt_fcfs){
            System.out.println("Comparing average wait time round robin robin algorithm is more effective");
        } else {
            System.out.println("Comparing average wait time round first come first serve algorithm is more efficient");
        }
    }
}