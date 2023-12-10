package sample;

import java.util.Arrays;

public class IslandsWithDSU
{
    private static final String[] matrix=new String[] {
        /*"  #############  ###   ",
        "  #      #####   ##    ",
        "  #  ##  ##   #   #    ",
        "    ###      ##   #  # ",
        "  #   #########  ## ## ",
        "          ##       ##  ",
        "          ##########   ",*/
        
        "  #############  ###   ",
        "  #      #####   ##    ",
        "  #  ##  ##   #   #    ",
        "    ###      ##   #  # ",
        "  #   #########  ## ## ",
        "          ##       ##  ",
        "          ##########   ",
        
        /*"1 1 0 0 0",
        {"0 1 0 0 1"},
        {"1 0 0 1 1"},
        {"0 0 0 0 0"},
        {"1 0 1 0 1"}*/
    };

    // find with path compression.
    // If sets[s] < 0 then it is a link to ~sets[s].  Otherwise it is size of set
    static int find(int[] sets, int s)
    {
        int parent = ~sets[s];
        if (parent>=0)
        {
            int root = find(sets, parent);
            if (root != parent)
            {
                sets[s] = ~root;
            }
            return root;
        }
        return s;
    }

    // union-by-size
    // If sets[s] < 0 then it is a link to ~sets[s].  Otherwise it is size of set
    static boolean union(int[] sets, int x, int y)
    {
        x = find(sets,x);
        y = find(sets,y);
        if (x!=y)
        {
            if ((sets[x] < sets[y]))
            {
                sets[y] += sets[x];
                sets[x] = ~y;
            }
            else
            {
                sets[x] += sets[y];
                sets[y] = ~x;
            }
            return true;
        }
        return false;
    }

    // Count islands in matrix

    public static void main(String[] args)
    {
        // two rows of union-find sets.
        // top row is at even indexes, bottom row is at odd indexes.  This arrangemnt is chosen just
        // to make resizing this array easier.
        // For each value x:
        // x==0 => no set. x>0 => root set of size x. x<0 => link to ~x
        int cols=4;
        int[] setrows= new int[cols*2];

        int islandCount = 0;

        for (String s : matrix)
        {
            System.out.println(s);
            //Make sure our rows are big enough
            if (s.length() > cols)
            {
                cols=s.length();
                if (setrows.length < cols*2)
                {
                    int newlen = Math.max(cols,setrows.length)*2;
                    setrows = Arrays.copyOf(setrows, newlen);
                }
            }
            //Create sets for land in bottom row, merging left
            for (int col=0; col<s.length(); ++col)
            {
                if (!Character.isWhitespace(s.charAt(col)))
                {
                    int idx = col*2+1;
                    setrows[idx]=1; //set of size 1
                    if (idx>=2 && setrows[idx-2]!=0)
                    {
                        union(setrows, idx, idx-2);
                    }
                }
            }
            //merge up
            for (int col=0; col<cols; ++col)
            {
                int topidx = col*2;
                int botidx = topidx+1;
                if (setrows[topidx]!=0 && setrows[botidx]!=0)
                {
                    int toproot=find(setrows,topidx);
                    if ((toproot&1)!=0)
                    {
                        //top set is already linked down
                        union(setrows, toproot, botidx);
                    }
                    else
                    {
                        //link top root down.  It does not matter that we aren't counting its size, since
                        //we will shortly throw it aaway
                        setrows[toproot] = ~botidx;
                    }
                }
            }
            //count root sets, discard top row, and move bottom row up while fixing links
            for (int col=0; col<cols; ++col)
            {
                int topidx = col * 2;
                int botidx = topidx + 1;
                if (setrows[topidx]>0)
                {
                    ++islandCount;
                }
                int v = setrows[botidx];
                setrows[topidx] = (v>=0 ? v : v|1); //fix up link if necessary
                setrows[botidx] = 0;
            }
        }
        
        System.out.println(Arrays.toString(setrows));

        //count remaining root sets in top row
        for (int col=0; col<cols; ++col)
        {
            if (setrows[col*2]>0)
            {
                ++islandCount;
            }
        }

        System.out.println("\nThere are "+islandCount+" islands there");
    }

}
