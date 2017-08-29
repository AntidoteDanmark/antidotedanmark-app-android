package dk.antidotedanmark;

import android.content.Intent;
import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;


public class FirstAidGuide extends IntroActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFullscreen(false);

        /* Enable/disable skip button */
        setSkipEnabled(false);

        /* Enable/disable finish button */
        setFinishEnabled(true);


        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_material_darker)
                .backgroundDark(R.color.primary_dark)
                .fragment(R.layout.fragment_first_aid_guide01)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_material_darker)
                .backgroundDark(R.color.primary_dark)
                .fragment(R.layout.fragment_first_aid_guide02)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_material_darker)
                .backgroundDark(R.color.primary_dark)
                .fragment(R.layout.fragment_first_aid_guide03)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_material_darker)
                .backgroundDark(R.color.primary_dark)
                .fragment(R.layout.fragment_first_aid_guide04)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_material_darker)
                .backgroundDark(R.color.primary_dark)
                .fragment(R.layout.fragment_first_aid_guide05)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_material_darker)
                .backgroundDark(R.color.primary_dark)
                .fragment(R.layout.fragment_first_aid_guide06)
                .build());
    }
}
