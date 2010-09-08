import java.util.*;

public class MyBot {
	/*
	 * Build State of the world
	 * take random guesses of next move
	 * evaluate guesses choose best overall
	 * opponent modeling
	 * score = (total growth)*(total planets) + ships/(planets+growth) 
	 */	
    private class State {
      public void State(){
      }
    }


    public static void DoTurn(PlanetWars pw) {
//BEG
      // (1) If we currently have a fleet in flight, just do nothing.
      if (pw.MyFleets().size() >= 1) {
          return;
      }
      // (2) Find my strongest planet.
      Planet source = null;
      double sourceScore = Double.MIN_VALUE;
      for (Planet p : pw.MyPlanets()) {
          double score = (double)p.NumShips();
          if (score > sourceScore) {
            sourceScore = score;
            source = p;
          }
      }
      // (3) Find the weakest enemy or neutral planet.
      Planet dest = null;
      double destScore = Double.MIN_VALUE;
      for (Planet p : pw.NotMyPlanets()) {
          double score = 1.0 / (1 + p.NumShips());
          if (score > destScore) {
          destScore = score;
          dest = p;
          }
      }
      // (4) Send half the ships from my strongest planet to the weakest
      // planet that I do not own.
      if (source != null && dest != null) {
          int numShips = source.NumShips() / 2;
          pw.IssueOrder(source, dest, numShips);
      }
    }

    public static void main(String[] args) {
      String line = "";
      String message = "";
      int c;
      try {
        while ((c = System.in.read()) >= 0) {
          switch (c) {
            case '\n':
               if (line.equals("go")) {
                 PlanetWars pw = new PlanetWars(message);
                 DoTurn(pw);
                 pw.FinishTurn();
                 message = "";
               } else {
                 message += line + "\n";
               }
              line = "";
              break;
            default:
              line += (char)c;
              break;
          }
        }
      } catch (Exception e) {
          // Owned.
      }
    }
}

