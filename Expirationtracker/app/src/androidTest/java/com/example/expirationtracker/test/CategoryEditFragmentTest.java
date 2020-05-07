package com.example.expirationtracker.test;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import com.example.expirationtracker.R;
import com.example.expirationtracker.ui.Category.CategoryEditFragment;
import com.example.expirationtracker.ui.NavActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

public class CategoryEditFragmentTest {

    @Rule
    public IntentsTestRule<NavActivity> mIntentsRule  = new IntentsTestRule<NavActivity>(NavActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Intent intent = new Intent();
            intent.putExtra("content", "CATEGORY_EDIT");
            return intent;
        }
    };
    private NavActivity mActivity = null;
    @Before
    public void setUp() throws Exception {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("1@gmail.com", "123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mActivity = mIntentsRule.getActivity();
                        } else {
                        }

                    }
                });

    }

    @Test
    public void testLaunch(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword("1@gmail.com", "123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(mActivity != null){
                                LinearLayout container = (LinearLayout) mActivity.findViewById(R.id.fragment_category_edit);
                                assertNotNull(container);
                                CategoryEditFragment test = new CategoryEditFragment();
                                mActivity.getSupportFragmentManager().beginTransaction().add(container.getId(), test).commitAllowingStateLoss();
                                getInstrumentation().waitForIdleSync();
                                View view = test.getView();
                                assertNotNull(view);
                            }
                        } else {

                        }

                    }
                });

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}