import java.util.*;
class Solution {
    static HashMap<String,Integer> giftList;
    static HashMap<String,Integer> giftS;
    static int[] willTakeGift;
    public int solution(String[] friends, String[] gifts) {
        
        int answer = 0;
        giftList = new HashMap<>();
        giftS = new HashMap<>();
        willTakeGift = new int[friends.length];
        
        for(int i=0;i<friends.length;i++)
        {
            giftS.put(friends[i],0);
        }
        for(int i=0;i<gifts.length;i++)
        {
            String[] gifters = gifts[i].split(" ");
            
            giftS.put(gifters[0],giftS.get(gifters[0])+1);
            giftS.put(gifters[1],giftS.get(gifters[1])-1);
            if(giftList.containsKey(gifts[i]))
                giftList.put(gifts[i],giftList.get(gifts[i])+1);
            else
                giftList.put(gifts[i],1);
        }
        
        combination(0,2,friends,new boolean[friends.length]);
        
        for(int i=0;i<willTakeGift.length;i++){
            
            answer = Math.max(willTakeGift[i],answer);
        }
        return answer;
    }
    
    public static void combination(int cnt, int r,String[] friends,boolean[] visited){
        if(r==0){
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<visited.length;i++){
                if(visited[i])
                    list.add(i);
            }
            checkWho(list.get(0),list.get(1),friends);
            
            return;
        }
        if(friends.length<=cnt){
            return;
        }
        visited[cnt]=true;
        combination(cnt+1,r-1,friends,visited);
        visited[cnt]=false;
        combination(cnt+1,r,friends,visited);
        
        
    }
    
    public static void checkWho(int a, int b, String[] friends)
    {
        if(giftList.containsKey(friends[a] + " " + friends[b])||giftList.containsKey(friends[b] + " " + friends[a])){
            int giftScore = 0;
            //a가 b에게 준 경우
            if(giftList.containsKey(friends[a] + " " + friends[b]))
               {
                    giftScore+=giftList.get(friends[a] + " " + friends[b]);
                }
            //b가 a에게 준 경우
            if(giftList.containsKey(friends[b] + " " + friends[a]))
               {
                   giftScore-=giftList.get(friends[b] + " " + friends[a]);
               }
            if(giftScore>0)
                {
                    willTakeGift[a]+=1;
                }
            else if(giftScore<0)
               {
                    willTakeGift[b]+=1;
               }
            else{
                    if(giftS.get(friends[a]) > giftS.get(friends[b]))
                    {
                        willTakeGift[a]+=1;
                    }else if(giftS.get(friends[b]) > giftS.get(friends[a]))
                    {
                        willTakeGift[b]+=1;
                    }
                }

        }else
        {
            if(giftS.get(friends[a]) > giftS.get(friends[b]))
            {
                willTakeGift[a]+=1;
            }else if(giftS.get(friends[b]) > giftS.get(friends[a]))
            {
                willTakeGift[b]+=1;
            }

        }
        
    }
}