package com.fpliu.newton.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 转场动画工具
 * 请求确保使用的Theme中有如下配置：
 * &lt;item name="android:windowIsTranslucent"&gt;true&lt;/item&gt;
 * &lt;item name="android:windowAnimationStyle"&gt;@null&lt;/item&gt;
 */
public final class AnimationHelper {

    private AnimationHelper() {
    }

    /**
     * 退出Activity的时候调用
     *
     * @param activity                    要退出的Activity
     * @param closeActivityAfterAnimation 当动画结束后，是否销毁当前Activity
     * @param animation                   {@link AnimationFactory}
     */
    public static void out(final Activity activity, boolean closeActivityAfterAnimation, Animation animation) {
        if (closeActivityAfterAnimation) {
            animation.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    activity.finish();
                }
            });
        }
        getRootView(activity).startAnimation(animation);
    }

    /**
     * 退出Activity的时候调用
     *
     * @param activity                    要退出的Activity
     * @param closeActivityAfterAnimation 当动画结束后，是否销毁当前Activity
     * @param animator                    {@link AnimatorBuilder}
     */
    public static void out(final Activity activity, boolean closeActivityAfterAnimation, Animator animator) {
        if (closeActivityAfterAnimation) {
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animator) {
                    activity.finish();
                }
            });
        }
        animator.setTarget(getRootView(activity));
        animator.start();
    }

    /**
     * 退出Dialog的时候调用
     *
     * @param dialog                      要退出的Dialog
     * @param dismissDialogAfterAnimation 当动画结束后，是否销毁当前Dialog
     * @param animation                   {@link AnimatorBuilder}
     */
    public static void out(final Dialog dialog, boolean dismissDialogAfterAnimation, Animation animation) {
        if (dismissDialogAfterAnimation) {
            animation.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    dialog.dismiss();
                }
            });
        }
        getRootView(dialog).startAnimation(animation);
    }

    /**
     * 退出Dialog的时候调用
     *
     * @param dialog                      要退出的Dialog
     * @param dismissDialogAfterAnimation 当动画结束后，是否销毁当前Dialog
     * @param animator                    {@link AnimatorBuilder}
     */
    public static void out(final Dialog dialog, boolean dismissDialogAfterAnimation, Animator animator) {
        if (dismissDialogAfterAnimation) {
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animator) {
                    dialog.dismiss();
                }
            });
        }
        animator.setTarget(getRootView(dialog));
        animator.start();
    }

    public static View getRootView(Activity activity) {
        return activity.getWindow().getDecorView();
    }

    public static View getRootView(Dialog dialog) {
        return dialog.getWindow().getDecorView();
    }
}
