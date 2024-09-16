package src;

import java.util.*;

public class SnakeAndLadders {
    Player player;
    Random random = new Random();
    int diceRoll;
    List<Integer> justToShowSnakes =new ArrayList<>();
    List<Integer>justToShowLadders= new ArrayList<>();
    List<Integer> newLadderLocationStart = new ArrayList<>();
    List<Integer> newLadderLocationEnd = new ArrayList<>();
    List<Integer> newSnakeLocationStart = new ArrayList<>();
    List<Integer> newSnakeLocationEnd = new ArrayList<>();
    List<Integer> defaultShit= new ArrayList<>();
    Scanner s = new Scanner(System.in);
    public SnakeAndLadders(String name)
    {
        player = new Player(name);
    }

    public void startGame()
    {
        System.out.println("\t\t\t\t  SNAKE AND LADDERS\n how many snakes and ladders do you want?");

        int ha = s.nextInt();
        while(ha>48||ha<2)
        {
            if(ha>12)
            {
                System.out.println("Please enter the amount of snakes and ladders below 12!!");
            }
            else
            {
                System.out.println("Please enter the amount of snakes and ladders above 3!!");
            }
            ha = s.nextInt();
        }

        for(int i = 0;i<ha;i++)
        {
            int l1 = random.nextInt(5,80);
            int l2 = random.nextInt(20,100);
            while(l1>l2||defaultShit.contains(l1)||defaultShit.contains(l2))
            {
                l1 = random.nextInt(5,80);
                 l2 = random.nextInt(20,100);
            }
            int s1 = random.nextInt(20,100);
            int s2 = random.nextInt(5,80);
            while(s2>s1||defaultShit.contains(s1)||defaultShit.contains(s2))
            {
                s1 = random.nextInt(20,100);
                s2 = random.nextInt(5,80);
            }
            newLadderLocationStart.add(l1);
            justToShowLadders.add(l1);
            newSnakeLocationStart.add(s1);
            justToShowSnakes.add(s1);
            newLadderLocationEnd.add(l2);
            newSnakeLocationEnd.add(s2);

        }
        justToShowLadders.sort(Comparator.naturalOrder());
        justToShowSnakes.sort(Comparator.naturalOrder());
        System.out.println("\nLOCATIONS OF LADDERS!!");
        System.out.println(justToShowLadders);
        System.out.println("LOCATIONS OF SNAKES!!");
        System.out.println(justToShowSnakes);

        while (player.getTilePlace()<100)
        {
            System.out.println("\n\t\t\tPress 1 to roll the dice!!");
            ladderChecker(player.tilePlace);
            snakeChecker(player.tilePlace);
            int sa = s.nextInt();
            if(sa==1){
            diceRoll = player.diceRoll();
            System.out.println("\n"+player.name+" Rolled a "+diceRoll+" !!");
            player.tilePlace= game(diceRoll);
            if(player.tilePlace>100)
            {

                player.tilePlace-=diceRoll;
                System.out.printf("but it doesn't count as Tile exceeded 100,so %s going back to %d again\n",player.name,player.tilePlace);
            }}
        }
        System.out.println("\n\nGame Over,You WIN!!!");
    }
    public int game(int diceRoll)
    {
        int fakeTile = diceRoll+ player.getTilePlace();

        int newTile = getLocation(fakeTile);

        System.out.printf(switch (normalOrSnakeOrLadder(fakeTile))
        {
            case -1->"%s has been eaten by the SNAKE at tile %d and has been DRAGGED to the tile %d!!! :(\n".formatted( player.name,fakeTile,getLocation(fakeTile));
            case 1 ->"%s has found a LADDER at tile %d and has CLIMBED to the tile %d!!! :D\n".formatted(player.name,fakeTile,getLocation(fakeTile));
            default ->"%s has went from tile %d to %d.\n".formatted(player.name,player.getTilePlace(),fakeTile);
        });
        return newTile;
    }

    public void snakeChecker(int tile)
    {
        for(int i : justToShowSnakes)
        {
            if (i>tile)
            {
                System.out.printf("The nearest snake is at tile %d ! Be Careful.\n",i);
                return;
            }
        }
        System.out.print("There are no more Snakes. Be Happy.\n");
    }
    public void ladderChecker(int tile)
    {
        for(int i : justToShowLadders)
        {
            if(i>tile)
            {
                System.out.printf("The nearest ladder is at tile %d ! Pray to climb it.\n",i);
                return;
            }
        }
        System.out.print("There are no more ladders. Be careful.\n");
    }

    public int normalOrSnakeOrLadder(int fakeTile)
    {
        if(newLadderLocationStart.contains(fakeTile))
        {
            return 1;
        }
        else if(newSnakeLocationStart.contains(fakeTile))
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    public int getLocation(int fakeTile)
    {
        if(newLadderLocationStart.contains(fakeTile))
        {
            int index = newLadderLocationStart.indexOf(fakeTile);
            return newLadderLocationEnd.get(index);
        }
        if(newSnakeLocationStart.contains(fakeTile))
        {
            int index = newSnakeLocationStart.indexOf(fakeTile);
            return newSnakeLocationEnd.get(index);
        }
        return fakeTile;
    }
}
