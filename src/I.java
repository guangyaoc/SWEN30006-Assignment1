// I.java
package src;
import ch.aplu.jgamegrid.*;

import java.util.ArrayList;
import java.util.Arrays;

class I extends fatherOfBlocks {
  private final int blockId = 0;
  private final String blockName = "I";
  private Location[][] r = new Location[4][4];

  protected Tetris tetris;
  private int nb;
  private Actor nextTetrisBlock = null;

  I(Tetris tetris)
  {
    super();
    this.tetris = tetris;
    // rotId 0
    r[0][0] = new Location(new Location(-1, 0));
    r[1][0] = new Location(new Location(0, 0));
    r[2][0] = new Location(new Location(1, 0));
    r[3][0] = new Location(new Location(2, 0));
    // rotId 1
    r[0][1] = new Location(new Location(0, -1));
    r[1][1] = new Location(new Location(0, 0));
    r[2][1] = new Location(new Location(0, 1));
    r[3][1] = new Location(new Location(0, 2));
    // rotId 2
    r[0][2] = new Location(new Location(-1, 0));
    r[1][2] = new Location(new Location(0, 0));
    r[2][2] = new Location(new Location(1, 0));
    r[3][2] = new Location(new Location(2, 0));
    // rotId 3
    r[0][3] = new Location(new Location(0, -1));
    r[1][3] = new Location(new Location(0, 0));
    r[2][3] = new Location(new Location(0, 1));
    r[3][3] = new Location(new Location(0, 2));

    for (int i = 0; i < r.length; i++)
      blocks.add(new TetroBlock(blockId, r[i]));
  }
//toString 部分也不要动
  public String toString() {
    return "For testing, do not change: Block: " + blockName + ". Location: " + blocks + ". Rotation: " + rotId;
  }

  // The game is called in a run loop, this method for a block is called every 1/30 seconds as the starting point
  public void act()
  {
    if (isStarting) {
      for (TetroBlock a : blocks) {
        Location loc =
                new Location(getX() + a.getRelLoc(0).x, getY() + a.getRelLoc(0).y);
        gameGrid.addActor(a, loc);
      }
      isStarting = false;
      nb = 0;
    } else if (nb >= blocks.size() && canAutoPlay()) {
      autoMove();
    } else
    {
      setDirection(90);
      if (nb == 1)
        nextTetrisBlock = tetris.createRandomTetrisBlock();
      if (!advance())
      {
        if (nb == 0)  // Game is over when tetrisBlock cannot fall down
          tetris.gameOver();
        else
        {
          setActEnabled(false);
          gameGrid.addActor(nextTetrisBlock, new Location(6, 0));
          tetris.setCurrentTetrisBlock(nextTetrisBlock);
        }
      }
      nb++;
    }
  }




}
