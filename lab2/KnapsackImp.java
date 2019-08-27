import java.util.*;
import java.util.Comparator;;

public class KnapsackImp implements Knapsack{
	
	private class item {
		Double ratio;
		int weight;
		int value;
		int index;
		
		@SuppressWarnings("deprecation")
		private item(int wt, int val, int ind){
			this.weight=wt;
			this.value=val;
			this.index=ind;
			this.ratio = (double)val/(double)wt;
		}
	}
	
	@Override
	public int fractionalKnapsack(int[] weights, int[] values, int capacity) {
		// TODO Auto-generated method stub
		
		item[] a = new item[weights.length];
		
		for(int i=0;i<weights.length;i++) {
			a[i] = new item(weights[i],values[i],i);
		}
		
		Arrays.sort(a, new Comparator<item>()  
        { 
            @Override
            public int compare(item o1, item o2)  
            { 
                return o2.ratio.compareTo(o1.ratio) ; 
            } 
        });  //ascending order
		
		double totalValue = 0d;
		int remaincapacity=capacity;
		
		for(item i : a) {
			
			int currentWeight = (int) i.weight; 
            int currentValue = (int) i.value; 
            
			if(i.weight<=remaincapacity) {
				totalValue+=currentValue;
				remaincapacity-=currentWeight;
			}
			else {
				double part = ((double)remaincapacity/(double)currentWeight); 
                totalValue += (currentValue*part); 
                remaincapacity = (int)(remaincapacity - (currentWeight*part)); 
                break; 
			}
		}
		
        return (int)totalValue;
		
	}

	@Override
	public int discreteKnapsack(int[] weights, int[] values, int capacity) {
		// TODO Auto-generated method stub
		
		int table[][] = new int[values.length+1][capacity+1]; //adding 0 at start.
        
        for(int i = 0; i <= weights.length; i++) //account for the added 0.
        {
            for(int j = 0; j <= capacity; j++)
            {
                if(i == 0 || j == 0)
                    table[i][j] =  0;
                else if(weights[i-1] > j) 
                    table[i][j] = table[i-1][j];
                else
                    table[i][j] = Math.max( (table[i-1][j]), (values[i-1] + table[i-1][j-weights[i-1]]));
                
            }
        }
        return table[weights.length][capacity]; 
	}
	
	
}
