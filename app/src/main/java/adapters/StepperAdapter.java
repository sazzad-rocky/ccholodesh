package adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.stepstone.stepper.adapter.AbstractStepAdapter;

import java.util.ArrayList;

import fragments.RouteSelectionFragment;

/**
 * Created by Code Freak Tanveer on 09/10/2016.
 */
public class StepperAdapter extends AbstractStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private Context context;


    public StepperAdapter(FragmentManager fm, Context context) {
        super(fm);
        final RouteSelectionFragment routeSelectionFragment = new RouteSelectionFragment();
        routeSelectionFragment.onAttach(context);
        fragments.add(routeSelectionFragment);
    }
    @Override
    public Fragment createStep(int position) {
        Fragment step= fragments.get(position);

        Bundle b = new Bundle();
        b.putInt(CURRENT_STEP_POSITION_KEY, position);
        step.setArguments(b);
        return step;
    }

    @Override
    public int getCount() {
        return 1;
    }


}