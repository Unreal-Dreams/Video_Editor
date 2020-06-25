package com.daasuu.epf.filter;

/**
 * Created by sudamasayuki on 2017/05/18.
 */

public class GlInvertFilter extends GlFilter {
    private static final String FRAGMENT_SHADER =
            "precision mediump float;" +
                    "varying vec2 vTextureCoord;" +
                    "uniform lowp sampler2D sTexture;" +
                    "void main() {" +
                    "lowp vec4 color = texture2D(sTexture, vTextureCoord);" +
                    "gl_FragColor = vec4((1.0 - color.rgb), color.w);" +
                    "}";

    public GlInvertFilter() {
        super(DEFAULT_VERTEX_SHADER, FRAGMENT_SHADER);
    }
}
