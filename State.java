import java.util.List;


    public class State {
      PlanetWars pw;
      int score=0;
      public int source=-1, dest=-1, num_ships=-1;
      public State(PlanetWars pw){
    	  this.pw = pw;
      }
      public List<State> children(){
    	  return null;
      }
      public int score(){
    	  List<Planet> planets = pw.MyPlanets();
    	  int totalGrowth=0, totalPlanets=0, totalShips=0;
    	  for (Planet planet : planets){
    		  totalPlanets++;
    		  totalGrowth += planet.GrowthRate();
    		  totalShips += planet.NumShips();
    	  }
    	  return score;
      }      
    }
