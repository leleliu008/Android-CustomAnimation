package com.fpliu.newton.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Sequent2 {

    private List<View> targets = new ArrayList<>();
    private Map<View, Animator> animatorMap = new HashMap<>();

    private Sequent2() {
    }

    public static Sequent2 obtain() {
        return new Sequent2();
    }

    public Sequent2 anim(Activity activity, int viewId, int animId) {
        return anim(activity.findViewById(viewId), animId);
    }

    public Sequent2 anim(View parent, int viewId, int animId) {
        return anim(parent.findViewById(viewId), animId);
    }

    public Sequent2 anim(View target, int animId) {
        Animator animator = AnimatorInflater.loadAnimator(target.getContext(), animId);
        animator.setTarget(target);
        targets.add(target);
        animatorMap.put(target, animator);
        return this;
    }

    public Sequent2 anim(Activity activity, int viewId, Animator animator) {
        return anim(activity.findViewById(viewId), animator);
    }

    public Sequent2 anim(View parent, int viewId, Animator animator) {
        return anim(parent.findViewById(viewId), animator);
    }

    public Sequent2 anim(View target, Animator animator) {
        targets.add(target);
        animatorMap.put(target, animator);
        return this;
    }

    public Sequent2 anim(ObjectAnimator animator) {
        View target = (View) animator.getTarget();
        targets.add(target);
        animatorMap.put(target, animator);
        return this;
    }

    public AnimatorSet sequent() {
        List<Animator> animators = new ArrayList<>();
        int count = targets.size();
        for (int i = 0; i < count; i++) {
            View target = targets.get(i);
            resetAnimation(target);
            animators.add(animatorMap.get(target));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animators);
        return animatorSet;
    }

    private void resetAnimation(View view) {
        view.setAlpha(1);
        view.setScaleX(1);
        view.setScaleY(1);
        view.setTranslationX(0);
        view.setTranslationY(0);
        view.setRotation(0);
        view.setRotationY(0);
        view.setRotationX(0);
    }
}
