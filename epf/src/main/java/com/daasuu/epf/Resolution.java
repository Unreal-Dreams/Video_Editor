package com.daasuu.epf;

import java.io.Serializable;

/**
 * Created by sudamasayuki on 2017/05/16.
 */

public class Resolution implements Serializable {
    private final int width;
    private final int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resolution that = (Resolution) o;

        if (height != that.height) return false;
        if (width != that.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }
}
