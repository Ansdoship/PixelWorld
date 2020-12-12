package com.ansdoship.pixelworld.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PositionF extends Vector2 {

    public static float distance(PositionF a, PositionF b) {
        return (float) PositionF.dst(a.x, a.y, b.x, b.y);
	}

    public static float distance(Vector2 a, Vector2 b) {
        return (float) PositionF.dst(a.x, a.y, b.x, b.y);
	}

    public static float distance(Vector3 a, Vector3 b) {
        return (float) PositionF.dst(a.x, a.y, b.x, b.y);
	}

    public static float angle(PositionF start, PositionF end) {
        return (float)Math.atan2(end.y - start.y, end.x - start.x);
	}

    public static float angle(Vector2 start, Vector2 end) {
        return (float)Math.atan2(end.y - start.y, end.x - start.x);
	}

    public static float angle(Vector3 start, Vector3 end) {
        return (float)Math.atan2(end.y - start.y, end.x - start.x);
	}

}
