import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordChessImp implements WordChess
{
    private int binarySearch(String dict[],String word)
    {
        int low = 0;
        int high = dict.length-1;
        
        
        do
        {
            int mid = (low+high)/2;
            if(word.compareTo(dict[mid]) > 0)
                low = mid+1;
            else if(word.compareTo(dict[mid]) < 0)
                high = mid-1;
            else
                return 1;
        }while(low <= high);
        
        return 0;
    }
    
    
    public String[] findPath(String[] dictionary, String startWord, String endWord)
    {
        boolean find=false;
        Queue<String> tree = new LinkedList<String>();
        Collection<String> visit = new LinkedList<String>();
        Map<String,String> pair = new HashMap<String,String>();
        List<String> words = new ArrayList<String>();
        
        tree.add(startWord);
        visit.add(startWord);
        
        while(!tree.isEmpty()&&find==false) {
            
            String a = tree.poll();
            //char[] start = a.toCharArray();
            
            for(int i=0;i<startWord.length();i++) {
                for(char j='A';j<='Z';j++) {
                    StringBuilder temporary = new StringBuilder(a);
                    temporary.setCharAt(i, j);
                    String node = temporary.toString();
                    //start[i]=j;
                    //String node = start.toString();
                    
                    if(node.equals(endWord)) {
                        find=true;
                    }
                    
                    if(visit.contains(node)) {
                        continue;
                    };
                    
                    if(binarySearch(dictionary,node)==1) {
                        tree.add(node);
                        visit.add(node);
                        pair.put(node,a);
                    }
                }
            }
        }
        
        if(!find) {
            return null;
        }
        
        String cur = endWord;
        
        do{
            words.add(cur);
            cur = pair.get(cur);
        }while(!cur.equals(startWord));
        
        words.add(cur);
        String string[] = new String[words.size()];
        words.toArray(string);
        return string;
    }
}
