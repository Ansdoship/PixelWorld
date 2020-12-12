package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.math.Vector2;

public class Position {

    public int x;
    public int y;

    public Position() {
    }

    public Position( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Position( Position p ) {
        this.x = p.x;
        this.y = p.y;
    }

    public Position set( int x, int y ) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Position set( Position p ) {
        x = p.x;
        y = p.y;
        return this;
    }

    public Position clone() {
        return new Position( this );
    }

    public Position scale( float f ) {
        this.x *= f;
        this.y *= f;
        return this;
    }

    public Position offset( int dx, int dy ) {
        x += dx;
        y += dy;
        return this;
    }

    public Position offset( Position d ) {
        x += d.x;
        y += d.y;
        return this;
    }

    @Override
    public boolean equals( Object obj ) {
        if (obj instanceof Position) {
            Position p = (Position)obj;
            return p.x == x && p.y == y;
        } else {
            return false;
        }
    }
}

