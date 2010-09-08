import java.util.List;


    public class State {
      PlanetWars pw;
      int score=0;
      public int source=-1, dest=-1, num_ships=-1;
      public State(PlanetWars pw){
    	  this.pw = pw;
      }
      public List<State> children(){
    	  List<State> children;
        for(Planet my_planet : this.pw.MyPlanets()){
          for(Plan enemy_planet : this.pw.EnemyPlanet()){ 
            State this_state = new State(pw);
            this_state.source = my_planet;
            this_state.dest   = enemy_planet;
            this_state.num_ships = my_planet.NumShips()/2;
            children.add(this_state)
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
    	  this.score = (totalPlanets*totalGrowth + totalShips)*(totalGrowth);    	  
    	  return this.score;
      }      
    }
