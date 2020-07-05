package mooc.vandy.java4android.birthdayprob.logic;

import java.sql.Array;

import mooc.vandy.java4android.birthdayprob.ui.OutputInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early Android interactions.
 * Designing the assignments this way allows you to first learn key 'Java' features without
 * having to beforehand learn the complexities of Android.
 *
 */
public class Logic
        implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG =
            Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface
     * [MainActivity.java].
     * <p>
     * It is called 'mOut' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'mOut' is good enough).
     */
    OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance
     * (which implements [OutputInterface]) to 'out'
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labelled 'Process...' is pressed.
     */
    public void process() {
        int groupSize = mOut.getSize();
        int simulationCount = mOut.getCount();

        if (groupSize < 2 || groupSize > 365) {
            mOut.makeAlertToast("Group Size must be in the range 2-365.");
            return;
        }
        if (simulationCount <= 0) {
            mOut.makeAlertToast("Simulation Count must be positive.");
            return;
        }

        double percent = calculate(groupSize, simulationCount);

        // report results
        mOut.println("For a group of " + groupSize + " people, the percentage");
        mOut.println("of times that two people share the same birthday is");
        mOut.println(String.format("%.2f%% of the time.", percent));

    }

    /**
     * This is the method that actually does the calculations.
     * <p>
     * We provide you this method that way we can test it with unit testing.
     */
    public double calculate(int size, int count) {

        // TODO -- add your code here
        int counterofoccurency=0;
        int seed;
        int i;
        for(seed=1; seed<count+1;seed++)
        {
            if(isthereoccuring(birthdayarray(seed,size))==1)
            {
                counterofoccurency += 1;
            }
        }

        return counterofoccurency*100.0/count;





    }

    // TODO - add your code here
    // List<String> aList = new ArrayList<String>();


    //the problem is perhaps that the name of the function is the name use
    public static List<Integer> birthdayarray(int seed, int size)
    {

        Random rnd = new Random();
        rnd.setSeed(seed);
        //np.random.seed(seed);
        List<Integer> birthdayarray = new ArrayList<Integer>();
        for(int i=1; i<=size;i++ )
        {
            birthdayarray.add(rnd.nextInt(364));
        }
        return  birthdayarray;
    }

    public static int isthereoccuring(List<Integer> listofbirthday)//return a boolean wether there is ocuring birthday or not
    {

        while(listofbirthday.size() >0)
        {
            int firstbirthday=listofbirthday.get(0);
            listofbirthday.remove(0);//Use foreach in Java
            for(  int i : listofbirthday)
            {
                if (firstbirthday - i == 0)
                {
                    return 1;
                }
            }

        }
        return 0;
    }

}
