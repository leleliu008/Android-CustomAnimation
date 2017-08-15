package com.fpliu.newton.animation;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public final class AnimationFactory {

    public static final Interpolator BOUNCE_INTERPOLATOR = new BounceInterpolator();

    public static long duration = 600;

    public static Animation slideFromBottom() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return translateAnimation;
    }

    public static Animation slideToBottom() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return translateAnimation;
    }

    public static Animation slideFromTop() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return translateAnimation;
    }

    public static Animation slideToTop(Interpolator inter) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -1.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(inter);
        return translateAnimation;
    }

    public static Animation slideFromLeft() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return translateAnimation;
    }

    public static Animation slideToLeft() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return translateAnimation;
    }

    public static Animation fadingIn() {
        AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
        alphaAnim.setFillAfter(true);
        alphaAnim.setDuration(duration);
        return alphaAnim;
    }

    public static Animation fadingOut() {
        AlphaAnimation alphaAnim = new AlphaAnimation(1, 0);
        alphaAnim.setFillAfter(true);
        alphaAnim.setDuration(duration);
        return alphaAnim;
    }

    public static Animation rotate3DFromLeft() {
        FlipAnimation rotate3dAnim = new FlipAnimation(384, 640, false);
        rotate3dAnim.setFillAfter(true);
        rotate3dAnim.setDuration(duration);
        rotate3dAnim.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotate3dAnim;
    }

    public static Animation rotate3DFromRight() {
        FlipAnimation rotate3dAnim = new FlipAnimation(384, 640, true);
        rotate3dAnim.setFillAfter(true);
        rotate3dAnim.setDuration(duration);
        rotate3dAnim.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotate3dAnim;
    }

    public static Animation rotateLeftCenterIn() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                -90, 0,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotateAnimation;

    }

    public static Animation rotaLeftCenterOut() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 180,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotateAnimation;
    }

    public static Animation rotateLeftTopIn() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                -90, 0,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotateAnimation;
    }

    public static Animation rotateLeftTopOut(Interpolator inter) {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, -90,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotateAnimation;
    }

    public static Animation rotateCenterIn() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotateAnimation;
    }

    public static Animation rotateCenterOut() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, -360,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return rotateAnimation;
    }

    public static Animation scaleBig() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0, 1.0f,
                0, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation scaleSmall() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0,
                1.0f, 0,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation scaleBigLeftTop() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0, 1.0f,
                0, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation scaleSmallLeftTop() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0,
                1.0f, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation scaleToBigHorizontalIn() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0f, 1.0f,
                1.0f, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation scaleToBigHorizontalOut() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0f,
                1.0f, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation scaleToBigVerticalIn() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.0f,
                0f, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation scaleToBigVerticalOut() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.0f,
                1.0f, 0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return scaleAnimation;
    }

    public static Animation shakeMode(Integer shakeCount) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.1f,
                Animation.RELATIVE_TO_PARENT, 0.1f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        if (shakeCount == null) {
            translateAnimation.setRepeatCount(1);
        } else {
            translateAnimation.setRepeatCount(shakeCount);
        }
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return translateAnimation;
    }

    public static Animation slideFromRight() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        return translateAnimation;
    }

    public static Animation slideToRight(Interpolator inter) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(inter);
        return translateAnimation;
    }
}
