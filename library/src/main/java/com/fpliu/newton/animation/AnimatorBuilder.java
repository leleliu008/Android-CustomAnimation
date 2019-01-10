package com.fpliu.newton.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public final class AnimatorBuilder {

    private View targetView;
    private Animator animator;

    private AnimatorBuilder() {
    }

    public static AnimatorBuilder obtain() {
        return new AnimatorBuilder();
    }

    public AnimatorBuilder target(Activity activity, int targetViewId) {
        targetView = activity.findViewById(targetViewId);
        return this;
    }

    public AnimatorBuilder target(View parent, int targetViewId) {
        targetView = parent.findViewById(targetViewId);
        return this;
    }

    public AnimatorBuilder target(View targetView) {
        this.targetView = targetView;
        return this;
    }

    public AnimatorBuilder anim(Context context, int animId) {
        this.animator = AnimatorInflater.loadAnimator(context, animId);
        this.animator.setTarget(targetView);
        return this;
    }

    public AnimatorBuilder anim(Animator animator) {
        this.animator = animator;
        this.animator.setTarget(targetView);
        return this;
    }

    public AnimatorBuilder alpha(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.ALPHA, values);
        return this;
    }

    public AnimatorBuilder alpha(float from, float to) {
        animator = ObjectAnimator.ofFloat(targetView, View.ALPHA, from, to);
        return this;
    }

    public AnimatorBuilder scaleX(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.SCALE_X, values);
        return this;
    }

    public AnimatorBuilder scaleX(float from, float to) {
        animator = ObjectAnimator.ofFloat(targetView, View.SCALE_X, from, to);
        return this;
    }

    public AnimatorBuilder scaleY(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, values);
        return this;
    }

    public AnimatorBuilder scaleY(float from, float to) {
        animator = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, from, to);
        return this;
    }

    public AnimatorBuilder scale(float... values) {
        AnimatorSet animatorSet = new AnimatorSet();
        Animator scaleX = ObjectAnimator.ofFloat(targetView, View.SCALE_X, values);
        Animator scaleY = ObjectAnimator.ofFloat(targetView, View.SCALE_Y, values);
        animatorSet.playTogether(scaleX, scaleY);
        animator = animatorSet;
        return this;
    }

    public AnimatorBuilder scale(float from, float to) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(targetView, View.SCALE_X, from, to))
                .with(ObjectAnimator.ofFloat(targetView, View.SCALE_Y, from, to));
        animator = animatorSet;
        return this;
    }

    public AnimatorBuilder rotationX(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.ROTATION_X, values);
        return this;
    }

    public AnimatorBuilder rotationY(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.ROTATION_Y, values);
        return this;
    }

    public AnimatorBuilder rotation(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.ROTATION, values);
        return this;
    }

    public AnimatorBuilder translationX(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, values);
        return this;
    }

    public AnimatorBuilder translationX(float from, float to) {
        animator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, from, to);
        return this;
    }

    public AnimatorBuilder translationY(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y, values);
        return this;
    }

    public AnimatorBuilder translationY(float from, float to) {
        animator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Y, from, to);
        return this;
    }

    public AnimatorBuilder translationZ(float... values) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_Z, values);
        } else {
            animator = ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, values);
        }
        return this;
    }

    public AnimatorBuilder x(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.X, values);
        return this;
    }

    public AnimatorBuilder y(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, View.Y, values);
        return this;
    }

    public AnimatorBuilder z(float... values) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ObjectAnimator.ofFloat(targetView, View.Z, values);
        } else {
            animator = ObjectAnimator.ofFloat(targetView, View.X, values);
        }
        return this;
    }

    public AnimatorBuilder backgroundColor(int... values) {
        ObjectAnimator animator = new ObjectAnimator();
        animator.setTarget(targetView);
        animator.setIntValues(values);
        animator.setEvaluator(new ArgbEvaluator());
        this.animator = animator;
        return this;
    }

    public AnimatorBuilder left(int... values) {
        animator = ObjectAnimator.ofInt(targetView, "left", values);
        return this;
    }

    public AnimatorBuilder left(int from, int to) {
        animator = ObjectAnimator.ofInt(targetView, "left", from, to);
        return this;
    }

    public AnimatorBuilder right(int... values) {
        animator = ObjectAnimator.ofInt(targetView, "right", values);
        return this;
    }

    public AnimatorBuilder right(int from, int to) {
        animator = ObjectAnimator.ofInt(targetView, "right", from, to);
        return this;
    }

    public AnimatorBuilder top(int... values) {
        animator = ObjectAnimator.ofInt(targetView, "top", values);
        return this;
    }

    public AnimatorBuilder top(int from, int to) {
        animator = ObjectAnimator.ofInt(targetView, "top", from, to);
        return this;
    }

    public AnimatorBuilder bottom(int... values) {
        animator = ObjectAnimator.ofInt(targetView, "bottom", values);
        return this;
    }

    public AnimatorBuilder bottom(int from, int to) {
        animator = ObjectAnimator.ofInt(targetView, "bottom", from, to);
        return this;
    }

    public AnimatorBuilder minimumWidth(int... values) {
        animator = ObjectAnimator.ofInt(targetView, "minimumWidth", values);
        return this;
    }

    public AnimatorBuilder minimumHeight(int... values) {
        animator = ObjectAnimator.ofInt(targetView, "minimumHeight", values);
        return this;
    }

    public AnimatorBuilder pivotX(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, "pivotX", values);
        return this;
    }

    public AnimatorBuilder pivotY(float... values) {
        animator = ObjectAnimator.ofFloat(targetView, "pivotY", values);
        return this;
    }

    public AnimatorBuilder interpolator(TimeInterpolator interpolator) {
        ensureAnimatorNotNull();
        animator.setInterpolator(interpolator);
        return this;
    }

    /**
     * 设置动画时长
     *
     * @param duration 必须大于0
     * @return
     */
    public AnimatorBuilder duration(int duration) {
        ensureAnimatorNotNull();
        animator.setDuration(duration);
        return this;
    }

    /**
     * 设置动画重复次数
     *
     * @param repeatCount {@link ValueAnimator#INFINITE}
     * @return
     */
    public AnimatorBuilder repeatCount(int repeatCount) {
        if (animator instanceof ValueAnimator) {
            ((ValueAnimator) animator).setRepeatCount(repeatCount);
        }
        return this;
    }

    /**
     * 设置动画执行完一个后，如何重复执行
     *
     * @param repeatMode {@link ValueAnimator#RESTART}、{@link ValueAnimator#REVERSE}
     * @return
     */
    public AnimatorBuilder repeatMode(int repeatMode) {
        if (animator instanceof ValueAnimator) {
            ((ValueAnimator) animator).setRepeatMode(repeatMode);
        }
        return this;
    }

    public AnimatorBuilder delay(long delay) {
        ensureAnimatorNotNull();
        animator.setStartDelay(delay);
        return this;
    }

    public AnimatorBuilder evaluator(TypeEvaluator typeEvaluator) {
        ensureAnimatorNotNull();
        if (animator instanceof ValueAnimator) {
            ((ValueAnimator) animator).setEvaluator(typeEvaluator);
        }
        return this;
    }

    /**
     * 添加监听器
     *
     * @param listener {@link AnimatorListenerAdapter}
     * @return
     */
    public AnimatorBuilder listener(Animator.AnimatorListener listener) {
        ensureAnimatorNotNull();
        animator.addListener(listener);
        return this;
    }

    public Animator build() {
        return animator;
    }

    public AnimatorSet buildAsAnimatorSet() {
        if (animator instanceof AnimatorSet) {
            return (AnimatorSet) animator;
        } else {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setTarget(targetView);
            animatorSet.setStartDelay(animator.getStartDelay());
            animatorSet.setDuration(animator.getDuration());
            animatorSet.setInterpolator(animator.getInterpolator());
            animatorSet.playTogether(animator);
            return animatorSet;
        }
    }

    public AnimatorSet togetherWith(Animator... animators) {
        if (animator instanceof AnimatorSet) {
            AnimatorSet animatorSet = (AnimatorSet) animator;
            animatorSet.playTogether(animators);
            return animatorSet;
        } else {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setTarget(targetView);
            animatorSet.setStartDelay(animator.getStartDelay());
            animatorSet.setDuration(animator.getDuration());
            animatorSet.setInterpolator(animator.getInterpolator());

            List<Animator> animatorList = Arrays.asList(animators);
            animatorList.add(animator);
            animatorSet.playTogether(animatorList);

            return animatorSet;
        }
    }

    public AnimatorBuilder start() {
        animator.start();
        return this;
    }

    private void ensureAnimatorNotNull() {
        if (animator == null) {
            animator = new AnimatorSet();
        }
    }
}
