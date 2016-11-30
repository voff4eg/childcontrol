package com.example.childcontrol.childcontrol;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

import de.greenrobot.event.EventBus;

public class SplashIntroActivity extends IntroActivity {

    private UserTypeDao userTypeDao;
    private UserType userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        // get the note DAO
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        userTypeDao = daoSession.getUserTypeDao();

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_material_metaphor)
                .description(R.string.description_material_metaphor)
                .image(R.drawable.art_material_metaphor)
                .background(R.color.color_material_metaphor)
                .backgroundDark(R.color.color_dark_material_metaphor)
                .build());

        final Slide selectorSlide;
        selectorSlide = new FragmentSlide.Builder()
                .background(R.color.first_slide_background)
                .backgroundDark(R.color.second_slide_background)
                .fragment(SelectorFragment.newInstance())
                .build();
        addSlide(selectorSlide);

        final Slide finishSlide;
        finishSlide = new FragmentSlide.Builder()
                .background(R.color.first_slide_background)
                .backgroundDark(R.color.second_slide_background)
                .fragment(FinishFragment.newInstance())
                .build();
        addSlide(finishSlide);
    }

    public UserTypeDao getUserTypeDao() {
        return userTypeDao;
    }

    public void onStart() {
        super.onStart();
        // регистрация приемника при старте фрагмента
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        // отписываемся от регистрации при закрытии фрагмента
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    // В этом методе-колбэке мы получаем наши данные
    // (объект `event` типа класса-модели MessageEvent)
    public void onEvent(MessageEvent event) {
        // извлекаем из модели отправленную строку: event.message = "Hello everyone!"
        switch (event.res) {
            case R.id.parent:
                userType = userTypeDao.queryBuilder().build().unique();
                if (userType == null || userType.getType() == null) {
                    userType = new UserType(null, UserType.PARENT_USER_TYPE);
                    userTypeDao.insertOrReplace(userType);
                } else if (!userType.getType().equals(UserType.PARENT_USER_TYPE)) {
                    userType.setType(UserType.PARENT_USER_TYPE);
                    userTypeDao.update(userType);
                }
                break;
            case R.id.child:
                userType = userTypeDao.queryBuilder().build().unique();
                if (userType == null) {
                    userType = new UserType(null, UserType.CHILD_USER_TYPE);
                    userTypeDao.insertOrReplace(userType);
                } else if (!userType.getType().equals(UserType.CHILD_USER_TYPE)) {
                    userType.setType(UserType.CHILD_USER_TYPE);
                    userTypeDao.update(userType);
                }
                break;
        }
    }
}
