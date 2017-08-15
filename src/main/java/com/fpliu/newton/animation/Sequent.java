package com.fpliu.newton.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Sequent {

    private static final int DEFAULT_OFFSET = 100;
    private static final int DEFAULT_DURATION = 300;
    private static final int DEFAULT_DELAY = 0;

    private ViewGroup viewGroup;

    private List<View> targetList = new ArrayList<>();

    private SparseArray<Boolean> skipViews = new SparseArray<>();

    private SparseArray<Animator> particularAnimators = new SparseArray<>();
    private SparseArray<Animator> particularChildrenAnimators = new SparseArray<>();

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

    /**
     * 设置动画
     *
     * @param animator {@link AnimatorBuilder}
     * @return
     */
    public Sequent anim(Animator animator) {
        this.animator = animator;
        return this;
    }

    /**
     * 如果View控件，忽略它的动画效果
     * 如果是ViewGroup，不遍历它的孩子
     *
     * @param viewId
     * @param skipMe 如果viewId是ViewGroup，表示是否也忽略自己
     * @return
     */
    public Sequent skip(int viewId, boolean skipMe) {
        if (viewId > 0) {
            skipViews.put(viewId, skipMe);
        }
        return this;
    }

    /**
     * 给指定的控件设置特殊的动画
     *
     * @param viewId   指定的控件的Id
     * @param animator 动画 可以通过{@link AnimatorBuilder}构造一个实例
     * @return
     */
    public Sequent particular(int viewId, Animator animator) {
        if (viewId > 0) {
            particularAnimators.put(viewId, animator);
        }
        return this;
    }

    /**
     * 给指定的容器里的所有控件设置特殊的动画
     *
     * @param viewGroupId 指定的容器的Id
     * @param animator    动画 可以通过{@link AnimatorBuilder}构造一个实例
     * @return
     */
    public Sequent particularChildren(int viewGroupId, Animator animator) {
        if (viewGroupId > 0) {
            particularChildrenAnimators.put(viewGroupId, animator);
        }
        return this;
    }

    public void start() {
        fetchChildLayouts(viewGroup);
        arrangeLayouts(targetList);
        setAnimation();
    }

    private void fetchChildLayouts(ViewGroup viewGroup) {
        int viewId = viewGroup.getId();
        if (viewId > 0) {
            Boolean skipMe = skipViews.get(viewId);
            if (skipMe != null) {
                if (!skipMe) {
                    if (viewGroup.getVisibility() == View.VISIBLE) {
                        viewGroup.setVisibility(View.INVISIBLE);
                        targetList.add(viewGroup);
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
                Boolean skipMe = skipViews.get(view.getId());
                if (skipMe != null) {
                    if (!skipMe) {
                        if (view.getVisibility() == View.VISIBLE) {
                            view.setVisibility(View.INVISIBLE);
                            targetList.add(view);
                        }
                    }
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
        int count = targetList.size();
        for (int i = 0; i < count; i++) {
            final View view = targetList.get(i);
            final int offset = i * startOffset;

            resetAnimation(view);

            List<Animator> animatorList = new ArrayList<>();
            animatorList.add(getStartObjectAnimator(offset, view));

            int viewId = view.getId();
            if (viewId > 0) {
                Animator animator = particularAnimators.get(viewId);
                if (animator == null) {
                    if (this.animator == null) {
                        animatorList.add(ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1));
                    } else {
                        Animator animatorCopy = this.animator.clone();
                        animatorCopy.setTarget(view);
                        animatorList.add(animatorCopy);
                    }
                } else {
                    animator.setTarget(view);
                    animatorList.add(animator);
                }
            } else {
                if (this.animator == null) {
                    animatorList.add(ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1));
                } else {
                    Animator animatorCopy = this.animator.clone();
                    animatorCopy.setTarget(view);
                    animatorList.add(animatorCopy);
                }
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
