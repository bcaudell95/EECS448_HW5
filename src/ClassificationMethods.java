import java.util.ArrayList;

public class ClassificationMethods {
    private static double activeClassifiedAsActive;
    private static double activeClassifiedAsNonActive;
    private static double nonActiveClassifiedAsActive;
    private static double nonActiveClassifiedAsNonActive;

    private static ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
    private static ArrayList<ArrayList<Double>> activeData = new ArrayList<ArrayList<Double>>();
    private static ArrayList<ArrayList<Double>> nonActiveData = new ArrayList<ArrayList<Double>>();

    private static ArrayList<Double> activeMeanVector;
    private static ArrayList<Double> nonActiveMeanVector;
    private static ArrayList<Double> activeSTD;
    private static ArrayList<Double> nonActiveSTD;

    static {
        getData();
    }

    public static double EuclidianDistanceMethod() {
        resetCounters();

        for(ArrayList<Double> singleData : activeData) {
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow(singleData.get(i) - activeMeanVector.get(i), 2);
                nonActiveDistance += Math.pow(singleData.get(i) - nonActiveMeanVector.get(i), 2);
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

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow(singleData.get(i) - activeMeanVector.get(i), 2);
                nonActiveDistance += Math.pow(singleData.get(i) - nonActiveMeanVector.get(i), 2);
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
        resetCounters();

        for(ArrayList<Double> singleData : activeData) {
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow((singleData.get(i) - activeMeanVector.get(i)) / activeSTD.get(i), 2);
                nonActiveDistance += Math.pow((singleData.get(i) - nonActiveMeanVector.get(i)) / nonActiveSTD.get(i), 2);
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

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow((singleData.get(i) - activeMeanVector.get(i)) / activeSTD.get(i), 2);
                nonActiveDistance += Math.pow((singleData.get(i) - nonActiveMeanVector.get(i)) / nonActiveSTD.get(i), 2);
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
        resetCounters();

        for(ArrayList<Double> singleData : activeData) {
            double activeVote = 0;
            double nonActiveVote = 0;

            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = Math.abs(singleData.get(i) - activeMeanVector.get(i));
                nonActiveDistance = Math.abs(singleData.get(i) - nonActiveMeanVector.get(i));

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

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = Math.abs(singleData.get(i) - activeMeanVector.get(i));
                nonActiveDistance = Math.abs(singleData.get(i) - nonActiveMeanVector.get(i));

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                nonActiveClassifiedAsActive++;
            } else {
                nonActiveClassifiedAsNonActive++;
            }
        }

        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    public static double MahalanokiVotingMethod() {
        resetCounters();

        for(ArrayList<Double> singleData : activeData) {
            double activeVote = 0;
            double nonActiveVote = 0;

            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = (Math.abs(singleData.get(i) - activeMeanVector.get(i))) / activeSTD.get(i);
                nonActiveDistance = (Math.abs(singleData.get(i) - nonActiveMeanVector.get(i))) / nonActiveSTD.get(i);

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

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = (Math.abs(singleData.get(i) - activeMeanVector.get(i))) / activeSTD.get(i);
                nonActiveDistance = (Math.abs(singleData.get(i) - nonActiveMeanVector.get(i))) / nonActiveSTD.get(i);

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                nonActiveClassifiedAsActive++;
            } else {
                nonActiveClassifiedAsNonActive++;
            }
        }

        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    private static int overallActiveVote = 0;
    private static int overallNonActiveVote = 0;

    //runs each element in each row through each of the previous four methods and tallies up which votes for active/nonactive
    //then determines based upon whichever receives more votes
    public static double CustomMethod() {
        resetCounters();

        for(ArrayList<Double> singleData : activeData) {
            resetOverallVotes();

            //-----------
            //  Distances
            //-----------

            //Euclidian distance
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow(singleData.get(i) - activeMeanVector.get(i), 2);
                nonActiveDistance += Math.pow(singleData.get(i) - nonActiveMeanVector.get(i), 2);
            }

            if(activeDistance < nonActiveDistance) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //Mahalanoki distance
            activeDistance = 0;
            nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow((singleData.get(i) - activeMeanVector.get(i)) / activeSTD.get(i), 2);
                nonActiveDistance += Math.pow((singleData.get(i) - nonActiveMeanVector.get(i)) / nonActiveSTD.get(i), 2);
            }

            if(activeDistance < nonActiveDistance) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //-------
            // Voting
            //-------

            //Euclidian voting
            double activeVote = 0;
            double nonActiveVote = 0;

            activeDistance = 0;
            nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = Math.abs(singleData.get(i) - activeMeanVector.get(i));
                nonActiveDistance = Math.abs(singleData.get(i) - nonActiveMeanVector.get(i));

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //Mahalanoki Voting
            activeVote = 0;
            nonActiveVote = 0;

            activeDistance = 0;
            nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = (Math.abs(singleData.get(i) - activeMeanVector.get(i))) / activeSTD.get(i);
                nonActiveDistance = (Math.abs(singleData.get(i) - nonActiveMeanVector.get(i))) / nonActiveSTD.get(i);

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //check to see how many methods resulted in active vs nonactive
            if(overallActiveVote > overallNonActiveVote) {
                activeClassifiedAsActive++;
            } else {
                activeClassifiedAsNonActive++;
            }
        }

        for(ArrayList<Double> singleData : nonActiveData) {
            resetOverallVotes();

            //-----------
            //  Distances
            //-----------

            //Euclidian distance
            double activeDistance = 0;
            double nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow(singleData.get(i) - activeMeanVector.get(i), 2);
                nonActiveDistance += Math.pow(singleData.get(i) - nonActiveMeanVector.get(i), 2);
            }

            if(activeDistance < nonActiveDistance) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //Mahalanoki distance
            activeDistance = 0;
            nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance += Math.pow((singleData.get(i) - activeMeanVector.get(i)) / activeSTD.get(i), 2);
                nonActiveDistance += Math.pow((singleData.get(i) - nonActiveMeanVector.get(i)) / nonActiveSTD.get(i), 2);
            }

