package com.nullprogram.dp40d;

import lombok.Data;

@Data
public final class Pair implements Comparable<Pair> {

    public static final Pair INFINITY = new Pair(Float.POSITIVE_INFINITY);

    private final Point a;
    private final Point b;
    private final float length;

    public Pair(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.length = a.dist(b);
    }

    private Pair(float length) {
        this.a = null;
        this.b = null;
        this.length = length;
    }

    @Override
    public int compareTo(Pair p) {
        return (int) Math.signum(length - p.length);
    }
}
