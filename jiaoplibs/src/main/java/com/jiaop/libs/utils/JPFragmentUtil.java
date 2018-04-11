package com.jiaop.libs.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by JiaoP
 * Fragment帮助类
 */
public class JPFragmentUtil {

    private static final int TYPE_ADD_FRAGMENT = 0x01;
    private static final int TYPE_SHOW_FRAGMENT = 0x01 << 1;
    private static final int TYPE_HIDE_FRAGMENT = 0x01 << 2;
    private static final int TYPE_SHOW_HIDE_FRAGMENT = 0x01 << 3;
    private static final int TYPE_REPLACE_FRAGMENT = 0x01 << 4;
    private static final int TYPE_REMOVE_FRAGMENT = 0x01 << 5;
    private static final int TYPE_REMOVE_TO_FRAGMENT = 0x01 << 6;

    private static final String ARGS_ID = "args_id";
    private static final String ARGS_IS_HIDE = "args_is_hide";
    private static final String ARGS_IS_ADD_STACK = "args_is_add_stack";

    private JPFragmentUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId) {
        add(fm, add, containerId, false, false);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param isHide
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           final boolean isHide) {
        add(fm, add, containerId, isHide, false);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param isHide
     * @param isAddStack
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           final boolean isHide,
                           final boolean isAddStack) {
        putArgs(add, new Args(containerId, isHide, isAddStack));
        operateNoAnim(fm, TYPE_ADD_FRAGMENT, null, add);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param enterAnim
     * @param exitAnim
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           @AnimRes final int enterAnim,
                           @AnimRes final int exitAnim) {
        add(fm, add, containerId, false, enterAnim, exitAnim, 0, 0);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param isAddStack
     * @param enterAnim
     * @param exitAnim
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           final boolean isAddStack,
                           @AnimRes final int enterAnim,
                           @AnimRes final int exitAnim) {
        add(fm, add, containerId, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           @AnimRes final int enterAnim,
                           @AnimRes final int exitAnim,
                           @AnimRes final int popEnterAnim,
                           @AnimRes final int popExitAnim) {
        add(fm, add, containerId, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param isAddStack
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           final boolean isAddStack,
                           @AnimRes final int enterAnim,
                           @AnimRes final int exitAnim,
                           @AnimRes final int popEnterAnim,
                           @AnimRes final int popExitAnim) {
        FragmentTransaction ft = fm.beginTransaction();
        putArgs(add, new Args(containerId, false, isAddStack));
        addAnim(ft, enterAnim, exitAnim, popEnterAnim, popExitAnim);
        operate(TYPE_ADD_FRAGMENT, fm, ft, null, add);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param sharedElements
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           @NonNull final View... sharedElements) {
        add(fm, add, containerId, false, sharedElements);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param add
     * @param containerId
     * @param isAddStack
     * @param sharedElements
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment add,
                           @IdRes final int containerId,
                           final boolean isAddStack,
                           @NonNull final View... sharedElements) {
        FragmentTransaction ft = fm.beginTransaction();
        putArgs(add, new Args(containerId, false, isAddStack));
        addSharedElement(ft, sharedElements);
        operate(TYPE_ADD_FRAGMENT, fm, ft, null, add);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param adds
     * @param containerId
     * @param showIndex
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final List<Fragment> adds,
                           @IdRes final int containerId,
                           final int showIndex) {
        add(fm, adds.toArray(new Fragment[adds.size()]), containerId, showIndex);
    }

    /**
     * 新增 fragment
     *
     * @param fm
     * @param adds
     * @param containerId
     * @param showIndex
     */
    public static void add(@NonNull final FragmentManager fm,
                           @NonNull final Fragment[] adds,
                           @IdRes final int containerId,
                           final int showIndex) {
        for (int i = 0, len = adds.length; i < len; ++i) {
            putArgs(adds[i], new Args(containerId, showIndex != i, false));
        }
        operateNoAnim(fm, TYPE_ADD_FRAGMENT, null, adds);
    }

    /**
     * 显示 fragment
     *
     * @param show
     */
    public static void show(@NonNull final Fragment show) {
        putArgs(show, false);
        operateNoAnim(show.getFragmentManager(), TYPE_SHOW_FRAGMENT, null, show);
    }

    /**
     * 显示 fragment
     *
     * @param fm
     */
    public static void show(@NonNull final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        for (Fragment show : fragments) {
            putArgs(show, false);
        }
        operateNoAnim(fm,
                TYPE_SHOW_FRAGMENT,
                null,
                fragments.toArray(new Fragment[fragments.size()])
        );
    }

    /**
     * 隐藏 fragment
     *
     * @param hide
     */
    public static void hide(@NonNull final Fragment hide) {
        putArgs(hide, true);
        operateNoAnim(hide.getFragmentManager(), TYPE_HIDE_FRAGMENT, null, hide);
    }

    /**
     * 隐藏 fragment
     *
     * @param fm
     */
    public static void hide(@NonNull final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        for (Fragment hide : fragments) {
            putArgs(hide, true);
        }
        operateNoAnim(fm,
                TYPE_HIDE_FRAGMENT,
                null,
                fragments.toArray(new Fragment[fragments.size()])
        );
    }

    /**
     * 先显示后隐藏 fragment
     *
     * @param showIndex
     * @param fragments
     */
    public static void showHide(final int showIndex, @NonNull final List<Fragment> fragments) {
        showHide(fragments.get(showIndex), fragments);
    }

    /**
     * 先显示后隐藏 fragment
     *
     * @param show
     * @param hide
     */
    public static void showHide(@NonNull final Fragment show, @NonNull final List<Fragment> hide) {
        for (Fragment fragment : hide) {
            putArgs(fragment, fragment != show);
        }
        operateNoAnim(show.getFragmentManager(), TYPE_SHOW_HIDE_FRAGMENT, show,
                hide.toArray(new Fragment[hide.size()]));
    }

    /**
     * 先显示后隐藏 fragment
     *
     * @param showIndex
     * @param fragments
     */
    public static void showHide(final int showIndex, @NonNull final Fragment... fragments) {
        showHide(fragments[showIndex], fragments);
    }

    /**
     * 先显示后隐藏 fragment
     *
     * @param show
     * @param hide
     */
    public static void showHide(@NonNull final Fragment show, @NonNull final Fragment... hide) {
        for (Fragment fragment : hide) {
            putArgs(fragment, fragment != show);
        }
        operateNoAnim(show.getFragmentManager(), TYPE_SHOW_HIDE_FRAGMENT, show, hide);
    }

    /**
     * 先显示后隐藏 fragment
     *
     * @param show
     * @param hide
     */
    public static void showHide(@NonNull final Fragment show,
                                @NonNull final Fragment hide) {
        putArgs(show, false);
        putArgs(hide, true);
        operateNoAnim(show.getFragmentManager(), TYPE_SHOW_HIDE_FRAGMENT, show, hide);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment) {
        replace(srcFragment, destFragment, false);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     * @param isAddStack
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment,
                               final boolean isAddStack) {
        Args args = getArgs(srcFragment);
        replace(srcFragment.getFragmentManager(), destFragment, args.id, isAddStack);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     * @param enterAnim
     * @param exitAnim
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim) {
        replace(srcFragment, destFragment, false, enterAnim, exitAnim, 0, 0);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     * @param isAddStack
     * @param enterAnim
     * @param exitAnim
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment,
                               final boolean isAddStack,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim) {
        replace(srcFragment, destFragment, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim,
                               @AnimRes final int popEnterAnim,
                               @AnimRes final int popExitAnim) {
        replace(srcFragment, destFragment, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     * @param isAddStack
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment,
                               final boolean isAddStack,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim,
                               @AnimRes final int popEnterAnim,
                               @AnimRes final int popExitAnim) {
        Args args = getArgs(srcFragment);
        replace(srcFragment.getFragmentManager(), destFragment, args.id, isAddStack,
                enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     * @param sharedElements
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment,
                               final View... sharedElements) {
        replace(srcFragment, destFragment, false, sharedElements);
    }

    /**
     * 替换 fragment
     *
     * @param srcFragment
     * @param destFragment
     * @param isAddStack
     * @param sharedElements
     */
    public static void replace(@NonNull final Fragment srcFragment,
                               @NonNull final Fragment destFragment,
                               final boolean isAddStack,
                               final View... sharedElements) {
        Args args = getArgs(srcFragment);
        replace(srcFragment.getFragmentManager(),
                destFragment,
                args.id,
                isAddStack,
                sharedElements
        );
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId) {
        replace(fm, fragment, containerId, false);
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param isAddStack
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId,
                               final boolean isAddStack) {
        FragmentTransaction ft = fm.beginTransaction();
        putArgs(fragment, new Args(containerId, false, isAddStack));
        operate(TYPE_REPLACE_FRAGMENT, fm, ft, null, fragment);
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param enterAnim
     * @param exitAnim
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim) {
        replace(fm, fragment, containerId, false, enterAnim, exitAnim, 0, 0);
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param isAddStack
     * @param enterAnim
     * @param exitAnim
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId,
                               final boolean isAddStack,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim) {
        replace(fm, fragment, containerId, isAddStack, enterAnim, exitAnim, 0, 0);
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim,
                               @AnimRes final int popEnterAnim,
                               @AnimRes final int popExitAnim) {
        replace(fm, fragment, containerId, false, enterAnim, exitAnim, popEnterAnim, popExitAnim);
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param isAddStack
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId,
                               final boolean isAddStack,
                               @AnimRes final int enterAnim,
                               @AnimRes final int exitAnim,
                               @AnimRes final int popEnterAnim,
                               @AnimRes final int popExitAnim) {
        FragmentTransaction ft = fm.beginTransaction();
        putArgs(fragment, new Args(containerId, false, isAddStack));
        addAnim(ft, enterAnim, exitAnim, popEnterAnim, popExitAnim);
        operate(TYPE_REPLACE_FRAGMENT, fm, ft, null, fragment);
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param sharedElements
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId,
                               final View... sharedElements) {
        replace(fm, fragment, containerId, false, sharedElements);
    }

    /**
     * 替换 fragment
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param isAddStack
     * @param sharedElements
     */
    public static void replace(@NonNull final FragmentManager fm,
                               @NonNull final Fragment fragment,
                               @IdRes final int containerId,
                               final boolean isAddStack,
                               final View... sharedElements) {
        FragmentTransaction ft = fm.beginTransaction();
        putArgs(fragment, new Args(containerId, false, isAddStack));
        addSharedElement(ft, sharedElements);
        operate(TYPE_REPLACE_FRAGMENT, fm, ft, null, fragment);
    }

    /**
     * 出栈 fragment
     *
     * @param fm
     */
    public static void pop(@NonNull final FragmentManager fm) {
        pop(fm, true);
    }

    /**
     * 出栈 fragment
     *
     * @param fm
     * @param isImmediate
     */
    public static void pop(@NonNull final FragmentManager fm,
                           final boolean isImmediate) {
        if (isImmediate) {
            fm.popBackStackImmediate();
        } else {
            fm.popBackStack();
        }
    }

    /**
     * 出栈到指定 fragment
     *
     * @param fm
     * @param popClz
     * @param isIncludeSelf
     */
    public static void popTo(@NonNull final FragmentManager fm,
                             final Class<? extends Fragment> popClz,
                             final boolean isIncludeSelf) {
        popTo(fm, popClz, isIncludeSelf, true);
    }

    /**
     * 出栈到指定 fragment
     *
     * @param fm
     * @param popClz
     * @param isIncludeSelf
     * @param isImmediate
     */
    public static void popTo(@NonNull final FragmentManager fm,
                             final Class<? extends Fragment> popClz,
                             final boolean isIncludeSelf,
                             final boolean isImmediate) {
        if (isImmediate) {
            fm.popBackStackImmediate(popClz.getName(),
                    isIncludeSelf ? FragmentManager.POP_BACK_STACK_INCLUSIVE : 0);
        } else {
            fm.popBackStack(popClz.getName(),
                    isIncludeSelf ? FragmentManager.POP_BACK_STACK_INCLUSIVE : 0);
        }
    }

    /**
     * 出栈所有 fragment
     *
     * @param fm
     */
    public static void popAll(@NonNull final FragmentManager fm) {
        popAll(fm, true);
    }

    /**
     * 出栈所有 fragment
     *
     * @param fm
     * @param isImmediate
     */
    public static void popAll(@NonNull final FragmentManager fm, final boolean isImmediate) {
        while (fm.getBackStackEntryCount() > 0) {
            if (isImmediate) {
                fm.popBackStackImmediate();
            } else {
                fm.popBackStack();
            }
        }
    }

    /**
     * 移除 fragment
     *
     * @param remove
     */
    public static void remove(@NonNull final Fragment remove) {
        operateNoAnim(remove.getFragmentManager(), TYPE_REMOVE_FRAGMENT, null, remove);
    }

    /**
     * 移除到指定 fragment
     *
     * @param removeTo
     * @param isIncludeSelf
     */
    public static void removeTo(@NonNull final Fragment removeTo, final boolean isIncludeSelf) {
        operateNoAnim(removeTo.getFragmentManager(), TYPE_REMOVE_TO_FRAGMENT,
                isIncludeSelf ? removeTo : null, removeTo);
    }

    /**
     * 移除所有 fragment
     *
     * @param fm
     */
    public static void removeAll(@NonNull final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        operateNoAnim(fm,
                TYPE_REMOVE_FRAGMENT,
                null,
                fragments.toArray(new Fragment[fragments.size()])
        );
    }

    /**
     * 设置传递参数
     *
     * @param fragment
     * @param args
     */
    private static void putArgs(final Fragment fragment, final Args args) {
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            fragment.setArguments(bundle);
        }
        bundle.putInt(ARGS_ID, args.id);
        bundle.putBoolean(ARGS_IS_HIDE, args.isHide);
        bundle.putBoolean(ARGS_IS_ADD_STACK, args.isAddStack);
    }

    private static void putArgs(final Fragment fragment, final boolean isHide) {
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            fragment.setArguments(bundle);
        }
        bundle.putBoolean(ARGS_IS_HIDE, isHide);
    }

    /**
     * 获取传递参数
     *
     * @param fragment
     * @return
     */
    private static Args getArgs(final Fragment fragment) {
        Bundle bundle = fragment.getArguments();
        return new Args(bundle.getInt(ARGS_ID, fragment.getId()),
                bundle.getBoolean(ARGS_IS_HIDE),
                bundle.getBoolean(ARGS_IS_ADD_STACK));
    }

    private static void operateNoAnim(final FragmentManager fm,
                                      final int type,
                                      final Fragment src,
                                      Fragment... dest) {
        FragmentTransaction ft = fm.beginTransaction();
        operate(type, fm, ft, src, dest);
    }

    private static void operate(final int type,
                                final FragmentManager fm,
                                final FragmentTransaction ft,
                                final Fragment src,
                                final Fragment... dest) {
        if (src != null && src.isRemoving()) {
            Log.e("FragmentUtils", src.getClass().getName() + " is isRemoving");
            return;
        }
        String name;
        Bundle args;
        switch (type) {
            case TYPE_ADD_FRAGMENT:
                for (Fragment fragment : dest) {
                    name = fragment.getClass().getName();
                    args = fragment.getArguments();
                    Fragment fragmentByTag = fm.findFragmentByTag(name);
                    if (fragmentByTag != null && fragmentByTag.isAdded()) {
                        ft.remove(fragmentByTag);
                    }
                    ft.add(args.getInt(ARGS_ID), fragment, name);
                    if (args.getBoolean(ARGS_IS_HIDE)) ft.hide(fragment);
                    if (args.getBoolean(ARGS_IS_ADD_STACK)) ft.addToBackStack(name);
                }
                break;
            case TYPE_HIDE_FRAGMENT:
                for (Fragment fragment : dest) {
                    ft.hide(fragment);
                }
                break;
            case TYPE_SHOW_FRAGMENT:
                for (Fragment fragment : dest) {
                    ft.show(fragment);
                }
                break;
            case TYPE_SHOW_HIDE_FRAGMENT:
                ft.show(src);
                for (Fragment fragment : dest) {
                    if (fragment != src) {
                        ft.hide(fragment);
                    }
                }
                break;
            case TYPE_REPLACE_FRAGMENT:
                name = dest[0].getClass().getName();
                args = dest[0].getArguments();
                ft.replace(args.getInt(ARGS_ID), dest[0], name);
                if (args.getBoolean(ARGS_IS_ADD_STACK)) ft.addToBackStack(name);
                break;
            case TYPE_REMOVE_FRAGMENT:
                for (Fragment fragment : dest) {
                    if (fragment != src) {
                        ft.remove(fragment);
                    }
                }
                break;
            case TYPE_REMOVE_TO_FRAGMENT:
                for (int i = dest.length - 1; i >= 0; --i) {
                    Fragment fragment = dest[i];
                    if (fragment == dest[0]) {
                        if (src != null) ft.remove(fragment);
                        break;
                    }
                    ft.remove(fragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    private static void addAnim(final FragmentTransaction ft,
                                final int enter,
                                final int exit,
                                final int popEnter,
                                final int popExit) {
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
    }

    private static void addSharedElement(final FragmentTransaction ft,
                                         final View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (View view : views) {
                ft.addSharedElement(view, view.getTransitionName());
            }
        }
    }

    /**
     * 获取顶部 fragment
     *
     * @param fm
     * @return
     */
    public static Fragment getTop(@NonNull final FragmentManager fm) {
        return getTopIsInStack(fm, false);
    }

    /**
     * 获取栈中顶部 fragment
     *
     * @param fm
     * @return
     */
    public static Fragment getTopInStack(@NonNull final FragmentManager fm) {
        return getTopIsInStack(fm, true);
    }

    /**
     * 获取栈中顶部 fragment
     *
     * @param fm
     * @param isInStack
     * @return
     */
    private static Fragment getTopIsInStack(@NonNull final FragmentManager fm,
                                            final boolean isInStack) {
        List<Fragment> fragments = getFragments(fm);
        for (int i = fragments.size() - 1; i >= 0; --i) {
            Fragment fragment = fragments.get(i);
            if (fragment != null) {
                if (isInStack) {
                    if (fragment.getArguments().getBoolean(ARGS_IS_ADD_STACK)) {
                        return fragment;
                    }
                } else {
                    return fragment;
                }
            }
        }
        return null;
    }

    /**
     * 获取顶部可见 fragment
     *
     * @param fm
     * @return
     */
    public static Fragment getTopShow(@NonNull final FragmentManager fm) {
        return getTopShowIsInStack(fm, false);
    }

    /**
     * 获取栈中顶部可见 fragment
     *
     * @param fm
     * @return
     */
    public static Fragment getTopShowInStack(@NonNull final FragmentManager fm) {
        return getTopShowIsInStack(fm, true);
    }

    private static Fragment getTopShowIsInStack(@NonNull final FragmentManager fm,
                                                final boolean isInStack) {
        List<Fragment> fragments = getFragments(fm);
        for (int i = fragments.size() - 1; i >= 0; --i) {
            Fragment fragment = fragments.get(i);
            if (fragment != null
                    && fragment.isResumed()
                    && fragment.isVisible()
                    && fragment.getUserVisibleHint()) {
                if (isInStack) {
                    if (fragment.getArguments().getBoolean(ARGS_IS_ADD_STACK)) {
                        return fragment;
                    }
                } else {
                    return fragment;
                }
            }
        }
        return null;
    }

    /**
     * 获取同级别的 fragment
     *
     * @param fm
     * @return
     */
    public static List<Fragment> getFragments(@NonNull final FragmentManager fm) {
        @SuppressWarnings("RestrictedApi")
        List<Fragment> fragments = fm.getFragments();
        if (fragments == null || fragments.isEmpty()) return Collections.emptyList();
        return fragments;
    }

    /**
     * 获取同级别栈中的 fragment
     *
     * @param fm
     * @return
     */
    public static List<Fragment> getFragmentsInStack(@NonNull final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        List<Fragment> result = new ArrayList<>();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.getArguments().getBoolean(ARGS_IS_ADD_STACK)) {
                result.add(fragment);
            }
        }
        return result;
    }

    /**
     * 获取所有 fragment
     *
     * @param fm
     * @return
     */
    public static List<FragmentNode> getAllFragments(@NonNull final FragmentManager fm) {
        return getAllFragments(fm, new ArrayList<FragmentNode>());
    }

    private static List<FragmentNode> getAllFragments(@NonNull final FragmentManager fm,
                                                      final List<FragmentNode> result) {
        List<Fragment> fragments = getFragments(fm);
        for (int i = fragments.size() - 1; i >= 0; --i) {
            Fragment fragment = fragments.get(i);
            if (fragment != null) {
                result.add(new FragmentNode(fragment,
                        getAllFragments(fragment.getChildFragmentManager(),
                                new ArrayList<FragmentNode>())));
            }
        }
        return result;
    }

    /**
     * 获取栈中所有 fragment
     *
     * @param fm
     * @return
     */
    public static List<FragmentNode> getAllFragmentsInStack(@NonNull final FragmentManager fm) {
        return getAllFragmentsInStack(fm, new ArrayList<FragmentNode>());
    }

    private static List<FragmentNode> getAllFragmentsInStack(@NonNull final FragmentManager fm,
                                                             final List<FragmentNode> result) {
        List<Fragment> fragments = getFragments(fm);
        for (int i = fragments.size() - 1; i >= 0; --i) {
            Fragment fragment = fragments.get(i);
            if (fragment != null && fragment.getArguments().getBoolean(ARGS_IS_ADD_STACK)) {
                result.add(new FragmentNode(fragment,
                        getAllFragmentsInStack(fragment.getChildFragmentManager(),
                                new ArrayList<FragmentNode>())));
            }
        }
        return result;
    }

    /**
     * 查找 fragment
     *
     * @param fm
     * @param findClz
     * @return
     */
    public static Fragment findFragment(@NonNull final FragmentManager fm,
                                        final Class<? extends Fragment> findClz) {
        return fm.findFragmentByTag(findClz.getName());
    }

    /**
     * 处理 fragment 回退键
     *
     * @param fragment
     * @return
     */
    public static boolean dispatchBackPress(@NonNull final Fragment fragment) {
        return fragment.isResumed()
                && fragment.isVisible()
                && fragment.getUserVisibleHint()
                && fragment instanceof OnBackClickListener
                && ((OnBackClickListener) fragment).onBackClick();
    }

    /**
     * 处理 fragment 回退键
     *
     * @param fm
     * @return
     */
    public static boolean dispatchBackPress(@NonNull final FragmentManager fm) {
        List<Fragment> fragments = getFragments(fm);
        if (fragments == null || fragments.isEmpty()) return false;
        for (int i = fragments.size() - 1; i >= 0; --i) {
            Fragment fragment = fragments.get(i);
            if (fragment != null
                    && fragment.isResumed()
                    && fragment.isVisible()
                    && fragment.getUserVisibleHint()
                    && fragment instanceof OnBackClickListener
                    && ((OnBackClickListener) fragment).onBackClick()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置背景色
     *
     * @param fragment
     * @param color
     */
    public static void setBackgroundColor(@NonNull final Fragment fragment,
                                          @ColorInt final int color) {
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }

    /**
     * 设置背景资源
     *
     * @param fragment
     * @param resId
     */
    public static void setBackgroundResource(@NonNull final Fragment fragment,
                                             @DrawableRes final int resId) {
        View view = fragment.getView();
        if (view != null) {
            view.setBackgroundResource(resId);
        }
    }

    /**
     * 设置背景
     *
     * @param fragment
     * @param background
     */
    public static void setBackground(@NonNull final Fragment fragment, final Drawable background) {
        ViewCompat.setBackground(fragment.getView(), background);
    }

    /**
     * 设置Fragment名称
     *
     * @param fragment
     * @return
     */
    public static String getSimpleName(final Fragment fragment) {
        return fragment == null ? "null" : fragment.getClass().getSimpleName();
    }

    private static class Args {
        int id;
        boolean isHide;
        boolean isAddStack;

        private Args(final int id, final boolean isHide, final boolean isAddStack) {
            this.id = id;
            this.isHide = isHide;
            this.isAddStack = isAddStack;
        }
    }

    public static class FragmentNode {
        Fragment fragment;
        List<FragmentNode> next;

        public FragmentNode(final Fragment fragment, final List<FragmentNode> next) {
            this.fragment = fragment;
            this.next = next;
        }

        @Override
        public String toString() {
            return fragment.getClass().getSimpleName()
                    + "->"
                    + ((next == null || next.isEmpty()) ? "no child" : next.toString());
        }
    }


    public interface OnBackClickListener {
        boolean onBackClick();
    }

}