            if(activeDistance < nonActiveDistance) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //-------
            // Voting
            //-------

            //Euclidian voting
            double activeVote = 0;
            double nonActiveVote = 0;

            activeDistance = 0;
            nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = Math.abs(singleData.get(i) - activeMeanVector.get(i));
                nonActiveDistance = Math.abs(singleData.get(i) - nonActiveMeanVector.get(i));

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //Mahalanoki Voting
            activeVote = 0;
            nonActiveVote = 0;

            activeDistance = 0;
            nonActiveDistance = 0;

            for(int i = 0; i < singleData.size(); i++) {
                activeDistance = (Math.abs(singleData.get(i) - activeMeanVector.get(i))) / activeSTD.get(i);
                nonActiveDistance = (Math.abs(singleData.get(i) - nonActiveMeanVector.get(i))) / nonActiveSTD.get(i);

                if(activeDistance < nonActiveDistance) {
                    activeVote++;
                } else {
                    nonActiveVote++;
                }
            }

            if(activeVote < nonActiveVote) {
                overallActiveVote++;
            } else {
                overallNonActiveVote++;
            }

            //check to see how many methods resulted in active vs nonactive
            if(overallActiveVote > overallNonActiveVote) {
                nonActiveClassifiedAsActive++;
            } else {
                nonActiveClassifiedAsNonActive++;
            }
        }

        return (activeClassifiedAsActive / nonActiveClassifiedAsActive);
    }

    private static void resetOverallVotes() {
        overallActiveVote = 0;
        overallNonActiveVote = 0;
    }

    //retrieve data from the data retrieval layer (txt files)
    private static void getData() {
        data = DataRetrievalLayer.GetData();

        activeData = new ArrayList<ArrayList<Double>>(data.subList(0, 1347));
        nonActiveData = new ArrayList<ArrayList<Double>>(data.subList(1347, DataRetrievalLayer.dataLength));

        activeMeanVector = DataRetrievalLayer.GetData().get(0);
        nonActiveMeanVector = DataRetrievalLayer.GetData().get(1);
        activeSTD = DataRetrievalLayer.GetData().get(2);
        nonActiveSTD = DataRetrievalLayer.GetData().get(3);
    }

    private static void resetCounters() {
        activeClassifiedAsActive = 0;
        activeClassifiedAsNonActive = 0;
        nonActiveClassifiedAsActive = 0;
        nonActiveClassifiedAsNonActive = 0;
    }
}
