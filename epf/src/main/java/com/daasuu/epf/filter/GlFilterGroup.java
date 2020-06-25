package com.daasuu.epf.filter;

import android.opengl.GLES20;
import android.util.Pair;

import com.daasuu.epf.EFramebufferObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FRAMEBUFFER;

/**
 * Created by sudamasayuki on 2017/05/16.
 */

public class GlFilterGroup extends GlFilter {

    private final Collection<GlFilter> filters;

    private final ArrayList<Pair<GlFilter, EFramebufferObject>> list = new ArrayList<Pair<GlFilter, EFramebufferObject>>();

    public GlFilterGroup(final GlFilter... glFilters) {
        this(Arrays.asList(glFilters));
    }

    public GlFilterGroup(final Collection<GlFilter> glFilters) {
        filters = glFilters;
    }

    @Override
    public void setup() {
        super.setup();

        if (filters != null) {
            final int max = filters.size();
            int count = 0;

            for (final GlFilter shader : filters) {
                shader.setup();
                final EFramebufferObject fbo;
                if ((count + 1) < max) {
                    fbo = new EFramebufferObject();
                } else {
                    fbo = null;
                }
                list.add(Pair.create(shader, fbo));
                count++;
            }
        }
    }

    @Override
    public void release() {
        for (final Pair<GlFilter, EFramebufferObject> pair : list) {
            if (pair.first != null) {
                pair.first.release();
            }
            if (pair.second != null) {
                pair.second.release();
            }
        }
        list.clear();
        super.release();
    }

    @Override
    public void setFrameSize(final int width, final int height) {
        super.setFrameSize(width, height);

        for (final Pair<GlFilter, EFramebufferObject> pair : list) {
            if (pair.first != null) {
                pair.first.setFrameSize(width, height);
            }
            if (pair.second != null) {
                pair.second.setup(width, height);
            }
        }
    }

    private int prevTexName;

    @Override
    public void draw(final int texName, final EFramebufferObject fbo) {
        prevTexName = texName;
        for (final Pair<GlFilter, EFramebufferObject> pair : list) {
            if (pair.second != null) {
                if (pair.first != null) {
                    pair.second.enable();
                    GLES20.glClear(GL_COLOR_BUFFER_BIT);

                    pair.first.draw(prevTexName, pair.second);
                }
                prevTexName = pair.second.getTexName();

            } else {
                if (fbo != null) {
                    fbo.enable();
                } else {
                    GLES20.glBindFramebuffer(GL_FRAMEBUFFER, 0);
                }

                if (pair.first != null) {
                    pair.first.draw(prevTexName, fbo);
                }
            }
        }
    }

}
