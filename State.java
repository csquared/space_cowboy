import java.util.ArrayList;
import java.util.List;


    public class State {
      PlanetWars pw;
      int score=0;
      public int source=-1, dest=-1, num_ships=-1;
      public State(PlanetWars pw){
    	  this.pw = pw;
      }
      public List<State> children(){
    	  List<State> children = new ArrayList<State>();
        for(Planet my_planet : this.pw.MyPlanets()){
          for(Planet enemy_planet : this.pw.EnemyPlanets()){ 
            State this_state = new State(pw);
            this_state.source = my_planet.PlanetID();
            this_state.dest   = enemy_planet.PlanetID();
            this_state.num_ships = my_planet.NumShips()/2;
            children.add(this_state);
          }
        } 
        return children;
      }
      public int score(){
    	  List<Planet> planets = pw.MyPlanets();
    	  int totalGrowth=0, totalPlanets=0, totalShips=0;
    	  for (Planet planet : planets){
    		  totalPlanets++;
    		  totalGrowth += planet.GrowthRate();
    		  totalShips += planet.NumShips();
    	  }
    	  List<Fleet> fleets = pw.MyFleets();
    	  for (Fleet fleet : fleets){
    		  totalShips+=fleet.NumShips();
    	  }
    	  if (source >= 0 && dest >= 0 && num_ships > 0){
    		  totalShips -= num_ships;
    		  if (num_ships > pw.GetPlanet(source).NumShips()){
    			  totalPlanets++;
    			  totalGrowth += pw.GetPlanet(source).GrowthRate();
    			  totalShips += num_ships - pw.GetPlanet(source).NumShips();
    		  }
    	  }
    	  this.score = (totalPlanets*totalGrowth + totalShips)*(totalGrowth);    	  
    	  return this.score;
      }      
    }
