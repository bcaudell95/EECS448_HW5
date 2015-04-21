import java.util.ArrayList;
import java.util.Random;

public class ClassificationMethods {
    private static double activeClassifiedAsActive;
    private static double activeClassifiedAsNonActive;
    private static double nonActiveClassifiedAsActive;
    private static double nonActiveClassifiedAsNonActive;

    private static ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
    private static ArrayList<ArrayList<Double>> activeData = new ArrayList<ArrayList<Double>>();
    private static ArrayList<ArrayList<Double>> nonActiveData = new ArrayList<ArrayList<Double>>();

    private static double activeMeanVector;
    private static double nonActiveMeanVector;
    private static double activeSTD;
    private static double nonActiveSTD;

    public static double EuclidianDistanceMethod() {
        getData();

        for(ArrayList<Double> singleData : activeData) {
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance += Math.pow(cell - activeMeanVector, 2);
                nonActiveDistance += Math.pow(cell - nonActiveMeanVector, 2);
            }

            if(activeDistance < nonActiveDistance) {
                activeClassifiedAsActive++;
            } else {
                activeClassifiedAsNonActive++;
            }
        }

        for(ArrayList<Double> singleData : nonActiveData) {
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance += Math.pow(cell - activeMeanVector, 2);
                nonActiveDistance += Math.pow(cell - nonActiveMeanVector, 2);
            }

            if(activeDistance < nonActiveDistance) {
                nonActiveClassifiedAsActive++;
            } else {
                nonActiveClassifiedAsNonActive++;
            }
        }

        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    public static double MahalanokiDistanceMethod() {
        getData();

        for(ArrayList<Double> singleData : activeData) {
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance += Math.pow(cell - activeMeanVector, 2);
                nonActiveDistance += Math.pow(cell - nonActiveMeanVector, 2);
            }

            if(activeDistance < nonActiveDistance) {
                activeClassifiedAsActive++;
            } else {
                activeClassifiedAsNonActive++;
            }
        }

        for(ArrayList<Double> singleData : nonActiveData) {
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance += Math.pow((cell - activeMeanVector) / activeSTD, 2);
                nonActiveDistance += Math.pow((cell - nonActiveMeanVector) / nonActiveSTD, 2);
            }

            if(activeDistance < nonActiveDistance) {
                nonActiveClassifiedAsActive++;
            } else {
                nonActiveClassifiedAsNonActive++;
            }
        }

        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    public static double EuclidianVotingMethod() {
        getData();

        for(ArrayList<Double> singleData : activeData) {
            double activeVote = 0;
            double nonActiveVote = 0;

            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance = Math.abs(cell - activeMeanVector);
                nonActiveDistance = Math.abs(cell - nonActiveMeanVector);

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                activeClassifiedAsActive++;
            } else {
                activeClassifiedAsNonActive++;
            }
        }

        for(ArrayList<Double> singleData : nonActiveData) {
            double activeVote = 0;
            double nonActiveVote = 0;

            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance = Math.abs(cell - activeMeanVector);
                nonActiveDistance = Math.abs(cell - nonActiveMeanVector);

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                activeClassifiedAsActive++;
            } else {
                activeClassifiedAsNonActive++;
            }
        }

        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    public static double MahalanokiVotingMethod() {
        getData();

        for(ArrayList<Double> singleData : activeData) {
            double activeVote = 0;
            double nonActiveVote = 0;

            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance = (Math.abs(cell - activeMeanVector)) / activeSTD;
                nonActiveDistance = (Math.abs(cell - nonActiveMeanVector)) / nonActiveSTD;

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                activeClassifiedAsActive++;
            } else {
                activeClassifiedAsNonActive++;
            }
        }

        for(ArrayList<Double> singleData : nonActiveData) {
            double activeVote = 0;
            double nonActiveVote = 0;

            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(Double cell : singleData) {
                activeDistance = (Math.abs(cell - activeMeanVector)) / activeSTD;
                nonActiveDistance = (Math.abs(cell - nonActiveMeanVector)) / nonActiveSTD;

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                activeClassifiedAsActive++;
            } else {
                activeClassifiedAsNonActive++;
            }
        }

        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    public static double CustomMethod() {
        getData();



        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    //retrieve data from the data retrieval layer (txt files)
    private static void getData() {
        data = DataRetrievalLayer.GetData();

        activeData = new ArrayList<ArrayList<Double>>(data.subList(0, 1347));
        nonActiveData = new ArrayList<ArrayList<Double>>(data.subList(1347, DataRetrievalLayer.dataLength));

        activeMeanVector = DataRetrievalLayer.GetActiveMeanVector();
        nonActiveMeanVector = DataRetrievalLayer.GetNonActiveMeanVector();
        activeSTD = DataRetrievalLayer.GetActiveSTD();
        nonActiveSTD = DataRetrievalLayer.GetNonActiveSTD();
    }
}
