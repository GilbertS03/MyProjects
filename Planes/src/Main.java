import java.util.*;

class Plane{
    private final int planeNumber;
    private int fuelAmount;
    private final String flag;
    private int time;
    public Plane(int num, int fuel, String status, int min){
        planeNumber = num;
        fuelAmount = fuel;
        flag = status;
        time = min;
    }

    public void fuelLoss(){fuelAmount--;}
    public void timeLoss(){time--;}

    public int getPlaneNumber(){return planeNumber;}
    public int getFuelAmount(){return fuelAmount;}
    public int getTime(){return time;}
    public String getFlag(){return flag;}
}
public class Main {

    //printing results
    public static void printQueues(Deque<Plane> processedPlanes, Deque<Plane> crashedPlanes){
        int ctr = 0;
        System.out.println("Number of Processed Departing Planes without fuel:");
        for (Plane p : processedPlanes) {
            if(p.getFuelAmount() <= 0 && p.getFlag().equals("d"))
                //System.out.println("Plane " + p.getPlaneNumber() + " fuel: " + p.getFuelAmount());
                ctr++;
        }
        System.out.println(ctr);

        ctr = 0;
        System.out.println("Number of Processed Departing Planes with fuel:");
        for(Plane p : processedPlanes){
            if(p.getFuelAmount() > 0 && p.getFlag().equals("d"))
                //System.out.println("Plane: " + p.getPlaneNumber() + " fuel: " + p.getFuelAmount());
                ctr++;
        }
        System.out.println(ctr);

        ctr = 0;
        System.out.println("Planes Arrived without Crashing:");
        for(Plane p : processedPlanes){
            if(p.getFlag().equals("a"))
                //System.out.println("Plane: " + p.getPlaneNumber() + " fuel: " + p.getFuelAmount());
                ctr++;
        }
        System.out.println(ctr);

        ctr = 0;
        System.out.println("Crashed Planes:");
        for (Plane p : crashedPlanes)
            //System.out.println("Plane " + p.getPlaneNumber() + " fuel: " + p.getFuelAmount());
            ctr++;
        System.out.println(ctr);
    }

    //Getting inputs from user
    public static int[] getSimulationInputs(){
        Scanner scan = new Scanner(System.in);
        int arrivalProbConst, departureProbConst, landingTime, departingTime, totalTime;
        int[] inputs = new int[5];

        System.out.println("Enter percent chance of plane arriving (0-100): ");
        arrivalProbConst = scan.nextInt();
        while(arrivalProbConst > 100 || arrivalProbConst < 0){
            System.out.println("Invalid value");
            System.out.println("Enter percent chance of plane arriving (0-100): ");
            arrivalProbConst = scan.nextInt();
        }
        inputs[0] = arrivalProbConst;

        System.out.println("Enter percent chance of plane departing (0-100): ");
        departureProbConst = scan.nextInt();
        while(departureProbConst > 100 || departureProbConst < 1){
            System.out.println("Invalid value");
            System.out.println("Enter percent chance of plane arriving (0-100): ");
            departureProbConst = scan.nextInt();
        }
        inputs[1] = departureProbConst;

        System.out.println("Enter time for plane to arrive (2-5): ");
        landingTime = scan.nextInt();
        while(landingTime > 5 || landingTime < 2){
            System.out.println("Invalid value");
            System.out.println("Enter time for plane to arrive (2-5): ");
            landingTime = scan.nextInt();
        }
        inputs[2] = landingTime;

        System.out.println("Enter time for plane to depart (2-5): ");
        departingTime = scan.nextInt();
        while(departingTime > 5 || departingTime < 2){
            System.out.println("Invalid value");
            System.out.println("Enter time for plane to depart (2-5): ");
            departingTime = scan.nextInt();
        }
        inputs[3] = departingTime;

        System.out.println("Enter total simulation time:");
        totalTime = scan.nextInt();
        while (totalTime < 0){
            System.out.println("Invalid value:");
            System.out.println("Enter total simulation time: ");
            totalTime = scan.nextInt();
        }
        inputs[4] = totalTime;
        return inputs;
    }

    //Clear the arrival and departure queues so nothing is left over
    public static void clearQueues(Deque<Plane> arrivalQueue, Deque<Plane> departureQueue, Deque<Plane> processedPlanes,
                                   Deque<Plane> crashedPlanes){
        while(!(departureQueue.isEmpty()) || !(arrivalQueue.isEmpty())){

            for (Plane x : arrivalQueue) {
                x.fuelLoss();
                x.timeLoss();
            }

            // Decrement time and fuel for planes in departure queue
            for (Plane x : departureQueue) {
                x.timeLoss();
                x.fuelLoss();
            }

            if (!departureQueue.isEmpty()) {
                Plane x = departureQueue.peek();
                if (x.getTime() <= 0) { //Checks the time of plane to see if it is ready according to user
                    departureQueue.remove();
                    processedPlanes.add(x);
                }
            }
            else if (!arrivalQueue.isEmpty()) {
                Plane x = arrivalQueue.peek();
                if (x.getTime() <= 0) {
                    arrivalQueue.remove();
                    if (x.getFuelAmount() <= 0)
                        crashedPlanes.add(x);
                    else
                        processedPlanes.add(x);
                }
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();

        Deque<Plane> arrivalQueue = new LinkedList<>();
        Deque<Plane> departureQueue = new LinkedList<>();
        Deque<Plane> crashedPlanes = new LinkedList<>();
        Deque<Plane> processedPlanes = new LinkedList<>();

        int[] inputs = getSimulationInputs();

        int arrivalProbConst = inputs[0], departureProbConst = inputs[1], landingTime = inputs[2];
        int departingTime = inputs[3], totalTime = inputs[4]; //inputs from user

        int arrivalProb, departureProb, fuelAmount; //randomly generated numbers

        for(int i = 1; i <= totalTime; i++){
            //Iterator to decrement time and fuel
            for (Plane x : arrivalQueue) {
                x.fuelLoss();
                x.timeLoss();
            }

            //Iterator to decrement time and fuel
            for (Plane x : departureQueue) {
                x.timeLoss();
                x.fuelLoss();
            }

            arrivalProb = rand.nextInt(0, 100); //Probabilities of arriving planes
            departureProb = rand.nextInt(0, 100); //Probabilities of departing planes
            if(arrivalProb >= 0 && arrivalProb <= arrivalProbConst){ //If num falls in these numbers, plane is arriving
                fuelAmount = rand.nextInt(5, 10);
                Plane x = new Plane(i, fuelAmount, "a", landingTime);
                arrivalQueue.add(x);
            }

            if(departureProb >= 0 && departureProb <= departureProbConst){//If num falls in these numbers, plane is departing
                fuelAmount = rand.nextInt(5, 10);
                Plane x = new Plane(i, fuelAmount, "d", departingTime);
                departureQueue.add(x);
            }
            if (!departureQueue.isEmpty()) {
                Plane x = departureQueue.peek();
                if (x.getTime() <= 0) { //Checks the time of plane to see if it is ready according to user
                    departureQueue.remove();
                    processedPlanes.add(x);
                }
            }
            else if (!arrivalQueue.isEmpty()) {
                Plane x = arrivalQueue.peek();
                if (x.getTime() <= 0) {
                    arrivalQueue.remove();
                    if (x.getFuelAmount() <= 0)
                        crashedPlanes.add(x);
                    else
                        processedPlanes.add(x);
                }
            }
        }
        clearQueues(arrivalQueue, departureQueue, processedPlanes, crashedPlanes);
        System.out.println("__________________________________________________");
        printQueues(processedPlanes, crashedPlanes);
    }
}