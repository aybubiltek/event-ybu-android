package tr.edu.ybu.eventandroid;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zeynep on 23.08.2016.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    //shared pref mode
    int PRIVATE_MODE=0;

    //shared preferences file name
    private static final String PREF_NAME="ybu-welcome";
    private static final String IS_FIRST_TIME_LAUNCH="IsFirstTimeLaunch";

    public PrefManager(Context context){
        this._context=context;
        pref=_context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=pref.edit();
    }
    public void setIsFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
}
