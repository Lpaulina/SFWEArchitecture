package edu.baylor.cs.csi3471;

import java.util.Objects;


public class Pair<L ,R>{
    private final L left;
    private final R right;

    public Pair(L left, R right){
        this.left = left;
        this.right = right;
    }

    public L getLeft(){
        return left;
    }

    public R getRight(){
        return right;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pair<?,?> other = (Pair<?,?>) obj;
        return Objects.equals(left, other.left) && Objects.equals(right, other.right);
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, right);
    }

    
}
