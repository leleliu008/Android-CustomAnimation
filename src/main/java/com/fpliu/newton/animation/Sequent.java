package com.fpliu.newton.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Sequent {

    private static final int DEFAULT_OFFSET = 100;
    private static final int DEFAULT_DURATION = 300;
    private static final int DEFAULT_DELAY = 0;

    private ViewGroup viewGroup;

    private List<View> viewList = new ArrayList<>();

    private Set<Integer> skipSet = new HashSet<>();

    private int startOffset = DEFAULT_OFFSET;
    private int duration = DEFAULT_DURATION;
    private int delay = DEFAULT_DELAY;
    private Animator animator;
    private Direction direction = Direction.FORWARD;

    public enum Direction {
        FORWARD,
        BACKWARD,
        RANDOM
    }

    private Sequent() {
    }

    public static Sequent obtain(Activity activity, int viewGroupId) {
        Sequent sequent = new Sequent();
        sequent.viewGroup = (ViewGroup) activity.findViewById(viewGroupId);
        return sequent;
    }

    public static Sequent obtain(ViewGroup parent, int viewGroupId) {
        Sequent sequent = new Sequent();
        sequent.viewGroup = (ViewGroup) parent.findViewById(viewGroupId);
        return sequent;
    }

    public static Sequent obtain(ViewGroup viewGroup) {
        Sequent sequent = new Sequent();
        sequent.viewGroup = viewGroup;
        return sequent;
    }

    public Sequent offset(int offset) {
        this.startOffset = offset;
        return this;
    }

    public Sequent duration(int duration) {
        this.duration = duration;
        return this;
    }

    public Sequent delay(int delay) {
        this.delay = delay;
        return this;
    }

    public Sequent flow(Direction direction) {
        this.direction = direction;
        return this;
    }

    public Sequent anim(Context context, int animId) {
        this.animator = AnimatorInflater.loadAnimator(context, animId);
        return this;
    }

    public Sequent anim(Animator animator) {
        this.animator = animator;
        return this;
    }

    /**
     * 跳过一个ViewGroup不遍历它的孩子
     *
     * @param viewGroupId
     * @return
     */
    public Sequent skip(int viewGroupId) {
        if (viewGroupId > 0) {
            skipSet.add(viewGroupId);
            Log.d("HY_XX", "skip() viewGroupId = " + viewGroupId);
        }
        return this;
    }

    public void start() {
        fetchChildLayouts(viewGroup);
        arrangeLayouts(viewList);
        setAnimation();
    }

    private void fetchChildLayouts(ViewGroup viewGroup) {
        int id = viewGroup.getId();
        if (id > 0) {
            for (int skipId : skipSet) {
                //要跳过它
                if (skipId == id) {
                    if (viewGroup.getVisibility() == View.VISIBLE) {
                        viewGroup.setVisibility(View.INVISIBLE);
                        viewList.add(viewGroup);
                    }
                    return;
                }
            }
        }

        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                fetchChildLayouts((ViewGroup) view);
            } else {
                if (view.getVisibility() == View.VISIBLE) {
                    view.setVisibility(View.INVISIBLE);
                    viewList.add(view);
                }
            }
        }
    }

    private List<View> arrangeLayouts(List<View> viewList) {
        switch (direction) {
            case BACKWARD:
                Collections.reverse(viewList);
                break;
            case RANDOM:
                Collections.shuffle(viewList);
                break;
        }
        return viewList;
    }

    private void setAnimation() {
        int count = viewList.size();
        for (int i = 0; i < count; i++) {
            final View view = viewList.get(i);
            final int offset = i * startOffset;

            resetAnimation(view);

            List<Animator> animatorList = new ArrayList<>();
            animatorList.add(getStartObjectAnimator(offset, view));

            if (animator != null) {
                Animator animatorCopy = animator.clone();
                animatorCopy.setTarget(view);
                animatorList.add(animatorCopy);
            } else {
                animatorList.add(ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1));
            }

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animatorList);
            animatorSet.setDuration(duration);
            if (delay == 0) {
                animatorSet.setStartDelay(i * startOffset);
            } else if (i == 0) {
                animatorSet.setStartDelay(delay);
            } else {
                animatorSet.setStartDelay((i * startOffset) + delay);
            }
            animatorSet.start();
        }
    }

    private void resetAnimation(View view) {
        ViewCompat.setAlpha(view, 1);
        ViewCompat.setScaleX(view, 1);
        ViewCompat.setScaleY(view, 1);
        ViewCompat.setTranslationX(view, 0);
        ViewCompat.setTranslationY(view, 0);
        ViewCompat.setRotation(view, 0);
        ViewCompat.setRotationY(view, 0);
        ViewCompat.setRotationX(view, 0);
    }

    private ObjectAnimator getStartObjectAnimator(int offset, final View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        objectAnimator.setDuration(1).setStartDelay(offset);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator anim) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator anim) {
            }

            @Override
            public void onAnimationEnd(Animator anim) {
            }

            @Override
            public void onAnimationCancel(Animator anim) {
            }
        });
        return objectAnimator;
    }
}
