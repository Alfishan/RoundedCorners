package lonewolfapps.roundedcorners;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Creates the head layer view which is displayed directly on window manager.
 * It means that the view is above every application's view on your phone -
 * until another application does the same.
 */
public class CornerLayer extends View {

    private Context mContext;
    private FrameLayout mFrameLayout;
    private WindowManager mWindowManager;

    public CornerLayer(Context context) {
        super(context);
        mContext = context;
        mFrameLayout = new FrameLayout(mContext);

        addToWindowManager();
    }

    private void addToWindowManager() {
        final WindowManager.LayoutParams params;
        params = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT
                ,WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS|
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);

        mFrameLayout.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bg_about_content_border));
        mFrameLayout.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
        mFrameLayout.setFitsSystemWindows(true);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(mFrameLayout, params);
    }

    /**
     * Removes the view from window manager.
     */
    public void destroy() {
        mWindowManager.removeView(mFrameLayout);
    }
}
