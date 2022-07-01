package com.app.cardiolog.Models;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.app.cardiolog.R;

public class DialogFragment extends android.app.DialogFragment {
    private static final String TAG = "DialogFragment";

    public interface OnInputListener {
        void sendInput(int sysval,int diaval,int bpm,String comment);
    }
    public OnInputListener mOnInputListener;

    EditText sysInput,diaInput,bpmInput,commentInput;
    Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View v= inflater.inflate(R.layout.inputfragment,container,false);
        sysInput=v.findViewById(R.id.systolic);
        diaInput=v.findViewById(R.id.diastolic);
        bpmInput=v.findViewById(R.id.bpm_in);
        commentInput=v.findViewById(R.id.comment_in);
        mButton=v.findViewById(R.id.add_btn);

        Bundle bundle =new Bundle();
        bundle.putInt("sysval",sysInput.getInputType());
        bundle.putInt("diaval",diaInput.getInputType());
        bundle.putInt("bpmval",bpmInput.getInputType());
        bundle.putString("commentval",commentInput.getText().toString());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sysval = sysInput.getInputType();
                int diaval = diaInput.getInputType();
                int bpm = bpmInput.getInputType();
                String com=commentInput.getText().toString();
                mOnInputListener.sendInput(sysval,diaval,bpm,com);
                getDialog().dismiss();
            }
        });

return v;
    }

    @Override public void onAttach(Context context)
    {
        super.onAttach(context);
        try {
            mOnInputListener = (OnInputListener)getActivity();
        }
        catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: "
                    + e.getMessage());
        }
    }


}
