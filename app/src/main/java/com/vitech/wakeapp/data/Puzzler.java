package com.vitech.wakeapp.data;

/**
 * Created by Home on 20-06-2016.
 */
public class Puzzler {
    public String[] intPuzzle = {"1","2","3","4","5","6","7","8","9"};
    public String[] charPuzzle = {"a","b","c","d","e","f","g","h","i"};
    public int[] index = {0,1,2,3,4,5,6,7,8};
    public int ord;
    public String[] shuffle(int r){
        String[] p = new String[9];
        switch (r){
            case 0:p = intPuzzle;break;
            case 1:p = charPuzzle;break;
        }
        int n  = p.length;

        for (int i = 0; i < n; i++) {
            int random = (int)(Math.random()*(n-i));
            int it = index[random];
            index[random] = index[i];
            index [i] = it;
            String temp = p[random];
            p[random]= p[i];
            p[i]=temp;

        }

        ord = 0;
        return p;
    }

    public boolean checkord(int cid){

        if(index[cid]==ord){
            ord++;
            return true;
        }
        return false;
    }










}


